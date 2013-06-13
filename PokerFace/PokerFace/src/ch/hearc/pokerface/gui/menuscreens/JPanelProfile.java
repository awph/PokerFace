
package ch.hearc.pokerface.gui.menuscreens;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	private JButton					createProfileButton;
	private JButton					quit;
	private JFrameMain				mainFrame;

	private ProfileListContainer	container;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * First panel shown when opening the game. Lists profiles found in file "./profile.dat"
	 * Allows to create or delete profiles.
	 * @param mainFrame
	 * @throws Exception
	 */
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

	@SuppressWarnings("unchecked")
	private LinkedList<Profile> unserializeProfiles()
	{
		LinkedList<Profile> profileList = new LinkedList<Profile>();
		try
		{

            File f = new File(System.getProperty("user.home") + "\\.PokerFaceProfiles.dat");

            // If file doesn't exist, create a new profile.dat containing an empty list of profiles
            if (!f.exists()) {
                f.createNewFile();

    			FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home") + "\\.PokerFaceProfiles.dat");
    			ObjectOutputStream out = new ObjectOutputStream(fileOut);
    			out.writeObject(new LinkedList<Profile>());
    			out.close();
    			fileOut.close();
            }

			FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + "\\.PokerFaceProfiles.dat");
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
				toggleCreateProfileButton();
			}
		});

		quit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainFrame.closeApp();
				System.exit(0); // 0 normal, -1 anormal
			}
		});
	}

	private void appearance()
	{
		ButtonTools.setStyleToButton(quit, "pink");
		ButtonTools.setStyleToButton(createProfileButton, "pink");

		container.setSize(200, 200);

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

		add(outsideBox, BorderLayout.CENTER);
	}

	public void toggleCreateProfileButton()
	{
		createProfileButton.setEnabled(!createProfileButton.isEnabled());
	}
}
