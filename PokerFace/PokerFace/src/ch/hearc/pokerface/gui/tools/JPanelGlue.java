
package ch.hearc.pokerface.gui.tools;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class JPanelGlue extends JPanel
{

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//private int axis;
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelGlue(int axis)
	{
		setOpaque(false);
		switch(axis)
		{
			case BoxLayout.X_AXIS:
				add(Box.createHorizontalGlue());
				break;
			case BoxLayout.Y_AXIS:
				add(Box.createVerticalGlue());
				break;
		}
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

}

