
package ch.hearc.pokerface.gui.tools;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;


public class ButtonTools
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private static String buttonFontName = "resources/Palacio.ttf";
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static Font getButtonFont() throws FontFormatException, IOException
	{
		Font font = Font.createFont(Font.TRUETYPE_FONT, new File(buttonFontName));
		font = font.deriveFont(20.0f);
		return font;
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}

