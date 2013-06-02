
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

	private int					nbTurnBet;
	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private final static int	MIN_COEF_RAISE	= 1;
	private final static int	MAX_COEF_RAISE	= 1;
	private final static long	TIME_TO_PLAY	= 1000; //ms

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
	 * Compute a percentage with the chance of win
	 *
	 * @param win
	 *            : Chance of win, [0,1]
	 */
	private double getRaisePercentageBankroll(double win)
	{
		double max = Math.E / 0.3;
		return Math.pow(Math.E, win) / (1.3 - win) / max;
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
		return (((Math.pow(win, 1 / 3) * Math.pow(Math.pow(Math.E, 1 - bet), 3)) / (Math.pow(0.7, 1 / 3) * Math.pow(Math.E, 3))) * Math.pow(3, Math.sqrt(alreadyBet) / bet) + alreadyBet) * 100;
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
		return Math.pow(Math.pow(Math.E, Math.sqrt(win) / 0.17) + (1 - bet) * Math.pow(Math.E, Math.sqrt(1 - bet) / 0.24), 1.04) - 40 + alreadyBet * 150;
	}

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
					double valueHandPreFlop = Statistics.getChanceHandValuePreFlop(pocket, gameEngine.getNbPlayers());
					double callValuePreFlop = getChanceCallValuePreFlop(valueHandPreFlop / 100.0, (double)gameEngine.getBet() / (double)bankroll, (double)getBetSpending() / (double)(bankroll + getBetSpending()));

					if (valueHandPreFlop <= 20.0)
					{
						action = Action.Fold;
					}
					else if (valueHandPreFlop < 70.0)
					{
						if (Math.random() * 100.0 > callValuePreFlop)
						{
							action = Action.Fold;
						}
					}
					if (action == null)
					{
						if (valueHandPreFlop >= 70.0)
						{
							callValuePreFlop /= Math.pow(nbTurnBet, 2);
						}
						else
						{
							callValuePreFlop /= (Math.pow(nbTurnBet, 2) + 1);
						}

						if (Math.random() * 100.0 > callValuePreFlop)
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

					break;
				case FlopState:
					action = Action.Fold;
					break;
				case TurnState:
					action = Action.Fold;
					break;
				case RiverState:
					double valueWinRiver = getRiverValues().getWin();
					double callValueRiver = getChanceCallValuePreFlop(valueWinRiver / 100.0, (double)gameEngine.getBet() / (double)bankroll, (double)getBetSpending() / (double)(bankroll + getBetSpending()));

					if (Math.random() * 100.0 < callValueRiver)
					{
						if (valueWinRiver >= 70.0)
						{
							callValueRiver /= Math.pow(nbTurnBet, 2);
						}
						else
						{
							callValueRiver /= (Math.pow(nbTurnBet, 2) + 1);
						}

						if (Math.random() * 100.0 < callValueRiver)
						{
							raiseAmount = (int)(getRaisePercentageBankroll(valueWinRiver) * getBankroll());
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
