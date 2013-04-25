
package ch.hearc.pokerface.gui.options;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import ch.hearc.pokerface.gameengine.player.profile.ActiveProfile;


public class JPanelTopBar extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JLabel testLabel;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelTopBar(JPanel parentPanel) // TODO rendre SINGLETON!
	{
		setMaximumSize(new Dimension(200,parentPanel.getHeight()));
		setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.BLACK));


		testLabel = new JLabel("");
		add(testLabel);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refreshProfile()
	{
		testLabel.setText(ActiveProfile.getInstance().getProfile().getName());
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

