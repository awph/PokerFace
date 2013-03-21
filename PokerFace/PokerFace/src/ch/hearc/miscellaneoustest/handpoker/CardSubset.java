
package ch.hearc.miscellaneoustest.handpoker;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;

public class CardSubset implements Iterable<Card>
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Set<Card>	cards;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public CardSubset()
	{
		cards = new TreeSet<Card>();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void add(Card c)
	{
		cards.add(c);
		//		sort();
	}

	public void remove(Card c)
	{
		cards.remove(c);
		//		sort();
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
		if (!isHand()) { return null; }
		//TODO: some color
		StringBuilder stringBuilder = new StringBuilder("");

		for(Card c:cards)
		{
			stringBuilder.append(c.getValue().getStringValue());
		}

		return stringBuilder.toString();
	}

	public static CardSubset union(CardSubset subset1, CardSubset subset2)
	{
		CardSubset out = new CardSubset();

		for(Card card:subset1.cards)
		{
			out.add(new Card(card));
		}

		for(Card card:subset2.cards)
		{
			out.add(new Card(card));
		}

		return out;
	}

	public void sub(CardSubset subset2)
	{
		for(Card card:subset2.cards)
		{
			remove(card); //TODO: error si card n'existe pas
		}
	}

	@Override
	public Iterator<Card> iterator()
	{
		return cards.iterator();
	}

	public int size()
	{
		return cards.size();
	}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isHand()
	{
		return cards.size() == 5;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	//	private void sort()
	//	{
	//		Collections.
	//		Collections.sort(cards, new Comparator<Card>()
	//		{
	//
	//			@Override
	//			public int compare(Card c1, Card c2)
	//			{
	//				return c1.getValue().getStringValue().compareTo(c2.getValue().getStringValue());
	//			}
	//		});
	//	}

}
