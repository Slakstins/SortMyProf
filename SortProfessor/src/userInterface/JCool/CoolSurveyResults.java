package userInterface.JCool;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class CoolSurveyResults extends JPanel {
	
	public CoolSurveyResults(ArrayList<String> surveyResults){
		super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    JLabel emptyLabel = new JLabel("\n", SwingConstants.LEFT);
		JLabel houseName = new JLabel(surveyResults.get(0));
		JLabel score = new JLabel(surveyResults.get(1));
		JLabel timeStamp = new JLabel(surveyResults.get(2));
		JTextArea comment = new JTextArea(surveyResults.get(3));
		comment.setEditable(false);

		comment.setLineWrap(true);
		comment.setMaximumSize(new Dimension(700, 50));
		comment.setMinimumSize(new Dimension(700, 50));
		
		
		
		
		JLabel username = new JLabel(surveyResults.get(4));
		this.add(houseName);
		this.add(score);
		this.add(timeStamp);
		this.add(username);
		this.add(comment);
		this.add(emptyLabel);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	
	
	
	
	
	

}
