package userInterface.JCool;

import java.awt.Button;



public class CoolButton extends Button {
	private int x;
	private int y;
	private int width = 100;
	private int height = 20;
	public CoolButton(String title, int x, int y){
		super(title);
		this.x = x;
		this.y = y;
	    this.setBounds(x, y, width, height);
	}
	
	public void setDimensions(int newWidth, int newHeight) {
		
	    this.setBounds(x, y, width, height);
	}

}
