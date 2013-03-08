package ch.hearc.coursjava.poo.thread.additionvectorielle;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAdditionneur
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
	public void testAdditionneur()
		{
		long[] tab1 = {10,20,30,40};
		long[] tab2 = {100,200,300,400};

		int n = tab1.length;

		Additionneur additionneur = new Additionneur(tab1, tab2);
		additionneur.run();

		long[] tabResultatTheorique = {110, 220, 330, 440};
		long[] tabResultatEmpirique = additionneur.getTabResult();

		assertTrue(tabResultatEmpirique.length == tabResultatTheorique.length);

		for(int i = 1; i <= n; i++)
			{
			assertTrue(tabResultatEmpirique[i-1] == tabResultatTheorique[i-1]);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
