
package ch.hearc.pokerface.gui.gamescreen.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.statistics.StatisticValue;
import ch.hearc.pokerface.gui.gamescreen.player.PlayerComponent;

public class JPanelStatistics extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Player					player;
	private StateType				currentState;
	private StatisticValue			statisticValue;

	private JPanelStatisticsItem	straightFlushItem;
	private JPanelStatisticsItem	fourOfKindItem;
	private JPanelStatisticsItem	fullHouseItem;
	private JPanelStatisticsItem	flushItem;
	private JPanelStatisticsItem	straightItem;
	private JPanelStatisticsItem	threeOfKindItem;
	private JPanelStatisticsItem	twoPairsItem;
	private JPanelStatisticsItem	onePairItem;
	private JPanelStatisticsItem	highCardItem;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

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
			loadNewStatistics();
		}
		getParent().repaint();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		straightFlushItem = new JPanelStatisticsItem("Straight Flush");
		fourOfKindItem = new JPanelStatisticsItem("Four Of A Kind");
		fullHouseItem = new JPanelStatisticsItem("Full House");
		flushItem = new JPanelStatisticsItem("Flush");
		straightItem = new JPanelStatisticsItem("Straight");
		threeOfKindItem = new JPanelStatisticsItem("Three Of A Kind");
		twoPairsItem = new JPanelStatisticsItem("Two Pairs");
		onePairItem = new JPanelStatisticsItem("One Pair");
		highCardItem = new JPanelStatisticsItem("High Card");

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
		setBackground(new Color(25, 25, 25, 120));
	}

	/*------------------------------*\
	|*			Sub class			*|
	\*------------------------------*/

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
			straightFlushItem.setPercent((int)statisticValue.getStraightFlush());
			fourOfKindItem.setPercent((int)statisticValue.getFourOfKind());
			fullHouseItem.setPercent((int)statisticValue.getFullHouse());
			flushItem.setPercent((int)statisticValue.getFlush());
			straightItem.setPercent((int)statisticValue.getStraight());
			threeOfKindItem.setPercent((int)statisticValue.getThreeOfKind());
			twoPairsItem.setPercent((int)statisticValue.getTwoPairs());
			onePairItem.setPercent((int)statisticValue.getOnePair());
			highCardItem.setPercent((int)statisticValue.getHighCard());
		}
	}

	private class JPanelStatisticsItem extends JPanel
	{
		/*------------------------------------------------------------------*\
		|*							Attributs Private						*|
		\*------------------------------------------------------------------*/

		private String			title;
		private int				percent;
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

		public void setPercent(float percent)
		{
			if (percent <= 0)
			{
				noChance = true;
			}
			else
			{
				noChance = true;
			}
			this.percent = (int)percent;
			jLabelPercent.setText(this.percent + "%");
			jPanelGradient.setPercent(this.percent);
		}

		/*------------------------------------------------------------------*\
		|*							Methodes Private						*|
		\*------------------------------------------------------------------*/

		private void geometry()
		{
			GridLayout layout = new GridLayout(1, 3);
			setLayout(layout);

			jLabelTitle = new JLabel(title);
			jPanelGradient = new JPanelGradient();
			jLabelPercent = new JLabel(percent + "%");

			Font font = jLabelTitle.getFont();
			try
			{
				font = Font.createFont(Font.TRUETYPE_FONT, new File(PlayerComponent.FONT));
			}
			catch (FontFormatException | IOException e)
			{
				e.printStackTrace();
			}
			font = font.deriveFont(12f);

			jLabelTitle.setFont(font);
			jLabelPercent.setFont(font);

			add(jLabelTitle);
			add(jPanelGradient);
			add(jLabelPercent);
		}

		private void apparence()
		{
			setBackground(null);
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

			private int				percent;
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

			public void setPercent(int percent)
			{
				this.percent = percent;

				int w = getWidth();
				int h = getHeight();

				gradient = new GradientPaint(0, 0, Color.RED, w, h, Color.GREEN);
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
				gradient = new GradientPaint(0, 0, Color.BLUE, getWidth(), getHeight(), Color.RED);
				gradientRectangle = new Rectangle2D.Double(0, 0, percent * getWidth(), getHeight());
			}

			private void apparence()
			{
				setBackground(null);
			}
		}
	}
}
