
package ch.hearc.coursjava.poo.derivation.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class Dessin implements Iterable<Form>
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private Dessin(List<Form> listeForm)
		{
		this.listeForm = listeForm;
		}

	public Dessin()
		{
		this(new ArrayList<Form>());
		}

	public Dessin(Dessin source)
		{
		this();
		for(Form form:source)
			{
			listeForm.add(form.cloneOf());
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public Iterator<Form> iterator()
		{
		return listeForm.iterator();
		}

	public double sumAire()
		{
		double sumAire = 0;
		for(Form form:this)
			{
			sumAire += form.aire();
			}
		return sumAire;
		}

	public double sumPerimetre()
		{
		double sumPerimetre = 0;
		for(Form form:this)
			{
			sumPerimetre += form.perimetre();
			}
		return sumPerimetre;
		}

	public boolean addForm(Form form)
		{
		boolean ok = listeForm.add(form);
		Collections.sort(listeForm, new Comparator<Form>()
			{

				@Override
				public int compare(Form form1, Form form2)
					{
					if (!form1.getNom().equals(form2.getNom()))
						{
						return form1.getNom().compareTo(form2.getNom());
						}
					else
						{
						if (!MathTools.isEgal(form1.perimetre(), form2.perimetre()))
							{
							return Double.compare(form1.perimetre(), form2.perimetre());
							}
						else
							{
							if (!MathTools.isEgal(form1.aire(), form2.aire()))
								{
								return Double.compare(form1.aire(), form2.aire());
								}
							else
								{
								return form1.getClass().getSimpleName().compareTo(form2.getClass().getSimpleName());
								}
							}
						}
					}

			});
		return ok;
		}

	public boolean removeForm(Form form)
		{
		return listeForm.remove(form);
		}

	public Dessin cloneOf()
		{
		return new Dessin(this);
		}

	public boolean isEquale(Dessin dessin2)
		{
		if (listeForm.size() != dessin2.listeForm.size())
			{
			return false;
			}
		else
			{
			Iterator<Form> it1 = listeForm.iterator();

			for(Form form2:dessin2)
				{
				Form form1 = it1.next();

				if (!form1.isEquale(form2)) { return false; }
				}

			return true;
			}
		}

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Dessin [listeForm=");
		builder.append(this.listeForm);
		builder.append("]");
		return builder.toString();
		}

	@Override
	public boolean equals(Object object)
		{
		if (object instanceof Dessin)
			{
			return isEquale((Dessin)object);
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

	//input
	private List<Form> listeForm;

	}
