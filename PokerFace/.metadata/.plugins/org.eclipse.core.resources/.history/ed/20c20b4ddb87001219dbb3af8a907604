
package ch.hearc.coursjava.kitbase.tableau.equationquadratique;

import com.bilat.tools.io.console.Clavier;

public class EquationQuadratique
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * <pre>
	 * tab[0] = <b>a</b>
	 * tab[1] = <b>b</b>
	 * tab[2] = <b>c</b>
	 *
	 * ax2+bx+c=0
	 * </pre>
	 */
	public static void afficher(double[] tabCoefficients, double[] tabX)
		{
		System.out.println(tabCoefficients[0] + "x2 " + tabCoefficients[1] + "x + " + tabCoefficients[2] + " = 0");

		if (tabX.length == 0)
			{
			System.out.println("Aucune solution");
			}
		else if (tabX.length == 1)
			{
			System.out.println("x = " + tabX[0]);
			}
		else
			{
			System.out.println("x1 = " + tabX[0]);
			System.out.println("x2 = " + tabX[1]);
			}
		}

	/**
	 * <pre>
	 * tab[0] = <b>a</b>
	 * tab[1] = <b>b</b>
	 * tab[2] = <b>c</b>
	 *
	 * ax2+bx+c=0
	 * </pre>
	 */
	public static void afficherV2(double[] tabCoefficients, double[] tabX)
		{
		int i = 1;
		for(double ai:tabCoefficients)
			{
			System.out.println(ai + "x" + i + " ");
			i++;
			}

		if (isSolution(tabX))
			{
			i = 1;
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

	/**
	 * <pre>
	 * tab[0] = <b>a</b>
	 * tab[1] = <b>b</b>
	 * tab[2] = <b>c</b>
	 *
	 * ax2+bx+c=0
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
		double c = Clavier.lireDouble("c = ");

		return new double[] { a, b, c };
		}

	/**
	 * <pre>
	 * tab[0] = <b>a</b>
	 * tab[1] = <b>b</b>
	 * tab[2] = <b>c</b>
	 *
	 * @return length tab depend du nombre de solution
	 * </pre>
	 */
	public static double[] solve(double[] tab)
		{
		return solve(tab[0], tab[1], tab[2]);
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
