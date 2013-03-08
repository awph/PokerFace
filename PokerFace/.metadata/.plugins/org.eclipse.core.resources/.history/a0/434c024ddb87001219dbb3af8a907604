
package ch.hearc.coursjava.poo.derivation.animal;

import ch.hearc.coursjava.poo.interfaces.hello.Afficher_I;

public abstract class Animal implements Afficher_I ,Manger_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Animal(String nom)
		{
		this.nom = nom;
		}

	public Animal(Animal source)
		{
		this(source.nom); // this à la première ligne (contrainte). Copie superficielle autorisée car inaltérable.
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Abstract						*|
	\*------------------------------------------------------------------*/

	public abstract Animal cloneOf();

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void afficher()
		{
		System.out.println(this);
		}

	public String nomClasse()
		{
		return Animal.class.getSimpleName();
		}

	@Override
	public String toString()
		{
		StringBuilder str = new StringBuilder();
		str.append(nom);
		str.append(" ");
		str.append(nomClasse());
		str.append(" ");
		str.append(manger());
		return str.toString();
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	@Override
	public boolean equals(Object objet)
		{
		if (objet instanceof Animal)
			{
			return this.nom.equals(((Animal)objet).nom);
			}
		else
			{
			return false;
			}
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

	// Input
	private String nom;

	}
