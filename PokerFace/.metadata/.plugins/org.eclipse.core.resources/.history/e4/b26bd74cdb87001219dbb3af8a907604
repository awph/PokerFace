
package ch.hearc.coursjava.poo.intro.quadratique;

import java.util.Arrays;

/**
 * Objectif : montrons qu'un type Enum est une classe.
 *
 */
public class UseEnum
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
		// Récupération de tous les champs de l'énumération.
		System.out.println(Arrays.toString(CoefficientType.values()));

		// Conversion String to CoefficientType.
		String a = "A";
		CoefficientType enumeration = CoefficientType.valueOf(a);
		System.out.println(enumeration);

		switch(enumeration)
			{
			case A:
				System.out.println("A = " + enumeration.name()); // Conversion CoefficientType to String.
				break;
			case B:
				System.out.println("B = " + enumeration.name());
				break;
			case C:
				System.out.println("C = " + enumeration.name());
				break;
			default:
				break;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
