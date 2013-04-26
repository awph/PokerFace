
package ch.hearc.pokerface.gameengine.statistics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.compute.ComputeBestHand;
import ch.hearc.pokerface.gameengine.compute.HandType;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.statistics.simulation.Simulation;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.CardSubset;
import ch.hearc.pokerface.gameengine.subsets.Deck;
import ch.hearc.pokerface.gameengine.subsets.Hand;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class Statistics
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private final static String											FILENAME_XML		= "file";
	private final static Map<StateType, Map<String, StatisticValue>>[]	maps				= XMLReader.getXMLValue(FILENAME_XML);

	public final static int												NUMBER_CARDS_FLOP	= 3;
	public final static int												NUMBER_CARDS_TURN	= 4;
	public final static int												NUMBER_CARDS_RIVER	= 5;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public static StatisticValue getPreFlopValues(Pocket p, int nbPlayer)
	{
		return maps[nbPlayer - XMLReader.NB_MIN_PLAYER].get(StateType.PreFlopState).get(p.getKey());
	}

	/**
	 * Return Flop/Turn/River values, depending of the size of the board : 3 -> Flop, 4 -> Tur, 5 -> River
	 *
	 * @param p
	 * @param b
	 * @param nbPlayer
	 * @return 0 -> Flop, 1 -> Turn, 2 -> River
	 * @return
	 */
	public static void getFlopTurnRiverValues(final Player player, final Pocket pocket, final Card[] board, int nbPlayer)
	{
		final Simulation flop = new Simulation(pocket, board, nbPlayer, NUMBER_CARDS_FLOP);
		final Simulation turn = new Simulation(pocket, board, nbPlayer, NUMBER_CARDS_TURN);
		final Simulation river = new Simulation(pocket, board, nbPlayer, NUMBER_CARDS_RIVER);

		flop.addObserver(player);
		turn.addObserver(player);
		river.addObserver(player);

		new Thread(flop).start();
		new Thread(turn).start();
		new Thread(river).start();
	}

	//public static int getOuts(Pocket pocket, Board board)
	public static CardSubset getOuts(Pocket pocket, Board board)
	{
		HandsPokerMap hpm = HandsPokerMap.getInstance();

		Deck deck = new Deck();
		deck.sub(board);
		deck.sub(pocket);

		CardSubset visibleCards = new CardSubset();

		for(Card c:board)
		{
			visibleCards.add(c);
		}

		for(Card c:pocket)
		{
			visibleCards.add(c);
		}

		Map<Card, HandType> outs = new HashMap<Card, HandType>();
		Hand hand = new ComputeBestHand(visibleCards).getHighestHand();
		int handRank = hpm.getHand(hand).getRank();
		HandType handType = hpm.getHand(hand).getHandType();
		HandType highestHandType = handType;

		for(Card card:deck)
		{
			//TODO: enlever les kicker des suites ouvertes, si sur les 4 cartes on complete la sute par le bas, ce n'est pas un out
			CardSubset cardSubsetTemp = visibleCards.cloneOf();
			cardSubsetTemp.add(card);
			Hand newHand = new ComputeBestHand(cardSubsetTemp).getHighestHand();
			int newHandRank = hpm.getHand(newHand).getRank();
			HandType newHandType = hpm.getHand(newHand).getHandType();

			Board newBoard = board.cloneOf();
			newBoard.add(card);
			int newBoardRank = hpm.getHand(newBoard.getKey(deck)).getRank();
			HandType newBoardType = hpm.getHand(newBoard.getKey(deck)).getHandType();

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

			boolean outEnoughStrong = true;
			if (newHandType.getIntValue() < highestHandType.getIntValue() - 2 && highestHandType.getIntValue() > HandType.TwoPair.getIntValue())
			{
				outEnoughStrong = false;
			}

			highestHandType = (newHandType.getIntValue() > highestHandType.getIntValue()) ? newHandType : highestHandType;

			// If the hand improved then we have an out
			if (handImproved && handStrongerThanBoard && outEnoughStrong)
			{
				outs.put(card, newHandType);
			}
		}

		CardSubset myOuts = new CardSubset();
		for(Card card:outs.keySet())
		{
			HandType type =  outs.get(card);
		//	if (!(type.getIntValue() < highestHandType.getIntValue() - 2 && highestHandType.getIntValue() > HandType.TwoPair.getIntValue()))
			//{
				myOuts.add(card);
			//}
		}

		return myOuts;
	}
}
