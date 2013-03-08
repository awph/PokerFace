
package ch.hearc.coursjava.gui.j2d.hello.bouton;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class JFrameHelloBouton extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameHelloBouton()
		{
		geometrie();
		controle();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometrie()
		{
		jBoutonDessin = new JBoutonDessin();

		this.add(jBoutonDessin);

		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		}

	private void controle()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setSize(500, 500);
		setLocation(40, 40);
		setTitle("bouton avec j2d");
		setResizable(true);
		setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//tools
	private JBoutonDessin jBoutonDessin;
	}
