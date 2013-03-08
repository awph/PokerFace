
package ch.hearc.coursjava.poo.interfaces.trie;

import ch.hearc.coursjava.poo.interfaces.hello.Afficher_I;

public class Homme implements Afficher_I ,Comparable<Homme>
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Homme(double taille, String nom)
		{
		this.taille = taille;
		this.nom = nom;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public int compareTo(Homme h2)
		{
		if (!this.getNom().equals(h2.getNom()))
			{
			return this.getNom().compareTo(h2.getNom());
			}
		else
			{
			return Double.compare(this.getTaille(), h2.getTaille());
			}
		}

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Homme [taille=");
		builder.append(this.taille);
		builder.append(", nom=");
		builder.append(this.nom);
		builder.append("]");
		return builder.toString();
		}

	@Override
	public void afficher()
		{
		System.out.println(this);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public double getTaille()
		{
		return this.taille;
		}

	public String getNom()
		{
		return this.nom;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Inputs
	private double taille;
	private String nom;

	}
