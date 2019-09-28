package zavrsniRad;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
	public FileManager() {
		super();
	}
		
	public void insert(ArrayList<String> value, String fileLocation) throws IOException {
		FileWriter fileWriter = new FileWriter(fileLocation);
		for (String string : value) {
			fileWriter.write(string + System.lineSeparator());
		}
		
		fileWriter.close();
	}
}

