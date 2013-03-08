
package ch.hearc.coursjava.gui.geometrieControl.framejumelles.version1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JButtonLinkedLabel extends JButton
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JButtonLinkedLabel(JLabelCounter label, int i)
		{
		this.label = label;//On récupère la référence
		this.i = i;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		this.setText("Button " + i);
		}

	private void control()
		{
		addActionListener(actionListener());
		}

	private void appearance()
		{
		this.setSize(100, 30);
		}

	private ActionListener actionListener()
		{
		return new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent event)
					{
					label.incrementDisplayCounter();
					}
			};
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Inputs
	private JLabelCounter label;
	private int i;
	}
