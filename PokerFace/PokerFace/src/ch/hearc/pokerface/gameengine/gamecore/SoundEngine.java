
package ch.hearc.pokerface.gameengine.gamecore;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEngine
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private float				volume;
	private Clip				check;
	private Clip				call;
	private Clip				allin;
	private Clip				bet;
	private Clip				fold;
	private Clip				win;
	private Clip				lose;
	private Clip				raise;
	private Clip				yourturn;
	
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
			// Set up an audio input stream piped from the sound file.
			AudioInputStream aisCheck = AudioSystem.getAudioInputStream(new File(PATH + CHECK_FILENAME)); //TODO from jar
			AudioInputStream aisCall = AudioSystem.getAudioInputStream(new File(PATH + CALL_FILENAME)); //TODO from jar
			AudioInputStream aisAllin = AudioSystem.getAudioInputStream(new File(PATH + ALLIN_FILENAME)); //TODO from jar
			AudioInputStream aisBet = AudioSystem.getAudioInputStream(new File(PATH + BET_FILENAME)); //TODO from jar
			AudioInputStream aisFold = AudioSystem.getAudioInputStream(new File(PATH + FOLD_FILENAME)); //TODO from jar
			//AudioInputStream aisWin = AudioSystem.getAudioInputStream(new File(PATH + WIN_PATH)); //TODO from jar
			//AudioInputStream aisLose = AudioSystem.getAudioInputStream(new File(PATH + LOSE_PATH)); //TODO from jar
			AudioInputStream aisRaise = AudioSystem.getAudioInputStream(new File(PATH + RAISE_FILENAME)); //TODO from jar
			AudioInputStream aisYourTurn = AudioSystem.getAudioInputStream(new File(PATH + YOURTURN_FILENAME)); //TODO from jar
			
			// Get a clip resource.
			check = AudioSystem.getClip();
			call = AudioSystem.getClip();
			allin = AudioSystem.getClip();
			bet = AudioSystem.getClip();
			fold = AudioSystem.getClip();
			win = AudioSystem.getClip();
			lose = AudioSystem.getClip();
			raise = AudioSystem.getClip();
			yourturn = AudioSystem.getClip();
			
			// Open audio clip and load samples from the audio input stream.
			check.open(aisCheck);
			call.open(aisCall);
			allin.open(aisAllin);
			bet.open(aisBet);
			fold.open(aisFold);
			//win.open(aisWin);
			//lose.open(aisLose);
			raise.open(aisRaise);
			yourturn.open(aisYourTurn);
			
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
		catch (LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void playSound(final Action action)
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
		float max = ((FloatControl)check.getControl(FloatControl.Type.MASTER_GAIN)).getMaximum();
		float min = ((FloatControl)check.getControl(FloatControl.Type.MASTER_GAIN)).getMinimum();
		
		volume = todB(volume);
		if (volume > max)
		{
			volume = max;
		}
		if (volume < min)
		{
			volume = min;
		}
		
		this.volume = volume;
		((FloatControl)check.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
		((FloatControl)call.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
		((FloatControl)allin.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
		((FloatControl)bet.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
		((FloatControl)fold.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
		//((FloatControl)win.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
		//((FloatControl)lose.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
		((FloatControl)raise.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
	}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private float todB(float percent)
	{
		return (float)(20 * Math.log10((percent + 10e-6) / 80) + 4.08231);
	}
	
	private void playCheck()
	{
		if (check.isRunning())
		{
			check.stop();
		}
		check.setFramePosition(0);
		check.start();
	}
	
	private void playCall()
	{
		if (call.isRunning())
		{
			call.stop();
		}
		call.setFramePosition(0);
		call.start();
	}
	
	private void playAllin()
	{
		if (allin.isRunning())
		{
			allin.stop();
		}
		allin.setFramePosition(0);
		allin.start();
	}
	
	private void playBet()
	{
		if (bet.isRunning())
		{
			bet.stop();
		}
		bet.setFramePosition(0);
		bet.start();
	}
	
	private void playFold()
	{
		if (fold.isRunning())
		{
			fold.stop();
		}
		fold.setFramePosition(0);
		fold.start();
	}
	
	private void playWin()
	{
		if (win.isRunning())
		{
			win.stop();
		}
		win.setFramePosition(0);
		win.start();
	}
	
	private void playLose()
	{
		if (lose.isRunning())
		{
			lose.stop();
		}
		lose.setFramePosition(0);
		lose.start();
	}
	
	private void playRaise()
	{
		if (raise.isRunning())
		{
			raise.stop();
		}
		raise.setFramePosition(0);
		raise.start();
	}
	
	private void playYourTurn()
	{
		if (yourturn.isRunning())
		{
			yourturn.stop();
		}
		yourturn.setFramePosition(0);
		yourturn.start();
	}
	
}
