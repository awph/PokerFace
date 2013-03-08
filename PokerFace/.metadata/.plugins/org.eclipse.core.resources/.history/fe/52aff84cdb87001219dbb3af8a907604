
package ch.hearc.coursjava.poo.derivation.form;

public abstract class Form
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Form(String nom)
		{
		this.nom = nom;
		}

	public Form(Form source)
		{
		this(source.nom);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Abstract						*|
	\*------------------------------------------------------------------*/

	public abstract double aire();

	public abstract double perimetre();

	public abstract Form cloneOf();

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Form [nom=");
		builder.append(this.nom);
		builder.append(", aire()=");
		builder.append(this.aire());
		builder.append(", perimetre()=");
		builder.append(this.perimetre());
		builder.append("]");
		return builder.toString();
		}

	@Override
	public boolean equals(Object object)
		{
		if (object instanceof Form) { return isEquale((Form)object); }
		return false;
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isEquale(Form form2)
		{
		return nom.equals(form2.nom);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getNom()
		{
		return nom;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//input
	private String nom;

	}
