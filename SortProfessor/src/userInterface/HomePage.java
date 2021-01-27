package userInterface;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sortProfessor.services.ServiceManager;

public class HomePage extends Page {
	private JPanel cards;
	

	public HomePage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;
		// TODO Auto-generated constructor stub
		
		
		//Establish cool components and listeners
        CoolLabel labelUsername = new CoolLabel("Homepage", 100, 100);
        CoolLabel labelPassword = new CoolLabel("WOW", 100, 150);
		
		
		//Add components to a Cool panel
        CoolPanel panel = new CoolPanel();
        
        panel.add(labelUsername);
        panel.add(labelPassword);
        
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
