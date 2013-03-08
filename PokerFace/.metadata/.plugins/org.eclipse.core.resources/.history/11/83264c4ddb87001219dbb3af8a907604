
package ch.hearc.coursjava.gui.j2d.traitTournant;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class JFrameTraitTournant extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameTraitTournant(OptionAnimation optionAnimation)
		{
		this.optionAnimation = optionAnimation;
		geometrie();
		controle();
		apparence();
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

	private void geometrie()
		{
		jPanelDessinTraitTournant = new JPanelDessinTraitTournant(optionAnimation);
		jPanelControle = new JPanelControle(jPanelDessinTraitTournant, optionAnimation);

		setLayout(new BorderLayout());
		this.add(jPanelDessinTraitTournant, BorderLayout.CENTER);
		this.add(jPanelControle, BorderLayout.SOUTH);

		}

	private void controle()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setLocation(30, 30);
		setSize(300, 300);
		setTitle("TraitTournant");
		setResizable(true);
		setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input
	private OptionAnimation optionAnimation;

	//Tools
	private JPanelDessinTraitTournant jPanelDessinTraitTournant;
	private JPanelControle jPanelControle;
	}
