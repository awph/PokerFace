
package ch.hearc.pokerface.gui;

import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ch.hearc.pokerface.gui.gamescreen.table.board.JPanelGameBoard;
import ch.hearc.pokerface.gui.menuscreens.JOPTableConfiguration;
import ch.hearc.pokerface.gui.menuscreens.JPanelMainMenu;
import ch.hearc.pokerface.gui.menuscreens.JPanelProfile;

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
		//createSplashScreen(); //TODO il faut introduire un fichier manifest pour lancer le splash screen

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
			if (!tableConfig.switchToGame())
			{
				return; // Abort the process of switching to gameBoard
			}

			setFullscreen();
		}
		layout.show(this.getContentPane(), card);
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

	private void setFullscreen()
	{
		setResizable(false);
				/*setVisible(false);
		dispose();
		//setUndecorated(true); // TODO Disable for test purposes
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
		setVisible(true);*/
	}

	private void geometry()
	{
		setExtendedState(Frame.MAXIMIZED_BOTH);

		setUndecorated(true);
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
			// TODO Auto-generated catch block
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent arg0)
			{
				// SERIALIZATION DES PROFILES

				// A mettre dans une methode private void serializeProfiles()
				/*try
				{
					List<Profile> list = new ArrayList<Profile>();
					FileOutputStream fileOut = new FileOutputStream("D:\\profiles.dat");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(list);
					out.close();
					fileOut.close();
				}
				catch (IOException i)
				{
					i.printStackTrace();
				}*/
			}
		});
	}

	private void appearance()
	{

		setSize(1200, (int)(1200 * 0.75));


		setTitle("\u2666 \u2665 \u2660 \u2663 Pokerface \u2663 \u2660 \u2665 \u2666");

		setResizable(false);

		layout.show(this.getContentPane(), "panelProfile");

		//setUndecorated(true);
		this.setVisible(true);
		validate();
	}

	private void createSplashScreen()
	{
		final SplashScreen splash = SplashScreen.getSplashScreen();
		if (splash == null)
		{
			System.out.println("SplashScreen.getSplashScreen() returned null");
			return;
		}
		Graphics2D g = splash.createGraphics();
		if (g == null)
		{
			System.out.println("g is null");
			return;
		}
		for(int i = 0; i < 100; i++)
		{
			renderSplashFrame(g, i);
			splash.update();
			try
			{
				Thread.sleep(90);
			}
			catch (InterruptedException e)
			{
			}
		}
		splash.close();
	}

	private void renderSplashFrame(Graphics2D g, int i)
	{
		g.drawString("Prout" + i, 120, 150);
	}

}
