
package ch.hearc.coursjava.gui.layout.exercice.boutoncentre;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;



public class JFrameBoutonCentre extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameBoutonCentre()
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
		setLayout(new BorderLayout());

		Box boxV = Box.createVerticalBox();
		Box boxH = Box.createHorizontalBox();

		bouton = new JButton("Bouton");

		boxV.add(Box.createVerticalGlue());
		boxV.add(bouton);
		boxV.add(Box.createVerticalGlue());

		boxH.add(Box.createHorizontalGlue());
		boxH.add(boxV);
		boxH.add(Box.createHorizontalGlue());

		add(boxH, BorderLayout.CENTER);
		}

	private void apparence()
		{
		setSize(300, 300);
		setTitle("Frame BoutonCentre");
		setLocation(30, 30);
		setResizable(true);
		this.setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	JButton bouton;

	}

