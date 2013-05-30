
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.gamescreen.player.PlayerComponent;
import ch.hearc.pokerface.gui.tools.ButtonTools;
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
		super(ImageIO.read(new File("resources/table/background.png")));
		setDoubleBuffered(true);

		this.gameEngine = gameEngine;
		playerComponents = new ArrayList<PlayerComponent>();

		// BOARD & STATS
		boardCardsPanel = new BoardCardsPanel();


		potBet = new JLabel();
		potStateTotal = new JLabel();
		potTurnTotal = new JLabel();
		currentState = new JLabel();

		stylePotLabel(potBet);
		stylePotLabel(potStateTotal);
		stylePotLabel(potTurnTotal);
		stylePotLabel(currentState);

		JPanel panelPot = new JPanel();
		panelPot.setOpaque(false);
		panelPot.setLayout(new GridLayout(0,1));

		JPanel currentStatePanel = new JPanel();
		currentStatePanel.setOpaque(false);
		currentStatePanel.setLayout(new GridLayout(0,1));
		currentStatePanel.add(new JLabel());

		currentStatePanel.add(currentState);
		currentStatePanel.add(new JLabel());

		panelPot.add(potBet);
		panelPot.add(potTurnTotal);
		panelPot.add(potStateTotal);


		JPanel panelBoard = new JPanel();
		panelBoard.setOpaque(false);
		panelBoard.setLayout(new BorderLayout());
		panelBoard.add(panelPot, BorderLayout.WEST);

		panelBoard.add(boardCardsPanel, BorderLayout.CENTER);

		panelBoard.add(currentStatePanel, BorderLayout.EAST);

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
			font = Font.createFont(Font.TRUETYPE_FONT, new File(ButtonTools.BUTTON_FONT_NAME));
		}
		catch (FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
		font = font.deriveFont(25f);

		potString.setForeground(Color.RED); // HTML?
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
			potBet.setText("Current bet : " + gameEngine.getBet());
			potTurnTotal.setText("Turn total : " + gameEngine.getTurnTotal());
			potStateTotal.setText("State total : " + gameEngine.getStateTotal());
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
