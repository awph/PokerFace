
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
	private Box boxContainer;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProfileComponent(Profile profile, ProfileListContainer parent)
	{
		super();

		boxContainer = Box.createHorizontalBox();
		this.profile = profile;
		this.parent = parent;

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
		setLayout(new GridLayout());

		JLabel nameProfile = new JLabel(profile.getName());
		JLabel capital = new JLabel(Integer.toString(profile.getCapital()), ImageShop.ICON_COIN, SwingConstants.CENTER);

		nameProfile.setForeground(Color.RED);
		capital.setForeground(Color.YELLOW);

		try
		{
			nameProfile.setFont(ButtonTools.getButtonFont(false));
			capital.setFont(ButtonTools.getButtonFont(false));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//add(new JLabel(FAKE_HORIZONTAL_STRUT));
		add(nameProfile);
		//add(Box.createHorizontalGlue());
		add(capital);
		//add(Box.createHorizontalGlue());
		//add(new JPanelGlue(BoxLayout.X_AXIS));
		JLabel alignedAvatar = new JLabel(profile.getAvatar().getIcon(), SwingConstants.RIGHT);
		add(alignedAvatar);
		//add(new JLabel(FAKE_HORIZONTAL_STRUT));

		//add(boxContainer);
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
