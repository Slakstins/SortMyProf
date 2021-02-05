package userInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sortProfessor.services.ServiceManager;
import userInterface.JCool.CoolButton;
import userInterface.JCool.CoolLabel;
import userInterface.JCool.CoolPanel;
import userInterface.JCool.CoolQuestion;
import userInterface.JCool.CoolTextField;

public class SurveyPage extends Page {
	
	
	private JPanel cards;
	private CoolPanel surveyPanel;
	private JScrollPane scrollPane;
	private int questionsStartX = 100;
	private int questionsStartY = 100;
	
	public SurveyPage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;
		
		

        
		surveyPanel = new CoolPanel();
		surveyPanel.setLayout(new BoxLayout(surveyPanel, BoxLayout.Y_AXIS));
		
		surveyPanel.add(Box.createRigidArea(new Dimension(40, 20)));	
		
		
		surveyPanel.setPreferredSize(new Dimension(1080, 1500));
		
		

//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		
		
		
        // Text Area at the Center
        // TODO add the things here
        surveyPanel.setVisible(true);
        
        CoolButton cancelButton = new CoolButton("Back", 0, 0);
        

        cancelButton.setMaximumSize(new Dimension(60, 30));
        
        surveyPanel.add(cancelButton);

        //ADD SCORE DROP DOWN
        
		scrollPane = new JScrollPane(surveyPanel);
        
		scrollPane.setPreferredSize(new Dimension(PageLoader.frameWidth, PageLoader.frameHeight));
        cards.add(scrollPane, "SurveyPage");
        //frame.revalidate(); //not sure if this is necessary
        //Adding Components to the frame.
        
        frame.setVisible(true);
	}
	
	
	public void initializeQuestions() {

		
		ArrayList<ArrayList<String>> questions = serviceManager.pullQuestions();
		if (questions.size() > 0) {
			if (questions.get(0).size() > 0) {
				
				for (int i = 0; i < questions.get(0).size(); i++) {
					ArrayList<String> options = new ArrayList<String>();
					String question = questions.get(0).get(i);
					String option1 = questions.get(1).get(i);
					String option2 = questions.get(2).get(i);
					String option3 = questions.get(3).get(i);
					String option4 = questions.get(4).get(i);
					
					options.add(option1);
					options.add(option2);
					options.add(option3);
					options.add(option4);
					//
					CoolQuestion questionPanel = new CoolQuestion(question, options);
					surveyPanel.add(questionPanel);
				}
			}
		}
	}

	
	public void open() {
		initializeQuestions();
        CoolButton submitButton = new CoolButton("Submit", 0, 0);
        submitButton.setMaximumSize(new Dimension(60, 30));
        surveyPanel.add(submitButton);
		CardLayout layoutCards = (CardLayout)(cards.getLayout());
		layoutCards.show(cards, "SurveyPage");



	}

	public void close() {
		
	}



}
