package userInterface;

import javax.swing.JFrame;

import sortProfessor.services.ServiceManager;

public abstract class Page {
	protected JFrame frame;
	
	public Page(JFrame frame, ServiceManager serviceManager) {
		this.frame = frame;
	}
	
	public abstract void open();
	public abstract void close();

	

}
