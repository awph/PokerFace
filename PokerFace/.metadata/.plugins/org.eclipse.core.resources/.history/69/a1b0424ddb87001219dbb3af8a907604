
package ch.hearc.coursjava.gui.layout.exemples.boxlayout;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrameBoxLayout extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameBoxLayout()
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

		bouton1 = new JButton("Bouton1");
		bouton2 = new JButton("Bouton2");
		bouton3 = new JButton("Bouton3");
		bouton4 = new JButton("Bouton4");
		bouton5 = new JButton("Bouton5");

		add(boxV, BorderLayout.CENTER);

		boxV.add(bouton1);
		boxV.add(Box.createVerticalStrut(10));
		boxV.add(bouton2);
		boxV.add(bouton3);
		boxV.add(bouton4);
		boxV.add(Box.createVerticalGlue());
		boxV.add(bouton5);
		}

	private void apparence()
		{
		setSize(300, 300);
		setTitle("Frame BoxLayout");
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
	private JButton bouton4;
	private JButton bouton5;
	}
