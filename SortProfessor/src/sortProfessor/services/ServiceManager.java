package sortProfessor.services;

import java.util.ArrayList;

import sortProfessor.services.Add.AddClass;
import sortProfessor.services.Add.AddProfessor;
import sortProfessor.services.Add.AddSchool;
import sortProfessor.services.Add.SubmitSurvey;
import sortProfessor.services.pull.PullProfByClass;
import sortProfessor.services.pull.PullProfSurveys;
import sortProfessor.services.pull.PullProfessors;
import sortProfessor.services.pull.PullQuestions;
import sortProfessor.services.pull.PullSchoolHouses;

public class ServiceManager {
	DatabaseConnectionService dbcs = null;
	//add services here
	private UserService userService;
	private AddProfessor addProfessor;
	private AddClass addClass;
	private AddSchool addSchool;
	private SubmitSurvey submitSurvey;
	private PullProfessors pullProfessors;
	private PullProfSurveys pullProfSurveys;
	private PullQuestions pullQuestions;
	private PullSchoolHouses pullSchoolHouses;
	private PullProfByClass pullProfByClass;
	private String currentUsername;
	private DataImporter dataImporter;
	
	public ServiceManager(DatabaseConnectionService dbcs) {
		this.dbcs = dbcs;
		initializeServices();
	}
	
	/*
	 * add services here
	 */
	private void initializeServices() {
		addProfessor = new AddProfessor(dbcs);
		userService = new UserService(dbcs, this);
		addClass = new AddClass(dbcs);
		addSchool = new AddSchool(dbcs);
		pullProfSurveys = new PullProfSurveys(dbcs);
		submitSurvey = new SubmitSurvey(dbcs);
		
		
		pullProfessors = new PullProfessors(dbcs);
		pullQuestions = new PullQuestions(dbcs);
		pullSchoolHouses = new PullSchoolHouses(dbcs);
		pullProfByClass = new PullProfByClass(dbcs);
		dataImporter = new DataImporter(dbcs, this);

		
	}
	
	//could make this take the filename, but formatting must be correct for excel file
	public void importData() {
		dataImporter.importAllData();
		
	}
	
	public void setUser(String username) {
		this.currentUsername = username;
	}
	
	public String getUser() {
		return this.currentUsername;
	}
	
	
	
	//add services here
	public ArrayList<ArrayList<String>> pullProfSurveys(String profID) {
		return pullProfSurveys.pullProfSurveys(profID);
	}

    public boolean addSchool(String schoolName) {
    	return addSchool.addSchool(schoolName);
    }
    
    public boolean submitSurvey(String profID, String studentUsername, String houseName, int score, String comment) {
    	return submitSurvey.submitSurvey(profID, studentUsername, houseName, score, comment);
    	
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
    
    public ArrayList<ArrayList<String>> pullSchoolHouses(String schoolName) {
    	return pullSchoolHouses.pullSchoolHouses(schoolName);
    }
    
    public ArrayList<ArrayList<String>> pullProfByClass(String schoolName, String className) {
    	return pullProfByClass.pullProfByClass(schoolName, className);
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
