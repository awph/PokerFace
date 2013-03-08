
package ch.hearc.coursjava.poo.derivation.form;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DessinTest
	{

	/*------------------------------------------------------------------*\
	 |*							Constructeurs							*|
	 \*------------------------------------------------------------------*/

	@Before
	public void before()
		{
		// rien
		}

	@After
	public void after()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	 |*							Methodes Public							*|
	 \*-----------------------------------------------------------------*/

	@Test
	public void testDessinCloneOf()
		{
		Carre carre1 = new Carre("c1", 2);
		Carre carre2 = new Carre("c2", 3);
		Cercle cercle1 = new Cercle("c1", 2);

		Dessin dessinSource = new Dessin();
		dessinSource.addForm(carre1);
		dessinSource.addForm(carre2);
		dessinSource.addForm(cercle1);

		Dessin dessinClone = dessinSource.cloneOf();

		assertTrue(dessinSource != dessinClone);

		Iterator<Form> itDessinClone = dessinClone.iterator();
		for(Form form:dessinSource)
			{
			assertTrue(form != itDessinClone.next());
			}

		assertTrue(dessinSource.isEquale(dessinClone));

		cercle1.setRayon(999);
		System.out.println(dessinSource);
		System.out.println(dessinClone);
		assertFalse(dessinSource.isEquale(dessinClone));

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
