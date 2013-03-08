
package ch.hearc.coursjava.gui.geometrieControl.framejumelles.version1;

import static org.junit.Assert.assertTrue;

import java.awt.Toolkit;

public class Starter
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Starter(int nbButton)
		{
		assertTrue(nbButton > 0);

		this.nbButton = nbButton;
		tabButton = new JButtonLinkedLabel[nbButton];
		tabLabel = new JLabelCounter[nbButton];

		for(int i = 0; i < nbButton; i++)
			{
			tabLabel[i] = new JLabelCounter();
			tabButton[i] = new JButtonLinkedLabel(tabLabel[i], i + 1);
			}

		frameButton = new JFrameBL(tabButton, 0, 0);
		frameLabel = new JFrameBL(tabLabel, Toolkit.getDefaultToolkit().getScreenSize().width / 2, 0);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Inputs
	private int nbButton;

	//Tools
	private JButtonLinkedLabel[] tabButton;
	private JLabelCounter[] tabLabel;
	private JFrameBL frameButton;
	private JFrameBL frameLabel;
	}
