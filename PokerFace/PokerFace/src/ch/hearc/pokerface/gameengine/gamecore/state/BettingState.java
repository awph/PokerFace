
package ch.hearc.pokerface.gameengine.gamecore.state;

import java.util.List;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.player.Role;

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
		int nbTurnMax = NB_TURN_MAX;
		boolean allChecked = false;
		//Chaque tour on commence par la smallBlind. Au premier tour, c'est à la big blind de terminer le tour, donc BB joue 2x
		ge.updateGUI();
		if (ge.getOldState() == StateType.PreFlopState)
		{
			betFirstTurn(ge);

			--nbTurnMax;
			allChecked = true;
			int nbUnfoldedPlayer = ge.getUnfoldedPlayer();
			int nbAllInPlayer = ge.getAllInPlayer();

			if (nbAllInPlayer != nbUnfoldedPlayer)
			{
				List<Player> players = ge.getPlayers();
				for(Player p:players)
				{
					//If the player isn't folded and his bet = betSpend or has all in
					if (p.isFolded() || (p.getBetSpending() < ge.getPot().getBet() && p.getBankroll() != 0))
					{
						allChecked = false;
					}
				}
				allChecked = (allChecked || (nbUnfoldedPlayer - nbAllInPlayer) <= 1);
			}
		}
		ge.updateGUI();
		if (!allChecked)
		{
			betNormalTurn(ge,nbTurnMax);
		}

		ge.updateGUI();
		ge.setNewState();
		ge.updateGUI();

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

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void betNormalTurn(GameEngine ge,int nbTurnMax)
	{
		boolean allChecked = false;
		int betSpend = 0;
		int nbAllinPlayer = 0;
		int nbUnfoldedPlayer = 0;

		for(int i = 0; i < nbTurnMax && !allChecked; ++i)
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

			allChecked = true;
			if (nbAllinPlayer != nbUnfoldedPlayer)
			{
				List<Player> players = ge.getPlayers();
				for(Player p:players)
				{
					//If the player isn't folded and his bet = betSpend or has all in
					if (p.isFolded() || (p.getBetSpending() < betSpend && p.getBankroll() != 0))
					{
						allChecked = false;
					}
				}
				allChecked = (allChecked || (nbUnfoldedPlayer - nbAllinPlayer) <= 1);
			}
		}
	}

	private void betFirstTurn(GameEngine ge)
	{
		boolean postSmallBlind = false;
		boolean postBigBlind = false;
		int nbUnfoldedPlayer = ge.getUnfoldedPlayer() + 2;//BB & SB play twice

		for(int j = 0; j < nbUnfoldedPlayer; ++j)
		{
			ge.updateGUI();
			Player player = ge.getCurrentPlayer();

			if (!postSmallBlind && player.getRole() == Role.SmallBlind)
			{
				ge.betSmallBlind();
				postSmallBlind = true;
			}
			else if (!postBigBlind && player.getRole() == Role.BigBlind)
			{
				ge.betBigBlind();
				postBigBlind = true;
			}
			else if (player.getBankroll() != 0)//If not all in
			{
				//If player -> wait()
				//Else IA computes
				player.doAction();
			}

			ge.updateGUI();
			ge.changeCurrentPlayer();
		}
	}

}
