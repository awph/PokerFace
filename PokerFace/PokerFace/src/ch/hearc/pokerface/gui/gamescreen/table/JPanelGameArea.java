
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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
import ch.hearc.pokerface.gameengine.statistics.StatisticValue;
import ch.hearc.pokerface.gui.gamescreen.player.PlayerComponent;
import ch.hearc.pokerface.gui.tools.EllipsisLayout;
import ch.hearc.pokerface.gui.tools.ImagePanel;

public class JPanelGameArea extends ImagePanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private List<PlayerComponent>	playerComponents;
	private Player					humanPlayer;
	private GameEngine				gameEngine;

	private BoardCardsPanel boardCardsPanel;
	private JLabel					board;
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

		board = new JLabel();
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

		final Player humanPlayer = GameEngine.HUMAN_PLAYER;
		Thread t = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				StatisticValue pf = humanPlayer.getPreFlopValues();
				StatisticValue f = humanPlayer.getFlopValues();
				StatisticValue t = humanPlayer.getTurnValues();
				StatisticValue r = humanPlayer.getRiverValues();
				while(true)
				{
					pf = humanPlayer.getPreFlopValues();
					f = humanPlayer.getFlopValues();
					t = humanPlayer.getTurnValues();
					r = humanPlayer.getRiverValues();

					writeStat(pf, statsPF, "PreFlop");
					writeStat(f, statsF, "Flop");
					writeStat(t, statsT, "Turn");
					writeStat(r, statsR, "River");

					updateUI();
				}
			}
		});
		t.start();

	}



	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void updateGUI()
	{
		//TODO BOARDPANEL.updateGUI()
		Card[] cards = gameEngine.getUnorderedBoard();

		boardCardsPanel.setCards(cards);

		for(PlayerComponent playerComponent:playerComponents)
		{
			playerComponent.updateGUI();
		}

		pot.setText("<html><body>Bet : " + gameEngine.getPot().getBet() + "<br/>StateSpend : " + gameEngine.getPot().getStateTotal() + "<br/>TotalSpend : " + gameEngine.getPot().getTurnTotal() + "</body></html>");
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

	private void writeStat(StatisticValue sv, JLabel lbl, String title)
	{
		if (sv != null)
		{
			DecimalFormat df = new DecimalFormat("#.##");
			StringBuilder str = new StringBuilder("<html><body>");
			str.append(title);
			str.append("<br/>");
			str.append("Win&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getWin()) + "%<br/>");
			str.append("Loss&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getLoss()) + "%<br/>");
			str.append("Tie&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getTie()) + "%<br/>");
			str.append("nbAO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getNbOpponantWinner()) + "<br/>");
			str.append("SF&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getStraightFlush()) + "%<br/>");
			str.append("4K&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getFourOfKind()) + "%<br/>");
			str.append("FH&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getFullHouse()) + "%<br/>");
			str.append("F&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getFlush()) + "%<br/>");
			str.append("Straight&nbsp;&nbsp;&nbsp;: " + df.format(sv.getStraight()) + "%<br/>");
			str.append("3K&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getThreeOfKind()) + "%<br/>");
			str.append("2P&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getTwoPairs()) + "%<br/>");
			str.append("1P&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getOnePair()) + "%<br/>");
			str.append("HC&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + df.format(sv.getHighCard()) + "%<br/>");
			str.append("</body></html>");

			lbl.setText(str.toString());

		}
	}
}
