
package ch.hearc.pokerface.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ch.hearc.pokerface.gui.gamescreen.table.board.JPanelGameBoard;
import ch.hearc.pokerface.gui.menuscreens.JOPTableConfiguration;
import ch.hearc.pokerface.gui.menuscreens.JPanelMainMenu;
import ch.hearc.pokerface.gui.menuscreens.JPanelProfile;
import ch.hearc.pokerface.gui.options.JPanelTopBar;
import ch.hearc.pokerface.gui.tools.ImageShop;

public class JFrameMain extends JFrame
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private JPanelProfile	panelProfile;
	private JPanelMainMenu	panelMainMenu;
	private JPanelGameBoard	panelGameBoard;
	private CardLayout		layout;
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public JFrameMain()
	{
		JPanelTopBar.getInstance().setFrameMain(this);
		
		geometry();
		control();
		appearance();
	}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	// CARD MANIPULATIONS
	public void setCard(String card)
	{
		if (card == "panelGameBoard")
		{
			JOPTableConfiguration tableConfig = new JOPTableConfiguration(this, panelGameBoard);
			if (!tableConfig.switchToGame()) { return; // Abort the process of switching to gameBoard
			}
			// Game is launching
			
			panelGameBoard.refreshAllComponents();
			setFullscreen(true);
		}
		else if (card == "panelProfile")
		{
			panelProfile.refreshProfilesData();
		}
		
		else if (card == "panelMainMenu")
		{
			panelMainMenu.refreshPlayButtonStatus();
		}
		layout.show(this.getContentPane(), card);
	}
	
	public void gameToMainMenu()
	{
		setFullscreen(false);
		setSize(1200, (int)(1200 * 0.75));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)((dim.getWidth() - getWidth()) / 2), (int)((dim.getHeight() - getHeight()) / 2));
		
		panelMainMenu.refreshTopBar();
		setCard("panelMainMenu");
		panelGameBoard.stop();
	}
	
	public void closeApp()
	{
		panelProfile.serializeProfiles();
		if (JOptionPane.showConfirmDialog(null, "Confirm quit?") != 0) { return; }
		System.exit(0);
	}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public JPanelProfile getProfilePanel()
	{
		return panelProfile;
	}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void setFullscreen(boolean isFullscreened)
	{
		setVisible(false);
		dispose();
		if (isFullscreened)
		{
			setExtendedState(MAXIMIZED_BOTH);
		}
		else
		{
			setExtendedState(NORMAL);
			setSize(1200, (int)(1200 * 0.75));
		}
		/*setUndecorated(!isUndecorated());
		if (isFullscreened)
		{
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
		}
		else
		{
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
		}*/
		setVisible(true);
	}
	
	private void geometry()
	{
		
		/**
		 * Layout
		 */
		layout = new CardLayout();
		setLayout(layout);
		
		/**
		 * Instanciations
		 */
		try
		{
			panelProfile = new JPanelProfile(this);
			panelMainMenu = new JPanelMainMenu(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		panelGameBoard = new JPanelGameBoard();
		
		/**
		 * Adds
		 */
		
		add(panelProfile, "panelProfile");
		add(panelGameBoard, "panelGameBoard");
		add(panelMainMenu, "panelMainMenu");
	}
	
	private void control()
	{
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent arg0)
			{
				closeApp();
			}
		});
	}
	
	private void appearance()
	{
		setSize(1200, (int)(1200 * 0.75));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)((dim.getWidth() - getWidth()) / 2), (int)((dim.getHeight() - getHeight()) / 2));
		
		setTitle("\u2666 \u2665 \u2660 \u2663 Pokerface \u2663 \u2660 \u2665 \u2666");
		
		setResizable(false);
		
		layout.show(this.getContentPane(), "panelProfile");
		
		//setUndecorated(true);
		this.setVisible(true);
		validate();
		
		setIconImage(ImageShop.IMAGE_APPICON);
	}
}
