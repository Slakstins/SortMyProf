

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import sortProfessor.services.DatabaseConnectionService;
import sortProfessor.services.ServiceManager;
import userInterface.PageLoader;

public class ApplicationRunner {
	private String serverName;
	private String databaseName;
	private String serverUsername;
	private String serverPassword;
	public ApplicationRunner() {
	
	}

	public void runApplication(String[] args) {
		setDBProperties();
		DatabaseConnectionService dbConnectionService = new DatabaseConnectionService(serverName, databaseName);
		dbConnectionService.connect(serverUsername, serverPassword);
		ServiceManager serviceManager = new ServiceManager(dbConnectionService);
		
		
		
		PageLoader pageLoader = new PageLoader(serviceManager);
		//pageLoader.openLoginPage();
		pageLoader.openHomePage();
	}
	
	public void setDBProperties() {
		File serverDetailsFile = new File("SortProfessor.properties");
		Scanner reader = null;
		try {
			reader = new Scanner(serverDetailsFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found for server details");
			e.printStackTrace();
		}
		serverUsername = this.formatProperty(reader.nextLine());
		serverPassword = this.formatProperty(reader.nextLine());
		databaseName = this.formatProperty(reader.nextLine());
		serverName = this.formatProperty(reader.nextLine()); 

	}
	
	public String formatProperty(String property) {
		int equalsIndex = property.indexOf("=");
		return property.substring(equalsIndex + 1);
		
	}
}
