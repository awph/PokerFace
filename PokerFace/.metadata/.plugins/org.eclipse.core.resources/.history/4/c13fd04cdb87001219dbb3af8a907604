
package ch.hearc.coursjava.poo.thread.de;

import ch.hearc.coursjava.poo.intro.chrono.Chrono;


public class UseDe
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
		Chrono chrono = new Chrono();
		final int NB_EXPERIENCE = 10000, MIN = 1, MAX = 600;
		SimulationRollDice simulation = new SimulationRollDice(NB_EXPERIENCE, MIN, MAX);
		simulation.run();

		System.out.println("For " + NB_EXPERIENCE + " tests of " + MAX + " faces, the average is : " + simulation.getAverage());
		System.out.println("Time : " + chrono);
		}

	}
