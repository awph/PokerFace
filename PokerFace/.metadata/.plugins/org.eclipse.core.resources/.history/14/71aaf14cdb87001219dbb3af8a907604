
package ch.hearc.coursjava.poo.derivation.times;

import ch.hearc.coursjava.moo.heritage.simple.HmTimes;



public class HmsTimes extends HmTimes
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public HmsTimes(int heure, int minute, int seconde)
		{
		// Construction de la classe parente
		super(heure, minute); //Contrainte : à la premiere ligne
		// Construction de nous
		this.seconde = seconde;
		}

	public HmsTimes()
		{
		// V1 - Best
		this(0,0,0);
		// V2
//		super();
//		this.seconde = 0;
		}

	public HmsTimes(HmsTimes source)
		{
		// V1
		//this(source.getHeure(), source.getMinute(), source.seconde);
		// V2 - Best
		super(source);
		this.seconde = source.seconde;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		return super.toString() + "s " + seconde; // super obligatoire pour éviter la récursivité : appel de la classe parente
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void set(int heure, int minute, int seconde)
		{
		// V1
//		super.set(heure, minute);
//		this.seconde = seconde;
		// V2 - Best
		set(heure, minute);
		this.seconde = seconde;
		// V3
//		this.set(heure, minute);
//		this.seconde = seconde;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Input / Output
	private int seconde;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/



	}

