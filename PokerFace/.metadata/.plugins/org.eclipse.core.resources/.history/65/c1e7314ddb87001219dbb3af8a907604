
package ch.hearc.coursjava.gui.layout.exercice.consolidation;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ch.hearc.coursjava.gui.layout.exercice.consolidation.atomes.JBoxControl;
import ch.hearc.coursjava.gui.layout.exercice.consolidation.atomes.JPanelEtat;
import ch.hearc.coursjava.gui.layout.exercice.consolidation.atomes.JPanelText;
import ch.hearc.coursjava.gui.layout.exercice.consolidation.atomes.JPanelTitre;



public class JPanelConsolidation extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelConsolidation()
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
		// rien
		}

	private void geometrie()
		{
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		panelEtat = new JPanelEtat();
		panelTitre = new JPanelTitre(panelEtat.getLabelEtat());
		panelText = new JPanelText();
		boxControl = new JBoxControl(panelEtat.getLabelEtat());

		add(panelTitre, BorderLayout.NORTH);
		add(panelText, BorderLayout.CENTER);
		add(boxControl, BorderLayout.WEST);
		add(panelEtat, BorderLayout.SOUTH);

		}

	private void apparence()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JPanelEtat panelEtat;
	private JPanelTitre panelTitre;
	private JPanelText panelText;
	private JBoxControl boxControl;
	}

