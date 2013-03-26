
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

	public CardSubset(CardSubset cardSubsetSource)
	{
		this();
		for(Card card:cardSubsetSource)
		{
			add(card.cloneOf());
		}
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

	public Card[] getArray()
	{
		return cards.toArray(new Card[0]);
	}

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

	public void sub(CardSubset subset2)
	{
		for(Card card:subset2.cards)
		{
			remove(card); //TODO: error si card n'existe pas
		}
	}

	public int size()
	{
		return cards.size();
	}

	public CardSubset cloneOf()
	{
		return new CardSubset(this);
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

	@Override
	public Iterator<Card> iterator()
	{
		return cards.iterator();
	}

}
