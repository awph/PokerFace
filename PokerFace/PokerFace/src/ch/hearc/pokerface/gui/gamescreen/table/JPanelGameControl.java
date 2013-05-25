
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
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.tools.ButtonTools;

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

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final String	ALL_IN	= "All in";
	private static final String	FOLD	= "Fold";


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
		checkCallButton.setEnabled(true); //TODO WTF ???
		Player humanPlayer = GameEngine.HUMAN_PLAYER;
		int betRaiseValue = gameEngine.getRaiseValue();
		try
		{
			if (humanPlayer.getBankroll() >= gameEngine.getRaiseValue())
			{
				betRaiseValue = gameEngine.getRaiseValue();
			}
			else
			{
				checkCallButton.setEnabled(false);
				betRaiseValue = humanPlayer.getBankroll();
			}

			moneySpinner.setModel(new SpinnerNumberModel(betRaiseValue, betRaiseValue, humanPlayer.getBankroll(), 1));
		}
		catch (IllegalArgumentException e)
		{
			moneySpinner.setModel(new SpinnerNumberModel(0, 0, 0, 0));
		}

		moneySlider.setMinimum(betRaiseValue);
		moneySlider.setMaximum(humanPlayer.getBankroll());
		moneySlider.setValue(betRaiseValue);

		if (!humanPlayer.isFolded())
		{
			if (humanPlayer.getCallValue() == 0)
			{
				checkCallButton.setText("Check");
				betRaiseButton.setText("Bet " + betRaiseValue + "$");
			}
			else
			{
				if (betRaiseValue <= humanPlayer.getCallValue())
				{
					checkCallButton.setText("All-in with " + betRaiseValue + "$");
					betRaiseButton.setText("All-in with  " + betRaiseValue + "$");
				}
				else
				{
					checkCallButton.setText("Call " + humanPlayer.getCallValue() + "$");
					betRaiseButton.setText("Raise " + betRaiseValue + "$");
				}
			}
			allinButton.setText(ALL_IN);
			foldButton.setText(FOLD);
		}
		else
		{

			allinButton.setText("");
			betRaiseButton.setText("");
			checkCallButton.setText("");
			foldButton.setText("");
		}

		boolean isHumanPlayerTurn = false;

		if (!gameEngine.getIsFinished())
		{
			isHumanPlayerTurn = (humanPlayer == gameEngine.getCurrentPlayer());

			if (isHumanPlayerTurn)
			{
				moneySlider.setMaximum(humanPlayer.getBankroll());
				moneySlider.setMinimum(betRaiseValue);
			}
		}
		else
		{
			if (humanPlayer.isDead())
			{
				JOptionPane.showMessageDialog(null, gameEngine.getPlayers().get(0).getProfile().getName() + " wins !", "You lose !", JOptionPane.INFORMATION_MESSAGE);
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

		allinButton = new JButton(ALL_IN);
		moneySlider = new JSlider(SwingConstants.VERTICAL);
		betRaiseButton = new JButton();
		checkCallButton = new JButton();
		foldButton = new JButton(FOLD);
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
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e)
			{
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});

		// makes spinner update immediately
		JComponent comp = moneySpinner.getEditor();
		JFormattedTextField field = (JFormattedTextField)comp.getComponent(0);
		DefaultFormatter formatter = (DefaultFormatter)field.getFormatter();
		formatter.setCommitsOnValidEdit(true);

		moneySpinner.setMaximumSize(new Dimension(60, 20));
		moneySlider.setPreferredSize(new Dimension(60, 150));
		allinButton.setMargin(new Insets(10, 10, 10, 10));

		GridLayout layout = new GridLayout(1, 4);
		setLayout(layout);

		Box boxButtons = Box.createVerticalBox();
		boxButtons.add(Box.createVerticalGlue());
		boxButtons.add(allinButton);
		boxButtons.add(Box.createVerticalStrut(5));
		boxButtons.add(betRaiseButton);
		boxButtons.add(Box.createVerticalStrut(5));
		boxButtons.add(checkCallButton);
		boxButtons.add(Box.createVerticalStrut(5));
		boxButtons.add(foldButton);
		boxButtons.add(Box.createVerticalGlue());

		Box spinnerBox = Box.createVerticalBox();
		spinnerBox.add(Box.createVerticalGlue());
		spinnerBox.add(moneySpinner);
		spinnerBox.add(Box.createVerticalStrut(5));
		spinnerBox.add(moneySlider);
		spinnerBox.add(Box.createVerticalGlue());

		Box boxControls = Box.createHorizontalBox();
		boxControls.add(Box.createHorizontalGlue());
		boxControls.add(spinnerBox);
		boxControls.add(boxButtons);
		boxControls.add(Box.createHorizontalGlue());

		add(boxControls);
		add(scrollPane);
	}

	private void control()
	{
		moneySpinner.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if ((int)moneySpinner.getValue() <= moneySlider.getMaximum() && (int)moneySpinner.getValue() >= moneySlider.getMinimum())
				{
					moneySlider.setValue((int)moneySpinner.getValue());
				}
			}
		});

		moneySlider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				moneySpinner.setValue(moneySlider.getValue());
				if (moneySlider.getMaximum() == moneySlider.getValue())
				{
					betRaiseButton.setText("All-in with " + moneySlider.getValue() + "$");
				}
				else if (gameEngine.getBet() == 0)
				{
					betRaiseButton.setText("Bet " + moneySlider.getValue() + "$");
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
				disableButton();
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
				disableButton();
				Player humanPlayer = GameEngine.HUMAN_PLAYER;
				synchronized (humanPlayer)
				{
					if (gameEngine.getBet() == 0)
					{
						humanPlayer.bet(moneySlider.getValue());
					}
					else
					{
						humanPlayer.raise(moneySlider.getValue());
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
				disableButton();
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
				disableButton();
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

	private void disableButton()
	{
		betRaiseButton.setEnabled(false);
		checkCallButton.setEnabled(false);
		foldButton.setEnabled(false);
		allinButton.setEnabled(false);
		moneySlider.setEnabled(false);
		moneySpinner.setEnabled(false);
	}
}
