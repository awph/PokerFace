
package ch.hearc.coursjava.gui.geometrieControl.curiosite;

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

public class JFrameCuriosite extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameCuriosite()
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

	private void apparence()
		{
		setSize(300, 300);
		setTitle("Curiosite");
		setLocation(30, 30);
		setResizable(true);
		this.setVisible(true);
		}

	private void control()
		{
		ActionListener listener = new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent event)
					{
					JButton btn = (JButton)event.getSource();

					System.out.println(btn.getText());

					}
			};

		boutonExterne.addActionListener(listener);
		boutonInterne.addActionListener(listener);

		controlRevision();
		}

	private void controlRevision()
		{
		getContentPane().addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent event)
					{
					//Attrape le this de JFrameCuriosite.
					//This tout court serait le this de ComponentAdapter
					System.out.println(JFrameCuriosite.this.getSize());
					}

			});

		//L'un ou l'autre mais pas les deux à la fois
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter()
			{

				@Override
				public void windowClosing(WindowEvent event)
					{
					//Faire quelque chose d'intelligent
					System.out.println("Fin de la JVM");
					System.exit(0); // 0 normal, -1 anormal
					}
			});

		boutonInterne.addMouseListener(new MouseAdapter()
			{

				@Override
				public void mouseClicked(MouseEvent event)
					{
					System.out.println(event.getPoint());
					}
			});
		}

	private void geometrie()
		{
		boutonExterne = new JButton("Bouton Externe");
		boutonInterne = new JButton("Bouton Interne");

		//V1 : syntaxe simplifée
		//add(boutonExterne);
		//setLayout(new FlowLayout());

		//V2 : version longue
		getContentPane().add(boutonExterne);
		getContentPane().setLayout(new FlowLayout());

		boutonExterne.add(boutonInterne);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JButton boutonExterne;
	private JButton boutonInterne;
	}
