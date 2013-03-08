
package ch.hearc.coursjava.kitbase.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UseMap
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
		Map<String, Integer> map = new HashMap<String, Integer>();
		fill(map);

		afficherKey(map);
		afficherValeur(map);

		afficherAllSmart(map);
		afficherAllForeach(map);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void afficherAllForeach(Map<String, Integer> map)
		{
		Set<Entry<String, Integer>> entry = map.entrySet(); // Ensemble des lignes du dico
		for(Entry<String, Integer> ligne:entry)
			{
			String key = ligne.getKey();
			Integer valeur = ligne.getValue();
			System.out.println("Le chauffeur " + key + " a le numéro " + valeur);
			}
		}

	private static void afficherAllSmart(Map<String, Integer> map)
		{
		System.out.println(map);
		}

	private static void afficherValeur(Map<String, Integer> map)
		{
		System.out.println(map.values());
		}

	private static void afficherKey(Map<String, Integer> map)
		{
		System.out.println(map.keySet());
		}

	private static void fill(Map<String, Integer> map)
		{
		map.put("Racine", 24689);
		map.put("Müller", 12345);
		map.put("Chavaillaz", 2845);
		}

	}
