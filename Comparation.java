package zavrsniRad;

import java.util.ArrayList;
import java.util.HashMap;

public class Comparation {
	private ArrayList<String> bookWords;
	private ArrayList<String> dictionaryWords;
	
	public Comparation(ArrayList<String> bookWords, ArrayList<String> dictionaryWords) {
		this.bookWords = bookWords;
		this.dictionaryWords = dictionaryWords;
						}
	
	public ArrayList<String> missingFromDictionary() {
		ArrayList<String> newWords = new ArrayList<String>();
		
		for (String bookword : bookWords) {
			if(!dictionaryWords.contains(bookword) && !newWords.contains(bookword)) {
				newWords.add(bookword);
			}
		}
		return newWords;
		
		}
	
	public HashMap<String, Integer> dictionaryWordOccurencesInBook() {
		HashMap<String, Integer> rezultat = new HashMap<String, Integer>();
		
		for (String bookWord : bookWords) {
			if (rezultat.containsKey(bookWord)) {
				rezultat.put(bookWord, rezultat.get(bookWord) + 1);
				continue;
			}
			
			if (this.dictionaryWords.contains(bookWord)) {
				rezultat.put(bookWord, 1);
			}
		}
		
		return rezultat;
	}
	
}
