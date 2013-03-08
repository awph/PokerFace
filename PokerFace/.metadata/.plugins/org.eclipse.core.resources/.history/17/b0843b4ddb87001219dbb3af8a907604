
package ch.hearc.coursjava.gui.layout.exemples.vide;

import javax.swing.JButton;
import javax.swing.JFrame;



public class JFrameLayoutVide extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JFrameLayoutVide()
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
		setLayout(null);

		bouton = new JButton("Bouton");
		bouton.setSize(200, 20);
		bouton.setLocation(50,50);

		add(bouton);
		}

	private void apparence()
		{
		setSize(300, 300);
		setTitle("Frame layout vide");
		setLocation(30, 30);
		setResizable(true);
		this.setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JButton bouton;
	}

