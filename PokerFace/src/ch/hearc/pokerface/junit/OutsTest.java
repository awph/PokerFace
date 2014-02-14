
package ch.hearc.pokerface.junit;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.cards.CardValue;
import ch.hearc.pokerface.gameengine.statistics.Statistics;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class OutsTest
{
	/*------------------------------------------------------------------*\
	 |*							Constructeurs							*|
	 \*------------------------------------------------------------------*/

	@Before
	public void before()
	{
		// rien
	}

	@After
	public void after()
	{
		// rien
	}

	/*------------------------------------------------------------------*\
	 |*							Methodes Public							*|
	 \*-----------------------------------------------------------------*/

	@Test
	public void test01()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Four, CardColor.Spades));
		pocket.add(new Card(CardValue.Four, CardColor.Hearts));
		Board board = new Board();
		board.add(new Card(CardValue.Six, CardColor.Clubs));
		board.add(new Card(CardValue.Seven, CardColor.Diamonds));
		board.add(new Card(CardValue.Ten, CardColor.Spades));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 2;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #2");
	}

	@Test
	public void test02()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Ace, CardColor.Spades));
		pocket.add(new Card(CardValue.Four, CardColor.Hearts));
		Board board = new Board();
		board.add(new Card(CardValue.Six, CardColor.Hearts));
		board.add(new Card(CardValue.Two, CardColor.Diamonds));
		board.add(new Card(CardValue.Jack, CardColor.Clubs));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 3;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #3");
	}

	@Test
	public void test03()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Six, CardColor.Clubs));
		pocket.add(new Card(CardValue.Seven, CardColor.Diamonds));
		Board board = new Board();
		board.add(new Card(CardValue.Five, CardColor.Spades));
		board.add(new Card(CardValue.Nine, CardColor.Hearts));
		board.add(new Card(CardValue.Ace, CardColor.Diamonds));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 4;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #4");
	}

	@Test
	public void test04()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Ace, CardColor.Diamonds));
		pocket.add(new Card(CardValue.Jack, CardColor.Hearts));
		Board board = new Board();
		board.add(new Card(CardValue.Five, CardColor.Spades));
		board.add(new Card(CardValue.Ace, CardColor.Spades));
		board.add(new Card(CardValue.Jack, CardColor.Diamonds));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 4;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #4");
	}

	@Test
	public void test05()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Jack, CardColor.Clubs));
		pocket.add(new Card(CardValue.Queen, CardColor.Diamonds));
		Board board = new Board();
		board.add(new Card(CardValue.Jack, CardColor.Diamonds));
		board.add(new Card(CardValue.Three, CardColor.Clubs));
		board.add(new Card(CardValue.Four, CardColor.Spades));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 5;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #5");
	}

	@Test
	public void test06()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Three, CardColor.Diamonds));
		pocket.add(new Card(CardValue.Six, CardColor.Clubs));
		Board board = new Board();
		board.add(new Card(CardValue.Eight, CardColor.Hearts));
		board.add(new Card(CardValue.Jack, CardColor.Diamonds));
		board.add(new Card(CardValue.Ace, CardColor.Clubs));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 6;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #6");
	}

	@Test
	public void test07()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Ace, CardColor.Clubs));
		pocket.add(new Card(CardValue.King, CardColor.Diamonds));
		Board board = new Board();
		board.add(new Card(CardValue.Three, CardColor.Diamonds));
		board.add(new Card(CardValue.Two, CardColor.Hearts));
		board.add(new Card(CardValue.Eight, CardColor.Hearts));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 6;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #6");
	}

	@Test
	public void test08()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Five, CardColor.Hearts));
		pocket.add(new Card(CardValue.Five, CardColor.Diamonds));
		Board board = new Board();
		board.add(new Card(CardValue.Five, CardColor.Clubs));
		board.add(new Card(CardValue.Queen, CardColor.Hearts));
		board.add(new Card(CardValue.Two, CardColor.Spades));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 7;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #7");
	}

	@Test
	public void test09()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Nine, CardColor.Hearts));
		pocket.add(new Card(CardValue.Ten, CardColor.Clubs));
		Board board = new Board();
		board.add(new Card(CardValue.Three, CardColor.Clubs));
		board.add(new Card(CardValue.Eight, CardColor.Diamonds));
		board.add(new Card(CardValue.Jack, CardColor.Hearts));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 8;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #8");
	}

	@Test
	public void test10()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Seven, CardColor.Spades));
		pocket.add(new Card(CardValue.Eight, CardColor.Spades));
		Board board = new Board();
		board.add(new Card(CardValue.Ace, CardColor.Hearts));
		board.add(new Card(CardValue.Nine, CardColor.Spades));
		board.add(new Card(CardValue.Six, CardColor.Diamonds));
		board.add(new Card(CardValue.Queen, CardColor.Clubs));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 8;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #8");
	}

	@Test
	public void test11()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Ace, CardColor.Hearts));
		pocket.add(new Card(CardValue.King, CardColor.Hearts));
		Board board = new Board();
		board.add(new Card(CardValue.Three, CardColor.Hearts));
		board.add(new Card(CardValue.Five, CardColor.Spades));
		board.add(new Card(CardValue.Seven, CardColor.Hearts));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 9;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #9");
	}

	@Test
	public void test12()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Ace, CardColor.Hearts));
		pocket.add(new Card(CardValue.King, CardColor.Clubs));
		Board board = new Board();
		board.add(new Card(CardValue.Queen, CardColor.Spades));
		board.add(new Card(CardValue.Jack, CardColor.Clubs));
		board.add(new Card(CardValue.Six, CardColor.Diamonds));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 10;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #10");
	}

	@Test
	public void test13()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.King, CardColor.Clubs));
		pocket.add(new Card(CardValue.Jack, CardColor.Clubs));
		Board board = new Board();
		board.add(new Card(CardValue.Ace, CardColor.Clubs));
		board.add(new Card(CardValue.Two, CardColor.Clubs));
		board.add(new Card(CardValue.Ten, CardColor.Hearts));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 12;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #12");
	}

	@Test
	public void test14()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Jack, CardColor.Hearts));
		pocket.add(new Card(CardValue.Ten, CardColor.Hearts));
		Board board = new Board();
		board.add(new Card(CardValue.Nine, CardColor.Clubs));
		board.add(new Card(CardValue.Queen, CardColor.Hearts));
		board.add(new Card(CardValue.Three, CardColor.Hearts));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 15;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #15");
	}

	@Test
	public void test15()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Jack, CardColor.Diamonds));
		pocket.add(new Card(CardValue.Ten, CardColor.Diamonds));
		Board board = new Board();
		board.add(new Card(CardValue.Eight, CardColor.Diamonds));
		board.add(new Card(CardValue.Queen, CardColor.Diamonds));
		board.add(new Card(CardValue.King, CardColor.Spades));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 15;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #15");
	}

	@Test
	public void test16()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Jack, CardColor.Spades));
		pocket.add(new Card(CardValue.Eight, CardColor.Clubs));
		Board board = new Board();
		board.add(new Card(CardValue.Nine, CardColor.Spades));
		board.add(new Card(CardValue.Ten, CardColor.Hearts));
		board.add(new Card(CardValue.Jack, CardColor.Clubs));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 6;//4;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #4");
	}

	@Test
	public void test17()
	{
		Pocket pocket = new Pocket();
		pocket.add(new Card(CardValue.Nine, CardColor.Clubs));
		pocket.add(new Card(CardValue.Seven, CardColor.Diamonds));
		Board board = new Board();
		board.add(new Card(CardValue.Five, CardColor.Spades));
		board.add(new Card(CardValue.Six, CardColor.Hearts));
		board.add(new Card(CardValue.Two, CardColor.Diamonds));

		int nbOuts = Statistics.getOuts(pocket, board);
		int nbOutsTheoretic = 4;

		assertTrue(nbOuts == nbOutsTheoretic);

		//System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + Statistics.getOuts(pocket, board).toString() + "\t" + Statistics.getOuts(pocket, board).size() + "\t #4");
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
