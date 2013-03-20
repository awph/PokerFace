
package ch.hearc.miscellaneoustest.handpoker;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;
import ch.hearc.miscellaneoustest.handpoker.cards.CardColor;
import ch.hearc.miscellaneoustest.handpoker.cards.CardValue;

public class Deck
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Set<Card>	cards;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Deck()
	{
		cards = new HashSet<Card>();

		cards.add(new Card(CardValue.Ace, CardColor.Clubs));
		cards.add(new Card(CardValue.Two, CardColor.Clubs));
		cards.add(new Card(CardValue.Three, CardColor.Clubs));
		cards.add(new Card(CardValue.Four, CardColor.Clubs));
		cards.add(new Card(CardValue.Five, CardColor.Clubs));
		cards.add(new Card(CardValue.Six, CardColor.Clubs));
		cards.add(new Card(CardValue.Seven, CardColor.Clubs));
		cards.add(new Card(CardValue.Eight, CardColor.Clubs));
		cards.add(new Card(CardValue.Nine, CardColor.Clubs));
		cards.add(new Card(CardValue.Ten, CardColor.Clubs));
		cards.add(new Card(CardValue.Jack, CardColor.Clubs));
		cards.add(new Card(CardValue.Queen, CardColor.Clubs));
		cards.add(new Card(CardValue.King, CardColor.Clubs));

		cards.add(new Card(CardValue.Ace, CardColor.Diamonds));
		cards.add(new Card(CardValue.Two, CardColor.Diamonds));
		cards.add(new Card(CardValue.Three, CardColor.Diamonds));
		cards.add(new Card(CardValue.Four, CardColor.Diamonds));
		cards.add(new Card(CardValue.Five, CardColor.Diamonds));
		cards.add(new Card(CardValue.Six, CardColor.Diamonds));
		cards.add(new Card(CardValue.Seven, CardColor.Diamonds));
		cards.add(new Card(CardValue.Eight, CardColor.Diamonds));
		cards.add(new Card(CardValue.Nine, CardColor.Diamonds));
		cards.add(new Card(CardValue.Ten, CardColor.Diamonds));
		cards.add(new Card(CardValue.Jack, CardColor.Diamonds));
		cards.add(new Card(CardValue.Queen, CardColor.Diamonds));
		cards.add(new Card(CardValue.King, CardColor.Diamonds));

		cards.add(new Card(CardValue.Ace, CardColor.Hearts));
		cards.add(new Card(CardValue.Two, CardColor.Hearts));
		cards.add(new Card(CardValue.Three, CardColor.Hearts));
		cards.add(new Card(CardValue.Four, CardColor.Hearts));
		cards.add(new Card(CardValue.Five, CardColor.Hearts));
		cards.add(new Card(CardValue.Six, CardColor.Hearts));
		cards.add(new Card(CardValue.Seven, CardColor.Hearts));
		cards.add(new Card(CardValue.Eight, CardColor.Hearts));
		cards.add(new Card(CardValue.Nine, CardColor.Hearts));
		cards.add(new Card(CardValue.Ten, CardColor.Hearts));
		cards.add(new Card(CardValue.Jack, CardColor.Hearts));
		cards.add(new Card(CardValue.Queen, CardColor.Hearts));
		cards.add(new Card(CardValue.King, CardColor.Hearts));

		cards.add(new Card(CardValue.Ace, CardColor.Spades));
		cards.add(new Card(CardValue.Two, CardColor.Spades));
		cards.add(new Card(CardValue.Three, CardColor.Spades));
		cards.add(new Card(CardValue.Four, CardColor.Spades));
		cards.add(new Card(CardValue.Five, CardColor.Spades));
		cards.add(new Card(CardValue.Six, CardColor.Spades));
		cards.add(new Card(CardValue.Seven, CardColor.Spades));
		cards.add(new Card(CardValue.Eight, CardColor.Spades));
		cards.add(new Card(CardValue.Nine, CardColor.Spades));
		cards.add(new Card(CardValue.Ten, CardColor.Spades));
		cards.add(new Card(CardValue.Jack, CardColor.Spades));
		cards.add(new Card(CardValue.Queen, CardColor.Spades));
		cards.add(new Card(CardValue.King, CardColor.Spades));
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void remove(Card c)
	{
		cards.remove(c);
	}

	public void add(Card c)
	{
		cards.add(c);
	}

	@Override
	public String toString()
	{
		Iterator<Card> it = cards.iterator();

		StringBuilder stringBuilder = new StringBuilder("");

		while(it.hasNext())
		{
			stringBuilder.append(it.next());
			stringBuilder.append("");
		}
		return stringBuilder.toString();
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
