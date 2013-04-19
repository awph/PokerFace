
package ch.hearc.pokerface.gameengine.gamecore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.compute.ComputeBestHand;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.compute.HandsPokerValue;
import ch.hearc.pokerface.gameengine.gamecore.state.PreFlopState;
import ch.hearc.pokerface.gameengine.gamecore.state.State;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.player.AI;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.player.Role;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.CardSubset;
import ch.hearc.pokerface.gameengine.subsets.Deck;
import ch.hearc.pokerface.tools.Pair;

public class GameEngine
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private StateType		oldState;
	private State			state;
	private int				indexPlayer;
	private boolean			isFinished;
	private Pot				pot;
	private Board			board;
	private List<Player>	players;
	private Deck			deck;
	private SoundEngine		soundEngine;
	private HandsPokerMap	handsPokerMap;
	private int				smallBlind;
	private int				bigBlind;
	private Card[]			futureBoard;
	private int				magicIndex;
	private int				indexDealer;

	// initialize to nbPlayer*(-2) and incremented each draw of card. After the players have received theirs cards, we use it to define the correct card in the variable futureBoard to get
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public GameEngine(int smallBlind, int nbPlayer, Profile profile, int bankroll)
	{
		isFinished = false;
		pot = new Pot();
		soundEngine = new SoundEngine();
		futureBoard = new Card[Board.NUMBER_CARDS];
		handsPokerMap = HandsPokerMap.getInstance();

		this.smallBlind = smallBlind;
		bigBlind = 2 * smallBlind;

		players = new ArrayList<Player>();
		players.add(new Player(profile, bankroll));

		for(int i = 1; i < nbPlayer; ++i)
		{
			//TODO get a random profile
			players.add(new AI(profile, bankroll));
		}
		indexPlayer = (int)Math.random() * nbPlayer;
		indexDealer = indexPlayer - 1;

		initialize();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void run()
	{
		while(!isFinished)
		{
			state.addCads(this);
			state.bet(this);
			state.nextSate(this);
		}
	}

	public int changeCurrentPlayer()
	{
		do
		{
			indexPlayer = (indexPlayer < (players.size() - 1)) ? indexPlayer + 1 : 0;
		} while(getCurrentPlayer().isFolded());
		return indexPlayer;
	}

	public void showdown()
	{
		List<Pair<HandsPokerValue, Player>> handsValues = new ArrayList<Pair<HandsPokerValue, Player>>();

		for(int i = 0; i < players.size(); ++i)
		{
			Player player = players.get(i);
			if (!player.isFolded())
			{
				ComputeBestHand computeBestHand = new ComputeBestHand(CardSubset.union(player.getPocket(), board));
				handsValues.add(new Pair<HandsPokerValue, Player>(handsPokerMap.getHand(computeBestHand.getHighestHand()), player));
			}
		}

		Collections.sort(handsValues);

		Map<Integer, Pair<Integer,List<Player>>> playerSortByRank = new TreeMap<Integer, Pair<Integer,List<Player>>>();

		for(Pair<HandsPokerValue, Player> pair:handsValues)
		{
			int rank = pair.getKey().getRank();
			if (playerSortByRank.get(rank) == null)
			{
				playerSortByRank.put(rank, new Pair<Integer,List<Player>>(0,new ArrayList<Player>()));
			}
			Pair<Integer,List<Player>> contentMap = playerSortByRank.get(rank);
			if(pair.getValue().getTurnSpending() > contentMap.getKey())
			{
				contentMap.setKey(pair.getValue().getTurnSpending());
			}

			contentMap.getValue().add(pair.getValue());
			playerSortByRank.put(rank, contentMap);
		}



	}

	public Card drawCard()
	{
		if (magicIndex++ < 0)
		{
			if (magicIndex == 0)
			{
				for(int i = 0; i < Board.NUMBER_CARDS; ++i)
				{
					futureBoard[i] = deck.getNewCard();
				}
			}
			return deck.getNewCard();
		}
		else
		{
			return futureBoard[magicIndex - 1];
		}
	}

	public void bet(int amount)
	{
		//TODO: SoundEngine play sound here
		pot.addStateTotal(amount);
		notify();
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getUnfoldedPlayer()
	{
		int out = 0;
		for(Player p:players)
		{
			out += (p.isFolded()) ? 0 : 1;
		}
		return out;
	}

	public Board getBoard()
	{
		return board;
	}

	public Player getCurrentPlayer()
	{
		return players.get(indexPlayer);
	}

	public int getNbPlayers()
	{
		return players.size();
	}

	public StateType getOldState()
	{
		return oldState;
	}

	public Pot getPot()
	{
		return pot;
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setState(State s)
	{
		state = s;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void initialize()
	{
		oldState = null;
		setState(new PreFlopState());
		board = new Board();
		deck = new Deck();
		magicIndex = -2 * players.size();
		players.get(indexDealer).setRole(Role.Nothing);
		indexDealer = (indexDealer < (players.size() - 1)) ? indexDealer + 1 : 0;
		players.get(indexDealer).setRole(Role.Dealer);
		int indexSmallBlind = ((indexDealer + 1) < (players.size() - 1)) ? indexDealer + 1 : 0;
		int indexBigBlind = ((indexSmallBlind + 1) < (players.size() - 1)) ? indexSmallBlind + 1 : 0;
		players.get(indexSmallBlind).setRole(Role.SmallBlind);
		players.get(indexSmallBlind).bet(smallBlind);
		if (players.size() > 2)
		{
			players.get(indexBigBlind).setRole(Role.BigBlind);
			players.get(indexBigBlind).bet(bigBlind);
		}
		indexPlayer = ((indexBigBlind + 1) < (players.size() - 1)) ? indexBigBlind + 1 : 0;
	}
}
