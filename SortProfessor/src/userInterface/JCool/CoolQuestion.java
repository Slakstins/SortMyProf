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
	
	private ArrayList<JRadioButton> buttons;


	public CoolQuestion(String question, ArrayList<String> answers){
		super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.buttons = new ArrayList<JRadioButton>();
		 ButtonGroup buttonGroup = new ButtonGroup();
	     JRadioButton option1 = new JRadioButton();
	     JRadioButton option2 = new JRadioButton();
	     JRadioButton option3 = new JRadioButton();
	     JRadioButton  option4 = new JRadioButton();
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
	     buttons.add(option1);
	     buttons.add(option2);
	     buttons.add(option3);
	     buttons.add(option4);
	     this.add(questionText);
	     //randomize order

	     ArrayList<Integer> randomized = generateOrder(4);
	     while(randomized.size() > 0) {
	    	 switch(randomized.get(0)) {
	    	 case 0:
	    		 this.add(option1);
	    		 break;
	    	 case 1:
	    		 this.add(option2);
	    		 break;
	    	 case 2:
	    		 this.add(option3);
	    		 break;	
	    	 case 3:
	    		 this.add(option4);
	    		 break;}
	    	 randomized.remove(0);
	     }


	     this.add(emptyLabel);
	}
	
	//overcomplicated unncessary algorithm to scramble orders :)
	public ArrayList<Integer> generateOrder(int numAnswers) {
		ArrayList<Integer> randomized = new ArrayList<Integer>();
		ArrayList<Integer> toGet = new ArrayList<Integer>();
		for (int i = 0; i < numAnswers; i++) {
			toGet.add(i);
		}
		while(toGet.size() > 0) {
			int val = (int) (Math.random() * toGet.size()); //val is a number between 0 and size
			int removed = toGet.get(val);
			randomized.add(removed);
			for (int i = 0; i < toGet.size(); i++) {
				if (toGet.get(i) == removed) {
					toGet.remove(i);
					break;
				}
			}
		}
		return randomized;
	}
	
	/**
	 * returns the answer number indexed at 0
	 * returns -1 if unanswered
	 * @return
	 */
	public int getAnswer() {
		for (int i = 0; i < buttons.size(); i++) {
			JRadioButton curButton = buttons.get(i);
			if (curButton.isSelected()) {
				return i;
			}
		}

		return -1;

	}
       
        
}
