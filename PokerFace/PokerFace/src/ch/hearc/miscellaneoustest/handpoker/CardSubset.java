
package ch.hearc.miscellaneoustest.handpoker;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;

public class CardSubset
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private List<Card>	cards;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public CardSubset()
	{
		cards = new LinkedList<Card>();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void add(Card c)
	{
		cards.add(c);
		sort();
	}

	public void remove(Card c)
	{
		cards.remove(c);
		sort();
	}

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder("");

		for(Card c:cards)
		{
			stringBuilder.append(c);
		}

		return stringBuilder.toString();
	}

	public String getKey()
	{
		StringBuilder stringBuilder = new StringBuilder("");

		for(Card c:cards)
		{
			stringBuilder.append(c.getValue().getStringValue());
		}

		return stringBuilder.toString();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void sort()
	{
		Collections.sort(cards, new Comparator<Card>()
		{

			@Override
			public int compare(Card c1, Card c2)
			{
				return c1.getValue().getStringValue().compareTo(c2.getValue().getStringValue());
			}
		});
	}

}
