
package ch.hearc.pokerface.gameengine.cards;

public enum CardColor
{
	Diamonds("\u2666", 1), Hearts("\u2665", 2), Spades("\u2660", 3), Clubs("\u2663", 4);

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private String	valueString;
	private int		valueInt;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private CardColor(String valString, int valInt)
	{
		valueString = valString;
		valueInt = valInt;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
	{
		return valueString;
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getIntValue()
	{
		return valueInt;
	}

	public String getStringValue()
	{
		return valueString;
	}
}
