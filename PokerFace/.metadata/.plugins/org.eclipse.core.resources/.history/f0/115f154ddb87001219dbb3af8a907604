
package ch.hearc.coursjava.kitbase.excel;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class Histogramme
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * <pre>
	 * Exemple :
	 * xMin = 3, xMax = 4
	 * Output[0] = nbApparition de 3
	 * Output[1] = nbApparition de 4
	 *
	 * Contrainte xMin < xMax
	 * </pre>
	 */
	public static int[] create(int xMin, int xMax, int nbTime)
		{
		int[] histogramme = new int[xMax - xMin + 1];
		for(int i = 0; i < nbTime; i++)
			{
			int alea = MathTools.randomNumber(xMin, xMax); //in[xMin, xMax]
			histogramme[alea - xMin]++; // -xMin pour faire apparaître apparitions de xMin dans la case 0
			}
		return histogramme;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
