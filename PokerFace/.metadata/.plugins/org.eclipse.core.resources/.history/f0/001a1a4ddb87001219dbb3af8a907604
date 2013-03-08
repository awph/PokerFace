
package ch.hearc.coursjava.kitbase.consolidation.sysargs;

import ch.hearc.coursjava.kitbase.tableau.equationquadratique.EquationQuadratique;

public class UseEquationQuadratiqueSysARGS
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
		double a = Double.valueOf(System.getProperty("a"));
		double b = Double.valueOf(System.getProperty("b"));
		double c = Double.valueOf(System.getProperty("c"));

		double[] tabX = EquationQuadratique.solve(a, b, c);
		for(double xi:tabX)
			{
			System.out.println(xi);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
