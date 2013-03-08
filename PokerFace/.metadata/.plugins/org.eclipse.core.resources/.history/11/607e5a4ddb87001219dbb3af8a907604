
package ch.hearc.coursjava.gui.j2d.hello;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class JPanelDessinHello extends JPanel
	{

	public JPanelDessinHello() // constructeur
		{
		rectangle = new Rectangle2D.Double(20, 50, 70, 60);
		rectangleFill = new Rectangle2D.Double(30, 60, 50, 20);
		line = new Line2D.Double(60, 20, 70, 30);
		text = "Coucou";
		textColor = new Color(128, 63, 18);
		font = new Font("Helvetica", Font.BOLD + Font.ITALIC, 80);
		ellipse = new Arc2D.Double(100, 200, 60, 60, 0, 270, Arc2D.PIE);

		Point2D p1 = new Point2D.Double(150, 200);
		Point2D p2 = new Point2D.Double(150, 300);
		Color color1 = Color.BLUE;
		Color color2 = Color.RED;
		gradient = new GradientPaint(p1, color1, p2, color2);
		gradientRectangle = new Rectangle2D.Double(150, 200, 350, 100);
		gradientText = "Test Gradient";
		}

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

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

	private void dessiner(Graphics2D g2d)
		{
		//g2d.rotate(Math.PI/6);
		g2d.scale(2, 1);

		g2d.draw(rectangle);

		g2d.setColor(Color.red);
		g2d.fill(rectangleFill);

		g2d.draw(line);

		g2d.setColor(textColor);
		g2d.setFont(font);
		g2d.drawString(text, 100, 100);

		g2d.draw(ellipse);

		g2d.setPaint(gradient);
		g2d.draw(gradientRectangle);
		g2d.drawString(gradientText, 150, 250);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Rectangle2D rectangle;
	private Rectangle2D rectangleFill;
	private Line2D line;
	private String text;
	private Color textColor;
	private Font font;
	private Arc2D ellipse;
	private GradientPaint gradient;
	private Rectangle2D gradientRectangle;
	private String gradientText;
	}
