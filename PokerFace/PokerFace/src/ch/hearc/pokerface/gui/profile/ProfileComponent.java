
package ch.hearc.pokerface.gui.profile;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import ch.hearc.pokerface.gameengine.player.profile.ActiveProfile;
import ch.hearc.pokerface.gameengine.player.profile.Profile;

public class ProfileComponent extends Box
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Profile	profile;
	private final int HORIZONTAL_GAP = 10;
	private final ProfileListContainer parent;
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
		add(new JLabel(profile.getName()));
		add(Box.createHorizontalGlue());
		add(new JLabel(Double.toString(profile.getCapital())));
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
				System.err.println(ProfileComponent.this.profile.getName());
				ActiveProfile.getInstance().setProfile(ProfileComponent.this.profile);
				parent.getProfilePanel().getMainFrame().setCard("panelMainMenu");
			}
		});
	}

}
