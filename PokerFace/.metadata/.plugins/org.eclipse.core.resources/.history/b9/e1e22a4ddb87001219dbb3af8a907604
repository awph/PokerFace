
package ch.hearc.coursjava.gui.layout.exercice.consolidation.atomes;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelTitre extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelTitre(JLabel labelEtat)
		{
		this.labelEtat = labelEtat;

		geometie();
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

	private void geometie()
		{
		bouton1 = new BoutonSmart("Bouton1",labelEtat);
		bouton2 = new BoutonSmart("Bouton2",labelEtat);

		Box boxH = Box.createHorizontalBox();
		boxH.add(Box.createHorizontalGlue());
		boxH.add(bouton1);
		boxH.add(Box.createHorizontalStrut(10));
		boxH.add(bouton2);
		boxH.add(Box.createHorizontalGlue());

		setLayout(new BorderLayout());
		add(boxH, BorderLayout.CENTER);
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
