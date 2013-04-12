
package ch.hearc.pokerface.gameengine.subsets;

import ch.hearc.pokerface.gameengine.cards.Card;


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

