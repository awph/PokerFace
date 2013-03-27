
package ch.hearc.miscellaneoustest.handpoker;

import ch.hearc.miscellaneoustest.handpoker.cards.Card;
import ch.hearc.miscellaneoustest.handpoker.subset.CardSubset;
import ch.hearc.miscellaneoustest.handpoker.subset.Hand;

public class ComputeBestHandInASubset
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Poker with 5 cards
	private static final int			LENGTH_HAND		= 5;
	private static final HandsPokerMap	HANDS_POKER_MAP	= HandsPokerMap.getInstance();

	private Hand						highestHand;
	private CardSubset					allCards;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Size minimum 5
	 */
	public ComputeBestHandInASubset(CardSubset actualHand)
	{
		if (actualHand.size() < LENGTH_HAND)
		{
			System.err.println("Size Error");//TODO : Find a better way to do it
		}

		allCards = actualHand;
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Hand getHighestHand()
	{
		searchHighestHand();
		return highestHand;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void searchHighestHand()
	{
		Card[] currentHand = new Card[LENGTH_HAND];
		Card[] subset = allCards.getArray();

		int h = 0;
		for(Card c:allCards)
		{
			subset[h++] = c;
		}

		for(int i = 0; i < subset.length - 4; ++i)
		{
			for(int j = i + 1; j < subset.length - 3; ++j)
			{
				for(int k = j + 1; k < subset.length - 2; ++k)
				{
					for(int l = k + 1; l < subset.length - 1; ++l)
					{
						for(int m = l + 1; m < subset.length; ++m)
						{
							currentHand[0] = subset[i];
							currentHand[1] = subset[j];
							currentHand[2] = subset[k];
							currentHand[3] = subset[l];
							currentHand[4] = subset[m];

							Hand hand = new Hand();

							for(Card c:currentHand)
							{
								hand.add(c);
							}

							if (highestHand == null || HANDS_POKER_MAP.getHand(hand).compareTo(HANDS_POKER_MAP.getHand(highestHand)) > 0)
							{
								highestHand = hand;
							}

						}
					}
				}
			}
		}
	}
}
