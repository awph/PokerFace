
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import net.miginfocom.swing.MigLayout;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.player.Role;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ColorShop;
import ch.hearc.pokerface.gui.tools.ImageShop;

public class JPanelGameControl extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Tools
	private JSpinner			moneySpinner;
	private JSlider				moneySlider;
	private JButton				allinButton;
	private JButton				betRaiseButton;
	private JButton				checkCallButton;
	private JButton				foldButton;
	private boolean				hasHumanPlayed;
	private boolean				isHumanPlayerTurn;
	private boolean				mouseIsInLogger			= false;

	private JEditorPane			loggerTextArea;
	private JPanelStatistics	statisticsPanel;

	//IO
	private GameEngine			gameEngine;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final long	TIME_BETWEEN_EACH_CLICK	= 100;
	private static final String	ALL_IN					= "All in";
	private static final String	FOLD					= "Fold";

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelGameControl(GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;
		this.hasHumanPlayed = false;
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
		int betRaiseValue = gameEngine.getRaiseValue();

		if (humanPlayer.getBankroll() >= gameEngine.getRaiseValue())
		{
			betRaiseValue = gameEngine.getRaiseValue();
		}
		else
		{
			checkCallButton.setEnabled(false);
			betRaiseValue = humanPlayer.getBankroll();
		}

		isHumanPlayerTurn = (humanPlayer == gameEngine.getCurrentPlayer() && !humanPlayer.getHasWon() && !gameEngine.getIsCurrentGameFinised())
				&& !(gameEngine.getOldState() == StateType.PreFlopState && (humanPlayer.getRole() == Role.BigBlind || humanPlayer.getRole() == Role.SmallBlind) && humanPlayer.getNbTurnBet() == 1 && humanPlayer.getBetSpending() <= gameEngine.getBigBlind());

		if (!hasHumanPlayed && isHumanPlayerTurn && !humanPlayer.isFolded() && !humanPlayer.isDead() && humanPlayer.getBankroll() != 0)
		{
			moneySpinner.setModel(new SpinnerNumberModel(betRaiseValue, betRaiseValue, humanPlayer.getBankroll(), 1));

			moneySlider.setMinimum(betRaiseValue);
			moneySlider.setMaximum(humanPlayer.getBankroll());
			moneySlider.setValue(betRaiseValue);
			statisticsPanel.setCurrentState(gameEngine.getOldState());

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

		if (!gameEngine.getIsFinished())
		{
			if (!hasHumanPlayed && isHumanPlayerTurn)
			{
				moneySlider.setMaximum(humanPlayer.getBankroll());
				moneySlider.setMinimum(betRaiseValue);
			}
		}
		else
		{
			gameEngine.finishTheGame();
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
		allinButton = new JButton("");
		moneySlider = new JSlider(SwingConstants.VERTICAL);
		betRaiseButton = new JButton();
		checkCallButton = new JButton();
		foldButton = new JButton("");
		moneySpinner = new JSpinner();
		loggerTextArea = new JEditorPane();
		loggerTextArea.setContentType("text/html");
		loggerTextArea.setBackground(ColorShop.PF_BACKGROUND_LOGGER);
		loggerTextArea.setForeground(Color.WHITE);
		Dimension dim = new Dimension(100, 300);
		loggerTextArea.setSize(dim);
		loggerTextArea.setPreferredSize(dim);
		loggerTextArea.setMinimumSize(dim);
		loggerTextArea.setMaximumSize(dim);
		loggerTextArea.setAutoscrolls(false);
		loggerTextArea.setEditable(false);
		gameEngine.setLogger(loggerTextArea);

		betRaiseButton.setEnabled(false);
		checkCallButton.setEnabled(false);
		foldButton.setEnabled(false);
		allinButton.setEnabled(false);
		moneySlider.setEnabled(false);
		moneySpinner.setEnabled(false);

		statisticsPanel = new JPanelStatistics(GameEngine.HUMAN_PLAYER);

		JScrollPane scrollPane = new JScrollPane(loggerTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(200, 60));

		scrollPane.getVerticalScrollBar().addMouseListener(loggerMouseListener);

		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener()
		{
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e)
			{
				if (!mouseIsInLogger)
				{
					e.getAdjustable().setValue(e.getAdjustable().getMaximum());
				}
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

		setLayout(new BorderLayout());

		JPanel panelGridRight = new JPanel();
		panelGridRight.setOpaque(false);

		setLayout(new MigLayout("insets 0", "[50lp, fill]20", "[20lp, fill]0"));

		add(new JLabel(ImageShop.ICON_HANDRANKING), "width " + "15%!");
		add(boxControls, "width " + "15%!");
		add(scrollPane, "width " + "20%!");
		add(statisticsPanel, "width " + "50%!");
	}

	private void control()
	{
		loggerTextArea.addMouseListener(loggerMouseListener);

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
				if (isHumanPlayerTurn)
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
			}
		});

		allinButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!hasHumanPlayed)
				{
					disableButton();
					Player humanPlayer = GameEngine.HUMAN_PLAYER;
					synchronized (humanPlayer)
					{
						humanPlayer.allIn();
						humanPlayer.notify();
					}
				}
			}
		});

		betRaiseButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!hasHumanPlayed)
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
			}
		});

		checkCallButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!hasHumanPlayed)
				{
					disableButton();
					Player humanPlayer = GameEngine.HUMAN_PLAYER;
					synchronized (humanPlayer)
					{
						humanPlayer.check();
						humanPlayer.notify();
					}
				}
			}
		});

		foldButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!hasHumanPlayed)
				{
					disableButton();
					Player humanPlayer = GameEngine.HUMAN_PLAYER;
					synchronized (humanPlayer)
					{
						humanPlayer.fold();
						humanPlayer.notify();
					}
				}
			}
		});
	}

	private void appearance()
	{
		setBackground(ColorShop.PF_BACKGROUND);
		ButtonTools.setStyleToButton(checkCallButton, "turquoise");
		ButtonTools.setStyleToButton(foldButton, "red");
		ButtonTools.setStyleToButton(betRaiseButton, "green");
		ButtonTools.setStyleToButton(allinButton, "gold");
	}

	private void disableButton()
	{
		if (!hasHumanPlayed)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						Thread.sleep(TIME_BETWEEN_EACH_CLICK);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					JPanelGameControl.this.hasHumanPlayed = false;
				}
			}).start();
		}
		hasHumanPlayed = true;
		betRaiseButton.setEnabled(false);
		checkCallButton.setEnabled(false);
		foldButton.setEnabled(false);
		allinButton.setEnabled(false);
		moneySlider.setEnabled(false);
		moneySpinner.setEnabled(false);
	}

	private MouseListener	loggerMouseListener	= new MouseAdapter()
												{

													@Override
													public void mouseExited(MouseEvent arg0)
													{
														mouseIsInLogger = false;
													}

													@Override
													public void mouseEntered(MouseEvent arg0)
													{
														mouseIsInLogger = true;
													}
												};
}
