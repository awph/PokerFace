
package ch.hearc.coursjava.poo.intro.garage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Garage // extends Object
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Garage(String nom)
		{
		this.nom = nom;
		this.listeVoiture = new LinkedList<Voiture>();
		}

	/**
	 * Constructeur de copie profonde (clone)
	 */
	public Garage(Garage srcGarage)
		{
		this(srcGarage.nom); // Copie superficielle autorisé, car class string inalterable.
		for(Voiture v:srcGarage.listeVoiture)
			{
			this.add(v.cloneOf());
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Garage [nom=");
		builder.append(this.nom);
		builder.append(", voituresListe=");
		builder.append(this.listeVoiture);
		builder.append("]");
		return builder.toString();
		}

	@Override
	public boolean equals(Object g2)
		{
		if (g2 instanceof Garage)
			{
			return isEquale((Garage)g2);
			}
		else
			{
			return false;
			}
		}

	public Garage cloneOf()
		{
		return new Garage(this);
		}

	public boolean add(Voiture v)
		{
		return this.listeVoiture.add(v);
		}

	public boolean remove(Voiture v)
		{
		return this.listeVoiture.remove(v);
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setNom(String nom)
		{
		this.nom = nom;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isEquale(Garage g2)
		{
		if (!this.nom.equals(g2.nom))
			{
			return false;
			}
		else if (this.listeVoiture.size() != g2.listeVoiture.size())
			{
			return false;
			}
		else
			{
			Iterator<Voiture> it1 = this.listeVoiture.listIterator();
			for(Voiture v2:g2.listeVoiture)
				{
				Voiture v1 = it1.next();
				if (!v1.isEqual(v2)) { return false; }
				}
			return true;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Inputs
	private String nom;
	private List<Voiture> listeVoiture;

	}
