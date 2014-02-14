
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

	public static final int	NUMBER_OF_CARDS	= 5;

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

	/**
	 * Add the card c the {@code Set} by limited the size at {@code NUMBER_OF_CARDS = 5}.
	 */
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

	/**
	 * Create and return the key of the Hand.
	 * The creation of value appends five cards and adds "s" if all five cards are of the same color, "o" otherwise.
	 *
	 * @return the key of the pocket.
	 */
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
