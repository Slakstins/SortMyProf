package userInterface;

import javax.swing.JFrame;

import sortProfessor.services.ServiceManager;

public class PageLoader {
	//add pages here
	private JFrame frame;
	private LoginPage loginPage;
	private Page homePage;
	private ServiceManager serviceManager;
	
	public PageLoader(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
		frame = new JFrame("Sort My Professor");
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				serviceManager.closeDatabaseConnection();
				System.exit(0);
			}
			
			
			
		});
		initializePages(frame, serviceManager);

		
	}
	//add pages here
	private void initializePages(JFrame frame, ServiceManager serviceManager) {
		loginPage = new LoginPage(frame, serviceManager);
		homePage = new HomePage(frame, serviceManager);
	}
	//add methods for controlling pages
	
	public void openHomePage() {
	
		homePage.open();
	}
	
	public void closeHomePage() {
		homePage.close();
	}
	
	public void openLoginPage() {
		loginPage.open();
	}
	
	public void closeLoginPage() {
		loginPage.close();
	}
	

	
}
