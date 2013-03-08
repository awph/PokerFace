
package ch.hearc.coursjava.kitbase.tableau2D.manuel.heterogene;

import ch.hearc.coursjava.kitbase.tools.MathTools;

public class UseTableauManuelHeterogene
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
		int[] tabNbColonnesParLignes = new int[] { 1, 3, 2, 1, 2 };
		float[][] tab2DSource = create(tabNbColonnesParLignes);
		fill(tab2DSource);
		display("Source", tab2DSource);
		float[][] tab2DClone = clone(tab2DSource);
		System.out.println("is egale: " + isEgal(tab2DClone, tab2DSource));
		tab2DSource[0][0] = -999;
		display("Clone", tab2DClone);
		display("Source modifée", tab2DSource);
		System.out.println("is egale: " + isEgal(tab2DClone, tab2DSource));
		}

	private static void hello()
		{
		int[] tabNbColonnesParLignes = new int[] { 1, 3, 2, 1, 2 };
		float[][] tab2DIrregulier = create(tabNbColonnesParLignes);
		fill(tab2DIrregulier);
		display("tab2DIrregulier", tab2DIrregulier);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void fill(float[][] externArray)
		{
		int n = externArray.length;

		for(int i = 1; i <= n; i++)
			{
			for(int j = 1; j <= externArray[i - 1].length; j++)
				{
				externArray[i - 1][j - 1] = (i * 10 + j);
				}
			}
		}

	/**
	 * tab peut être carré,rectangulaire ou hétérogène
	 */
	private static void display(String titre, float[][] tab)
		{
		System.out.println(titre);
		for(float[] ligne:tab)
			{
			for(float aij:ligne)
				{
				System.out.print(aij + "\t");
				}
			System.out.println();
			}
		}

	private static float[][] create(int[] tabNbColonnesParLignes)
		{
		int n = tabNbColonnesParLignes.length;
		float[][] tab = new float[n][];
		for(int i = 1; i <= n; i++)
			{
			int mi = tabNbColonnesParLignes[i - 1];
			tab[i - 1] = new float[mi];
			}
		return tab;
		}

	private static float[][] clone(float[][] tabOriginal)
		{
		int n = tabOriginal.length;

		//Création de la structure
		float[][] tabClone = new float[n][];
		for(int i = 1; i <= n; i++)
			{
			tabClone[i - 1] = new float[tabOriginal[i - 1].length];
			// tabClone[i-1] = tabOriginal[i-1]; // FAUX! COPIE SUPERFICIELLE DES TABLEAUX INTERNES
			}

		//Remplissage de la structure
		for(int i = 1; i <= n; i++)
			{
			for(int j = 1; j <= tabClone[i - 1].length; j++)
				{
				tabClone[i - 1][j - 1] = tabOriginal[i - 1][j - 1];
				}
			}

		return tabClone;
		}

	private static boolean isEgal(float[][] tab1, float[][] tab2)
		{
		return isEgal(tab1, tab2, (float)1e-4);
		}

	private static boolean isEgal(float[][] tab1, float[][] tab2, float epsilon)
		{
		if (isSameSize(tab1, tab2))
			{
			int n = tab1.length;
			for(int i = 1; i <= n; i++)
				{
				int mi = tab1[i - 1].length;
				for(int j = 1; j <= mi; j++)
					{
					if (!MathTools.isEgal(tab1[i - 1][j - 1], tab2[i - 1][j - 1], epsilon)) { return false; }
					}
				}
			return true;
			}
		else
			{
			return false;
			}
		}



	private static boolean isSameSize(float[][] tab1, float[][] tab2)
		{
		if (tab1.length != tab2.length)
			{
			return false;
			}
		else
			{
			int n = tab1.length;
			for(int i = 1; i <= n; i++)
				{
				if (tab1[i - 1].length != tab2[i - 1].length) { return false; }
				}
			return true;
			}
		}
	}
