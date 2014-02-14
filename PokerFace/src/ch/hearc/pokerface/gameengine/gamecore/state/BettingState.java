
package ch.hearc.pokerface.gameengine.gamecore.state;

import java.util.List;

import ch.hearc.pokerface.gameengine.gamecore.Action;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.gamecore.SoundEngine;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.player.Role;

public class BettingState extends State
{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void addCards(GameEngine ge)
	{
		//Nothing
	}

	@Override
	public void bet(GameEngine ge)
	{
		if (ge.getUnfoldedPlayer() > 1)
		{
			if (ge.getUnfoldedPlayer() - ge.getAllInPlayer() > 1)
			{
				do
				{
					if (!ge.getPostSmallBlind() && !ge.getPostBigBlind())//In this case, if everybody checks, the big blind can play twice
					{
						firstBetProcessing(ge, ge.getCurrentPlayer());
					}
					else
					{
						//If someone has allin because he's so poor, the player doesn't have to play alone ! He has to "simulate" an allin
						normalBetProcessing(ge, ge.getCurrentPlayer());
					}
				} while(!allChecked(ge));
			}

			ge.setNewState();

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

	@SuppressWarnings("incomplete-switch")
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
		}
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * Check if everybody has checked or allin. In a nutshell if everybody didn't raise
	 *
	 * @param ge
	 *            : The GameEngine
	 * @return
	 */
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
					if ((p.getBetSpending() < ge.getBet() && p.getBankroll() != 0))
					{
						allChecked = false;
					}
				}
			}
			allChecked = (allChecked || (nbUnfoldedPlayer - nbAllInPlayer) <= 1);
		}

		return allChecked;
	}

	/**
	 * State for a normalBet, i.e. the bet after the flop/turn or river
	 *
	 * @param ge
	 *            : The GameEngine
	 * @param player
	 *            : The currentplayer
	 */
	private void normalBetProcessing(GameEngine ge, Player player)
	{
		//If he checks, he's considered as the last player who has "raised"
		if (ge.getBet() == 0)
		{
			ge.setIndexLastRaise(player);
		}
		do
		{
			if (player.getBankroll() != 0)
			{
				player.doAction();
			}
			ge.changeCurrentPlayer();
			player = ge.getCurrentPlayer();
		} while(player != ge.getLastRaisePlayer() && ge.getPlayers().size() > 1);
	}

	/**
	 * Only for the bettingstate after the preflop
	 *
	 * @param ge
	 *            : The GameEngine
	 * @param player
	 *            : The currentPlayer
	 */
	private void firstBetProcessing(GameEngine ge, Player player)
	{
		boolean hasBigBlindToPlayTwice = false;
		boolean bigBlindCantPlayMore = false;
		boolean isThePlayerTheLastRaisePlayer = false;

		while(!isThePlayerTheLastRaisePlayer)
		{
			if (!ge.getPostSmallBlind())
			{
				if (ge.getNbPlayers() == 2)
				{
					ge.changeCurrentPlayer();
					player = ge.getCurrentPlayer();
					player.betSmallBlind();
					ge.changeCurrentPlayer();
					player = ge.getCurrentPlayer();
					player.betBigBlind();
				}
				else
				{
					player.betSmallBlind();
				}
			}
			else if (!ge.getPostBigBlind())
			{
				player.betBigBlind();
			}
			else if (player.getBankroll() != 0)
			{
				if (player == GameEngine.HUMAN_PLAYER)
				{
					SoundEngine.getInstance().playSound(Action.YourTurn);
				}
				player.doAction();
			}
			Player oldPlayerBB = player;
			ge.changeCurrentPlayer();
			player = ge.getCurrentPlayer();

			isThePlayerTheLastRaisePlayer = player == ge.getLastRaisePlayer();

			//We check if everybody checks, the BB can play twice, but only in this case !
			if (hasBigBlindToPlayTwice && !bigBlindCantPlayMore)
			{
				isThePlayerTheLastRaisePlayer = oldPlayerBB.getBetSpending() <= ge.getBigBlind();
				hasBigBlindToPlayTwice = false;
				bigBlindCantPlayMore = true;
			}
			else if (isThePlayerTheLastRaisePlayer && Math.abs(ge.getAllInPlayer()-ge.getUnfoldedPlayer()) > 1 && !bigBlindCantPlayMore && player.getRole() == Role.BigBlind && ge.getBet() <= ge.getBigBlind())
			{
				hasBigBlindToPlayTwice = true;
				isThePlayerTheLastRaisePlayer = false;
			}

		}
	}
}
