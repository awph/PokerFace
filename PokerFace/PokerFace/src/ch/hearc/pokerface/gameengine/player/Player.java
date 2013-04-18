
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

	private int			bankrool;
	private int			turnSpending;
	private boolean		folded;
	private int			betSpending;
	private Pocket		pocket;
	private GameEngine	gameEngine;
	private Profile		profile;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Player(Profile profile, int bankroll)
	{
		this.profile = profile;
		this.bankrool = bankroll;
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

	public void giveMoney(int money)
	{

	}

	public void takeMoney(int money)
	{

	}

	public void allIn()
	{

	}

	public void check()
	{
		gameEngine.bet();
	}

	public void bet(int amount)
	{

	}

	public void call()
	{
	}

	public void raise(int amount)
	{

	}

	/**
	 * The the folded attribut to true when the player folds
	 */
	public void fold()
	{
		folded = true;
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

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getBankrool()
	{
		return this.bankrool;
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

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
