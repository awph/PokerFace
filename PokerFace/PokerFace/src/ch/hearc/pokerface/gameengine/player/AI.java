
package ch.hearc.pokerface.gameengine.player;

import ch.hearc.pokerface.gameengine.compute.ComputeBestHand;
import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.compute.HandsPokerValue;
import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.gamecore.Pot;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gameengine.statistics.Odds;
import ch.hearc.pokerface.gameengine.statistics.Statistics;
import ch.hearc.pokerface.gameengine.subsets.CardSubset;
import ch.hearc.pokerface.gameengine.subsets.Pocket;

public class AI extends Player
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

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
		System.out.println("IA : " + pocket);
		bet(getCallValue());
		//level1();
	}

	private void level1()
	{
		if (getCallValue() == 0)
		{
			bet(0);
		}
		else
		{
			Pot pot = gameEngine.getPot();
			Odds potOdds = new Odds(getCallValue(), pot.getTurnTotal() + getCallValue());
			int nbCardsInDeck = gameEngine.getNbPlayers() * Pocket.NUMBER_OF_CARDS - gameEngine.getBoard().size();
			int nbOuts = Statistics.getOuts(pocket, gameEngine.getBoard()).size();//TODO retourne un int
			Odds pokerOdds = new Odds(nbOuts, nbCardsInDeck - nbOuts);

			if (gameEngine.getOldState() == StateType.RiverState)
			{
				HandsPokerValue handsPokerValue = HandsPokerMap.getInstance().getHand(new ComputeBestHand(CardSubset.union(pocket, gameEngine.getBoard())).getHighestHand());
			}
			else
			{
				if (pokerOdds.compareTo(potOdds) == 1)
				{
					bet(getCallValue());
				}
				else
				{
					fold();
				}
			}

		}
	}

	private void level2()
	{

	}

	private void level3()
	{

	}
}
