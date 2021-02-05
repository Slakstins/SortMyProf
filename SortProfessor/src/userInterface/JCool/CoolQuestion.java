package userInterface.JCool;

import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class CoolQuestion extends Panel {
	
	public CoolQuestion(String question, ArrayList<String> answers){
		super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		 ButtonGroup buttonGroup = new ButtonGroup();
	     JRadioButton option1 = new JRadioButton();
	     JRadioButton option2 = new JRadioButton();
	     JRadioButton option3 = new JRadioButton();
	     JRadioButton option4 = new JRadioButton();
	     JLabel emptyLabel = new JLabel("\n", SwingConstants.LEFT);
	     
	     
	     
	     option1.setText(answers.get(0));
	     option2.setText(answers.get(1));
	     option3.setText(answers.get(2));
	     option4.setText(answers.get(3));
	     
	     JLabel questionText = new JLabel(question, SwingConstants.LEFT);
	     
	     
	     buttonGroup.add(option1);
	     buttonGroup.add(option2);
	     buttonGroup.add(option3);
	     buttonGroup.add(option4);	
	     this.add(questionText);
	     this.add(option1);
	     this.add(option2);
	     this.add(option3);
	     this.add(option4);
	     this.add(emptyLabel);
	}
       
        
}
