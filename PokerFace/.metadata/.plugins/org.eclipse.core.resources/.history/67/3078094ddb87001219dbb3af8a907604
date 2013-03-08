
package ch.hearc.coursjava.kitbase.tableau2D.manuel.rectangulaire;

public class UseTableauManuelRectnagulaire
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
		hello();
		useClone();
		}

	private static void useClone()
		{
		int n = 2;
		int m = 3;
		float[][] tableauExterne = create(n, m);
		fill(tableauExterne);
		afficherForEach(tableauExterne);
		float[][] tabClone = clone(tableauExterne);
		tabClone[0][0] = -999;
		afficherForEach(tableauExterne);
		afficherForEach(tabClone);
		}

	private static void hello()
		{
		int n = 2;
		int m = 3;
		float[][] tableauExterne = create(n, m);
		fill(tableauExterne);
		afficherForEach(tableauExterne);
		afficherIndice(tableauExterne);
		}

	private static float[][] create(int n, int m)
		{
		float[][] tableauExterne = new float[n][];
		for(int i = 1; i <= n; i++)
			{
			tableauExterne[i - 1] = new float[m];
			}
		return tableauExterne;
		}

	private static float[][] createSimple(int n, int m)
		{
		return new float[n][m];
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void afficherIndice(float[][] tableauExterne)
		{
		int n = tableauExterne.length;
		for(int i = 1; i <= n; i++)
			{
			int mi = tableauExterne[i - 1].length;
			for(int j = 1; j <= mi; j++)
				{
				System.out.print(tableauExterne[i - 1][j - 1] + "\t");
				}
			System.out.println();
			}

		}

	private static void afficherForEach(float[][] tableauExterne)
		{
		for(float[] ligne:tableauExterne)
			{
			for(float aij:ligne)
				{
				System.out.print(aij + "\t");
				}
			System.out.println();
			}
		}

	private static void fill(float[][] tableauExterne)
		{
		int n = tableauExterne.length;
		for(int i = 1; i <= n; i++)
			{
			int mi = tableauExterne[i - 1].length;
			for(int j = 1; j <= mi; j++)
				{
				tableauExterne[i - 1][j - 1] = i * 10 + j;
				}
			}

		}

	private static float[][] clone(float[][] tabSource)
		{
		int n = tabSource.length;
		int m = tabSource[0].length;

		//Création de la structure
		float[][] tabClone = new float[n][m];

		//Remplissage de la structure
		for(int i = 1; i <= n; i++)
			{
			for(int j = 1; j <= m; j++)
				{
				tabClone[i - 1][j - 1] = tabSource[i - 1][j - 1];
				}
			}

		return tabClone;
		}
	}
