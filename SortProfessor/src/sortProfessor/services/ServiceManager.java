package sortProfessor.services;

public class ServiceManager {
	DatabaseConnectionService dbcs = null;
	//add services here
	private UserService userService;
	private AddProfessor addProfessor;
	private AddClass addClass;
	
	public ServiceManager(DatabaseConnectionService dbcs) {
		this.dbcs = dbcs;
		initializeServices();
	}
	
	/*
	 * add services here
	 */
	private void initializeServices() {
		addProfessor = new AddProfessor(dbcs);
		userService = new UserService(dbcs);
		addClass = new AddClass(dbcs);
		
	}
	
	
	
	//add services here

    public boolean addClass(String className) {
    	return addClass.addClass(className);
    }
    
    public boolean addProfessor(String fname, String lname) {
    	return addProfessor.addProfessor(fname, lname);
    }
    
    public boolean login(String username, String password) {
    	return userService.login(username, password);
    }
    
    public boolean register(String username, String password) {
    	return userService.register(username, password);
    }
    
    /*
     * called when the JFrame is closed
     */
    public void closeDatabaseConnection() {
    	dbcs.closeConnection();
    	
    }
    
	
}
