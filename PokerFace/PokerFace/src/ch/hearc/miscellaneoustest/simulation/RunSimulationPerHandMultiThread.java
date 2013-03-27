
package ch.hearc.miscellaneoustest.simulation;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

import ch.hearc.miscellaneoustest.handpoker.ComputeBestHandInASubset;
import ch.hearc.miscellaneoustest.handpoker.HandsPokerMap;
import ch.hearc.miscellaneoustest.handpoker.cards.Card;
import ch.hearc.miscellaneoustest.handpoker.cards.CardColor;
import ch.hearc.miscellaneoustest.handpoker.cards.CardValue;
import ch.hearc.miscellaneoustest.handpoker.subset.CardSubset;
import ch.hearc.miscellaneoustest.handpoker.subset.Deck;
import ch.hearc.miscellaneoustest.handpoker.subset.Hand;
import ch.hearc.miscellaneoustest.handpoker.subset.Pocket;

public class RunSimulationPerHandMultiThread
{
	private static final int	NB_SIMULATION		= 10000;
	private static final int	NB_CARDS_IN_BOARD	= 5;
	private static final int	NB_PLAYER			= 9;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args) throws IOException, InterruptedException
	{
		main();
	}

	public static void main() throws IOException, InterruptedException
	{
		ConcurrentMap<String, Data> charts = Method.createConcurrentHashMap();

		Card[] value = { new Card(CardValue.Ace, CardColor.Clubs), new Card(CardValue.Two, CardColor.Clubs), new Card(CardValue.Three, CardColor.Clubs), new Card(CardValue.Four, CardColor.Clubs), new Card(CardValue.Five, CardColor.Clubs), new Card(CardValue.Six, CardColor.Clubs),
				new Card(CardValue.Seven, CardColor.Clubs), new Card(CardValue.Eight, CardColor.Clubs), new Card(CardValue.Nine, CardColor.Clubs), new Card(CardValue.Ten, CardColor.Clubs), new Card(CardValue.Jack, CardColor.Clubs), new Card(CardValue.Queen, CardColor.Clubs),
				new Card(CardValue.King, CardColor.Clubs)};

		Card[] value2 = {new Card(CardValue.Ace, CardColor.Spades), new Card(CardValue.Two, CardColor.Spades), new Card(CardValue.Three, CardColor.Spades), new Card(CardValue.Four, CardColor.Spades), new Card(CardValue.Five, CardColor.Spades),
				new Card(CardValue.Six, CardColor.Spades), new Card(CardValue.Seven, CardColor.Spades), new Card(CardValue.Eight, CardColor.Spades), new Card(CardValue.Nine, CardColor.Spades), new Card(CardValue.Ten, CardColor.Spades), new Card(CardValue.Jack, CardColor.Spades),
				new Card(CardValue.Queen, CardColor.Spades), new Card(CardValue.King, CardColor.Spades) };

		long start = System.currentTimeMillis();
		for(int a = 0; a < value.length; ++a)
		{
			for(int b = a; b < value.length; ++b)
			{
				runSimu(charts, value[a], value2[b], false);
				if (!value[a].getValue().equals(value2[b].getValue()))
				{
					runSimu(charts, value[a], value2[b], true);
				}
			}
		}

		//displayMap(charts);
		Method.writeMap(charts);
		System.out.println(System.currentTimeMillis() - start);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void runSimu(final ConcurrentMap<String, Data> charts, final Card c1, final Card c2, final boolean same) throws InterruptedException
	{
		final int nbCore = Runtime.getRuntime().availableProcessors();
		Thread[] t = new Thread[nbCore];

		for(int i = 0; i < nbCore; ++i)
		{
			t[i] = new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					simPerThread(charts, c1, c2, same,NB_SIMULATION/nbCore);
				}
			});
			t[i].start();
		}

		for(int i = 0;i < nbCore;++i)
		{
			t[i].join();
		}
	}

	private static void simPerThread(ConcurrentMap<String, Data> charts, Card c1, Card c2, boolean same,int nbSimulation)
	{
		Pocket[] handPlayers = new Pocket[NB_PLAYER];
		Hand[] bestHandPlayers = new Hand[NB_PLAYER];

		handPlayers[0] = new Pocket();
		handPlayers[0].add(c1);
		handPlayers[0].add(c2);

		for(int i = 0; i < nbSimulation; ++i)
		{
			Deck d = new Deck();
			d.remove(c1, true);
			d.remove(c2, true);
			CardSubset subset = new CardSubset();

			for(int j = 1; j < NB_PLAYER; ++j)//Start at 1, because first hand was generated
			{
				handPlayers[j] = new Pocket();
				handPlayers[j].add(d.getNewCard());
				handPlayers[j].add(d.getNewCard());
			}

			for(int j = 0; j < NB_CARDS_IN_BOARD; ++j)
			{
				subset.add(d.getNewCard());
			}

			for(int j = 0; j < NB_PLAYER; ++j)
			{
				bestHandPlayers[j] = new ComputeBestHandInASubset(CardSubset.union(handPlayers[j], subset)).getHighestHand();
			}

			HandsPokerMap hpm = HandsPokerMap.getInstance();

			int index_best = 0;
			for(int j = 1; j < NB_PLAYER; ++j)
			{
				if (hpm.getHand(bestHandPlayers[j]).compareTo(hpm.getHand(bestHandPlayers[index_best])) > 0)
				{
					index_best = j;
				}
			}

			int j = 0;
			for(Pocket p:handPlayers)
			{
				Card[] cards = p.getArray();

				StringBuilder key = new StringBuilder();
				key.append(cards[0].getValue().getStringValue());
				key.append(cards[1].getValue().getStringValue());

				if (same && !cards[0].getValue().getStringValue().equals(cards[1].getValue().getStringValue()))
				{
					key.append("s");
				}
				else
				{
					key.append("o");
				}

				charts.get(key.toString()).addTime();
				if (j++ == index_best)
				{
					charts.get(key.toString()).addWin();
				}
			}
		}
	}
}
