
package ch.hearc.pokerface.gui.menuscreens;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.profile.ActiveProfile;
import ch.hearc.pokerface.gui.JFrameMain;
import ch.hearc.pokerface.gui.gamescreen.table.board.JPanelGameBoard;

public class JOPTableConfiguration extends JOptionPane
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private static final int	NB_PLAYER_MAX		= 10;
	private static final int	NB_PLAYER_MIN		= 2;
	private static final int	NB_PLAYER_DEFAULT	= 4;

	private static final int	BLIND_MIN			= 10;
	private static final int	DIVISOR_MIN_BLIND	= 4;

	private JTextField			bankRollTextField;
	private JTextField			smallBlindTextField;
	private JSpinner			nbPlayersSpinner;

	private int					startCash;
	private int					nbPlayers;
	private int					smallBlind;

	//Inputs

	private JFrameMain				frameMain;
	private JPanelGameBoard		panelGameBoard;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JOPTableConfiguration(JFrameMain frameMain, JPanelGameBoard panelGameBoard)
	{
		this.frameMain = frameMain;
		this.panelGameBoard = panelGameBoard;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static boolean trySwitchToGameBoard()
	{
		return false;
	}

	public boolean switchToGame()
	{
		boolean dialog = false;
		boolean checkValues = false;
		boolean isCanceled = false;
		do
		{
			dialog = showDialog();
			checkValues = checkValues();

			if (!dialog)
			{
				isCanceled = true;
			}
			else if (checkValues)
			{
				panelGameBoard.start(new GameEngine(frameMain,smallBlind, nbPlayers, ActiveProfile.getInstance().getProfile(), startCash, panelGameBoard));
			}

		} while(!checkValues && !isCanceled);
		return !isCanceled;
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

	private boolean checkValues()
	{
		try
		{
			nbPlayers = Integer.parseInt(nbPlayersSpinner.getValue().toString());
			smallBlind = Integer.parseInt(smallBlindTextField.getText());
			startCash = Integer.parseInt(bankRollTextField.getText());
		}
		catch (Exception e)
		{
			JOptionPane.showOptionDialog(null, "One or more fields don't contain a number !", "Invalid values !", DEFAULT_OPTION, ERROR_MESSAGE, null, null, null);
			return false;
		}

		try
		{
			if (nbPlayers < NB_PLAYER_MIN || nbPlayers > NB_PLAYER_MAX)
			{
				Exception e = new Exception("Values out of bounds for player ! Minimum " + NB_PLAYER_MIN + " and maximum " + NB_PLAYER_MAX);
				throw e;
			}
			if (smallBlind < BLIND_MIN || smallBlind > startCash / DIVISOR_MIN_BLIND)
			{
				Exception e = new Exception("Values out of bounds for the blind ! Minimum " + BLIND_MIN + " and maximum " + startCash / DIVISOR_MIN_BLIND);
				throw e;
			}
			if (startCash > ActiveProfile.getInstance().getProfile().getCapital())
			{
				Exception e = new Exception("Values out of bounds for the bankroll ! You don't have enough money ! ");
				throw e;
			}
		}
		catch (Exception e)
		{
			JOptionPane.showOptionDialog(null, e.getMessage(), "Invalid values !", DEFAULT_OPTION, ERROR_MESSAGE, null, null, null);
			return false;
		}

		return true;
	}

	private boolean showDialog()
	{
		String options[] = { "Play", "Cancel" };
		return showOptionDialog(frameMain, getPanel(), "Table Configuration", DEFAULT_OPTION, PLAIN_MESSAGE, null, options, null) == 0;
	}

	private JPanel getPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));

		int bankRoll = (ActiveProfile.getInstance().getProfile().getCapital());

		nbPlayersSpinner = new JSpinner(new SpinnerNumberModel(NB_PLAYER_DEFAULT, NB_PLAYER_MIN, NB_PLAYER_MAX, 1));
		smallBlindTextField = new JTextField(String.valueOf(BLIND_MIN));
		bankRollTextField = new JTextField(String.valueOf(bankRoll));

		panel.add(new JLabel("Blind"));
		panel.add(smallBlindTextField);
		panel.add(new JLabel("Number of players"));
		panel.add(nbPlayersSpinner);
		panel.add(new JLabel("Bankroll"));
		panel.add(bankRollTextField);

		return panel;
	}
}
