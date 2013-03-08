
package ch.hearc.coursjava.kitbase.consolidation.de;

import java.util.HashSet;
import java.util.Set;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class UseDes
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		long timeStart = System.currentTimeMillis();
		final int PRECISION = 100000, MIN = 1, MAX = 600;
		double result = dice(PRECISION, MIN, MAX);
		long timeStop = System.currentTimeMillis();

		System.out.println("For " + PRECISION + " tests, the average is : " + result);
		System.out.println("Ceil : " + Math.ceil(result));
		System.out.println("Time : " + (timeStop - timeStart));

		}

	//1) Precision : 14.700030182348579 for 2147483647 tests
	//2) Precision : 14.700203942926695 for 2147483647 tests
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static double dice(final int NB_EXPERIENCE, final int MIN, final int MAX)
		{
		int nbExperience = NB_EXPERIENCE, i = 0;
		int sizeSetFull = MAX - MIN + 1;
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
	}
