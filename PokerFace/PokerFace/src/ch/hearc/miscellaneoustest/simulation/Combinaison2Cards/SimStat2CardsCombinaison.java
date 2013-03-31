
package ch.hearc.miscellaneoustest.simulation.Combinaison2Cards;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

import ch.hearc.miscellaneoustest.handpoker.ComputeBestHandInASubset;
import ch.hearc.miscellaneoustest.handpoker.HandsPokerMap;
import ch.hearc.miscellaneoustest.handpoker.cards.Card;
import ch.hearc.miscellaneoustest.handpoker.cards.CardColor;
import ch.hearc.miscellaneoustest.handpoker.cards.CardValue;
import ch.hearc.miscellaneoustest.handpoker.subset.Board;
import ch.hearc.miscellaneoustest.handpoker.subset.CardSubset;
import ch.hearc.miscellaneoustest.handpoker.subset.Hand;
import ch.hearc.miscellaneoustest.handpoker.subset.Pocket;
import ch.hearc.miscellaneoustest.simulation.Deck;


public class SimStat2CardsCombinaison
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
		Pocket handPlayers = new Pocket();
		handPlayers.add(c1);
		handPlayers.add(c2);

		//Key for the charts (The same for all simulations
		Card[] cards = handPlayers.getArray();
		StringBuilder key = new StringBuilder(cards[0].getValue().getStringValue());
		key.append(cards[1].getValue().getStringValue());
		key.append((same) ? "s" : "o");

		HandsPokerMap hpm = HandsPokerMap.getInstance();

		Hand bestHandPlayer;

		Data data = charts.get(key.toString());

		for(int i = 0; i < NB_SIMULATION; ++i)
		{
			Deck d = new Deck();
			d.remove(c1, true);
			d.remove(c2, true);
			Board board = new Board();

			//We remove 2*n cards, n number of players
			for(int j = 1; j < NB_TOT_PLAYERS; ++j)//Start at 1, because first hand was generated
			{
				d.getNewCard();
				d.getNewCard();
			}

			for(int j = 0; j < NB_CARDS_IN_BOARD; ++j)
			{
				board.add(d.getNewCard());
			}

			bestHandPlayer = new ComputeBestHandInASubset(CardSubset.union(handPlayers, board)).getHighestHand();

			data.addTime(hpm.getHand(bestHandPlayer).getHandName());
		}
	}
}
