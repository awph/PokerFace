
package ch.hearc.pokerface.gui.profile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.menuscreens.JPanelProfile;

public class ProfileListContainer extends Box
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private List<ProfileComponent>	profileComponentList;
	private List<Component>			profileReferenceList;
	private final int				VERTICAL_GAP	= 10;

	// Tools
	private JButton					downArrow;
	private JButton					upArrow;
	private int						currentIndex;			// Profile displayed at the top of the list

	private final JPanelProfile profilePanel;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProfileListContainer(List<Profile> profileList, JPanelProfile profilePanel)
	{
		super(BoxLayout.Y_AXIS);
		this.profilePanel = profilePanel;

		currentIndex = 1;

		profileComponentList = new ArrayList<ProfileComponent>();
		profileReferenceList = new ArrayList<Component>();

		fillProfileListTest();

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

	public JPanelProfile getProfilePanel()
	{
		return profilePanel;
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void control()
	{
		upArrow.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae)
			{
				if (currentIndex > 1)
				{
					currentIndex--;
				}
				refreshProfiles();
				//System.out.println(currentIndex);
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
				//System.out.println(currentIndex);
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
		addProfiles();

		repaint();
		revalidate();
	}

	private void addProfiles()
	{

		for(int i = currentIndex, j=2; i < currentIndex + 3; ++i, j=j+2)
		{
			profileReferenceList.add(add(profileComponentList.get(i),j));
			Box vStrutBox = Box.createHorizontalBox();
			vStrutBox.add(createVerticalStrut(VERTICAL_GAP));
			profileReferenceList.add(add(vStrutBox,j+1));
		}
	}

	private void appearance()
	{
		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5)));
	}

	private void geometry()
	{

		upArrow = new JButton("\u2191");
		upArrow.setBackground(Color.MAGENTA);
		upArrow.setFocusPainted(false);
		upArrow.setOpaque(false);
		upArrow.setBorderPainted(false);

		downArrow = new JButton("\u2193");
		downArrow.setBackground(Color.MAGENTA);
		downArrow.setOpaque(false);
		downArrow.setFocusPainted(false);
		downArrow.setBorderPainted(false);

		Box upArrowBox = Box.createHorizontalBox();
		upArrowBox.add(createHorizontalGlue());
		upArrowBox.add(upArrow);

		Box vStrutBox = Box.createHorizontalBox();
		vStrutBox.add(createVerticalStrut(VERTICAL_GAP));

		add(upArrowBox);
		add(vStrutBox);

		addProfiles();

		Box downArrowBox = Box.createHorizontalBox();
		downArrowBox.add(createHorizontalGlue());
		downArrowBox.add(downArrow);
		add(downArrowBox);
	}

	private void fillProfileListTest()
	{
		int n = 10;
		for(int i = 0; i < n; ++i)
		{
			profileComponentList.add(new ProfileComponent(new Profile("Profile " + i, 1, 10000),this));
		}
	}
}
