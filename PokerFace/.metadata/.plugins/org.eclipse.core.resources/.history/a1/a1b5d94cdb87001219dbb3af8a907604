
package ch.hearc.coursjava.poo.intro.hellotest;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class CalculatriceTest
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
	public void testAdd()
		{
		double result = Calculatrice.add(12.5, 16.8);
		double restultTheorique = 29.3;

		assertTrue(MathTools.isEgal(result, restultTheorique, 1e-6));
		}

	@Test
	public void testProd()
		{
			//test 1
			{
			double result = Calculatrice.prod(5.0, 15.1);
			double resultTheorique = 75.5;

			assertTrue(MathTools.isEgal(result, resultTheorique, 1e-6));
			}

			//test 2
			{
			double result = Calculatrice.prod(5.0, 12.2);
			double resultTheorique = 61.0;

			assertTrue(MathTools.isEgal(result, resultTheorique, 1e-6));
			}

			//test 3
			{
			double result1 = Calculatrice.prod(5.0, 12.2);
			double result2 = Calculatrice.prod(12.2, 5.0);

			assertTrue(MathTools.isEgal(result1, result2, 1e-6));
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
