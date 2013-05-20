
package ch.hearc.pokerface.gui.options;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import ch.hearc.pokerface.gameengine.player.profile.Profile;

public final class JPanelTopBar extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private static volatile JPanelTopBar	instance	= null;

	// Inputs / Outputs
	private JLabel							testLabel;
	private JLabel							clockLabel;
	private SimpleDateFormat ft;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*
	 * @return The only instance of the class
	 */
	public final static JPanelTopBar getInstance()
	{
		if (JPanelTopBar.instance == null)
		{
			synchronized (JPanelTopBar.class)
			{
				if (JPanelTopBar.instance == null)
				{
					JPanelTopBar.instance = new JPanelTopBar();
				}
			}
		}
		return JPanelTopBar.instance;
	}

	private JPanelTopBar()
	{

		setLayout(new BorderLayout());
		testLabel = new JLabel();
		clockLabel = new JLabel();

		add(testLabel, BorderLayout.WEST);
		add(clockLabel, BorderLayout.EAST);

		ft = new SimpleDateFormat("HH:mm:ss");

		Timer t = new Timer(1000, updateClockAction);
		t.start();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refreshProfile(Profile profile)
	{
		testLabel.setText(profile.getName());
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private ActionListener updateClockAction = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  clockLabel.setText(ft.format(new Date()));
		    }
		};
}
