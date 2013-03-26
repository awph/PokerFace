
package ch.hearc.miscellaneoustest.simulation;

import java.io.IOException;
import java.util.Map;

import ch.hearc.miscellaneoustest.handpoker.ComputeBestHandInASubset;
import ch.hearc.miscellaneoustest.handpoker.HandsPokerMap;
import ch.hearc.miscellaneoustest.handpoker.cards.Card;
import ch.hearc.miscellaneoustest.handpoker.subset.CardSubset;
import ch.hearc.miscellaneoustest.handpoker.subset.Deck;
import ch.hearc.miscellaneoustest.handpoker.subset.Hand;
import ch.hearc.miscellaneoustest.handpoker.subset.Pocket;

public class RunSimulation
{
	private static final int	NB_SIMULATION		= 1000000;
	private static final int	NB_CARDS_IN_BOARD	= 5;
	private static final int	NB_PLAYER			= 2;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args) throws IOException
	{
		main();
	}

	public static void main() throws IOException
	{
		long start = System.currentTimeMillis();
		Map<String, Data> charts = Method.createHashMap();

		Pocket[] handPlayers = new Pocket[NB_PLAYER];
		Hand[] bestHandPlayers = new Hand[NB_PLAYER];

		for(int i = 0; i < NB_SIMULATION; ++i)
		{
			Deck d = new Deck();
			CardSubset subset = new CardSubset();

			for(int j = 0; j < NB_PLAYER; ++j)
			{
				handPlayers[j] = new Pocket();
				handPlayers[j].add(d.getNewCard());
				handPlayers[j].add(d.getNewCard());
			}

			for(int j = 0; j < NB_CARDS_IN_BOARD; ++j)
			{
				subset.add(d.getNewCard());
			}

			for(int j = 0; j < NB_PLAYER; ++j)
			{
				bestHandPlayers[j] = new ComputeBestHandInASubset(CardSubset.union(handPlayers[j], subset)).getHighestHand();
			}

			HandsPokerMap hpm = HandsPokerMap.getInstance();

			int index_best = 0;
			for(int j = 1; j < NB_PLAYER; ++j)
			{
				if (hpm.getHand(bestHandPlayers[j]).compareTo(hpm.getHand(bestHandPlayers[index_best])) > 0)
				{
					index_best = j;
				}
			}

			int j = 0;
			for(Pocket p:handPlayers)
			{
				Card[] cards = p.getArray();

				StringBuilder key = new StringBuilder();
				key.append(cards[0].getValue().getStringValue());
				key.append(cards[1].getValue().getStringValue());

				if (cards[0].getColor() != cards[1].getColor())
				{
					key.append("o");
				}
				else
				{
					key.append("s");
				}
				charts.get(key.toString()).addTime();
				if (j++ == index_best)
				{
					charts.get(key.toString()).addWin();
				}
			}
		}
		//displayMap(charts);
		Method.writeMap(charts);
		System.out.println(System.currentTimeMillis() - start);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
