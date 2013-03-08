
package ch.hearc.coursjava.poo.derivation.animal;


public abstract class Mamifere extends Animal
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Mamifere(String nom, int temperatureSang)
		{
		super(nom);
		this.temperatureSang = temperatureSang;
		}

	public Mamifere(Mamifere source)
		{
		super(source);
		this.temperatureSang = source.temperatureSang;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		return temperatureSang + " " + super.toString();
		}

	@Override
	public String nomClasse()
		{
		return super.nomClasse() + " " + Mamifere.class.getSimpleName();
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	@Override
	public boolean equals(Object objet)
		{
		if (objet instanceof Mamifere)
			{
			Mamifere mamifere2 = (Mamifere) objet;
			return this.temperatureSang == mamifere2.temperatureSang && super.equals(mamifere2);
			}
		else
			{
			return false;
			}
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getTemperatureSang()
		{
		return this.temperatureSang;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private int temperatureSang;

	}
