
package ch.hearc.pokerface.gameengine.player;

import java.util.Observable;
import java.util.Observer;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.gamecore.Action;
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

	protected int				bankroll;
	protected int				turnSpending;
	protected boolean			folded;
	protected int				betSpending;
	protected Pocket			pocket;
	protected Profile			profile;
	protected Role				role;
	protected GameEngine		gameEngine;
	protected StatisticValue	svPreFlop;
	protected StatisticValue	svFlop;
	protected StatisticValue	svTurn;
	protected StatisticValue	svRiver;
	protected boolean			dead;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Constructor
	 *
	 * @param profile
	 *            : The player's profile
	 * @param bankroll
	 *            : The value of the bankrool
	 * @param gameEngine
	 *            : The GameEngine
	 */
	public Player(Profile profile, int bankroll, GameEngine gameEngine)
	{
		this.profile = profile;
		this.bankroll = bankroll;
		this.gameEngine = gameEngine;

		newTurn();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Wait an action triggered by the GUI with notifiy
	 *
	 * @throws InterruptedException
	 */
	public void doAction()
	{
		try
		{
			synchronized (this)
			{
				wait();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize all the values for a new turn
	 */
	public void newTurn()
	{
		this.pocket = new Pocket();
		role = Role.Nothing;

		this.turnSpending = 0;
		this.betSpending = 0;
		this.folded = false;
		this.dead = false;

		svPreFlop = null;
		svFlop = null;
		svTurn = null;
		svRiver = null;
	}

	/**
	 * Set the betSpend to zero
	 */
	public void endBettingState()
	{
		this.betSpending = 0;
	}

	/**
	 * Add money to the player's bankroll
	 *
	 * @param money
	 *            : Money to give
	 */
	public void giveMoney(int money)
	{
		bankroll += money;
	}

	/**
	 * Take money from the player's bankrool
	 *
	 * @param money
	 *            : Money to take
	 */
	public void takeMoney(int money)
	{
		turnSpending += money;
		betSpending += money;
		bankroll -= money;
	}

	/**
	 * The player calls first and then bet all his bankroll
	 */
	public void allIn()
	{
		gameEngine.logPlayerAction(this, Action.Allin, bankroll);
		gameEngine.bet(bankroll);
	}

	/**
	 * Player checks (bet 0)
	 */
	public void check()
	{
		if (betSpending == gameEngine.getPot().getBet())
		{
			gameEngine.logPlayerAction(this, Action.Check);
			gameEngine.bet(0);
		}
		else
		{
			call();
		}
	}

	/**
	 * The player bets the amount
	 * @param amount : Amount to bet
	 */
	public void bet(int amount)
	{
		if (amount < bankroll)
		{
			gameEngine.logPlayerAction(this, Action.Bet,amount);
			gameEngine.bet(amount);
		}
		else
		{
			allIn();
		}
		gameEngine.setIndexLastRaise(this);//Notifiy that we've just raised
	}

	/**
	 * The player pays the amount to continue the game
	 */
	public void call()
	{
		int callValue = getCallValue();
		if (callValue != 0)
		{
			gameEngine.logPlayerAction(this, Action.Call, callValue);
		}
		gameEngine.bet(callValue);
	}

	/**
	 * The player raise, first he calls and then he bets
	 * @param amount : Money for the raise (call + bet)
	 */
	public void raise(int amount)
	{
		gameEngine.logPlayerAction(this, Action.Raise, amount);
		gameEngine.bet(amount);
		gameEngine.setIndexLastRaise(this);//Notifiy that we've just raised
	}

	/**
	 * The folded attribut to true when the player folds
	 */
	public void fold()
	{
		gameEngine.logPlayerAction(this, Action.Fold);
		folded = true;
	}

	/**
	 * The player has a bankroll empty, so he's ejected of the board. Set the attribut dead to true
	 */
	public void kill()
	{
		role = Role.Nothing;
		dead = true;
	}

	/**
	 * Add new card to the player's pocker
	 *
	 * @param card
	 *            : the new card
	 */
	public void addCard(Card card)
	{
		pocket.add(card);
		if (pocket.size() == Pocket.NUMBER_OF_CARDS)
		{
			svPreFlop = Statistics.getPreFlopValues(pocket, gameEngine.getNbPlayers());
		}
	}

	/**
	 * Remove the amount to the player's turningSpend
	 *
	 * @param amount
	 *            : Money to take
	 */
	public void removeTurningSpend(int amount)
	{
		turnSpending = (turnSpending - amount > 0) ? turnSpending - amount : 0;
	}

	/**
	 * Observer use for the pattern Observer
	 */
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

	/**
	 * Set the role of the player
	 *
	 * @param r
	 *            : The role
	 */
	public void setRole(Role r)
	{
		role = r;
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getCallValue()
	{
		return gameEngine.getPot().getBet() - betSpending;
	}

	public Role getRole()
	{
		return role;
	}

	public Profile getProfile()
	{
		return profile;
	}

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

	public boolean isDead()
	{
		return this.dead;
	}
}
