
package ch.hearc.pokerface.gui.gamescreen.card;

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

	private String	cardValue;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/** Abstract class for a card graphical component. Use PlayerCard and BoardCard instead.
	 *
	 * @param card
	 */
	public CardComponent(Card card)
	{
		this(card.getId());
	}

	public CardComponent(String cardValue)
	{
		this.cardValue = cardValue;
		updateImage();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setCard(String cardValue)
	{
		if (!this.cardValue.equals(cardValue))
		{
			this.cardValue = cardValue;
			updateImage();
		}
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void updateImage()
	{
		if (cardValue.equals("empty"))
		{
			setIcon(null);
		}
		else
		{
			try
			{
				setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("resources/table/cards/" + cardValue + ".png"))));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			repaint();
		}
	}

}
