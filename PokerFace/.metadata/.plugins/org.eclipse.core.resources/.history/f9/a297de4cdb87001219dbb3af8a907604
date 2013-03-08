
package ch.hearc.coursjava.poo.intro.complexe;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class ComplexeTest
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
	public void testGetter()
		{
		Complexe z = new Complexe(4, 3);

		assertTrue(z.getIm() == 3);
		assertTrue(z.getRe() == 4);
		assertTrue(z.getModule() == 5);
		assertTrue(MathTools.isEgal(z.getArgument(), Math.atan2(3, 4), 1e-6));
		}

	@Test
	public void testAdd()
		{
		Complexe z1 = new Complexe(1.5, 2);
		Complexe z2 = new Complexe(0.5, 3);

		Complexe resultat = z1.add(z2);
		Complexe resultatTheorique = new Complexe(2, 5);

		assertTrue(resultat.isEquale(resultatTheorique));
		}

	@Test
	public void testSous()
		{
		Complexe z1 = new Complexe(1.5, 2);
		Complexe z2 = new Complexe(0.5, 3);

		Complexe resultat = z1.sous(z2);
		Complexe resultatTheorique = new Complexe(1, -1);

		assertTrue(resultat.isEquale(resultatTheorique));
		}

	@Test
	public void testProdFacteur()
		{
		Complexe z1 = new Complexe(1, -2);

		Complexe resultat = z1.produit(-5);
		Complexe resultatTheorique = new Complexe(-5, 10);

		assertTrue(resultat.isEquale(resultatTheorique));
		}

	@Test
	public void testProd()
		{
		Complexe z1 = Complexe.create(3, 2);
		Complexe z2 = Complexe.create(0.5, 3);

		Complexe resultat = z1.produit(z2);
		Complexe resultatTheorique = Complexe.create(1.5, 5);

		assertTrue(resultat.isEquale(resultatTheorique));
		}

	@Test
	public void testDivFacteur()
		{
		Complexe z1 = new Complexe(1, -2);

		Complexe resultat = z1.div(-5);
		Complexe resultatTheorique = new Complexe(-0.2, 0.4);

		assertTrue(resultat.isEquale(resultatTheorique));
		}

	@Test
	public void testGravite2()
		{
		Complexe centre = new Complexe(3, 4);
		int n = 100;
		int r = 10;

		Complexe[] tab = new Complexe[n];
		for(int i = 0; i < n; i++)
			{
			tab[i] = Complexe.create(r, 2 * Math.PI / n * i);
			tab[i].addS(centre);
			}

		Complexe result = Complexe.gravite(tab);
		Complexe resultatTheorique = centre;

		assertTrue(result.isEquale(resultatTheorique));

		}

	@Test
	public void testGravite1()
		{
		Complexe[] tab = new Complexe[5];
		tab[0] = new Complexe();
		tab[1] = new Complexe(1, 2);
		tab[2] = new Complexe(3, 4);
		tab[3] = new Complexe(5, 6);
		tab[4] = new Complexe(7, 8);

		Complexe result = Complexe.gravite(tab);
		// X = 16 / 5 = 3.2, Y = 20 / 4 = 5
		Complexe resultTheorique = new Complexe(3.2, 4);
		assertTrue(result.isEquale(resultTheorique));
		}

	@Test
	public void testSetArgument()
		{
		Complexe result = Complexe.create(2, Math.PI / 4);
		result.setArgument(Math.PI / 2);
		Complexe resultTheorique = Complexe.create(2, Math.PI / 2);

		assertTrue(result.isEquale(resultTheorique));
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
