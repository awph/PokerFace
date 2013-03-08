
package ch.hearc.coursjava.gui.j2d.image.utilisation;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrameImage extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JFrameImage()
		{
		geometrie();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometrie()
		{
		panelImage = new JPanelImage();
		boutonImage = new JButton(ImageShop.IMAGE_CH_ICON);

		this.setLayout(new BorderLayout());

		this.add(panelImage, BorderLayout.CENTER);
		this.add(boutonImage, BorderLayout.SOUTH);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setSize(800, 600);
		setTitle("Image Example");
		setLocation(30, 30);
		setResizable(true);

		setIconImage(ImageShop.IMAGE_CH);

		this.setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelImage panelImage;
	private JButton boutonImage;
	}
