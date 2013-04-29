
package ch.hearc.pokerface.gameengine.subsets;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;

public class Board extends CardSubset
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	public static final int	NUMBER_CARDS	= 5;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Board()
	{
		super();
	}

	public Board(Board boardSource)
	{
		this();
		for(Card card:boardSource)
		{
			add(card.cloneOf());
		}
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

	@Override
	public Board cloneOf()
	{
		return new Board(this);
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getKey(Deck deck)
	{
		if (size() < 4)
		{
			System.err.println("Board < 4");
			return null;
		}
		else if (size() == NUMBER_CARDS)
		{
			StringBuilder key = new StringBuilder("");

			CardColor color = this.iterator().next().getColor();
			boolean sameColor = true;
			for(Card c:this)
			{
				if (c.getColor() != color)
				{
					sameColor = false;
				}
				key.append(c.getValue().getStringValue());
			}

			if (sameColor)
			{
				key.append("s");
			}
			return key.toString();
		}
		Board boardTemp = this.cloneOf();
		HandsPokerMap handsPokerMap = HandsPokerMap.getInstance();

		for(Card deckCard:deck)
		{
			int sameColor = 0;
			boolean sameValue = false;
			for(Card boardCard:boardTemp)
			{
				if (deckCard.getColor().getIntValue() == boardCard.getColor().getIntValue())
				{
					sameColor++;
				}
				if (deckCard.getValue().getIntValue() == boardCard.getValue().getIntValue()) //To Pair(Double Pair), to Three or to Four
				{
					sameValue = true;
				}
			}
			if (sameColor == 4 || sameValue) // Color
			{
				continue;
			}

			boardTemp.add(deckCard);

			StringBuilder key = new StringBuilder("");

			for(Card c:boardTemp)
			{
				key.append(c.getValue().getStringValue());
			}

			if (handsPokerMap.getHand(key.toString()).getHandName().equals("S"))
			{
				boardTemp.remove(deckCard);
			}
			else
			{
				return key.toString();
			}
		}
		return null;
	}
}
