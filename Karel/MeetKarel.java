/*
 * File: MeetKarel.java
 * --------------------
 * Its just a simple introduction with Karel
 */

import stanford.karel.*;

public class MeetKarel extends Karel {

	public void run() {
		move();
		turnRight();
		turnAround();
		move();
		move();
		
	}
	
	private void turnRight() {
		turnLeft();
		turnLeft();
		turnLeft();
		
	}
	
	private void turnAround(){
		turnLeft();
		turnLeft();
	}
	
}