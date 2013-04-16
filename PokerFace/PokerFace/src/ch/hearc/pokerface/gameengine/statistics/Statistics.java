
package ch.hearc.pokerface.gameengine.statistics;

import java.util.Map;

import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.statistics.simulation.Simulation;
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

	/**
	 * Return Flop/Turn/River values, depending of the size of the board : 3 -> Flop, 4 -> Tur, 5 -> River
	 * @param p
	 * @param b
	 * @param nbPlayer
	 * @return
	 */
	public static StatisticValue getFlopOrTurnOrRiverValues(Pocket p, Board b, int nbPlayer)
	{
		Simulation sm = new Simulation(p, b, nbPlayer);
		return sm.run();
	}


	public static int getOuts(Pocket p, Board b)
	{
		return 0;
	}
}

