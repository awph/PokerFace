
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
		Player humanPlayer = GameEngine.HUMAN_PLAYER;
		moneySlider.setMaximum(humanPlayer.getBankroll());
		moneySlider.setMinimum(humanPlayer.getCallValue());

		boolean isHumanPlayerTurn = false;
		if (!gameEngine.getIsFinished())
		{
			isHumanPlayerTurn = (humanPlayer == gameEngine.getCurrentPlayer());
		}
		else
		{
			if(!humanPlayer.isDead())
			{
				JOptionPane.showMessageDialog(null, "Motherfucker you're soooooooooooooooo good !", "YEAH", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Fuck you off little pussy ! You're too bad, fucking noooooooob", "LOOOOOOOOOSER", JOptionPane.INFORMATION_MESSAGE);
			}
		}
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
				Player humanPlayer = GameEngine.HUMAN_PLAYER;
				synchronized (humanPlayer)
				{
					humanPlayer.allIn();
					humanPlayer.notify();
				}
			}
		});

		betRaiseButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Player humanPlayer = GameEngine.HUMAN_PLAYER;
				synchronized (humanPlayer)
				{
					//TODO : Slider
					if (gameEngine.getPot().getBet() == 0)
					{
						humanPlayer.bet(gameEngine.getBigBlind());
					}
					else
					{
						humanPlayer.raise(humanPlayer.getBetSpending() + gameEngine.getBigBlind());
					}
					humanPlayer.notify();
				}
			}
		});

		checkCallButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Player humanPlayer = GameEngine.HUMAN_PLAYER;
				synchronized (humanPlayer)
				{
					humanPlayer.check();
					humanPlayer.notify();
				}
			}
		});

		foldButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Player humanPlayer = GameEngine.HUMAN_PLAYER;
				synchronized (humanPlayer)
				{
					humanPlayer.fold();
					humanPlayer.notify();
				}
			}
		});
	}

	private void appearance()
	{

	}
}
