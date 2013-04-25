
package ch.hearc.pokerface.gui.gamescreen.table.board;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ch.hearc.pokerface.gui.JFrameMain;
import ch.hearc.pokerface.gui.gamescreen.table.JPanelGameArea;
import ch.hearc.pokerface.gui.gamescreen.table.JPanelGameControl;


public class JPanelGameBoard extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Inputs
	private JFrameMain frameMain;

	//Content
	private JPanelGameArea gameArea;
	private JPanelGameControl gameControl;

	//Tools
	private JSplitPane jsp;

	//Game
	private int smallBlind = 5;
	private int bigBling = 10;
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

	public void refresh()
	{
		jsp.setDividerLocation(getHeight()/3 * 2);
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
		GameInformation gameInformation = new GameInformation();

		int nbPlayers = 5;

		gameArea = new JPanelGameArea(gameInformation, nbPlayers);
		gameControl = new JPanelGameControl(gameInformation);

		jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,gameArea,gameControl);
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
		jsp.setDividerSize(0);
		add(jsp, BorderLayout.CENTER);
	}
}

