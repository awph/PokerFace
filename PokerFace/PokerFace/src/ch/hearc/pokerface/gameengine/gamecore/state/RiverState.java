
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
		ge.getBoard().add(ge.drawCard());
	}

	@Override
	public void bet(GameEngine ge)
	{

	}

	@Override
	public void nextSate(GameEngine ge)
	{

	}
}
