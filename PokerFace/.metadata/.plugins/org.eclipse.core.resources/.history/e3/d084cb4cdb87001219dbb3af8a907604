
package ch.hearc.coursjava.poo.thread.hello;

public class UseThread
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		System.out.println("Fin du thread main");
		}

	public static void main()
		{
		//useHello();
		useHello2();
		}

	private static void useHello2()
		{
		int n = 10;
		Thread[] tabThread = new Thread[n];

		for(int i = 1; i <= n; i++)
			{
			final int sleepFactor = n - i;
			tabThread[i - 1] = new Thread(new Runnable()
				{

					@Override
					public void run()
						{
						sleep(sleepFactor * 100);
						System.out.println(Thread.currentThread().getName());
						}
				});
			tabThread[i - 1].setName("Thread" + i);
			}

		for(int i = 1; i <= n; i++)
			{
			tabThread[i - 1].start();
			}
		try
			{
			for(int i = 1; i <= n; i++)
				{
				tabThread[i - 1].join(); //Le thread courant attend tabThread[i-1]
				//Qui est le thread courant?
				//Le thread qui exécute la ligne de code : ici le thread main
				System.out.println(Thread.currentThread().getName()); //Affiche "main"
				}
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	private static void useHello()
		{
		Thread thread1 = new Thread(new Runnable()
			{

				@Override
				public void run()
					{
					sleep(2000);
					System.out.println(Thread.currentThread().getName());
					}

			});

		Thread thread2 = new Thread(new Runnable()
			{

				@Override
				public void run()
					{
					sleep(4000);
					System.out.println(Thread.currentThread().getName());
					}
			});

		thread1.setName("Thread1");
		thread2.setName("Thread2");

		thread1.start();
		thread2.start();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static void sleep(int delayMs)
		{
		try
			{
			Thread.sleep(delayMs);
			}
		catch (InterruptedException e)
			{

			e.printStackTrace();
			}
		}

	}
