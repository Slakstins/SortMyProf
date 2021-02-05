package userInterface.JCool;

import java.awt.Panel;

import javax.swing.JPanel;

import userInterface.PageLoader;

public class CoolPanel extends JPanel {
	public CoolPanel() {
		super.setBounds(0, 0, PageLoader.frameWidth, PageLoader.frameHeight);
		this.setLayout(null);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
	}

}
