
package ch.hearc.coursjava.gui.animation.component;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



public class JPanelControle extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelControle(StartStop_I startStop)
		{
		this.startStop = startStop;

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

	private void switchEtatsBoutons()
		{
		boutonStart.setEnabled(!boutonStart.isEnabled());
		boutonStop.setEnabled(!boutonStop.isEnabled());
		}

	private void control()
		{
		boutonStart.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent event)
					{
					System.out.println("[JPanelControle] : start");
					switchEtatsBoutons();
					startStop.start();
					}
			});

		boutonStop.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent event)
					{
					System.out.println("[JPanelControle] : stop");
					switchEtatsBoutons();
					startStop.stop();
					}
			});
		}

	private void geometrie()
		{
		boutonStart = new JButton("Start");
		boutonStop = new JButton("Stop");

		setLayout(new FlowLayout());
		add(boutonStart);
		add(boutonStop);

		}

	private void apparence()
		{
		boutonStart.setEnabled(true);
		boutonStop.setEnabled(false);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JButton boutonStart;
	private JButton boutonStop;

	// Inputs
	private StartStop_I startStop;
	}

