package userInterface.JCool;

import java.awt.TextField;

public class CoolTextField extends TextField {
	private int x;
	private int y;
	private int width = 200;
	private int height = 20;
	private static int characters = 60;
	public CoolTextField(String title, int x, int y){
		super(title, characters);
		this.x = x;
		this.y = y;
	    this.setBounds(x, y, width, height);
	}
	
	public void setDimensions(int newWidth, int newHeight) {
		
	    this.setBounds(x, y, width, height);
	}

}


