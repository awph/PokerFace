
package ch.hearc.coursjava.gui.j2d.traitTournant;

import junit.framework.Assert;

public class Interval
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Hyp : iStart <= iStop
	 */
	public Interval(double iStart, double iStop)
		{
		Assert.assertTrue(iStart <= iStop);
		this.iStart = iStart;
		this.iStop = iStop;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public double getiStart()
		{
		return this.iStart;
		}

	public double getiStop()
		{
		return this.iStop;
		}

	public double getMilieu()
		{
		return (iStop + iStart) / 2;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Inputs
	private double iStart;
	private double iStop;

	}
