
package ch.hearc.coursjava.gui.j2d.image.utilisation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class JPanelImage extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelImage()
		{
		imageJAR = ImageShop.IMAGE_UIB;

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

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void geometrie()
		{
		}

	private void control()
		{
		}

	private void apparence()
		{
		}

	private void dessiner(Graphics2D g2d)
		{
		g2d.drawImage(imageJAR, 0, 0, null);

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Image imageJAR;
	}
