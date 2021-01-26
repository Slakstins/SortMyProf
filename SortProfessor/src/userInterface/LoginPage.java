package userInterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sortProfessor.services.ServiceManager;

public class LoginPage extends Page {
	
	public LoginPage(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader) {
		super(frame, serviceManager, pageLoader);
	}

	
	public void open() {
	        JMenuBar mb = new JMenuBar();
	        JMenu m1 = new JMenu("FILE");
	        JMenu m2 = new JMenu("Help");
	        mb.add(m1);
	        mb.add(m2);
	        JMenuItem m11 = new JMenuItem("Open");
	        JMenuItem m22 = new JMenuItem("Save as");
	        m1.add(m11);
	        m1.add(m22);

	        //Creating the panel at bottom and adding components
	        CoolLabel labelUsername = new CoolLabel("Username", 100, 100);
	        CoolLabel labelPassword = new CoolLabel("Password", 100, 150);
	        
	        CoolTextField tfUsername = new CoolTextField(null, 200, 100); 
	        CoolTextField tfPassword = new CoolTextField(null, 200, 150);

	        CoolButton loginButton = new CoolButton("Login", 200, 200);

	        loginButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (serviceManager.login(tfUsername.getText(), tfPassword.getText())) {
						System.out.println("logged in as " + tfUsername.getText());
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
					if (serviceManager.register(tfUsername.getText(), tfPassword.getText())) {
						System.out.println("registered in as " + tfUsername.getText());
					}
					else {
						System.out.println("Registration failed");
					}
				}
	        	
	        });

	        // Text Area at the Center
	        frame.add(tfUsername);
	        frame.add(tfPassword);

	        frame.add(labelUsername);
	        frame.add(labelPassword);
	        frame.add(loginButton);
	        frame.add(registerButton);
	        //Adding Components to the frame.
	        
	        frame.setVisible(true);
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}



}
