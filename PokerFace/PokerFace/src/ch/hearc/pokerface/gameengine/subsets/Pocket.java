
package ch.hearc.pokerface.gameengine.subsets;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;


public class Pocket extends CardSubset
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public final static int NUMBER_OF_CARDS = 2;

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
		if (size() < NUMBER_OF_CARDS)
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

		stringBuilder.append((sameColor) ? "s" : "o");

		return stringBuilder.toString();
	}
}

