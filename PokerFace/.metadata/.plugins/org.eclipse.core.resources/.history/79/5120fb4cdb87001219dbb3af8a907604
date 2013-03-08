
package ch.hearc.coursjava.poo.derivation.form;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class Carre extends Form
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Carre(String nom, double cote)
		{
		super(nom);
		this.cote = cote;
		}

	public Carre(Carre source)
		{
		super(source);
		this.cote = source.cote;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public double aire()
		{
		return cote * cote;
		}

	@Override
	public double perimetre()
		{
		return 4 * cote;
		}

	@Override
	public Carre cloneOf()
		{
		return new Carre(this);
		}

	@Override
	public boolean equals(Object object)
		{
		if (object instanceof Carre) { return isEquale((Carre)object); }
		return false;
		}

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Carre [cote=");
		builder.append(this.cote);
		builder.append("]");
		return builder.toString();
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	@Override
	public boolean isEquale(Form form2)
		{
		if(!(form2 instanceof Carre))
			{
			return false;
			}
		else
			{
			Carre carre2 = (Carre) form2;
			return super.isEquale(carre2) && MathTools.isEgal(cote, carre2.cote);
			}

		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public double getCote()
		{
		return this.cote;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//input
	private double cote;

	}
