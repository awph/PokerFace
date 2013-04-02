
package ch.hearc.miscellaneoustest.handpoker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;
import ch.hearc.miscellaneoustest.handpoker.subset.Board;
import ch.hearc.miscellaneoustest.handpoker.subset.CardSubset;
import ch.hearc.miscellaneoustest.handpoker.subset.Deck;
import ch.hearc.miscellaneoustest.handpoker.subset.Hand;
import ch.hearc.miscellaneoustest.handpoker.subset.Pocket;

public class HandsPokerMap
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private static final String				FILENAME		= "pokerHands.txt";
	private static final String				SEPARATOR		= ";";
	private static final int				INDEX_KEY		= 1;
	private static final int				INDEX_RANK		= 0;
	private static final int				INDEX_HAND_NAME	= 2;
	private static HandsPokerMap			instance;

	private Map<String, HandsPokerValue>	hands;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public HandsPokerValue getHand(String key)
	{
		try
		{
			return hands.get(new String(key.toCharArray()));
		}
		catch (NullPointerException e)
		{
			return null;
		}
	}

	public HandsPokerValue getHand(Hand hand)
	{
		return hands.get(hand.getKey());
	}

	public CardSubset getOuts(Board board, Pocket pocket)
	{
		Deck deck = new Deck();
		deck.sub(board);
		deck.sub(pocket);

		Hand hand = new Hand();

		for(Card c:board)
		{
			hand.add(c);
		}

		for(Card c:pocket)
		{
			hand.add(c);
		}

		CardSubset outs = new CardSubset();
		int handRank = getHand(hand).getRank();
		HandType handType = getHand(hand).getHandType();

		for(Card card:deck)
		{
			//TODO: mettre une value sur le nom de la main
			//TODO: enlever les kicker des suites ouvertes
			//TODO:si sur les 4 cartes on complete la sute par le bas, ce n'est pas un out
			CardSubset cardSubsetTemp = hand.cloneOf();
			cardSubsetTemp.add(card);
			Hand newHand = new ComputeBestHandInASubset(cardSubsetTemp).getHighestHand();
			int newHandRank = getHand(newHand).getRank();
			HandType newHandType = getHand(newHand).getHandType();

			Board newBoard = board.cloneOf();
			newBoard.add(card);
			int newBoardRank = getHand(newBoard.getKey(deck)).getRank();
			HandType newBoardType = getHand(newBoard.getKey(deck)).getHandType();

			// Is the new hand better than the old one?
			boolean handImproved = newHandRank < handRank && newHandType.getIntValue() > handType.getIntValue();

			// Is the new hand better than the new board? (eliminate the kickers)
			boolean handStrongerThanBoard = newHandType.getIntValue() > newBoardType.getIntValue();

			if (handType == HandType.OnePair)
			{
				handStrongerThanBoard = newHandType.getIntValue() - newBoardType.getIntValue() > 1; // We want a three of a kind minimum
			}

			if (handType == HandType.HighCard && newHandType == HandType.OnePair)
			{
				if (handStrongerThanBoard) // Pair with a pocket card
				{
					int i = 0;
					Iterator<Card> iterator = board.iterator();
					while(iterator.hasNext())
					{
						Iterator<Card> pocketIterator = pocket.iterator();
						Card boardCard = iterator.next();
						while(pocketIterator.hasNext())
						{
							if (boardCard.getValue().getIntValue() > pocketIterator.next().getValue().getIntValue()) // when the pocket is lower than the board
							{
								i++;
								break;
							}
						}
					}
					if (i == 3)
					{
						handStrongerThanBoard = true;
					}
					else
					{
						iterator = newBoard.iterator();
						while(iterator.hasNext())
						{
							if (iterator.next().getValue().getIntValue() > card.getValue().getIntValue())
							{
								handStrongerThanBoard = false;
							}
						}
					}
				}
			}

			// If the hand improved then we have an out
			if (handImproved && handStrongerThanBoard)
			{
				outs.add(card);
			}
		}

		return outs;
	}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public synchronized static HandsPokerMap getInstance()
	{
		if (instance == null)
		{
			instance = new HandsPokerMap();
		}
		return instance;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void fill()
	{
		FileReader fr;
		BufferedReader br;
		try
		{
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String line;

			while((line = br.readLine()) != null)
			{
				String[] values = line.split(SEPARATOR);

				hands.put(values[INDEX_KEY], new HandsPokerValue(Integer.valueOf(values[INDEX_RANK]), values[INDEX_HAND_NAME], values[INDEX_HAND_NAME]));
			}

			br.close();
			fr.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File not found !");
		}
		catch (IOException e)
		{
			System.err.println("Error during reading file !");
		}

	}

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private HandsPokerMap()
	{
		hands = new HashMap<String, HandsPokerValue>();
		fill();
	}
}
