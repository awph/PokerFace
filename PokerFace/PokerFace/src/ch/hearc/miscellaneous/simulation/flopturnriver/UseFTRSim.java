
package ch.hearc.miscellaneous.simulation.flopturnriver;

import java.io.IOException;
import java.util.Arrays;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.cards.CardValue;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.statistics.Statistics;

public class UseFTRSim
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private final static int	NB_PLAYER	= 10;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args) throws IOException, InterruptedException
	{
		main();
	}

	public static void main() throws IOException, InterruptedException
	{
		Player player = new Player(null, 0, null);
		player.addCard(new Card(CardValue.Three, CardColor.Clubs));
		player.addCard(new Card(CardValue.Seven, CardColor.Hearts));

		//Board
		Card[] board = new Card[5];
		board[0] = new Card(CardValue.Six, CardColor.Spades);
		board[1] = new Card(CardValue.Three, CardColor.Hearts);
		board[2] = new Card(CardValue.Seven, CardColor.Clubs);
		board[3] = new Card(CardValue.Ace, CardColor.Spades);
		board[4] = new Card(CardValue.Queen, CardColor.Spades);

		System.out.println(player.getPocket() + " " + Arrays.toString(board));

		long start = System.currentTimeMillis();
		Statistics.getFlopTurnRiverValues(player, player.getPocket(), board, NB_PLAYER);

		waitValues(player);

		System.out.println((System.currentTimeMillis() - start) / 1000.0 + "s");
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static void waitValues(final Player player)
	{
		Thread flop = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					while(player.getFlopValues() == null)
					{
						Thread.sleep(10);
					}
					System.out.println("\n-- Flop --");
					System.out.println(player.getFlopValues());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		Thread turn = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
				while(player.getTurnValues() == null)
				{
					Thread.sleep(100);
				}
				System.out.println("\n-- Turn --");
				System.out.println(player.getTurnValues());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		Thread river = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
				while(player.getRiverValues() == null)
				{
					Thread.sleep(100);
				}
				System.out.println("\n-- River --");
				System.out.println(player.getRiverValues());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		flop.start();
		turn.start();
		river.start();
		//Just to have an approximation of time. In the real case, we won't have the join
		try
		{
			flop.join();
			turn.join();
			river.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
