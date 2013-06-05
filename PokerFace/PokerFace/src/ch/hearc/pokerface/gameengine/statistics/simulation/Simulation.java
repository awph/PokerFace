
package ch.hearc.pokerface.gameengine.statistics.simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.compute.ComputeBestHand;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.compute.HandsPokerValue;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.statistics.StatisticValue;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.CardSubset;
import ch.hearc.pokerface.gameengine.subsets.Deck;
import ch.hearc.pokerface.gameengine.subsets.Hand;
import ch.hearc.pokerface.gameengine.subsets.Pocket;
import ch.hearc.pokerface.tools.Pair;

public class Simulation extends Observable implements Runnable ,Observer
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Data[]							datas;

	private final Pocket					pocket;
	private final Board						board;
	private final int						nbPlayer;
	private final Deck						deck;
	private final int						nbCardBoard;
	private final StateType					stateType;
	private boolean							stillRunSimulation;
	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final int				NB_SIMULATION				= 20000;
	private static final int				NB_CORE						= Runtime.getRuntime().availableProcessors();
	private static final int				NB_CARDS_IN_BOARD			= 5;
	private static final int				NB_SIMULATION_PER_THREAD	= NB_SIMULATION / NB_CORE;
	private static final ExecutorService	EXECUTOR_SERVICE			= Executors.newFixedThreadPool(NB_CORE);

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Simulation(Pocket p, Card[] cards, int nbPlayer, int nbCardBoard)
	{
		this.pocket = p;
		this.stillRunSimulation = true;
		this.board = new Board();
		for(int i = 0; i < nbCardBoard; ++i)
		{
			board.add(cards[i]);
		}

		this.nbPlayer = nbPlayer;
		this.deck = new Deck();
		this.datas = new Data[NB_CORE];
		this.nbCardBoard = nbCardBoard;

		if (nbCardBoard == 3)
		{
			stateType = StateType.FlopState;
		}
		else if (nbCardBoard == 4)
		{
			stateType = StateType.TurnState;
		}
		else if (nbCardBoard == 5)
		{
			stateType = StateType.RiverState;
		}
		else
		{
			stateType = StateType.ShowdownState;
		}

		for(int i = 0; i < NB_CORE; ++i)
		{
			datas[i] = new Data();
		}

		for(Card c:pocket)
		{
			deck.removeByValue(c);
		}
		for(Card c:board)
		{
			deck.removeByValue(c);
		}
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
	{
		List<Future<Void>> futures = new LinkedList<Future<Void>>();

		for(int i = 0; i < NB_CORE; ++i)
		{
			synchronized (EXECUTOR_SERVICE)
			{
				futures.add(EXECUTOR_SERVICE.submit(simulatorCallable(i)));
			}
		}

		try
		{
			for(Future<Void> future:futures)
			{
				future.get();
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			e.printStackTrace();
		}

		Pair<StateType, StatisticValue> response = new Pair<StateType, StatisticValue>(stateType, joinDatas());

		setChanged();
		notifyObservers(response);
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if (stateType == (StateType)arg)
		{
			stillRunSimulation = false;
		}

	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * Combine all the datas in one
	 */
	private StatisticValue joinDatas()
	{
		Data d = datas[0];
		for(int i = 1; i < NB_CORE; ++i)
		{
			d.union(datas[2]);
		}

		Map<String, Integer> map = d.getMap();
		double nbTime = d.getNbTime();
		return new StatisticValue(d.getWinPercentage(), d.getTiePercentage(), d.getLossPercentage(), d.getAverageOpponantWinner(), map.get("SF") / nbTime * 100, map.get("4K") / nbTime * 100, map.get("FH") / nbTime * 100, map.get("F") / nbTime * 100, map.get("S") / nbTime * 100, map.get("3K")
				/ nbTime * 100, map.get("2P") / nbTime * 100, map.get("1P") / nbTime * 100, map.get("HC") / nbTime * 100);
	}

	private Callable<Void> simulatorCallable(final int index)
	{
		return new Callable<Void>()
		{
			@Override
			public Void call() throws Exception
			{
				Pocket[] playersPocket = new Pocket[nbPlayer];
				Hand[] bestHandPlayers = new Hand[nbPlayer];

				playersPocket[0] = pocket;

				HandsPokerMap hpm = HandsPokerMap.getInstance();

				int nbOpponantWinner = 0;
				int resultHandComparaison = 0;
				int stateOfHumanPlayer = 1;//1 -> win, 0 -> tie, -1 -> lose

				for(int i = 0; i < NB_SIMULATION_PER_THREAD && stillRunSimulation; ++i)
				{
					Deck d = new Deck(deck);
					Board b = new Board(board);

					for(int j = 1; j < nbPlayer; ++j)//Start at 1, because first hand was generated
					{
						playersPocket[j] = new Pocket();
						playersPocket[j].add(d.getNewCard());
						playersPocket[j].add(d.getNewCard());
					}

					for(int j = nbCardBoard; j < NB_CARDS_IN_BOARD; ++j)
					{
						b.add(d.getNewCard());
					}

					for(int j = 0; j < nbPlayer; ++j)
					{
						bestHandPlayers[j] = new ComputeBestHand(CardSubset.union(playersPocket[j], b)).getHighestHand();
					}

					HandsPokerValue bestHandHumanPlayer = hpm.getHand(bestHandPlayers[0]);
					nbOpponantWinner = 0;
					resultHandComparaison = 0;
					stateOfHumanPlayer = 1;//1 -> win, 0 -> tie, -1 -> lose
					for(int j = 1; j < nbPlayer; ++j)
					{
						resultHandComparaison = hpm.getHand(bestHandPlayers[j]).compareTo(bestHandHumanPlayer);
						if (resultHandComparaison > 0)//Lose
						{
							stateOfHumanPlayer = -1;
							nbOpponantWinner++;
						}
						else if (resultHandComparaison == 0 && stateOfHumanPlayer != -1)//Tie
						{
							stateOfHumanPlayer = 0;
							nbOpponantWinner++;
						}
					}

					if (stateOfHumanPlayer == 1)//The human player has the best hand
					{
						datas[index].addWin();
					}
					else if (stateOfHumanPlayer == 0)
					{
						datas[index].addTie();
					}
					else
					{
						datas[index].addLoss();
					}

					if (stateOfHumanPlayer != 1)
					{
						datas[index].addWinnerOpponant(nbOpponantWinner);
					}
					datas[index].addTime(bestHandHumanPlayer.getShortHandName());
				}
				return null;
			}
		};
	}
}
