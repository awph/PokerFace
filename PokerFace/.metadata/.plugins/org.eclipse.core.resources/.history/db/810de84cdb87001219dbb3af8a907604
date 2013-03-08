
package ch.hearc.coursjava.poo.interfaces.heritagemultiple;

import java.util.Arrays;

public class UseEntreeSortie
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
		C c = new C();
		D d = new D();
		EntreeSortie_I[] tabEntreeSortie = new EntreeSortie_I[2];

		tabEntreeSortie[0] = c;
		tabEntreeSortie[1] = d;

		afficher(tabEntreeSortie);

		String[] tabSaisir = saisir(tabEntreeSortie);

		System.out.println(Arrays.toString(tabSaisir));

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void afficher(EntreeSortie_I[] tabEntreeSortie)
		{
		for(EntreeSortie_I entreeSortie:tabEntreeSortie)
			{
			entreeSortie.afficher();
			}
		}

	private static String[] saisir(EntreeSortie_I[] tabEntreeSortie)
		{
		String[] tabSaisir = new String[tabEntreeSortie.length];
		int i = 1;
		for(EntreeSortie_I entreeSortie:tabEntreeSortie)
			{
			tabSaisir[i - 1] = entreeSortie.saisir();
			++i;
			}
		return tabSaisir;
		}
	}
