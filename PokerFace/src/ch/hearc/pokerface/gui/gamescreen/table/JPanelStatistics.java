
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.statistics.StatisticValue;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ColorShop;

public class JPanelStatistics extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Player							player;
	private StateType						currentState;
	private StatisticValue					statisticValue;

	private JPanelStatisticsItemWinLoseTie	winLoseTieItem;

	private JPanelStatisticsItem			straightFlushItem;
	private JPanelStatisticsItem			fourOfKindItem;
	private JPanelStatisticsItem			fullHouseItem;
	private JPanelStatisticsItem			flushItem;
	private JPanelStatisticsItem			straightItem;
	private JPanelStatisticsItem			threeOfKindItem;
	private JPanelStatisticsItem			twoPairsItem;
	private JPanelStatisticsItem			onePairItem;
	private JPanelStatisticsItem			highCardItem;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final int				PERCENT_WIDTH_TITLE		= 17;
	private static final int				PERCENT_WIDTH_GRADIENT	= 50;
	private static final int				PERCENT_WIDTH_VALUE		= 33;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Displays statistics regarding the player's current hand and probabilities
	 * @param player
	 */
	public JPanelStatistics(Player player)
	{
		this.player = player;

		geometry();
		apparence();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setCurrentState(StateType currentState)
	{
		if (currentState != StateType.BettingState && currentState != StateType.ShowdownState && (statisticValue == null || this.currentState != currentState))
		{
			this.currentState = currentState;
		}
		loadNewStatistics();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		winLoseTieItem = new JPanelStatisticsItemWinLoseTie();

		straightFlushItem = new JPanelStatisticsItem("Straight Flush");
		fourOfKindItem = new JPanelStatisticsItem("Four Of A Kind");
		fullHouseItem = new JPanelStatisticsItem("Full House");
		flushItem = new JPanelStatisticsItem("Flush");
		straightItem = new JPanelStatisticsItem("Straight");
		threeOfKindItem = new JPanelStatisticsItem("Three Of A Kind");
		twoPairsItem = new JPanelStatisticsItem("Two Pairs");
		onePairItem = new JPanelStatisticsItem("One Pair");
		highCardItem = new JPanelStatisticsItem("High Card");

		add(winLoseTieItem);
		add(straightFlushItem);
		add(fourOfKindItem);
		add(fullHouseItem);
		add(flushItem);
		add(straightItem);
		add(threeOfKindItem);
		add(twoPairsItem);
		add(onePairItem);
		add(highCardItem);
	}

	private void apparence()
	{
		setBackground(ColorShop.PF_BACKGROUND);
	}

	private void loadNewStatistics()
	{
		switch(currentState)
		{
			case PreFlopState:
				this.statisticValue = player.getPreFlopValues();
				break;
			case FlopState:
				this.statisticValue = player.getFlopValues();
				break;
			case TurnState:
				this.statisticValue = player.getTurnValues();
				break;
			case RiverState:
				this.statisticValue = player.getRiverValues();
				break;
			default:
				break;
		}
		refreshStatistics();
	}

	private void refreshStatistics()
	{
		if (statisticValue != null)
		{
			winLoseTieItem.setPercents(statisticValue.getWin(), statisticValue.getTie(), statisticValue.getLoss());

			straightFlushItem.setPercent(statisticValue.getStraightFlush());
			fourOfKindItem.setPercent(statisticValue.getFourOfKind());
			fullHouseItem.setPercent(statisticValue.getFullHouse());
			flushItem.setPercent(statisticValue.getFlush());
			straightItem.setPercent(statisticValue.getStraight());
			threeOfKindItem.setPercent(statisticValue.getThreeOfKind());
			twoPairsItem.setPercent(statisticValue.getTwoPairs());
			onePairItem.setPercent(statisticValue.getOnePair());
			highCardItem.setPercent(statisticValue.getHighCard());
		}
	}

	/*------------------------------*\
	|*			Sub class			*|
	\*------------------------------*/

	private class JPanelStatisticsItem extends JPanel
	{
		/*------------------------------------------------------------------*\
		|*							Attributs Private						*|
		\*------------------------------------------------------------------*/

		private String			title;
		private double			percent;
		private JLabel			jLabelTitle;
		private JLabel			jLabelPercent;
		private JPanelGradient	jPanelGradient;
		private boolean			noChance;

		/*------------------------------------------------------------------*\
		|*							Constructeurs							*|
		\*------------------------------------------------------------------*/

		public JPanelStatisticsItem(String title)
		{
			this.title = title;

			geometry();
			apparence();

			setPercent(0);
		}

		/*------------------------------------------------------------------*\
		|*							Methodes Public							*|
		\*------------------------------------------------------------------*/

		/*------------------------------*\
		|*				Set				*|
		\*------------------------------*/

		public void setPercent(double percent)
		{
			if (Math.abs(percent - 0.0) <= 10e-6)
			{
				noChance = true;
			}
			else
			{
				noChance = false;
			}
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			this.percent = percent;
			jLabelPercent.setText(decimalFormat.format(percent) + "%");
			jPanelGradient.setPercent(this.percent);
			apparence();
		}

		/*------------------------------------------------------------------*\
		|*							Methodes Private						*|
		\*------------------------------------------------------------------*/

		private void geometry()
		{
			setLayout(new MigLayout("insets 0", "[50lp, fill]0", "[20lp, fill]0"));

			jLabelTitle = new JLabel(title);
			jPanelGradient = new JPanelGradient();
			jLabelPercent = new JLabel(percent + ".00%");

			Font font = jLabelTitle.getFont();
			try
			{
				font = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream(ButtonTools.BUTTON_FONT_NAME));
			}
			catch (FontFormatException | IOException e)
			{
				e.printStackTrace();
			}
			font = font.deriveFont(12f);

			jLabelTitle.setFont(font);
			jLabelPercent.setFont(font);

			add(jLabelTitle, "width " + PERCENT_WIDTH_TITLE + "%!");
			add(jPanelGradient, "width " + PERCENT_WIDTH_GRADIENT + "%!");
			add(jLabelPercent, "width " + PERCENT_WIDTH_VALUE + "%!");
		}

		private void apparence()
		{
			setBackground(ColorShop.PF_BACKGROUND);
			if (noChance)
			{
				jLabelTitle.setForeground(Color.GRAY);
				jLabelPercent.setForeground(Color.GRAY);
			}
			else
			{
				jLabelTitle.setForeground(Color.WHITE);
				jLabelPercent.setForeground(Color.WHITE);
			}
		}

		/*------------------------------*\
		|*			Sub class			*|
		\*------------------------------*/

		private class JPanelGradient extends JPanel
		{
			/*------------------------------------------------------------------*\
			|*							Attributs Private						*|
			\*------------------------------------------------------------------*/

			private double			percent;
			private GradientPaint	gradient;
			private Rectangle2D		gradientRectangle;

			/*------------------------------------------------------------------*\
			|*							Constructeurs							*|
			\*------------------------------------------------------------------*/

			public JPanelGradient()
			{
				this.percent = 0;
				geometry();
				apparence();
			}

			/*------------------------------------------------------------------*\
			|*							Methodes Public							*|
			\*------------------------------------------------------------------*/

			/*------------------------------*\
			|*				Set				*|
			\*------------------------------*/

			public void setPercent(double percent)
			{
				this.percent = percent;

				int w = getWidth();
				int h = getHeight();

				gradient = new GradientPaint(0, 0, ColorShop.PF_RED, w, h, ColorShop.PF_GREEN);
				gradientRectangle = new Rectangle2D.Double(0, 0, (percent / 100.0) * w, h);

				repaint();
			}

			/*------------------------------------------------------------------*\
			|*							Methodes Protected						*|
			\*------------------------------------------------------------------*/

			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D)g;

				g2d.setPaint(gradient);
				g2d.fill(gradientRectangle);
			}

			/*------------------------------------------------------------------*\
			|*							Methodes Private						*|
			\*------------------------------------------------------------------*/

			private void geometry()
			{
				gradient = new GradientPaint(0, 0, Color.RED, getWidth(), getHeight(), Color.GREEN);
				gradientRectangle = new Rectangle2D.Double(0, 0, (percent / 100.0) * getWidth(), getHeight());
			}

			private void apparence()
			{
				setBackground(ColorShop.PF_BACKGROUND);
			}
		}
	}

	private class JPanelStatisticsItemWinLoseTie extends JPanel
	{
		/*------------------------------------------------------------------*\
		|*							Attributs Private						*|
		\*------------------------------------------------------------------*/

		private double								percentWin;
		private double								percentTie;
		private double								percentLose;
		private JLabel								jLabelTitle;
		private JLabel								jLabelPercent;
		private JPanelStatisticsItemWinLoseTieColor	jPanelStatisticsItemWinLoseTieColor;

		/*------------------------------------------------------------------*\
		|*							Constructeurs							*|
		\*------------------------------------------------------------------*/

		public JPanelStatisticsItemWinLoseTie()
		{
			geometry();
			apparence();

			setPercents(0, 0, 0);
		}

		/*------------------------------------------------------------------*\
		|*							Methodes Public							*|
		\*------------------------------------------------------------------*/

		/*------------------------------*\
		|*				Set				*|
		\*------------------------------*/

		public void setPercents(double percentWin, double percentTie, double percentLose)
		{
			this.percentWin = percentWin;
			this.percentTie = percentTie;
			this.percentLose = percentLose;
			jPanelStatisticsItemWinLoseTieColor.setPercents(percentWin, percentTie, percentLose);
			apparence();
		}

		/*------------------------------------------------------------------*\
		|*							Methodes Private						*|
		\*------------------------------------------------------------------*/

		private void geometry()
		{
			setLayout(new MigLayout("insets 0", "[50lp, fill]0", "[20lp, fill]0"));
			setBackground(ColorShop.PF_BACKGROUND);

			jLabelTitle = new JLabel("Win | Tie | Lose");
			jPanelStatisticsItemWinLoseTieColor = new JPanelStatisticsItemWinLoseTieColor();
			jLabelPercent = new JLabel(percentWin + ".00% | " + percentTie + ".00% | " + percentLose + ".00%");

			Font font = jLabelTitle.getFont();
			try
			{
				font = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream(ButtonTools.BUTTON_FONT_NAME));
			}
			catch (FontFormatException | IOException e)
			{
				e.printStackTrace();
			}
			font = font.deriveFont(12f);

			jLabelTitle.setFont(font);
			jLabelPercent.setFont(font);

			jLabelTitle.setForeground(Color.WHITE);
			jLabelPercent.setForeground(Color.WHITE);

			add(jLabelTitle, "width " + PERCENT_WIDTH_TITLE + "%!");
			add(jPanelStatisticsItemWinLoseTieColor, "width " + PERCENT_WIDTH_GRADIENT + "%!");
			add(jLabelPercent, "width " + PERCENT_WIDTH_VALUE + "%!");
		}

		private void apparence()
		{
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			jLabelPercent.setText(decimalFormat.format(percentWin) + "% | " + decimalFormat.format(percentTie) + "% | " + decimalFormat.format(percentLose) + "%");
		}

		/*------------------------------*\
		|*			Sub class			*|
		\*------------------------------*/

		private class JPanelStatisticsItemWinLoseTieColor extends JPanel
		{
			/*------------------------------------------------------------------*\
			|*							Attributs Private						*|
			\*------------------------------------------------------------------*/

			private Rectangle2D	winRectangle;
			private Rectangle2D	tieRectangle;
			private Rectangle2D	loseRectangle;

			/*------------------------------------------------------------------*\
			|*							Constructeurs							*|
			\*------------------------------------------------------------------*/

			public JPanelStatisticsItemWinLoseTieColor()
			{
				setPercents(0, 0, 0);
				geometry();
				apparence();
			}

			/*------------------------------------------------------------------*\
			|*							Methodes Public							*|
			\*------------------------------------------------------------------*/

			/*------------------------------*\
			|*				Set				*|
			\*------------------------------*/

			public void setPercents(double percentWin, double percentTie, double percentLose)
			{
				winRectangle = new Rectangle2D.Double(0, 0, (percentWin / 100.0) * getWidth(), getHeight());
				tieRectangle = new Rectangle2D.Double(winRectangle.getWidth(), 0, (percentTie / 100.0) * getWidth(), getHeight());
				loseRectangle = new Rectangle2D.Double(tieRectangle.getX() + tieRectangle.getWidth(), 0, (percentLose / 100.0) * getWidth(), getHeight());

				repaint();
			}

			/*------------------------------------------------------------------*\
			|*							Methodes Protected						*|
			\*------------------------------------------------------------------*/

			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D)g;

				g2d.setPaint(ColorShop.PF_GREEN);
				g2d.fill(winRectangle);
				g2d.setPaint(ColorShop.PF_YELLOW);
				g2d.fill(tieRectangle);
				g2d.setPaint(ColorShop.PF_RED);
				g2d.fill(loseRectangle);
			}

			/*------------------------------------------------------------------*\
			|*							Methodes Private						*|
			\*------------------------------------------------------------------*/

			private void geometry()
			{
				//Rien
			}

			private void apparence()
			{
				setBackground(ColorShop.PF_BACKGROUND);
			}
		}
	}
}
