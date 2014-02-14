
package ch.hearc.pokerface.gui.tools;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * JPanel with a background Image
 *
 * @author Danick Fort
 */
public class ImagePanel extends JPanel
{

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private BufferedImage	backgroundImage;
	private int				xImagePosition;
	private int				yImagePosition;
	private Dimension		currentImageDimension;
	private Image			scaledImage;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Default constructor
	 *
	 * @param image
	 */
	public ImagePanel(BufferedImage image)
	{
		currentImageDimension = new Dimension();
		backgroundImage = image;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes protected						*|
	\*------------------------------------------------------------------*/

	/**
	 * Draws the current image
	 */
	@Override
	protected void paintComponent(Graphics graphics)
	{
		if (!currentImageDimension.equals(getSize()))
		{
			scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			currentImageDimension = new Dimension(getSize());
			int width = getWidth() - 1;
			int height = getHeight() - 1;

			xImagePosition = (width - scaledImage.getWidth(this)) / 2;
			yImagePosition = (height - scaledImage.getHeight(this)) / 2;
		}

		if (backgroundImage != null)
		{
			graphics.drawImage(scaledImage, xImagePosition, yImagePosition, this);
		}
	}
}
