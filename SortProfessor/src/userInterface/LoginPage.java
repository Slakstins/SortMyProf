package userInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sortProfessor.services.ServiceManager;
import userInterface.JCool.CoolButton;
import userInterface.JCool.CoolLabel;
import userInterface.JCool.CoolPanel;
import userInterface.JCool.CoolTextField;

public class LoginPage extends Page {
	
	
	private JPanel cards;
	
	public LoginPage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader, JPanel cards) {
		super(frame, serviceManager, pageLoader);
		this.cards = cards;


        CoolLabel labelUsername = new CoolLabel("Username", 100, 100);
        CoolLabel labelPassword = new CoolLabel("Password", 100, 150);
        
        CoolTextField tfUsername = new CoolTextField(null, 200, 100); 
        JPasswordField tfPassword = new JPasswordField();
        tfPassword.setBounds(200, 150, 200, 20);

        CoolButton loginButton = new CoolButton("Login", 200, 200);

        loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] passwordArray = tfPassword.getPassword();
				String passwordString = new String(passwordArray);


				if (serviceManager.login(tfUsername.getText(), passwordString)) {
					System.out.println("logged in as " + tfUsername.getText());
					pageLoader.openHomePage();
				}
				else {
					System.out.println("login failed");
				}
			}
        });

        CoolButton registerButton = new CoolButton("Register", 300, 200);

        registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Page registerPage = new RegisterPage(frame, serviceManager, pageLoader, cards, tfUsername.getText(), tfPassword.getText());
				registerPage.open();
			}
        	
        });
        
		CoolPanel loginPanel = new CoolPanel();

        // Text Area at the Center
        loginPanel.add(tfUsername);
        loginPanel.add(tfPassword);

        loginPanel.add(labelUsername);
        loginPanel.add(labelPassword);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
        loginPanel.setVisible(true);
        cards.add(loginPanel, "LoginPage");
        //frame.revalidate(); //not sure if this is necessary
        //Adding Components to the frame.
        
        frame.setVisible(true);
	}

	
	public void open() {
		CardLayout layoutCards = (CardLayout)(cards.getLayout());
		layoutCards.show(cards, "LoginPage");



	}

	public void close() {
		// TODO Auto-generated method stub
		
	}



}
