
package ch.hearc.pokerface.gameengine.gamecore;


public class Pot
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private int betTotal;
	private int turnTotal;
	private int bet;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Pot()
	{

	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void addBet(int amount)
	{
		bet += amount;
	}

	public void addBetTotal(int amount)
	{
		betTotal += amount;
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getBetTotal()
	{
		return betTotal;
	}

	public int getTurnTotal()
	{
		return turnTotal;
	}

	public int getBet()
	{
		return bet;
	}
}

