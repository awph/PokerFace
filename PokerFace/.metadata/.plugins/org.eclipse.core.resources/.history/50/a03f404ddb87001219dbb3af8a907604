
package ch.hearc.coursjava.gui.layout.exemples.flowlayout;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrameFlowLayout extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameFlowLayout()
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
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		layout.setHgap(30);
		layout.setVgap(50);
		setLayout(layout);

		bouton1 = new JButton("Bouton1");
		bouton2 = new JButton("Bouton2");
		bouton3 = new JButton("Bouton3");
		bouton4 = new JButton("Bouton4");
		bouton5 = new JButton("Bouton5");

		add(bouton1);
		add(bouton2);
		add(bouton3);
		add(bouton4);
		add(bouton5);
		}

	private void apparence()
		{
		bouton1.setPreferredSize(new Dimension(200,200));
		apparenceFrame();
		}

	private void apparenceFrame()
		{
		setSize(300, 300);
		setTitle("Frame FlowLayout");
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
