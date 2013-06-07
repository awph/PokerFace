
package ch.hearc.pokerface.gui.profile;

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
import ch.hearc.pokerface.gui.tools.ColorShop;
import ch.hearc.pokerface.gui.tools.ImageShop;

public class ProfileComponent extends ProfileComponentPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Profile						profile;
	private final ProfileListContainer	parent;

	// Tools
	private JLabel						deleteButton;
	private JLabel						alignedAvatar;
	private JLabel						capital;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public ProfileComponent(Profile profile, ProfileListContainer parent)
	{
		super();

		this.profile = profile;
		this.parent = parent;

		deleteButton = new JLabel(ImageShop.ICON_REMOVEPROFILE_SCALED);

		alignedAvatar = new JLabel(profile.getAvatar().getIcon(), SwingConstants.RIGHT);
		geometry();
		control();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
	{
		Box deleteBox = Box.createHorizontalBox();
		deleteBox.setOpaque(false);

		deleteBox.add(Box.createHorizontalGlue());
		deleteBox.add(deleteButton);

		setLayout(new GridLayout());

		JLabel nameProfile = new JLabel(profile.getName());
		capital = new JLabel("$" + Integer.toString(profile.getCapital()), ImageShop.ICON_COIN, SwingConstants.CENTER);

		nameProfile.setForeground(ColorShop.PF_RED);
		capital.setForeground(ColorShop.PF_GOLD_COLOR);

		try
		{
			nameProfile.setFont(ButtonTools.getButtonFont(false).deriveFont(26f));
			capital.setFont(ButtonTools.getButtonFont(false).deriveFont(26f));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Box nameAvatar = Box.createHorizontalBox();
		deleteBox.setOpaque(false);
		nameAvatar.add(alignedAvatar);
		nameAvatar.add(nameProfile);

		add(nameAvatar);

		add(capital);

		add(deleteBox);
	}

	private void control()
	{
		addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{

				if (ProfileComponent.this.profile.getCapital() > 0)
				{
					ActiveProfile.getInstance().setProfile(ProfileComponent.this.profile);
					JPanelTopBar.getInstance().refreshProfile(ProfileComponent.this.profile);

					parent.getProfilePanel().getMainFrame().setCard("panelMainMenu");
				}
				else
				{
					ProfileComponent.this.setEnabled(false);
					parent.deleteProfile(ProfileComponent.this, profile);
				}
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
		removeAll();
		geometry();
		repaint();
		revalidate();
	}

	@Override
	public Profile getProfile()
	{
		return profile;
	}
}
