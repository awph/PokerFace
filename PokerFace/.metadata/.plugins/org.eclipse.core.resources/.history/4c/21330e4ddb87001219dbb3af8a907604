
package ch.hearc.coursjava.kitbase.tableau.equationlineaire;

import com.bilat.tools.io.console.Clavier;

public class EquationLineaire
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void afficher(double[] tab, double x)
		{
		System.out.println(tab[0] + "x " + tab[1] + " = 0");
		System.out.println("x = " + x);
		}

	/**
	 * <pre>
	 * tab[0]=a
	 * tab[1]=b
	 *
	 * ax+b=0
	 * </pre>
	 */
	public static double[] saisirCoefficient()
		{
		double a;
		do
			{
			a = Clavier.lireDouble("a = ");
			} while(a == 0);

		double b = Clavier.lireDouble("b = ");
		double[] tab = new double[2];

		tab[0] = a;
		tab[1] = b;

		return tab;
		}

	public static double solve(double a, double b)
		{
		return -b / a;
		}

	/**
	 * <pre>
	 * tab[0] = <b>a</b>
	 * tab[1] = <i>b</i>
	 *
	 * ax+b=0
	 * </pre>
	 */
	public static double solve(double[] tab)
		{
		return solve(tab[0], tab[1]);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/



	}
