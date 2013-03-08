
package ch.hearc.coursjava.poo.derivation.animal;

public class Croco extends Reptile
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Croco(String nom, int nombreEcailles, int longuer)
		{
		super(nom, nombreEcailles);
		this.longuer = longuer;
		}

	public Croco(Croco source)
		{
		super(source);
		this.longuer = source.longuer;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public Croco cloneOf()
		{
		return new Croco(this);
		}

	@Override
	public String manger()
		{
		return "Gnu";
		}

	@Override
	public String nomClasse()
		{
		return super.nomClasse() + " " + Croco.class.getSimpleName();
		}

	@Override
	public String toString()
		{
		return longuer + " " + super.toString();
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isEgal(Croco croco2)
		{
		return equals(croco2);
		}

	@Override
	public boolean equals(Object objet)
		{
		if (objet instanceof Croco)
			{
			Croco croco2 = (Croco)objet;
			return longuer == croco2.longuer && super.equals(croco2);
			}
		else
			{
			return false;
			}
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setLonguer(int longuer)
		{
		this.longuer = longuer;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//input
	private int longuer;

	}
