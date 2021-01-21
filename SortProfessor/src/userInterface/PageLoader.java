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
		frame = new JFrame();
		initializePages(frame);

		
	}
	//add pages here
	private void initializePages(JFrame frame) {
		loginPage = new LoginPage(frame);
		homePage = new HomePage(frame);
	}
	//add methods for controlling pages
	
	public void openHomePage() {
	
		homePage.open();
	}
	
	public void closeHomePage() {
		loginPage.close();
	}
	

	
}
