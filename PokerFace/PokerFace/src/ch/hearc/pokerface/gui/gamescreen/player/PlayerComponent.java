
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
	private JLabel name;
	private JLabel money;
	private Player player;
	private CardComponent card1;
	private CardComponent card2;
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public PlayerComponent(Player player)
	{
		this.player = player;
		this.name = new JLabel(player.getProfile().getName());
		this.money = new JLabel(player.getBankroll() + "");

		//TODO player.getPocket().getArray(), au début sera null
		card1 = new CardComponent("AsPique");
		card2 = new CardComponent("AsTrefle");

		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
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
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
