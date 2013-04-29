
package ch.hearc.pokerface.gameengine.compute;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ch.hearc.pokerface.gameengine.subsets.Hand;

public class HandsPokerMap
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private static final String				FILENAME				= "pokerHands.txt";
	private static final String				SEPARATOR				= ";";
	private static final int				INDEX_RANK				= 0;
	private static final int				INDEX_KEY				= 1;
	private static final int				INDEX_SHORT_HAND_NAME	= 2;
	private static final int				INDEX_HAND_NAME			= 3;

	private static HandsPokerMap			instance;

	private Map<String, HandsPokerValue>	hands;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private HandsPokerMap()
	{
		hands = new HashMap<String, HandsPokerValue>();
		fill();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public synchronized static HandsPokerMap getInstance()
	{
		if (instance == null)
		{
			instance = new HandsPokerMap();
		}
		return instance;
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public HandsPokerValue getHand(String key)
	{
		return hands.get(key);
	}

	public HandsPokerValue getHand(Hand hand)
	{
		return hands.get(hand.getKey());
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * Fill the map with the file which contains all the possibile hands
	 */
	private void fill()
	{
		FileReader fr;
		BufferedReader br;
		try
		{
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String line;

			while((line = br.readLine()) != null)
			{
				String[] values = line.split(SEPARATOR);

				hands.put(values[INDEX_KEY], new HandsPokerValue(Integer.valueOf(values[INDEX_RANK]), values[INDEX_HAND_NAME], values[INDEX_SHORT_HAND_NAME]));
			}

			br.close();
			fr.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File not found !");
		}
		catch (IOException e)
		{
			System.err.println("Error during the reading process !");
		}
	}
}
