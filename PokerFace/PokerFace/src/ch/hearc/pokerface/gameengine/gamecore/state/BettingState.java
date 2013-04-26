
package ch.hearc.pokerface.gameengine.gamecore.state;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;

public class BettingState extends State
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private final static int	NB_TURN_MAX	= 4;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void addCads(GameEngine ge)
	{

	}

	@Override
	public void bet(GameEngine ge)
	{
		try
		{
			boolean allChecked = false;
			for(int i = 0; i < NB_TURN_MAX && !allChecked; ++i)
			{
				int betSpend = ge.getPot().getBet();
				int nbUnfoldedPlayer = ge.getUnfoldedPlayer();
				int nbAllinPlayer = 0;
				for(int j = 0; j < nbUnfoldedPlayer; ++j)
				{
					if (ge.getCurrentPlayer().getBankroll() != 0)//If all in
					{
						//If player -> wait()
						//Else IA compute
						ge.getCurrentPlayer().doAction();
					}
					else
					{
						++nbAllinPlayer;
					}

					ge.changeCurrentPlayer();
				}
				allChecked = (ge.getPot().getBet() == betSpend || (nbUnfoldedPlayer - nbAllinPlayer) <= 1);
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		Player firstPlayer = ge.getCurrentPlayer();
		Player currentPlayer = firstPlayer;
		do
		{
			currentPlayer.endBettingState();
			currentPlayer = ge.getCurrentPlayer();
		} while(currentPlayer != firstPlayer);

	}

	@Override
	public void nextSate(GameEngine ge)
	{
		switch(ge.getOldState())
		{
			case PreFlopState:
				ge.setState(new FlopState());
				break;

			case FlopState:
				ge.setState(new TurnState());
				break;

			case TurnState:
				ge.setState(new RiverState());
				break;

			case RiverState:
				ge.showdown();
				ge.setState(null);
				break;

			default:
				break;
		}

	}
}
