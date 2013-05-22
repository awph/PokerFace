
package ch.hearc.pokerface.gameengine.player.profile.tools;

import java.util.ArrayList;
import java.util.Arrays;
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

	private static List<String> AINamesList = new ArrayList<String>();
	private static List<Integer> AIAvatarIDsList = new ArrayList<Integer>();

	private static final int NUMBER_OF_AVATARS = 31;
	private static final String NAMES = "Marco,Diego,Khaled,Patrick,Steve,Danick,Simon,Adrian,Mirco,Alexandre,Damiano,Kevin,Cyriaque,Eddy,Issa,Loris,Johan,Vincent,Etienne,Matthieu,Dany,David,Gary,Jason,Matthieu,Sébastien,Essayas,Simon,Michael,Cyrille,Thomas";

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
		AINamesList = Arrays.asList(NAMES.split("\\s*,\\s*"));
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	private static String getAIName()
	{
		return AINamesList.get(random.nextInt(AINamesList.size()));
	}

	private static int getAIAvatarID()
	{
		return AIAvatarIDsList.get(random.nextInt(AIAvatarIDsList.size()));
	}
}

