
package ch.hearc.coursjava.gui.geometrieControl.boutonlabellink;

import javax.swing.JLabel;



public class GenerateFrames
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public GenerateFrames(int nbLinks)
		{
		this.nbLinks = nbLinks;
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void createFrames()
		{
		boutonFrame = new JFrameListElements();
		labelFrame = new JFrameListElements();

		for(int i = 1; i <= nbLinks; i++)
			{
			JLabel label = new JLabel("0");
			BoutonLabel bouton = new BoutonLabel(label, "Bouton " + i);

			boutonFrame.add(bouton);
			labelFrame.add(label);
			}

		labelFrame.setLocation(240,0);

		boutonFrame.setVisible(true);
		labelFrame.setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Inputs
	private int nbLinks;

	//Tools
	private JFrameListElements boutonFrame;
	private JFrameListElements labelFrame;
	}

