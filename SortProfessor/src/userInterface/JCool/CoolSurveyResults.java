package userInterface.JCool;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CoolSurveyResults extends JPanel {
	
	public CoolSurveyResults(ArrayList<String> surveyResults){
		CoolLabel houseName = new CoolLabel(surveyResults.get(0));
		CoolLabel score = new CoolLabel(surveyResults.get(1));
		CoolLabel timeStamp = new CoolLabel(surveyResults.get(2));
		CoolLabel comment = new CoolLabel(surveyResults.get(3));
		CoolLabel username = new CoolLabel(surveyResults.get(4));
		this.add(houseName);
		this.add(score);
		this.add(timeStamp);
		this.add(comment);
		this.add(username);
	}
	
	
	
	
	
	
	

}
