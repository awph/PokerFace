
package ch.hearc.pokerface.gameengine.compute;


public enum HandType
{
	HighCard("HC", 1), OnePair("1P", 2), TwoPair("2P", 3), ThreeOfKind("3K", 4), Straight("S", 5), Flush("F", 6), FullHouse("FH", 7), FourOfKind("4K", 8), StraightFlush("SF", 9);

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private String	valueString;
	private int		valueInt;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private HandType(String valString, int valInt)
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

