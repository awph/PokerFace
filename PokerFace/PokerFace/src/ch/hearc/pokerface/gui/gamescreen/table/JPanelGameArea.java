
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.gamescreen.player.PlayerComponent;
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
	private JLabel					statsPF;
	private JLabel					statsF;
	private JLabel					statsT;
	private JLabel					statsR;
	private JLabel					pot;

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

		statsPF = new JLabel();
		statsF = new JLabel();
		statsT = new JLabel();
		statsR = new JLabel();
		pot = new JLabel();
		Box box = Box.createVerticalBox();
		box.add(boardCardsPanel);
		box.add(pot);
		Box boxStats = Box.createHorizontalBox();
		boxStats.add(statsPF);
		boxStats.add(Box.createHorizontalStrut(15));
		boxStats.add(statsF);
		boxStats.add(Box.createHorizontalStrut(15));
		boxStats.add(statsT);
		boxStats.add(Box.createHorizontalStrut(15));
		boxStats.add(statsR);
		//box.add(boxStats);
		//

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

		panelPlayer.add(box, EllipsisLayout.CENTER);
		add(panelPlayer, BorderLayout.CENTER);
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
			pot.setText("<html><body>Bet : " + gameEngine.getBet() + "<br/>StateSpend : " + gameEngine.getStateTotal() + "<br/>TotalSpend : " + gameEngine.getTurnTotal() + "</body></html>");
		}
	}
}
