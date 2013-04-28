
package ch.hearc.pokerface.gui;

import java.io.IOException;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.cards.CardValue;
import ch.hearc.pokerface.gameengine.statistics.Statistics;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class UseJFrameMain
{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args) throws IOException
	{
		main();
	}

	public static void main()
	{
		Pocket p = new Pocket();
		p.add(new Card(CardValue.Ace,CardColor.Spades));
		p.add(new Card(CardValue.Ace,CardColor.Diamonds));

		//Il faut précharger le XML !
		//TODO : Le faire pendant le splashscreen
		Statistics.getPreFlopValues(p, 2);
		new JFrameMain();
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}
