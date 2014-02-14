
package ch.hearc.pokerface.gameengine.gamecore;

public enum Action
{
	Bet("bets"), Raise("raises"), Fold("folds"), Call("calls"), Check("checks"), Allin("all in with"), PostSmallBlind("posts small blind"), PostBigBlind("posts big blind"), Winner("Winner"), Loser("Loser"), WinMoney("wins"), YourTurn("yourturn"), Leave("sits out");

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private String	valueString;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private Action(String valString)
	{
		valueString = valString;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
	{
		return valueString;
	}
}
