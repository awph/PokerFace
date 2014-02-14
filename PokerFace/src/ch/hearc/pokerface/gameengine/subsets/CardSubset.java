
package ch.hearc.pokerface.gameengine.subsets;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import ch.hearc.pokerface.gameengine.cards.Card;

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

	/**
	 * Mix two cardSubsets
	 * @param subset1 : First cardSubset
	 * @param subset2 : Second cardSubet
	 */
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

	/**
	 * Add a card to the cardSubset
	 * @param c : Card
	 */
	public void add(Card c)
	{
		cards.add(c);
	}

	/**
	 * Remove a card to the cardSubset
	 * @param c : Card
	 */
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

	/**
	 * Remove all the value from a cardSubset to the current one
	 * @param subset2 : The cardSubset which contains all the cards which have to be removed
	 */
	public void sub(CardSubset subset2)
	{
		for(Card card:subset2.cards)
		{
			remove(card);
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

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Card[] getArray()
	{
		return cards.toArray(new Card[0]);
	}
}
