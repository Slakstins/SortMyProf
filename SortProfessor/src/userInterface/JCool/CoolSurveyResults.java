package userInterface.JCool;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CoolSurveyResults extends JPanel {
	
	private final int yIncrement = 50;
	public CoolSurveyResults(ArrayList<String> surveyResults, int x, int y){
		CoolTextField houseName = new CoolTextField(surveyResults.get(0), x, y);
		CoolTextField score = new CoolTextField(surveyResults.get(1), x, y + yIncrement * 1);
		CoolTextField timeStamp = new CoolTextField(surveyResults.get(2), x, y + yIncrement * 2);
		CoolTextField comment = new CoolTextField(surveyResults.get(3), x, y + yIncrement * 3);
		CoolTextField username = new CoolTextField(surveyResults.get(4), x, y + yIncrement * 4);
		this.add(houseName);
		this.add(score);
		this.add(timeStamp);
		this.add(comment);
		this.add(username);
	}
	
	
	
	
	
	
	

}
