package ch.hearc.coursjava.poo.intro.quadratique;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class QuadratiqueTest
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
	public void test1()
		{
		Quadratique quadratique = new Quadratique(-2, 6, -4);
		double[] tabResult = quadratique.getTabX();
		double[] tabResultTheorique = {2,1};

		quadratique.toString();

		assertTrue(quadratique.isSoluble());
		assertTrue(isEgal(tabResult, tabResultTheorique, 1e-6));
		}

	@Test
	public void test2()
		{
		Quadratique quadratique = new Quadratique(1,1,1);
		double[] tabResult = quadratique.getTabX();
		double[] tabResultTheorique = new double[0];

		quadratique.toString();

		assertTrue(!quadratique.isSoluble());
		assertTrue(quadratique.isSoluble() == false); // HORREUR
		assertFalse(quadratique.isSoluble());
		assertTrue(isEgal(tabResult, tabResultTheorique, 1e-6));
		}

	@Test
	public void test3()
		{
		Quadratique quadratique = new Quadratique(3,-6,3);
		double[] tabResult = quadratique.getTabX();
		double[] tabResultTheorique = {1};

		quadratique.toString();

		assertTrue(quadratique.isSoluble());
		assertTrue(isEgal(tabResult, tabResultTheorique, 1e-6));
		}


	@Test
	public void testMap()
		{
		Map<CoefficientType, Double> mapABC = new HashMap<CoefficientType, Double>();
		mapABC.put(CoefficientType.A, (double)-2);
		mapABC.put(CoefficientType.B, (double)6);
		mapABC.put(CoefficientType.C, (double)-4);
		Quadratique quadratique = new Quadratique(mapABC);
		double[] tabResult = quadratique.getTabX();
		double[] tabResultTheorique = {1,2};

		assertTrue(quadratique.isSoluble());
		assertTrue(isEgal(tabResult, tabResultTheorique, 1e-6));
		}



	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static boolean isEgal(double[] tab1, double[] tab2, double epsilon)
		{
		if(tab1.length != tab2.length)
			{
			return false;
			}
		else
			{
			Arrays.sort(tab1);
			Arrays.sort(tab2);

			for(int i = 1; i <= tab1.length; i++)
				{
				if(!MathTools.isEgal(tab1[i -1], tab2[i -1], epsilon))
					{
					return false;
					}
				}
			return true;
			}
		}
	}
