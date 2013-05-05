
package ch.hearc.pokerface.gameengine.gamecore;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import net.jsoundsystem.JSound;

public class SoundEngine
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private float				volume;
	private JSound				check;
	private JSound				call;
	private JSound				allin;
	private JSound				bet;
	private JSound				fold;
	private JSound				win;
	private JSound				lose;
	private JSound				raise;
	private JSound				yourturn;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static SoundEngine	instance;

	private static final String	PATH				= "resources/sounds/";

	private static final String	CHECK_FILENAME		= "check.wav";
	private static final String	CALL_FILENAME		= "call.wav";
	private static final String	ALLIN_FILENAME		= "allin.wav";
	private static final String	BET_FILENAME		= "bet.wav";
	private static final String	FOLD_FILENAME		= "fold.wav";
	private static final String	WIN_FILENAME		= "win.wav";
	private static final String	LOSE_FILENAME		= "lose.wav";
	private static final String	RAISE_FILENAME		= "raise.wav";
	private static final String	YOURTURN_FILENAME	= "yourturn.wav";

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private SoundEngine()
	{
		volume = 1f;

		try
		{
			check = new JSound(PATH + CHECK_FILENAME);
			call = new JSound(PATH + CALL_FILENAME);
			allin = new JSound(PATH + ALLIN_FILENAME);
			bet = new JSound(PATH + BET_FILENAME);
			fold = new JSound(PATH + FOLD_FILENAME);
			//win = new JSound(PATH + WIN_PATH);
			//lose = new JSound(PATH + LOSE_PATH);
			raise = new JSound(PATH + RAISE_FILENAME);
			yourturn = new JSound(PATH + YOURTURN_FILENAME);

			setVolume(volume);
		}
		catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void playSound(final Action action)
	{
		Thread thread = new Thread(new Runnable()
		{
			public void run()
			{
				switch(action)
				{
					case Bet:
					case PostSmallBlind:
					case PostBigBlind:
						playBet();
						break;
					case Raise:
						playRaise();
						break;
					case Fold:
						playFold();
						break;
					case Call:
						playCall();
						break;
					case Check:
						playCheck();
						break;
					case Allin:
						playAllin();
						break;
					case Winner:
						playWin();
						break;
					case Loser:
						playLose();
						break;
					case YourTurn:
						playYourTurn();
					default:
						break;
				}
			}
		});
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
	}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public synchronized static SoundEngine getInstance()
	{
		if (instance == null)
		{
			instance = new SoundEngine();
		}
		return instance;
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setVolume(float volume)
	{
		this.volume = volume;
		check.setVolume(volume);
		call.setVolume(volume);
		allin.setVolume(volume);
		bet.setVolume(volume);
		fold.setVolume(volume);
		//win.setVolume(volume);
		//lose.setVolume(volume);
		raise.setVolume(volume);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void playCheck()
	{
		check.play();
	}

	private void playCall()
	{
		call.play();
	}

	private void playAllin()
	{
		allin.play();
	}

	private void playBet()
	{
		bet.play();
	}

	private void playFold()
	{
		fold.play();
	}

	private void playWin()
	{
		//win.play();
	}

	private void playLose()
	{
		//lose.play();
	}

	private void playRaise()
	{
		raise.play();
	}

	private void playYourTurn()
	{
		yourturn.play();
	}

}
