
package ch.hearc.pokerface.gui.menuscreens;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.JFrameMain;
import ch.hearc.pokerface.gui.profile.ProfileListContainer;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ImagePanel;

public class JPanelProfile extends ImagePanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JButton					createProfileButton;	// TODO create profile!
	private JButton					quit;
	private JFrameMain				mainFrame;

	private ProfileListContainer	container;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelProfile(JFrameMain mainFrame) throws Exception
	{
		super(ImageIO.read(ClassLoader.getSystemResource("resources/menus/misc/background.jpg")));
		this.mainFrame = mainFrame;

		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void serializeProfiles()
	{
		container.serializeProfiles();
	}

	public void refreshProfilesData()
	{
		container.refreshProfilesData();
		removeAll();
		appearance();
	}

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

		container = new ProfileListContainer(unserializeProfiles(), this);
		createProfileButton = new JButton("Create profile");
		quit = new JButton("Quit");
	}

	private LinkedList<Profile> unserializeProfiles()
	{
		LinkedList<Profile> profileList = new LinkedList<Profile>();
		try
		{

			FileInputStream fileIn = new FileInputStream("profiles.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			profileList = (LinkedList<Profile>)in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i)
		{
			i.printStackTrace();
			return new LinkedList<Profile>();
		}
		catch (ClassNotFoundException c)
		{
			System.out.println("Profile class not found");
			c.printStackTrace();
			System.exit(0);
			return null;
		}
		return profileList;
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

		quit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}

	private void appearance()
	{
		ButtonTools.setStyleToButton(quit, "pink");
		ButtonTools.setStyleToButton(createProfileButton, "pink");

		setLayout(new BorderLayout());

		JPanel panelButtons = new JPanel();
		panelButtons.setOpaque(false);
		panelButtons.setLayout(new FlowLayout());
		panelButtons.add(createProfileButton);
		panelButtons.add(quit);

		Box outsideBox = Box.createHorizontalBox();
		Box insideBox = Box.createVerticalBox();

		insideBox.add(Box.createVerticalGlue());
		insideBox.add(container);
		insideBox.add(panelButtons);
		insideBox.add(Box.createVerticalGlue());

		outsideBox.add(Box.createHorizontalGlue());
		outsideBox.add(insideBox);
		outsideBox.add(Box.createHorizontalGlue());

		/*JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setOpaque(false);
		container.add(new JPanelGlue(BoxLayout.Y_AXIS));
		container.add(outsideBox, BorderLayout.CENTER);
		container.add(new JPanelGlue(BoxLayout.Y_AXIS));
		add(container, BorderLayout.CENTER);*/
		add(outsideBox, BorderLayout.CENTER);
	}
}
