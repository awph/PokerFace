
package ch.hearc.coursjava.poo.intro.quadratique;

import java.util.Arrays;
import java.util.Map;


/**
 * Résolution dans les nombres réels
 */
public class Quadratique
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Quadratique(double a, double b, double c)
		{
		//Inputs
		this.a = a;
		this.b = b;
		this.c = c;
		//Output
		this.solve();
		}

	/**
	 * Clé : a ou b ou c
	 */
	public Quadratique(Map<CoefficientType, Double> mapABC)
		{
		this(mapABC.get(CoefficientType.A), mapABC.get(CoefficientType.B), mapABC.get(CoefficientType.C)); // Forcement à la premiere ligne
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		StringBuilder str = new StringBuilder();
		str.append(A);
		str.append(a);
		str.append(B);
		str.append(b);
		str.append(C);
		str.append(c);
		str.append(SOLUTION);
		str.append(Arrays.toString(tabX));

		return str.toString();
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isSoluble()
		{
		return tabX.length >= 1;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/**
	 * <pre>
	 * La taille du tableau dépent du nombre de solution
	 * En particulier si pas de solution la taille est 0
	 * </pre>
	 */
	public double[] getTabX()
		{
		return this.tabX;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void solve()
		{
		double discriminant = discriminant();

		if (discriminant < 0)
			{
				this.tabX = new double[0];
			}
		else if(discriminant == 0)
			{
			this.tabX = new double[1];
			this.tabX[0] = -b / (2 * a);
			}
		else
			{
			this.tabX = new double[2];
			this.tabX[0] = (-b + Math.sqrt(discriminant)) / (2 * a);
			this.tabX[1] = (-b - Math.sqrt(discriminant)) / (2 * a);
			}
		}

	private double discriminant()
		{
		return b*b - 4*a*c;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Inputs
	private double a;
	private double b;
	private double c;

	//outputs
	private double[] tabX;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final String A = "\na = ";
	private static final String B = "\nb = ";
	private static final String C = "\nc = ";
	private static final String SOLUTION = "\nsolution = ";
	}

