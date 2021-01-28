package userInterface;

import javax.swing.JFrame;

import sortProfessor.services.ServiceManager;

public abstract class Page {
	protected JFrame frame;
	protected PageLoader pageLoader;
	protected ServiceManager serviceManager;
	
	public Page(JFrame frame, ServiceManager serviceManager, PageLoader pageLoader) {
		this.frame = frame;
		this.pageLoader = pageLoader;
		this.serviceManager = serviceManager;
	}
	
	public abstract void open();

}
