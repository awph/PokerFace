
package ch.hearc.coursjava.gui.j2d.degradeHSB;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JPanelDegradeHSB extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelDegradeHSB()
		{
		isFini = true;

		geometrie();
		controle();
		apparence();
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

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void apparence()
		{
		// rien
		}

	private void controle()
		{
		this.addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent event)
					{
					int w = JPanelDegradeHSB.this.getWidth();
					int h = JPanelDegradeHSB.this.getHeight();

					JPanelDegradeHSB.this.image = creer(w, h);

					}
			});

		boutonStart.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent event)
					{
					startAnimation();
					boutonStart.setEnabled(false);
					}
			});

		}

	private void geometrie()
		{
		boutonStart = new JButton("Start");

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.add(boutonStart);
		}

	private void dessiner(Graphics2D g2d)
		{
		g2d.drawImage(image, 0, 0, null);
		}

	private Image creer(int w, int h)
		{
		this.bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		fillImage(0);

		return new ImageIcon(bufferedImage).getImage();
		}

	private void fillImage(double hueStart)
		{
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();

		double hue = hueStart;
		deltaHue = 1 / (double)h;

		for(int i = 1; i <= h; i++)
			{
			for(int j = 1; j <= w; j++)
				{

				int colorInt = Color.getHSBColor((float)hue, 1, 1).getRGB();
				bufferedImage.setRGB(j - 1, i - 1, colorInt);
				}
			hue += deltaHue;
			}
		}

	private void stepAnimation()
		{
		hueEffectif += deltaHue;

		//fillImage(hueEffectif); //problème de concurrence en cas de rétrécissement de la fenêtre

		try
			{
			SwingUtilities.invokeAndWait(new Runnable()
				{

					@Override
					public void run()
						{
						fillImage(hueEffectif);
						}
				});
			}
		catch (InvocationTargetException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		catch (InterruptedException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

		repaint();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//tools
	private JButton boutonStart;

	private Image image;
	private BufferedImage bufferedImage;
	private boolean isFini;
	private double deltaHue;
	private double hueEffectif;
	}
