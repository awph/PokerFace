
package ch.hearc.pokerface.gui.gamescreen.table.board;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
		System.out.println("updateGUI");
		//TODO: here update all component in this view
		gameArea.updateGUI();
		gameControl.updateGUI();
	}

	public void refresh()
	{
		jsp.setDividerLocation(getHeight() / 3 * 2);
	}

	public void start(final GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;
		refresh();

		gameArea = new JPanelGameArea(gameEngine);
		gameControl = new JPanelGameControl(gameEngine);

		jsp.add(gameArea);
		jsp.add(gameControl);

		jsp.setDividerSize(0);
		add(jsp, BorderLayout.CENTER);
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

		jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		//TODO : add Top Bar to Jsp
		setLayout(new BorderLayout());
	}

	private void control()
	{
		addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent arg0)
			{
				refresh();
			}
		});
	}

	private void appearance()
	{
	}
}
