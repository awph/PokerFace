
package ch.hearc.pokerface.gui.tools;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageShop
{
	private static final String		PATH								= "resources/";

	private static final String		NAME_IMAGE_BUTTON_P_NORMAL			= "buttons/pink_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_P_SELECTED		= "buttons/pink_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_T_NORMAL			= "buttons/turquoise_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_T_SELECTED		= "buttons/turquoise_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_GR_NORMAL			= "buttons/green_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_GR_SELECTED		= "buttons/green_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_GO_NORMAL			= "buttons/gold_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_GO_SELECTED		= "buttons/gold_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_R_NORMAL			= "buttons/red_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_R_SELECTED		= "buttons/red_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_B_NORMAL			= "buttons/blue_button_normal.png";
	private static final String		NAME_IMAGE_BUTTON_B_SELECTED		= "buttons/blue_button_selected.png";

	private static final String		NAME_IMAGE_BUTTON_QUIT_GAME			= "buttons/exit_game.png";
	private static final String		NAME_IMAGE_BUTTON_QUIT_APPLICATION	= "buttons/exit_app.png";
	private static final String		NAME_IMAGE_BUTTON_HELP				= "buttons/help.png";

	private static final String		NAME_IMAGE_TOKEN_BB					= "table/tokens/bb.png";
	private static final String		NAME_IMAGE_TOKEN_SB					= "table/tokens/sb.png";
	private static final String		NAME_IMAGE_TOKEN_D					= "table/tokens/d.png";

	private static final String		NAME_IMAGE_COIN						= "coin.png";

	private static final String		NAME_IMAGE_TABLE_BACKGROUND			= "table/background.png";
	private static final String		NAME_IMAGE_SPLASH					= "splash.png";

	private static final String		NAME_IMAGE_HANDRANKING				= "handranking.png";

	private static final String		NAME_IMAGE_APPICON					= "appicon.png";
	private static final String		NAME_IMAGE_REMOVEPROFILE			= "remove.png";
	private static final String		NAME_ICON_LOGO_TOPBAR				= "logo_pokerface_topbar.png";

	private static final String		NAME_IMAGE_HE_ARC					= "hearc.png";

	private static final boolean	IS_BLOQUANT							= true;

	public static final ImageIcon	IMAGE_HE_ARC						= ImageTools.loadIconJar(PATH + NAME_IMAGE_HE_ARC, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_QUIT_GAME				= ImageTools.loadIconJar(PATH + NAME_IMAGE_BUTTON_QUIT_GAME, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_QUIT_APPLICATION		= ImageTools.loadIconJar(PATH + NAME_IMAGE_BUTTON_QUIT_APPLICATION, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_HELP					= ImageTools.loadIconJar(PATH + NAME_IMAGE_BUTTON_HELP, IS_BLOQUANT);

	public static final Image		IMAGE_HANDRANKING					= ImageTools.loadJar(PATH + NAME_IMAGE_HANDRANKING, IS_BLOQUANT);
	public static final ImageIcon	ICON_HANDRANKING					= ImageTools.loadIconJar(PATH + NAME_IMAGE_HANDRANKING, IS_BLOQUANT);

	public static final Image		IMAGE_REMOVEPROFILE					= ImageTools.loadJar(PATH + NAME_IMAGE_REMOVEPROFILE, IS_BLOQUANT);
	public static final ImageIcon	ICON_REMOVEPROFILE_SCALED			= ImageTools.loadIconJar(PATH + NAME_IMAGE_REMOVEPROFILE, IS_BLOQUANT);

	public static final Image		IMAGE_BACKGROUND					= ImageTools.loadJar(PATH + NAME_IMAGE_TABLE_BACKGROUND, IS_BLOQUANT);
	public static final ImageIcon	ICON_BACKGROUND						= ImageTools.loadIconJar(PATH + NAME_IMAGE_TABLE_BACKGROUND, IS_BLOQUANT);
	public static final Image		IMAGE_SPLASH						= ImageTools.loadJar(PATH + NAME_IMAGE_SPLASH, IS_BLOQUANT);

	public static final Image		IMAGE_COIN							= ImageTools.loadJar(PATH + NAME_IMAGE_COIN, IS_BLOQUANT);
	public static final ImageIcon	ICON_COIN							= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_COIN, 1, IS_BLOQUANT);

	public static final Image		IMAGE_APPICON						= ImageTools.loadJar(PATH + NAME_IMAGE_APPICON, IS_BLOQUANT);

	public static final ImageIcon	ICON_LOGO_TOPBAR					= ImageTools.loadIconJar(PATH + NAME_ICON_LOGO_TOPBAR, IS_BLOQUANT);

	public static final Image		IMAGE_TOKEN_BB						= ImageTools.loadJar(PATH + NAME_IMAGE_TOKEN_BB, IS_BLOQUANT);
	public static final Image		IMAGE_TOKEN_SB						= ImageTools.loadJar(PATH + NAME_IMAGE_TOKEN_SB, IS_BLOQUANT);
	public static final Image		IMAGE_TOKEN_D						= ImageTools.loadJar(PATH + NAME_IMAGE_TOKEN_D, IS_BLOQUANT);

	public static final ImageIcon	ICON_TOKEN_BB						= ImageTools.loadIconJar(PATH + NAME_IMAGE_TOKEN_BB, IS_BLOQUANT);
	public static final ImageIcon	ICON_TOKEN_SB						= ImageTools.loadIconJar(PATH + NAME_IMAGE_TOKEN_SB, IS_BLOQUANT);
	public static final ImageIcon	ICON_TOKEN_D						= ImageTools.loadIconJar(PATH + NAME_IMAGE_TOKEN_D, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_P_NORMAL				= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_P_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_P_SELECTED				= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_P_SELECTED, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_P_NORMAL_SCALED			= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_P_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_P_SELECTED_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_P_SELECTED, 0.3, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_T_NORMAL_SCALED			= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_T_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_T_SELECTED_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_T_SELECTED, 0.3, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_T_NORMAL				= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_T_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_T_SELECTED				= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_T_SELECTED, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_GR_NORMAL_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_GR_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_GR_SELECTED_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_GR_SELECTED, 0.3, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_GR_NORMAL				= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_GR_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_GR_SELECTED			= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_GR_SELECTED, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_GO_NORMAL_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_GO_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_GO_SELECTED_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_GO_SELECTED, 0.3, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_GO_NORMAL				= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_GO_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_GO_SELECTED			= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_GO_SELECTED, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_R_NORMAL_SCALED			= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_R_NORMAL, 0.3, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_R_SELECTED_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_R_SELECTED, 0.3, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_R_NORMAL				= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_R_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_R_SELECTED				= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_R_SELECTED, IS_BLOQUANT);

	public static final Image		IMAGE_BUTTON_B_NORMAL				= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_B_NORMAL, IS_BLOQUANT);
	public static final Image		IMAGE_BUTTON_B_SELECTED				= ImageTools.loadJar(PATH + NAME_IMAGE_BUTTON_B_SELECTED, IS_BLOQUANT);

	public static final ImageIcon	ICON_BUTTON_B_NORMAL_SCALED			= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_B_NORMAL, 0.75, IS_BLOQUANT);
	public static final ImageIcon	ICON_BUTTON_B_SELECTED_SCALED		= ImageTools.loadScaledIcon(PATH + NAME_IMAGE_BUTTON_B_SELECTED, 0.75, IS_BLOQUANT);

}
