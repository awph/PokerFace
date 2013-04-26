
package ch.hearc.pokerface.gameengine.player;

import java.util.Observable;
import java.util.Observer;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gameengine.statistics.StatisticValue;
import ch.hearc.pokerface.gameengine.statistics.Statistics;
import ch.hearc.pokerface.gameengine.subsets.Pocket;
import ch.hearc.pokerface.tools.Pair;

public class Player implements Observer
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private int				bankroll;
	private int				turnSpending;
	private boolean			folded;
	private int				betSpending;
	private Pocket			pocket;
	private GameEngine		gameEngine;
	private Profile			profile;
	private Role			role;
	private StatisticValue	svPreFlop;
	private StatisticValue	svFlop;
	private StatisticValue	svTurn;
	private StatisticValue	svRiver;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Player(Profile profile, int bankroll)
	{
		this.profile = profile;
		this.bankroll = bankroll;
		this.turnSpending = 0;
		this.folded = false;
		this.betSpending = 0;
		this.pocket = new Pocket();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void newTurn()
	{
		this.turnSpending = 0;
		this.folded = false;
		this.betSpending = 0;
		this.pocket = new Pocket();
		svPreFlop = null;
		svFlop = null;
		svTurn = null;
		svRiver = null;
	}

	public void endBettingState()
	{
		this.betSpending = 0;
	}

	public void giveMoney(int money)
	{
		turnSpending += money;//TODO remove this line when simulation for divideUp is done
		bankroll += money;
	}

	public void takeMoney(int money)
	{
		turnSpending += money;
		betSpending += money;
		bankroll -= money;
	}

	public void allIn()
	{
		gameEngine.bet(bankroll);
	}

	public void check()
	{
		if (betSpending == gameEngine.getPot().getStateTotal())
		{
			gameEngine.bet(0);
		}
		else
		{
			call();
		}
	}

	public void bet(int amount)
	{
		if (amount < bankroll)
		{
			gameEngine.bet(amount);
		}
		else
		{
			allIn();
		}
	}

	public void call()
	{
		int amount = (gameEngine.getPot().getBet() - betSpending);
		if (amount < bankroll)
		{
			gameEngine.bet(amount);
		}
		else
		{
			allIn();
		}
	}

	public void raise(int amount)
	{
		if (amount < bankroll)
		{
			gameEngine.bet(amount);
		}
		else
		{
			allIn();
		}
	}

	/**
	 * The the folded attribut to true when the player folds
	 */
	public void fold()
	{
		folded = true;
		gameEngine.bet(-1);
	}

	/**
	 * Add new card to the player's pocker
	 *
	 * @param card
	 *            the new card
	 */
	public void addCard(Card card)
	{
		pocket.add(card);
		if (pocket.size() == 2)
		{
			svPreFlop = Statistics.getPreFlopValues(pocket, 2);//gameEngine.getNbPlayers());//TODO
		}
	}

	public void removeTurningSpend(int amount)
	{
		turnSpending = (turnSpending - amount > 0) ? turnSpending - amount : 0;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		Pair<StateType, StatisticValue> response = (Pair<StateType, StatisticValue>)arg;
		StateType stateType = response.getKey();

		if (stateType == StateType.FlopState)
		{
			svFlop = response.getValue();
		}
		else if (stateType == StateType.TurnState)
		{
			svTurn = response.getValue();
		}
		else
		{
			svRiver = response.getValue();
		}
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setRole(Role r)
	{
		role = r;
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public StatisticValue getPreFlopValues()
	{
		return svPreFlop;
	}

	public StatisticValue getFlopValues()
	{
		return svFlop;
	}

	public StatisticValue getTurnValues()
	{
		return svTurn;
	}

	public StatisticValue getRiverValues()
	{
		return svRiver;
	}

	public int getBankroll()
	{
		return this.bankroll;
	}

	public int getTurnSpending()
	{
		return this.turnSpending;
	}

	public int getBetSpending()
	{
		return this.betSpending;
	}

	public Pocket getPocket()
	{
		return this.pocket;
	}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isFolded()
	{
		return this.folded;
	}
}
