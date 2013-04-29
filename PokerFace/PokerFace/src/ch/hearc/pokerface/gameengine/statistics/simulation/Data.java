
package ch.hearc.pokerface.gameengine.statistics.simulation;

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
	private int						nbTie;
	private int						nbLoss;
	private int						nbTime;
	private int						nbOpponantWinner;
	private Map<String, Integer>	map;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Data()
	{
		nbWin = 0;
		nbTie = 0;
		nbLoss = 0;
		nbTime = 0;
		nbOpponantWinner = 0;
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

	/**
	 * Add another data to the current one
	 * @param d2 : Other data
	 */
	public void union(Data d2)
	{
		nbWin += d2.getNumberOfWin();
		nbLoss += d2.getNumberOfLoss();
		nbTie += d2.getNumberOfTie();
		nbTime += d2.getNbTime();
		nbOpponantWinner += d2.getOpponantWinner();
		map.put("SF", map.get("SF") + d2.map.get("SF"));
		map.put("4K", map.get("4K") + d2.map.get("4K"));
		map.put("FH", map.get("FH") + d2.map.get("FH"));
		map.put("F", map.get("F") + d2.map.get("F"));
		map.put("S", map.get("S") + d2.map.get("S"));
		map.put("3K", map.get("3K") + d2.map.get("3K"));
		map.put("2P", map.get("2P") + d2.map.get("2P"));
		map.put("1P", map.get("1P") + d2.map.get("1P"));
		map.put("HC", map.get("HC") + d2.map.get("HC"));
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Data [map=");
		builder.append(this.map);
		builder.append("]");
		DecimalFormat df = new DecimalFormat("#.##");
		return String.valueOf(df.format(getWinPercentage()) + "\n" + builder.toString());
	}

	public void addTime(String key)
	{
		nbTime++;
		map.put(key, map.get(key) + 1);
	}

	public void addWin()
	{
		nbWin++;
	}

	public void addLoss()
	{
		nbLoss++;
	}

	public void addTie()
	{
		nbTie++;
	}

	public void addWinnerOpponant(int n)
	{
		nbOpponantWinner += n;
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Set<Entry<String, Integer>> getEntrySet()
	{
		return map.entrySet();
	}

	public Map<String, Integer> getMap()
	{
		return map;
	}

	public int getNumberOfWin()
	{
		return nbWin;
	}

	public int getNumberOfLoss()
	{
		return nbLoss;
	}

	public int getNumberOfTie()
	{
		return nbTie;
	}

	public int getNbTime()
	{
		return nbTime;
	}

	public int getOpponantWinner()
	{
		return nbOpponantWinner;
	}

	public double getWinPercentage()
	{
		return (double)nbWin / (double)nbTime * 100;
	}

	public double getLossPercentage()
	{
		return (double)nbLoss / (double)nbTime * 100;
	}

	public double getTiePercentage()
	{
		return (double)nbTie / (double)nbTime * 100;
	}

	public double getAverageOpponantWinner()
	{
		return nbOpponantWinner / ((double)nbLoss + (double)nbTie);
	}
}
