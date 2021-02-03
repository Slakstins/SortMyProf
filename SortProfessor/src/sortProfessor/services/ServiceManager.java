package sortProfessor.services;

import java.util.ArrayList;

import sortProfessor.services.Add.AddClass;
import sortProfessor.services.Add.AddProfessor;
import sortProfessor.services.Add.AddSchool;
import sortProfessor.services.pull.PullProfSurveys;
import sortProfessor.services.pull.PullProfessors;
import sortProfessor.services.pull.PullQuestions;

public class ServiceManager {
	DatabaseConnectionService dbcs = null;
	//add services here
	private UserService userService;
	private AddProfessor addProfessor;
	private AddClass addClass;
	private AddSchool addSchool;
	private PullProfessors pullProfessors;
	private PullProfSurveys pullProfSurveys;
	private PullQuestions pullQuestions;
	
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
		addSchool = new AddSchool(dbcs);
		pullProfSurveys = new PullProfSurveys(dbcs);
		
		
		pullProfessors = new PullProfessors(dbcs);
		pullQuestions = new PullQuestions(dbcs);
		
	}
	
	
	
	//add services here
	public ArrayList<ArrayList<String>> pullProfSurveys(String profID) {
		return pullProfSurveys.pullProfSurveys(profID);
	}

    public boolean addSchool(String schoolName) {
    	return addSchool.addSchool(schoolName);
    }
    

    public boolean addClass(String className, String profID) {
    	return addClass.addClass(className, profID);
    }
    
    public boolean addProfessor(String fname, String lname, String schoolID) {
    	return addProfessor.addProfessor(fname, lname, schoolID);
    }
    
    public ArrayList<ArrayList<String>> pullProfessors(String fname, String lname) {
    	return pullProfessors.pullProfessors(fname, lname);
    }
    
    public boolean login(String username, String password) {
    	return userService.login(username, password);
    }
    
    public boolean register(String username, String password, String firstName, String lastName) {
    	return userService.register(username, password, firstName, lastName);
    }
    
    public ArrayList<ArrayList<String>> pullQuestions() {
    	return pullQuestions.pullQuestions();
    }
    /*
     * called when the JFrame is closed
     */
    public void closeDatabaseConnection() {
    	dbcs.closeConnection();
    	
    }
    
	
}
