
package ch.hearc.coursjava.poo.intro.complexe;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class Complexe
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Complexe(double re, double im)
		{
		this.re = re;
		this.im = im;
		toPolaire();
		}

	public Complexe(Complexe source)
		{
		this(source.re, source.im); // forcement à la premiere ligne
		}

	public Complexe()
		{
		this(0, 0);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public Complexe cloneOf()
		{
		return new Complexe(this);
		}

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Complexe [re=");
		builder.append(this.re);
		builder.append(", im=");
		builder.append(this.im);
		builder.append(", module=");
		builder.append(this.module);
		builder.append(", argument=");
		builder.append(this.argument);
		builder.append("]");
		return builder.toString();
		}

	@Override
	public boolean equals(Object z2)
		{
		if (z2 instanceof Complexe)
			{
			return isEquale((Complexe)z2);
			}
		else
			{
			return false;
			}
		}

	public Complexe add(Complexe z2)
		{
		// Code juste
		return new Complexe(this.re + z2.re, this.im + z2.im);

		// code faux, car plus court, et le toPolaire n'est pas nécessaire, et grand risque d'oublier toPolaire:
		// Maintenance super difficile. On construit les objets à l'interieur des constructeur, et pas dehors comme ci-dessous!
		//		Complexe somme = new Complexe();
		//		somme.im = this.im + z2.im;
		//		somme.re = this.re + z2.re;
		//		toPolaire();
		//		return somme;
		}

	public void addS(Complexe z2)
		{
		re += z2.re;
		im += z2.im;
		toPolaire(); // Attention ne pas oublier
		}

	public Complexe sous(Complexe z2)
		{
		return add(z2.produit(-1));
		}

	public Complexe produit(Complexe z2)
		{
		return Complexe.create(this.module * z2.module, this.argument + z2.argument);
		}

	public Complexe produit(double facteur)
		{
		return new Complexe(this.re * facteur, this.im * facteur);
		}

	public Complexe div(double facteur)
		{
		return produit(1 / facteur);
		}

	public Complexe puissance(int n)
		{
		return Complexe.create(Math.pow(this.module, n), this.argument * n);
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static Complexe create(double module, double argument)
		{
		double re = toRe(module, argument);
		double im = toIm(module, argument);

		return new Complexe(re, im);
		}

	public static Complexe gravite(Complexe[] tabZ)
		{
		//V1
		/*Complexe gravite = new Complexe();

		for(Complexe z:tabZ)
			{
			gravite = gravite.add(z);
			}

		gravite = gravite.div(tabZ.length);

		return gravite;*/

		// V2: plus performante
		Complexe gravite = new Complexe();

		for(Complexe z:tabZ)
			{
			gravite.addS(z);
			}

		return gravite.div(tabZ.length);
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isEquale(Complexe z2)
		{
		return isEquale(z2, 1e-4);
		}

	public boolean isEquale(Complexe z2, double epsilon)
		{
		return MathTools.isEgal(this.im, z2.im, epsilon) && MathTools.isEgal(this.re, z2.re, epsilon);
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setRe(double re)
		{
		this.re = re;
		toPolaire();
		}

	public void setIm(double im)
		{
		this.im = im;
		toPolaire();
		}

	public void setModule(double mod)
		{
		this.module = mod;
		toCartesien();
		}

	public void setArgument(double arg)
		{
		this.argument = arg;
		toCartesien();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public double getRe()
		{
		return this.re;
		}

	public double getIm()
		{
		return this.im;
		}

	public double getModule()
		{
		return this.module;
		}

	public double getArgument()
		{
		return this.argument;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void toPolaire()
		{
		this.argument = Math.atan2(im, re);
		this.module = Math.sqrt(im * im + re * re);
		}

	private void toCartesien()
		{
		this.re = toRe(this.module, this.argument);
		this.im = toIm(this.module, this.argument);
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static double toIm(double module, double argument)
		{
		return module * Math.sin(argument);
		}

	private static double toRe(double module, double argument)
		{
		return module * Math.cos(argument);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	// Répresentation 1: cartesienne
	private double re;
	private double im;

	// Répresentation 2: polaire
	private double module;
	private double argument;

	}
