
package ch.hearc.pokerface.gui.menuscreens;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.JFrameMain;
import ch.hearc.pokerface.gui.profile.ProfileListContainer;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ImagePanel;
import ch.hearc.pokerface.gui.tools.JPanelGlue;

public class JPanelProfile extends ImagePanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JButton	createProfileButton; // TODO create profile!
	private JFrameMain mainFrame;

	private ProfileListContainer container;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelProfile(JFrameMain mainFrame) throws Exception
	{
		super(ImageIO.read(new File("resources/background.jpg")));
		this.mainFrame = mainFrame;

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


	private void geometry()
	{

		container = new ProfileListContainer(new ArrayList<Profile>(),this);
		createProfileButton = new JButton("Create profile");
		ButtonTools.setStyleToButton(createProfileButton, "pink");

		setLayout(new BorderLayout());

		Box outsideBox = Box.createHorizontalBox();
		Box insideBox = Box.createVerticalBox();

		insideBox.add(Box.createVerticalGlue());
		insideBox.add(container);
		insideBox.add(createProfileButton);
		insideBox.add(Box.createVerticalGlue());

		outsideBox.add(Box.createHorizontalGlue());
		outsideBox.add(insideBox);
		outsideBox.add(Box.createHorizontalGlue());

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		container.setOpaque(false);
		container.add(new JPanelGlue(BoxLayout.Y_AXIS));
		container.add(outsideBox,BorderLayout.CENTER);
		container.add(new JPanelGlue(BoxLayout.Y_AXIS));
		add(container,BorderLayout.CENTER);
	}

	private void control()
	{
		createProfileButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				container.createNewProfile();
			}
		});
	}

	private void appearance()
	{
		//setBackground(Color.GREEN);
	}
}
