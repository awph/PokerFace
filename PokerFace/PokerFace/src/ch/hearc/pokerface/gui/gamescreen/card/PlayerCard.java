
package ch.hearc.pokerface.gui.gamescreen.card;

import ch.hearc.pokerface.gameengine.cards.Card;

/**
 * Card held by a player in a PlayerComponent
 */
public class PlayerCard extends CardComponent
{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * PlayerCard constructor
	 * @param cardValue:
	 * 			value of the current card displayed on board
	 */
	public PlayerCard(String cardValue)
	{
		super(cardValue);
	}

	/**
	 * PlayerCard constructor
	 * @param card:
	 * 			value of the current card displayed on board
	 */
	public PlayerCard(Card card)
	{
		super(card);
	}

}

