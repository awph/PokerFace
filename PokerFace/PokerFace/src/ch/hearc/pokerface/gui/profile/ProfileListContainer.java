
package ch.hearc.pokerface.gui.profile;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.pokerface.gameengine.player.profile.Profile;

public class ProfileListContainer extends Box
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private List<ProfileComponent>	profileComponentList;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProfileListContainer(List<Profile> profileList)
	{
		super(BoxLayout.Y_AXIS);
		profileComponentList = new ArrayList<ProfileComponent>();

		fillProfileListTest();

		geometry();
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
		for(ProfileComponent component:profileComponentList)
		{
			add(component);
			add(createVerticalStrut(10));
		}
	}

	private void fillProfileListTest()
	{
		int n = 3;
		for(int i = 0; i < n; ++i)
		{
			profileComponentList.add(new ProfileComponent(new Profile("Profile " + i, i)));
		}
	}
}
