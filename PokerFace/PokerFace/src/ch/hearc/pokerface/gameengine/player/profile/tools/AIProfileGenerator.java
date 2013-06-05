
package ch.hearc.pokerface.gameengine.player.profile.tools;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ch.hearc.pokerface.gameengine.player.profile.Profile;


public class AIProfileGenerator
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static List<String> AINamesList = new LinkedList<String>();
	private static List<Integer> AIAvatarIDsList = new LinkedList<Integer>();

	private static final int NUMBER_OF_AVATARS = 31;
	private static final String NAMES = "Marco,Diego,Khaled,Patrick,Steve,Danick,Simon,Adrian,Mirco,Alexandre,Damiano,Kevin,Cyriaque,Eddy,Issa,Loris,Johan,Vincent,Etienne,Matthieu,Dany,David,Gary,Jason,Sébastien,Essayas,Simon,Michael,Cyrille,Thomas";

	// Tools
	private static Random random = new Random();
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	// Empty

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/


	public static Profile fetchAIProfile()
	{
		if (AINamesList.isEmpty())
		{
			fillNames();
		}
		if (AIAvatarIDsList.isEmpty())
		{
			fillAvatarIDs();
		}
		return new Profile(getAIName(),getAIAvatarID());
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static void fillAvatarIDs()
	{
		for(int i = 1; i <= NUMBER_OF_AVATARS; ++i)
		{
			AIAvatarIDsList.add(i);
		}
	}

	private static void fillNames()
	{
		AINamesList = new LinkedList<String>(Arrays.asList(NAMES.split("\\s*,\\s*")));
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	private static String getAIName()
	{
		int randomIndex = random.nextInt(AINamesList.size());
		String name = AINamesList.get(randomIndex);
		AINamesList.remove(name);
		return name;
	}

	private static int getAIAvatarID()
	{
		int randomIndex = random.nextInt(AIAvatarIDsList.size());
		int id = AIAvatarIDsList.get(randomIndex);
		AIAvatarIDsList.remove(randomIndex);
		return id;
	}
}

