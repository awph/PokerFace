
package ch.hearc.miscellaneoustest.simulation.WinLose2Cards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

public class SimStat2CardsWinLoseV2
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private static final int					NB_SIMULATION		= 100000;
	private static final int					NB_CARDS_IN_BOARD	= 5;
	private static final int					NB_TOT_PLAYERS		= 2;
	private static final int					NB_CORE				= Runtime.getRuntime().availableProcessors();
	private static final ExecutorService		EXECUTOR_SERVICE	= Executors.newFixedThreadPool(NB_CORE);

	private static Record[]						hands;
	private static List<Map<String, Data>>		maps;
	private static Card[]						value;
	private static Card[]						value2;
	private static ConcurrentMap<String, Data>	charts;
	//private static Future<Void>[] futures;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException
	{
		hands = new Record[169];
		maps = new ArrayList<Map<String, Data>>();
		charts = Method.createConcurrentHashMap();
		value = new Card[13];
		value2 = new Card[13];

		value[0] = new Card(CardValue.Ace, CardColor.Clubs);
		value[1] = new Card(CardValue.Two, CardColor.Clubs);
		value[2] = new Card(CardValue.Three, CardColor.Clubs);
		value[3] = new Card(CardValue.Four, CardColor.Clubs);
		value[4] = new Card(CardValue.Five, CardColor.Clubs);
		value[5] = new Card(CardValue.Six, CardColor.Clubs);
		value[6] = new Card(CardValue.Seven, CardColor.Clubs);
		value[7] = new Card(CardValue.Eight, CardColor.Clubs);
		value[8] = new Card(CardValue.Nine, CardColor.Clubs);
		value[9] = new Card(CardValue.Ten, CardColor.Clubs);
		value[10] = new Card(CardValue.Jack, CardColor.Clubs);
		value[11] = new Card(CardValue.Queen, CardColor.Clubs);
		value[12] = new Card(CardValue.King, CardColor.Clubs);

		value2[0] = new Card(CardValue.Ace, CardColor.Spades);
		value2[1] = new Card(CardValue.Two, CardColor.Spades);
		value2[2] = new Card(CardValue.Three, CardColor.Spades);
		value2[3] = new Card(CardValue.Four, CardColor.Spades);
		value2[4] = new Card(CardValue.Five, CardColor.Spades);
		value2[5] = new Card(CardValue.Six, CardColor.Spades);
		value2[6] = new Card(CardValue.Seven, CardColor.Spades);
		value2[7] = new Card(CardValue.Eight, CardColor.Spades);
		value2[8] = new Card(CardValue.Nine, CardColor.Spades);
		value2[9] = new Card(CardValue.Ten, CardColor.Spades);
		value2[10] = new Card(CardValue.Jack, CardColor.Spades);
		value2[11] = new Card(CardValue.Queen, CardColor.Spades);
		value2[12] = new Card(CardValue.King, CardColor.Spades);

		int index = 0;
		for(int a = 0; a < value.length; ++a)
		{
			for(int b = a; b < value.length; ++b)
			{
				hands[index++] = new Record(value[a], value2[b], false);

				if (value[a].getValue().getIntValue() != value2[b].getValue().getIntValue())
				{
					hands[index++] = new Record(value[a], value[b], true);
				}
			}
		}

		//futures = new Future[NB_CORE];

		main();
	}

	public static void main() throws IOException, InterruptedException, ExecutionException
	{
		int range = hands.length / NB_CORE;
		List<Callable<Void>> callables = new ArrayList<Callable<Void>>();
		for(int i = 0; i < NB_CORE; ++i)
		{
			callables.add(callable(range*i, (i < (NB_CORE - 1) ? range * i + range : hands.length)));
		}

		long start = System.currentTimeMillis();
		//charts = Method.createConcurrentHashMap();
		EXECUTOR_SERVICE.invokeAll(callables);//Bloquant
		//System.out.println(1);
		//maps.add(charts);
		System.out.println(System.currentTimeMillis() - start);

		EXECUTOR_SERVICE.shutdown();
		Method.writeMap(charts, "Stats2Cards_WinLose.txt");
		//Method.writeAllMaps(maps, "Stats2Cards_WinLose.txt");
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static Callable<Void> callable(final int iStart, final int iEnd)
	{
		return new Callable<Void>()
		{

			@Override
			public Void call() throws Exception
			{
				for(int i = iStart; i < iEnd; ++i)
				{
					simulation(hands[i].getC1(), hands[i].getC2(), hands[i].getSame());
				}
				return null;
			}
		};
	}

	private static void simulation(Card c1, Card c2, boolean same)
	{
		Pocket[] handPlayers = new Pocket[NB_TOT_PLAYERS];
		Hand[] bestHandPlayers = new Hand[NB_TOT_PLAYERS];

		handPlayers[0] = new Pocket();
		handPlayers[0].add(c1);
		handPlayers[0].add(c2);

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
				bestHandPlayers[j] = new ComputeBestHandInASubset(CardSubset.union(handPlayers[j], board)).getHighestHand();
			}

			HandsPokerMap hpm = HandsPokerMap.getInstance();

			int index_best = 0;
			for(int j = 1; j < NB_TOT_PLAYERS; ++j)
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
