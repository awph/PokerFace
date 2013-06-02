
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Box;
import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gui.gamescreen.card.BoardCard;

public class BoardCardsPanel extends JPanel
{

	private Set<String>		cards;
	private List<String>	newCards;
	private Box				box;

	private List<BoardCard>	boardCards;

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public BoardCardsPanel()
	{
		setOpaque(false);
		cards = new TreeSet<String>();
		newCards = new ArrayList<String>();

		boardCards = new ArrayList<BoardCard>();
		initBoardCards();

		setLayout(new BorderLayout());
		box = Box.createHorizontalBox();
		box.setPreferredSize(new Dimension(100*5+20,140));

		for(BoardCard card:boardCards)
		{
			box.add(card);
		}

		add(box, BorderLayout.CENTER);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	private void initBoardCards()
	{
		for(int i = 0; i < 5; ++i)
		{
			boardCards.add(new BoardCard("ghost"));
		}
	}

	private void setAllGhost()
	{
		for(BoardCard card:boardCards)
		{
			card.setCard("ghost");
		}
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setCards(Card[] cardArray)
	{
		int n = cardArray.length;
		if (n == 0)
		{
			setAllGhost();
		}
		else
		{
			for(int i = 0; i < n; ++i)
			{
				boardCards.get(i).setCard(cardArray[i].getId());
			}
		}
	}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
