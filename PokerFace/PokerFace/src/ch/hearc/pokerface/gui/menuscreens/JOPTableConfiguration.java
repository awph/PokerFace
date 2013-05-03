
package ch.hearc.pokerface.gui.menuscreens;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.profile.ActiveProfile;
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
	private static final int	BLIND_DEFAULT		= 10;

	private JTextArea			bankRollTextArea;
	private JTextArea			smallBlindSpinner;
	private JSpinner			nbPlayersSpinner;

	private int					startCash;
	private int					nbPlayers;
	private int					smallBlind;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JOPTableConfiguration(JPanelGameBoard panelGameBoard)
	{
		boolean dialog = false;
		boolean checkValues = false;
		do
		{
			dialog = showDialog();
			checkValues = checkValues();

			if (!dialog)
			{
				return;
			}

			else if (checkValues)
			{
				panelGameBoard.start(new GameEngine(smallBlind, nbPlayers, ActiveProfile.getInstance().getProfile(), startCash, panelGameBoard));
			}
		} while(!checkValues);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static boolean trySwitch() {
		// TODO : cancel button management (go mainMenu if cancel..)
		return false;
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
		try {
			nbPlayers = Integer.parseInt(nbPlayersSpinner.getValue().toString());
			smallBlind = Integer.parseInt(smallBlindSpinner.getText());
			startCash = Integer.parseInt(bankRollTextArea.getText());
		}
		catch(Exception e)
		{
			JOptionPane.showOptionDialog(null, "Invalid values!", "Mother Fucker!", DEFAULT_OPTION, ERROR_MESSAGE, null, null, null);
		}

		boolean nbPlayersOk = (nbPlayers >= NB_PLAYER_MIN && nbPlayers <= 10);
		boolean smallBlindOk = (smallBlind >= BLIND_MIN && smallBlind <= startCash / 4);
		boolean startCashOk = (startCash <= ActiveProfile.getInstance().getProfile().getCapital());

		return (nbPlayersOk && smallBlindOk && startCashOk);
	}

	private boolean showDialog()
	{
		String options[] = { "Play", "Cancel" };
		return (showOptionDialog(null, getPanel(), "Table Configuration", DEFAULT_OPTION, PLAIN_MESSAGE, null, options, null) == 0);
	}

	private JPanel getPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));

		int bankRoll = (ActiveProfile.getInstance().getProfile().getCapital());

		nbPlayersSpinner = new JSpinner(new SpinnerNumberModel(NB_PLAYER_DEFAULT, NB_PLAYER_MIN, NB_PLAYER_MAX, 1));

		smallBlindSpinner = new JTextArea(String.valueOf(10));

		((DefaultEditor)nbPlayersSpinner.getEditor()).getTextField().setEditable(false);
		bankRollTextArea = new JTextArea(String.valueOf(bankRoll));

		panel.add(new JLabel("Blind"));
		panel.add(smallBlindSpinner);
		panel.add(new JLabel("Number of players"));
		panel.add(nbPlayersSpinner);
		panel.add(new JLabel("Bankroll"));
		panel.add(bankRollTextArea);

		return panel;
	}
}
