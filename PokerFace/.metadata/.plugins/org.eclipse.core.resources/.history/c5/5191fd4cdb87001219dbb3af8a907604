
package ch.hearc.coursjava.poo.derivation.animal;

public class UseZoo
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
		Chat chat = new Chat("Norbert");
		Lion lion = new Lion("alex", 12);
		Croco croco = new Croco("John", 55, 6);

		Zoo zoo = new Zoo("Zoo Bâle");
		zoo.add(chat);
		zoo.add(lion);
		zoo.add(croco);

		System.out.println(zoo);
		zoo.manger();

		Zoo zooClone = zoo.cloneOf();

		croco.setLonguer(99);

		System.out.println(zooClone);
		System.out.println(zoo);

		for(Animal animal:zoo)
			{
			System.out.println(animal.manger());
			}

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
