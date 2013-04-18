
package ch.hearc.pokerface.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.gamescreen.table.board.JPanelGameBoard;
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
		//createSplashScreen(); TODO il faut introduire un fichier manifest pour lancer le splash screen

		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setCard(String card)
	{
		layout.show(this.getContentPane(), card);
	}

	public void previousCard()
	{
		layout.previous(this.getContentPane());
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
		panelProfile = new JPanelProfile(this);
		panelGameBoard = new JPanelGameBoard(this);
		panelMainMenu = new JPanelMainMenu(this);

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
				try
				{
					List<Profile> list = new ArrayList<Profile>();
					list.add(new Profile("prout", 1));
					list.add(new Profile("prout", 2));
					list.add(new Profile("prout", 3));
					list.add(new Profile("prout", 4));
					FileOutputStream fileOut = new FileOutputStream("D:\\profiles.dat");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(list);
					out.close();
					fileOut.close();
				}
				catch (IOException i)
				{
					i.printStackTrace();
				}
			}
		});
	}

	private void appearance()
	{
		setSize(800, 600);
		setTitle("Po-po-po-pokerface po-po-pokerface");
		setLocation(30, 30);
		setResizable(true);
		getContentPane().setBackground(Color.GREEN);
		layout.show(this.getContentPane(), "panelProfile");
		setBackground(Color.GREEN);
		this.setVisible(true);
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
