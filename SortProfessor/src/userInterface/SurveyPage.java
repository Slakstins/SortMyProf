package userInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sortProfessor.services.ServiceManager;
import userInterface.JCool.CoolButton;
import userInterface.JCool.CoolLabel;
import userInterface.JCool.CoolPanel;
import userInterface.JCool.CoolTextField;

public class SurveyPage extends Page {
	
	
	private JPanel cards;
	
	public SurveyPage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;
        
		CoolPanel surveyPanel = new CoolPanel();

        // Text Area at the Center
        // TODO add the things here
        surveyPanel.setVisible(true);
        cards.add(surveyPanel, "SurveyPage");
        //frame.revalidate(); //not sure if this is necessary
        //Adding Components to the frame.
        
        frame.setVisible(true);
	}

	
	public void open() {
		CardLayout layoutCards = (CardLayout)(cards.getLayout());
		layoutCards.show(cards, "SurveyPage");



	}

	public void close() {
		// TODO Auto-generated method stub
		
	}



}
