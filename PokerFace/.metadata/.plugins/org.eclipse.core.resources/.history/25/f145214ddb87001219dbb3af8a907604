
package ch.hearc.coursjava.kitbase.collection.liste;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Assert;

public class UseListe
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
		useListIntro();
		useListBiIndice();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void useListIntro()
		{
		List<String> list = new ArrayList<String>();

		list.add("test");
		list.add("test2");
		list.add("test3");

		afficherIterator(list);
		afficherForeach(list);
		afficherSmart(list);
		afficherBad(list);
		}

	private static void useListBiIndice()
		{
		final int N = 5; // marcherait aussi si pas constant

		List<Integer> listX = new ArrayList<Integer>();
		List<Integer> listY = new ArrayList<Integer>();

		fill(listX, N);
		fill(listY, N);
		//listY.add(12); // Test des assertions des methodes afficher

		afficherBiListV1(listX, listY);
		afficherBiListV2(listX, listY);
		}

	/**
	 * hyp : listX.size() == listY.size()
	 */
	private static void afficherBiListV2(List<Integer> listX, List<Integer> listY)
		{
		Assert.assertTrue(listX.size() == listY.size());

		ListIterator<Integer> itX = listX.listIterator();
		for(Integer y:listY)
			{
			int x = itX.next();

			System.out.println(toStringSmart(x,y));
			}
		}

	/**
	 * Goal : Minimiser les instanciations de String
	 */
	private static String toStringSmart(int x,int y)
		{
		StringBuilder str = new StringBuilder(); // alterable (alors que String est inalterable)

		str.append(ENTETE);
		str.append(x);
		str.append(VIRGULE);
		str.append(y);
		str.append(FIN);

		return str.toString();
		}

	/**
	 * hyp : listX.size() == listY.size()
	 */
	private static void afficherBiListV1(List<Integer> listX, List<Integer> listY)
		{
		Assert.assertTrue(listX.size() == listY.size());

		ListIterator<Integer> itX = listX.listIterator();
		ListIterator<Integer> itY = listY.listIterator();

		while(itX.hasNext())
			{
			Integer x = itX.next();
			Integer y = itY.next();

			toStringSmart(x,y);
			}
		}

	private static void fill(List<Integer> list, int n)
		{
		for(int i = 1; i <= n; i++)
			{
			//list.add(i); // Juste ! Syntaxe simplifiée (Java >= 1.5)
			list.add(new Integer(i));
			}
		}


	/**
	 * Complexité quadratique, ne jamais employer un get dans une boucle
	 */
	private static void afficherBad(List<String> list)
		{
		for(int i = 1; i <= list.size(); i++)
			{
			System.out.println(list.get(i - 1));
			}
		}

	private static void afficherSmart(List<String> list)
		{
		System.out.println(list);
		}

	private static void afficherForeach(List<String> list)
		{
		for(String elem:list)
			{
			System.out.println(elem);
			}
		}

	private static void afficherIterator(List<String> list)
		{
		ListIterator<String> it = list.listIterator();
		while(it.hasNext())
			{
			String elem = it.next();
			System.out.println(elem);
			}
		}


	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	// Instancier une seule fois pendant toute la durée de vie du programme
	private final static String ENTETE = "(x,y) = (";
	private final static String VIRGULE = ",";
	private final static String FIN = ")";


	}
