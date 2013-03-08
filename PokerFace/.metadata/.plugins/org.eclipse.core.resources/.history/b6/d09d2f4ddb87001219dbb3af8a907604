
package ch.hearc.coursjava.gui.layout.exercice.consolidation.atomes;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import ch.hearc.coursjava.gui.layout.exercice.consolidation.JPanelAbout;
import ch.hearc.coursjava.gui.layout.exercice.consolidation.JPanelConsolidation;

public class JPanelRoot extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelRoot()
		{
		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void removeOngletAbout()
		{
		tabbedPane.remove(INDEX_ABOUT);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void apparence()
		{
		//rien
		}

	private void control()
		{
		//rien
		}

	private void geometry()
		{
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		panelConsolidation = new JPanelConsolidation();
		panelAbout = new JPanelAbout();

		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.add(panelConsolidation, 0);
		tabbedPane.add(panelAbout, INDEX_ABOUT);

		panelTitreAbout = new JPanelTitreAbout(this);

		tabbedPane.setTitleAt(0, "Home");
		tabbedPane.setTabComponentAt(1, panelTitreAbout);

		add(tabbedPane);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelConsolidation panelConsolidation;
	private JTabbedPane tabbedPane;
	private JPanelAbout panelAbout;
	private JPanelTitreAbout panelTitreAbout;

	private static final int INDEX_ABOUT = 1;

	}
