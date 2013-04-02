
package ch.hearc.miscellaneoustest.handpoker;

import java.util.HashMap;
import java.util.Map;

public class HandsPokerValue implements Comparable<HandsPokerValue>
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private int									rank;
	private String								handName;
	private HandType							handType;
	private static final Map<String, HandType>	HAND_TYPE_MAP	= new HashMap<String, HandType>()
																{
																	{
																		put("HC", HandType.HighCard);
																		put("1P", HandType.OnePair);
																		put("2P", HandType.TwoPair);
																		put("3K", HandType.ThreeOfKind);
																		put("S", HandType.Straight);
																		put("F", HandType.Flush);
																		put("FH", HandType.FullHouse);
																		put("4K", HandType.FourOfKind);
																		put("SF", HandType.StraightFlush);
																	}
																};

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public HandsPokerValue(int rank, String handName, String shortHandName)
	{
		this.rank = rank;
		this.handName = handName;
		this.handType = HAND_TYPE_MAP.get(shortHandName);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Lower rank is better !
	 */
	@Override
	public int compareTo(HandsPokerValue other)
	{
		if (other == null || rank < other.rank) { return 1; }
		if (rank == other.rank)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getRank()
	{
		return this.rank;
	}

	public String getHandName()
	{
		return this.handName;
	}

	public HandType getHandType()
	{
		return this.handType;
	}
}
