
package ch.hearc.pokerface.gui.gamescreen.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JPanel;

import ch.hearc.pokerface.gameengine.gamecore.GameEngine;
import ch.hearc.pokerface.gameengine.player.Player;
import ch.hearc.pokerface.gui.gamescreen.player.PlayerComponent;

public class JPanelGameArea extends JPanel
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private List<PlayerComponent>	playerComponents;
	private GameEngine				gameEngine;
	//TODO BOARDPANEL

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelGameArea(GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;
		playerComponents = new ArrayList<PlayerComponent>();

		Box box = Box.createVerticalBox();
		Box playerBox = Box.createHorizontalBox();

		for(Player player:this.gameEngine.getPlayers())
		{
			playerComponents.add(new PlayerComponent(player));
			playerBox.add(playerComponents.get(playerComponents.size() - 1));
			playerBox.add(Box.createHorizontalStrut(15));
		}
		box.add(playerBox);
		box.add(Box.createVerticalStrut(50));
		//box.add(); //TODO BOARDPANEL
		add(box);

	}

	public void updateGUI()
	{
		//TODO BOARDPANEL.updateGUI()
		for(PlayerComponent playerComponent : playerComponents)
		{
			playerComponent.updateGUI();
		}
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

}
