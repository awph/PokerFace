
package ch.hearc.coursjava.poo.derivation.animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Zoo implements Iterable<Animal>
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private Zoo(String nom, List<Animal> listeAnimaux)
		{
		this.nom = nom;
		this.listeAnimaux = listeAnimaux;
		}

	public Zoo(String nom)
		{
		this(nom, new ArrayList<Animal>());
		}

	public Zoo(Zoo source)
		{
		this(source.nom);

		for(Animal animal:source.listeAnimaux)
			{
			listeAnimaux.add(animal.cloneOf());
			//listeAnimaux.add(new Animal(animal)); Interdit car abstrait
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public Zoo cloneOf()
		{
		return new Zoo(this);
		}

	public boolean add(Animal animal)
		{
		return listeAnimaux.add(animal);
		}

	public boolean remove(Animal animal)
		{
		return listeAnimaux.remove(animal);
		}

	public void manger()
		{
		for(Animal animal:listeAnimaux)
			{
			System.out.println(animal.manger());
			}
		}

	@Override
	public Iterator<Animal> iterator()
		{
		return listeAnimaux.iterator();
		}

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Zoo [nom=");
		builder.append(this.nom);
		builder.append(", listeAnimaux=");
		builder.append(this.listeAnimaux);
		builder.append("]");
		return builder.toString();
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isEqual(Zoo zoo2)
		{
		if (!nom.equals(zoo2.nom))
			{
			return false;
			}
		else
			{
			if (listeAnimaux.size() != zoo2.listeAnimaux.size())
				{
				return false;
				}
			else
				{
				sort(this);
				sort(zoo2);

				Iterator<Animal> itZoo2 = zoo2.iterator();
				for(Animal animal:this)
					{
					if (!animal.equals(itZoo2.next())) { return false; }
					}
				return true;
				}
			}
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static void sort(Zoo zoo)
		{
		Collections.sort(zoo.listeAnimaux, new Comparator<Animal>()
			{

				@Override
				public int compare(Animal animal1, Animal animal2)
					{
					if (!animal1.getNom().equals(animal2.getNom()))
						{
						return animal1.getNom().compareTo(animal2.getNom());
						}
					else
						{
						if (!animal1.manger().equals(animal2.manger()))
							{
							return animal1.manger().compareTo(animal2.manger());
							}
						else
							{
							return animal1.getClass().getSimpleName().compareTo(animal2.getClass().getSimpleName());
							}
						}
					}
			});
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//inputs
	private String nom;

	private List<Animal> listeAnimaux;

	}
