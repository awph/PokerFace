
package ch.hearc.miscellaneoustest.handpoker;

import ch.hearc.miscellaneoustest.handpoker.subset.CardSubset;
import ch.hearc.miscellaneoustest.handpoker.subset.Deck;
import ch.hearc.miscellaneoustest.handpoker.subset.Hand;

public class Use
{
	private static final int	NB_SIMULATION		= 10;
	private static final int	NB_CARDS_IN_BOARD	= 7;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
	{
		main();
	}

	public static void main()
	{
		for(int i = 0; i < NB_SIMULATION; ++i)
		{
			Deck d = new Deck();
			CardSubset subset = new CardSubset();

			for(int j = 0;j < NB_CARDS_IN_BOARD; ++j)
			{
				subset.add(d.getNewCard());
			}

			System.out.print("CARDS : ");
			System.out.println(subset);
			System.out.print("BEST : ");
			HandsPokerMap hpm = HandsPokerMap.getInstance();
			d.getNewCard();
			Hand bestHand = new ComputeBestHandInASubset(subset).getHighestHand();
			HandsPokerValue hpv = hpm.getHand(bestHand);
			System.out.println(bestHand + " -> " + hpv.getRank() + " " + hpv.getHandName());

//			System.out.println();
			//hpm.getOuts("67T", "44");

//			hpm.getOuts("84J", "9T");
		}
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
