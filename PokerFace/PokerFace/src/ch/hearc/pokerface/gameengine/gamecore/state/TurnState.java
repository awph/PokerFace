
package ch.hearc.pokerface.gameengine.gamecore.state;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;

public class TurnState extends State
{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void addCads(GameEngine ge)
	{
		ge.getBoard().add(ge.drawCard());
	}

	@Override
	public void bet(GameEngine ge)
	{

	}

	@Override
	public void nextSate(GameEngine ge)
	{
		ge.setOldState(StateType.TurnState);
		ge.setState(new BettingState());
	}
}
