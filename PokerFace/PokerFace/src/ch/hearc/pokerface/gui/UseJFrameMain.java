
package ch.hearc.pokerface.gui;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.cards.CardValue;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.gamecore.SoundEngine;
import ch.hearc.pokerface.gameengine.statistics.Statistics;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

import javax.swing.*;
import java.io.IOException;

public class UseJFrameMain
{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public static void main(String[] args) throws IOException
	{
		main();
		setupLookAndFeel();
	}
	
	public static void main()
	{
		//splashScreenLoading();
		
		new JFrameMain();
	}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private static void splashScreenLoading()
	{
		Pocket p = new Pocket();
		p.add(new Card(CardValue.Ace, CardColor.Spades));
		p.add(new Card(CardValue.Ace, CardColor.Diamonds));
		
		//Il faut précharger le XML !
		//TODO : Le faire pendant le splashscreen
		Statistics.getPreFlopValues(p, 2);
		SoundEngine.getInstance();
		HandsPokerMap.getInstance();
	}
	
	private static void setupLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
