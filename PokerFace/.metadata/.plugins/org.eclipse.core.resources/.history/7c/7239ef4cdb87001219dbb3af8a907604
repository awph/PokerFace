
package ch.hearc.coursjava.poo.interfaces.hello;

import java.util.ArrayList;
import java.util.List;

import ch.hearc.coursjava.poo.derivation.animal.Chat;

public class UseAfficher
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
		A a1 = new A();
		Afficher_I affichable1 = new A();
		Afficher_I affichable2 = new B();
		Chat tom = new Chat("Tom");

		List<Afficher_I> listAffichable = new ArrayList<Afficher_I>();
		listAffichable.add(affichable1);
		listAffichable.add(affichable2);
		listAffichable.add(tom);
		listAffichable.add(a1);

		afficher(listAffichable);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private static void afficher(List<Afficher_I> listAffichable)
		{
		for(Afficher_I elem:listAffichable)
			{
			elem.afficher();
			}
		}
	}
