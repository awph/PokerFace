
package ch.hearc.miscellaneous.simulation.flopturnriver;

import java.io.IOException;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.cards.CardValue;
import ch.hearc.pokerface.gameengine.statistics.StatisticValue;
import ch.hearc.pokerface.gameengine.statistics.Statistics;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class UseFTRSim
{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args) throws IOException, InterruptedException
	{
		main();
	}

	public static void main() throws IOException, InterruptedException
	{
		Pocket p = new Pocket();
		p.add(new Card(CardValue.Three,CardColor.Clubs));
		p.add(new Card(CardValue.Seven,CardColor.Hearts));

		Board b = new Board();
		b.add(new Card(CardValue.Ace,CardColor.Spades));
		b.add(new Card(CardValue.Queen,CardColor.Hearts));
		b.add(new Card(CardValue.Four,CardColor.Diamonds));

		System.out.println(p + " " + b);
		long start = System.currentTimeMillis();
		StatisticValue sv = Statistics.getFlopOrTurnOrRiverValues(p, b, 9);
		System.out.println(sv);
		System.out.println((System.currentTimeMillis()-start)/1000.0 + "s");
	}
}
