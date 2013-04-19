
package ch.hearc.miscellaneous.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.cards.CardValue;
import ch.hearc.pokerface.gameengine.compute.ComputeBestHand;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.compute.HandsPokerValue;
import ch.hearc.pokerface.gameengine.gamecore.Pot;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.CardSubset;
import ch.hearc.pokerface.tools.Pair;
import ch.hearc.pokerface.tools.Triple;

public class TestDivideUpPot
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private static List<Player>		players;
	private static HandsPokerMap	handsPokerMap	= HandsPokerMap.getInstance();
	private static Board			board;
	private static Pot				pot;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
	{
		//CAS 1
		players = new ArrayList<Player>();
		players.add(createPlayer(100, new Card(CardValue.Ace, CardColor.Clubs), new Card(CardValue.Ace, CardColor.Diamonds)));
		players.add(createPlayer(150, new Card(CardValue.King, CardColor.Clubs), new Card(CardValue.King, CardColor.Diamonds)));
		players.add(createPlayer(200, new Card(CardValue.King, CardColor.Spades), new Card(CardValue.King, CardColor.Hearts)));
		players.add(createPlayer(300, new Card(CardValue.Three, CardColor.Clubs), new Card(CardValue.Two, CardColor.Diamonds)));

		board = new Board();
		board.add(new Card(CardValue.Ace, CardColor.Spades));
		board.add(new Card(CardValue.Seven, CardColor.Spades));
		board.add(new Card(CardValue.Eight, CardColor.Spades));

		pot = new Pot();
		pot.addStateTotal(750);
		pot.nextState();
		showdown();

		System.out.println(board);
		for(Player p:players)
		{
			System.out.println(p.getPocket());
			System.out.println(p.getTurnSpending());
		}

		//CAS 2
		players = new ArrayList<Player>();
		players.add(createPlayer(50, new Card(CardValue.Ace, CardColor.Clubs), new Card(CardValue.Ace, CardColor.Diamonds)));
		players.add(createPlayer(200, new Card(CardValue.King, CardColor.Clubs), new Card(CardValue.King, CardColor.Diamonds)));
		players.add(createPlayer(150, new Card(CardValue.Queen, CardColor.Spades), new Card(CardValue.Queen, CardColor.Hearts)));
		players.add(createPlayer(200, new Card(CardValue.Three, CardColor.Clubs), new Card(CardValue.Two, CardColor.Diamonds)));

		board = new Board();
		board.add(new Card(CardValue.Ace, CardColor.Spades));
		board.add(new Card(CardValue.Seven, CardColor.Spades));
		board.add(new Card(CardValue.Eight, CardColor.Spades));

		pot = new Pot();
		pot.addStateTotal(600);
		pot.nextState();
		showdown();

		System.out.println(board);
		for(Player p:players)
		{
			System.out.println(p.getPocket());
			System.out.println(p.getTurnSpending());
		}

		//CAS 3
		players = new ArrayList<Player>();
		players.add(createPlayer(100, new Card(CardValue.King, CardColor.Spades), new Card(CardValue.King, CardColor.Hearts)));
		players.add(createPlayer(200, new Card(CardValue.King, CardColor.Clubs), new Card(CardValue.King, CardColor.Diamonds)));
		players.add(createPlayer(250, new Card(CardValue.Queen, CardColor.Spades), new Card(CardValue.Queen, CardColor.Hearts)));
		players.add(createPlayer(350, new Card(CardValue.Three, CardColor.Clubs), new Card(CardValue.Two, CardColor.Diamonds)));

		board = new Board();
		board.add(new Card(CardValue.Ace, CardColor.Spades));
		board.add(new Card(CardValue.Seven, CardColor.Spades));
		board.add(new Card(CardValue.Eight, CardColor.Spades));

		pot = new Pot();
		pot.addStateTotal(900);
		pot.nextState();
		showdown();

		System.out.println(board);
		for(Player p:players)
		{
			System.out.println(p.getPocket());
			System.out.println(p.getTurnSpending());
		}
	}

	public static Player createPlayer(int money, Card c1, Card c2)
	{
		Player p = new Player(null, 0);
		p.giveMoney(money);
		p.addCard(c1);
		p.addCard(c2);
		return p;
	}

	public static void showdown()
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

		//Map<Rank,Triple<groupPot,SumBets,List<Player>>>
		Map<Integer, Triple<Integer, Integer, List<Player>>> playerSortByRank = new TreeMap<Integer, Triple<Integer, Integer, List<Player>>>();

		for(Pair<HandsPokerValue, Player> pair:handsValues)
		{
			int rank = pair.getKey().getRank();
			if (playerSortByRank.get(rank) == null)
			{
				playerSortByRank.put(rank, new Triple<Integer, Integer, List<Player>>(0, 0, new ArrayList<Player>()));
			}
			Triple<Integer, Integer, List<Player>> triple = playerSortByRank.get(rank);
			/*int playerTurnSpends = pair.getValue().getTurnSpending();

			triple.setKey(triple.getKey() + playerTurnSpends);
			triple.setValue1(triple.getValue1() + playerTurnSpends);*/
			triple.getValue2().add(pair.getValue());
			playerSortByRank.put(rank, triple);
		}

		//We transform it to array
		Set<Entry<Integer, Triple<Integer, Integer, List<Player>>>> entrySet = playerSortByRank.entrySet();
		Triple<Integer, Integer, List<Player>>[] triples = new Triple[entrySet.size()];
		int i = 0;
		for(Entry<Integer, Triple<Integer, Integer, List<Player>>> entry:entrySet)
		{
			Triple<Integer, Integer, List<Player>> triple = entry.getValue();
			triples[i++] = new Triple<Integer, Integer, List<Player>>(triple.getKey(), triple.getValue1(), triple.getValue2());
		}

		divideUpPot(triples);
	}

	/**
	 * @param triples
	 *            Triple<groupPot,SumBets,List<Player>>
	 */
	private static void divideUpPot(Triple<Integer, Integer, List<Player>>[] triples)
	{
		for(int i = 0; i < triples.length; ++i)
		{
			int min = triples[i].getValue2().get(0).getTurnSpending();
			for(Player p:triples[i].getValue2())
			{
				min = (p.getTurnSpending() < min) ? p.getTurnSpending() : min;
				triples[i].setKey(triples[i].getKey() + p.getTurnSpending());
				triples[i].setValue1(triples[i].getValue1() + p.getTurnSpending());
				int givenMoney = p.getTurnSpending();
				p.removeTurningSpend(givenMoney);
				p.giveMoney(givenMoney);
			}

			for(int j = i + 1; j < triples.length; ++j)
			{
				for(Player p:triples[j].getValue2())
				{
					if (p.getTurnSpending() >= min)
					{
						p.removeTurningSpend(min * triples[i].getValue2().size());
						triples[i].setKey(triples[i].getKey() + min * triples[i].getValue2().size());
					}
					else
					{
						triples[i].setKey(triples[i].getKey() + p.getTurnSpending());
						p.removeTurningSpend(min);
					}
				}
			}
		}

		for(int i = 0; i < triples.length; ++i)
		{
			for(Player p:triples[i].getValue2())
			{
				int moneyGiven = triples[i].getKey() * p.getTurnSpending();
				if (moneyGiven != 0 && triples[i].getValue1() != 0)
				{
					moneyGiven /= triples[i].getValue1();
					pot.removeAmount(moneyGiven);
					p.giveMoney(moneyGiven - p.getTurnSpending());
				}
			}
		}
		int rest = pot.getTurnTotal();
		pot.removeAmount(rest);

		System.out.println("Reste : " + rest);

		//If there is some money in the pot after the split, we distribute it to the winners at the left of the dealer
		//The amount must be under nbPlayer
		/*while(pot.getTurnTotal() != 0)
		{
			//TODO
		}*/
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
