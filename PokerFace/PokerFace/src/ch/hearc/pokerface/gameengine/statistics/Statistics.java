
package ch.hearc.pokerface.gameengine.statistics;

import java.util.Map;

import ch.hearc.pokerface.gameengine.cards.Card;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gameengine.statistics.simulation.Simulation;
import ch.hearc.pokerface.gameengine.subsets.Board;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class Statistics
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private final static String											FILENAME_XML		= "file";
	private final static Map<StateType, Map<String, StatisticValue>>[]	maps				= XMLReader.getXMLValue(FILENAME_XML);

	public final static int											NUMBER_CARDS_FLOP	= 3;
	public final static int											NUMBER_CARDS_TURN	= 4;
	public final static int											NUMBER_CARDS_RIVER	= 5;

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
		return maps[nbPlayer - XMLReader.NB_MIN_PLAYER].get(StateType.PreFlopState).get(p.getKey());
	}

	/**
	 * Return Flop/Turn/River values, depending of the size of the board : 3 -> Flop, 4 -> Tur, 5 -> River
	 *
	 * @param p
	 * @param b
	 * @param nbPlayer
	 * @return 0 -> Flop, 1 -> Turn, 2 -> River
	 * @return
	 */
	public static void getFlopTurnRiverValues(final Player player, final Pocket pocket, final Card[] board, int nbPlayer)
	{
		final Simulation flop = new Simulation(pocket, board, nbPlayer, NUMBER_CARDS_FLOP);
		final Simulation turn = new Simulation(pocket, board, nbPlayer, NUMBER_CARDS_TURN);
		final Simulation river = new Simulation(pocket, board, nbPlayer, NUMBER_CARDS_RIVER);

		flop.addObserver(player);
		turn.addObserver(player);
		river.addObserver(player);

		new Thread(flop).start();
		new Thread(turn).start();
		new Thread(river).start();
	}

	public static int getOuts(Pocket p, Board b)
	{
		return 0;
	}
}
