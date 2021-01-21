package sortProfessor.services;

public class ServiceManager {
	DatabaseConnectionService dbcs = null;
	//add services here
	private AddProfessor addProfessor;
	
	public ServiceManager(DatabaseConnectionService dbcs) {
		this.dbcs = dbcs;
		initializeServices();
	}
	
	/*
	 * add services here
	 */
	private void initializeServices() {
		AddProfessor addProfessor = new AddProfessor(dbcs);
	}
	
	//add services here
    public void addProfessor(String fname, String lname) {
    	addProfessor.addProfessor(fname, lname);
    }
	
}
