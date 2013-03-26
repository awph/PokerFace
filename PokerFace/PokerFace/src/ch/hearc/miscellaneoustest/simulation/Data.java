
package ch.hearc.miscellaneoustest.simulation;


public class Data
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private int	nbWin;
	private int nbTime;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Data()
	{
		nbWin = 0;
		nbTime = 0;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void addWin()
	{
		nbWin++;
	}

	public void addTime()
	{
		nbTime++;
	}

	@Override
	public String toString()
	{
		return String.valueOf((double)nbWin/(double)nbTime*100);
	}

}

