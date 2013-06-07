
package ch.hearc.pokerface.gui.gamescreen.card;

import ch.hearc.pokerface.gameengine.cards.Card;


public class BoardCard extends CardComponent
{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public BoardCard(String cardValue)
	{
		super(cardValue);
	}

	public BoardCard(Card card)
	{
		super(card);
	}
}

