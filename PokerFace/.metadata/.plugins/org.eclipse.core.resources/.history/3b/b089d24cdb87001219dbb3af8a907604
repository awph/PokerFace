
package ch.hearc.coursjava.poo.thread.additionvectorielle;

import java.util.Arrays;

import junit.framework.Assert;

public class Additionneur implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Hyp : tab1 et tab2 sont de même longueur
	 */
	public Additionneur(long[] tab1, long[] tab2)
		{
		Assert.assertTrue(tab1.length == tab2.length);
		this.tab1 = tab1;
		this.tab2 = tab2;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		int n = n();
		tabResult = new long[n];

		if (Runtime.getRuntime().availableProcessors() > 1)
			{
			runParallele();
			}
		else
			{
			runSequentiel();
			}
		}

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Additionneur [tab1=");
		builder.append(Arrays.toString(this.tab1));
		builder.append(", tab2=");
		builder.append(Arrays.toString(this.tab2));
		builder.append(", tabResult=");
		builder.append(Arrays.toString(this.tabResult));
		builder.append("]");
		return builder.toString();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public long[] getTabResult()
		{
		return this.tabResult;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void runSequentiel()
		{
		int n = n();

		for(int i = 1; i <= n; i++)
			{
			tabResult[i - 1] = tab1[i - 1] + tab2[i - 1];
			}
		}

	private void runParallele()
		{
		int n = n();
		int m = n / 2;

		Interval interval1 = new Interval(1, m);
		Interval interval2 = new Interval(m + 1, n);

		Thread threadInterval1 = new Thread(runnable(interval1));
		Thread threadInterval2 = new Thread(runnable(interval2));

		threadInterval1.start();
		threadInterval2.start();

		try
			{
			threadInterval1.join();
			threadInterval2.join();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	private Runnable runnable(final Interval interval)
		{
		return new Runnable()
			{

				@Override
				public void run()
					{
					for(int i = interval.getiStart(); i <= interval.getiStop(); i++)
						{
						tabResult[i - 1] = tab1[i - 1] + tab2[i - 1];
						}
					}
			};
		}

	private int n()
		{
		return tab1.length;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Inputs
	private long[] tab1;
	private long[] tab2;

	//Outputs
	private long[] tabResult;

	}
