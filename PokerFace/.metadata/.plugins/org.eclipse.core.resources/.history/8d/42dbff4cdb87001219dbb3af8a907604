
package ch.hearc.coursjava.poo.derivation.animal;

public class Lion extends Mamifere
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Lion(String nom, int temperatureSang)
		{
		super(nom, temperatureSang);
		}

	public Lion(Mamifere source)
		{
		super(source);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String manger()
		{
		return "Gazelles";
		}

	@Override
	public String nomClasse()
		{
		return super.nomClasse() + " " + Lion.class.getSimpleName();
		}

	@Override
	public Lion cloneOf()
		{
		return new Lion(this);
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isEgal(Lion lion2)
		{
		return equals(lion2);
		}

	@Override
	public boolean equals(Object objet)
		{
		if (objet instanceof Lion)
			{
			return isEgal((Lion)objet);
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
	}
