
package ch.hearc.coursjava.gui.geometrieControl.framejumelles.version1;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class JFrameBL extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameBL(JComponent[] tabComponent, int x, int y)
		{
		this.tabComponent = tabComponent;

		geometry();
		control();
		appearance(x, y);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int cols = width / 2 / tabComponent[0].getSize().width;
		int rows = (int)Math.ceil(tabComponent.length / cols);

		setLayout(new GridLayout(cols, rows));

		for(Component component:tabComponent)
			{
			add(component);
			}
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance(int x, int y)
		{
		setTitle("Button linked Label");
		setSize(200, 600);
		setLocation(x, y);
		setResizable(true);
		pack();
		setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Inputs
	private JComponent[] tabComponent;

	}
