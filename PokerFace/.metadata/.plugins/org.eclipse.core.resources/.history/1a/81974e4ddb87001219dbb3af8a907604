
package ch.hearc.coursjava.gui.j2d.salleconference;

import java.awt.BorderLayout;

import javax.swing.JFrame;




public class JFrameSalleConference extends JFrame
	{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameSalleConference()
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
		salleConference = new JPanelSalleConference();
		control = new JPanelControl(salleConference);

		this.setLayout(new BorderLayout());
		this.add(salleConference, BorderLayout.CENTER);
		this.add(control, BorderLayout.SOUTH);
		}

	private void apparence()
		{
		setSize(800, 600);
		setTitle("Salle Conference");
		setLocation(30, 30);
		setResizable(true);
		this.setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JPanelSalleConference salleConference;
	private JPanelControl control;
	}

