package userInterface;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sortProfessor.services.ServiceManager;
import userInterface.JCool.CoolPanel;
import userInterface.JCool.CoolTextField;

public class PageLoader {
	//add pages here
	private JFrame frame;
	private LoginPage loginPage;
	private ProfDataPage profDataPage;
	private Page homePage;
	private ServiceManager serviceManager;
	private JPanel cards;
	public static int frameWidth = 1080;
	public static int frameHeight = 720;
	
	public PageLoader(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
		frame = new JFrame("Sort My Professor");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
	    frame.setSize(frameWidth, frameHeight);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				serviceManager.closeDatabaseConnection();
				System.exit(0);
			}
		});

	    cards = new JPanel(new CardLayout());
	    cards.setBounds(0, 0, 1080, 720);
	    frame.add(cards);
		initializePages(frame, serviceManager);
	}
	
	/*
	 * used by pages to add tables of queried data
	 * Returns a reference to the new table
	 */
	public JTable addTable(ArrayList<String> headers, CoolPanel panel, int x, int y) {
		
		JTable table = new JTable();
		TableModel tableModel = new TableModel(new ArrayList<ArrayList<String>>(), headers);
		table.setModel(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(x, y, 300, 400);
		panel.add(scrollPane);
		panel.revalidate();
		
		return table;
	} 
	
	
	//add pages here
	private void initializePages(JFrame frame, ServiceManager serviceManager) {
		profDataPage = new ProfDataPage(frame, serviceManager, this, cards);
		loginPage = new LoginPage(frame, serviceManager, this, cards);
		homePage = new HomePage(frame, serviceManager, this, cards);
	}
	//add methods for controlling pages
	
	public void openProfDataPage(String profID) {
		profDataPage.setProfID(profID);
		profDataPage.open();
		
	}
	

	
	public void openHomePage() {
	
		homePage.open();
	}
	
	
	public void openLoginPage() {
		loginPage.open();
	}
	
	

	
}
