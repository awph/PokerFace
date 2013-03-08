
package ch.hearc.coursjava.kitbase.tools;

public class MathTools
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static int alea0N(int n)
		{
		double x01 = Math.random();
		return (int)((n - 1) * x01 + 1);
		}

	public static int alea1N(int n)
		{
		return randomNumber(1, n);
		}

	public static int randomNumber(final int MIN, final int MAX)
		{
		return MIN + (int)(Math.random() * ((MAX - MIN) + 1));
		}

	public static boolean isEgal(double a, double b)
		{
		return isEgal(a, b, 1e-4);
		}

	public static boolean isEgal(double a, double b, double epsilon)
		{
		if (a != 0 && b != 0)
			{
			return Math.abs((a - b) / a) < epsilon;
			}
		else
			{
			return Math.abs(a - b) < epsilon;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
