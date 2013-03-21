
package ch.hearc.miscellaneoustest.handpoker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
		if (actualHand.size() < 5)
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
		List<Card> currentHand = new LinkedList<Card>();
		List<Card> subset = new ArrayList<Card>();

		for(int i = 0; i < 5; ++i)
		{
			currentHand.add(null);
		}

		for(Card c:allCards)
		{
			subset.add(c);
		}

		searchHighestHand(0, 0, currentHand, subset, null);
		return highestHand;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void searchHighestHand(int indexLastValueTaken, int indexCurrentHand, List<Card> currentHand, List<Card> subset, Card busyCard)
	{
		if (indexCurrentHand < LENGTH_HAND)
		{
			for(int i = 0; i < subset.size(); ++i)
			{
				if (subset.get(i) != busyCard)
				{
					indexLastValueTaken = i;
					busyCard = subset.get(i);
					currentHand.set(indexCurrentHand, busyCard);

					searchHighestHand(0, indexCurrentHand + 1, currentHand, subset, busyCard);

					subset.set(indexLastValueTaken, currentHand.get(indexCurrentHand));
				}
			}
		}
		else
		{
			Hand hand = new Hand();

			for(Card c:currentHand)
			{
				hand.add(c);
			}

			try
			{
				if (HANDS_POKER_MAP.getHand(hand).compareTo(HANDS_POKER_MAP.getHand(highestHand)) > 0)
				{
					highestHand = hand;
				}
			}
			catch (NullPointerException e)
			{
			}
		}
	}
}
