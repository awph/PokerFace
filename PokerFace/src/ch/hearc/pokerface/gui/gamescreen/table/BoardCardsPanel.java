
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gui.gamescreen.card.BoardCard;
import ch.hearc.pokerface.gui.gamescreen.card.CardComponent;

public class BoardCardsPanel extends JPanel
{
	private Box				box;

	private List<BoardCard>	boardCards;

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	/**
	 * BoardCardsPanel is the graphical representation of "the board".
	 */
	public BoardCardsPanel()
	{
		setOpaque(false);

		boardCards = new ArrayList<BoardCard>();
		initBoardCards();

		setLayout(new BorderLayout());
		box = Box.createHorizontalBox();
		box.setPreferredSize(new Dimension(90 * 5 + 15, 140));

		for(BoardCard card:boardCards)
		{
			box.add(card);
		}

		add(box, BorderLayout.CENTER);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/**
	 * Sets the current board status to match current gamestate
	 * @param cardArray :
	 * 			Board cards
	 */
	public void setCards(Card[] cardArray)
	{
		int n = cardArray.length;
		if (n == 0)
		{
			setAllGhost();
			//refreshBox();
		}
		else
		{
			for(int i = 0; i < n; ++i)
			{
				CardComponent cardInBox = (CardComponent)box.getComponent(i);
				boardCards.get(i).setCard(cardArray[i].getId());
				cardInBox.setCard(cardArray[i].getId());
			}
		}
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * Initialize the board's cards to ghost
	 */
	private void initBoardCards()
	{
		for(int i = 0; i < 3; ++i)
		{
			boardCards.add(new BoardCard("flop"));
		}
		boardCards.add(new BoardCard("turn"));
		boardCards.add(new BoardCard("river"));
	}

	/**
	 * Sets all the board's cards to ghost cards (no card)
	 */
	private void setAllGhost()
	{
		boardCards.removeAll(boardCards);

		initBoardCards();

		for(int i = 0; i < 3; ++i)
		{
			((CardComponent)box.getComponent(i)).setCard("flop");
		}
		((CardComponent)box.getComponent(3)).setCard("turn");
		((CardComponent)box.getComponent(4)).setCard("river");
	}

}
