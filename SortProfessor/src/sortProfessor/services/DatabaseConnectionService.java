package sortProfessor.services;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {

	//DO NOT EDIT THIS STRING, YOU WILL RECEIVE NO CREDIT FOR THIS TASK IF THIS STRING IS EDITED
	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password=${pass};";
	
	private Connection connection = null;
	
	private String databaseName;
	private String serverName;

	public DatabaseConnectionService(String serverName, String databaseName) {
		//DO NOT CHANGE THIS METHOD
		this.serverName = serverName;
		this.databaseName = databaseName;
	}

	
	public boolean connect(String user, String pass) {
		//TODO: Task 1
		//BUILD YOUR CONNECTION STRING HERE USING THE SAMPLE URL ABOVE
		String fullUrl = SampleURL
				.replace("${dbServer}", serverName)
				.replace("${dbName}", databaseName)
				.replace("${user}", user)
				.replace("${pass}", pass);
		try {
			connection = DriverManager.getConnection(fullUrl);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("connection to database failed");
			return false;
		}
	}
	

	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() {
		//TODO: Task 1
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
