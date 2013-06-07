
package ch.hearc.pokerface.gameengine.player;


public enum Role
{
	//List of the different possible roll for a player in the game
	Dealer("Dealer",1), SmallBlind("Small Blind",2), BigBlind("Big Blind",3), Nothing("",4);

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private String	valueString;
	private int		valueInt;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private Role(String valString, int valInt)
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

