
package ch.hearc.coursjava.gui.geometrieControl.framejumelles.version1;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JLabelCounter extends JLabel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JLabelCounter()
		{
		nbClick = -1;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void incrementDisplayCounter()
		{
		setText(++nbClick + "");
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		incrementDisplayCounter();
		}

	private void control()
		{
		//Rien
		}

	private void appearance()
		{
		setOpaque(true);
		setSize(100, 30);
		setBackground(Color.GRAY);
		setHorizontalAlignment(SwingConstants.CENTER);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private int nbClick;
	}
