
package ch.hearc.pokerface.gui.tools;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageTools
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * extension : .png ou .jpg
	 * marche aussi si image dans jar, qui est dans le classe path
	 */
	public static ImageIcon loadIconJar(String nameFile, boolean isBloquant)
		{
		URL url = ClassLoader.getSystemResource(nameFile);

		if (!isBloquant)
			{
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(url));
			}
		else
			{
			return new ImageIcon(url);
			}
		}

	/**
	 * extension : .png ou .jpg
	 * marche aussi si image dans jar, qui est dans le classe path
	 */
	public static Image loadJar(String nameFile, boolean isBloquant)
		{
		return loadIconJar(nameFile, isBloquant).getImage();
		}

	/**
	 * extension : .png ou .jpg
	 */
	public static ImageIcon loadIcon(String nameFile, boolean isBloquant)
		{
		if (!isBloquant)
			{
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(nameFile));
			}
		else
			{
			return new ImageIcon(nameFile);
			}
		}

	/**
	 * extension : .png ou .jpg
	 */
	public static Image load(String nameFile, boolean isBloquant)
		{
		return loadIcon(nameFile, isBloquant).getImage();
		}

	public static ImageIcon loadScaledIcon(String nameFile, double scale, boolean isBloquant)
	{
		return new ImageIcon(generateScaledBufferedImage(ImageTools.loadJar(nameFile, isBloquant), scale));
	}

	public static BufferedImage generateScaledBufferedImage(Image img, double scale)
	{
		BufferedImage bi = new BufferedImage((int)(scale * img.getWidth(null)), (int)(scale * img.getHeight(null)), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2dBI = (Graphics2D)bi.getGraphics();
		g2dBI.scale(scale, scale);
		g2dBI.drawImage(img, 0, 0, null);
		g2dBI.dispose();
		return bi;
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	}
