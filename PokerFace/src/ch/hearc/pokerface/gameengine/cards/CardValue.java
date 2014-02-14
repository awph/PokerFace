
package ch.hearc.pokerface.gameengine.cards;

public enum CardValue
{
	//List of the different possible value of a card
	Two("2", 1), Three("3", 2), Four("4", 3), Five("5", 4), Six("6", 5), Seven("7", 6), Eight("8", 7), Nine("9", 8), Ten("T", 9), Jack("J", 10), Queen("Q", 11), King("K", 12), Ace("A", 13);

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private String	valueString;
	private int		valueInt;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private CardValue(String valString, int valInt)
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
