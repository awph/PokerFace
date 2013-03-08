
package ch.hearc.coursjava.gui.layout.exercice.consolidation;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ch.hearc.coursjava.gui.layout.exercice.consolidation.atomes.JPanelRoot;

public class JFrameConsolidation extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameConsolidation()
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void geometrie()
		{
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		panelRoot = new JPanelRoot();

		add(panelRoot);
		}

	private void apparence()
		{
		setSize(300, 300);
		setTitle("Frame Tabbed pane");
		setLocation(30, 30);
		setResizable(true);
		this.setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelRoot panelRoot;
	}
