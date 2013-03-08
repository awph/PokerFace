
package ch.hearc.coursjava.gui.geometrieControl.hello;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JFrameHello extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameHello()
		{
		geometry();
		control();
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

	private void geometry()
		{
		bouton1 = new JButton("Hello bouton1");
		bouton2 = new JButton("Hello bouton2");
		label = new JLabel("Hello Label");

		setLayout(new FlowLayout());
		//Java >= 6
		add(label);
		add(bouton1);
		add(bouton2);

		//Java <6
		//getContentPane().add(label);
		//getContentPane().add(bouton);
		}

	private void control()
		{
		//gestionFermetureBasic();
		gestionFermetureCostumisable();

		label.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent event)
					{
					System.out.println("Label : " + event.getPoint());
					}
			});

		getContentPane().addMouseListener(new MouseAdapter()
			{

				@Override
				public void mouseClicked(MouseEvent event)
					{
					System.out.println("CP : " + event.getPoint());
					}
			});

		getContentPane().addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent event)
					{
					System.out.println(getContentPane().getSize());
					}
			});

		bouton1.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent event)
					{
					System.out.println("Bouton1 : " + event.getPoint());

					}
			});

		ActionListener actionListener = actionListener();
		bouton1.addActionListener(actionListener);
		bouton2.addActionListener(actionListener);
		}

	private void gestionFermetureBasic()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		}

	private void gestionFermetureCostumisable()
		{
		addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(WindowEvent arg0)
					{
					System.out.println("Closing");
					//Sauver data
					//Demander validation utilisateur
					//...

					System.exit(0); // 0 normal, -1 anormal
					}
			});
		}

	private ActionListener actionListener()
		{
		return new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent event)
					{
					JButton boutonSource = (JButton)event.getSource();

					System.out.println("Click from " + boutonSource.getText());
					}
			};
		}

	private void apparence()
		{
		bouton1.setBackground(new Color(100, 100, 100));
		label.setForeground(new Color(255, 100, 100));
		//label.setOpaque(true);
		//label.setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		apparenceFrame();
		}

	private void apparenceFrame()
		{
		setTitle("Hello");
		setSize(600, 400);
		setLocation(100, 100);
		setResizable(true);
		//Ne pas oublier !
		//Conseil : Dernière instruction du constructeur
		setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JLabel label;
	private JButton bouton1;
	private JButton bouton2;
	}
