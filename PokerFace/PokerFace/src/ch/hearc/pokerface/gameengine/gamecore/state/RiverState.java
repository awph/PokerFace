
package ch.hearc.pokerface.gameengine.gamecore.state;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;

public class RiverState extends State
{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void addCads(GameEngine ge)
	{
		ge.addToBoard(ge.drawCard());
	}

	@Override
	public void bet(GameEngine ge)
	{
		//Nothing
	}

	@Override
	public void nextSate(GameEngine ge)
	{
		ge.setOldState(StateType.RiverState);
		ge.setState(new BettingState());
	}
}
