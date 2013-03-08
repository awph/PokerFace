
package ch.hearc.coursjava.gui.geometrieControl.framejumelles.version2;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

public abstract class JFrameJumelles_A extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JFrameJumelles_A(int n)
		{
		this.n = n;
		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Abstract						*|
	\*------------------------------------------------------------------*/

	protected abstract JComponent createComponent(int i);

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public JComponent getComposant(int i)
		{
		return tabComponents[i - 1];
		}

	public int getN()
		{
		return this.n;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void apparence()
		{
		setSize(200, 600);
		setResizable(true);
		setVisible(true);
		}

	private void control()
		{
		// rien
		}

	private void geometry()
		{
		tabComponents = new JComponent[n];
		setLayout(new FlowLayout(FlowLayout.CENTER));
		for(int i = 1; i <= n; i++)
			{
			tabComponents[i - 1] = createComponent(i);
			add(tabComponents[i - 1]);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private int n;

	// Tools
	private JComponent[] tabComponents;
	}
