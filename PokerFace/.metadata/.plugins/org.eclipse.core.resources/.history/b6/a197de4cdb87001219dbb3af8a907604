
package ch.hearc.coursjava.poo.intro.garage;

public class Roue // extends Object
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Roue(String marque, int taille)
		{
		this.marque = marque;
		this.taille = taille;
		}

	/**
	 * Constructeur de copie profonde (clone)
	 */
	public Roue(Roue roueSource)
		{
		//v1
		//this.marque = roueSource.marque; //COPIE SUPERFICIELLE! AUTORISE CAR STRING EST INALTERABLE!
		//this.taille = roueSource.taille;

		//v2
		this(roueSource.marque, roueSource.taille);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Roue [marque=");
		builder.append(this.marque);
		builder.append(", taille=");
		builder.append(this.taille);
		builder.append("]");
		return builder.toString();
		}

	public Roue cloneOf()
		{
		return new Roue(this);
		}

	@Override
	public boolean equals(Object r2)
		{
		if (r2 instanceof Roue)
			{
			return isEquale((Roue)r2);
			}
		else
			{
			return false;
			}
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isEquale(Roue roue2)
		{
		return this.marque.equals(roue2.marque) && this.taille == roue2.taille;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setMarque(String marque)
		{
		this.marque = marque;
		}

	public void setTaille(int taille)
		{
		this.taille = taille;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getMarque()
		{
		return this.marque;
		}

	public int getTaille()
		{
		return this.taille;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//inputs
	private String marque;

	private int taille;

	}
