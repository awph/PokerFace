
package ch.hearc.coursjava.gui.j2d.traitTournant;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class JPanelDessinTraitTournant extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelDessinTraitTournant(OptionAnimation optionAnimation)
		{
		//input
		this.optionAnimation = optionAnimation;

		//tools
		ligneTournante = new Line2D.Double(0, 0, 0, 100);
		isAnimationRunning = false;
		alpha = 0;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D)g;

		AffineTransform oldG2d = g2d.getTransform();
		dessiner(g2d);
		g2d.setTransform(oldG2d);
		}

	public synchronized void startAnimation()
		{
		if (!isAnimationRunning)
			{
			isAnimationRunning = true;
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask()
				{

					@Override
					public void run()
						{
						stepAnimation();
						}
				}, 0, optionAnimation.getDelayMS());
			}
		}

	public void stopAnimation()
		{
		timer.cancel();
		isAnimationRunning = false;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void dessiner(Graphics2D g2d)
		{
		centrerG2d(g2d);
		dessinerTrait(g2d);
		}

	private void centrerG2d(Graphics2D g2d)
		{
		int w = this.getWidth();
		int h = this.getHeight();
		g2d.translate(w / 2, h / 2);
		g2d.scale(1, -1);
		}

	private void dessinerTrait(Graphics2D g2d)
		{
		g2d.rotate(alpha);
		g2d.draw(ligneTournante);
		}

	private void stepAnimation()
		{
		alpha -= optionAnimation.getdAlpha();
		repaint();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private Line2D ligneTournante;
	private Timer timer;
	private boolean isAnimationRunning;
	private double alpha;

	//input
	private OptionAnimation optionAnimation;

	}
