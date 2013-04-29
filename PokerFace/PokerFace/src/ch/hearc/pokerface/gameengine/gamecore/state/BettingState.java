
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

	private static boolean	postSmallBlind	= false;
	private static boolean	postBigBlind	= false;

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
		do
		{
			ge.updateGUI();
			if (!postSmallBlind && !postBigBlind)
			{
				firstBetProcessing(ge, ge.getCurrentPlayer());
			}
			else
			{
				normalBetProcessing(ge, ge.getCurrentPlayer());
			}
			ge.updateGUI();
		} while(!allChecked(ge));

		ge.setNewState();
		ge.updateGUI();

		//Reinitialise the player's bet
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
				postBigBlind = false;
				postSmallBlind = false;
				ge.showdown();
				break;

			default:
				break;
		}
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private boolean allChecked(GameEngine ge)
	{
		boolean allChecked = true;
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

		return allChecked;
	}

	private void normalBetProcessing(GameEngine ge, Player player)
	{
		ge.setIndexLastRaise(player);//If he checks, he's considered as the last player who has "raised"
		do
		{
			if (player.getBankroll() != 0)//If not all in
			{
				//If player -> wait()
				//Else IA computes
				player.doAction();
			}
			ge.updateGUI();
			ge.changeCurrentPlayer();
			player = ge.getCurrentPlayer();
			ge.updateGUI();
		}while(player != ge.getLastRaisePlayer());
	}

	private void firstBetProcessing(GameEngine ge, Player player)
	{
		boolean hasBigBlindToPlayTwice = false;
		boolean isThePlayerTheLastRaisePlayer = false;
		while(!isThePlayerTheLastRaisePlayer)
		{
			if (!postSmallBlind)
			{
				ge.betSmallBlind();
				postSmallBlind = true;
			}
			else if (!postBigBlind)
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
			player = ge.getCurrentPlayer();
			ge.updateGUI();

			isThePlayerTheLastRaisePlayer = player == ge.getLastRaisePlayer();

			if (isThePlayerTheLastRaisePlayer && player.getRole() == Role.BigBlind)
			{
				hasBigBlindToPlayTwice = true;
				isThePlayerTheLastRaisePlayer = false;
			}
			else if(hasBigBlindToPlayTwice)
			{
				isThePlayerTheLastRaisePlayer = true;
			}
		}
	}
}
