package userInterface;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import sortProfessor.services.ServiceManager;
import userInterface.JCool.CoolButton;
import userInterface.JCool.CoolLabel;
import userInterface.JCool.CoolPanel;
import userInterface.JCool.CoolSurveyResults;
import userInterface.JCool.CoolTextField;

public class ProfDataPage extends Page {
	private final int surveyResultsX = 400;
	private final int surveyResultsY = 100;
	private CoolPanel surveyDataPanel;
	
	private JPanel cards;
	private String profID = "-1";

	public ProfDataPage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;


        CoolLabel labelUsername = new CoolLabel("PlaceHolder", 100, 100);
        CoolLabel labelPassword = new CoolLabel("PlaceHolder", 100, 150);
        
        CoolTextField tfUsername = new CoolTextField(null, 200, 100); 
        JPasswordField tfPassword = new JPasswordField();
        tfPassword.setBounds(200, 150, 200, 20);

        CoolButton loginButton = new CoolButton("PlaceHolder", 200, 200);

        loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] passwordArray = tfPassword.getPassword();
				String passwordString = new String(passwordArray);


			}
        });

        CoolButton registerButton = new CoolButton("PlaceHolder", 300, 200);


        
		surveyDataPanel = new CoolPanel();

        // Text Area at the Center
        surveyDataPanel.add(tfUsername);
        surveyDataPanel.add(tfPassword);

        surveyDataPanel.add(labelUsername);
        surveyDataPanel.add(labelPassword);
        surveyDataPanel.add(loginButton);
        surveyDataPanel.add(registerButton);
        surveyDataPanel.setVisible(true);
        cards.add(surveyDataPanel, "ProfDataPage");
        //frame.revalidate(); //not sure if this is necessary
        //Adding Components to the frame.
        
        frame.setVisible(true);
	}
	public void loadDataPage(ArrayList<ArrayList<String>> surveysData) {
		if (surveysData.get(0).size() > 0) {
			for (int i = 0; i < surveysData.size(); i++) {
				ArrayList<String> surveyData = new ArrayList<String>();
					surveyData.add(surveysData.get(0).get(i));
					surveyData.add(surveysData.get(1).get(i));
					surveyData.add(surveysData.get(2).get(i));
					surveyData.add(surveysData.get(3).get(i));
					surveyData.add(surveysData.get(4).get(i));
					CoolSurveyResults coolSurveyResult = new CoolSurveyResults(surveyData, surveyResultsX, surveyResultsY);
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
