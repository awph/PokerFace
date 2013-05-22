
package ch.hearc.pokerface.gui.profile;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import ch.hearc.pokerface.gameengine.player.profile.ActiveProfile;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.options.JPanelTopBar;

public class ProfileComponent extends Box
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Profile						profile;
	private final ProfileListContainer	parent;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProfileComponent(Profile profile, ProfileListContainer parent)
	{
		super(BoxLayout.X_AXIS);
		this.profile = profile;
		this.parent = parent;

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
		add(new JLabel("   ")); // horizontal strut behaves wierd

		JLabel nameProfile = new JLabel(profile.getName());
		nameProfile.setForeground(Color.RED);

		add(nameProfile);
		add(Box.createHorizontalGlue());
		JLabel capital = new JLabel(Integer.toString(profile.getCapital()));
		capital.setForeground(Color.YELLOW);
		add(capital);
		add(Box.createHorizontalGlue());
		add(profile.getAvatar());
		add(new JLabel("   "));
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
	}

}
