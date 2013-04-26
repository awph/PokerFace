
package ch.hearc.pokerface.gameengine.gamecore.state;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;

public class PreFlopState extends State
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private final static int	NB_CARD_PREFLOP	= 2;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void addCads(GameEngine ge)
	{
		int nbPlayer = ge.getNbPlayers();
		for(int i = 0; i < nbPlayer; ++i)
		{
			for(int j = 0; j < NB_CARD_PREFLOP; ++j)
			{
				ge.getCurrentPlayer().addCard(ge.drawCard());
			}
			ge.changeCurrentPlayer();
		}
	}

	@Override
	public void bet(GameEngine ge)
	{
	}

	@Override
	public void nextSate(GameEngine ge)
	{
		ge.updateGUI();
		ge.setOldState(StateType.PreFlopState);
		ge.setState(new BettingState());
	}
}
