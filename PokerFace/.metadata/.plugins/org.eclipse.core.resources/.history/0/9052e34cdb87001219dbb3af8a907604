
package ch.hearc.coursjava.poo.intro.chrono;

public class Chrono
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Chrono()
		{
		this.start();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void start()
		{
		timeStart = System.currentTimeMillis();
		}

	public long stop()
		{
		timeStop = System.currentTimeMillis();
		return timeStop - timeStart;
		}

	@Override
	public String toString()
		{
		long delta = 0;
		if (timeStop == 0)
			{
			delta = System.currentTimeMillis() - timeStart;
			}
		else
			{
			delta = timeStop - timeStart;
			}
		return delta + "ms";
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private long timeStart;
	private long timeStop;
	}
