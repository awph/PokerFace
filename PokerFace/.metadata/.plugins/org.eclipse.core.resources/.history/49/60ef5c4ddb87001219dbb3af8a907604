
package ch.hearc.coursjava.gui.j2d.degradeHSB;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class JFrameDegradeHSB extends JFrame
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JFrameDegradeHSB()
		{
		geometrie();
		controle();
		apparence();
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
	
	private void geometrie()
		{
		jPanelDegradeHSB = new JPanelDegradeHSB();
		
		setLayout(new BorderLayout());
		this.add(jPanelDegradeHSB, BorderLayout.CENTER);
		}
	
	private void controle()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	
	private void apparence()
		{
		setLocation(30, 30);
		setSize(300, 300);
		setTitle("Degrade HSB");
		setResizable(true);
		setVisible(true);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	//tools
	private JPanelDegradeHSB jPanelDegradeHSB;
	}
