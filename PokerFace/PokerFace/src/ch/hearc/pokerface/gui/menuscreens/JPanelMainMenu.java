
package ch.hearc.pokerface.gui.menuscreens;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.pokerface.gui.JFrameMain;
import ch.hearc.pokerface.gui.options.JPanelTopBar;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ImagePanel;

public class JPanelMainMenu extends ImagePanel
{

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JButton			backButton;
	private JButton			playButton;
	private JFrameMain		mainFrame;
	private JPanelTopBar	topBarPanel = JPanelTopBar.getInstance();

	//Tools
	private JPanel			panelCenter;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMainMenu(JFrameMain mainFrame) throws Exception
	{
		super(ImageIO.read(new File("resources/background.jpg")));
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
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.X_AXIS));

		playButton = new JButton("PLAY");
		backButton = new JButton("BACK");

		ButtonTools.setStyleToButton(playButton, "blue");
		ButtonTools.setStyleToButton(backButton, "blue");

		panelCenter.setOpaque(false);
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

		Box playBox = Box.createHorizontalBox();
		Box backBox = Box.createHorizontalBox();

		playBox.add(Box.createHorizontalGlue());
		playBox.add(playButton);
		playBox.add(Box.createHorizontalGlue());

		backBox.add(Box.createHorizontalGlue());
		backBox.add(backButton);
		backBox.add(Box.createHorizontalGlue());

		insideBox.add(playBox);
		insideBox.add(backBox);

		panelCenter.add(insideBox);
		panelCenter.add(Box.createHorizontalGlue());

		add(panelCenter, BorderLayout.CENTER);
		add(topBarPanel, BorderLayout.NORTH);
	}

}
