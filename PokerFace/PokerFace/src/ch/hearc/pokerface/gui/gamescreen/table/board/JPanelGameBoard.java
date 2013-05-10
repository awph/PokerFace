
package ch.hearc.pokerface.gui.gamescreen.table.board;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gui.JFrameMain;
import ch.hearc.pokerface.gui.gamescreen.table.JPanelGameArea;
import ch.hearc.pokerface.gui.gamescreen.table.JPanelGameControl;

public class JPanelGameBoard extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Inputs
	private JFrameMain			frameMain;

	//Content
	private JPanelGameArea		gameArea;
	private JPanelGameControl	gameControl;

	//Tools
	private JSplitPane			jsp;

	//Game
	protected GameEngine		gameEngine;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelGameBoard(JFrameMain frameMain)
	{

		this.frameMain = frameMain;
		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void updateGUI()
	{

		//TODO: here update all component in this view
		gameArea.updateGUI();
		gameControl.updateGUI();
	}

	public void start(final GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;

		try
		{
			gameArea = new JPanelGameArea(gameEngine);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gameControl = new JPanelGameControl(gameEngine);

		add(gameArea, BorderLayout.CENTER);
		add(gameControl, BorderLayout.SOUTH);
		Thread t = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				gameEngine.run();
			}
		});
		t.start();
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

		setLayout(new BorderLayout());
	}

	private void control()
	{

	}

	private void appearance()
	{
	}
}
