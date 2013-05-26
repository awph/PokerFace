
package ch.hearc.pokerface.gui.profile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.JPanelGlue;

public class NewProfileComponent extends ProfileComponentPanel
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
		super();

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

		setLayout(new GridLayout());
		okButton = new JButton("OK");

		nameProfile = new JTextField("Name",5);
		nameProfile.setForeground(Color.RED);
		try
		{
			nameProfile.setFont(ButtonTools.getButtonFont(false));
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nameProfile.setMaximumSize(new Dimension(200, 50));

		add(nameProfile);
		add(new JPanelGlue(BoxLayout.X_AXIS));
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

