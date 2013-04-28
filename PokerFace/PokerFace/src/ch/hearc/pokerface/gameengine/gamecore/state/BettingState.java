
package ch.hearc.pokerface.gameengine.gamecore.state;

import java.util.List;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;

public class BettingState extends State
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private final static int	NB_TURN_MAX	= 3;

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
		if (ge.getOldState() == StateType.PreFlopState)
		{
			ge.betSmallBlind();
			ge.betBigBlind();
		}

		boolean allChecked = false;
		int betSpend = 0;
		int nbAllinPlayer = 0;
		int nbUnfoldedPlayer = 0;
		for(int i = 0; i < NB_TURN_MAX && !allChecked; ++i)
		{
			ge.updateGUI();
			nbUnfoldedPlayer = ge.getUnfoldedPlayer();
			betSpend = ge.getPot().getBet();
			for(int j = 0; j < nbUnfoldedPlayer; ++j)
			{
				Player player = ge.getCurrentPlayer();
				if (player.getBankroll() != 0)//If not all in
				{
					//If player -> wait()
					//Else IA computes
					player.doAction();
				}
				else
				{
					++nbAllinPlayer;
				}
				ge.updateGUI();
				ge.changeCurrentPlayer();
				ge.updateGUI();
				betSpend = ge.getPot().getBet();
			}

			List<Player> players = ge.getPlayers();
			allChecked = true;
			for(Player p:players)
			{
				//If the player isn't folded and his bet = betSpend or has all in
				if (p.isFolded() || (p.getBetSpending() < betSpend && p.getBankroll() != 0))
				{
					allChecked = false;
				}
			}
			allChecked = (allChecked || (nbUnfoldedPlayer - nbAllinPlayer) <= 1);
			ge.updateGUI();
		}

		Player firstPlayer = ge.getCurrentPlayer();
		Player currentPlayer = firstPlayer;
		do
		{
			currentPlayer.endBettingState();
			currentPlayer = ge.getPlayers().get(ge.changeCurrentPlayer());
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
				ge.setState(new PreFlopState());
				ge.showdown();
				break;

			default:
				break;
		}

	}
}
