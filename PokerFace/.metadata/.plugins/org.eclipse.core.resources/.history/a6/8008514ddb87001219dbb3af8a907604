
package ch.hearc.coursjava.gui.j2d.salleconference;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JPanelControl extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelControl(JPanelSalleConference salleConference)
		{
		this.salleConference = salleConference;

		geometrie();
		control();
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
		boutonStart = new JButton("Start");
		boutonStop = new JButton("Stop");

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.add(boutonStart);
		this.add(boutonStop);

		}

	private void control()
		{
		this.boutonStart.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent event)
					{
					switchStartStopEnable();
					salleConference.startAnimation();
					}
			});

		this.boutonStop.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent event)
					{
					switchStartStopEnable();
					salleConference.stopAnimation();
					}
			});
		}

	private void apparence()
		{
		boutonStart.setEnabled(true);
		boutonStop.setEnabled(false);
		}

	private void switchStartStopEnable()
		{
		this.boutonStart.setEnabled(!this.boutonStart.isEnabled());
		this.boutonStop.setEnabled(!this.boutonStop.isEnabled());
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private JPanelSalleConference salleConference;

	// Tools
	private JButton boutonStart;
	private JButton boutonStop;
	}
