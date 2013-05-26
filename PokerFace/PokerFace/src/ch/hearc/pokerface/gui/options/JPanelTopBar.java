
package ch.hearc.pokerface.gui.options;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.pokerface.gameengine.gamecore.SoundEngine;
import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.JFrameMain;
import ch.hearc.pokerface.gui.tools.ImageShop;

public final class JPanelTopBar extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private static volatile JPanelTopBar	instance	= null;

	// Inputs / Outputs
	private JLabel							avatar;
	private JLabel							name;
	private JLabel							bankroll;
	private JLabel							coin;
	private JLabel							clockLabel;
	private JLabel							quitApplication;
	private JLabel							quitGame;
	private JLabel							volumeIcon;
	private JSlider							volumeSlider;

	private SimpleDateFormat				ft;

	private JFrameMain						frameMain;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*
	 * @return The only instance of the class
	 */
	public final static JPanelTopBar getInstance()
	{
		if (JPanelTopBar.instance == null)
		{
			synchronized (JPanelTopBar.class)
			{
				if (JPanelTopBar.instance == null)
				{
					JPanelTopBar.instance = new JPanelTopBar();
				}
			}
		}
		return JPanelTopBar.instance;
	}

	private JPanelTopBar()
	{
		name = new JLabel();
		avatar = new JLabel();
		try
		{
			//TODO A mettre dans imageshop
			coin = new JLabel("", new ImageIcon(ImageIO.read(new File("resources/coin.png"))), 0);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		bankroll = new JLabel();
		clockLabel = new JLabel();
		clockLabel.setFont(new Font("Arial", Font.BOLD, 18));
		quitApplication = new JLabel("", ImageShop.ICON_BUTTON_QUIT_APPLICATION, 0);
		quitGame = new JLabel("", ImageShop.ICON_BUTTON_QUIT_GAME, 0);
		volumeIcon = new JLabel("\u266B");
		volumeIcon.setFont(new Font("Arial", Font.BOLD, 18));

		volumeSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 50);
		volumeSlider.setPaintLabels(false);

		ft = new SimpleDateFormat("HH:mm:ss");

		Box profil = Box.createHorizontalBox();
		profil.add(avatar);
		profil.add(Box.createHorizontalStrut(20));
		profil.add(name);
		profil.add(Box.createHorizontalStrut(20));
		profil.add(coin);
		profil.add(Box.createHorizontalStrut(5));
		profil.add(bankroll);

		Box options = Box.createHorizontalBox();
		options.add(volumeIcon);
		options.add(volumeSlider);

		Box info = Box.createHorizontalBox();
		info.add(Box.createGlue());
		info.add(clockLabel);
		info.add(Box.createHorizontalStrut(20));
		info.add(quitGame);
		info.add(Box.createHorizontalStrut(20));
		info.add(quitApplication);
		info.add(Box.createHorizontalStrut(10));

		setLayout(new GridLayout(1, 3));

		add(profil);
		add(options);
		add(info);

		Timer t = new Timer(1000, updateClockAction);
		t.start();
		control();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refreshProfile(Profile profile)
	{
		name.setText(profile.getName());
		avatar.setIcon(profile.getAvatar().getIcon());
		bankroll.setText(profile.getCapital() + " $");
	}

	public void setFrameMain(JFrameMain frameMain)
	{
		this.frameMain = frameMain;
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

	private void control()
	{
		quitApplication.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
		});

		quitGame.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				//TODO revenir menu principal
				if (frameMain != null)
				{
					frameMain.gameToMainMenu();
				}
			}
		});

		volumeSlider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				SoundEngine.getInstance().setVolume((float)(volumeSlider.getValue() / 100.0));
			}
		});
	}

	private ActionListener	updateClockAction	= new ActionListener()
												{
													public void actionPerformed(ActionEvent e)
													{
														clockLabel.setText(ft.format(new Date()));
													}
												};
}
