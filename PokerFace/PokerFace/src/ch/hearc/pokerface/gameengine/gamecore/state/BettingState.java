
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
		//Nothing
	}

	@Override
	public void bet(GameEngine ge)
	{
		if (ge.getUnfoldedPlayer() > 1)
		{
			do
			{
				ge.updateGUI();
				if (!postSmallBlind && !postBigBlind)//In this case, if everybody checks, the big blind can play twice
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
			while(firstPlayer.isFolded())
			{
				ge.changeCurrentPlayer();
				firstPlayer = ge.getCurrentPlayer();
			}
			Player currentPlayer = firstPlayer;
			do
			{
				currentPlayer.endBettingState();
				currentPlayer = ge.getPlayers().get(ge.changeCurrentPlayer());
			} while(currentPlayer != firstPlayer);
		}
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
				//Each time, it's a new instance of BettingState, so we must reinitialize the static attributes
				postBigBlind = false;
				postSmallBlind = false;
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
				if (!p.isFolded())
				{
					//If the player isn't folded and his bet = betSpend or has all in
					if ((p.getBetSpending() < ge.getPot().getBet() && p.getBankroll() != 0))
					{
						allChecked = false;
					}
				}
			}
			allChecked = (allChecked || (nbUnfoldedPlayer - nbAllInPlayer) <= 1);
		}

		return allChecked;
	}

	private void normalBetProcessing(GameEngine ge, Player player)
	{
		//If he checks, he's considered as the last player who has "raised"
		if (ge.getPot().getBet() == 0)
		{
			ge.setIndexLastRaise(player);
		}
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
		} while(player != ge.getLastRaisePlayer());
	}

	private void firstBetProcessing(GameEngine ge, Player player)
	{
		boolean hasBigBlindToPlayTwice = false;
		boolean isThePlayerTheLastRaisePlayer = false;

		while(!isThePlayerTheLastRaisePlayer)
		{
			if (!postSmallBlind && ge.getNbPlayers() > 2)
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

			//If everybody checks, the big blind can play twice
			if (isThePlayerTheLastRaisePlayer && player.getRole() == Role.BigBlind && ge.getUnfoldedPlayer() > 1)
			{
				hasBigBlindToPlayTwice = true;
				isThePlayerTheLastRaisePlayer = false;
			}
			else if (hasBigBlindToPlayTwice)
			{
				isThePlayerTheLastRaisePlayer = true;
			}
		}
	}
}
