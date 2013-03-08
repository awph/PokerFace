
package ch.hearc.coursjava.kitbase.consolidation.mapargs;

import java.util.HashMap;
import java.util.Map;


public class UseEquationQuadratiqueArgs
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * <pre>
	 * a=18 b=21 c=13
	 * Ordre sans importance, separateur espace
	 * </pre>
	 */
	public static void main(String[] args)
		{
		Map<String, Double> mapCoefficients = toMap(args);

		double[] tabX = EquationQuadratiqueMap.solve(mapCoefficients);
		EquationQuadratiqueMap.afficher(mapCoefficients, tabX);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static Map<String, Double> toMap(String[] tabAbc)
		{
		Map<String, Double> map = new HashMap<String, Double>();
		for(String elem:tabAbc)
			{
			String[] tabString = elem.split("=");
			String key = tabString[0];

			double value = Double.valueOf(tabString[1]);
			map.put(key, value);
			}
		return map;
		}
	}
