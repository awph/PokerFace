
package ch.hearc.pokerface.gui.gamescreen.table.board;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gui.gamescreen.table.JPanelGameArea;
import ch.hearc.pokerface.gui.gamescreen.table.JPanelGameControl;
import ch.hearc.pokerface.gui.options.JPanelTopBar;

public class JPanelGameBoard extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Content
	private JPanelGameArea		gameArea;
	private JPanelGameControl	gameControl;
	private final JPanelTopBar	panelTopBar	= JPanelTopBar.getInstance();

	//Game
	protected GameEngine		gameEngine;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelGameBoard()
	{
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
			e.printStackTrace();
		}

		gameControl = new JPanelGameControl(gameEngine);

		add(panelTopBar, BorderLayout.NORTH);
		add(gameArea, BorderLayout.CENTER);
		add(gameControl, BorderLayout.SOUTH);

		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				gameEngine.run();
			}
		});
		thread.setName("GameEngine");
		thread.start();
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
