
package ch.hearc.pokerface.gui.gamescreen.player;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.gamescreen.card.CardComponent;

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

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PlayerComponent(Player player)
	{
		this.player = player;
		this.name = new JLabel(player.getProfile().getName());
		this.money = new JLabel(Integer.toString(player.getBankroll()));
		card1 = new CardComponent("NO CARD");
		card2 = new CardComponent("NO MOTHER FUCKING CARD");

		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void updateGUI()
	{
		if (player.isDead())
		{
			//TODO Grisé
			card1 = new CardComponent("NO CARD");
			card2 = new CardComponent("NO MOTHER FUCKING CARD");
		}
		else
		{
			card1 = new CardComponent(player.getPocket().getArray()[0].getId());
			card2 = new CardComponent(player.getPocket().getArray()[1].getId());
		}
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
		box.add(card1);
		box.add(card2);
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
