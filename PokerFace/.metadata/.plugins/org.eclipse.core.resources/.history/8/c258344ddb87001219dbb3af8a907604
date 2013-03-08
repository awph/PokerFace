
package ch.hearc.coursjava.gui.layout.exercice.boxglue;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrameBoxGlue extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameBoxGlue()
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

		bouton1 = new JButton("Bouton1");
		bouton2 = new JButton("Bouton2");
		bouton3 = new JButton("Bouton3");

		Box boxV = Box.createVerticalBox();
		add(boxV, BorderLayout.CENTER);

		boxV.add(bouton1);

		boxV.add(Box.createVerticalGlue());
		boxV.add(bouton2);
		boxV.add(Box.createVerticalGlue());
		boxV.add(Box.createVerticalGlue());
		boxV.add(Box.createVerticalGlue());

		boxV.add(bouton3);
		}

	private void apparence()
		{
		setSize(300, 300);
		setTitle("Frame BoxGlue");
		setLocation(30, 30);
		setResizable(true);
		this.setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JButton bouton1;
	private JButton bouton2;
	private JButton bouton3;
	}
