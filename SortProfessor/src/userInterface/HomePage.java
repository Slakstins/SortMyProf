package userInterface;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import sortProfessor.services.ServiceManager;

public class HomePage extends Page {
	private JPanel cards;
	private JTabbedPane tabs;
	

	public HomePage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;
		this.tabs = new JTabbedPane();
		initializeTabs();
        
        cards.add(tabs, "HomePage");
	
	}
	private void initializeTabs() {
		//remember to update these
		createAddProfTab();
		createAddClassTab();
		createAddSchoolTab();
		
	}
	
	
	private void createAddProfTab(){

		
        CoolLabel labelFName = new CoolLabel("FirstName:", 100, 100);
        CoolLabel labelLName = new CoolLabel("LastName:", 100, 150);
        
        CoolTextField tfProfFName = new CoolTextField(null, 200, 100); 
        CoolTextField tfProfLName = new CoolTextField(null, 200, 150);

        CoolButton addProfButton = new CoolButton("AddProf", 200, 300);

        addProfButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (serviceManager.addProfessor(tfProfFName.getText(), tfProfLName.getText())) {
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
        CoolPanel panel = new CoolPanel();
        panel.add(addProfButton); 
        panel.add(labelFName);
        panel.add(labelLName);
        panel.add(tfProfFName);
        panel.add(tfProfLName);
        tabs.add(panel, "addProf");	
	}
	
	/*
	 * Should this be based on a school??
	 * Potential Problem: How will the user connect a school to a class without knowing
	 * what the keys are? There could be multiple schools with a specified name.
	 */
	private void createAddClassTab(){
		//Establish cool components and listeners
        CoolLabel labelClassName = new CoolLabel("ClassName:", 100, 100);
        CoolTextField tfClassName = new CoolTextField(null, 200, 100); 
        CoolButton addClassButton = new CoolButton("AddClass", 200, 300);

        addClassButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (serviceManager.addClass(tfClassName.getText())){
					System.out.println("Added Class: " + tfClassName.getText());
				}
				else {
					System.out.println("failed to add class");
				}
			}
        });	
		
		//Add components to a Cool panel
        CoolPanel panel = new CoolPanel();
        panel.add(labelClassName);
        panel.add(tfClassName);
        panel.add(addClassButton);
        //Add the panel to a new homePage tab
        tabs.add(panel, "addClass");	
	}
	
	
	private void createAddSchoolTab(){
		//Establish cool components and listeners
        CoolLabel labelSchoolName = new CoolLabel("SchoolName:", 100, 100);
        CoolTextField tfSchoolName = new CoolTextField(null, 200, 100); 
        CoolButton addSchoolButton = new CoolButton("AddSchool", 200, 300);

        addSchoolButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (serviceManager.addClass(tfSchoolName.getText())){
					System.out.println("Added School: " + tfSchoolName.getText());
				}
				else {
					System.out.println("failed to add School");
				}
			}
        });	
		
		//Add components to a Cool panel
        CoolPanel panel = new CoolPanel();
        panel.add(labelSchoolName);
        panel.add(tfSchoolName);
        panel.add(addSchoolButton);
        //Add the panel to a new homePage tab
        tabs.add(panel, "addSchool");	
	}


	@Override
	public void open() {
		CardLayout layoutCards = (CardLayout)(cards.getLayout());
		layoutCards.show(cards, "HomePage");
	}

}
