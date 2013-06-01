
package ch.hearc.pokerface.gui.profile;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.menuscreens.JPanelProfile;
import ch.hearc.pokerface.gui.tools.ColorShop;

public class ProfileListContainer extends Box
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private LinkedList<ProfileComponentPanel>	profileComponentList;
	private List<Component>						profileReferenceList;
	private final int							VERTICAL_GAP	= 10;

	// Tools
	private JButton								downArrow;
	private JButton								upArrow;
	private int									currentIndex;			// Profile displayed at the top of the list

	private final JPanelProfile					profilePanel;

	private LinkedList<Profile>					profileList;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProfileListContainer(LinkedList<Profile> profileList, JPanelProfile profilePanel)
	{
		super(BoxLayout.Y_AXIS);
		this.profilePanel = profilePanel;

		this.profileList = profileList;

		currentIndex = 0;

		profileComponentList = new LinkedList<ProfileComponentPanel>();
		profileReferenceList = new ArrayList<Component>();

		fillProfileList();

		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void deleteProfile(ProfileComponent profileComponent, Profile profile)
	{
		if (JOptionPane.showConfirmDialog(null, "Confirm deletion of " + profile.getName() + " ?") == 1) { return; }

		removeProfiles();
		profileComponentList.remove(profileComponent);
		profileList.remove(profile);
		addProfilesToBox();
		repaint();
		revalidate();

		serializeProfiles();

		getParent().repaint();
	}

	public void serializeProfiles()
	{
		try
		{
			/*List<Profile> list = new ArrayList<Profile>();
			int n = 10;
			for(int i = 0; i < n; i++)
			{
				list.add(new Profile("Profile " + i, 1, 10000));
			}*/
			FileOutputStream fileOut = new FileOutputStream("profiles.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(profileList);
			out.close();
			fileOut.close();
		}
		catch (IOException i)
		{
			i.printStackTrace();
		}
	}

	public void createNewProfile()
	{
		removeProfiles();

		currentIndex = 0;

		NewProfileComponent newProfile = new NewProfileComponent(this);
		profileComponentList.addFirst(newProfile);

		addProfilesToBox();
		repaint();
		revalidate();
	}

	public void addProfileFromNew(Profile profile, NewProfileComponent npc)
	{
		removeProfiles();

		currentIndex = 0;
		profileComponentList.remove(npc);
		profileComponentList.addFirst(new ProfileComponent(profile, this));
		profileList.addFirst(profile);
		serializeProfiles();

		addProfilesToBox();
		repaint();
		revalidate();
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public JPanelProfile getProfilePanel()
	{
		return profilePanel;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private MouseListener	arrowListener	= new MouseAdapter()
											{

												@Override
												public void mouseReleased(MouseEvent e)
												{
													profilePanel.repaint();
													getParent().repaint();
													((Component)e.getSource()).setForeground(Color.GREEN);
												}

												@Override
												public void mousePressed(MouseEvent e)
												{
													profilePanel.repaint();
													getParent().repaint();
													((Component)e.getSource()).setForeground(Color.WHITE); // Color of background when clicking. Cant remove background when clicking, because it is dictated by Look and Feel
												}

												@Override
												public void mouseExited(MouseEvent e)
												{
													profilePanel.repaint();
													getParent().repaint();
												}

												@Override
												public void mouseEntered(MouseEvent e)
												{

													profilePanel.repaint();
													getParent().repaint();
												}

												@Override
												public void mouseClicked(MouseEvent arg0)
												{
													profilePanel.repaint();
													getParent().repaint();
												}
											};

	private void control()
	{
		upArrow.addMouseListener(arrowListener);
		downArrow.addMouseListener(arrowListener);

		upArrow.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae)
			{
				if (currentIndex >= 1)
				{
					currentIndex--;
				}
				refreshProfiles();
			}
		});

		downArrow.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae)
			{
				if (currentIndex < profileComponentList.size() - 3)
				{
					currentIndex++;
				}
				refreshProfiles();
			}
		});
	}

	private void removeProfiles()
	{
		for(Component component:profileReferenceList)
		{
			remove(component);
		}
	}

	private void refreshProfiles()
	{
		removeProfiles();
		addProfilesToBox();

		repaint();
		revalidate();
	}

	private void addProfilesToBox()
	{

		for(int i = currentIndex, j = 1; i < currentIndex + 3; i++, j = j + 1)
		{
			if (i < profileComponentList.size())
			{
				profileReferenceList.add(add(profileComponentList.get(i), j));
			}
		}
	}

	private void appearance()
	{
		setBackground(ColorShop.PF_BACKGROUND_PROFILE_CONTAINER);
		setOpaque(true);
	}

	private void geometry()
	{

		upArrow = new JButton("\u2191");
		upArrow.setBackground(Color.MAGENTA);
		upArrow.setFocusPainted(false);
		upArrow.setOpaque(false);
		upArrow.setBorderPainted(false);
		upArrow.setForeground(Color.GREEN);

		downArrow = new JButton("\u2193");
		downArrow.setBackground(Color.MAGENTA);
		downArrow.setOpaque(false);
		downArrow.setFocusPainted(false);
		downArrow.setBorderPainted(false);
		downArrow.setForeground(Color.GREEN);

		Box upArrowBox = Box.createHorizontalBox();
		upArrowBox.add(createHorizontalGlue());
		upArrowBox.add(upArrow);

		Box vStrutBox = Box.createHorizontalBox();
		vStrutBox.add(createVerticalStrut(VERTICAL_GAP));

		add(upArrowBox);
		//add(vStrutBox);

		addProfilesToBox();

		Box downArrowBox = Box.createHorizontalBox();
		downArrowBox.add(createHorizontalGlue());
		downArrowBox.add(downArrow);
		add(downArrowBox);
	}

	private void fillProfileList()
	{
		for(Profile profile:profileList)
		{
			profileComponentList.add(new ProfileComponent(profile, this));
		}
	}

	public void refreshProfilesData()
	{
		for(ProfileComponentPanel profileComponent:profileComponentList)
		{
			if (profileComponent instanceof ProfileComponent)
			{
				((ProfileComponent)profileComponent).refreshData();
			}
		}
		repaint();
		getParent().repaint();
		refreshProfiles();
	}

}
