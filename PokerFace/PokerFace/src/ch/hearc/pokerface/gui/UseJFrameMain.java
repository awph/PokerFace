
package ch.hearc.pokerface.gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.cards.CardColor;
import ch.hearc.pokerface.gameengine.cards.CardValue;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.gamecore.SoundEngine;
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
		setupLookAndFeel();
	}

	public static void main()
	{
		splashScreenLoading();

		final JFrame fullscreenFrame = new JFrameMain();
		/*try
		{
			FullscreenSample.main(null);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(fullscreenFrame);

	}
	private final static class FullscreenSample {

	 	public static final void main(final String[] args) throws Exception {
	 		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	 		final JFrame fullscreenFrame = new JFrame();
	 		fullscreenFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	 		fullscreenFrame.setUndecorated(true);
	 		fullscreenFrame.setResizable(false);
	 		fullscreenFrame.add(new JLabel("Press ALT+F4 to exit fullscreen.", SwingConstants.CENTER), BorderLayout.SOUTH);
	 		fullscreenFrame.validate();

	 		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(fullscreenFrame);
	 	}

	 }
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void splashScreenLoading()
	{
		Pocket p = new Pocket();
		p.add(new Card(CardValue.Ace, CardColor.Spades));
		p.add(new Card(CardValue.Ace, CardColor.Diamonds));

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
