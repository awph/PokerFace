
package ch.hearc.pokerface.gameengine.player.profile;

import java.io.IOException;

import javax.swing.JLabel;

import ch.hearc.pokerface.gui.tools.ImageTools;

public class Avatar extends JLabel
{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Avatar(int id) throws IOException
	{
		super();
		setIcon(ImageTools.loadScaledIcon("resources/avatars/" + id + ".png", 1, false));
	}
}
