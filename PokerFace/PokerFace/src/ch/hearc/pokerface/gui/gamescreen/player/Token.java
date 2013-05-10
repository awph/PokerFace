
package ch.hearc.pokerface.gui.gamescreen.player;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Token extends JLabel
{
	private String	role;

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Token(String role)
	{

		this.role = role;
		setToken(role);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setToken(String role)
	{
		if (!("".equals(role)))
		{
			if ("Dealer".equals(role))
			{
				role = "d";
			}
			else if ("Small Blind".equals(role))
			{
				role = "sb";
			}
			else if ("Big Blind".equals(role))
			{
				role = "bb";
			}

			try
			{
				setIcon(new ImageIcon(ImageIO.read(new File("resources/table/tokens/" + role + ".png"))));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			setIcon(null);
		}
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
