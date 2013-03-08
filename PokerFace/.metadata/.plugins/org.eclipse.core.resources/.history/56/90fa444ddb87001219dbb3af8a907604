
package ch.hearc.coursjava.gui.layout.exemples.borderlayout;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrameBorderLayout extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameBorderLayout()
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
		layout.setHgap(30);
		layout.setVgap(50);
		setLayout(layout);

		bouton1 = new JButton("Bouton1");
		bouton2 = new JButton("Bouton2");
		bouton3 = new JButton("Bouton3");
		bouton4 = new JButton("Bouton4");
		bouton5 = new JButton("Bouton5");

		add(bouton1, BorderLayout.NORTH);
		add(bouton2, BorderLayout.SOUTH);
		add(bouton3, BorderLayout.CENTER);
		add(bouton4, BorderLayout.EAST);
		add(bouton5, BorderLayout.WEST);
		}

	private void apparence()
		{
		bouton1.setPreferredSize(new Dimension(-1, 75));
		bouton5.setPreferredSize(new Dimension(200, -1));
		apparenceFrame();
		}

	private void apparenceFrame()
		{
		setSize(300, 300);
		setTitle("Frame BorderLayout");
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
