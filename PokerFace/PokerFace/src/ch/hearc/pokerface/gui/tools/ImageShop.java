
package ch.hearc.pokerface.gui.tools;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageShop
{
	private static final String		PATH							= "resources/";

	private static final String		NAME_IMAGE_BUTTON_P_NORMAL		= "buttons/pink_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_P_SELECTED	= "buttons/pink_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_T_NORMAL		= "buttons/turquoise_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_T_SELECTED	= "buttons/turquoise_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_GR_NORMAL		= "buttons/green_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_GR_SELECTED	= "buttons/green_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_GO_NORMAL		= "buttons/gold_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_GO_SELECTED	= "buttons/gold_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_R_NORMAL		= "buttons/red_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_R_SELECTED	= "buttons/red_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_B_NORMAL		= "buttons/blue_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_B_SELECTED	= "buttons/blue_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_QUIT_GAME = "buttons/quitGame.png";
	private static final String		NAME_IMAGE_BUTTON_QUIT_APPLICATION = "buttons/quitApplication.png";

	private static final String		NAME_IMAGE_TOKEN_BB				= "table/tokens/bb.png";
	private static final String		NAME_IMAGE_TOKEN_SB				= "table/tokens/sb.png";
	private static final String		NAME_IMAGE_TOKEN_D				= "table/tokens/d.png";

	private static final String		NAME_IMAGE_BACKGROUND			= "table/background.png";

	private static final boolean	IS_BLOQUANT						= true;

	public static final ImageIcon ICON_BUTTON_QUIT_GAME = ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_QUIT_GAME, 0.1, IS_BLOQUANT);
	public static final ImageIcon ICON_BUTTON_QUIT_APPLICATION = ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_QUIT_APPLICATION, 0.4, IS_BLOQUANT);

	public static final Image		IMAGE_BACKGROUND				= ImageTools.load(PATH + NAME_IMAGE_BACKGROUND, IS_BLOQUANT);

	public static final Image		IMAGE_TOKEN_BB					= ImageTools.load(PATH + NAME_IMAGE_TOKEN_BB, IS_BLOQUANT);
	public static final Image		IMAGE_TOKEN_SB					= ImageTools.load(PATH + NAME_IMAGE_TOKEN_SB, IS_BLOQUANT);
	public static final Image		IMAGE_TOKEN_D					= ImageTools.load(PATH + NAME_IMAGE_TOKEN_D, IS_BLOQUANT);

	public static final ImageIcon	ICON_TOKEN_BB_SCALED			= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_TOKEN_BB, 1, IS_BLOQUANT);
	public static final ImageIcon	ICON_TOKEN_SB_SCALED			= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_TOKEN_SB, 1, IS_BLOQUANT);
	public static final ImageIcon	ICON_TOKEN_D_SCALED				= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_TOKEN_D, 1, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_P_NORMAL			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_P_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_P_SELECTED			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_P_SELECTED, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_P_NORMAL_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_P_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_P_SELECTED_SCALED	= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_P_SELECTED, 0.3, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_T_NORMAL_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_T_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_T_SELECTED_SCALED	= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_T_SELECTED, 0.3, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_T_NORMAL			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_T_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_T_SELECTED			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_T_SELECTED, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_GR_NORMAL_SCALED	= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_GR_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_GR_SELECTED_SCALED	= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_GR_SELECTED, 0.3, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_GR_NORMAL			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_GR_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_GR_SELECTED		= ImageTools.load(PATH + NAME_IMAGE_BUTTON_GR_SELECTED, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_GO_NORMAL_SCALED	= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_GO_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_GO_SELECTED_SCALED	= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_GO_SELECTED, 0.3, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_GO_NORMAL			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_GO_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_GO_SELECTED		= ImageTools.load(PATH + NAME_IMAGE_BUTTON_GO_SELECTED, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_R_NORMAL_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_R_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_R_SELECTED_SCALED	= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_R_SELECTED, 0.3, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_R_NORMAL			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_R_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_R_SELECTED			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_R_SELECTED, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_B_NORMAL			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_B_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_B_SELECTED			= ImageTools.load(PATH + NAME_IMAGE_BUTTON_B_SELECTED, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_B_NORMAL_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_B_NORMAL, 0.75, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_B_SELECTED_SCALED	= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_B_SELECTED, 0.75, IS_BLOQUANT);
}
