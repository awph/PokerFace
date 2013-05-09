
package ch.hearc.pokerface.gui.tools;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

/**
 * JPanel with a background Image
 *
 * @author Danick Fort
 */
public class ImagePanel extends JPanel
{
	public static final String	IMAGE_PROPERTY		= "ImagePanel.image";
	private InnerListener		innerListener		= new InnerListener();

	/**
	 * Default constructor
	 *
	 * @param image
	 */
	public ImagePanel(BufferedImage image)
	{
		super();
		addPropertyChangeListener(IMAGE_PROPERTY, innerListener);
		setImage(image);
	}

	/**
	 * Sets the image to be drawn
	 *
	 * @param image
	 */
	public void setImage(BufferedImage image)
	{
		putClientProperty(IMAGE_PROPERTY, image);
	}

	/**
	 * Method that return the currently drawn image
	 *
	 * @return the image
	 */
	public BufferedImage getImage()
	{
		return (BufferedImage)getClientProperty(IMAGE_PROPERTY);
	}

	/**
	 * Draws the current image
	 */
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		BufferedImage image = getImage();

		double scaleFactor = Math.min(1d, getScaleFactorToFill(new Dimension(image.getWidth(), image.getHeight()), getSize()));

	    int scaleWidth = (int) Math.round(image.getWidth() * scaleFactor);
	    int scaleHeight = (int) Math.round(image.getHeight() * scaleFactor);

	    //Image scaled = image.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
	    Image scaled = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

	    int width = getWidth() - 1;
	    int height = getHeight() - 1;

	    int x = (width - scaled.getWidth(this)) / 2;
	    int y = (height - scaled.getHeight(this)) / 2;


		if (image != null)
		{
			graphics.drawImage(scaled, x, y, this);
		}
	}

	/**
	 * Listener class of the JPanel
	 */
	private class InnerListener implements PropertyChangeListener
	{
		/**
		 * Called when properties change
		 */
		public void propertyChange(PropertyChangeEvent event)
		{
			String property = event.getPropertyName();
			if (property.equals(IMAGE_PROPERTY))
			{
				System.out.println("t");
				BufferedImage image = getImage();
				int width = 0;
				int height = 0;
				if (image != null)
				{
					width = image.getWidth(null);
					height = image.getHeight(null);
				}
				Dimension size = new Dimension(width, height);
				setPreferredSize(size);
				setMinimumSize(size);
				repaint();
			}
		}
	}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/


	public static double getScaleFactor(int iMasterSize, int iTargetSize) {

	    double dScale = 1;
	    if (iMasterSize > iTargetSize) {

	        dScale = (double) iTargetSize / (double) iMasterSize;

	    } else {

	        dScale = (double) iTargetSize / (double) iMasterSize;

	    }

	    return dScale;

	}

	public static double getScaleFactorToFit(Dimension original, Dimension toFit) {

	    double dScale = 1d;

	    if (original != null && toFit != null) {

	        double dScaleWidth = getScaleFactor(original.width, toFit.width);
	        double dScaleHeight = getScaleFactor(original.height, toFit.height);

	        dScale = Math.min(dScaleHeight, dScaleWidth);

	    }

	    return dScale;

	}

	public static double getScaleFactorToFill(Dimension masterSize, Dimension targetSize) {

	    double dScaleWidth = getScaleFactor(masterSize.width, targetSize.width);
	    double dScaleHeight = getScaleFactor(masterSize.height, targetSize.height);

	    double dScale = Math.max(dScaleHeight, dScaleWidth);

	    return dScale;

	}
}
