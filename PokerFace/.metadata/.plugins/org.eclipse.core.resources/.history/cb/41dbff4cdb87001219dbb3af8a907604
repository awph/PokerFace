
package ch.hearc.coursjava.poo.derivation.animal;

public abstract class Reptile extends Animal
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Reptile(String nom, int nombreEcailles)
		{
		super(nom);
		this.nombreEcailles = nombreEcailles;
		}

	public Reptile(Reptile source)
		{
		super(source);
		this.nombreEcailles = source.nombreEcailles;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		return nombreEcailles + " " + super.toString();
		}

	@Override
	public String nomClasse()
		{
		return super.nomClasse() + " " + Reptile.class.getSimpleName();
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	@Override
	public boolean equals(Object objet)
		{
		if (objet instanceof Reptile)
			{
			Reptile reptile2 = (Reptile)objet;
			return this.nombreEcailles == reptile2.nombreEcailles && super.equals(reptile2);
			}
		else
			{
			return false;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//input
	private int nombreEcailles;
	}
