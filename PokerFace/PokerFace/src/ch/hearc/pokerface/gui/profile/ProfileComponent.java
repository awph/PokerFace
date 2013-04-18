
package ch.hearc.pokerface.gui.profile;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import ch.hearc.pokerface.gameengine.player.profile.Profile;

public class ProfileComponent extends Box
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Profile	profile;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProfileComponent(Profile profile)
	{
		super(BoxLayout.X_AXIS);
		this.profile = profile;

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
		add(new JLabel(profile.getName()));
		add(Box.createHorizontalGlue());
		add(new JLabel(Double.toString(profile.getCapital())));
		add(Box.createHorizontalGlue());
		add(profile.getAvatar());
	}

	private void control()
	{
		addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				System.out.println(e.getSource());
			}
		});
	}

}
