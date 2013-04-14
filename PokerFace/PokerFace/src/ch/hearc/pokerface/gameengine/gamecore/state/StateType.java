
package ch.hearc.pokerface.gameengine.gamecore.state;


public enum StateType
{
	PreFlopState("PFS",1), FlopState("PS",2), TurnState("TS",3), RiverState("RS",4), ShowdownState("SS",5), BettingState("BS",6);

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private String	valueString;
	private int		valueInt;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private StateType(String valString, int valInt)
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

