package zavrsniRad;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class Main {

	public static void main(String[] args) {
		String connectionString = "jdbc:sqlite:C:/Users/Ladjevac/Desktop/Dictionary.db";
		String fileLocation = "C:/Users/Ladjevac/Desktop/test_knjiga.txt";

		DBConnection dbconnection = new DBConnection(connectionString);
		Book book = new Book(fileLocation);
		Dictionary dictionary = new Dictionary(dbconnection);

		try {
			ArrayList<String> dictionaryWords = dictionary.getAllWords();
			ArrayList<String> bookWords = book.getBookWords();

			/*
			 * Reci koje nedostaju u recniku a imaju u knjizi
			 */
			Comparation comparation = new Comparation(bookWords, dictionaryWords);
			ArrayList<String> missingWords = comparation.missingFromDictionary();
			dictionary.addNewWords(missingWords);
			
			/**
			 * Broj ponavljanja reci
			 */
			HashMap<String, Integer> brojPonavljanja = comparation.dictionaryWordOccurencesInBook();
			brojPonavljanja.forEach((key, value) -> System.out.println(key + value));
			
			/*
			 * Najkoriscenije reci iz knjge
			 */
			HashMap<String, Integer> najkoriscenije = new HashMap<String, Integer>();
			
			for (Map.Entry<String, Integer> entry : brojPonavljanja.entrySet()) {
				if (najkoriscenije.size() < 20) {
					najkoriscenije.put(entry.getKey(), entry.getValue());
				}
				
				HashMap<String, Integer> najkoriscenijeClone = (HashMap<String, Integer>) najkoriscenije.clone();
				
				for (Map.Entry<String, Integer> value : najkoriscenijeClone.entrySet()) {
					if (entry.getValue() > value.getValue() && !najkoriscenije.containsKey(entry.getKey())) {
						najkoriscenije.remove(value.getKey());
						najkoriscenije.put(entry.getKey(), entry.getValue());
					}
				}
			}
			
			najkoriscenije.forEach((key, value) -> {
				System.out.println(key + value);
			});

			/*
			 * Upisivanje svih reci
			 */
			ArrayList<String> sveReci = new ArrayList<String>();
			sveReci.addAll(dictionaryWords);
			sveReci.addAll(missingWords);
			Collections.sort(sveReci, String.CASE_INSENSITIVE_ORDER);
			
		    FileManager fileManager = new FileManager();
		    fileManager.insert(sveReci, "C:/Users/Ladjevac/Desktop/novereci.txt");
		} catch (SQLException | IOException e) {
			dbconnection.disconnect();
			e.printStackTrace();
		}
	}
}
