
package ch.hearc.coursjava.gui.j2d.image.fabrication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class JPanelDessin extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelDessin()
		{
		imageFabriquee = creer(33, 33);
		geometrie();
		control();
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
		AffineTransform transformOld = g2d.getTransform();
		dessiner(g2d);
		g2d.setTransform(transformOld);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void geometrie()
		{
		}

	private void control()
		{
		addComponentListener(new ComponentAdapter()
			{
				@Override
				public void componentResized(ComponentEvent event)
					{
					int w = JPanelDessin.this.getWidth();
					int h = JPanelDessin.this.getHeight();
					imageFabriquee = creer(w, h);
					}
			});
		}

	private void apparence()
		{
		}

	private void dessiner(Graphics2D g2d)
		{
		g2d.drawImage(imageFabriquee, 0, 0, null);
		}

	private Image creer(int w, int h)
		{
		BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		for(int i = 1; i <= h; i++)
			{
			for(int j = 1; j <= w; j++)
				{
				int colorInt = Color.RED.getRGB();
				bufferedImage.setRGB(j-1,i-1,colorInt);
				}
			}

		return new ImageIcon(bufferedImage).getImage();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Image imageFabriquee;
	}

