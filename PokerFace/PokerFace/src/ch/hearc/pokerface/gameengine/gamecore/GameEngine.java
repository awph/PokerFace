
package ch.hearc.pokerface.gameengine.gamecore;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.gamecore.state.State;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.Deck;


public class GameEngine
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private StateType oldState;
	private State state;
	private int indexPlayer;
	private boolean isFinished;
	private Pot pot;
	private Board board;
	private Player[] players;
	private Deck deck;
	private SoundEngine soundEngine;

	private static int nbPlayer;
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public GameEngine()
	{

	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void start()
	{

	}

	public void setState(State s)
	{
		state = s;
	}

	public Pot getPot()
	{
		return pot;
	}

	public void nextState()
	{
		state.nextSate(this);
	}

	public void nextPlayer()
	{
		state.nextPlayer(this);
	}

	public void addCards()
	{
		state.addCads(this);
	}

	public void bet()
	{
		state.bet(this);
	}

	public void divideUpPot()
	{

	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Board getBoard()
	{
		return board;
	}

	public Player getCuPlayer()
	{
		return players[indexPlayer];
	}

	public Card drawCard()
	{
		return deck.getNewCard();
	}

	public int getNbPlayers()
	{
		return nbPlayer;
	}

	public StateType getOldState()
	{
		return oldState;
	}
}

