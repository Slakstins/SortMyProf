package userInterface;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sortProfessor.services.ServiceManager;

public class PageLoader {
	//add pages here
	private JFrame frame;
	private LoginPage loginPage;
	private Page homePage;
	private ServiceManager serviceManager;
	private JPanel cards;
	static int frameWidth = 1080;
	static int frameHeight = 720;
	
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
	 */
	public void addTable(ArrayList<ArrayList<String>> contents, CoolPanel panel, int x, int y) {
		ArrayList<String> columnNames = new ArrayList<String>();
		for (int i = 0; i < contents.size(); i++) {
			//get the column name and remove it from the list
			ArrayList<String> curRow = contents.get(i);
			columnNames.add(curRow.get(0));
			//convert the row to a normal array and add it to data
			curRow.remove(0);

			
		}
		JTable table = new JTable();
		TableModel tableModel = new TableModel(contents, columnNames);
		table.setModel(tableModel);



		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(x, y, 300, 600);
		table.setFillsViewportHeight(true);
		panel.add(scrollPane);
		panel.revalidate();

		
	} 
	
	
	//add pages here
	private void initializePages(JFrame frame, ServiceManager serviceManager) {
		loginPage = new LoginPage(frame, serviceManager, this, cards);
		homePage = new HomePage(frame, serviceManager, this, cards);
	}
	//add methods for controlling pages
	
	public void openHomePage() {
	
		homePage.open();
	}
	
	
	public void openLoginPage() {
		loginPage.open();
	}
	
	

	
}
