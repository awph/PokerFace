
package ch.hearc.coursjava.gui.animation.component;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class JFrameBoutonTournant extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameBoutonTournant()
		{
		geometrie();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void geometrie()
		{
		boutonTournant = new JPanelBoutonTournant();

		setLayout(new BorderLayout());
		add(boutonTournant, BorderLayout.CENTER);
		}

	private void apparence()
		{
		setSize(300, 300);
		setTitle("Frame BoutonTournant");
		setLocation(30, 30);
		setResizable(true);
		this.setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelBoutonTournant boutonTournant;

	}
