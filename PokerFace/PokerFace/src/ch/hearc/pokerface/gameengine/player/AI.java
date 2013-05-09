
package ch.hearc.pokerface.gameengine.player;

import ch.hearc.pokerface.gameengine.gamecore.Action;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.gamecore.Pot;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gameengine.statistics.Odds;
import ch.hearc.pokerface.gameengine.statistics.StatisticValue;
import ch.hearc.pokerface.gameengine.statistics.Statistics;
import ch.hearc.pokerface.gameengine.subsets.Deck;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class AI extends Player
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private final static long	MAXIMUM_TIME_TO_PLAY	= 2000; //ms
	private final static long	MINIMUM_TIME_TO_PLAY	= 1000;	//ms
	private final static long	TIME_BETWEEN_EACH_LOOP	= 100;	//ms;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AI(Profile profile, int bankroll, GameEngine gameEngine)
	{
		super(profile, bankroll, gameEngine);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void doAction()
	{
		/*try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		check();*/
		level1();
	}

	private void level1()
	{
		try
		{
			long start = System.currentTimeMillis();
			StatisticValue sv = null;
			while(sv == null)
			{
				switch(gameEngine.getUnorderedBoard().length)
				{
					case 0:
						sv = svPreFlop;
						break;
					case 3:
						sv = svFlop;
						break;
					case 4:
						sv = svTurn;
						break;
					case 5:
						sv = svRiver;
						break;
				}
				if (sv == null)
				{
					Thread.sleep(TIME_BETWEEN_EACH_LOOP);
					System.out.println("wait");
				}
			}

			Action action;
			if (getCallValue() == 0)
			{
				action = Action.Check;
			}
			else if (gameEngine.getUnorderedBoard().length == 0)//PreFlop
			{
				double win = sv.getWin();
				int rand = (int)(Math.random() * 100);
				if (rand < win)
				{
					action = Action.Call;
				}
				else
				{
					action = Action.Fold;
				}
			}
			else
			{
				Pot pot = gameEngine.getPot();
				Odds potOdds = new Odds(getCallValue(), pot.getTurnTotal() + getCallValue());
				int nbCardsInDeck = Deck.NB_CARD_DECK - gameEngine.getNbPlayers() * Pocket.NUMBER_OF_CARDS - gameEngine.getUnorderedBoard().length;
				int nbOuts = Statistics.getOuts(pocket, gameEngine.getBoard());
				Odds pokerOdds = new Odds(nbOuts, nbCardsInDeck - nbOuts);

				if (pokerOdds.compareTo(potOdds) >= 0)
				{
					action = Action.Call;
				}
				else
				{
					action = Action.Fold;
				}
			}

			long delta = System.currentTimeMillis() - start;
			if (delta < MINIMUM_TIME_TO_PLAY)
			{
				Thread.sleep(MINIMUM_TIME_TO_PLAY - delta);
			}
			switch(action)
			{
				case Call:
					call();
					break;
				case Check:
					check();
					break;
				case Fold:
					fold();
					break;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
