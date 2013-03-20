
package ch.hearc.miscellaneoustest.handpoker.cards;

public enum CardValue
{
	Ace("A", 1), Two("2", 2), Three("3", 3), Four("4", 4), Five("5", 5), Six("6", 6), Seven("7", 7), Eight("8", 8), Nine("9", 9), Ten("T", 10), Jack("J", 11), Queen("Q", 12), King("K", 13);

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
