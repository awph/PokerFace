
package ch.hearc.coursjava.gui.animation.component;

import java.awt.BorderLayout;

import javax.swing.JPanel;



public class JPanelBoutonTournant extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelBoutonTournant()
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
		// rien
		}

	private void geometrie()
		{
		panelAnimation = new JPanelAnimation();
		panelControle = new JPanelControle(panelAnimation);

		setLayout(new BorderLayout());
		add(panelAnimation, BorderLayout.CENTER);
		add(panelControle, BorderLayout.SOUTH);
		}

	private void apparence()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelAnimation panelAnimation;
	private JPanelControle panelControle;
	}

