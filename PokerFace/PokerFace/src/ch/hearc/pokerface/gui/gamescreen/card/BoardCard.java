
package ch.hearc.pokerface.gui.gamescreen.card;

import ch.hearc.pokerface.gameengine.cards.Card;

/**
 * Card displayed on the board (flop 3x, turn 1x, river 1x)
 */
public class BoardCard extends CardComponent
{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * BoardCard constructor
	 * @param cardValue:
	 * 			value of the current card displayed on board
	 */
	public BoardCard(String cardValue)
	{
		super(cardValue);
	}

	/**
	 * BoardCard constructor
	 * @param card:
	 * 			value of the current card displayed on board
	 */
	public BoardCard(Card card)
	{
		super(card);
	}
}

