
package ch.hearc.pokerface.gui.tools;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageShop
{
	private static final String		PATH						= "resources/";

	private static final String		NAME_IMAGE_BUTTON_NORMAL	= "buttons/pink_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_SELECTED	= "buttons/pink_button_selected.png";
	private static final String		NAME_IMAGE_BACKGROUND		= "table/background.png";

	private static final boolean	IS_BLOQUANT					= true;

	public static final Image		IMAGE_BUTTON_NORMAL			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_SELECTED		= ImageTools.load(PATH + NAME_IMAGE_BUTTON_SELECTED, IS_BLOQUANT);
	public static final Image		IMAGE_BACKGROUND			= ImageTools.load(PATH + NAME_IMAGE_BACKGROUND, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_NORMAL_SCALED	= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_SELECTED_SCALED	= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_SELECTED, 0.3, IS_BLOQUANT);
}
