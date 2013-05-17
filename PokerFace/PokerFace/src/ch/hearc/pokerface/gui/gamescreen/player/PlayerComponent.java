
package ch.hearc.pokerface.gui.gamescreen.player;

import java.awt.Color;
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
	private Player			player;

	//IO
	private JLabel			name;
	private JLabel			money;

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

		try
		{
			money = new JLabel(Integer.toString(player.getBankroll()), new ImageIcon(ImageIO.read(new File("resources/coin.png"))), SwingConstants.CENTER);
			name = new JLabel(player.getProfile().getName(), player.getProfile().getAvatar().getIcon(), SwingConstants.CENTER);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		money.setHorizontalTextPosition(SwingConstants.LEFT);
		name.setHorizontalTextPosition(SwingConstants.LEFT);

		name.setForeground(Color.RED);
		money.setForeground(Color.GREEN);

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

		// IF Card is not revealed, image is "back.png" (to be set in getId of each card)
		if (player.getPocket().getArray().length != 0)
		{
			card1.setCard(player.getPocket().getArray()[0].getId());
			card2.setCard(player.getPocket().getArray()[1].getId());
		}
		role.setToken(player.getRole().toString());

		if (player.isFolded() || player.isDead())
		{
			setOpaque(true);
			setBackground(Color.red);
		}
		else
		{
			if (isOpaque())
			{
				setOpaque(false);
			}
			setBackground(null);
		}
		money.setText(Integer.toString(player.getBankroll()));
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
		setOpaque(false);
	}

}
