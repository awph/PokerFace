
package ch.hearc.coursjava.gui.j2d.salleconference;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class JPanelSalleConference extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSalleConference()
		{
		table = new Rectangle2D.Double(-W / 2, -H / 2, W, H);

		deltaAlpha = Math.PI * 2 / NB_TABLE_TOUR;

		alphaAnimation = 0;
		isFini = true;

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform transformOld = g2d.getTransform();
		dessiner(g2d);
		g2d.setTransform(transformOld);
		}

	/**
	 *  un seule thread à la fois peut rentre à l'interieur de la methode startAnimation,
	 *  ceux qui garantit, de laisser la variable isFini dans un état coherent,
	 *  et ainsi de n'instancier le thread qu'une seule fois.
	 */
	public synchronized void startAnimation()
		{
		if (isFini)
			{
			isFini = false;
			Thread thread = new Thread(new Runnable()
				{

					@Override
					public void run()
						{
						while(!isFini)
							{
							stepAnimation();
							}
						}

				});
			thread.start();
			}
		}

	public void stopAnimation()
		{
		isFini = true;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void dessiner(Graphics2D g2d)
		{
		centrerG2D(g2d);

		g2d.rotate(alphaAnimation);

		int rayon = RAYON_INITIAL;
		for(int i = 1; i <= NB_RANGE; i++)
			{
			ranger(g2d, rayon);
			rayon += DELTA_RAYON;
			}

		g2d.rotate(-alphaAnimation);
		}

	private void centrerG2D(Graphics2D g2d)
		{
		g2d.translate(this.getSize().width / 2, this.getSize().height / 2);
		g2d.scale(1, -1);
		}

	private void ranger(Graphics2D g2d, int rayon)
		{
		for(int i = 1; i <= NB_TABLE_TOUR; i++)
			{
			g2d.translate(0, rayon);

			g2d.draw(table);

			g2d.translate(0, -rayon);
			g2d.rotate(deltaAlpha);
			}
		}

	private void stepAnimation()
		{
		alphaAnimation += DELTA_ALPHA_ANIMATION;

		try
			{
			Thread.sleep(DELAY_MS);
			repaint();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private double deltaAlpha;
	private Rectangle2D table;
	private boolean isFini;
	private double alphaAnimation;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/
	// Animation
	private static final double DELTA_ALPHA_ANIMATION = Math.PI * 2 / 10;
	private static final long DELAY_MS = 200;

	// Geometry
	private static final int W = 40;
	private static final int H = 20;
	private static final int RAYON_INITIAL = 100;
	private static final int DELTA_RAYON = 50;
	private static final int NB_RANGE = 3;
	private static final int NB_TABLE_TOUR = 9;

	}
