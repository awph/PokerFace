
package ch.hearc.pokerface.gameengine.player;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class Player
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private int			bankroll;
	private int			turnSpending;
	private boolean		folded;
	private int			betSpending;
	private Pocket		pocket;
	private GameEngine	gameEngine;
	private Profile		profile;
	private Role		role;

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
	}

	public void endBettingState()
	{
		this.betSpending = 0;
	}

	public void giveMoney(int money)
	{
		turnSpending += money;//bankroll += money;
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
	}

	public void removeTurningSpend(int amount)
	{
		turnSpending = (turnSpending - amount > 0) ? turnSpending - amount : 0;
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
