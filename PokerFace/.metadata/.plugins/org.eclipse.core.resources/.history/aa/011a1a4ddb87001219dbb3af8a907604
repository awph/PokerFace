
package ch.hearc.coursjava.kitbase.consolidation.mapargs;

import java.util.HashMap;
import java.util.Map;

import com.bilat.tools.io.console.Clavier;

public class EquationQuadratiqueMap
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void afficher(Map<String, Double> mapCoefficients, double[] tabX)
		{
		System.out.println(mapCoefficients);

		if (isSolution(tabX))
			{
			int i = 1;
			for(double x:tabX)
				{
				System.out.println("x" + i + " = " + x);
				i++;
				}
			}
		else
			{
			System.out.println("Aucune solution");
			}
		}

	public static Map<String, Double> saisirCoefficient()
		{
		Map<String, Double> map = new HashMap<String, Double>();
		double a;
		do
			{

			a = Clavier.lireDouble();
			} while(a == 0);
		double b = Clavier.lireDouble("b = ");
		double c = Clavier.lireDouble("c = ");

		map.put("a", a);
		map.put("b", b);
		map.put("c", c);

		return map;
		}

	/**
	 * <pre>
	 * KeyMap : a b c
	 * </pre>
	 */
	public static double[] solve(Map<String, Double> map)
		{
		return solve(map.get("a"), map.get("b"), map.get("c"));
		}

	public static double[] solve(double a, double b, double c)
		{
		double discrimant = discriminant(a, b, c);
		if (discrimant < 0)
			{
			return new double[0];
			}
		else if (discrimant == 0)
			{
			return new double[] { -b / (2 * a) };
			}
		else
			{
			double[] tab = new double[2];
			tab[0] = (-b + Math.sqrt(discrimant)) / (2 * a);
			tab[1] = (-b - Math.sqrt(discrimant)) / (2 * a);
			return tab;
			}
		}

	public static double discriminant(double a, double b, double c)
		{
		return b * b - (4 * a * c);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static boolean isSolution(double[] tabX)
		{
		return tabX.length > 0;
		}

	}
