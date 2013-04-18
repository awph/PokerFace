
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

		setIcon(new ImageIcon(generateScaledAvatar(img)));

		this.id = id;
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	private BufferedImage generateScaledAvatar(Image img)
	{
		final double SCALE = AVATAR_SIDE_SIZE / img.getWidth(null);
		BufferedImage bi = new BufferedImage((int)(SCALE * img.getWidth(null)), (int)(SCALE * img.getHeight(null)), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2dBI = (Graphics2D)bi.getGraphics();
		g2dBI.scale(SCALE, SCALE);
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
