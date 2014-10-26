/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run(){
		while(frontIsClear()){
			turnLeft();
			forward();
			turnAround();
			repair();
			turnLeft();
			goToNextColoumn();
		}
		turnLeft();
		forward();
		turnAround();
		repair();
		turnLeft();
	}
	
	/*
	 * forward
	 * Pre-Conditions	: Karel is facing north
	 * Process			: Karel moves toward the top of the coloumns
	 * Post-Conditions	: Karel end up facing a wall at the top of the coloumn
	 */
	
	private void forward(){
		while(frontIsClear()){
			move();
		}
	}
	
	/*
	 * repair
	 * Pre-Conditions	: Karel facing down the coloumn, Karel is on top of the coloumn
	 * Process			: Karel moves down the coloumn, while putting beepers
	 * 						  in the correct places
	 * Post-Conditions	: Karel end up facing a wall at the bottom of the coloumn
	 */
	
	private void repair(){
		while(frontIsClear()){
			if(beepersPresent()){
				
			}else{
				putBeeper();
			}
			move();
		}
		if(beepersPresent()){
			
		}else{
			putBeeper();
		}
	}
	
	/*
	 * goToNextColoumn
	 * Pre-Conditions	: Karel is facing east
	 * Process			: Karel moves 4 tiles, to the next coloumn
	 * Post-Conditions	: Karel end up facing east at the bottom of the new coloumn
	 */
	
	
	private void goToNextColoumn(){
		for(int x = 0; x<4; x++){
			move();
		}
	}
}
