
package ch.hearc.coursjava.kitbase.tableau2D.manuel.triangulaire;

public class UseTableauManuelTriangulaire
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
		//useHello();
		useClone();
		}



	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void useClone()
		{
		int n = 3;
		float[][] tabTriangulaire = create(n);
		fill(tabTriangulaire);
		afficher(tabTriangulaire,"TabSource");

		float[][] tabClone = cloneOf(tabTriangulaire);

		afficher(tabClone,"TabClone");
		tabTriangulaire[0][0] = 999;
		afficher(tabTriangulaire,"TabSourceModifie");
		afficher(tabClone,"TabClone");
		}

	private static void useHello()
		{
		int n = 3;
		float[][] tabTriangulaire = create(n);
		fill(tabTriangulaire);
		afficher(tabTriangulaire,"TableauTriangulair");
		}

	private static float[][] cloneOf(float[][] tabSource)
		{
		//Création de la structure
		float[][] tabClone = create(tabSource.length);

		//Remplissage
		for(int i = 1; i <= tabSource.length; i++)
			{
			for(int j = 1; j <= tabSource[i - 1].length; j++)
				{
				tabClone[i - 1][j - 1] = tabSource[i - 1][j - 1];
				}
			}

		return tabClone;
		}

	private static void afficher(float[][] tab2D,String titre)
		{
		System.out.println(titre);
		for(float[] ligne:tab2D)
			{
			for(float aij:ligne)
				{
				System.out.print(aij + "\t");
				}
			System.out.println();
			}
		}

	private static void fill(float[][] tabTriangulaire)
		{
		int n = tabTriangulaire.length;
		for(int i = 1; i <= n; i++)
			{
			for(int j = 1; j <= i; j++)
				{
				tabTriangulaire[i - 1][j - 1] = i * 10 + j;
				}
			}
		}

	private static float[][] create(int n)
		{
		float[][] tableauExterne = new float[n][];
		for(int i = 1; i <= n; i++)
			{
			tableauExterne[i - 1] = new float[i];
			}
		return tableauExterne;
		}
	}
