
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
		autors = new JLabel("<html><p><b>Diego Antognini</b>, <b>Danick Fort</b> and <b>Alexandre Perez</b> in the class <b>INF2-DLMa</b></p></html>");
		copyright = new JLabel("<html><p style=\"text-align:center;\"><b>Copyright &copy; HE-Arc - 2013</b></p></html>");
		links = new JLabel("<html><p>XXXX : <a href=\"#\">Link</a><br />XXXX : <a href=\"#\">Link</a><br />XXXX : <a href=\"#\">Link</a><br />XXXX : <a href=\"#\">Link</a></p></html>");

		Box tot = Box.createVerticalBox();
		tot.add(heArc);
		tot.add(autors);
		tot.add(copyright);
		tot.add(links);

		add(tot);
	}
}
