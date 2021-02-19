package userInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import houseCalculations.ProfSorter;
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
	private ArrayList<CoolQuestion> questionPanels;
	private ArrayList<Integer> currentAnswers;
	private String profID = null;
	
	public SurveyPage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;
		surveyPanel = new CoolPanel();
		surveyPanel.setLayout(new BoxLayout(surveyPanel, BoxLayout.Y_AXIS));
		surveyPanel.add(Box.createRigidArea(new Dimension(40, 20)));	
		surveyPanel.setPreferredSize(new Dimension(1000, 1500));
        surveyPanel.setVisible(true);
        //ADD SCORE DROP DOWN
        
		scrollPane = new JScrollPane(surveyPanel);
        cards.add(scrollPane, "SurveyPage");
        
        frame.setVisible(true);
	}
	
	public void setProfID(String profID) {
		this.profID = profID;
	}
	
	
	public void initializeQuestions() {
		questionPanels = new ArrayList<CoolQuestion>();
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
					questionPanels.add(questionPanel);
					surveyPanel.add(questionPanel);
				}
			}
		}
	}

	
	public void open() {
	       CoolButton cancelButton = new CoolButton("Back", 0, 0);
	    cancelButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	close();
	    	pageLoader.openHomePage();
	    	}
	    });
	    cancelButton.setMaximumSize(new Dimension(60, 30));
	    surveyPanel.add(cancelButton);
		initializeQuestions();
        CoolButton submitButton = new CoolButton("Submit", 0, 0);
        submitButton.setMaximumSize(new Dimension(60, 30));
        surveyPanel.add(submitButton);
 

        submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkAllQuestionsAnswered()) {
					String house = ProfSorter.determineHouse(currentAnswers);
					close();
					pageLoader.openSurveyPage2(profID, house);
					
				}
			}
        });
		CardLayout layoutCards = (CardLayout)(cards.getLayout());
		layoutCards.show(cards, "SurveyPage");
	}
	
	/**
	 * more efficient to have an action listener for every buttonGroup
	 * that updates the arrayList value at that index in real time,
	 * but we only have 10 questions so nbd to just update them all 
	 * on submission.
	 * this method also sets the currentAnswers for the submission query
	 * 
	 * @return
	 */
	private boolean checkAllQuestionsAnswered() {
		currentAnswers = new ArrayList<Integer>();
		for (int i = 0; i < questionPanels.size(); i++) {
			CoolQuestion curQuestion = questionPanels.get(i);
			int answer = (curQuestion.getAnswer());
			currentAnswers.add(answer);
			if (answer == -1) {
				JOptionPane.showMessageDialog(null, "Not all questions are answered");
				return false;
			}
		}
		return true;
		
	}

	public void close() {
		surveyPanel.removeAll();
	}

}
