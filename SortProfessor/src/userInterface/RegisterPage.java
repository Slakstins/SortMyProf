package userInterface;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import sortProfessor.services.ServiceManager;
import userInterface.JCool.CoolButton;
import userInterface.JCool.CoolLabel;
import userInterface.JCool.CoolPanel;
import userInterface.JCool.CoolTextField;

public class RegisterPage extends Page {
	private JPanel cards;

	public RegisterPage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards, String username, String password) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;


        CoolLabel labelUsername = new CoolLabel("Username", 100, 100);
        CoolLabel labelPassword = new CoolLabel("Password", 100, 150);
        
        CoolTextField tfUsername = new CoolTextField(username, 200, 100); 
        JPasswordField tfPassword = new JPasswordField(password);
        tfPassword.setBounds(200, 150, 200, 20);

        CoolLabel labelFName = new CoolLabel("FName", 100, 225);
        CoolLabel labelLName = new CoolLabel("LName", 100, 275);

        CoolTextField tfFName = new CoolTextField(null, 200, 225);
        CoolTextField tfLName = new CoolTextField(null, 200, 275);

        CoolButton registerButton = new CoolButton("Register", 300, 325);
        CoolButton backToLogin = new CoolButton("back", 30, 30);
        
        backToLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText("");
				pageLoader.openLoginPage();
			}
        	
        });

        registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				char[] passwordArray = tfPassword.getPassword();
				String passwordString = new String(passwordArray);
				if (serviceManager.register(true, tfUsername.getText(), passwordString, tfFName.getText(), tfLName.getText())) {
					System.out.println("registered " + tfFName.getText() + " " + tfLName.getText() + " as "+ tfUsername.getText());
					tfPassword.setText("");
					// TAKE THE USER TO A PAGE TO FILL OUT MORE REGISTRATION INFO
					pageLoader.openHomePage();
				}

				else {
					JOptionPane.showMessageDialog(null, "Registration failed");
				}
			}
        	
        });
        
		CoolPanel loginPanel = new CoolPanel();

        // Text Area at the Center
        loginPanel.add(tfUsername);
        loginPanel.add(tfPassword);
        loginPanel.add(tfFName);
        loginPanel.add(tfLName);

        loginPanel.add(backToLogin);
        loginPanel.add(labelUsername);
        loginPanel.add(labelPassword);
        loginPanel.add(registerButton);
        loginPanel.add(labelFName);
        loginPanel.add(labelLName);
        loginPanel.setVisible(true);
        cards.add(loginPanel, "RegisterPage");

        frame.setVisible(true);
	}

	@Override
	public void open() {
		CardLayout layoutCards = (CardLayout)(cards.getLayout());
		layoutCards.show(cards, "RegisterPage");

	}

}
