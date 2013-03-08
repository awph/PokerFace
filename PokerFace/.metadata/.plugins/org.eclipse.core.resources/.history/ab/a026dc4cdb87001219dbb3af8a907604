
package ch.hearc.coursjava.poo.intro.garage;

public class UseGarage
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
		//		useRoue();
		//		useCloneRoue();
		//		useCloneVoiture();
		useCloneGarage();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void useCloneVoiture()
		{
		Roue[] tabRoue = new Roue[4];
		tabRoue[0] = new Roue("Michelin1", 19);
		tabRoue[1] = new Roue("Michelin2", 20);
		tabRoue[2] = new Roue("Michelin3", 21);
		tabRoue[3] = new Roue("Michelin4", 22);

		Voiture vSource = new Voiture(tabRoue, "Toyota");
		System.out.println(vSource);
		Voiture vClone = vSource.cloneOf();
		vSource.setMarque("Subaru");
		vSource.getTabRoue()[0].setTaille(-1);
		vSource.getTabRoue()[0].setMarque("Dunlop");
		System.out.println(vSource);
		System.out.println(vClone);

		}

	private static void useCloneRoue()
		{
		Roue roueSource = new Roue("Michelin", 19);
		System.out.println(roueSource.toString());
		Roue roueClone = new Roue(roueSource);
		roueSource.setMarque("Dunlop");
		roueSource.setTaille(12);
		System.out.println(roueSource);
		System.out.println(roueClone);
		}

	private static void useCloneGarage()
		{
		Roue[] tabStdRoues = new Roue[1];
		tabStdRoues[0] = new Roue("Michelin", 16);

		Voiture bmwM3 = new Voiture(tabStdRoues, "BMW M3");
		Voiture nouvBmwM3 = new Voiture(tabStdRoues, "nouv. BMW M3");

		Garage gSrc = new Garage("BMW SA, src");
		gSrc.add(new Voiture(tabStdRoues, "BMW X3")); // pas possible d'effacer cette instance depuis la garage.
		gSrc.add(new Voiture(tabStdRoues, "BMW X5")); // pas possible d'effacer cette instance depuis la garage.
		gSrc.add(bmwM3);

		System.out.println(gSrc);

		Garage gClone = gSrc.cloneOf();

		gSrc.setNom("BMW SA, src mod");
		gSrc.remove(bmwM3);

		gClone.setNom("BMW SA, clone");
		gClone.add(nouvBmwM3);

		System.out.println(gSrc);
		System.out.println(gClone);
		}

	private static void useRoue()
		{
		Roue maRoue = new Roue("Michelin", 19);
		System.out.println(maRoue.toString());
		}
	}
