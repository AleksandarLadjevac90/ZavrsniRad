package zavrsniRad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dictionary {
	DBConnection connection;
	
	public Dictionary(DBConnection connection) {
		this.connection = connection;
	}
	
	public ArrayList<String> getAllWords() throws SQLException {
		String upit = " SELECT DISTINCT word" +
	                  " FROM entries";
		Connection connection = this.connection.getConnection();
		
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(upit);
		
		ArrayList<String> words = new ArrayList<String>();
		while(result.next()) {
			words.add(result.getString(1).toLowerCase());
		}
		
		connection.close();
		
		return words;
	}
	
	public void addNewWords(ArrayList<String> newWords) throws SQLException {
		String query = "INSERT INTO `missing_words` "
				+ "(`word`) VALUES ";
		
		for (String string : newWords) {
			query = query.concat("\n(?),");
		}
		// remove last comma
		query = query.substring(0, query.length() -1);
		
		Connection connection = this.connection.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		
		int index = 1;
		for (String string : newWords) {
			statement.setString(index++, string);
		}
		
		statement.execute();
		System.out.println("Successfully inserted new words");
		connection.close();
	}
}
