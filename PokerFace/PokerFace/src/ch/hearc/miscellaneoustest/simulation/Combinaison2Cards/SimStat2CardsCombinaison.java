
package ch.hearc.miscellaneoustest.simulation.Combinaison2Cards;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;
import ch.hearc.miscellaneoustest.handpoker.cards.CardColor;
import ch.hearc.miscellaneoustest.handpoker.cards.CardValue;
import ch.hearc.miscellaneoustest.handpoker.subset.Board;
import ch.hearc.miscellaneoustest.handpoker.subset.CardSubset;
import ch.hearc.miscellaneoustest.handpoker.subset.Pocket;
import ch.hearc.miscellaneoustest.simulation.Deck;

public class SimStat2CardsCombinaison
{
	private static final int	NB_SIMULATION		= 1000000000;
	private static final int	NB_CARDS_IN_BOARD	= 5;

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
				new Card(CardValue.King, CardColor.Clubs) };

		Card[] value2 = { new Card(CardValue.Ace, CardColor.Spades), new Card(CardValue.Two, CardColor.Spades), new Card(CardValue.Three, CardColor.Spades), new Card(CardValue.Four, CardColor.Spades), new Card(CardValue.Five, CardColor.Spades), new Card(CardValue.Six, CardColor.Spades),
				new Card(CardValue.Seven, CardColor.Spades), new Card(CardValue.Eight, CardColor.Spades), new Card(CardValue.Nine, CardColor.Spades), new Card(CardValue.Ten, CardColor.Spades), new Card(CardValue.Jack, CardColor.Spades), new Card(CardValue.Queen, CardColor.Spades),
				new Card(CardValue.King, CardColor.Spades) };

		long start = System.currentTimeMillis();
		for(int a = 0; a < value.length; ++a)
		{
			for(int b = a; b < value.length; ++b)
			{
				Pocket handPlayer = new Pocket();
				handPlayer.add(value[a]);
				handPlayer.add(value2[b]);

				runSimu(charts, handPlayer);

				if (a != b)
				{
					Pocket handPlayerSame = new Pocket();
					handPlayerSame.add(value[a]);
					handPlayerSame.add(value[b]);

					runSimu(charts, handPlayerSame);
				}
			}
		}

		Method.displayMap(charts);
		System.out.println(System.currentTimeMillis() - start);
		//maps.add(charts);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void runSimu(final ConcurrentMap<String, Data> charts, final Pocket handPlayer) throws InterruptedException
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
					simPerThread(charts, handPlayer, NB_SIMULATION / nbCore);
				}
			});
			t[i].start();
		}

		for(int i = 0; i < nbCore; ++i)
		{
			t[i].join();
		}
	}

	private static void simPerThread(ConcurrentMap<String, Data> charts, Pocket handPlayers, int nbSimulation)
	{
		for(int i = 0; i < nbSimulation; ++i)
		{
			StringBuilder keySB = new StringBuilder();

			Deck d = new Deck();
			int val = -1;
			for(Card c:handPlayers.getArray())
			{
				d.remove(c, true);
				keySB.append(c.getValue().getStringValue());
				if (val == -1)
				{
					val = c.getColor().getIntValue();
				}
				else
				{
					if (val == c.getColor().getIntValue())
					{
						keySB.append("s");
					}
					else
					{
						keySB.append("o");
					}
				}
			}
			String key = keySB.toString();
			Board board = new Board();

			for(int j = 0; j < NB_CARDS_IN_BOARD; ++j)
			{
				board.add(d.getNewCard());
			}

			Card[] cards = CardSubset.union(handPlayers, board).getArray();

			//Built histogram of cards
			Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

			//Built histogram of colors
			Map<CardColor, Integer> mapColor = new HashMap<CardColor, Integer>();
			mapColor.put(CardColor.Clubs, 0);
			mapColor.put(CardColor.Diamonds, 0);
			mapColor.put(CardColor.Hearts, 0);
			mapColor.put(CardColor.Spades, 0);

			for(Card c:cards)
			{
				mapColor.put(c.getColor(), mapColor.get(c.getColor()) + 1);
				if (map.containsKey(c.getValue().getIntValue()))
				{
					map.put(c.getValue().getIntValue(), map.get(c.getValue().getIntValue()) + 1);
				}
				else
				{
					map.put(c.getValue().getIntValue(), 1);
				}
			}

			boolean straight = false;
			String[] availableStraight = { "AJKQT", "9JKQT", "89JQT", "789JT", "6789T", "56789", "45678", "34567", "23456", "2345A" };

			for(int k = 0; k < 2 && !straight; ++k)
			{
				for(String str:availableStraight)
				{
					if (str.equals(cards[k].getValue().getStringValue() + cards[k + 1].getValue().getStringValue() + cards[k + 2].getValue().getStringValue() + cards[k + 3].getValue().getStringValue() + cards[k + 4].getValue().getStringValue()))
					{
						straight = true;
						break;
					}
				}
			}

			for(Entry<CardColor, Integer> entry:mapColor.entrySet())
			{
				if (entry.getValue() >= 5)
				{
					if (straight)
					{
						charts.get(key).addTime("SF");
						return;
					}
					else
					{
						charts.get(key).addTime("F");
						return;
					}
				}
			}

			if (straight)
			{
				charts.get(key).addTime("S");
				return;
			}

			int nbPair = 0;
			boolean k3 = false;
			if (map.size() <= 6)
			{
				for(Entry<Integer, Integer> entry:map.entrySet())
				{
					if (entry.getValue() == 4)
					{
						charts.get(key).addTime("4K");
						return;
					}
					else if (entry.getValue() == 3)
					{
						k3 = true;
					}
					else if (entry.getValue() == 2)
					{
						nbPair++;
					}
				}

				if (k3 && nbPair >= 1)//3k + 1pair (2 possible)
				{
					charts.get(key).addTime("FH");
				}
				else if (k3)
				{
					charts.get(key).addTime("3K");
				}
				else if (nbPair == 1)
				{
					charts.get(key).addTime("1P");
				}
				else if (nbPair > 1)//2 or 3
				{
					charts.get(key).addTime("2P");
				}
			}
			else
			{
				charts.get(key).addTime("HC");
			}
		}
	}
}
