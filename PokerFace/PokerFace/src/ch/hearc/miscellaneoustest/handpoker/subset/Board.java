
package ch.hearc.miscellaneoustest.handpoker.subset;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;

public class Board extends CardSubset
{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Board()
	{
		super();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void add(Card c)
	{
		if (size() < 5)
		{
			super.add(c);
		}
	}

}
