
package ch.hearc.coursjava.gui.geometrieControl.framejumelles.version1;

public class UseFrameJumelle
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * n=18
	 */
	public static void main(String[] args)
		{
		if (args.length == 1)
			{
			try
				{
				int n = Integer.valueOf((args[0].split("="))[1]);
				new Starter(n);
				}
			catch (NumberFormatException e)
				{
				error();
				}
			}
		else
			{
			error();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void error()
		{
		System.err.println("Error in the argument");
		}
	}
