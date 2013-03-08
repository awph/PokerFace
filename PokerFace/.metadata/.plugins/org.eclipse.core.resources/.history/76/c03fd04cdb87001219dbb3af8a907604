
package ch.hearc.coursjava.poo.thread.de;

import java.util.HashSet;
import java.util.Set;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class SimulationRollDice implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public SimulationRollDice(int nbExperience, int min, int max)
		{
		//Inputs
		this.nbExperience = nbExperience;
		this.min = min;
		this.max = max;

		//Tools
		this.nbCore = Runtime.getRuntime().availableProcessors();
		this.tabResultThread = new double[nbCore];
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		Thread[] tabThread = new Thread[nbCore];
		for(int i = 0; i < nbCore; i++)
			{
			tabThread[i] = new Thread(runnable(i, nbExperiencePerThread(i)));
			}

		for(int i = 0; i < nbCore; i++)
			{
			tabThread[i].start();
			}

		try
			{
			for(int i = 0; i < nbCore; i++)
				{
				tabThread[i].join();
				}
			consolidation();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}

		}

	private int nbExperiencePerThread(int indexThread)
		{
		final int nbExperiencePerThread;
		if (indexThread < (nbCore - 1))
			{
			nbExperiencePerThread = nbExperience / nbCore;
			}
		else
			{
			nbExperiencePerThread = nbExperience / nbCore + nbExperience % nbCore;
			}
		//System.out.println(nbExperiencePerThread);
		return nbExperiencePerThread;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public double getAverage()
		{
		return average;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private Runnable runnable(final int index, final int nbExperiencePerThread)
		{
		return new Runnable()
			{

				@Override
				public void run()
					{
					tabResultThread[index] = dice(nbExperiencePerThread, min, max);
					}
			};
		}

	private void consolidation()
		{
		double sum = 0;
		for(double resultatThread:tabResultThread)
			{
			sum += resultatThread;
			}
		average = sum/nbCore;
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static double dice(final int NB_EXPERIENCE, final int MIN, final int MAX)
		{
		int nbExperience = NB_EXPERIENCE, i = 0;
		int sizeSetFull = MAX-MIN+1;
		//System.out.println("Exp : " + nbExperience);
		Set<Integer> set = new HashSet<Integer>();
		while(nbExperience-- > 0)
			{
			while(set.size() != sizeSetFull)
				{
				set.add(MathTools.randomNumber(MIN, MAX));
				i++;
				}
			set.clear();
			}
		return i / (double)NB_EXPERIENCE;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private double[] tabResultThread;
	private int nbCore;

	//Inputs
	private int nbExperience;
	private int min;
	private int max;

	//Outputs
	private double average;
	}
