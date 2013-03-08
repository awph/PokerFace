
package ch.hearc.coursjava.poo.interfaces.trie;

import java.util.Comparator;

public class HommesComparator implements Comparator<Homme>
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

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

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
