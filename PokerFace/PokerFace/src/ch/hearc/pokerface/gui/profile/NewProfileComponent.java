
package ch.hearc.pokerface.gui.profile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import ch.hearc.pokerface.gameengine.player.profile.Profile;

public class NewProfileComponent extends Box
{

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JButton okButton;
	private ProfileListContainer parent;
	private JTextField nameProfile;
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public NewProfileComponent(ProfileListContainer parent)
	{
		super(BoxLayout.X_AXIS);

		this.parent = parent;

		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));

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
		okButton = new JButton("ok");

		nameProfile = new JTextField("Name",5);
		nameProfile.setForeground(Color.RED);
		nameProfile.setMaximumSize(new Dimension(200, 50));

		add(nameProfile);
		add(Box.createHorizontalGlue());
		add(okButton);
	}
	private void control()
	{
		okButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				NewProfileComponent.this.parent.addProfileFromNew(new Profile(nameProfile.getText(),1,10000),NewProfileComponent.this);
			}
		});
	}
}

