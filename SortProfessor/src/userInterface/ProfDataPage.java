package userInterface;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import sortProfessor.services.ServiceManager;
import userInterface.JCool.CoolButton;
import userInterface.JCool.CoolLabel;
import userInterface.JCool.CoolPanel;
import userInterface.JCool.CoolSurveyResults;
import userInterface.JCool.CoolTextField;

public class ProfDataPage extends Page {
	private CoolPanel surveyDataPanel;
	private JScrollPane scrollPane;
	
	private JPanel cards;
	private String profID = "-1";

	public ProfDataPage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;
		surveyDataPanel = new CoolPanel();
		surveyDataPanel.setLayout(new BoxLayout(surveyDataPanel, BoxLayout.Y_AXIS));
		surveyDataPanel.add(Box.createRigidArea(new Dimension(40, 20)));	
		surveyDataPanel.setPreferredSize(new Dimension(1000, 2000));
        surveyDataPanel.setVisible(true);
        //ADD SCORE DROP DOWN
        
		scrollPane = new JScrollPane(surveyDataPanel);
        cards.add(scrollPane, "ProfDataPage");
        frame.setVisible(true);
	}
	
	
	public void loadDataPage(ArrayList<ArrayList<String>> surveysData) {
		if (surveysData != null && surveysData.get(0).size() > 0) {
			for (int i = 0; i < surveysData.get(0).size(); i++) {
					ArrayList<String> surveyData = new ArrayList<String>();
					surveyData.add(surveysData.get(0).get(i));
					surveyData.add(surveysData.get(1).get(i));
					surveyData.add(surveysData.get(2).get(i));
					surveyData.add(surveysData.get(3).get(i));
					surveyData.add(surveysData.get(4).get(i));
					CoolSurveyResults coolSurveyResult = new CoolSurveyResults(surveyData);
					this.surveyDataPanel.add(coolSurveyResult);
			}
			surveyDataPanel.revalidate();
		}
		else {
			System.out.println("no surveys for that prof");
		}
	}

	public void open() {
		CardLayout layoutCards = (CardLayout)(cards.getLayout());
		ArrayList<ArrayList<String>> surveysData = serviceManager.pullProfSurveys(profID);
	
		System.out.println(surveysData);
		System.out.println("opened with profID = " + profID);
		this.loadDataPage(surveysData);

		layoutCards.show(cards, "ProfDataPage");
	}

	
	public void setProfID(String profID) {
		this.profID = profID;
		
	}

}
