
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;

public class JPanelGameControl extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JSlider		moneySlider;
	private JButton		allinButton;
	private JButton		betRaiseButton;
	private JButton		checkCallButton;
	private JButton		foldButton;

	private GameEngine	gameEngine;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelGameControl(GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;

		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void updateGUI()
	{
		Player humanPlayer = gameEngine.getPlayers().get(GameEngine.HUMAN_PLAYER_INDEX);
		moneySlider.setMaximum(humanPlayer.getBankroll());
		moneySlider.setMinimum(humanPlayer.getCallValue());

		boolean isHumanPlayerTurn = false;

		isHumanPlayerTurn = (humanPlayer == gameEngine.getCurrentPlayer() && !humanPlayer.isFolded());

		betRaiseButton.setEnabled(isHumanPlayerTurn);
		checkCallButton.setEnabled(isHumanPlayerTurn);
		foldButton.setEnabled(isHumanPlayerTurn);
		allinButton.setEnabled(isHumanPlayerTurn);
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

	private void geometry()
	{
		Box box = Box.createVerticalBox();
		allinButton = new JButton("All in");
		moneySlider = new JSlider(SwingConstants.HORIZONTAL);
		betRaiseButton = new JButton("Bet/Raise");
		checkCallButton = new JButton("Check/Call");
		foldButton = new JButton("Fold");

		box.add(allinButton);
		box.add(moneySlider);
		box.add(betRaiseButton);
		box.add(checkCallButton);
		box.add(foldButton);
		add(box);
	}

	private void control()
	{
		allinButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				gameEngine.getPlayers().get(GameEngine.HUMAN_PLAYER_INDEX).allIn();
				notify();
			}
		});

		betRaiseButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Player humanPlayer = gameEngine.getPlayers().get(GameEngine.HUMAN_PLAYER_INDEX);
				if (humanPlayer.getCallValue() == 0)
				{
					humanPlayer.bet(moneySlider.getValue());
				}
				else
				{
					humanPlayer.raise(moneySlider.getValue());
				}
				notify();
			}
		});

		checkCallButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Player humanPlayer = gameEngine.getPlayers().get(GameEngine.HUMAN_PLAYER_INDEX);
				if (humanPlayer.getCallValue() == 0)
				{
					humanPlayer.check();
				}
				else
				{
					humanPlayer.call();
				}
				notify();
			}
		});

		foldButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				gameEngine.getPlayers().get(GameEngine.HUMAN_PLAYER_INDEX).fold();
				notify();
			}
		});
	}

	private void appearance()
	{

	}
}
