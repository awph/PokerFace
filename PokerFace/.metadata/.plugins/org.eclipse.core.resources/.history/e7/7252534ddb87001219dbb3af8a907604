
package ch.hearc.coursjava.gui.j2d.image.fabrication;

import java.awt.BorderLayout;

import javax.swing.JFrame;



public class JFrameImageFabrication extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JFrameImageFabrication()
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

	private void geometrie()
		{
		panelDessin = new JPanelDessin();

		this.setLayout(new BorderLayout());

		this.add(panelDessin, BorderLayout.CENTER);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setSize(800, 600);
		setTitle("Image fabrication");
		setLocation(30, 30);
		setResizable(true);


		this.setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelDessin panelDessin;
	}

