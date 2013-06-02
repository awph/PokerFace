
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.gamescreen.player.PlayerComponent;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ColorShop;
import ch.hearc.pokerface.gui.tools.EllipsisLayout;
import ch.hearc.pokerface.gui.tools.ImagePanel;

public class JPanelGameArea extends ImagePanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private List<PlayerComponent>	playerComponents;
	private GameEngine				gameEngine;

	private BoardCardsPanel			boardCardsPanel;

	private JLabel					potBet;
	private JLabel					potTurnTotal;
	private JLabel					potStateTotal;

	private JLabel	currentState;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelGameArea(GameEngine gameEngine) throws IOException
	{
		super(ImageIO.read(ClassLoader.getSystemResource("resources/table/misc/background.png")));
		setDoubleBuffered(true);

		this.gameEngine = gameEngine;
		playerComponents = new ArrayList<PlayerComponent>();

		// BOARD & STATS
		boardCardsPanel = new BoardCardsPanel();


		potBet = new JLabel();
		potStateTotal = new JLabel();
		potTurnTotal = new JLabel();
		currentState = new JLabel();

		currentState.setForeground(ColorShop.PF_RED);

		stylePotLabel(potBet);
		stylePotLabel(potStateTotal);
		stylePotLabel(potTurnTotal);
		stylePotLabel(currentState);





		JPanel currentStatePanel = new JPanel();
		currentStatePanel.setOpaque(false);
		currentStatePanel.setLayout(new GridLayout(0,1));
		currentStatePanel.add(new JLabel());

		currentStatePanel.add(currentState);
		currentStatePanel.add(new JLabel());

		JPanel containerCurrentStatePanel = new JPanel();
		containerCurrentStatePanel.setOpaque(false);
		containerCurrentStatePanel.setLayout(new BoxLayout(containerCurrentStatePanel,BoxLayout.X_AXIS));
		containerCurrentStatePanel.setPreferredSize(new Dimension(100,100));
		containerCurrentStatePanel.add(currentStatePanel);






		JPanel panelPot = new JPanel();
		panelPot.setOpaque(false);
		panelPot.setLayout(new GridLayout(0,1));

		potBet.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ColorShop.PF_GOLD_COLOR));
		panelPot.add(potBet);
		panelPot.add(potTurnTotal);
		potStateTotal.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, ColorShop.PF_GOLD_COLOR));
		panelPot.add(potStateTotal);

		JPanel containerPanelPot = new JPanel();
		containerPanelPot.setOpaque(false);
		containerPanelPot.setLayout(new BoxLayout(containerPanelPot,BoxLayout.X_AXIS));
		containerPanelPot.setPreferredSize(new Dimension(260,140));
		containerPanelPot.add(panelPot);




		JPanel panelBoard = new JPanel();
		panelBoard.setOpaque(false);
		panelBoard.setLayout(new BoxLayout(panelBoard,BoxLayout.X_AXIS));
		panelBoard.add(containerPanelPot);

		panelBoard.add(boardCardsPanel);

		panelBoard.add(containerCurrentStatePanel);

		setLayout(new BorderLayout());

		JPanel panelPlayer = new JPanel();
		panelPlayer.setLayout(new EllipsisLayout());
		panelPlayer.setOpaque(false);

		panelPlayer.setBorder(new EmptyBorder(50, 10, 50, 10)); // inside padding

		for(Player player:this.gameEngine.getPlayers())
		{
			playerComponents.add(new PlayerComponent(player));

			panelPlayer.add(playerComponents.get(playerComponents.size() - 1));

		}

		panelPlayer.add(panelBoard, EllipsisLayout.CENTER);
		add(panelPlayer, BorderLayout.CENTER);
	}


	private void stylePotLabel(JLabel potString)
	{
		Font font = potString.getFont();
		try
		{
			font = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream(ButtonTools.BUTTON_FONT_NAME));
		}
		catch (FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
		font = font.deriveFont(25f);

		potString.setFont(font);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void updateGUI()
	{
		if (!gameEngine.getIsFinished())
		{
			Card[] cards = gameEngine.getUnorderedBoard();

			boardCardsPanel.setCards(cards);

			boolean everyoneAllin = false;

			if (gameEngine.getAllInPlayer() == gameEngine.getUnfoldedPlayer())
			{
				everyoneAllin = true;
			}

			boolean hasSomeoneWon = false;
			for(PlayerComponent playerComponent:playerComponents)
			{
				if (playerComponent.getPlayer().getHasWon())
				{
					hasSomeoneWon = true;
					break;
				}
			}
			for(PlayerComponent playerComponent:playerComponents)
			{
				playerComponent.updateGUI();

				if (playerComponent.getPlayer().getHasWon())
				{
					playerComponent.setHasWonGraphics(true);
				}
				else
				{
					playerComponent.setHasWonGraphics(false);
				}

				if (!hasSomeoneWon && playerComponent.getPlayer() == gameEngine.getCurrentPlayer())
				{
					playerComponent.setCurrentlyPlayingGraphics(true);
				}
				else
				{
					playerComponent.setCurrentlyPlayingGraphics(false);
				}
				if (everyoneAllin)
				{
					playerComponent.setAllinShow(true);
				}
			}
			potBet.setText("<html><font color=black>Current bet</font> : $" + gameEngine.getBet() + "</html>");
			potTurnTotal.setText("<html><font color=black>Turn total</font> : $" + gameEngine.getTurnTotal() + "</html>");
			potStateTotal.setText("<html><font color=black>State total</font> : $" + gameEngine.getStateTotal() + "</html>");
			switch (gameEngine.getOldState())
			{
				case BettingState:
					currentState.setText("Betting");
					break;
				case FlopState:
					currentState.setText("Flop");
					break;
				case PreFlopState:
					currentState.setText("Preflop");
					break;
				case RiverState:
					currentState.setText("River");
					break;
				case ShowdownState:
					currentState.setText("Showdown");
					break;
				case TurnState:
					currentState.setText("Turn");
					break;
			}
		}
	}
}
