
package ch.hearc.pokerface.gui.menuscreens;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.pokerface.gui.tools.ImageShop;

public final class JPanelAbout extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private static volatile JPanelAbout	instance	= null;

	// Inputs / Outputs
	private JLabel						heArc;
	private JLabel						autors;
	private JLabel						copyright;
	private JLabel						links;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*
	 * Dialog showing licensing and authors' information
	 * @return The only instance of the class
	 */
	public final static JPanelAbout getInstance()
	{
		if (JPanelAbout.instance == null)
		{
			synchronized (JPanelAbout.class)
			{
				if (JPanelAbout.instance == null)
				{
					JPanelAbout.instance = new JPanelAbout();
				}
			}
		}
		return JPanelAbout.instance;
	}

	private JPanelAbout()
	{
		heArc = new JLabel("",ImageShop.IMAGE_HE_ARC,0);
		autors = new JLabel("<html><p><b>Diego Antognini INF2dlm-a</b><br /><b>Danick Fort INF2dlm-a</b><br /><b>Alexandre Perez INF2dlm-a</b></p></html>");
		copyright = new JLabel("<html><p style=\"text-align:center;\"><b>Copyright &copy; HE-Arc - 2013</b></p></html>");
		links = new JLabel("<html><p>Wallpaper : <a href=\"http://pixelperfectdigital.com/digitals/0/51/dspname.html\">http://pixelperfectdigital.com/digitals/0/51/dspname.html</a><br />" +
				"Cards : <a href=\"http://sourceforge.net/projects/vector-cards/\">http://sourceforge.net/projects/vector-cards/</a><br />" +
				"Cover Cards : <a href=\"http://stovermagic.deviantart.com/art/Bicycle-Card-Vector-310320736\">http://stovermagic.deviantart.com/art/Bicycle-Card-Vector-310320736</a><br />" +
				"Sounds Win : <a href=\"http://www.freesound.org/people/Wolfsinger/sounds/25762/\">http://www.freesound.org/people/Wolfsinger/sounds/25762/</a><br />" +
				"Sounds Lose : <a href=\"http://www.freesound.org/people/Kastenfrosch/sounds/162458/\">http://www.freesound.org/people/Kastenfrosch/sounds/162458/</a>" +
				"</p></html>");

		Box tot = Box.createVerticalBox();
		tot.add(heArc);
		tot.add(autors);
		tot.add(copyright);
		tot.add(links);

		add(tot);
	}
}
