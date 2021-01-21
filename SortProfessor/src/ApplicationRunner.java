

import java.sql.Connection;

import sortProfessor.services.DatabaseConnectionService;
import sortProfessor.services.ServiceManager;
import userInterface.PageLoader;

public class ApplicationRunner {
	private String serverName = "titan.csse.rose-hulman.edu";
	private String databaseName = "SortProfessor30";
	private String serverUsername = "SodaBaseUserlakstise30";
	private String serverPassword = "Password123";	
	public ApplicationRunner() {
	
	}

	public void runApplication(String[] args) {
		DatabaseConnectionService dbConnectionService = new DatabaseConnectionService(serverName, databaseName);
		dbConnectionService.connect(serverUsername, serverPassword);
		Connection connection = dbConnectionService.getConnection();
		
		ServiceManager serviceManager = new ServiceManager(dbConnectionService);
		PageLoader pageLoader = new PageLoader(serviceManager);

		
		pageLoader.openHomePage();
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
