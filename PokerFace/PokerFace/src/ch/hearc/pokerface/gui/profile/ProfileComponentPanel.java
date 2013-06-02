
package ch.hearc.pokerface.gui.profile;

import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.player.profile.Profile;


public abstract class ProfileComponentPanel extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProfileComponentPanel()
	{
		setOpaque(false);

		//setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));
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
public abstract Profile getProfile();
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}

