
package ch.hearc.pokerface.gui.profile;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ch.hearc.pokerface.gameengine.player.profile.ActiveProfile;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.options.JPanelTopBar;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ImageShop;

public class ProfileComponent extends ProfileComponentPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Profile						profile;
	private final ProfileListContainer	parent;

	// Tools
	private static final String			FAKE_HORIZONTAL_STRUT	= "   ";
	private Box							boxContainer;

	private JLabel						deleteButton;
	private JLabel						alignedAvatar;
	private JLabel						capital;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProfileComponent(Profile profile, ProfileListContainer parent)
	{
		super();

		boxContainer = Box.createHorizontalBox();
		this.profile = profile;
		this.parent = parent;

		deleteButton = new JLabel(ImageShop.ICON_REMOVEPROFILE_SCALED);

		alignedAvatar = new JLabel(profile.getAvatar().getIcon(), SwingConstants.RIGHT);
		//setAlignmentX(Component.CENTER_ALIGNMENT);
		geometry();
		control();
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

	private void geometry()
	{
		Box avatarX = Box.createHorizontalBox();
		avatarX.setOpaque(false);

		avatarX.add(Box.createHorizontalGlue());
		avatarX.add(alignedAvatar);
		avatarX.add(deleteButton);

		setLayout(new GridLayout());

		JLabel nameProfile = new JLabel(profile.getName());
		capital = new JLabel(Integer.toString(profile.getCapital()), ImageShop.ICON_COIN, SwingConstants.CENTER);

		nameProfile.setForeground(Color.RED);
		capital.setForeground(Color.YELLOW);

		try
		{
			nameProfile.setFont(ButtonTools.getButtonFont(false));
			capital.setFont(ButtonTools.getButtonFont(false));
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

		add(nameProfile);

		add(capital);

		add(avatarX);
		//add(deleteButton);
	}

	private void control()
	{
		addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{

				ActiveProfile.getInstance().setProfile(ProfileComponent.this.profile);
				JPanelTopBar.getInstance().refreshProfile(ProfileComponent.this.profile);

				parent.getProfilePanel().getMainFrame().setCard("panelMainMenu");
			}
		});

		deleteButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				parent.deleteProfile(ProfileComponent.this, profile);
			}
		});
	}

	public void refreshData()
	{
		/*if (profile == ActiveProfile.getInstance().getProfile())
		{
			capital = new JLabel(Integer.toString(ActiveProfile.getInstance().getProfile().getCapital()), ImageShop.ICON_COIN, SwingConstants.CENTER);
		}*/
		capital = new JLabel(Integer.toString(profile.getCapital()), ImageShop.ICON_COIN, SwingConstants.CENTER);
		System.out.println("refreshing " + profile.getName() + profile.getCapital());
		repaint();
		revalidate();
	}

}
