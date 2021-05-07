package userInterface.JCool;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class CoolSurveyResults extends JPanel {
	
	public CoolSurveyResults(ArrayList<String> surveyResults){
		super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    JLabel emptyLabel = new JLabel("\n", SwingConstants.LEFT);
		JLabel houseName = new JLabel("House: " + surveyResults.get(0));
		JLabel score = new JLabel("Rating: " + surveyResults.get(1));
		JLabel timeStamp = new JLabel("Date of Sorting: " + surveyResults.get(2).substring(0, 10)); //10 to truncate timestamp
		JTextArea comment = new JTextArea(surveyResults.get(3));
		comment.setEditable(false);
		comment.setBackground(this.getBackground());

		comment.setLineWrap(true);
		comment.setMaximumSize(new Dimension(700, 50));
		comment.setMinimumSize(new Dimension(700, 50));
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.gray, Color.DARK_GRAY));
		
		JLabel username = new JLabel("Sorted by: " + surveyResults.get(4));
		username.setAlignmentX(LEFT_ALIGNMENT);
		houseName.setAlignmentX(LEFT_ALIGNMENT);
		score.setAlignmentX(LEFT_ALIGNMENT);
		timeStamp.setAlignmentX(LEFT_ALIGNMENT);
		this.add(username);
		this.add(houseName);
		this.add(score);
		this.add(timeStamp);
		this.add(comment);
		this.add(emptyLabel);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	
}
