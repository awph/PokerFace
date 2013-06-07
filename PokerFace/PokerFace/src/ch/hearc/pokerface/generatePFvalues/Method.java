
package ch.hearc.pokerface.generatePFvalues;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import ch.hearc.pokerface.gameengine.statistics.simulation.Data;


public class Method
{
	public static void displayMap(Map<String, Data> map)
	{
		Set<Entry<String, Data>> entry = map.entrySet();
		for(Entry<String, Data> l:entry)
		{
			System.out.println(l.getKey() + "\t:\t" + l.getValue());
		}
	}

	public static void displayMap(ConcurrentMap<String, Data> map)
	{
		Set<Entry<String, Data>> entry = map.entrySet();
		for(Entry<String, Data> l:entry)
		{
			System.out.println(l.getKey() + "\t:\t" + l.getValue());
		}
	}

	public static void writeMap(Map<String, Data> map,String filename) throws IOException
	{
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);

		Set<Entry<String, Data>> entry = map.entrySet();
		for(Entry<String, Data> l:entry)
		{
			bw.write(l.getKey() + ";" + l.getValue() + "\n");
		}

		bw.close();
		fw.close();
	}

	public static void writeAllMaps(List<Map<String, Data>> maps,String filename) throws IOException
	{
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);

		Set<String> keys = maps.get(0).keySet();
		int i = 0;
		for(String key:keys)
		{
			++i;
			StringBuilder sb = new StringBuilder(key);
			sb.append(";");

			for(Map<String,Data> map:maps)
			{
				sb.append(map.get(key));
				sb.append(";");
			}
			if (i != keys.size())
			{
				sb.append("\n");
			}
			bw.write(sb.toString());
		}

		bw.close();
		fw.close();
	}

	public static ConcurrentMap<String, Data> createConcurrentHashMap()
	{
		ConcurrentMap<String, Data> out = new ConcurrentHashMap<String, Data>();

		out.put("AAo", new Data());
		out.put("KKo", new Data());
		out.put("QQo", new Data());
		out.put("JJo", new Data());
		out.put("TTo", new Data());
		out.put("99o", new Data());
		out.put("88o", new Data());
		out.put("AKs", new Data());
		out.put("77o", new Data());
		out.put("AQs", new Data());
		out.put("AKo", new Data());
		out.put("AJs", new Data());
		out.put("ATs", new Data());
		out.put("AQo", new Data());
		out.put("AJo", new Data());
		out.put("KQs", new Data());
		out.put("66o", new Data());
		out.put("9As", new Data());
		out.put("ATo", new Data());
		out.put("JKs", new Data());
		out.put("8As", new Data());
		out.put("KTs", new Data());
		out.put("KQo", new Data());
		out.put("7As", new Data());
		out.put("9Ao", new Data());
		out.put("JKo", new Data());
		out.put("55o", new Data());
		out.put("JQs", new Data());
		out.put("8Ao", new Data());
		out.put("6As", new Data());
		out.put("9Ks", new Data());
		out.put("KTo", new Data());
		out.put("5As", new Data());
		out.put("QTs", new Data());
		out.put("7Ao", new Data());
		out.put("4As", new Data());
		out.put("8Ks", new Data());
		out.put("JQo", new Data());
		out.put("9Ko", new Data());
		out.put("3As", new Data());
		out.put("9Qs", new Data());
		out.put("6Ao", new Data());
		out.put("7Ks", new Data());
		out.put("5Ao", new Data());
		out.put("JTs", new Data());
		out.put("QTo", new Data());
		out.put("44o", new Data());
		out.put("2As", new Data());
		out.put("6Ks", new Data());
		out.put("4Ao", new Data());
		out.put("8Ko", new Data());
		out.put("8Qs", new Data());
		out.put("5Ks", new Data());
		out.put("9Js", new Data());
		out.put("3Ao", new Data());
		out.put("9Qo", new Data());
		out.put("7Ko", new Data());
		out.put("JTo", new Data());
		out.put("4Ks", new Data());
		out.put("2Ao", new Data());
		out.put("7Qs", new Data());
		out.put("6Ko", new Data());
		out.put("9Ts", new Data());
		out.put("8Js", new Data());
		out.put("8Qo", new Data());
		out.put("6Qs", new Data());
		out.put("3Ks", new Data());
		out.put("33o", new Data());
		out.put("9Jo", new Data());
		out.put("5Ko", new Data());
		out.put("5Qs", new Data());
		out.put("2Ks", new Data());
		out.put("8Ts", new Data());
		out.put("7Js", new Data());
		out.put("4Ko", new Data());
		out.put("7Qo", new Data());
		out.put("8Jo", new Data());
		out.put("4Qs", new Data());
		out.put("9To", new Data());
		out.put("3Ko", new Data());
		out.put("6Qo", new Data());
		out.put("89s", new Data());
		out.put("7Ts", new Data());
		out.put("6Js", new Data());
		out.put("3Qs", new Data());
		out.put("22o", new Data());
		out.put("5Qo", new Data());
		out.put("2Ko", new Data());
		out.put("8To", new Data());
		out.put("5Js", new Data());
		out.put("7Jo", new Data());
		out.put("2Qs", new Data());
		out.put("79s", new Data());
		out.put("6Ts", new Data());
		out.put("4Qo", new Data());
		out.put("4Js", new Data());
		out.put("89o", new Data());
		out.put("7To", new Data());
		out.put("78s", new Data());
		out.put("3Qo", new Data());
		out.put("6Jo", new Data());
		out.put("3Js", new Data());
		out.put("69s", new Data());
		out.put("5Ts", new Data());
		out.put("5Jo", new Data());
		out.put("2Js", new Data());
		out.put("2Qo", new Data());
		out.put("79o", new Data());
		out.put("68s", new Data());
		out.put("4Ts", new Data());
		out.put("6To", new Data());
		out.put("4Jo", new Data());
		out.put("59s", new Data());
		out.put("67s", new Data());
		out.put("78o", new Data());
		out.put("3Ts", new Data());
		out.put("3Jo", new Data());
		out.put("69o", new Data());
		out.put("58s", new Data());
		out.put("2Ts", new Data());
		out.put("5To", new Data());
		out.put("2Jo", new Data());
		out.put("49s", new Data());
		out.put("57s", new Data());
		out.put("68o", new Data());
		out.put("4To", new Data());
		out.put("39s", new Data());
		out.put("56s", new Data());
		out.put("59o", new Data());
		out.put("67o", new Data());
		out.put("48s", new Data());
		out.put("3To", new Data());
		out.put("29s", new Data());
		out.put("47s", new Data());
		out.put("58o", new Data());
		out.put("2To", new Data());
		out.put("46s", new Data());
		out.put("45s", new Data());
		out.put("57o", new Data());
		out.put("38s", new Data());
		out.put("49o", new Data());
		out.put("28s", new Data());
		out.put("56o", new Data());
		out.put("37s", new Data());
		out.put("39o", new Data());
		out.put("48o", new Data());
		out.put("36s", new Data());
		out.put("35s", new Data());
		out.put("29o", new Data());
		out.put("47o", new Data());
		out.put("27s", new Data());
		out.put("46o", new Data());
		out.put("34s", new Data());
		out.put("45o", new Data());
		out.put("38o", new Data());
		out.put("26s", new Data());
		out.put("25s", new Data());
		out.put("28o", new Data());
		out.put("37o", new Data());
		out.put("24s", new Data());
		out.put("36o", new Data());
		out.put("35o", new Data());
		out.put("23s", new Data());
		out.put("27o", new Data());
		out.put("34o", new Data());
		out.put("26o", new Data());
		out.put("25o", new Data());
		out.put("24o", new Data());
		out.put("23o", new Data());

		return out;
	}

	public static Map<String, Data> createHashMap()
	{
		Map<String, Data> out = new HashMap<String, Data>();

		out.put("AAo", new Data());
		out.put("KKo", new Data());
		out.put("QQo", new Data());
		out.put("JJo", new Data());
		out.put("TTo", new Data());
		out.put("99o", new Data());
		out.put("88o", new Data());
		out.put("AKs", new Data());
		out.put("77o", new Data());
		out.put("AQs", new Data());
		out.put("AKo", new Data());
		out.put("AJs", new Data());
		out.put("ATs", new Data());
		out.put("AQo", new Data());
		out.put("AJo", new Data());
		out.put("KQs", new Data());
		out.put("66o", new Data());
		out.put("9As", new Data());
		out.put("ATo", new Data());
		out.put("JKs", new Data());
		out.put("8As", new Data());
		out.put("KTs", new Data());
		out.put("KQo", new Data());
		out.put("7As", new Data());
		out.put("9Ao", new Data());
		out.put("JKo", new Data());
		out.put("55o", new Data());
		out.put("JQs", new Data());
		out.put("8Ao", new Data());
		out.put("6As", new Data());
		out.put("9Ks", new Data());
		out.put("KTo", new Data());
		out.put("5As", new Data());
		out.put("QTs", new Data());
		out.put("7Ao", new Data());
		out.put("4As", new Data());
		out.put("8Ks", new Data());
		out.put("JQo", new Data());
		out.put("9Ko", new Data());
		out.put("3As", new Data());
		out.put("9Qs", new Data());
		out.put("6Ao", new Data());
		out.put("7Ks", new Data());
		out.put("5Ao", new Data());
		out.put("JTs", new Data());
		out.put("QTo", new Data());
		out.put("44o", new Data());
		out.put("2As", new Data());
		out.put("6Ks", new Data());
		out.put("4Ao", new Data());
		out.put("8Ko", new Data());
		out.put("8Qs", new Data());
		out.put("5Ks", new Data());
		out.put("9Js", new Data());
		out.put("3Ao", new Data());
		out.put("9Qo", new Data());
		out.put("7Ko", new Data());
		out.put("JTo", new Data());
		out.put("4Ks", new Data());
		out.put("2Ao", new Data());
		out.put("7Qs", new Data());
		out.put("6Ko", new Data());
		out.put("9Ts", new Data());
		out.put("8Js", new Data());
		out.put("8Qo", new Data());
		out.put("6Qs", new Data());
		out.put("3Ks", new Data());
		out.put("33o", new Data());
		out.put("9Jo", new Data());
		out.put("5Ko", new Data());
		out.put("5Qs", new Data());
		out.put("2Ks", new Data());
		out.put("8Ts", new Data());
		out.put("7Js", new Data());
		out.put("4Ko", new Data());
		out.put("7Qo", new Data());
		out.put("8Jo", new Data());
		out.put("4Qs", new Data());
		out.put("9To", new Data());
		out.put("3Ko", new Data());
		out.put("6Qo", new Data());
		out.put("89s", new Data());
		out.put("7Ts", new Data());
		out.put("6Js", new Data());
		out.put("3Qs", new Data());
		out.put("22o", new Data());
		out.put("5Qo", new Data());
		out.put("2Ko", new Data());
		out.put("8To", new Data());
		out.put("5Js", new Data());
		out.put("7Jo", new Data());
		out.put("2Qs", new Data());
		out.put("79s", new Data());
		out.put("6Ts", new Data());
		out.put("4Qo", new Data());
		out.put("4Js", new Data());
		out.put("89o", new Data());
		out.put("7To", new Data());
		out.put("78s", new Data());
		out.put("3Qo", new Data());
		out.put("6Jo", new Data());
		out.put("3Js", new Data());
		out.put("69s", new Data());
		out.put("5Ts", new Data());
		out.put("5Jo", new Data());
		out.put("2Js", new Data());
		out.put("2Qo", new Data());
		out.put("79o", new Data());
		out.put("68s", new Data());
		out.put("4Ts", new Data());
		out.put("6To", new Data());
		out.put("4Jo", new Data());
		out.put("59s", new Data());
		out.put("67s", new Data());
		out.put("78o", new Data());
		out.put("3Ts", new Data());
		out.put("3Jo", new Data());
		out.put("69o", new Data());
		out.put("58s", new Data());
		out.put("2Ts", new Data());
		out.put("5To", new Data());
		out.put("2Jo", new Data());
		out.put("49s", new Data());
		out.put("57s", new Data());
		out.put("68o", new Data());
		out.put("4To", new Data());
		out.put("39s", new Data());
		out.put("56s", new Data());
		out.put("59o", new Data());
		out.put("67o", new Data());
		out.put("48s", new Data());
		out.put("3To", new Data());
		out.put("29s", new Data());
		out.put("47s", new Data());
		out.put("58o", new Data());
		out.put("2To", new Data());
		out.put("46s", new Data());
		out.put("45s", new Data());
		out.put("57o", new Data());
		out.put("38s", new Data());
		out.put("49o", new Data());
		out.put("28s", new Data());
		out.put("56o", new Data());
		out.put("37s", new Data());
		out.put("39o", new Data());
		out.put("48o", new Data());
		out.put("36s", new Data());
		out.put("35s", new Data());
		out.put("29o", new Data());
		out.put("47o", new Data());
		out.put("27s", new Data());
		out.put("46o", new Data());
		out.put("34s", new Data());
		out.put("45o", new Data());
		out.put("38o", new Data());
		out.put("26s", new Data());
		out.put("25s", new Data());
		out.put("28o", new Data());
		out.put("37o", new Data());
		out.put("24s", new Data());
		out.put("36o", new Data());
		out.put("35o", new Data());
		out.put("23s", new Data());
		out.put("27o", new Data());
		out.put("34o", new Data());
		out.put("26o", new Data());
		out.put("25o", new Data());
		out.put("24o", new Data());
		out.put("23o", new Data());

		return out;
	}
}
