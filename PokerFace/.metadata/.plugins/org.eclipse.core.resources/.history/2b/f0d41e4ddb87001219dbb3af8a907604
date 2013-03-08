
package ch.hearc.coursjava.kitbase.collection.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * set : unicite des elements
 */
public class UseSet
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
		Set<Integer> set = new HashSet<Integer>();

		fill(set, 10); // size avant : 0 size apres  10
		afficherToString(set);
		fill(set, 10); // size avant : 10 size apres toujours 10

		afficherToString(set);
		afficherForEach(set);
		afficherIterator(set);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void afficherIterator(Set<Integer> set)
		{
		Iterator<Integer> it = set.iterator();
		while(it.hasNext())
			{
			Integer elem = it.next();
			System.out.println(elem);
			}
		}

	private static void afficherForEach(Set<Integer> set)
		{
		for(Integer elem:set)
			{
			System.out.println(elem);
			}
		}

	private static void afficherToString(Set<Integer> set)
		{
		System.out.println(set);
		}

	private static void fill(Set<Integer> set, int n)
		{
		for(int i = 1; i <= n; i++)
			{
			set.add(i);
			}
		}

	}
