
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ImageTools;

public class JPanelGameControl extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JSpinner	moneyTextArea;
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

		if (humanPlayer.getCallValue() == 0)
		{
			checkCallButton.setText("Check");
		}
		else
		{
			checkCallButton.setText("Call " + humanPlayer.getCallValue() + "$");
		}
		boolean isHumanPlayerTurn = false;
		if (!gameEngine.getIsFinished())
		{
			isHumanPlayerTurn = (humanPlayer == gameEngine.getCurrentPlayer());

			if (isHumanPlayerTurn)
			{
				moneySlider.setMaximum(humanPlayer.getBankroll());
				moneySlider.setMinimum(humanPlayer.getCallValue());
			}
		}
		else
		{
			if (humanPlayer.isDead())
			{
				JOptionPane.showMessageDialog(null, "Sorry buddy, you didn't get so much luck !", "You lose !", JOptionPane.INFORMATION_MESSAGE);

			}
			else
			{
				JOptionPane.showMessageDialog(null, "Woaw guy, you mastered the entire game !", "You win !", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		betRaiseButton.setEnabled(isHumanPlayerTurn);
		checkCallButton.setEnabled(isHumanPlayerTurn);
		foldButton.setEnabled(isHumanPlayerTurn);
		allinButton.setEnabled(isHumanPlayerTurn);
		moneySlider.setEnabled(isHumanPlayerTurn);
		moneyTextArea.setEnabled(isHumanPlayerTurn);
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
		moneyTextArea = new JSpinner();

		// makes spinner update imediately
		JComponent comp = moneyTextArea.getEditor();
		JFormattedTextField field = (JFormattedTextField)comp.getComponent(0);
		DefaultFormatter formatter = (DefaultFormatter)field.getFormatter();
		formatter.setCommitsOnValidEdit(true);

		moneyTextArea.setMaximumSize(moneyTextArea.getMinimumSize());

		allinButton.setMargin(new Insets(10, 10, 10, 10));

		Box boxAll = Box.createHorizontalBox();
		boxAll.add(moneyTextArea);
		boxAll.add(Box.createHorizontalGlue());
		boxAll.add(allinButton);

		box.add(boxAll);
		box.add(Box.createVerticalStrut(5));
		box.add(moneySlider);
		box.add(Box.createVerticalStrut(5));
		box.add(betRaiseButton);
		box.add(Box.createVerticalStrut(5));
		box.add(checkCallButton);
		box.add(Box.createVerticalStrut(5));
		box.add(foldButton);
		add(box);
	}

	private void control()
	{

		moneyTextArea.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e)
			{
				if ((int)moneyTextArea.getValue() <= moneySlider.getMaximum() && (int)moneyTextArea.getValue() > moneySlider.getMinimum())
				{
					moneySlider.setValue((int)moneyTextArea.getValue());
				}
				else
				{
					moneyTextArea.setValue(moneySlider.getValue());
				}
			}
		});

		moneySlider.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				moneyTextArea.setValue(moneySlider.getValue());
				if (gameEngine.getPot().getBet() == 0)
				{
					betRaiseButton.setText("Bet " + moneySlider.getValue() + "$");
				}
				else if (moneySlider.getMaximum() == moneySlider.getValue())
				{
					betRaiseButton.setText("All-in with " + moneySlider.getValue() + "$");
				}
				else
				{
					betRaiseButton.setText("Raise " + moneySlider.getValue() + "$");
				}
			}
		});

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
					if (gameEngine.getPot().getBet() == 0)
					{
						//humanPlayer.bet(gameEngine.getBigBlind());
						humanPlayer.bet(moneySlider.getValue());
					}
					else
					{
						//humanPlayer.raise(humanPlayer.getBetSpending() + gameEngine.getBigBlind());
						humanPlayer.raise(humanPlayer.getBetSpending() + moneySlider.getValue());
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
		setBackground(Color.BLACK);
		styleButton(checkCallButton);
		styleButton(foldButton);
		styleButton(betRaiseButton);
		styleButton(allinButton);
	}

	private void styleButton(final JButton button)
	{
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setContentAreaFilled(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setForeground(Color.WHITE);
		try
		{
			button.setFont(ButtonTools.getButtonFont());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		button.setIcon(ImageTools.loadScaledIcon("resources/buttons/pink_button_normal.png", 0.3, false));
		button.addMouseListener(new java.awt.event.MouseAdapter()
		{
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt)
			{
				button.setIcon(ImageTools.loadScaledIcon("resources/buttons/pink_button_selected.png", 0.3, false));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt)
			{
				button.setIcon(ImageTools.loadScaledIcon("resources/buttons/pink_button_normal.png", 0.3, false));

			}
		});
	}
}
