
package ch.hearc.miscellaneoustest.simulation.Combinaison2Cards;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Data
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Map<String, Integer>	map;
	private int						nbTime;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Data()
	{
		nbTime = 0;
		map = new HashMap<String, Integer>();
		map.put("SF", 0);
		map.put("4K", 0);
		map.put("FH", 0);
		map.put("F", 0);
		map.put("S", 0);
		map.put("3K", 0);
		map.put("2P", 0);
		map.put("1P", 0);
		map.put("HC", 0);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void addTime(String key)
	{
		nbTime++;
		map.put(key, map.get(key) + 1);
	}

	public Set<Entry<String, Integer>> getEntrySet()
	{
		return map.entrySet();
	}

	public int getTotal()
	{
		return nbTime;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Data [map=");
		builder.append(this.map);
		builder.append("]");
		return builder.toString();
	}

}
