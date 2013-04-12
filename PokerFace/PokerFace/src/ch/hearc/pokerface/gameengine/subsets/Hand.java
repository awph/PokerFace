
package ch.hearc.pokerface.gameengine.subsets;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.compute.HandsPokerValue;

public class Hand extends CardSubset
{
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
		if (size() < 5)
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

	public HandsPokerValue getHandsPokerValue()
	{
		HandsPokerMap hps = HandsPokerMap.getInstance();
		return hps.getHand(this);
	}
}
