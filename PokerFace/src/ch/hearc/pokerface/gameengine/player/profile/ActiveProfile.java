
package ch.hearc.pokerface.gameengine.player.profile;

/*
 * Singleton class
 *
 */
public final class ActiveProfile
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private static volatile ActiveProfile instance = null;

	// Inputs / Outputs
	private Profile profile;
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*
	 * @return The only instance of the class, which is the current active profile chosen by the user in the profile panel
	 */
	public final static ActiveProfile getInstance()
	{
		if (ActiveProfile.instance == null)
		{
			synchronized(ActiveProfile.class) {
				if (ActiveProfile.instance == null)
				{
					ActiveProfile.instance = new ActiveProfile();
				}
			}
		}
		return ActiveProfile.instance;
	}

	private ActiveProfile()
	{

	}
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setProfile(Profile profile)
	{
		this.profile = profile;
	}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Profile getProfile()
	{
		return this.profile;
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}

