
package ch.hearc.pokerface.gui.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonTools
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	public static final String	BUTTON_FONT_NAME	= "resources/font/Franklin Gothic Demi Cond.ttf";

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static Font getButtonFont(boolean big) throws FontFormatException, IOException
	{
		Font font = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream(BUTTON_FONT_NAME));
		if (big)
		{
			font = font.deriveFont(50.0f);
		}
		else
		{
			font = font.deriveFont(19.0f);
		}
		return font;
	}

	public static void setStyleToButton(final JButton button, String color)
	{

		ImageIcon selectedIcon = null;
		ImageIcon normalIcon = null;

		if ("pink".equals(color))
		{
			selectedIcon = ImageShop.ICON_BUTTON_P_SELECTED_SCALED;
			normalIcon = ImageShop.ICON_BUTTON_P_NORMAL_SCALED;
		}
		else if ("red".equals(color))
		{
			selectedIcon = ImageShop.ICON_BUTTON_R_SELECTED_SCALED;
			normalIcon = ImageShop.ICON_BUTTON_R_NORMAL_SCALED;
		}
		else if ("blue".equals(color))
		{
			selectedIcon = ImageShop.ICON_BUTTON_B_SELECTED_SCALED;
			normalIcon = ImageShop.ICON_BUTTON_B_NORMAL_SCALED;
		}
		else if ("turquoise".equals(color))
		{
			selectedIcon = ImageShop.ICON_BUTTON_T_SELECTED_SCALED;
			normalIcon = ImageShop.ICON_BUTTON_T_NORMAL_SCALED;
		}
		else if ("green".equals(color))
		{
			selectedIcon = ImageShop.ICON_BUTTON_GR_SELECTED_SCALED;
			normalIcon = ImageShop.ICON_BUTTON_GR_NORMAL_SCALED;
		}
		else if ("gold".equals(color))
		{
			selectedIcon = ImageShop.ICON_BUTTON_GO_SELECTED_SCALED;
			normalIcon = ImageShop.ICON_BUTTON_GO_NORMAL_SCALED;
		}

		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setContentAreaFilled(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setForeground(Color.WHITE);

		try
		{
			button.setFont(getButtonFont("blue".equals(color)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		button.setIcon(normalIcon);

		setMouseListener(button, selectedIcon, normalIcon);
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

	private static void setMouseListener(final JButton button, final ImageIcon selectedIcon, final ImageIcon normalIcon)
	{
		button.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent evt)
			{
				button.setIcon(selectedIcon);
			}

			@Override
			public void mouseExited(MouseEvent evt)
			{
				button.setIcon(normalIcon);
			}
		});
	}
}
