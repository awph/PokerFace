
package ch.hearc.pokerface.gui.gamescreen.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.hearc.pokerface.gameengine.player.AI;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.gamescreen.card.CardComponent;
import ch.hearc.pokerface.gui.gamescreen.card.PlayerCard;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ColorShop;
import ch.hearc.pokerface.gui.tools.ImageShop;

public class PlayerComponent extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private Player			player;

	/*
	 * @contains Whether the player's cards has to be showns or not, in case of an all-in from everyone
	 */
	boolean allinShow = false;

	//IO
	private JLabel			name;
	private JLabel			money;
	private JLabel			betSpend;
	private JLabel			turnSpend;

	private CardComponent	card1;
	private CardComponent	card2;
	private Token			role;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PlayerComponent(Player player)
	{
		this.player = player;
		//money = new JLabel(Integer.toString(player.getBankroll()));

		money = new JLabel("$" + Integer.toString(player.getBankroll()), ImageShop.ICON_COIN, SwingConstants.CENTER);
		betSpend = new JLabel("" + player.getBetSpending());
		turnSpend = new JLabel("" + player.getTurnSpending());
		name = new JLabel(player.getProfile().getName(), player.getProfile().getAvatar().getIcon(), SwingConstants.CENTER);

		money.setHorizontalTextPosition(SwingConstants.LEFT);
		name.setHorizontalTextPosition(SwingConstants.LEFT);

		card1 = new PlayerCard("back");
		card2 = new PlayerCard("back");

		role = new Token(player.getRole().toString());

		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setHasWonGraphics(boolean b)
	{
		if (b)
		{
			setBackground(ColorShop.PF_GREEN);
		}
		else
		{
			setBackground(ColorShop.PF_BACKGROUND_PLAYER_COMPONENT);
		}
	}

	public void setCurrentlyPlayingGraphics(boolean b)
	{
		if (b)
		{
			setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
		}
		else
		{
			setBorder(BorderFactory.createEmptyBorder());
		}

	}

	public void updateGUI()
	{
		if (player.isFolded())
		{
			card1.setCard("folded");
			card2.setCard("empty");
		}
		else if(player.isDead())
		{
			card1.setCard("sitsout");
			card2.setCard("empty");
		}
		else
		{

			if (player.getPocket().getArray().length != 0)
			{
				//Hide the card of the adverser
				if (allinShow || !(player instanceof AI))
				{
					card1.setCard(player.getPocket().getArray()[0].getId());
					card2.setCard(player.getPocket().getArray()[1].getId());
				}
			}
		}

		role.setToken(player.getRole().toString());
		money.setText("$" + Integer.toString(player.getBankroll()));
		betSpend.setText("$" + player.getBetSpending());
		turnSpend.setText("$" + player.getTurnSpending());
		betSpend.repaint();
		getParent().repaint();
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setAllinShow(boolean b)
	{
		allinShow = b;
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public Player getPlayer()
	{
		return player;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
	{
		Box box = Box.createVerticalBox();

		Box nameBox = Box.createHorizontalBox();
		nameBox.add(name);
		nameBox.add(role);
		box.add(nameBox);

		Box moneyAndBetSpendBox = Box.createHorizontalBox();
		moneyAndBetSpendBox.add(betSpend);
		moneyAndBetSpendBox.add(Box.createHorizontalStrut(10));
		moneyAndBetSpendBox.add(turnSpend);
		moneyAndBetSpendBox.add(Box.createHorizontalGlue());
		moneyAndBetSpendBox.add(money);

		box.add(moneyAndBetSpendBox);
		Box cardsBox = Box.createHorizontalBox();

		cardsBox.add(Box.createHorizontalGlue());
		cardsBox.add(card1);
		cardsBox.add(card2);
		box.add(cardsBox);
		add(box);
	}

	private void control()
	{
		//Rien
	}

	private void appearance()
	{
		setBackground(ColorShop.PF_BACKGROUND_PLAYER_COMPONENT);

		Font font = name.getFont();
		try
		{
			font = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream(ButtonTools.BUTTON_FONT_NAME));
		}
		catch (FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
		font = font.deriveFont(20f);
		font = font.deriveFont(Font.PLAIN);

		name.setFont(font);
		money.setFont(font);
		betSpend.setFont(font);
		turnSpend.setFont(font);

		name.setForeground(Color.WHITE);
		money.setForeground(ColorShop.PF_GOLD_COLOR);
		betSpend.setForeground(ColorShop.PF_GREEN);
		turnSpend.setForeground(ColorShop.PF_BLUE);
	}

}
