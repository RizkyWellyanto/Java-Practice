/*
 * File: Checkerboard.java
 * -----------------------
 * a graphic program that'll make a checkerboard
 * using a tons of rectangle
 */

import java.awt.Color;	//Needed for colors
import acm.graphics.*;	//Needed for all graphic programs
import acm.program.*;	//Needed for all programs

public class Checkerboard extends GraphicsProgram {
	
	private static final  int nRows=8;
	//Set the number number of Rows
	private static final int nColumn=8;
	//Set the number of columns
	
	public void run(){
		int sqSize = getHeight() / nColumn;						//Define the size of the Square,  
		for(int i=0; i<nRows; i++) {							//Do the gridding in each Row
			for(int j=0; j<nColumn; j++){						//Do the gridding in each column
				
				int x = i*sqSize;
				//Set a new variable x as the starting x-axis point of the rectangles
				
				int y = j*sqSize;
				//Set a new variable y as the starting x-axis point of the rectangles
				
				GRect sq = new GRect(x, y, sqSize, sqSize);		//Create a new Rectangle 
				sq.setFilled(((i+j) % 2) != 0);
				/* Note: Boolean Expression
				 * look at the boolean expression, if the remainder of (i+j)/2 is not equal to zero.
				 * then the rectangle is filled with color, if not, the rectangle is blank
				 * thus all odd rectangle are blank, and even rectangle are filled with colors
				 * thus creating a checkerboard pattern
				 */
				
				sq.setFillColor(Color.BLACK);
				//Set the fill color of the rectangles
				add(sq);
				//Adding the rectagles to the canvas
				
			}
		}
	}
	
}
