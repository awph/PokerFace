
package ch.hearc.coursjava.gui.layout.exercice.consolidation.atomes;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class JBoxControl extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JBoxControl(JLabel labelEtat)
		{
		super(BoxLayout.Y_AXIS);

		this.labelEtat = labelEtat;
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

	private void apparence()
		{

		}

	private void control()
		{

		}

	private void geometrie()
		{
		bouton1 = new BoutonSmart("Bouton1",labelEtat);
		bouton2 = new BoutonSmart("Bouton2",labelEtat);

		add(bouton1);
		add(Box.createVerticalGlue());
		add(bouton2);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools

	private BoutonSmart bouton1;
	private BoutonSmart bouton2;

	//Inputs
	private JLabel labelEtat;
	}
