
package ch.hearc.miscellaneoustest.handpoker.subset;

import ch.hearc.miscellaneoustest.handpoker.HandsPokerMap;
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
		if (size() < 5)
		{
			super.add(c);
		}
	}

	@Override
	public Board cloneOf()
	{
		return new Board(this);
	}

	public String getKey(Deck deck)
	{
		if (size() != 4)
		{
			System.err.println("Board != 4");
			return null;
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
		return null;//TODO: make getRank au lieu de getKey
	}
}
