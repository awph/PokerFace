
package ch.hearc.coursjava.poo.thread.additionvectorielle;

//Regarder pour le prochain TE!!!
public class UseAdditionneur
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		int n = Integer.MAX_VALUE / 20;

		System.out.println(n);
		long[] tab1 = new long[n];
		long[] tab2 = new long[n];

		for(int i = 1; i <= n; i++)
			{
			tab1[i - 1] = i;
			tab2[i - 1] = -i;
			}

		Additionneur additionneur = new Additionneur(tab1, tab2);
		additionneur.run();

		long[] tabResult = additionneur.getTabResult();

		for(int i = 1; i <= n; i++)
			{
			if (tabResult[i - 1] != 0)
				{
				System.err.println("Erreur " + i);
				}
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
