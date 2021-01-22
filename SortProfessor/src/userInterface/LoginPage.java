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
	private ServiceManager serviceManager;
	
	public LoginPage(JFrame frame, ServiceManager serviceManager) {
		super(frame, serviceManager);
		this.serviceManager = serviceManager;
	}

	
	public void open() {
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(1080, 720);
	        frame.setLayout(null);
	        //Creating the MenuBar and adding components
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
	        JLabel labelUsername = new JLabel("Username");
	        labelUsername.setBounds(100, 100, 100, 20);
	        JLabel labelPassword = new JLabel("Password");
	        labelPassword.setBounds(100, 150, 100, 20);
	        
	        
	        JTextField tfUsername = new JTextField(30); 
	        tfUsername.setBounds(200, 100, 200, 20);
	        
	        JTextField tfPassword = new JTextField(60);
	        tfPassword.setBounds(200, 150, 200, 20);
	        JButton loginButton = new JButton("Login");
	        loginButton.setBounds(200, 200, 100, 20);
	        loginButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (serviceManager.login(tfUsername.getText(), tfPassword.getText())) {
						System.out.println("logged in as " + tfUsername.getText());
					}
				}
	        });
	        
	        JButton registerButton = new JButton("Register");
	        registerButton.setBounds(300, 200, 100, 20);

	        
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
