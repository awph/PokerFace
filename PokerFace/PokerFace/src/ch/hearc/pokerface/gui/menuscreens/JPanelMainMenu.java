
package ch.hearc.pokerface.gui.menuscreens;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.pokerface.gui.JFrameMain;


public class JPanelMainMenu extends JPanel
{

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JButton	backButton;
	private JButton	selectProfileButton;
	private JFrameMain mainFrame;

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

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
	{
		setLayout(new FlowLayout());

		backButton = new JButton("Back");
		selectProfileButton = new JButton("Select men");
	}

	private void control()
	{
		selectProfileButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event)
			{
				JPanelMainMenu.this.mainFrame.setCard("panelProfile");
			}
		});
		backButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event)
			{
				JPanelMainMenu.this.mainFrame.previousCard();
			}
		});
	}

	private void appearance()
	{
		add(backButton);
		add(selectProfileButton);
	}

}


