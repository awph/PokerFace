
package ch.hearc.pokerface.gameengine.statistics;

public class Odds implements Comparable<Odds>
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Input
	private int		favorable;
	private int		defavorable;

	//Tools
	private double	win;
	private double	loss;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Odds(int favorable, int defavorable)
	{
		this.favorable = favorable;
		this.defavorable = defavorable;

		win = favorable / (favorable + defavorable) * 100.0;
		loss = 100.0 - win;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public int compareTo(Odds o)
	{
		if (favorable == o.favorable && defavorable == o.defavorable)
		{
			return 0;
		}
		else if (win > o.win)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("");
		builder.append(this.defavorable);
		builder.append(" : ");
		builder.append(this.defavorable);
		builder.append(" -> Win : ");
		builder.append(this.win);
		builder.append(", Loss : ");
		builder.append(this.loss);
		return builder.toString();
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public double getWin()
	{
		return this.win;
	}

	public double getLoss()
	{
		return this.loss;
	}

}
