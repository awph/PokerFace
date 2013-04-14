
package ch.hearc.miscellaneoustest.simulation.poolAllInOne;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ch.hearc.pokerface.gameengine.gamecore.state.StateType;

public class XMLWriter
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private ConcurrentMap<String, Data>	map;
	private int							nbPlayer;
	private StateType						phaseName;
	private String						filename;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public XMLWriter(ConcurrentMap<String, Data> map, int nbPlayer, StateType phaseName, String filename)
	{
		this.map = map;
		this.nbPlayer = nbPlayer;
		this.phaseName = phaseName;
		this.filename = filename;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void write()
	{
		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("StatisticsHand");
			doc.appendChild(rootElement);

			DecimalFormat df = new DecimalFormat("#.##");

			Element enbPlayer = doc.createElement("NbPlayer");
			rootElement.appendChild(enbPlayer);

			Attr nbPlayerAttr = doc.createAttribute("id");
			nbPlayerAttr.setValue(String.valueOf(nbPlayer));
			enbPlayer.setAttributeNode(nbPlayerAttr);

			Element phase = doc.createElement("Phase");
			enbPlayer.appendChild(phase);

			Attr phaseAttr = doc.createAttribute("id");
			phaseAttr.setValue(phaseName.name());
			phase.setAttributeNode(phaseAttr);

			Set<Entry<String, Data>> entry = map.entrySet();
			for(Entry<String, Data> l:entry)
			{
				Element pocket = doc.createElement("Pocket");
				phase.appendChild(pocket);

				Attr pocketAttr = doc.createAttribute("id");
				pocketAttr.setValue(l.getKey());
				pocket.setAttributeNode(pocketAttr);

				Data d = l.getValue();
				double tot = d.getTotal();

				Element e = doc.createElement("Win");
				e.appendChild(doc.createTextNode(df.format(d.getWin())));
				pocket.appendChild(e);

				Set<Entry<String, Integer>> entry2 = d.getEntrySet();
				for(Entry<String, Integer> l2:entry2)
				{
					//We can't begin with a number, so we have to reverse the order of the letters
					Element elem = doc.createElement(new StringBuffer(l2.getKey()).reverse().toString());
					elem.appendChild(doc.createTextNode(df.format(l2.getValue()/tot*100.0)));
					pocket.appendChild(elem);
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename+".xml"));

			transformer.transform(source, result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
