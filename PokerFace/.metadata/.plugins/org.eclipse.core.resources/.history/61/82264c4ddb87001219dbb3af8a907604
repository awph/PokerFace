
package ch.hearc.coursjava.gui.j2d.traitTournant;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JPanelControle extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelControle(JPanelDessinTraitTournant jPanelDessinTraitTournant, OptionAnimation optionAnimation)
		{
		//input
		this.jPanelDessinTraitTournant = jPanelDessinTraitTournant;
		this.optionAnimation = optionAnimation;

		//tools
		this.intervalleSlider = new Interval(0, 100);
		this.intervalleAnimation = new Interval(2 * Math.PI / 100, 2 * Math.PI / 10);
		this.calibreur = new Calibreur(intervalleSlider, intervalleAnimation);

		geometrie();
		controle();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

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
		boutonStart = new JButton("BoutonStart");
		boutonStop = new JButton("BoutonStop");
		sliderAlpha = new JSlider(SwingConstants.HORIZONTAL, (int)intervalleSlider.getiStart(), (int)intervalleSlider.getiStop(), (int)intervalleSlider.getMilieu());

		setLayout(new FlowLayout(FlowLayout.CENTER));

		add(boutonStart);
		add(boutonStop);
		add(sliderAlpha);
		}

	private void controle()
		{
		boutonStart.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent event)
					{
					switchEtatBouton();
					jPanelDessinTraitTournant.startAnimation();
					}

			});

		boutonStop.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent event)
					{
					switchEtatBouton();
					jPanelDessinTraitTournant.stopAnimation();
					}
			});

		sliderAlpha.addChangeListener(new ChangeListener()
			{

				@Override
				public void stateChanged(ChangeEvent event)
					{
					double dalphaSlider = sliderAlpha.getValue();
					double dalphaCalibre = calibreur.calibrer(sliderAlpha.getValue());

					System.out.println(dalphaSlider);
					System.out.println(dalphaCalibre);

					optionAnimation.setdAlpha(dalphaCalibre);
					}
			});
		}

	private void apparence()
		{
		boutonStop.setEnabled(false);
		}

	private void switchEtatBouton()
		{
		boutonStart.setEnabled(!boutonStart.isEnabled());
		boutonStop.setEnabled(!boutonStop.isEnabled());
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Input
	private JPanelDessinTraitTournant jPanelDessinTraitTournant;
	private OptionAnimation optionAnimation;

	//Tools
	private JButton boutonStart;
	private JButton boutonStop;
	private JSlider sliderAlpha;

	private Interval intervalleSlider;
	private Interval intervalleAnimation;
	private Calibreur calibreur;
	}
