
package ch.hearc.coursjava.gui.j2d.traitTournant;

public class Calibreur
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Calibreur(Interval iDepart, Interval iArrivee)
		{
		//input
		this.IDepart = iDepart;
		this.IArrivee = iArrivee;

		//tools
		this.pente = (iArrivee.getiStop() - iArrivee.getiStart()) / (iDepart.getiStop() - iDepart.getiStart());
		this.translation = iArrivee.getiStart() - this.pente * IDepart.getiStart();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Calibreur [IDepart=");
		builder.append(this.IDepart);
		builder.append(", IArrivee=");
		builder.append(this.IArrivee);
		builder.append(", pente=");
		builder.append(this.pente);
		builder.append(", translation=");
		builder.append(this.translation);
		builder.append("]");
		return builder.toString();
		}

	public double calibrer(double x)
		{
		return pente * x + translation;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input
	private Interval IDepart;
	private Interval IArrivee;

	//tools
	private double pente;
	private double translation;
	}
