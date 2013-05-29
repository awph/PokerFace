
package ch.hearc.pokerface.gui.gamescreen.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.gamescreen.card.CardComponent;
import ch.hearc.pokerface.gui.gamescreen.card.PlayerCard;

public class PlayerComponent extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private Player				player;

	//IO
	private JLabel				name;
	private JLabel				money;

	private CardComponent		card1;
	private CardComponent		card2;
	private Token				role;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static final String	FONT	= "resources/Franklin Gothic Demi Cond.ttf";

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PlayerComponent(Player player)
	{
		this.player = player;
		//money = new JLabel(Integer.toString(player.getBankroll()));

		try
		{
			money = new JLabel("$" + Integer.toString(player.getBankroll()), new ImageIcon(ImageIO.read(new File("resources/coin.png"))), SwingConstants.CENTER);
			name = new JLabel(player.getProfile().getName(), player.getProfile().getAvatar().getIcon(), SwingConstants.CENTER);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

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

	public void updateGUI()
	{
		if (player.isFolded() || player.isDead())
		{
			card1.setCard("folded1");
			card2.setCard("folded2");
		}
		else
		{
			// IF Card is not revealed, image is "back.png" (to be set in getId of each card)
			if (player.getPocket().getArray().length != 0)
			{
				card1.setCard(player.getPocket().getArray()[0].getId());
				card2.setCard(player.getPocket().getArray()[1].getId());
			}
		}

		role.setToken(player.getRole().toString());
		money.setText("$" + Integer.toString(player.getBankroll()));
		getParent().repaint();
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

	private void geometry()
	{
		Box box = Box.createVerticalBox();
		box.add(name);
		box.add(money);
		Box cardsBox = Box.createHorizontalBox();
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
			font = Font.createFont(Font.TRUETYPE_FONT, new File(FONT));
		}
		catch (FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
		font = font.deriveFont(20f);
		font = font.deriveFont(Font.PLAIN);

		name.setFont(font);
		money.setFont(font);

		name.setForeground(Color.WHITE);
		money.setForeground(new Color(255, 215, 0));
	}
}
