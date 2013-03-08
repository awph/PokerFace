
package ch.hearc.coursjava.poo.derivation.form;

import static java.lang.Math.PI;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class Cercle extends Form
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Cercle(String nom, double rayon)
		{
		super(nom);
		this.rayon = rayon;
		}

	public Cercle(Cercle source)
		{
		super(source);
		this.rayon = source.rayon;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public boolean equals(Object object)
		{
		if (object instanceof Cercle) { return isEquale((Cercle)object); }
		return false;
		}

	@Override
	public double aire()
		{
		return rayon * rayon * PI;
		}

	@Override
	public double perimetre()
		{
		return 2 * rayon * PI;
		}

	@Override
	public Cercle cloneOf()
		{
		return new Cercle(this);
		}

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Cercle [rayon=");
		builder.append(this.rayon);
		builder.append("]");
		return builder.toString();
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	@Override
	public boolean isEquale(Form form2)
		{
		if (form2 instanceof Cercle)
			{
			Cercle cercle2 = (Cercle)form2;
			return super.isEquale(cercle2) && MathTools.isEgal(rayon, cercle2.rayon);
			}
		else
			{
			return false;
			}
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public double getRayon()
		{
		return this.rayon;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setRayon(double rayon)
		{
		this.rayon = rayon;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//input
	private double rayon;

	}
