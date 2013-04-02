
package ch.hearc.miscellaneoustest.handpoker;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;
import ch.hearc.miscellaneoustest.handpoker.cards.CardColor;
import ch.hearc.miscellaneoustest.handpoker.cards.CardValue;
import ch.hearc.miscellaneoustest.handpoker.subset.Board;
import ch.hearc.miscellaneoustest.handpoker.subset.Pocket;

public class Use
{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
	{
		main();
	}

	public static void main()
	{
		testOuts();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void testOuts()
	{
		HandsPokerMap hpm = HandsPokerMap.getInstance();
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Four, CardColor.Spades));
			pocket.add(new Card(CardValue.Four, CardColor.Hearts));
			Board board = new Board();
			board.add(new Card(CardValue.Six, CardColor.Clubs));
			board.add(new Card(CardValue.Seven, CardColor.Diamonds));
			board.add(new Card(CardValue.Ten, CardColor.Spades));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #2");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Ace, CardColor.Spades));
			pocket.add(new Card(CardValue.Four, CardColor.Hearts));
			Board board = new Board();
			board.add(new Card(CardValue.Six, CardColor.Hearts));
			board.add(new Card(CardValue.Two, CardColor.Diamonds));
			board.add(new Card(CardValue.Jack, CardColor.Clubs));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #3");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Six, CardColor.Clubs));
			pocket.add(new Card(CardValue.Seven, CardColor.Diamonds));
			Board board = new Board();
			board.add(new Card(CardValue.Five, CardColor.Spades));
			board.add(new Card(CardValue.Nine, CardColor.Hearts));
			board.add(new Card(CardValue.Ace, CardColor.Diamonds));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #4");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Ace, CardColor.Diamonds));
			pocket.add(new Card(CardValue.Jack, CardColor.Hearts));
			Board board = new Board();
			board.add(new Card(CardValue.Five, CardColor.Spades));
			board.add(new Card(CardValue.Ace, CardColor.Spades));
			board.add(new Card(CardValue.Jack, CardColor.Diamonds));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #4");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Jack, CardColor.Clubs));
			pocket.add(new Card(CardValue.Queen, CardColor.Diamonds));
			Board board = new Board();
			board.add(new Card(CardValue.Jack, CardColor.Diamonds));
			board.add(new Card(CardValue.Three, CardColor.Clubs));
			board.add(new Card(CardValue.Four, CardColor.Spades));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #5");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Three, CardColor.Diamonds));
			pocket.add(new Card(CardValue.Six, CardColor.Clubs));
			Board board = new Board();
			board.add(new Card(CardValue.Eight, CardColor.Hearts));
			board.add(new Card(CardValue.Jack, CardColor.Diamonds));
			board.add(new Card(CardValue.Ace, CardColor.Clubs));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #6");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Ace, CardColor.Clubs));
			pocket.add(new Card(CardValue.King, CardColor.Diamonds));
			Board board = new Board();
			board.add(new Card(CardValue.Three, CardColor.Diamonds));
			board.add(new Card(CardValue.Two, CardColor.Hearts));
			board.add(new Card(CardValue.Eight, CardColor.Hearts));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #6");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Five, CardColor.Hearts));
			pocket.add(new Card(CardValue.Five, CardColor.Diamonds));
			Board board = new Board();
			board.add(new Card(CardValue.Five, CardColor.Clubs));
			board.add(new Card(CardValue.Queen, CardColor.Hearts));
			board.add(new Card(CardValue.Two, CardColor.Spades));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #7");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Nine, CardColor.Hearts));
			pocket.add(new Card(CardValue.Ten, CardColor.Clubs));
			Board board = new Board();
			board.add(new Card(CardValue.Three, CardColor.Clubs));
			board.add(new Card(CardValue.Eight, CardColor.Diamonds));
			board.add(new Card(CardValue.Jack, CardColor.Hearts));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #8");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Ace, CardColor.Hearts));
			pocket.add(new Card(CardValue.King, CardColor.Hearts));
			Board board = new Board();
			board.add(new Card(CardValue.Three, CardColor.Hearts));
			board.add(new Card(CardValue.Five, CardColor.Spades));
			board.add(new Card(CardValue.Seven, CardColor.Hearts));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #9");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Ace, CardColor.Hearts));
			pocket.add(new Card(CardValue.King, CardColor.Clubs));
			Board board = new Board();
			board.add(new Card(CardValue.Queen, CardColor.Spades));
			board.add(new Card(CardValue.Jack, CardColor.Clubs));
			board.add(new Card(CardValue.Six, CardColor.Diamonds));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #10");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.King, CardColor.Clubs));
			pocket.add(new Card(CardValue.Jack, CardColor.Clubs));
			Board board = new Board();
			board.add(new Card(CardValue.Ace, CardColor.Clubs));
			board.add(new Card(CardValue.Two, CardColor.Clubs));
			board.add(new Card(CardValue.Ten, CardColor.Hearts));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #12");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Jack, CardColor.Hearts));
			pocket.add(new Card(CardValue.Ten, CardColor.Hearts));
			Board board = new Board();
			board.add(new Card(CardValue.Nine, CardColor.Clubs));
			board.add(new Card(CardValue.Queen, CardColor.Hearts));
			board.add(new Card(CardValue.Three, CardColor.Hearts));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #15");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Jack, CardColor.Diamonds));
			pocket.add(new Card(CardValue.Ten, CardColor.Diamonds));
			Board board = new Board();
			board.add(new Card(CardValue.Eight, CardColor.Diamonds));
			board.add(new Card(CardValue.Queen, CardColor.Diamonds));
			board.add(new Card(CardValue.King, CardColor.Spades));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #15");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Jack, CardColor.Spades));
			pocket.add(new Card(CardValue.Eight, CardColor.Clubs));
			Board board = new Board();
			board.add(new Card(CardValue.Nine, CardColor.Spades));
			board.add(new Card(CardValue.Ten, CardColor.Hearts));
			board.add(new Card(CardValue.Jack, CardColor.Clubs));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #4");
		}
		{
			Pocket pocket = new Pocket();
			pocket.add(new Card(CardValue.Nine, CardColor.Clubs));
			pocket.add(new Card(CardValue.Seven, CardColor.Diamonds));
			Board board = new Board();
			board.add(new Card(CardValue.Five, CardColor.Spades));
			board.add(new Card(CardValue.Six, CardColor.Hearts));
			board.add(new Card(CardValue.Two, CardColor.Diamonds));
			System.out.println("[" + pocket.toString() + "]\t[" + board.toString() + "]\t" + hpm.getOuts(board, pocket).toString() + "\t" + hpm.getOuts(board, pocket).size() + "\t #4");
		}
	}
}
