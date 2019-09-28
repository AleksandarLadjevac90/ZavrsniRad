package zavrsniRad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	Connection connection;
	String connectionString;
	
	public DBConnection(String connectionString) {
		this.connectionString = connectionString;
	}
	
	private void connect() {
		try {
			connection = DriverManager.getConnection(this.connectionString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			if (this.connection == null || this.connection.isClosed()) {
				this.connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.connection;
	}
	
	public void disconnect() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
