package zavrsniRad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.sqlite.util.StringUtils;

public class Book {
	ArrayList<String> words;
	String bookLocation;
	
	public Book(String bookLocation) {
		this.words = new ArrayList<String>();
		this.bookLocation = bookLocation;
	}
	
	public ArrayList<String> getBookWords() throws IOException {
		String bookLine;
		String[] lineStrings;
		BufferedReader reader = this.getFileReader();

		while((bookLine = reader.readLine()) != null) {
			lineStrings = bookLine.split("\\W+");
			for (String string : lineStrings) {
				if (!string.isEmpty() && !string.chars().allMatch(Character::isDigit)) {
					this.setWord(string.toLowerCase());
				}
			}
		}
		
		reader.close();
		
		return this.words;
	}
	
	private BufferedReader getFileReader() throws FileNotFoundException {
		BufferedReader fileReader = new BufferedReader(
			new FileReader(this.bookLocation)
		);
		
		return fileReader;
	}
	
	private void setWord(String word) {
		this.words.add(word);
	}
}
