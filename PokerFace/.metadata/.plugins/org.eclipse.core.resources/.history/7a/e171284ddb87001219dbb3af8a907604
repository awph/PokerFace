
package ch.hearc.coursjava.gui.layout.exercice.imbrication;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrameBoutonEmboite extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameBoutonEmboite()
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
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		boutonExterne = new JButton("BoutonExterne");
		bouton1 = new JButton("Bouton1");
		bouton2 = new JButton("Bouton2");
		bouton3 = new JButton("Bouton3");
		bouton4 = new JButton("Bouton4");

		GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setVgap(30);
		gridLayout.setHgap(100);

		boutonExterne.setLayout(gridLayout);

		add(boutonExterne, BorderLayout.CENTER);

		boutonExterne.add(bouton1);
		boutonExterne.add(bouton2);
		boutonExterne.add(bouton3);
		boutonExterne.add(bouton4);
		}

	private void apparence()
		{
		setSize(300, 300);
		setTitle("Frame Bouton emboité");
		setLocation(30, 30);
		setResizable(true);
		this.setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JButton boutonExterne;
	private JButton bouton1;
	private JButton bouton2;
	private JButton bouton3;
	private JButton bouton4;
	}
