
package ch.hearc.coursjava.gui.j2d.traitTournant;

public class OptionAnimation
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public OptionAnimation(double dAlpha, long delayMS)
		{
		this.dAlpha = dAlpha;
		this.delayMS = delayMS;
		}

	public OptionAnimation()
		{
		this(D_ALPHA, DELAY_MS);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("OptionAnimation [dAlpha=");
		builder.append(this.dAlpha);
		builder.append(", delayMS=");
		builder.append(this.delayMS);
		builder.append("]");
		return builder.toString();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setdAlpha(double dAlpha)
		{
		this.dAlpha = dAlpha;
		}

	public void setDelayMS(long delayMS)
		{
		this.delayMS = delayMS;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public long getDelayMS()
		{
		return this.delayMS;
		}

	public double getdAlpha()
		{
		return this.dAlpha;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	// input / output
	private double dAlpha;
	private long delayMS;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final long DELAY_MS = 6;
	private static final double D_ALPHA = 2 * Math.PI / 360;

	}
