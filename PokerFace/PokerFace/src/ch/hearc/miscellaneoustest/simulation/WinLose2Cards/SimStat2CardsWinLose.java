
package ch.hearc.miscellaneoustest.simulation.WinLose2Cards;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

import ch.hearc.miscellaneoustest.simulation.Deck;
import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.cards.CardValue;
import ch.hearc.pokerface.gameengine.compute.ComputeBestHand;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.compute.HandsPokerValue;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.CardSubset;
import ch.hearc.pokerface.gameengine.subsets.Hand;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class SimStat2CardsWinLose
{
	private static final int					NB_SIMULATION		= 10000;
	private static final int					NB_CARDS_IN_BOARD	= 5;
	private static final int					NB_TOT_PLAYERS		= 2;
	private static final int					NB_CORE				= Runtime.getRuntime().availableProcessors();
	private static ConcurrentMap<String, Data>	charts				= Method.createConcurrentHashMap();
	private static Record[]						records				= new Record[169];
	private static Card[]						value				= { new Card(CardValue.Ace, CardColor.Clubs), new Card(CardValue.Two, CardColor.Clubs), new Card(CardValue.Three, CardColor.Clubs), new Card(CardValue.Four, CardColor.Clubs), new Card(CardValue.Five, CardColor.Clubs),
			new Card(CardValue.Six, CardColor.Clubs), new Card(CardValue.Seven, CardColor.Clubs), new Card(CardValue.Eight, CardColor.Clubs), new Card(CardValue.Nine, CardColor.Clubs), new Card(CardValue.Ten, CardColor.Clubs), new Card(CardValue.Jack, CardColor.Clubs),
			new Card(CardValue.Queen, CardColor.Clubs), new Card(CardValue.King, CardColor.Clubs) };
	private static Card[]						value2				= { new Card(CardValue.Ace, CardColor.Spades), new Card(CardValue.Two, CardColor.Spades), new Card(CardValue.Three, CardColor.Spades), new Card(CardValue.Four, CardColor.Spades), new Card(CardValue.Five, CardColor.Spades),
			new Card(CardValue.Six, CardColor.Spades), new Card(CardValue.Seven, CardColor.Spades), new Card(CardValue.Eight, CardColor.Spades), new Card(CardValue.Nine, CardColor.Spades), new Card(CardValue.Ten, CardColor.Spades), new Card(CardValue.Jack, CardColor.Spades),
			new Card(CardValue.Queen, CardColor.Spades), new Card(CardValue.King, CardColor.Spades) };

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args) throws IOException, InterruptedException
	{
		main();
	}

	public static void main() throws IOException, InterruptedException
	{
		int index = 0;
		for(int a = 0; a < value.length; ++a)
		{
			for(int b = a; b < value.length; ++b)
			{
				records[index++] = new Record(value[a], value2[b], false);
				if (!value[a].getValue().equals(value2[b].getValue()))
				{
					records[index++] = new Record(value[a], value[b], true);
				}
			}
		}

		Thread[] threads = new Thread[NB_CORE];
		int range = records.length / NB_CORE;


		for(int i = 0; i < NB_CORE; ++i)
		{
			final int iStart = range * i;
			final int iEnd = (i < (NB_CORE - 1) ? iStart + range : records.length);
			threads[i] = new Thread(new Runnable()
			{

				@Override
				public void run()
				{
					for(int i = iStart; i < iEnd; ++i)
					{
						simPerThread(records[i].getC1(), records[i].getC2(), records[i].getSame());
					}
				}
			});
		}

		long start = System.currentTimeMillis();
		for(int i = 0;i < NB_CORE; ++i)
		{
			threads[i].start();
		}

		for(int i = 0; i < NB_CORE; ++i)
		{
			threads[i].join();
		}
		System.out.println(System.currentTimeMillis() - start);

		Method.displayMap(charts);
		//Method.writeMap(charts, "Stats2Cards_WinLose.txt");
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void simPerThread(Card c1, Card c2, boolean same)
	{
		Pocket[] handPlayers = new Pocket[NB_TOT_PLAYERS];
		Hand[] bestHandPlayers = new Hand[NB_TOT_PLAYERS];

		handPlayers[0] = new Pocket();
		handPlayers[0].add(c1);
		handPlayers[0].add(c2);

		Card[] cards = handPlayers[0].getArray();
		StringBuilder key = new StringBuilder();
		key.append(cards[0].getValue().getStringValue());
		key.append(cards[1].getValue().getStringValue());
		key.append((same) ? "s" : "o");

		HandsPokerMap hpm = HandsPokerMap.getInstance();

		Data data = charts.get(key.toString());

		for(int i = 0; i < NB_SIMULATION; ++i)
		{
			Deck d = new Deck();
			d.remove(c1, true);
			d.remove(c2, true);
			Board board = new Board();

			for(int j = 1; j < NB_TOT_PLAYERS; ++j)//Start at 1, because first hand was generated
			{
				handPlayers[j] = new Pocket();
				handPlayers[j].add(d.getNewCard());
				handPlayers[j].add(d.getNewCard());
			}

			for(int j = 0; j < NB_CARDS_IN_BOARD; ++j)
			{
				board.add(d.getNewCard());
			}

			for(int j = 0; j < NB_TOT_PLAYERS; ++j)
			{
				bestHandPlayers[j] = new ComputeBestHand(CardSubset.union(handPlayers[j], board)).getHighestHand();
			}

			boolean isHumanPlayerBest = true;
			HandsPokerValue bestHandHumanPlayer = hpm.getHand(bestHandPlayers[0]);
			for(int j = 1;isHumanPlayerBest &&  j < NB_TOT_PLAYERS; ++j)
			{
				if (hpm.getHand(bestHandPlayers[j]).compareTo(bestHandHumanPlayer) > 0)
				{
					isHumanPlayerBest = false;
				}
			}

			if (isHumanPlayerBest)//The human player has the best hand
			{
				data.addWin();
			}
			else
			{
				data.addTime();
			}
		}
	}
}
