
package ch.hearc.pokerface.gameengine.cards;

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

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public int compareTo(Card o)
	{
		if (value.getStringValue().equals(o.value.getStringValue()))
		{
			return ((Integer)(color.getIntValue())).compareTo(o.color.getIntValue()); // Color compare for TreeSet
		}
		else
		{
			return value.getStringValue().compareTo(o.value.getStringValue());
		}
	}

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

	public Card cloneOf()
	{
		return new Card(this);
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

	public String getId()
	{
		StringBuilder sb = new StringBuilder(value.getStringValue());

		if(color == CardColor.Clubs)
		{
			sb.append("c");
		}
		else if(color == CardColor.Diamonds)
		{
			sb.append("d");
		}
		else if(color == CardColor.Hearts)
		{
			sb.append("h");
		}
		else
		{
			sb.append("s");
		}

		return sb.toString();
	}
}
