
package ch.hearc.miscellaneoustest.handpoker.subset;

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

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

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

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void add(Card c)
	{
		cards.add(c);
	}

	public void remove(Card c)
	{
		try
		{
			cards.remove(c);
		}
		catch (NullPointerException e)
		{
		}
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

}
