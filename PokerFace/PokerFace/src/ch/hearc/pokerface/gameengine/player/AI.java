
package ch.hearc.pokerface.gameengine.player;

import ch.hearc.pokerface.gameengine.gamecore.Action;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
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

	private final static int									MIN_COEF_RAISE	= 1;
	private final static int									MAX_COEF_RAISE	= 5;
	private final static long									TIME_TO_PLAY	= 1000; //ms

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
		gameEngine.updateGUI();
		stopCurrentSimulation(true);
		//level1();
		//level23();
		check();
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
					double callValue = getChanceCallValue(valueHand / 100.0, (double)gameEngine.getBet() / (double)bankroll);

					if (valueHand <= 20.0)
					{
						action = Action.Fold;
					}
					else if (valueHand < 70.0)
					{
						if (Math.random() * 100.0 > callValue)
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

						if (Math.random() * 100.0 > callValue)
						{
							action = Action.Call;
						}
						else
						{
							int coef = MIN_COEF_RAISE + (int)(Math.random() * MAX_COEF_RAISE);
							raiseAmount = coef * gameEngine.getRaiseValue();
							action = Action.Raise;
						}
					}

					break;
				case FlopState:
					action = Action.Fold;
					break;
				case TurnState:
					action = Action.Fold;
					break;
				case RiverState:
					action = Action.Fold;
					break;
			}

			long delta = System.currentTimeMillis() - start;
			if (delta < TIME_TO_PLAY)
			{
				Thread.sleep(TIME_TO_PLAY - delta);
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

	@SuppressWarnings({ "incomplete-switch", "unused" })
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
			else if (gameEngine.getOldState() == StateType.PreFlopState)
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
				Odds potOdds = new Odds(getCallValue(), gameEngine.getStateTotal() + gameEngine.getTurnTotal() + getCallValue());
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
			if (delta < TIME_TO_PLAY)
			{
				Thread.sleep(TIME_TO_PLAY - delta);
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
}
