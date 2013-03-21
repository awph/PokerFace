
package ch.hearc.miscellaneoustest.handpoker;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;
import ch.hearc.miscellaneoustest.handpoker.cards.CardColor;
import ch.hearc.miscellaneoustest.handpoker.cards.CardValue;

public class Deck extends CardSubset
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

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

	//	@Override
	//	public void remove(Card c)
	//	{
	//		cards.remove(c);
	//	}
	//
	//	@Override
	//	public void add(Card c)
	//	{
	//		cards.add(c);
	//	}

	//	@Override
	//	public String toString()
	//	{
	//		Iterator<Card> it = cards.iterator();
	//
	//		StringBuilder stringBuilder = new StringBuilder("");
	//
	//		while(it.hasNext())
	//		{
	//			stringBuilder.append(it.next());
	//			stringBuilder.append("");
	//		}
	//		return stringBuilder.toString();
	//	}

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
