
package ch.hearc.pokerface.gui.gamescreen.player;

import java.awt.Color;

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
	private JLabel			role;	//TEMP

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PlayerComponent(Player player)
	{
		this.player = player;
		name = new JLabel(player.getProfile().getName());
		money = new JLabel(Integer.toString(player.getBankroll()));
		card1 = new CardComponent("NO CARD");
		card2 = new CardComponent("NO MOTHER FUCKING CARD");

		role = new JLabel(player.getRole().toString());

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
		card1.setText(player.getPocket().getArray()[0].toString());
		card2.setText(player.getPocket().getArray()[1].toString());
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}

		if(player.isFolded() || player.isDead())
		{
			setBackground(Color.red);
		}
		else
		{
			setBackground(null);
		}
		money.setText(Integer.toString(player.getBankroll()));
		role.setText(player.getRole().toString());
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
		box.add(role);
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
