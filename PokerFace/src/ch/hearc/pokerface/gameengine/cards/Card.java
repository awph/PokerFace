
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

	/**
	 * Construct a instance of a card.
	 *
	 * @param val
	 *            : value of the card.
	 * @param col
	 *            : color of the card.
	 */
	public Card(CardValue val, CardColor col)
	{
		value = val;
		color = col;
	}

	/**
	 * Construct a new instance of the same card
	 *
	 * @param card:
	 *            base card
	 */
	public Card(Card card)
	{
		this(card.value, card.color);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * @return the value 0 if the argument card is equal to this card; a value less than 0 if this card less than the card argument; and a value greater than 0
	 *         if this card greater than the card argument.
	 */
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

	/**
	 * @return the concatenate value and color.
	 */
	@Override
	public String toString()
	{
		return value.getStringValue() + color.getStringValue();
	}

	/**
	 * @return true if it's the same, false otherwise.
	 */
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

	/**
	 * Override the hashcode by just append the value and the color. Necessary for the {@code CardSubset}
	 *
	 * @return a hash code for this card.
	 */
	@Override
	public int hashCode()
	{
		return (value.getStringValue() + color.getStringValue()).hashCode();
	}

	/**
	 * @return a copy of this card
	 */
	public Card cloneOf()
	{
		return new Card(this);
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/**
	 * @return Value of this card
	 */
	public CardValue getValue()
	{
		return value;
	}

	/**
	 * @return Color of this card
	 */
	public CardColor getColor()
	{
		return color;
	}

	/**
	 * Create and return the id of this card. The id is used for load the correct image.
	 *
	 * @return the id of this card
	 */
	public String getId()
	{
		StringBuilder sb = new StringBuilder(value.getStringValue());

		if (color == CardColor.Clubs)
		{
			sb.append("c");
		}
		else if (color == CardColor.Diamonds)
		{
			sb.append("d");
		}
		else if (color == CardColor.Hearts)
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
