
package ch.hearc.pokerface.gui;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.UIManager;

import ch.hearc.pokerface.gameengine.compute.HandsPokerMap;
import ch.hearc.pokerface.gameengine.gamecore.SoundEngine;
import ch.hearc.pokerface.gameengine.statistics.Statistics;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;

public class UseJFrameMain
{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args) throws IOException
	{
		setupLookAndFeel();
		main();
	}

	public static void main()
	{
		splashScreenLoading();
		new JFrameMain();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void splashScreenLoading()
	{
		Statistics.initialize();
		SoundEngine.getInstance();
		HandsPokerMap.getInstance();

	}

	private static void setupLookAndFeel()
	{
		//TODO : Insert license
		String licensee = "";
		String licenseRegistrationNumber = 
		String product = "";
		String licenseType = "";
		String expireDate = "";
		String maxVersion = "";
		String license = "";

		String[] li = { "Licensee=" + licensee,
						"LicenseRegistrationNumber=" + licenseRegistrationNumber,
						"Product=" + product,
						"LicenseType=" + licenseType,
						"ExpireDate=" + expireDate,
						"MaxVersion=" + maxVersion };

		UIManager.put("Synthetica.license.info", li);
		UIManager.put("Synthetica.license.key", license);

		try
		{
			UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		UIManager.put("Synthetica.translucency4DisabledIcons.enabled", Boolean.valueOf(true));
		UIManager.put("Synthetica.rootPane.minimumWindowSize", new Dimension(100, 100));

	}
}