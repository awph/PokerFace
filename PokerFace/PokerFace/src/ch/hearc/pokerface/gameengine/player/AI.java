
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
import ch.hearc.pokerface.gameengine.subsets.Deck;
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
		check();
		//level1();
	}

	private void level1()
	{
		if (getCallValue() == 0)
		{
			check();
		}
		else if (gameEngine.getUnorderedBoard().length == 0)//PreFlop state
		{
			double win = svPreFlop.getWin();
			int rand = (int)(Math.random() * 100);
			if (rand < win)
			{
				call();
			}
			else
			{
				fold();
			}
		}
		else
		{
			Pot pot = gameEngine.getPot();
			Odds potOdds = new Odds(getCallValue(), pot.getTurnTotal() + getCallValue());
			int nbCardsInDeck = Deck.NB_CARD_DECK - gameEngine.getNbPlayers() * Pocket.NUMBER_OF_CARDS - gameEngine.getUnorderedBoard().length;
			int nbOuts = Statistics.getOuts(pocket, gameEngine.getBoard());
			Odds pokerOdds = new Odds(nbOuts, nbCardsInDeck - nbOuts);

			if (gameEngine.getOldState() == StateType.RiverState)
			{
				HandsPokerValue handsPokerValue = HandsPokerMap.getInstance().getHand(new ComputeBestHand(CardSubset.union(pocket, gameEngine.getBoard())).getHighestHand());
				//TODO : Définir ce qu'est une bonne main
			}
			else
			{
				if (pokerOdds.compareTo(potOdds) == 1)
				{
					call();
				}
				else
				{
					fold();
				}
			}

		}
	}
}
