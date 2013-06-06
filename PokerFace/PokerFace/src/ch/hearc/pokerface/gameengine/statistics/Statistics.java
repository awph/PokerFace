
package ch.hearc.pokerface.gameengine.statistics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

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

	private final static String											FILENAME_XML		= "datas/preflop_values";
	private final static Map<StateType, Map<String, StatisticValue>>[]	maps				= XMLReader.getXMLValue(FILENAME_XML);
	private static Map<String, Double>[]								chanceOfCall;
	public final static int												NUMBER_CARDS_FLOP	= 3;
	public final static int												NUMBER_CARDS_TURN	= 4;
	public final static int												NUMBER_CARDS_RIVER	= 5;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	@SuppressWarnings("unchecked")
	/**
	 * Initialize the variable chanceOfCall
	 */
	public static void initialize()
	{
		chanceOfCall = new HashMap[maps.length];

		for(int i = 0; i < maps.length; ++i)
		{
			chanceOfCall[i] = new HashMap<String, Double>();
			double max = 0;
			Set<Entry<String, StatisticValue>> entrySet = maps[i].get(StateType.PreFlopState).entrySet();
			for(Entry<String, StatisticValue> entry:entrySet)
			{
				if (entry.getValue().getWin() > max)
				{
					max = entry.getValue().getWin();
				}
			}

			for(Entry<String, StatisticValue> entry:entrySet)
			{
				chanceOfCall[i].put(entry.getKey(), entry.getValue().getWin()/max*100.0);
			}
		}
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/**
	 * Return the pourcentage to call the bet
	 *
	 * @param p
	 *            : Player's pocket
	 * @param nbPlayer
	 *            : Number of players
	 */
	public static double getChanceHandValuePreFlop(Pocket p, int nbPlayer)
	{
		return chanceOfCall[nbPlayer - XMLReader.NB_MIN_PLAYER].get(p.getKey());
	}

	/**
	 * Return Preflop values
	 *
	 * @param p
	 *            : Player's pocket
	 * @param nbPlayer
	 *            : Number of players
	 */
	public static StatisticValue getPreFlopValues(Pocket p, int nbPlayer)
	{
		return maps[nbPlayer - XMLReader.NB_MIN_PLAYER].get(StateType.PreFlopState).get(p.getKey());
	}

	/**
	 * Return Flop/Turn/River values, depending of the size of the board : 3 -> Flop, 4 -> Tur, 5 -> River
	 *
	 * @param p
	 *            : Player's pocket
	 * @param b
	 *            : Board
	 * @param nbPlayer
	 *            : Number of players
	 * @return 0 -> Flop, 1 -> Turn, 2 -> River
	 */
	public static void getFlopTurnRiverValues(final Player player, final Pocket pocket, final Card[] board, int nbPlayer, int nbCardInBoard)
	{
		final Simulation simulation = new Simulation(pocket, board, nbPlayer, nbCardInBoard);

		simulation.addObserver(player);
		player.addObserver(simulation);

		Thread thread = new Thread(simulation);
		thread.setName("Simulation for " + player.getProfile().getName() + " with " + nbCardInBoard + " cards");
		thread.start();
	}

	/**
	 * Return the number of outs
	 *
	 * @param pocket
	 *            : Player's pocket
	 * @param board
	 *            : Board
	 */
	public static int getOuts(Pocket pocket, Board board)
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

		CardSubset outs = new CardSubset();
		Hand currentHand = new ComputeBestHand(visibleCards).getHighestHand();
		int currentHandRank = hpm.getHand(currentHand).getRank();
		HandType currentHandType = hpm.getHand(currentHand).getHandType();
		HandType highestHandType = currentHandType;
		HandType highestBoardType = HandType.HighCard;

		for(Card card:deck)
		{
			CardSubset futureVisibleCards = visibleCards.cloneOf();
			futureVisibleCards.add(card);

			Hand newHand = new ComputeBestHand(futureVisibleCards).getHighestHand();
			int newHandRank = hpm.getHand(newHand).getRank();
			HandType newHandType = hpm.getHand(newHand).getHandType();

			highestHandType = (newHandType.getIntValue() > highestHandType.getIntValue()) ? newHandType : highestHandType;

			Board newBoard = board.cloneOf();
			newBoard.add(card);
			HandType newBoardType = hpm.getHand(newBoard.getKey(deck)).getHandType();

			highestBoardType = (newBoardType.getIntValue() > highestBoardType.getIntValue()) ? newBoardType : highestBoardType;

			// Is the new hand better than the old one?
			boolean handImproved = newHandRank < currentHandRank && newHandType.getIntValue() > currentHandType.getIntValue();

			// Is the new hand better than the new board? (eliminate the kickers)
			boolean handStrongerThanBoard = newHandType.getIntValue() > newBoardType.getIntValue();

			if (currentHandType == HandType.OnePair && handImproved && handStrongerThanBoard)
			{
				handStrongerThanBoard = (newHandType.getIntValue() >= HandType.ThreeOfKind.getIntValue());// We want a three of a kind minimum
			}

			boolean outEnoughStrong = true;
			if (newHandType.getIntValue() == HandType.OnePair.getIntValue() && handImproved && handStrongerThanBoard)
			{
				outEnoughStrong = false;
			}

			if (newHandType.getIntValue() == HandType.Straight.getIntValue() && handImproved && handStrongerThanBoard && outEnoughStrong)
			{
				Set<Card> boardSortByCardValue = new TreeSet<Card>(new Comparator<Card>()
				{
					@Override
					public int compare(Card card1, Card card2)
					{
						if (card1.getValue().equals(card2.getValue()))
						{
							return ((Integer)(card1.getColor().getIntValue())).compareTo(card2.getColor().getIntValue());
						}
						else
						{
							return card1.getValue().compareTo(card2.getValue());
						}
					}
				});

				for(Card c:newBoard)
				{
					boardSortByCardValue.add(c);
				}

				Iterator<Card> boardIterator = boardSortByCardValue.iterator();
				Card boardCard = boardIterator.next();
				int cardValue = boardCard.getValue().getIntValue();
				boolean isOpenStraight = true;
				while(boardIterator.hasNext())
				{
					boardCard = boardIterator.next();
					if (boardCard.getValue().getIntValue() == cardValue + 1)
					{
						cardValue++;
					}
					else
					{
						isOpenStraight = false;
					}
				}
				if (isOpenStraight)
				{
					Iterator<Card> pocketIterator = pocket.iterator();
					while(pocketIterator.hasNext())
					{
						Card pocketCard = pocketIterator.next();
						if (pocketCard.getValue().getIntValue() == cardValue + 1)
						{
							outEnoughStrong = true;
							break; //On complete la suite par le haut
						}
						else
						{
							outEnoughStrong = false;
						}
					}
				}
			}

			// If the hand improved then we have an out
			if (handImproved && handStrongerThanBoard && outEnoughStrong)
			{
				outs.add(card);
			}
		}

		if (highestBoardType.getIntValue() <= HandType.OnePair.getIntValue() && highestHandType.getIntValue() <= HandType.Straight.getIntValue())
		{
			// Counting overcard
			Iterator<Card> pocketIterator = pocket.iterator();
			while(pocketIterator.hasNext())
			{
				boolean isAnOvercard = true;
				Iterator<Card> boardIterator = board.iterator();
				Card pocketCard = pocketIterator.next();
				while(boardIterator.hasNext() && isAnOvercard)
				{
					Card boardCard = boardIterator.next();
					if (pocketCard.getValue().getIntValue() < boardCard.getValue().getIntValue())
					{
						isAnOvercard = false;
					}
				}
				if (isAnOvercard)
				{
					for(Card card:deck)
					{
						if (card.getValue().getIntValue() == pocketCard.getValue().getIntValue())
						{
							outs.add(card);
						}
					}
				}
			}
		}

		if (outs.size() == 0)
		{
			Iterator<Card> pocketIterator = pocket.iterator();
			while(pocketIterator.hasNext())
			{
				Card pocketCard = pocketIterator.next();
				for(Card card:deck)
				{
					if (card.getValue().getIntValue() == pocketCard.getValue().getIntValue())
					{
						outs.add(card);
					}
				}
			}
		}

		return outs.size();
	}

}
