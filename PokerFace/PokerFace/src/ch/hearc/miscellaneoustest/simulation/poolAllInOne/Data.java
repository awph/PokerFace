
package ch.hearc.miscellaneoustest.simulation.poolAllInOne;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Data
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private int						nbWin;
	private int						nbTime;
	private Map<String, Integer>	map;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Data()
	{
		nbWin = 0;
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

	public void addWin()
	{
		nbWin++;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Data [map=");
		builder.append(this.map);
		builder.append("]");
		DecimalFormat df = new DecimalFormat("#.##");
		return String.valueOf(df.format((double)nbWin / (double)nbTime * 100)) + "\n" + builder.toString();
	}

}
