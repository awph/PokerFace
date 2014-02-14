
package ch.hearc.pokerface.gameengine.statistics;


public class StatisticValue
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private double win;
	private double tie;
	private double loss;
	private double nbOpponantWinner;
	private double straightFlush;
	private double fourOfKind;
	private double fullHouse;
	private double flush;
	private double straight;
	private double threeOfKind;
	private double twoPairs;
	private double onePair;
	private double highCard;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Represents all the value for a simulation
	 * @param win			value in percent of win
	 * @param tie			value in percent of tie
	 * @param loss			value in percent of loss
	 * @param nbOpponantWinner	average of opponant winners
	 * @param straightFlush value in percent of having a straight flush
	 * @param fourOfKind	value in percent of having a 4 of a kind
	 * @param fullHouse		value in percent of having a full house
	 * @param flush			value in percent of having a flush
	 * @param straight		value in percent of having a straight
	 * @param threeOfKind	value in percent of having a 3 of a kind
	 * @param twoPairs		value in percent of having a 2 pairs
	 * @param onePair		value in percent of having a 1
	 * @param highCard		value in percent of having n highCard
	 */
	public StatisticValue(double win,double tie,double loss,double nbOpponantWinner, double straightFlush, double fourOfKind, double fullHouse, double flush, double straight, double threeOfKind, double twoPairs, double onePair, double highCard)
	{
		this.win = win;
		this.tie = tie;
		this.loss = loss;
		this.nbOpponantWinner = nbOpponantWinner;
		this.straightFlush = straightFlush;
		this.fourOfKind = fourOfKind;
		this.fullHouse = fullHouse;
		this.flush = flush;
		this.straight = straight;
		this.threeOfKind = threeOfKind;
		this.twoPairs = twoPairs;
		this.onePair = onePair;
		this.highCard = highCard;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Win : ");
		builder.append(this.win);
		builder.append("\nTie : ");
		builder.append(this.tie);
		builder.append("\nLoss : ");
		builder.append(this.loss);
		builder.append("\nAvarage opponant winners : ");
		builder.append(this.nbOpponantWinner);
		builder.append("\nStraightFlush : ");
		builder.append(this.straightFlush);
		builder.append("\nFourOfKind : ");
		builder.append(this.fourOfKind);
		builder.append("\nFullHouse : ");
		builder.append(this.fullHouse);
		builder.append("\nFlush : ");
		builder.append(this.flush);
		builder.append("\nStraight : ");
		builder.append(this.straight);
		builder.append("\nThreeOfKind : ");
		builder.append(this.threeOfKind);
		builder.append("\nTwoPairs : ");
		builder.append(this.twoPairs);
		builder.append("\nOnePair : ");
		builder.append(this.onePair);
		builder.append("\nHighCard : ");
		builder.append(this.highCard);
		return builder.toString();
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public double getWin()
	{
		return win;
	}

	public double getTie()
	{
		return tie;
	}

	public double getLoss()
	{
		return loss;
	}

	public double getNbOpponantWinner()
	{
		return nbOpponantWinner;
	}

	public double getStraightFlush()
	{
		return this.straightFlush;
	}

	public double getFourOfKind()
	{
		return this.fourOfKind;
	}

	public double getFullHouse()
	{
		return this.fullHouse;
	}

	public double getFlush()
	{
		return this.flush;
	}

	public double getStraight()
	{
		return this.straight;
	}

	public double getThreeOfKind()
	{
		return this.threeOfKind;
	}

	public double getTwoPairs()
	{
		return this.twoPairs;
	}

	public double getOnePair()
	{
		return this.onePair;
	}

	public double getHighCard()
	{
		return this.highCard;
	}
}

