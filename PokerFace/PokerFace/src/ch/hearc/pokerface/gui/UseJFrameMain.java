
package ch.hearc.pokerface.gui;

import java.io.IOException;

import javax.swing.UIManager;

import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.gamecore.SoundEngine;
import ch.hearc.pokerface.gameengine.statistics.Statistics;

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
		splashScreenLoading();

		new JFrameMain();
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void splashScreenLoading()
	{
		Statistics.initialize();
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
