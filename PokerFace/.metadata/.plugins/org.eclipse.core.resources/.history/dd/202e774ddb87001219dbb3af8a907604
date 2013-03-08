
package ch.hearc.coursjava.gui.animation.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.coursjava.poo.intro.complexe.Complexe;

public class JPanelAnimation extends JPanel implements StartStop_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelAnimation()
		{
		initAnimation();
		resizeManagement();

		geometrie();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void start()
		{
		System.out.println("[JPanelAnimation] : start");

		if (isFini)
			{
			// v1 bad
			//			animation(); // Fait par le thread AWT Queue Event : gèle l'interface graphique

			// v2 good
			//			animerThread(); // Fait par un thread séparé : interface graphique réactive

			// v3 bad
			// tout aussi bon si stepAnimation() est très court, sinon mauvais
//			animerTimerSwing();

			// v4 good
			// autant bien que v2
			animerTimerJDK();
			}
		}

	@Override
	public void stop()
		{
		System.out.println("[JPanelAnimation] : stop");

		// pour v3 seulement
		if (timerSwing != null) // utile si on exécute v1 et v2, car dans ce cas le timer vaut null
			{
			timerSwing.stop();
			}

		// pour v4 seulement
		if (timerJDK != null)
			{
			timerJDK.cancel();
			}

		// pour v1 et v2
		isFini = true;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void animerTimerJDK()
		{
		timerJDK = new java.util.Timer();

		timerJDK.scheduleAtFixedRate(new TimerTask()
			{

				@Override
				public void run()
					{
					System.out.println(Thread.currentThread().getName());
					stepAnimation();
					}
			}, 0, delayMS);
		}

	private void animerTimerSwing()
		{
		timerSwing = new javax.swing.Timer((int)delayMS, new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent event)
					{
					System.out.println(Thread.currentThread().getName());
					stepAnimation();
					}
			});

		timerSwing.start();
		}

	private void animerThread()
		{
		Thread thread = new Thread(new Runnable()
			{

				@Override
				public void run()
					{
					animation();
					}
			});

		thread.setName("Thread animation");
		thread.start();
		}

	private void animation()
		{
		System.out.println(Thread.currentThread().getName());

		isFini = false;

		while(!isFini)
			{
			stepAnimation();
			ralentirAnimation();
			}
		}

	private void stepAnimation()
		{
		Complexe zi = Complexe.create(rayon, alpha);
		zi.addS(z0); // translation de zi de z0
		int x = (int)zi.getRe();
		int y = (int)zi.getIm();

		boutonTournant.setLocation(x, y);
		alpha += dAlpha;
		}

	private void ralentirAnimation()
		{
		try
			{
			Thread.sleep(delayMS);
			}
		catch (InterruptedException e)
			{
			// rien
			}
		}

	private void initAnimation()
		{
		isFini = true;
		int n = 1000;
		dAlpha = 2 * Math.PI / n;
		alpha = 0;
		delayMS = 5;
		}

	private void control()
		{
		addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent event)
					{
					resizeManagement();
					}
			});

		boutonTournant.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent event)
					{
					System.out.println("position : " + boutonTournant.getLocation());
					}
			});
		}

	private void geometrie()
		{
		boutonTournant = new JButton("X");
		setLayout(null);

		boutonTournant.setSize(50, 50);
		boutonTournant.setLocation(30, 30);

		add(boutonTournant);
		}

	private void apparence()
		{
		// rien
		}

	private void resizeManagement()
		{
		System.out.println("[JPanelAnimation] : resized");
		int centreX = getSize().width / 2;
		int centreY = getSize().height / 2;
		rayon = getSize().width / 4;
		z0 = new Complexe(centreX, centreY);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JButton boutonTournant;
	private boolean isFini;
	private double dAlpha;
	private double alpha;
	private double rayon;
	private Complexe z0;
	private long delayMS;
	private javax.swing.Timer timerSwing;
	private java.util.Timer timerJDK;

	}
