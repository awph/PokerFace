
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.BorderLayout;
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

	private Set<String>	cards;
	private List<String> newCards;
	private Box box;


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

		setLayout(new BorderLayout());
		box = Box.createHorizontalBox();

		add(box, BorderLayout.CENTER);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setCards(Card[] cardArray)
	{
		if (cardArray.length > cards.size())
		{
			for(Card card:cardArray)
			{
				if(this.cards.add(card.getId()))
				{
					newCards.add(card.getId());
				}
			}
		}
		else if (cardArray.length == 0)
		{
			cards.clear();
			box.removeAll();
		}

		for(String card:this.newCards)
		{
			box.add(new BoardCard(card));
		}
		newCards.clear();
	}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
