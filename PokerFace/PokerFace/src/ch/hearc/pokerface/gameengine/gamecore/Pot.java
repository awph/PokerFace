
package ch.hearc.pokerface.gameengine.gamecore;

public class Pot
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private int	stateTotal;
	private int	turnTotal;
	private int	bet;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Represent the pot during a poker game
	 */
	public Pot()
	{
		initialize();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Bet : ");
		builder.append(this.bet);
		builder.append("\nStateTotal : ");
		builder.append(this.stateTotal);
		builder.append("\nTurnTotal : ");
		builder.append(this.turnTotal);
		return builder.toString();
	}

	/**
	 * Initialize total of the state,turn and the bet
	 */
	public void initialize()
	{
		stateTotal = turnTotal = bet = 0;
	}

	/**
	 * Add the stateTotal to the turnTotal and reinitilized stateTotal and bet
	 */
	public void nextState()
	{
		turnTotal += stateTotal;
		stateTotal = bet = 0;
	}

	/**
	 * Add the amount to the total of the state, update the current bet if necessary
	 *
	 * @param amount
	 */
	public void addStateTotal(int amount)
	{
		stateTotal += amount;
	}

	/**
	 * Remove the amount of the turnTotal
	 *
	 * @param amount
	 */
	public void removeAmount(int amount)
	{
		turnTotal -= amount;
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getStateTotal()
	{
		return stateTotal;
	}

	public int getTurnTotal()
	{
		return turnTotal;
	}

	public int getBet()
	{
		return bet;
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setBet(int amount)
	{
		if (amount > bet)
		{
			bet = amount;
		}
	}

}
