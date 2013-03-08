
package ch.hearc.coursjava.poo.intro.garage;

import java.util.Arrays;

public class Voiture // extends Object
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Voiture(Roue[] tabRoue, String marque)
		{
		this.tabRoue = tabRoue;
		this.marque = marque;
		}

	public Voiture(Voiture voitureSource)
		{
		//v1
		// this.marque = voitureSource.marque; // Copie supérficielle autorisée car String est inaltérable
		// this.tabRoue = clone(voitureSource.tabRoue);

		//v2
		this(clone(voitureSource.tabRoue), voitureSource.marque);
		// clone() en static! Sinon, on appelerait une méthode
		//d'un objet qu'on est en train de construire, ce qui est évidemment intérdi.
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public Voiture cloneOf()
		{
		return new Voiture(this);
		}

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Voiture [tabRoue=");
		builder.append(Arrays.toString(this.tabRoue));
		builder.append(", marque=");
		builder.append(this.marque);
		builder.append("]");
		return builder.toString();
		}

	@Override
	public boolean equals(Object v2)
		{
		if (v2 instanceof Voiture)
			{
			return isEqual((Voiture)v2);
			}
		else
			{
			return false;
			}
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setMarque(String marque)
		{
		this.marque = marque;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Roue[] getTabRoue()
		{
		return this.tabRoue;
		}

	public String getMarque()
		{
		return this.marque;
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isEqual(Voiture v2)
		{
		if (!this.marque.equals(v2.marque))
			{
			return false;
			}
		else if (this.tabRoue.length != v2.tabRoue.length)
			{
			return false;
			}
		else
			{
			for(int i = 1; i <= this.tabRoue.length; i++)
				{
				if (!this.tabRoue[i - 1].isEquale(v2.tabRoue[i - 1])) { return false; }
				}

			return true;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	// Rappel : dans une méthode static, on a pas le droit d'employer this.
	// Une méthode static est une méthode de classe. Une méthode non-static est une méthode d'instance.
	private static Roue[] clone(Roue[] tabRoueSource)
		{
		int n = tabRoueSource.length;
		Roue[] tabRoueClone = new Roue[n];
		for(int i = 1; i <= n; i++)
			{
			tabRoueClone[i - 1] = new Roue(tabRoueSource[i - 1]); // JUSTE! COpie profonde
			// tabRoueClone[i-1] = tabRoueSource[i-1].cloneOf(); // JUSTE! Copie profonde
			//tabRoueClone[i-1] = tabRoueSource[i-1]; // FAUX! Copie supérficielle
			}

		return tabRoueClone;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//inputs
	private Roue[] tabRoue;
	private String marque;
	}
