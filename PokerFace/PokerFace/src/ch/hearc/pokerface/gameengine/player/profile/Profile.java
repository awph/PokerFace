
package ch.hearc.pokerface.gameengine.player.profile;

import java.io.IOException;
import java.io.Serializable;

public class Profile implements Serializable
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private String	name;
	private Avatar	avatar;
	private double	capital;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Profile(String name, int avatarId)
	{
		this.name = name;
		try
		{
			this.avatar = new Avatar(avatarId);
		}
		catch (IOException e)
		{
			System.err.println("Problème à l'ouverture de l'avatar!");
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setAvatar(int avatarId)
	{
		try
		{
			this.avatar = new Avatar(avatarId);
		}
		catch (IOException e)
		{
			System.err.println("Problème au changement de l'avatar!");
			e.printStackTrace();
		}
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setCapital(double capital)
	{
		this.capital = capital;
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getName()
	{
		return this.name;
	}

	public Avatar getAvatar()
	{
		return this.avatar;
	}

	public double getCapital()
	{
		return this.capital;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
