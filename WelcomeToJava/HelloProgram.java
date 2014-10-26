/*
 * File:HelloProgram.java
 * ----------------------
 * this simple program will print "Hello, World"
 */

import java.awt.*;			//Needed for colors
import acm.graphics.*;		//Needed for graphic objects
import acm.program.*;		//Needed for all programs

public class HelloProgram extends GraphicsProgram {
	public void run(){
		GLabel label = new GLabel("Hello, World", 100, 100);
		label.setColor(Color.RED);
		label.setFont("SansSerif-36");
		add(label);
	}
}
