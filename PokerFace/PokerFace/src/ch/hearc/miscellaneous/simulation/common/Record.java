
package ch.hearc.miscellaneous.simulation.common;

import ch.hearc.pokerface.gameengine.cards.Card;


public class Record
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Card[] cards;
	private boolean same;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Record(Card c1,Card c2,boolean same)
	{
		cards = new Card[2];
		cards[0] = c1;
		cards[1] = c2;
		this.same = same;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();

		str.append(cards[0].getValue().getStringValue());
		str.append(cards[1].getValue().getStringValue());
		if(same)
		{
			str.append("s");
		}
		else
		{
			str.append("o");
		}

		return str.toString();
	}



	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Card getC1()
	{
		return cards[0];
	}

	public Card getC2()
	{
		return cards[1];
	}

	public boolean getSame()
	{
		return same;
	}
}

