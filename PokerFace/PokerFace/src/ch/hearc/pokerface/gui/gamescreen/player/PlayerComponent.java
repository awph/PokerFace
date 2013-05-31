
package ch.hearc.pokerface.gui.gamescreen.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.gamescreen.card.CardComponent;
import ch.hearc.pokerface.gui.gamescreen.card.PlayerCard;
import ch.hearc.pokerface.gui.options.JPanelTopBar;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ImageShop;

public class PlayerComponent extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private Player				player;

	private boolean				allinShow;
	private boolean				isHumanPlayer;

	//IO
	private JLabel				name;
	private JLabel				money;
	private JLabel				turnSpend;

	private CardComponent		card1;
	private CardComponent		card2;
	private Token				role;

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public static final Color	PF_GOLD_COLOR	= new Color(255, 215, 0);

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PlayerComponent(Player player)
	{
		this.player = player;
		//money = new JLabel(Integer.toString(player.getBankroll()));

		money = new JLabel("$" + Integer.toString(player.getBankroll()), ImageShop.ICON_COIN, SwingConstants.CENTER);
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
			setBackground(Color.GREEN);
		}
		else
		{

			setBackground(new Color(25, 25, 25, 120));
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
		if (player.isFolded() || player.isDead())
		{
			card1.setCard("folded1");
			card2.setCard("folded2");
		}
		else
		{

			if (player.getPocket().getArray().length != 0)
			{
				//if (allinShow || !(player instanceof AI))//TODO A METTRE DANS VERSION FINALE
				{
					card1.setCard(player.getPocket().getArray()[0].getId());
					card2.setCard(player.getPocket().getArray()[1].getId());
				}
			}
		}

		role.setToken(player.getRole().toString());
		money.setText("$" + Integer.toString(player.getBankroll()));
		JPanelTopBar.getInstance().setCapital(Integer.toString(player.getBankroll()));
		turnSpend.setText("$" + player.getTurnSpending());
		//getParent().update(getParent().getGraphics());
		turnSpend.repaint();
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
		box.add(nameBox);

		Box moneyAndBetSpendBox = Box.createHorizontalBox();
		moneyAndBetSpendBox.add(turnSpend);
		moneyAndBetSpendBox.add(Box.createHorizontalGlue());
		moneyAndBetSpendBox.add(money);

		box.add(moneyAndBetSpendBox);
		Box cardsBox = Box.createHorizontalBox();
		cardsBox.add(Box.createHorizontalGlue());
		cardsBox.add(card1);
		cardsBox.add(card2);
		cardsBox.add(role);
		box.add(cardsBox);
		add(box);
	}

	private void control()
	{
		// TODO Auto-generated method stub

	}

	private void appearance()
	{
		setBackground(new Color(25, 25, 25, 120));

		Font font = name.getFont();
		try
		{
			font = Font.createFont(Font.TRUETYPE_FONT, new File(ButtonTools.BUTTON_FONT_NAME));
		}
		catch (FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
		font = font.deriveFont(20f);
		font = font.deriveFont(Font.PLAIN);

		name.setFont(font);
		money.setFont(font);
		turnSpend.setFont(font);

		name.setForeground(Color.WHITE);
		money.setForeground(PF_GOLD_COLOR);
		turnSpend.setForeground(new Color(39, 181, 73));
	}

}
