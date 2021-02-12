package userInterface;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import sortProfessor.services.ServiceManager;
import userInterface.JCool.CoolButton;
import userInterface.JCool.CoolLabel;
import userInterface.JCool.CoolPanel;
import userInterface.JCool.CoolTextField;

public class SurveyPage2 extends Page{
	
	private JPanel cards;
	private int rating = 1;
	private String houseName = null;
	private String profID = null;
	private CoolPanel surveyPanel2;
	private JLabel houseLabel;
	
	public SurveyPage2(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;

		houseLabel = new JLabel();
		houseLabel.setBounds(400, 50, 200, 20);

        JLabel instruction = new JLabel("Optional Comment:");
        instruction.setBounds(100, 100, 300, 20);
        
        JTextArea comment = new JTextArea(); 
        comment.setBounds(100, 140, 600, 100);

        CoolButton submitButton = new CoolButton("SortProf", 200, 400);
        
        Integer[] numbers = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        JComboBox<Integer> ratingSelector = new JComboBox<Integer>(numbers);
        CoolLabel ratingLabel = new CoolLabel("Rating: ", 100, 260);

        ratingSelector.setBounds(100, 300, 50, 20);
        
        ratingSelector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rating = (int) ratingSelector.getSelectedItem();
				//hoping this runs when the selected number is updated
			}
        	
        });

        submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serviceManager.submitSurvey(profID, serviceManager.getUser(), houseName, rating, comment.getText());

				JOptionPane.showMessageDialog(null, "Survey submitted successfully");
				comment.setText("");
				pageLoader.openHomePage();
				
				//submit using the addSurvey query
			}
        });


        
        surveyPanel2 = new CoolPanel();
        surveyPanel2.add(instruction);
        surveyPanel2.add(comment);
        surveyPanel2.add(submitButton);
        surveyPanel2.add(ratingLabel);
        surveyPanel2.add(ratingSelector);
		surveyPanel2.add(houseLabel);


        // Text Area at the Center
        cards.add(surveyPanel2, "SurveyPage2");
        
        frame.setVisible(true);
	}
	
	public void setHouseName(String housename) {
		this.houseName = housename;
		houseLabel.setText("Selected house is " + housename);

	}
	
	public void setProfID(String id) {
		this.profID = id;
		
	}

	
	public void open() {
		CardLayout layoutCards = (CardLayout)(cards.getLayout());
		layoutCards.show(cards, "SurveyPage2");
	}


}
