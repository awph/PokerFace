
package ch.hearc.pokerface.gameengine.statistics;

import java.util.Map;

import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.Pocket;


public class Statistics
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private final static String FILENAME_XML = "file";
	private final static Map<StateType,Map<String,StatisticValue>>[] maps = XMLReader.getXMLValue(FILENAME_XML);


	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public static StatisticValue getPreFlopValues(Pocket p, int nbPlayer)
	{
		return maps[nbPlayer-XMLReader.NB_MIN_PLAYER].get(StateType.PreFlopState).get(p.getKey());
	}

	public static StatisticValue getFlopValues(Pocket p, Board b, int nbPlayer)
	{
		return new StatisticValue(0,0,0,0,0,0,0,0,0,0);
	}

	public static StatisticValue getTurnValues(Pocket p, Board b, int nbPlayer)
	{
		return new StatisticValue(0,0,0,0,0,0,0,0,0,0);
	}

	public static StatisticValue getRiverValues(Pocket p, Board b, int nbPlayer)
	{
		return new StatisticValue(0,0,0,0,0,0,0,0,0,0);
	}

	public static int getOuts(Pocket p, Board b)
	{
		return 0;
	}
}

