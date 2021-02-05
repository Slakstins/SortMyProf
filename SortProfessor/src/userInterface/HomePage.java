package userInterface;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import sortProfessor.services.ServiceManager;
import userInterface.JCool.CoolButton;
import userInterface.JCool.CoolLabel;
import userInterface.JCool.CoolPanel;
import userInterface.JCool.CoolTextField;

public class HomePage extends Page {
	private JPanel cards;
	private JTabbedPane tabs;

	

	public HomePage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;
		this.tabs = new JTabbedPane();
        
        cards.add(tabs, "HomePage");
	
	}
	private void initializeTabs() {
		//remember to update these
		createAddProfTab();
		createAddClassTab();
		createAddSchoolTab();
		createViewProfsTab();
		
	}
	
	
	private void createAddProfTab(){
		
        CoolLabel labelFName = new CoolLabel("FirstName:", 100, 100);
        CoolLabel labelLName = new CoolLabel("LastName:", 100, 150);
        CoolLabel schoolName = new CoolLabel("SchoolName:", 100, 200);

        
        CoolTextField tfProfFName = new CoolTextField(null, 200, 100); 
        CoolTextField tfProfLName = new CoolTextField(null, 200, 150);
        CoolTextField tfSchoolName = new CoolTextField(null, 200, 200);
        

        CoolButton addProfButton = new CoolButton("AddProf", 200, 300);
        
        CoolPanel panel = new CoolPanel();


        addProfButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (serviceManager.addProfessor(tfProfFName.getText(), tfProfLName.getText(), tfSchoolName.getText())) {
					System.out.println("Added professor: " + tfProfFName.getText() + " " +
				 tfProfLName.getText());
				}
				else {
					System.out.println("failed to addProf");
				}
			}
        });	
		//Establish cool components and listeners
		
		//Add components to a Cool panel
        this.addHomepageBase(panel);
        panel.add(schoolName);
        panel.add(tfSchoolName);
        panel.add(addProfButton); 
        panel.add(labelFName);
        panel.add(labelLName);
        panel.add(tfProfFName);
        panel.add(tfProfLName);
        tabs.add(panel, "addProf");	
	}
	
	

	
	/*
	 * Should this be based on a school??
	 * Potential Problem: How will the user connect a prof to a class without knowing
	 * what the keys are? There could be multiple profs with a specified name.
	 * One Idea: User searches for prof by name and selects the right one somehow.
	 */
	private void createAddClassTab(){
		//Establish cool components and listeners
        CoolLabel labelClassName = new CoolLabel("ClassName:", 100, 100);
        CoolTextField tfClassName = new CoolTextField(null, 200, 100); 
        CoolButton addClassButton = new CoolButton("AddClass", 300, 300);
        
        CoolButton searchProfsButton = new CoolButton("SearchProfs", 200, 300);
        
        CoolLabel labelProfName = new CoolLabel("ProfName:", 100, 160);
        CoolLabel labelProfFName = new CoolLabel("FirstName:", 100, 200);
        CoolLabel labelProfLName = new CoolLabel("LastName::", 100, 250);
        CoolTextField tfProfFname = new CoolTextField(null, 200, 200);
        CoolTextField tfProfLname = new CoolTextField(null, 200, 250);
        CoolPanel panel = new CoolPanel();
        
        ArrayList<String> header = new ArrayList<String>();
        
        header.add("ID");
        header.add("FirstName");
        header.add("LastName");
        header.add("SchoolName");
        header.add("AvgRating");
        JTable table = pageLoader.addTable(header, panel, 500, 100);
        
        searchProfsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<ArrayList<String>> results = serviceManager.pullProfessors(tfProfFname.getText(), tfProfLname.getText());
				TableModel model =(TableModel) table.getModel();
				model.setData(results);
			}
        });	
        
        addClassButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//this gets the ID for now. To not show the ID would be tricky
				TableModel model = (TableModel)table.getModel();
				String profIDString = (String) model.getValueAtByColumnString(table.getSelectedRow(), "ID");

				if (serviceManager.addClass(tfClassName.getText(), profIDString)){
					System.out.println("Added Class: " + tfClassName.getText() + " for prof with ID: " + profIDString);
				}
				else {
					System.out.println("failed to add class");
				}
			}
        });	
		
		//Add components to a Cool panel
        this.addHomepageBase(panel);
        panel.add(searchProfsButton);
        panel.add(labelProfName);
        panel.add(tfProfFname);
        panel.add(tfProfLname);
        panel.add(labelClassName);
        panel.add(tfClassName);
        panel.add(addClassButton);
        panel.add(labelProfFName);
        panel.add(labelProfLName);
        //Add the panel to a new homePage tab
        tabs.add(panel, "addClass");	
	}
	
	
	
	private void createViewProfsTab(){
		//Establish cool components and listeners
        CoolButton searchProfsButton = new CoolButton("SearchProfs", 200, 300);
        
        
        CoolLabel labelProfName = new CoolLabel("ProfName:", 100, 160);
        CoolLabel labelProfFName = new CoolLabel("FirstName:", 100, 200);
        CoolLabel labelProfLName = new CoolLabel("LastName:", 100, 250);
        
        CoolTextField tfProfFname = new CoolTextField(null, 200, 200);
        CoolTextField tfProfLname = new CoolTextField(null, 200, 250);
        CoolButton viewProfButton = new CoolButton("ViewProf", 300, 300);

        CoolPanel panel = new CoolPanel();
        
        ArrayList<String> header = new ArrayList<String>();
        
        header.add("ID");
        header.add("FirstName");
        header.add("LastName");
        header.add("SchoolName");
        header.add("AvgRating");

        JTable table = pageLoader.addTable(header, panel, 500, 100);
        
        
        viewProfButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//this gets the ID for now. To not show the ID would be tricky
				TableModel model = (TableModel)table.getModel();
				String profIDString = (String) model.getValueAtByColumnString(table.getSelectedRow(), "ID");
				//Switch over to a page with advanced data for the prof
				
				pageLoader.openProfDataPage(profIDString);

			}
        });	
        
        searchProfsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<ArrayList<String>> results = serviceManager.pullProfessors(tfProfFname.getText(), tfProfLname.getText());
				TableModel model =(TableModel) table.getModel();
				model.setData(results);
			}
        });	
        
		
		//Add components to a Cool panel
        this.addHomepageBase(panel);
        panel.add(searchProfsButton);
        panel.add(labelProfName);
        panel.add(labelProfFName);
        panel.add(labelProfLName);
        panel.add(tfProfFname);
        panel.add(tfProfLname);
        panel.add(viewProfButton);
        //Add the panel to a new homePage tab
        tabs.add(panel, "searchProfs");	
	}
	
	private void createAddSchoolTab(){
		//Establish cool components and listeners
        CoolLabel labelSchoolName = new CoolLabel("SchoolName:", 100, 100);
        CoolTextField tfSchoolName = new CoolTextField(null, 200, 100); 
        CoolButton addSchoolButton = new CoolButton("AddSchool", 200, 300);

        addSchoolButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (serviceManager.addSchool(tfSchoolName.getText())){
					System.out.println("Added School: " + tfSchoolName.getText());
				}
				else {
					System.out.println("failed to add School");
				}
			}
        });	
		
		//Add components to a Cool panel
        CoolPanel panel = new CoolPanel();
        this.addHomepageBase(panel);
        panel.add(labelSchoolName);
        panel.add(tfSchoolName);
        panel.add(addSchoolButton);
        //Add the panel to a new homePage tab
        tabs.add(panel, "addSchool");	
	}

	public void addHomepageBase(CoolPanel panel) {

		CoolLabel username = new CoolLabel("Welocme " + serviceManager.getUser() + "!", 20, 20);
		panel.add(username);
		
	}

	@Override
	public void open() {
		initializeTabs();
		CardLayout layoutCards = (CardLayout)(cards.getLayout());
		layoutCards.show(cards, "HomePage");
	}

}
