
package ch.hearc.coursjava.poo.derivation.form;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class CercleTest
	{

	/*------------------------------------------------------------------*\
	 |*							Constructeurs							*|
	 \*------------------------------------------------------------------*/

	@Before
	public void before()
		{
		cercle1 = new Cercle("c1", 2);
		cercle2 = new Cercle("c2", 3);
		cercle3 = new Cercle("c1", 2);
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
	public void testIsEquale()
		{
		boolean resultatEmpiriqueBool = cercle1.isEquale(cercle2);
		boolean resultatTheoriqueBool = false;
		assertTrue(resultatEmpiriqueBool == resultatTheoriqueBool);

		resultatEmpiriqueBool = cercle1.isEquale(cercle3);
		resultatTheoriqueBool = true;
		assertTrue(resultatEmpiriqueBool == resultatTheoriqueBool);
		}

	@Test
	public void testAire()
		{
		double resultatEmpirique = cercle2.aire();
		double resultatTheorique = 3 * 3 * Math.PI;
		assertTrue(MathTools.isEgal(resultatEmpirique, resultatTheorique, 1e-4));
		}

	@Test
	public void testPerimetre()
		{
		double resultatEmpirique = cercle1.perimetre();
		double resultatTheorique = 2 * 2 * Math.PI;
		assertTrue(MathTools.isEgal(resultatEmpirique, resultatTheorique, 1e-4));
		}

	@Test
	public void testCercleCloneOf()
		{
		Cercle source = new Cercle("source", 3);
		Cercle clone = source.cloneOf();

		assertTrue(source.isEquale(clone));
		assertTrue(source != clone);

		source.setRayon(999);

		assertTrue(!source.isEquale(clone));
		assertFalse(source.isEquale(clone));
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Cercle cercle1;
	private Cercle cercle2;
	private Cercle cercle3;

	}
