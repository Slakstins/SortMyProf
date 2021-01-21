package userInterface;

import javax.swing.JFrame;

public abstract class Page {
	JFrame frame;
	public Page(JFrame frame) {
		this.frame = frame;
	}
	
	public abstract void open();
	public abstract void close();

	

}
