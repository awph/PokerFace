
package ch.hearc.pokerface.gameengine.subsets;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;

public class Hand extends CardSubset
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static final int	NUMBER_CARDS	= 5;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Hand()
	{
		super();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void add(Card c)
	{
		if (size() < NUMBER_CARDS)
		{
			super.add(c);
		}
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getKey()
	{
		StringBuilder stringBuilder = new StringBuilder("");

		CardColor color = this.iterator().next().getColor();
		boolean sameColor = true;
		for(Card c:this)
		{
			if (c.getColor() != color)
			{
				sameColor = false;
			}
			stringBuilder.append(c.getValue().getStringValue());
		}

		if (sameColor)
		{
			stringBuilder.append("s");
		}
		return stringBuilder.toString();
	}
}
