
package ch.hearc.coursjava.kitbase.sysproperties;

public class UseSystemProporties
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
		afficherUser();
		afficherPara1();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * <pre>
	 * Nécessite de lancer la JVM avec
	 * java ... -Dpara1=coucou ...
	 * </pre>
	 */
	private static void afficherPara1()
		{
		String username = System.getProperty("para1");
		System.out.println(username);
		}

	private static void afficherUser()
		{
		String username = System.getProperty("user.name");
		System.out.println(username);
		}
	}
