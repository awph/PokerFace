
package ch.hearc.pokerface.gameengine.gamecore.state;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;

public class FlopState extends State
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private final static int	NB_CARD_FLOP	= 3;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void addCads(GameEngine ge)
	{
		for(int i = 0; i < NB_CARD_FLOP; ++i)
		{
			ge.addToBoard(ge.drawCard());
		}
	}

	@Override
	public void bet(GameEngine ge)
	{

	}

	@Override
	public void nextSate(GameEngine ge)
	{
		ge.setOldState(StateType.FlopState);
		ge.setState(new BettingState());
	}
}
