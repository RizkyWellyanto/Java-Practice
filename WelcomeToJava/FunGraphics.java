/*
 * File:FunGraphics
 * ----------------
 * just a simple graphic program
 */

import java.awt.*;			//Needed for colors

import acm.graphics.*;		//Needed for graphic objects
import acm.program.*;		//Needed for all programs

public class FunGraphics extends GraphicsProgram {
	public void run(){
		GLabel label = new GLabel("Hello, World", 100,100);
		label.setColor(Color.RED);
		label.setFont("SansSerif-36");
		add(label);
		
		GRect rect1 = new GRect(10, 10, 50, 50);
		add(rect1);
		
		GRect rect2 = new GRect(300, 100, 200, 100);
		rect2.setFilled(true);
		rect2.setFillColor(Color.GREEN);
		add(rect2);
		GOval oval = new GOval(300, 100, 200, 100);
		oval.setFilled(true);
		oval.setColor(Color.BLUE);
		add(oval);
		
		GLine line1 = new GLine(100, 150, 200, 175);
		add(line1);
		
		GLine dudeWheresMyLine = new GLine(0, 0, 100, 100);
		//common errors: the line has not added to the canvas yet
		
	}
}
