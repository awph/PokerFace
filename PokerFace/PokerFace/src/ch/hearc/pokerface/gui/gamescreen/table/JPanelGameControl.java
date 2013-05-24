
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.JPanelGlue;

public class JPanelGameControl extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JSpinner	moneySpinner;
	private JSlider		moneySlider;
	private JButton		allinButton;
	private JButton		betRaiseButton;
	private JButton		checkCallButton;
	private JButton		foldButton;

	private JTextArea	loggerTextArea;

	//IO
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
				String winnerName = null;
				for(Player ia:gameEngine.getPlayers())
				{
					if (ia.getHasWon())
					{
						winnerName = ia.getProfile().getName();
					}
				}
				JOptionPane.showMessageDialog(null, winnerName + " won !", "You lose !", JOptionPane.INFORMATION_MESSAGE);

			}
			else
			{
				JOptionPane.showMessageDialog(null, "You win this game !", "You win !", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		betRaiseButton.setEnabled(isHumanPlayerTurn);
		checkCallButton.setEnabled(isHumanPlayerTurn);
		foldButton.setEnabled(isHumanPlayerTurn);
		allinButton.setEnabled(isHumanPlayerTurn);
		moneySlider.setEnabled(isHumanPlayerTurn);
		moneySpinner.setEnabled(isHumanPlayerTurn);
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

		allinButton = new JButton("All in");
		moneySlider = new JSlider(SwingConstants.VERTICAL);
		betRaiseButton = new JButton();
		checkCallButton = new JButton();
		foldButton = new JButton("Fold");
		moneySpinner = new JSpinner();
		loggerTextArea = new JTextArea();

		loggerTextArea.setMaximumSize(loggerTextArea.getPreferredSize());
		loggerTextArea.setAutoscrolls(false);
		loggerTextArea.setEditable(false);
		gameEngine.setLogger(loggerTextArea);

		JScrollPane scrollPane = new JScrollPane(loggerTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(200, 60));

		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener()
		{
			public void adjustmentValueChanged(AdjustmentEvent e)
			{
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});

		// makes spinner update imediately
		JComponent comp = moneySpinner.getEditor();
		JFormattedTextField field = (JFormattedTextField)comp.getComponent(0);
		DefaultFormatter formatter = (DefaultFormatter)field.getFormatter();
		formatter.setCommitsOnValidEdit(true);

		moneySpinner.setMaximumSize(new Dimension(30, 20));

		allinButton.setMargin(new Insets(10, 10, 10, 10));

		GridLayout layout = new GridLayout(1, 4);
		setLayout(layout);

		Box boxButtons = Box.createVerticalBox();
		boxButtons.add(allinButton);
		boxButtons.add(Box.createVerticalStrut(5));
		boxButtons.add(betRaiseButton);
		boxButtons.add(Box.createVerticalStrut(5));
		boxButtons.add(checkCallButton);
		boxButtons.add(Box.createVerticalStrut(5));
		boxButtons.add(foldButton);

		Box spinnerBox = Box.createVerticalBox();
		spinnerBox.add(moneySpinner);
		spinnerBox.add(Box.createVerticalGlue());

		Box boxControls = Box.createHorizontalBox();
		boxControls.add(spinnerBox);
		boxControls.add(moneySlider);
		boxControls.add(boxButtons);

		Box containerBox = Box.createHorizontalBox();
		containerBox.add(boxControls);
		containerBox.add(new JPanelGlue(BoxLayout.X_AXIS));
		containerBox.add(scrollPane);
		add(containerBox);
	}

	private void control()
	{

		moneySpinner.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e)
			{
				if ((int)moneySpinner.getValue() <= moneySlider.getMaximum() && (int)moneySpinner.getValue() > moneySlider.getMinimum())
				{
					moneySlider.setValue((int)moneySpinner.getValue());
				}
				else
				{
					moneySpinner.setValue(moneySlider.getValue());
				}
			}
		});

		moneySlider.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				moneySpinner.setValue(moneySlider.getValue());
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
		ButtonTools.setStyleToButton(checkCallButton, "turquoise");
		ButtonTools.setStyleToButton(foldButton, "red");
		ButtonTools.setStyleToButton(betRaiseButton, "green");
		ButtonTools.setStyleToButton(allinButton, "gold");
	}

}
