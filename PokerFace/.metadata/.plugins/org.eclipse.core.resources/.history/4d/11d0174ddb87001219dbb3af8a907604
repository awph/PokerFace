
package ch.hearc.coursjava.kitbase.excel;


public class UseHistogramme
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
		final int MIN = 10;
		final int MAX = 100;
		final int NB_ITER = 10000000;
		final String FILE = "Histo.xls";


		int[] histogramme = Histogramme.create(MIN, MAX, NB_ITER);
		try
			{
			ExcelGenerator.generateXLS(histogramme, FILE);
			System.out.println("Succesfully done");
			}
		catch (Exception e)
			{
			e.printStackTrace();
			System.err.println("There was a problem");
			}
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
