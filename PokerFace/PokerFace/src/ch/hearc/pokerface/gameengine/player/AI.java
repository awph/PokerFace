
package ch.hearc.pokerface.gameengine.player;

import ch.hearc.pokerface.gameengine.gamecore.Action;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gameengine.statistics.Statistics;
import ch.hearc.pokerface.tools.Pair;

public class AI extends Player
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private int					nbTurnBet;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private final static int	MIN_COEF_RAISE									= 1;
	private final static int	MAX_COEF_RAISE									= 1;
	private final static long	TIME_TO_PLAY									= 1500; //ms
	private final static int	MINIMUN_TRY_BEFORE_ACTION_DEFAULT				= 10;
	private final static long	TIME_BETWEEN_EACH_TRY_TO_ACCESS_SIMULATION_DATA	= 100;	//ms

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
		level23();
		//check();
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
	 * Compute a percentage to bet with the change of win
	 *
	 * @param win
	 *            : Chance of win, [0,1]
	 */
	private double howMuchToBet(double win)
	{
		double a = Math.pow(Math.E, win);
		double b = (1.3 - win);
		double c = Math.E / 0.3;
		double res = a / b / c;
		return res;
	}

	/**
	 * Compute a percentage for the preflop whether to folow or not and used to determine whether there is a raise or not
	 *
	 * @param win
	 *            percentage to win with the current hand
	 * @param bet
	 *            amount we need to folow
	 * @param alreadyBet
	 *            amount we already spent in this turn
	 * @return percentage to folow
	 */
	private double getChanceCallValuePreFlop(double win, double bet, double alreadyBet)
	{
		win = (win > 0.0) ? (win < 1.0) ? win : 1.0 : 0.0;
		bet = (bet > 0.0) ? (bet < 1.0) ? bet : 1.0 : 0.0;
		alreadyBet = (alreadyBet > 0.0) ? (alreadyBet < 1.0) ? alreadyBet : 1.0 : 0.0;
		return ((Math.pow(win, 1.0 / 3.0) * Math.pow(Math.pow(Math.E, 1.0 - bet), 3.0)) / (Math.pow(0.7, 1.0 / 3.0) * Math.pow(Math.E, 3.0)) + alreadyBet) * 100.0;
	}

	/**
	 * Compute a percentage for the flop or turn whether to folow or not and used to determine whether there is a raise or not
	 *
	 * @param win
	 *            percentage to win with the current hand
	 * @param bet
	 *            amount we need to folow
	 * @param alreadyBet
	 *            amount we already spent in this turn
	 * @return percentage to folow
	 */
	private double getChanceCallValueFlopOrTurn(double win, double bet, double alreadyBet)
	{
		win = (win > 0.0) ? (win < 1.0) ? win : 1.0 : 0.0;
		bet = (bet > 0.0) ? (bet < 1.0) ? bet : 1.0 : 0.0;
		alreadyBet = (alreadyBet > 0.0) ? (alreadyBet < 1.0) ? alreadyBet : 1.0 : 0.0;
		return (Math.atan((1.3 * Math.pow(win, 1.0 / 5.0) * Math.pow((1.0 - bet), 3.0)) / (1.1 - Math.pow(win, 1.0 / 5.0) * Math.sqrt(1 - bet))) * 2.0 / Math.PI + Math.sqrt(Math.pow(win, 3.0) + Math.pow(bet, 1.0 / 3.0))) * 115.0 + alreadyBet * 100.0;
	}

	/**
	 * Compute a percentage for the river whether to folow or not and used to determine whether there is a raise or not
	 *
	 * @param win
	 *            percentage to win with the current hand
	 * @param bet
	 *            amount we need to folow
	 * @param alreadyBet
	 *            amount we already spent in this turn
	 * @return percentage to folow
	 */
	private double getChanceCallValueRiver(double win, double bet, double alreadyBet)
	{
		win = (win > 0.0) ? (win < 1.0) ? win : 1.0 : 0.0;
		bet = (bet > 0.0) ? (bet < 1.0) ? bet : 1.0 : 0.0;
		alreadyBet = (alreadyBet > 0.0) ? (alreadyBet < 1.0) ? alreadyBet : 1.0 : 0.0;
		return Math.pow(Math.pow(Math.E, Math.sqrt(win) / 0.17) + (1.0 - bet) * Math.pow(Math.E, Math.sqrt(1.0 - bet) / 0.24), 1.04) - 40.0 + alreadyBet * 150.0;
	}

	private void level23()
	{
		try
		{
			long start = System.currentTimeMillis();

			Action action = null;
			int raiseAmount = 0;
			Pair<Action, Integer> pair = null;
			double valueWin = 0;

			int count = 0;
			switch(gameEngine.getOldState())
			{
				case PreFlopState:
					valueWin = Statistics.getChanceHandValuePreFlop(pocket, gameEngine.getNbPlayers()) / 100.0;
					pair = actionPreFlop(valueWin, getChanceCallValuePreFlop(valueWin, (double)gameEngine.getBet() / (double)bankroll, (double)getBetSpending() / (double)(bankroll + getBetSpending())));
					break;

				case FlopState:
					while(getFlopValues() == null && count++ <= MINIMUN_TRY_BEFORE_ACTION_DEFAULT)
					{
						Thread.sleep(TIME_BETWEEN_EACH_TRY_TO_ACCESS_SIMULATION_DATA);
					}
					if (getFlopValues() != null)
					{
						valueWin = getFlopValues().getWin() / 100.0;
						pair = actionFlopTurnRiver(valueWin, getChanceCallValueFlopOrTurn(valueWin, (double)gameEngine.getBet() / (double)bankroll, (double)getBetSpending() / (double)(bankroll + getBetSpending())) / 100.0);
					}
					else
					{
						pair = new Pair<Action, Integer>(Action.Fold, 0);
					}
					break;
				case TurnState:
					while(getTurnValues() == null && count++ <= MINIMUN_TRY_BEFORE_ACTION_DEFAULT)
					{
						Thread.sleep(TIME_BETWEEN_EACH_TRY_TO_ACCESS_SIMULATION_DATA);
					}
					if (getTurnValues() != null)
					{
						valueWin = getTurnValues().getWin() / 100.0;
						pair = actionFlopTurnRiver(valueWin, getChanceCallValueFlopOrTurn(valueWin, (double)gameEngine.getBet() / (double)bankroll, (double)getBetSpending() / (double)(bankroll + getBetSpending())) / 100.0);
					}
					else
					{
						pair = new Pair<Action, Integer>(Action.Fold, 0);
					}
					break;

				case RiverState:
					while(getRiverValues() == null && count++ <= MINIMUN_TRY_BEFORE_ACTION_DEFAULT)
					{
						Thread.sleep(TIME_BETWEEN_EACH_TRY_TO_ACCESS_SIMULATION_DATA);
					}
					if (getRiverValues() != null)
					{
						valueWin = getRiverValues().getWin() / 100.0;
						pair = actionFlopTurnRiver(valueWin, getChanceCallValueRiver(valueWin, (double)gameEngine.getBet() / (double)bankroll, (double)getBetSpending() / (double)(bankroll + getBetSpending())) / 100.0);
					}
					else
					{
						pair = new Pair<Action, Integer>(Action.Fold, 0);
					}
					break;

				default:
					break;
			}

			action = pair.getKey();
			raiseAmount = pair.getValue();

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
				default:
					break;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private Pair<Action, Integer> actionPreFlop(double valueWin, double callValue)
	{
		Action action = null;
		int raiseAmount = 0;

		if (valueWin <= 0.2)
		{
			action = Action.Fold;
		}
		else if (valueWin < 0.7)
		{
			if (Math.random() > callValue)
			{
				action = Action.Fold;
			}
		}
		if (action == null)
		{
			if (valueWin >= 0.7)
			{
				callValue /= Math.pow(nbTurnBet, 2);
			}
			else
			{
				callValue /= (Math.pow(nbTurnBet, 2) + 1);
			}

			if (Math.random() > callValue)
			{
				action = Action.Call;
			}
			else
			{
				double coef = MIN_COEF_RAISE + Math.random() * MAX_COEF_RAISE;
				raiseAmount = (int)(coef * gameEngine.getRaiseValue());
				action = Action.Raise;
			}
		}

		return new Pair<Action, Integer>(action, raiseAmount);
	}

	private Pair<Action, Integer> actionFlopTurnRiver(double valueWin, double callValue)
	{
		Action action = Action.Fold;
		int raiseAmount = 0;

		if (Math.random() < callValue)
		{
			if (valueWin >= 0.7)
			{
				callValue /= Math.pow(nbTurnBet, 2);
			}
			else
			{
				callValue /= (Math.pow(nbTurnBet, 2) + 1);
			}

			if (Math.random() < callValue)
			{
				double a = howMuchToBet(valueWin);
				int b = getBankroll();
				raiseAmount = (int)(a * b);
				action = Action.Raise;
			}
			else
			{
				action = Action.Call;
			}
		}
		else
		{
			action = Action.Fold;
		}

		return new Pair<Action, Integer>(action, raiseAmount);
	}

	/*@SuppressWarnings({ "incomplete-switch", "unused" })
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
	}*/
}
