
package ch.hearc.pokerface.gui.profile;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import net.miginfocom.swing.MigLayout;

import ch.hearc.pokerface.gameengine.player.profile.Profile;
import ch.hearc.pokerface.gui.tools.ButtonTools;
import ch.hearc.pokerface.gui.tools.ColorShop;
import ch.hearc.pokerface.gui.tools.JPanelGlue;

public class NewProfileComponent extends ProfileComponentPanel
{

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JButton					okButton;
	private ProfileListContainer	parent;
	private JTextField				nameProfile;

	// Tools
	private Random					random	= new Random();

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public NewProfileComponent(ProfileListContainer parent)
	{
		super();

		this.parent = parent;

		geometry();
		control();
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

		setLayout(new GridLayout());
		okButton = new JButton("OK");
		okButton.setEnabled(false);

		JPanel okPanel = new JPanel();
		okPanel.setOpaque(false);
		okPanel.setLayout(new MigLayout(""));
		ButtonTools.setStyleToButton(okButton, "pink");
		okPanel.add(okButton, "pos 0.5al 0.5al");

		nameProfile = new JTextField("Name", 5);
		nameProfile.setDocument(new JTextFieldLimit(10));
		nameProfile.setForeground(ColorShop.PF_RED);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new MigLayout());
		namePanel.setOpaque(false);
		namePanel.add(nameProfile, "w 200, h 100, pos 0.5al 0.5al");

		try
		{
			nameProfile.setFont(ButtonTools.getButtonFont(false));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		nameProfile.setMaximumSize(new Dimension(200, 50));

		add(namePanel);
		add(new JPanelGlue(BoxLayout.X_AXIS));
		add(okPanel);

	}

	private void control()
	{
		okButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				NewProfileComponent.this.parent.addProfileFromNew(new Profile(nameProfile.getText().trim(), random.nextInt(30) + 1, 10000), NewProfileComponent.this);
				NewProfileComponent.this.parent.getProfilePanel().toggleCreateProfileButton();
			}
		});

		nameProfile.getDocument().addDocumentListener(new DocumentListener()
		{

			@Override
			public void removeUpdate(DocumentEvent arg0)
			{
				if (nameProfile.getText().trim().length() == 0)
				{
					okButton.setEnabled(false);
				}
				else
				{
					okButton.setEnabled(true);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				if (nameProfile.getText().trim().length() == 0)
				{
					okButton.setEnabled(false);
				}
				else
				{
					okButton.setEnabled(true);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent arg0)
			{
				if (nameProfile.getText().trim().length() == 0)
				{
					okButton.setEnabled(false);
				}
				else
				{
					okButton.setEnabled(true);
				}
			}
		});
	}

	@Override
	public Profile getProfile()
	{
		return null;
	}

	private class JTextFieldLimit extends PlainDocument
	{
		private int	limit;

		JTextFieldLimit(int limit)
		{
			super();
			this.limit = limit;
		}

		@Override
		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
		{
			if (str == null) { return; }

			if ((getLength() + str.length()) <= limit)
			{
				super.insertString(offset, str, attr);
			}
		}
	}
}
