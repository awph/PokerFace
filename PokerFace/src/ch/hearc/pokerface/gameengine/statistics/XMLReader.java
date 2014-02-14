
package ch.hearc.pokerface.gameengine.statistics;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ch.hearc.pokerface.gameengine.compute.HandType;
import ch.hearc.pokerface.gameengine.gamecore.state.StateType;

public class XMLReader
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	public final static int	NB_MIN_PLAYER	= 2;
	public final static int	NB_MAX_PLAYER	= 10;

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Get all the values in the XML file
	 * @param filename : Filename
	 */
	public static Map<StateType, Map<String, StatisticValue>>[] getXMLValue(String filename)
	{
		@SuppressWarnings("unchecked")
		Map<StateType, Map<String, StatisticValue>>[] out = new Map[NB_MAX_PLAYER-NB_MIN_PLAYER+1];

		for(int i = 0; i < NB_MAX_PLAYER-NB_MIN_PLAYER; ++i)
		{
			out[i] = new HashMap<StateType, Map<String, StatisticValue>>();
		}

		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(ClassLoader.getSystemResourceAsStream(filename + ".xml"));

			doc.getDocumentElement().normalize();

			NodeList nlNbPlayer = doc.getElementsByTagName("NbPlayer");

			for(int i = 0; i < nlNbPlayer.getLength(); ++i)
			{
				NodeList nlNbPhase = doc.getElementsByTagName("Phase");
				out[i] = new HashMap<StateType, Map<String,StatisticValue>>();
				Map<String, StatisticValue> map = new HashMap<String, StatisticValue>();

				Element eNbPlayer = (Element) nlNbPlayer.item(i);

				for(int j = 0;j < nlNbPhase.getLength(); ++j)
				{
					NodeList nlPocket = eNbPlayer.getElementsByTagName("Pocket");
					Element ePhase = (Element) nlNbPhase.item(j);

					for(int k = 0; k < nlPocket.getLength(); ++k)
					{
						Element e = (Element) nlPocket.item(k);

						String key = e.getAttribute("id");
						StatisticValue sv = new StatisticValue(Double.valueOf(e.getElementsByTagName("Win").item(0).getTextContent()),Double.valueOf(e.getElementsByTagName("Tie").item(0).getTextContent()),Double.valueOf(e.getElementsByTagName("Loss").item(0).getTextContent()),Double.valueOf(e.getElementsByTagName("nbOpponantWinners").item(0).getTextContent()), Double.valueOf(e.getElementsByTagName(new StringBuilder(HandType.StraightFlush.getStringValue()).reverse().toString()).item(0).getTextContent()), Double.valueOf(e
								.getElementsByTagName(new StringBuilder(HandType.FourOfKind.getStringValue()).reverse().toString()).item(0).getTextContent()),
								Double.valueOf(e.getElementsByTagName(new StringBuilder(HandType.FullHouse.getStringValue()).reverse().toString()).item(0).getTextContent()), Double.valueOf(e.getElementsByTagName(new StringBuilder(HandType.Flush.getStringValue()).reverse().toString()).item(0)
										.getTextContent()), Double.valueOf(e.getElementsByTagName(new StringBuilder(HandType.Straight.getStringValue()).reverse().toString()).item(0).getTextContent()), Double.valueOf(e
										.getElementsByTagName(new StringBuilder(HandType.ThreeOfKind.getStringValue()).reverse().toString()).item(0).getTextContent()), Double.valueOf(e.getElementsByTagName(new StringBuilder(HandType.TwoPair.getStringValue()).reverse().toString()).item(0)
										.getTextContent()), Double.valueOf(e.getElementsByTagName(new StringBuilder(HandType.OnePair.getStringValue()).reverse().toString()).item(0).getTextContent()), Double.valueOf(e
										.getElementsByTagName(new StringBuilder(HandType.HighCard.getStringValue()).reverse().toString()).item(0).getTextContent()));
						map.put(key, sv);
					}
					out[i].put(StateType.valueOf(ePhase.getAttribute("id")), map);
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("Problem during the reading process !");
		}

		return out;
	}
}
