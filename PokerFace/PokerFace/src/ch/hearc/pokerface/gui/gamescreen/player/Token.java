
package ch.hearc.pokerface.gui.gamescreen.player;

import javax.swing.JLabel;

import ch.hearc.pokerface.gui.tools.ImageShop;

public class Token extends JLabel
{

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Token(String role)
	{

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
				setIcon(ImageShop.ICON_TOKEN_D_SCALED);
			}
			else if ("Small Blind".equals(role))
			{
				setIcon(ImageShop.ICON_TOKEN_SB_SCALED);
			}
			else if ("Big Blind".equals(role))
			{
				setIcon(ImageShop.ICON_TOKEN_BB_SCALED);
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
