
package ch.hearc.pokerface.generatePFvalues;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.cards.CardValue;
import ch.hearc.pokerface.gameengine.compute.ComputeBestHand;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.compute.HandsPokerValue;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.statistics.simulation.Data;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.CardSubset;
import ch.hearc.pokerface.gameengine.subsets.Deck;
import ch.hearc.pokerface.gameengine.subsets.Hand;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class RunPreFlopSim
{
	private static final int					NB_SIMULATION		= 20000;
	private static final int					NB_CARDS_IN_BOARD	= 5;
	private static final int					NB_TOT_PLAYERS		= 2;
	private static final int					NB_CORE				= Runtime.getRuntime().availableProcessors();
	private static final ExecutorService		EXECUTOR_SERVICE	= Executors.newFixedThreadPool(NB_CORE);
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
		// Creation of hands
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

		long start = System.currentTimeMillis();

		List<Future<Void>> futures = new LinkedList<Future<Void>>();

		for(int i = 0; i < records.length; ++i)
		{
			futures.add(EXECUTOR_SERVICE.submit(new PokerSimulatorCallable(records[i].getC1(), records[i].getC2(), records[i].getSame())));
		}

		for(Future<Void> future:futures)
		{
			try
			{
				future.get();
			}
			catch (ExecutionException e)
			{
				System.err.println(e.toString());
			}
		}

		EXECUTOR_SERVICE.shutdown();

		System.out.println("Time : " + (System.currentTimeMillis() - start)/1000.0 + "sec");

		XMLWriter xmlw = new XMLWriter(charts, NB_TOT_PLAYERS, StateType.PreFlopState, "file");
		xmlw.write();
		/*Pocket p = new Pocket();
		p.add(new Card(CardValue.Eight,CardColor.Clubs));
		p.add(new Card(CardValue.Nine,CardColor.Hearts));
		System.out.println(Statistics.getPreFlopValues(p, 2));*/
		//Method.displayMap(charts);
		//Method.writeMap(charts, "Stats2Cards_WinLose.txt");
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	public static class PokerSimulatorCallable implements Callable<Void>
	{

		private Card	c1, c2;
		private boolean	same;

		public PokerSimulatorCallable(Card c1, Card c2, boolean same)
		{
			this.c1 = c1;
			this.c2 = c2;
			this.same = same;
		}

		@Override
		public Void call() throws Exception
		{
			Pocket[] playersPocket = new Pocket[NB_TOT_PLAYERS];
			Hand[] bestHandPlayers = new Hand[NB_TOT_PLAYERS];

			playersPocket[0] = new Pocket();
			playersPocket[0].add(c1);
			playersPocket[0].add(c2);

			Card[] cards = playersPocket[0].getArray();
			StringBuilder key = new StringBuilder();
			key.append(cards[0].getValue().getStringValue());
			key.append(cards[1].getValue().getStringValue());
			key.append((same) ? "s" : "o");

			HandsPokerMap hpm = HandsPokerMap.getInstance();

			Data data = charts.get(key.toString());
			int nbOpponantWinner = 0;
			int resultHandComparaison = 0;
			int stateOfHumanPlayer = 1;//1 -> win, 0 -> tie, -1 -> lose
			for(int i = 0; i < NB_SIMULATION; ++i)
			{
				Deck d = new Deck();
				d.removeByValue(c1);
				d.removeByValue(c2);
				Board board = new Board();

				for(int j = 1; j < NB_TOT_PLAYERS; ++j)//Start at 1, because first hand was generated
				{
					playersPocket[j] = new Pocket();
					playersPocket[j].add(d.getNewCard());
					playersPocket[j].add(d.getNewCard());
				}

				for(int j = 0; j < NB_CARDS_IN_BOARD; ++j)
				{
					board.add(d.getNewCard());
				}

				for(int j = 0; j < NB_TOT_PLAYERS; ++j)
				{
					bestHandPlayers[j] = new ComputeBestHand(CardSubset.union(playersPocket[j], board)).getHighestHand();
				}

				HandsPokerValue bestHandHumanPlayer = hpm.getHand(bestHandPlayers[0]);
				nbOpponantWinner = 0;
				resultHandComparaison = 0;
				stateOfHumanPlayer = 1;//1 -> win, 0 -> tie, -1 -> lose
				for(int j = 1;j < NB_TOT_PLAYERS; ++j)
				{
					resultHandComparaison = hpm.getHand(bestHandPlayers[j]).compareTo(bestHandHumanPlayer);
					if (resultHandComparaison > 0)//Lose
					{
						stateOfHumanPlayer = -1;
						nbOpponantWinner++;
					}
					else if(resultHandComparaison == 0 && stateOfHumanPlayer != -1)//Tie
					{
						stateOfHumanPlayer = 0;
						nbOpponantWinner++;
					}
				}

				if (stateOfHumanPlayer == 1)//The human player has the best hand
				{
					data.addWin();
				}
				else if(stateOfHumanPlayer == 0)
				{
					data.addTie();
				}
				else
				{
					data.addLoss();
				}

				if (stateOfHumanPlayer != 1)
				{
					data.addWinnerOpponant(nbOpponantWinner);
				}
				data.addTime(bestHandHumanPlayer.getShortHandName());
			}
			return null;
		}
	}
}
