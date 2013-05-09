
package ch.hearc.pokerface.gui.gamescreen.player;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.gamescreen.card.CardComponent;
import ch.hearc.pokerface.gui.gamescreen.card.PlayerCard;

public class PlayerComponent extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JLabel			name;
	private JLabel			money;
	private Player			player;
	private CardComponent	card1;
	private CardComponent	card2;
	private Token			role;	//TEMP

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PlayerComponent(Player player)
	{
		this.player = player;
		name = new JLabel(player.getProfile().getName());
		money = new JLabel(Integer.toString(player.getBankroll()));
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
		try
		{
			// IF Card is not revealed, image is "back.png" (to be set in getId of each card)
			card1.setCard(player.getPocket().getArray()[0].getId());
			card2.setCard(player.getPocket().getArray()[1].getId());
		}
		catch (Exception e)
		{
			//e.printStackTrace();
		}

		if (player.isFolded() || player.isDead())
		{
			setBackground(Color.red);
		}
		else
		{
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
		// TODO Auto-generated method stub

	}

}
