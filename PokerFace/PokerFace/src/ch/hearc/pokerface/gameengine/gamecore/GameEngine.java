
package ch.hearc.pokerface.gameengine.gamecore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

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
import ch.hearc.pokerface.gameengine.player.profile.tools.AIProfileGenerator;
import ch.hearc.pokerface.gameengine.statistics.Statistics;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.CardSubset;
import ch.hearc.pokerface.gameengine.subsets.Deck;
import ch.hearc.pokerface.gui.JFrameMain;
import ch.hearc.pokerface.gui.gamescreen.table.board.JPanelGameBoard;
import ch.hearc.pokerface.gui.options.JPanelTopBar;
import ch.hearc.pokerface.tools.Pair;
import ch.hearc.pokerface.tools.Triple;

public class GameEngine
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private StateType			oldState;
	private State				state;

	private int					indexPlayer;
	private int					indexDealer;
	private int					indexSmallBlind;
	private int					indexBigBlind;
	private int					indexLastRaise;
	private int					magicIndex;
	private int					smallBlind;
	private int					bigBlind;
	private int					raiseValue;
	private int					nbRaise;
	private int					nbTurn;

	private boolean				isFinished;
	private boolean isCurrentGameFinished;

	private Pot					pot;
	private Board				board;
	private List<Player>		players;
	private Deck				deck;
	private SoundEngine			soundEngine;
	private HandsPokerMap		handsPokerMap;

	private Card[]				futureBoard;
	private JPanelGameBoard		panelGameBoard;
	private Profile				profilePlayer;
	private JEditorPane			logger;
	private JFrameMain			frameMain;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static Player		HUMAN_PLAYER;
	private static final int	NB_TURN_BEFORE_CHANGE_BLIND	= 8;
	private static final long	DELAY_BETWEEN_EACH_TURN		= 3000;
	private static final int	NB_SECOND_BEFORE_LEAVING	= 5;

	// initialize to nbPlayer*(-2) and incremented each draw of card. After the players have received theirs cards, we use it to define the correct card in the variable futureBoard to get
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * GameEngine
	 * @param frame : The frame with which the GameEngine will interact
	 * @param smallBlind : The value of the smallBlind
	 * @param nbPlayer : Number of player
	 * @param profile : The profile of the human player
	 * @param bankroll : The bankroll of the players
	 * @param panelGameBoard : The panel with which the GameEngine will interact
	 */
	public GameEngine(JFrameMain frame, int smallBlind, int nbPlayer, Profile profile, int bankroll, JPanelGameBoard panelGameBoard)
	{
		this.frameMain = frame;
		this.panelGameBoard = panelGameBoard;
		this.profilePlayer = profile;
		logger = null;

		nbTurn = 0;
		pot = new Pot();
		soundEngine = SoundEngine.getInstance();
		futureBoard = new Card[Board.NUMBER_CARDS];
		handsPokerMap = HandsPokerMap.getInstance();

		this.smallBlind = smallBlind;
		bigBlind = 2 * smallBlind;

		players = new ArrayList<Player>();
		players.add(new Player(profile, bankroll, this));
		profile.setCapital(profile.getCapital() - bankroll);

		HUMAN_PLAYER = players.get(0);

		for(int i = 1; i < nbPlayer; ++i)
		{
			players.add(new AI(AIProfileGenerator.fetchAIProfile(), bankroll, this));
		}
		indexPlayer = (int)(Math.random() * nbPlayer);
		indexDealer = getPreviousIndex(indexPlayer);
		raiseValue = 0;
		nbRaise = 0;

		initialize();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Finish the game and announce who has won
	 */
	public void finishTheGame()
	{
		if (HUMAN_PLAYER == players.get(0))
		{
			soundEngine.playSound(Action.Winner);
			log("<b style=\"color:green;\">You win !</b> You are the master !");
			showDialog("YOU WIN !");
		}
		else
		{
			soundEngine.playSound(Action.Loser);
			log("<b style=\"color:red;\">You lose !</b> The winner is <b>" + players.get(0).getProfile().getName() + "</b>");
			showDialog("YOU LOSE !");
		}
		log("You will leave the game in " + NB_SECOND_BEFORE_LEAVING + " seconds");
		int sec = NB_SECOND_BEFORE_LEAVING;

		try
		{
			while(sec >= 0)
			{
				log(String.valueOf(sec--));
				Thread.sleep(1000);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		leave();
		frameMain.gameToMainMenu();
	}

	/**
	 * Set the new capital of the humanplayer
	 */
	public void leave()
	{
		profilePlayer.setCapital(profilePlayer.getCapital() + HUMAN_PLAYER.getBankroll());
	}

	public void run()
	{
		while(!isFinished)
		{
			state.addCads(this);
			state.bet(this);
			state.nextSate(this);
		}
	}

	/**
	 * Change the current player
	 * @return return the index of the new current player
	 */
	public int changeCurrentPlayer()
	{
		do
		{
			indexPlayer = getNextIndex(indexPlayer);
		} while(getCurrentPlayer().isFolded());
		return indexPlayer;
	}

	/**
	 * Draw a card from the deck
	 * @return card just drawed in the deck
	 */
	public Card drawCard()
	{
		if (magicIndex++ < 0)
		{
			Card card = deck.getNewCard();
			if (magicIndex == 0)
			{
				for(int i = 0; i < Board.NUMBER_CARDS; ++i)
				{
					futureBoard[i] = deck.getNewCard();
				}
				//We run the simulation for the state FLOP, TURN & RIVER, we're currently at the end of the state PREFLOP.
				//So, we have all the betting state to compute these values
				runSimulationPlayer();
			}
			return card;
		}
		else
		{
			return futureBoard[magicIndex - 1];
		}
	}

	/**
	 * Bet action
	 * @param player : The player
	 * @param amount : The amount
	 */
	public void bet(Player player, int amount)
	{
		if (amount >= player.getBankroll())
		{
			allin(player);
		}
		else
		{
			logPlayerAction(player, Action.Bet, amount);
			betRaiseAllinAction(player, amount);
		}
	}

	/**
	 * Call action
	 * @param player : The player
	 */
	public void call(Player player)
	{
		int amount = player.getCallValue();
		if (amount >= player.getBankroll())
		{
			allin(player);
		}
		else if (amount == 0)
		{
			check(player);
		}
		else
		{
			logPlayerAction(player, Action.Call, amount);
			player.takeMoney(amount);
			pot.addStateTotal(amount);
			updateGUI();
		}
	}

	/**
	 * Raise action
	 * @param player : The player
	 * @param amount : The amount
	 */
	public void raise(Player player, int amount)
	{
		if (amount >= player.getBankroll())
		{
			allin(player);
		}
		else
		{
			logPlayerAction(player, Action.Raise, amount);
			betRaiseAllinAction(player, amount);
		}
	}

	/**
	 * Allin action
	 * @param player : The player
	 */
	public void allin(Player player)
	{
		int amount = player.getBankroll();
		logPlayerAction(player, Action.Allin, amount);
		betRaiseAllinAction(player, amount);
	}

	/**
	 * Check action
	 * @param player : The player
	 */
	public void check(Player player)
	{
		if (player.getCallValue() != 0)
		{
			call(player);
		}
		else
		{
			logPlayerAction(player, Action.Check);
			updateGUI();
		}
	}

	/**
	 * Fold action
	 * @param player : The player
	 */
	public void fold(Player player)
	{
		if (player == players.get(indexLastRaise))
		{
			indexLastRaise = getNextIndex(indexLastRaise);
		}
		logPlayerAction(player, Action.Fold);
		updateGUI();
	}

	/**
	 * Bet small/big blind action
	 * @param player : The player
	 * @param isSmall : True if it's the small blind, false if it's the big one
	 */
	public void betBlind(Player player, boolean isSmall)
	{
		int blind;
		Action action;

		if (isSmall)
		{
			blind = smallBlind;
			action = Action.PostSmallBlind;
		}
		else
		{
			blind = bigBlind;
			action = Action.PostBigBlind;
		}

		if (player.getBankroll() <= blind)
		{
			allin(player);
		}
		else
		{
			logPlayerAction(player, action, blind);
			pot.addStateTotal(blind);
			pot.setBet(blind);
			player.takeMoney(blind);
			setIndexLastRaise(player);
			updateGUI();
		}
	}

	/**
	 * Determine which player has/have won
	 */
	public void showdown()
	{
		logBoard("Showdown", "");

		List<Pair<HandsPokerValue, Player>> handsValues = new ArrayList<Pair<HandsPokerValue, Player>>();

		//Count the hand value of each player's hand
		for(Player player:players)
		{
			if (!player.isFolded())
			{
				ComputeBestHand computeBestHand = new ComputeBestHand(CardSubset.union(player.getPocket(), board));
				handsValues.add(new Pair<HandsPokerValue, Player>(handsPokerMap.getHand(computeBestHand.getHighestHand()), player));
			}
		}

		Collections.sort(handsValues);

		int bestRank = handsValues.get(0).getKey().getRank();
		for(Pair<HandsPokerValue, Player> pair:handsValues)
		{
			Player p = pair.getValue();
			logPlayerFinalResult((pair.getKey().getRank() == bestRank ? "<b style=\"color:green;\">Winner</b>" : "<b style=\"color:red;\">Loser</b>"), p, pair.getKey().getHandName());
		}

		//Map<Rank,Triple<groupPot,SumBets,List<Player>>>
		Map<Integer, Triple<Integer, Integer, List<Player>>> playerSortByRank = new TreeMap<Integer, Triple<Integer, Integer, List<Player>>>();

		//Groupe all players by ranking and if there is an equality, there would be n players in the same group
		for(int i = 0; i < handsValues.size(); ++i)
		{
			Pair<HandsPokerValue, Player> pair = handsValues.get(i);

			int rank = pair.getKey().getRank();
			if (playerSortByRank.get(rank) == null)
			{
				playerSortByRank.put(rank, new Triple<Integer, Integer, List<Player>>(0, 0, new ArrayList<Player>()));
			}
			Triple<Integer, Integer, List<Player>> triple = playerSortByRank.get(rank);
			triple.getValue2().add(pair.getValue());
			playerSortByRank.put(rank, triple);
		}

		//We transform it to an array
		Set<Entry<Integer, Triple<Integer, Integer, List<Player>>>> entrySet = playerSortByRank.entrySet();
		@SuppressWarnings("unchecked")
		Triple<Integer, Integer, List<Player>>[] triples = new Triple[entrySet.size()];
		int i = 0;
		for(Entry<Integer, Triple<Integer, Integer, List<Player>>> entry:entrySet)
		{
			Triple<Integer, Integer, List<Player>> triple = entry.getValue();
			triples[i++] = new Triple<Integer, Integer, List<Player>>(triple.getKey(), triple.getValue1(), triple.getValue2());
		}

		//We put the flag hasWon to true for the player(s) who win(s)
		for(i = 0; i < triples[0].getValue2().size(); ++i)
		{
			triples[0].getValue2().get(i).win();
		}

		isCurrentGameFinished = true;
		updateGUI();
		divideUpPot(triples);
		isFinished = players.size() == 1;
		if (isFinished || indexPlayer >= players.size())
		{
			indexPlayer = 0;
		}

		updateGUI();
		try
		{
			Thread.sleep(DELAY_BETWEEN_EACH_TURN);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Update the graphic user interface
	 */
	public void updateGUI()
	{
		panelGameBoard.updateGUI();
		JPanelTopBar.getInstance().setCapital(Integer.toString(HUMAN_PLAYER.getBankroll() + profilePlayer.getCapital()));
	}

	/**
	 * Set the new State
	 */
	public void setNewState()
	{
		indexLastRaise = -1;
		indexPlayer = getNextIndex(indexDealer);
	}

	/**
	 * Add a card to the current board
	 * @param card : New card to add
	 */
	public void addToBoard(Card card)
	{
		board.add(card);
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public boolean getIsCurrentGameFinised()
	{
		return isCurrentGameFinished;
	}

	public int getNbTurn()
	{
		return nbTurn;
	}

	public boolean getIsFinished()
	{
		return isFinished;
	}

	public int getSmallBlind()
	{
		return smallBlind;
	}

	public int getBigBlind()
	{
		return bigBlind;
	}

	public Board getBoard()
	{
		return board;
	}

	public int getUnfoldedPlayer()
	{
		int out = 0;
		for(Player p:players)
		{
			out += (p.isFolded()) ? 0 : 1;
		}
		return out;
	}

	public int getAllInPlayer()
	{
		int out = 0;
		for(Player p:players)
		{
			out += (p.getBankroll() == 0) ? 1 : 0;
		}
		return out;
	}

	public Card[] getUnorderedBoard()
	{
		int nbCards = 0;

		if (oldState == StateType.FlopState)
		{
			nbCards = Statistics.NUMBER_CARDS_FLOP;
		}
		else if (oldState == StateType.TurnState)
		{
			nbCards = Statistics.NUMBER_CARDS_TURN;
		}
		else if (oldState == StateType.RiverState)
		{
			nbCards = Statistics.NUMBER_CARDS_RIVER;
		}

		Card[] cards = new Card[nbCards];
		for(int i = 0; i < nbCards; ++i)
		{
			cards[i] = futureBoard[i];
		}
		return cards;
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

	public int getStateTotal()
	{
		return pot.getStateTotal();
	}

	public int getTurnTotal()
	{
		return pot.getTurnTotal();
	}

	public int getBet()
	{
		return pot.getBet();
	}

	public int getRaiseValue()
	{
		return raiseValue + players.get(indexPlayer).getCallValue();
	}

	public int getNbRaise()
	{
		return nbRaise;
	}

	public List<Player> getPlayers()
	{
		return players;
	}

	public Player getLastRaisePlayer()
	{
		if (indexLastRaise < 0 || indexLastRaise >= players.size())
		{
			return null;
		}
		else
		{
			return players.get(indexLastRaise);
		}
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setLogger(JEditorPane logger)
	{
		this.logger = logger;
	}

	public void setIndexLastRaise(Player p)
	{
		indexLastRaise = players.indexOf(p);
	}

	public void setState(State s)
	{
		pot.nextState();
		state = s;
	}

	public void setOldState(StateType oldState)
	{
		this.oldState = oldState;
		String state = "";
		StringBuilder sb = new StringBuilder();
		nbRaise = 0;

		if (this.oldState != StateType.PreFlopState)
		{
			raiseValue = bigBlind;
		}

		if (this.oldState == StateType.FlopState)
		{
			state = "Flop";
		}
		else if (this.oldState == StateType.TurnState)
		{
			state = "Turn";
		}
		else if (this.oldState == StateType.RiverState)
		{
			state = "River";
		}
		else
		{
			state = "Preflop";
		}

		Card[] cards = getUnorderedBoard();
		for(Card c:cards)
		{
			sb.append(c.toString());
		}

		logBoard(state, sb.toString().replaceAll("\\u2666", "<b style=\"color:red;\">\u2666</b>").replaceAll("\\u2665", "<b style=\"color:red;\">\u2665</b>").replaceAll("\\u2663", "<b style=\"color:black;\">\u2663</b>").replaceAll("\\u2660", "<b style=\"color:black;\">\u2660</b>"));
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * Divide up the pot correctily. It takes into account the bet that each player has play and divide up the pot correctely, first to the winner and if the winner had a smaller pot than the other, so the second group will divide up the rest of the pot etc..
	 * @param triples
	 *            Triple<groupPot,SumBets,List<Player>>
	 */
	private void divideUpPot(Triple<Integer, Integer, List<Player>>[] triples)
	{
		for(int i = 0; i < triples.length; ++i)
		{
			//Find the min bet of the group
			int min = triples[i].getValue2().get(0).getTurnSpending();

			for(int j = 0; j < triples[i].getValue2().size(); ++j)
			{
				Player p = triples[i].getValue2().get(j);
				min = (p.getTurnSpending() < min) ? p.getTurnSpending() : min;
				triples[i].setKey(triples[i].getKey() + p.getTurnSpending());
				triples[i].setValue1(triples[i].getValue1() + p.getTurnSpending());
			}

			//Decrease the min found to all next players and add each bet to the groupPot
			for(int j = i + 1; j < triples.length; ++j)
			{
				for(int k = 0; k < triples[j].getValue2().size(); ++k)
				{
					Player p = triples[j].getValue2().get(k);
					if (p.getTurnSpending() >= min)
					{
						p.removeTurningSpend(min);
						triples[i].setKey(triples[i].getKey() + min);
					}
					else
					{
						triples[i].setKey(triples[i].getKey() + p.getTurnSpending());
						p.removeTurningSpend(min);
					}
				}
			}
		}

		int nbPlayer = 0;
		for(Triple<Integer, Integer, List<Player>> triple:triples)
		{
			nbPlayer += triple.getValue2().size();
		}

		int[] winValues = new int[nbPlayer];

		//We know now which money each player has to receive, so we process
		for(int i = 0; i < triples.length; ++i)
		{
			for(int j = 0; j < triples[i].getValue2().size(); ++j)
			{
				Player p = triples[i].getValue2().get(j);
				int moneyGiven = triples[i].getKey() * p.getTurnSpending();
				if (moneyGiven != 0 && triples[i].getValue1() != 0)
				{
					moneyGiven /= triples[i].getValue1();
					pot.removeAmount(moneyGiven);
					p.giveMoney(moneyGiven);
					winValues[i + j] += moneyGiven;
				}
			}
		}

		//There might be a rest if the pot is not divisible (if it's a prime number or and odd one). In this case, we take the rest and distribute it between all the first group of winners, $ per $
		int rest = pot.getTurnTotal();
		pot.removeAmount(rest);

		while(rest != 0)
		{
			for(int i = 0; i < triples[0].getValue2().size(); ++i)
			{
				Player p = triples[0].getValue2().get(i);
				if (rest > 0)
				{
					p.giveMoney(1);
					winValues[i] += 1;
					--rest;
				}
			}
		}

		int indexWinValues = 0;
		for(Triple<Integer, Integer, List<Player>> triple:triples)
		{
			List<Player> players = triple.getValue2();
			for(Player p:players)
			{
				logPlayerAction(p, Action.WinMoney, winValues[indexWinValues++]);
			}
		}

		//We remove all the play who sits out, because they are so poor ! (They lost all their money)
		List<Player> playersToKill = new ArrayList<Player>();
		for(int i = 0; i < players.size(); ++i)
		{
			Player p = players.get(i);
			if (p.getBankroll() <= 0)
			{
				logPlayerAction(p, Action.Leave);
				p.kill();
				playersToKill.add(p);
			}
		}

		for(Player p:playersToKill)
		{
			players.remove(p);
		}
	}

	/**
	 * @param val : The index with which we want the next one
	 * @return the index of the next player
	 */
	private int getNextIndex(int val)
	{
		return (val < (players.size() - 1)) ? val + 1 : 0;
	}

	/**
	 * @param val : The index with which we want the next one
	 * @return the index of the previous player
	 */
	private int getPreviousIndex(int val)
	{
		return (val == 0) ? getNbPlayers() - 1 : val - 1;
	}

	/**
	 * Initialize the gameEngine between each game (a game contains preflop, flop, turn, river, showdown and the different betting states
	 */
	private void initialize()
	{
		for(Player p:players)
		{
			p.newTurn();
		}

		if (!isFinished)
		{
			if (++nbTurn % NB_TURN_BEFORE_CHANGE_BLIND == 0)
			{
				smallBlind *= 2;
				bigBlind *= 2;
			}

			isCurrentGameFinished = false;
			isFinished = false;
			pot.initialize();
			oldState = null;

			setState(new PreFlopState());
			board = new Board();
			deck = new Deck();

			magicIndex = -2 * players.size();

			indexDealer = getNextIndex(indexDealer);
			players.get(indexDealer).setRole(Role.Dealer);

			//If there are 2 players, there is no small blind !
			if (players.size() > 2)
			{
				indexSmallBlind = getNextIndex(indexDealer);
				indexPlayer = indexSmallBlind;
				Player smallBlindPlayer = players.get(indexSmallBlind);
				smallBlindPlayer.setRole(Role.SmallBlind);

				indexBigBlind = getNextIndex(indexSmallBlind);
			}
			else
			{
				indexSmallBlind = -1;
				indexBigBlind = getNextIndex(indexDealer);
				indexPlayer = indexBigBlind;
			}
			indexLastRaise = -1;

			raiseValue = bigBlind;
			nbRaise = 0;
			Player bigBlindPlayer = players.get(indexBigBlind);
			bigBlindPlayer.setRole(Role.BigBlind);
		}
	}

	/**
	 * Run the simulation FLOP, TURN, RIVER for all the player who aren't fold
	 */
	private void runSimulationPlayer()
	{
		int nbCardInBoard = Statistics.NUMBER_CARDS_FLOP;
		do
		{
			for(Player player:players)
			{
				if (!player.isFolded())
				{
					Statistics.getFlopTurnRiverValues(player, player.getPocket(), futureBoard, getNbPlayers(), nbCardInBoard);
				}
			}
		} while(++nbCardInBoard <= Statistics.NUMBER_CARDS_RIVER);
	}

	/**
	 * Log simply a message
	 * @param message : Message to log
	 */
	private void log(final String message)
	{
		if (logger != null)
		{
			try
			{
				//Necessary error because we modifiy the GUI in another thread than AWT-Q-EVENT
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						try
						{
							((HTMLEditorKit)logger.getEditorKit()).insertHTML((HTMLDocument)logger.getDocument(), ((HTMLDocument)logger.getDocument()).getLength(), message + "<br />", 0, 0, null);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				});
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
		else
		{
			System.out.println(message);
		}
	}

	/**
	 * Log a specific player's action
	 * @param player : The player to log
	 * @param action : The player's action
	 * @param amount : The player's amount
	 */
	private void logPlayerAction(Player player, Action action, int amount)
	{
		String actionText = action.toString();
		if (action == Action.Raise || action == Action.Bet || action == Action.Allin || action == Action.WinMoney && amount > 0)
		{
			actionText = "<b style=\"color:green;\">" + actionText + "</b>";
		}
		else if (action == Action.Fold || action == Action.Leave)
		{
			actionText = "<b style=\"color:red;\">" + actionText + "</b>";
		}
		log("<b>" + player.getProfile().getName() + "</b>" + " " + actionText + " " + ((amount != -1) ? amount + "$" : ""));
		soundEngine.playSound(action);
	}

	/**
	 * Log a specific player's action WITHOUT any amounts, i.e. XXX sits out
	 * @param player : The player to log
	 * @param action : The player's action
	 */
	private void logPlayerAction(Player player, Action action)
	{
		logPlayerAction(player, action, -1);
	}

	/**
	 * Log the current board
	 * @param state : The current state
	 * @param cards : The card of the current state
	 */
	private void logBoard(String state, String cards)
	{
		log("<br /><b style=\"color:red;\">---- " + state + " ----</b>");
		if (!state.equals("Preflop") && !state.equals("Showdown"))
		{
			log(cards);
		}
		log("");

	}

	/**
	 * Log the result of the showdown for a player
	 * @param rank : The rank of the player
	 * @param player : The Player
	 * @param handName : The player's hand
	 */
	private void logPlayerFinalResult(String rank, Player player, String handName)
	{
		log(rank + " : " + player.getProfile().getName() + " with "
				+ player.getPocket().toString().replaceAll("\\u2666", "<b style=\"color:red;\">\u2666</b>").replaceAll("\\u2665", "<b style=\"color:red;\">\u2665</b>").replaceAll("\\u2663", "<b style=\"color:black;\">\u2663</b>").replaceAll("\\u2660", "<b style=\"color:black;\">\u2660</b>")
				+ " \u2192 <b>" + handName + "</b>");
	}

	/**
	 * Display the dialog that'll say to the player if he lost or won
	 * @param text : The text to display
	 */
	private void showDialog(String text)
	{
		JOptionPane.showMessageDialog(frameMain, text, text, JOptionPane.CLOSED_OPTION);
	}

	/**
	 * BetRaiseAllin process
	 * @param player : The current player
	 * @param amount : The player's amount
	 */
	private void betRaiseAllinAction(Player player, int amount)
	{
		if (amount + player.getBetSpending() > pot.getBet())
		{
			raiseValue = player.getBetSpending() + amount - pot.getBet();
			setIndexLastRaise(player);
			pot.setBet(player.getBetSpending() + amount);
			nbRaise++;
		}
		player.takeMoney(amount);
		pot.addStateTotal(amount);
		updateGUI();
	}
}
