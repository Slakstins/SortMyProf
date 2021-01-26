

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sortProfessor.services.DatabaseConnectionService;
import sortProfessor.services.ServiceManager;
import userInterface.PageLoader;

public class ApplicationRunner {
	//make these READ FROM A FILE
	private String serverName = "titan.csse.rose-hulman.edu";
	private String databaseName = "SortProfessor30";
	private String serverUsername = "SortProfessorUser30";
	private String serverPassword = "Password1234";	
	public ApplicationRunner() {
	
	}

	public void runApplication(String[] args) {

		DatabaseConnectionService dbConnectionService = new DatabaseConnectionService(serverName, databaseName);
		dbConnectionService.connect(serverUsername, serverPassword);
		
		ServiceManager serviceManager = new ServiceManager(dbConnectionService);
		serviceManager.addProfessor("Sriram", "Mohan");
		PageLoader pageLoader = new PageLoader(serviceManager);
		
		
		pageLoader.openLoginPage();
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
