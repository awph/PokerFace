
package ch.hearc.pokerface.generatePFvalues;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.cards.CardValue;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gameengine.subsets.Pocket;


public class UsePreFlopSim
{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
	{
		main();
	}

	public static void main()
	{
		int nbPlayer = 10;
		Profile profile = new Profile("test",1);
		GameEngine ge = new GameEngine(null,0,nbPlayer,profile,0,null);
		Pocket p = new Pocket();
		p.add(new Card(CardValue.Three,CardColor.Clubs));
		p.add(new Card(CardValue.Seven,CardColor.Hearts));

		Player player = new Player(profile,0, ge);
		player.addCard(new Card(CardValue.Three,CardColor.Clubs));

		long start = System.currentTimeMillis();
		player.addCard(new Card(CardValue.Seven,CardColor.Hearts));

		while(player.getPreFlopValues() == null){}

		System.out.println((System.currentTimeMillis()-start)/1000.0 + "s");
		System.out.println(player.getPocket());
		System.out.println(player.getPreFlopValues());
	}
}

