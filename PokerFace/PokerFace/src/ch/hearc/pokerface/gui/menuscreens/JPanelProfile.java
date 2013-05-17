
package ch.hearc.pokerface.gui.menuscreens;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.JFrameMain;
import ch.hearc.pokerface.gui.profile.ProfileListContainer;

public class JPanelProfile extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JButton	createProfileButton; // TODO create profile!
	private JFrameMain mainFrame;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelProfile(JFrameMain mainFrame)
	{
		this.mainFrame = mainFrame;

		refreshProfileList();

		geometry();
		control();
		appearance();
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
	public JFrameMain getMainFrame()
	{
		return mainFrame;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/


	private void refreshProfileList()
	{
		// TODO Auto-generated method stub

	}

	private void geometry()
	{
		setLayout(new BorderLayout());
		Box outsideBox = Box.createHorizontalBox();
		Box insideBox = Box.createVerticalBox();

		insideBox.add(Box.createVerticalGlue());
		insideBox.add(new ProfileListContainer(new ArrayList<Profile>(),this));
		insideBox.add(Box.createVerticalGlue());

		outsideBox.add(Box.createHorizontalGlue());
		outsideBox.add(insideBox);
		outsideBox.add(Box.createHorizontalGlue());

		add(outsideBox,BorderLayout.CENTER);
	}

	private void control()
	{
	}

	private void appearance()
	{
		//setBackground(Color.GREEN);
	}
}
