
package ch.hearc.coursjava.gui.geometrieControl.framejumelles.version2;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ControleurManager
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public ControleurManager(JFrameBoutons frameBoutons, JFrameLabel frameLabels)
		{
		this.frameBoutons = frameBoutons;
		this.frameLabels = frameLabels;

		work();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void work()
		{
		int n = frameBoutons.getN();
		tabControleurs = new Controleur[n];
		for(int i = 1; i <= n; i++)
			{
			JButton boutonI = frameBoutons.getComposant(i);
			JLabel labelI = frameLabels.getComposant(i);

			tabControleurs[i - 1] = new Controleur(boutonI, labelI);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private JFrameBoutons frameBoutons;
	private JFrameLabel frameLabels;

	// Tools
	private Controleur[] tabControleurs;
	}
