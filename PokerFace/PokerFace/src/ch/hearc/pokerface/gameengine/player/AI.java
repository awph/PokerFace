
package ch.hearc.pokerface.gameengine.player;

import java.util.HashMap;
import java.util.Map;

import ch.hearc.pokerface.gameengine.gamecore.Action;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.gamecore.Pot;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gameengine.statistics.Odds;
import ch.hearc.pokerface.gameengine.statistics.StatisticValue;
import ch.hearc.pokerface.gameengine.statistics.Statistics;
import ch.hearc.pokerface.gameengine.subsets.Deck;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class AI extends Player
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private int													nbTurnBet;
	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private final static int									MIN_COEF_RAISE			= 1;
	private final static int									MAX_COEF_RAISE			= 5;
	private final static long									MAXIMUM_TIME_TO_PLAY	= 2000; //ms
	private final static long									MINIMUM_TIME_TO_PLAY	= 1000; //ms

	//Map<StateType, Map<nbPlayer,minimumRanking>
	private static final Map<StateType, Map<Integer, Integer>>	GOOD_HAND				= new HashMap<StateType, Map<Integer, Integer>>()
																						{
																							{
																								//TODO Define good hands for each case
																								put(StateType.PreFlopState, new HashMap<Integer, Integer>(2, 1));
																								put(StateType.PreFlopState, new HashMap<Integer, Integer>(3, 1));
																								put(StateType.PreFlopState, new HashMap<Integer, Integer>(4, 1));
																								put(StateType.PreFlopState, new HashMap<Integer, Integer>(5, 1));
																								put(StateType.PreFlopState, new HashMap<Integer, Integer>(6, 1));
																								put(StateType.PreFlopState, new HashMap<Integer, Integer>(7, 1));
																								put(StateType.PreFlopState, new HashMap<Integer, Integer>(8, 1));
																								put(StateType.PreFlopState, new HashMap<Integer, Integer>(9, 1));
																								put(StateType.PreFlopState, new HashMap<Integer, Integer>(10, 1));

																								put(StateType.FlopState, new HashMap<Integer, Integer>(2, 1));
																								put(StateType.FlopState, new HashMap<Integer, Integer>(3, 1));
																								put(StateType.FlopState, new HashMap<Integer, Integer>(4, 1));
																								put(StateType.FlopState, new HashMap<Integer, Integer>(5, 1));
																								put(StateType.FlopState, new HashMap<Integer, Integer>(6, 1));
																								put(StateType.FlopState, new HashMap<Integer, Integer>(7, 1));
																								put(StateType.FlopState, new HashMap<Integer, Integer>(8, 1));
																								put(StateType.FlopState, new HashMap<Integer, Integer>(9, 1));
																								put(StateType.FlopState, new HashMap<Integer, Integer>(10, 1));

																								put(StateType.TurnState, new HashMap<Integer, Integer>(2, 1));
																								put(StateType.TurnState, new HashMap<Integer, Integer>(3, 1));
																								put(StateType.TurnState, new HashMap<Integer, Integer>(4, 1));
																								put(StateType.TurnState, new HashMap<Integer, Integer>(5, 1));
																								put(StateType.TurnState, new HashMap<Integer, Integer>(6, 1));
																								put(StateType.TurnState, new HashMap<Integer, Integer>(7, 1));
																								put(StateType.TurnState, new HashMap<Integer, Integer>(8, 1));
																								put(StateType.TurnState, new HashMap<Integer, Integer>(9, 1));
																								put(StateType.TurnState, new HashMap<Integer, Integer>(10, 1));

																								put(StateType.RiverState, new HashMap<Integer, Integer>(2, 1));
																								put(StateType.RiverState, new HashMap<Integer, Integer>(3, 1));
																								put(StateType.RiverState, new HashMap<Integer, Integer>(4, 1));
																								put(StateType.RiverState, new HashMap<Integer, Integer>(5, 1));
																								put(StateType.RiverState, new HashMap<Integer, Integer>(6, 1));
																								put(StateType.RiverState, new HashMap<Integer, Integer>(7, 1));
																								put(StateType.RiverState, new HashMap<Integer, Integer>(8, 1));
																								put(StateType.RiverState, new HashMap<Integer, Integer>(9, 1));
																								put(StateType.RiverState, new HashMap<Integer, Integer>(10, 1));
																							}
																						};

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AI(Profile profile, int bankroll, GameEngine gameEngine)
	{
		super(profile, bankroll, gameEngine);
		nbTurnBet = 1;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void doAction()
	{
		stopCurrentSimulation(true);
		//level1();
		level23();
		nbTurnBet++;
	}

	@Override
	public void endBettingState()
	{
		super.endBettingState();
		nbTurnBet = 1;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * Compute a percentage with the chance of win
	 *
	 * @param x
	 *            : Chance of win, [0,1]
	 */
	/*private double getPercentageBankroll(double x)
	{
		double max = Math.E / 0.3;
		return Math.pow(Math.E, x) / (1.3 - x);
	}*/

	private double getChanceCallValue(double x, double y)
	{
		return Math.pow(x, 1.0 / 3.0) * Math.pow(Math.pow(Math.E, 1 - y), 3.0) / (Math.pow(0.7, 1.0 / 3.0) * Math.pow(Math.E, 3.0)) * 100;
	}

	/*private double getChancePlayValue(double x)
	{
		return (1.0 - Math.pow(Math.E, x))/(1.0 - Math.E)*100.0;
	}*/

	@SuppressWarnings("incomplete-switch")
	private void level23()
	{
		try
		{
			long start = System.currentTimeMillis();
			StatisticValue sv = null;

			switch(gameEngine.getUnorderedBoard().length)
			{
				case 0:
					sv = svPreFlop;
					break;
				case 3:
					sv = svFlop;
					break;
				case 4:
					sv = svTurn;
					break;
				case 5:
					sv = svRiver;
					break;
			}

			Action action = null;
			int raiseAmount = 0;

			switch(gameEngine.getOldState())
			{
				case PreFlopState:
					double valueHand = Statistics.getChanceHandValuePreFlop(pocket, gameEngine.getNbPlayers());
					double callValue = getChanceCallValue(valueHand / 100.0, (double)gameEngine.getPot().getBet() / (double)bankroll);
					//System.out.println(pocket + " VH : " + valueHand);
					//System.out.println("CV : " + callValue);
					if (valueHand <= 20.0)
					{
						action = Action.Fold;
					}
					else if (valueHand < 70.0)
					{
						double r = Math.random() * 100.0;
						System.out.println("\t" + r);
						if (r > callValue)
						{
							action = Action.Fold;
						}
					}
					if (action == null)
					{
						if (valueHand >= 70.0)
						{
							callValue /= Math.pow(nbTurnBet, 2);
						}
						else
						{
							callValue /= (Math.pow(nbTurnBet, 2) + 1);
						}
						//System.out.println("CV : " + callValue);
						if (Math.random() * 100.0 > callValue)
						{
							action = Action.Call;
						}
						else
						{
							int coef = MIN_COEF_RAISE + (int)(Math.random() * MAX_COEF_RAISE);
							raiseAmount = coef * gameEngine.getBigBlind() + getCallValue();
							action = Action.Raise;
						}
					}

					break;
				case FlopState:
					break;
				case TurnState:
					break;
				case RiverState:
					break;
			}

			long delta = System.currentTimeMillis() - start;
			if (delta < MINIMUM_TIME_TO_PLAY)
			{
				Thread.sleep(MINIMUM_TIME_TO_PLAY - delta);
			}

			switch(action)
			{
				case Call:
					call();
					break;
				case Raise:
					raise(raiseAmount);
					break;
				case Fold:
					if (getCallValue() == 0)
					{
						check();
					}
					else
					{
						fold();
					}
					break;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("incomplete-switch")
	private void level1()
	{
		try
		{
			long start = System.currentTimeMillis();
			StatisticValue sv = null;

			switch(gameEngine.getUnorderedBoard().length)
			{
				case 0:
					sv = svPreFlop;
					break;
				case 3:
					sv = svFlop;
					break;
				case 4:
					sv = svTurn;
					break;
				case 5:
					sv = svRiver;
					break;
			}
			Action action;
			if (getCallValue() == 0)
			{
				action = Action.Check;
			}
			else if (gameEngine.getUnorderedBoard().length == 0)//PreFlop
			{
				double win = sv.getWin();
				int rand = (int)(Math.random() * 100);
				if (rand < win)
				{
					action = Action.Call;
				}
				else
				{
					action = Action.Fold;
				}
			}
			else
			{
				Pot pot = gameEngine.getPot();
				Odds potOdds = new Odds(getCallValue(), pot.getStateTotal() + pot.getTurnTotal() + getCallValue());
				int nbCardsInDeck = Deck.NB_CARD_DECK - gameEngine.getNbPlayers() * Pocket.NUMBER_OF_CARDS - gameEngine.getUnorderedBoard().length;
				int nbOuts = Statistics.getOuts(pocket, gameEngine.getBoard());
				Odds pokerOdds = new Odds(nbOuts, nbCardsInDeck - nbOuts);

				if (pokerOdds.compareTo(potOdds) >= 0)
				{
					action = Action.Call;
				}
				else
				{
					action = Action.Fold;
				}
			}

			long delta = System.currentTimeMillis() - start;
			if (delta < MINIMUM_TIME_TO_PLAY)
			{
				Thread.sleep(MINIMUM_TIME_TO_PLAY - delta);
			}
			switch(action)
			{
				case Call:
					call();
					break;
				case Check:
					check();
					break;
				case Fold:
					fold();
					break;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
