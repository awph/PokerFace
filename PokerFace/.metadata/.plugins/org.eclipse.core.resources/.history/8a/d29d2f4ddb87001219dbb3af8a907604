
package ch.hearc.coursjava.gui.layout.exercice.consolidation.atomes;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelTitreAbout extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelTitreAbout(JPanelRoot panelRoot)
		{
		this.panelRoot = panelRoot;

		geometry();
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
		// rien
		}

	private void control()
		{
		labelControl.addMouseListener(new MouseAdapter()
			{

				@Override
				public void mouseClicked(MouseEvent event)
					{
					System.out.println("[JPanelTitreAbout] : x clicked");
					panelRoot.removeOngletAbout();
					}
			});
		}

	private void geometry()
		{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		labelControl = new JLabel("x");
		labelText = new JLabel("About");
		add(labelText);
		add(labelControl);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JPanelRoot panelRoot;

	// Tools
	private JLabel labelText;
	private JLabel labelControl;
	}
