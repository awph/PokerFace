
package ch.hearc.pokerface.gameengine.player.profile;

import java.io.IOException;

import javax.swing.JLabel;

import ch.hearc.pokerface.gui.tools.ImageTools;

public class Avatar extends JLabel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private int				id;
	private final double	AVATAR_SIDE_SIZE	= 50.0;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Avatar(int id) throws IOException
	{
		super();

		setIcon(ImageTools.loadScaledIcon("resources/avatars/" + id + ".png", 1, false));

		this.id = id;
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

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
