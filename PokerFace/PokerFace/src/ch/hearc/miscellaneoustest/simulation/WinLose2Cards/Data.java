
package ch.hearc.miscellaneoustest.simulation.WinLose2Cards;

import java.text.DecimalFormat;


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
		nbTime++;
	}

	public void addTime()
	{
		nbTime++;
	}

	@Override
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return String.valueOf(df.format((double)nbWin/(double)nbTime*100));
	}

}

