
package ch.hearc.pokerface.gui.menuscreens;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.pokerface.gui.JFrameMain;
import ch.hearc.pokerface.gui.options.JPanelTopBar;


public class JPanelMainMenu extends JPanel
{

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JButton	backButton;
	private JButton	playButton;
	private JFrameMain mainFrame;
	private JPanelTopBar topBarPanel;

	//Tools
	private JPanel panelCenter;
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMainMenu(JFrameMain mainFrame)
	{
		this.mainFrame = mainFrame;

		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public JPanelTopBar getTopBar()
	{
		return topBarPanel;
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
	{
		setLayout(new BorderLayout());
		panelCenter = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter,BoxLayout.X_AXIS));

		playButton = new JButton("Play");
		backButton = new JButton("Back");

		topBarPanel = new JPanelTopBar(this);
	}

	private void control()
	{
		playButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event)
			{
				JPanelMainMenu.this.mainFrame.setCard("panelGameBoard");
			}
		});
		backButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event)
			{
				JPanelMainMenu.this.mainFrame.setCard("panelProfile");
			}
		});
	}

	private void appearance()
	{
		Box insideBox = Box.createVerticalBox();
		panelCenter.add(Box.createHorizontalGlue());

		insideBox.add(playButton);
		insideBox.add(backButton);

		panelCenter.add(insideBox);
		panelCenter.add(Box.createHorizontalGlue());
		add(panelCenter, BorderLayout.CENTER);
		add(topBarPanel,BorderLayout.NORTH);
	}

	public void refreshProfile()
	{
		topBarPanel.refreshProfile();
	}


}


