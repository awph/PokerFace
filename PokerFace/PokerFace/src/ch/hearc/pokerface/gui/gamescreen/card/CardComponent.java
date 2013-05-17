
package ch.hearc.pokerface.gui.gamescreen.card;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ch.hearc.pokerface.gameengine.cards.Card;

public abstract class CardComponent extends JLabel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/


	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public CardComponent(Card card)
	{
		this(card.getId());
	}

	public CardComponent(String cardValue)
	{
		setCard(cardValue);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setCard(String cardValue)
	{
		try
		{
			setIcon(new ImageIcon(ImageIO.read(new File("resources/table/cards/" + cardValue + ".png"))));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
