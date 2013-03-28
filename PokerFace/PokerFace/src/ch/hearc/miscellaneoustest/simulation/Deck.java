
package ch.hearc.miscellaneoustest.simulation;

import java.util.Iterator;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;
import ch.hearc.miscellaneoustest.handpoker.cards.CardColor;
import ch.hearc.miscellaneoustest.handpoker.cards.CardValue;
import ch.hearc.miscellaneoustest.handpoker.subset.CardSubset;

public class Deck extends CardSubset
{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Deck()
	{
		super();

		add(new Card(CardValue.Ace, CardColor.Clubs));
		add(new Card(CardValue.Two, CardColor.Clubs));
		add(new Card(CardValue.Three, CardColor.Clubs));
		add(new Card(CardValue.Four, CardColor.Clubs));
		add(new Card(CardValue.Five, CardColor.Clubs));
		add(new Card(CardValue.Six, CardColor.Clubs));
		add(new Card(CardValue.Seven, CardColor.Clubs));
		add(new Card(CardValue.Eight, CardColor.Clubs));
		add(new Card(CardValue.Nine, CardColor.Clubs));
		add(new Card(CardValue.Ten, CardColor.Clubs));
		add(new Card(CardValue.Jack, CardColor.Clubs));
		add(new Card(CardValue.Queen, CardColor.Clubs));
		add(new Card(CardValue.King, CardColor.Clubs));

		add(new Card(CardValue.Ace, CardColor.Diamonds));
		add(new Card(CardValue.Two, CardColor.Diamonds));
		add(new Card(CardValue.Three, CardColor.Diamonds));
		add(new Card(CardValue.Four, CardColor.Diamonds));
		add(new Card(CardValue.Five, CardColor.Diamonds));
		add(new Card(CardValue.Six, CardColor.Diamonds));
		add(new Card(CardValue.Seven, CardColor.Diamonds));
		add(new Card(CardValue.Eight, CardColor.Diamonds));
		add(new Card(CardValue.Nine, CardColor.Diamonds));
		add(new Card(CardValue.Ten, CardColor.Diamonds));
		add(new Card(CardValue.Jack, CardColor.Diamonds));
		add(new Card(CardValue.Queen, CardColor.Diamonds));
		add(new Card(CardValue.King, CardColor.Diamonds));

		add(new Card(CardValue.Ace, CardColor.Hearts));
		add(new Card(CardValue.Two, CardColor.Hearts));
		add(new Card(CardValue.Three, CardColor.Hearts));
		add(new Card(CardValue.Four, CardColor.Hearts));
		add(new Card(CardValue.Five, CardColor.Hearts));
		add(new Card(CardValue.Six, CardColor.Hearts));
		add(new Card(CardValue.Seven, CardColor.Hearts));
		add(new Card(CardValue.Eight, CardColor.Hearts));
		add(new Card(CardValue.Nine, CardColor.Hearts));
		add(new Card(CardValue.Ten, CardColor.Hearts));
		add(new Card(CardValue.Jack, CardColor.Hearts));
		add(new Card(CardValue.Queen, CardColor.Hearts));
		add(new Card(CardValue.King, CardColor.Hearts));

		add(new Card(CardValue.Ace, CardColor.Spades));
		add(new Card(CardValue.Two, CardColor.Spades));
		add(new Card(CardValue.Three, CardColor.Spades));
		add(new Card(CardValue.Four, CardColor.Spades));
		add(new Card(CardValue.Five, CardColor.Spades));
		add(new Card(CardValue.Six, CardColor.Spades));
		add(new Card(CardValue.Seven, CardColor.Spades));
		add(new Card(CardValue.Eight, CardColor.Spades));
		add(new Card(CardValue.Nine, CardColor.Spades));
		add(new Card(CardValue.Ten, CardColor.Spades));
		add(new Card(CardValue.Jack, CardColor.Spades));
		add(new Card(CardValue.Queen, CardColor.Spades));
		add(new Card(CardValue.King, CardColor.Spades));
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public Card getNewCard()
	{
		int key = (int)(Math.random() * (size()));
		Card c = null;
		int i = 0;

		Iterator<Card> it = this.iterator();
		while(it.hasNext() && i++ <= key)
		{
			c = it.next();
		}

		remove(c);

		return c;
	}

	//A SUPPRIMER DANS LA VERSION FINALE
	public void remove(Card c, boolean b)
	{
		Iterator<Card> it = this.iterator();

		while(it.hasNext())
		{
			Card temp = it.next();
			if (temp.equals(c))
			{
				remove(temp);
				break;
			}
		}
	}

}
