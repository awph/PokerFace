
package ch.hearc.miscellaneoustest.handpoker.subset;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;


public class Pocket extends CardSubset
{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Pocket()
	{
		super();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void add(Card c)
	{
		if (size() < 2)
		{
			super.add(c);
		}
	}


}

