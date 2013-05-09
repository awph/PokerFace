
package ch.hearc.pokerface.gameengine.player.profile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Avatar extends JLabel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private int	id;
	private final double AVATAR_SIDE_SIZE = 50.0;
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Avatar(int id) throws IOException
	{
		super();

		Image img = ImageIO.read(new File("resources/avatars/" + id + ".jpg"));

		setIcon(new ImageIcon(generateScaledBufferedImage(img, AVATAR_SIDE_SIZE / img.getWidth(null))));

		this.id = id;
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public static BufferedImage generateScaledBufferedImage(Image img, double scale)
	{
		BufferedImage bi = new BufferedImage((int)(scale * img.getWidth(null)), (int)(scale * img.getHeight(null)), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2dBI = (Graphics2D)bi.getGraphics();
		g2dBI.scale(scale, scale);
		g2dBI.drawImage(img, 0, 0, null);
		g2dBI.dispose();
		return bi;
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
