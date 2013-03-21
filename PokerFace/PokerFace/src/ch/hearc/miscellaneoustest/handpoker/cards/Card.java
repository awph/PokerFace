
package ch.hearc.miscellaneoustest.handpoker.cards;

public class Card implements Comparable<Card>
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private CardValue	value;
	private CardColor	color;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Card(CardValue val, CardColor col)
	{
		value = val;
		color = col;
	}

	public Card(Card card)
	{
		this(card.value, card.color);
	}

	@Override
	public int compareTo(Card o)
	{
		//		if (value.getIntValue() == o.value.getIntValue())
		//		{
		//			return 0;
		//		}
		//		else if (value.getIntValue() > o.value.getIntValue())
		//		{
		//			return 1;
		//		}
		//		else
		//		{
		//			return -1;
		//		}

		if (value.getStringValue().equals(o.value.getStringValue()))
		{
			return ((Integer)(color.getIntValue())).compareTo(o.color.getIntValue()); // Color compare for TreeSet
		}
		else
		{
			return value.getStringValue().compareTo(o.value.getStringValue());
		}
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
	{
		return value.getStringValue() + color.getStringValue();
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Card)
		{
			return value == ((Card)o).value && color == ((Card)o).color;
		}
		else
		{
			return false;
		}
	}

	//Necessary for the set
	@Override
	public int hashCode()
	{
		return (value.getStringValue() + color.getStringValue()).hashCode();
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public CardValue getValue()
	{
		return value;
	}

	public CardColor getColor()
	{
		return color;
	}

}
