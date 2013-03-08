
package ch.hearc.coursjava.poo.interfaces.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UseHomme
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
		List<Homme> listeHommes = new ArrayList<Homme>();

		Homme homme1 = new Homme(1, "c");
		Homme homme2 = new Homme(2, "b");
		Homme homme3 = new Homme(3, "a");
		Homme homme4 = new Homme(1, "a");
		Homme homme5 = new Homme(8, "c");

		listeHommes.add(homme1);
		listeHommes.add(homme2);
		listeHommes.add(homme3);
		listeHommes.add(homme4);
		listeHommes.add(homme5);

		//v1
		//HommesComparator hommescomparator = new HommesComparator();
		//Collections.sort(listeHommes, hommescomparator);

		//v2
		//Collections.sort(listeHommes); // BAD! Impossible de trier une fois par ordre croissant, et une autre fois par ordre décroissant

		//v3 : BEST! La meilleure solution si le code à implémenter est court, sinon, V1 est meilleur.
		Collections.sort(listeHommes, new Comparator<Homme>()
			{

				@Override
				public int compare(Homme h1, Homme h2)
					{
					if (!h1.getNom().equals(h2.getNom()))
						{
						return h1.getNom().compareTo(h2.getNom());
						}
					else
						{
						return Double.compare(h1.getTaille(), h2.getTaille());
						}
					}
			});
		afficher(listeHommes);

		//v3bis
		Collections.sort(listeHommes, new Comparator<Homme>()
			{

				@Override
				public int compare(Homme h1, Homme h2)
					{
					if (!h1.getNom().equals(h2.getNom()))
						{
						return -1 * h1.getNom().compareTo(h2.getNom());
						}
					else
						{
						return -1 * Double.compare(h1.getTaille(), h2.getTaille());
						}
					}
			});

		afficher(listeHommes);

		//v3bisbis
		Collections.sort(listeHommes, comparatorCroissantDecroissant());
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static Comparator<Homme> comparatorCroissantDecroissant()
		{
		return new Comparator<Homme>()
			{

				@Override
				public int compare(Homme h1, Homme h2)
					{
					if (!h1.getNom().equals(h2.getNom()))
						{
						return h1.getNom().compareTo(h2.getNom());
						}
					else
						{
						return -1 * Double.compare(h1.getTaille(), h2.getTaille());
						}
					}
			};
		}

	private static void afficher(List<Homme> listHommes)
		{
		for(Homme homme:listHommes)
			{
			homme.afficher();
			}
		}

	}
