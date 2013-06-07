
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

	/**
	 * Add the card c the {@code Set} by limited the size at {@code NUMBER_OF_CARDS = 2}.
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
	 * Create and return the key of the pocket.
	 * The creation appends the two value and add "s" if the two cards are the same color, "o" otherwise.
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

		stringBuilder.append((sameColor) ? "s" : "o");

		return stringBuilder.toString();
	}
}

