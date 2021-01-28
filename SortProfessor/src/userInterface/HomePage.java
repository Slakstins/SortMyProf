package userInterface;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sortProfessor.services.ServiceManager;

public class HomePage extends Page {
	private JPanel cards;
	

	public HomePage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;
		// TODO Auto-generated constructor stub
		
        CoolLabel labelFName = new CoolLabel("FirstName", 100, 100);
        CoolLabel labelLName = new CoolLabel("LastName", 100, 150);
        
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
        
        cards.add(panel, "HomePage");
	
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open() {
		CardLayout layoutCards = (CardLayout)(cards.getLayout());
		layoutCards.show(cards, "HomePage");
	}

}
