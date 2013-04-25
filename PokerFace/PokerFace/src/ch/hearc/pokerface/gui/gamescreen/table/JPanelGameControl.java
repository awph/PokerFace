
package ch.hearc.pokerface.gui.gamescreen.table;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import ch.hearc.pokerface.gui.gamescreen.table.board.GameInformation;

public class JPanelGameControl extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JButton allinButton;
	private JSlider moneySlider;
	private JButton betRaiseButton;
	private JButton checkCallButton;
	private JButton foldButton;

	private GameInformation gameInformation;
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelGameControl(GameInformation gameInformation)
	{

		this.gameInformation = gameInformation;

		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	private void geometry()
	{
		Box box = Box.createVerticalBox();
		allinButton = new JButton("All in");
		moneySlider = new JSlider(SwingConstants.HORIZONTAL);
		betRaiseButton = new JButton("Bet/Raise");
		checkCallButton = new JButton("Check/Call");
		foldButton = new JButton("Fold");

		allinButton.setEnabled(false);

		moneySlider.setMaximum(50);
		moneySlider.setMinimum(1);

		box.add(allinButton);
		box.add(moneySlider);
		box.add(betRaiseButton);
		box.add(checkCallButton);
		box.add(foldButton);
		add(box);
	}


	private void control()
	{
		// TODO Auto-generated method stub

	}

	private void appearance()
	{


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

}
