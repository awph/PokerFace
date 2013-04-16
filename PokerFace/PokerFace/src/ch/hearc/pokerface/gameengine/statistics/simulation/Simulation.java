
package ch.hearc.pokerface.gameengine.statistics.simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.compute.ComputeBestHand;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.compute.HandsPokerValue;
import ch.hearc.pokerface.gameengine.statistics.StatisticValue;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.CardSubset;
import ch.hearc.pokerface.gameengine.subsets.Deck;
import ch.hearc.pokerface.gameengine.subsets.Hand;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class Simulation
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

	public Simulation(Pocket p, Board b, int nbPlayer)
	{
		this.pocket = p;
		this.board = b;
		this.nbPlayer = nbPlayer;
		this.deck = new Deck();
		this.datas = new Data[NB_CORE];
		this.nbCardBoard = b.size();

		for(int i = 0; i < NB_CORE; ++i)
		{
			datas[i] = new Data();
		}

		for(Card c:p)
		{
			deck.removeByValue(c);
		}
		for(Card c:b)
		{
			deck.removeByValue(c);
		}
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public StatisticValue run()
	{
		List<Future<Void>> futures = new LinkedList<Future<Void>>();

		for(int i = 0; i < NB_CORE; ++i)
		{
			futures.add(EXECUTOR_SERVICE.submit(simulatorCallable(i)));
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

		EXECUTOR_SERVICE.shutdown();

		return joinDatas();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private StatisticValue joinDatas()
	{
		Data d = datas[0];
		for(int i = 1; i < NB_CORE; ++i)
		{
			d.union(datas[2]);
		}

		Map<String,Integer> map = d.getMap();
		double nbTime = d.getNbTime();

		return new StatisticValue(d.getWinPercentage(), map.get("SF")/nbTime*100, map.get("4K")/nbTime*100, map.get("FH")/nbTime*100, map.get("F")/nbTime*100, map.get("S")/nbTime*100, map.get("3K")/nbTime*100, map.get("2P")/nbTime*100, map.get("1P")/nbTime*100, map.get("HC")/nbTime*100);
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

				for(int i = 0; i < NB_SIMULATION_PER_THREAD; ++i)
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

					boolean isHumanPlayerBest = true;
					HandsPokerValue bestHandHumanPlayer = hpm.getHand(bestHandPlayers[0]);

					for(int j = 1; isHumanPlayerBest && j < nbPlayer; ++j)
					{
						if (hpm.getHand(bestHandPlayers[j]).compareTo(bestHandHumanPlayer) > 0)
						{
							isHumanPlayerBest = false;
						}
					}

					if (isHumanPlayerBest)//The human player has the best hand
					{
						datas[index].addWin();
					}
					datas[index].addTime(bestHandHumanPlayer.getShortHandName());
				}
				return null;
			}
		};
	}
}
