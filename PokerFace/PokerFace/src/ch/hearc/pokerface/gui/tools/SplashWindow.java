package ch.hearc.pokerface.gui.tools;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author will824 @ stackoverflow.com - http://stackoverflow.com/questions/6906267/debug-splashscreen-from-eclipse-without-generating-the-jar
 */
public class SplashWindow extends JFrame
{

	private static SplashWindow	instance;

	private boolean				paintCalled			= false;

	private Image				image;

	private SplashWindow(Image image)
	{
		super();
		this.image = image;

		setIconImage(ImageShop.IMAGE_APPICON);

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(image));
		this.add(label);
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.pack();
		this.setLocationRelativeTo(null);

	}

	public static void splash(URL imageURL)
	{
		if (imageURL != null)
		{
			splash(Toolkit.getDefaultToolkit().createImage(imageURL));
		}
	}

	public static void splash(Image image)
	{
		if (instance == null && image != null)
		{
			instance = new SplashWindow(image);
			instance.setVisible(true);

			if (!EventQueue.isDispatchThread() && Runtime.getRuntime().availableProcessors() == 1)
			{

				synchronized (instance)
				{
					while(!instance.paintCalled)
					{
						try
						{
							instance.wait();
						}
						catch (InterruptedException e)
						{
						}
					}
				}
			}
		}
	}

	@Override
	public void update(Graphics g)
	{
		paint(g);
	}

	@Override
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, this);
		if (!paintCalled)
		{
			paintCalled = true;
			synchronized (this)
			{
				notifyAll();
			}
		}
	}

	public static void disposeSplash()
	{
		instance.setVisible(false);
		instance.dispose();
	}
}
