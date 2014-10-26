/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run(){
		for(int x=0; x<2; x++){
			putBeeper();
			forward();
			putBeeper();
			checkMidpoint();
		}
		forwardToBeeper();
		turnLeft();
		takeLeftoverBeeper();
		forwardToBeeper();
	}
		
	/*
	 * checkMidpoint
	 * Pre-Condition	: Two beepers had been placed on each corner of the row/coloumn
	 * 					  and she's on top of one of those beepers
	 * Process			: Karel will move the two beepers one-by-one closer to each
	 * 					  other. once the two beeper is at one place, She'll stop.
	 * 					  indicating that the midpoint of that row/coloumn had been founded
	 * Post-Condition	: one beeper is present at the middle of that row/coloumn
	 */
	
	private void checkMidpoint(){
		while(beepersPresent()){
			turnAround();
			moveBeeper();
			if(frontIsClear()){
				move();
				forwardToBeeper();
			}else{
				turnAround();
				if(beepersPresent()){
					pickBeeper();
				}
				break;
			}
		}
	}
	
	/*
	 * moveBeeper
	 * Pre-Condition	: Karel is on top of a beeper
	 * Process			: She'll take the Beeper, move one tile toward the other beeper,
	 * 					  then put a beeper on that tile.
	 * Post-Condition	: She moved the beeper, one tile forward, and she's on top of it,
	 * 					  facing the same direction as before
	 * Note				: But if there's no beeper under Karel, she'll face a wall
	 * 					  so she can get out of the loop in the checkMidpoint method
	 */
	
	private void moveBeeper(){
		if(beepersPresent()){
			pickBeeper();
			move();
			if(noBeepersPresent()){
				putBeeper();
			}else{
				if(rightIsBlocked()){
					turnRight();
				}else{
					turnRight();
					forward();
				}
			}
		}
	}
	
	
	/*
	 * forwardToBeeper
	 * Pre-Condition	: ther's no beeper under Karel
	 * Process			: If there's no beeper under Karel, she'll go forward, until
	 * 					  she's on top of a beeper
	 * Post-Condition	: She's on top of a beeper
	 */
	
	
	private void forwardToBeeper(){
		while(noBeepersPresent()){
			forward();
		}
	}
	

	/*
	 * forward
	 * Pre-Condition	: There's no wall infront of Karel
	 * Process			: she'll keep moving until she's Blocked
	 * Post-Condition	: There's a wall infront of Karel, or She's on top of a beeper
	 * Note				: if she happens to be on top of a beeper, she'll also stops
	 */
	
	
	private void forward(){
		while(frontIsClear()){
			move();
			if(beepersPresent()){
				break;
			}
		}
	}
	
	
	/*
	 * takeLeftoverBeeper
	 * Pre-Condition	: Karel is in the middle of the map
	 * Process			: karel will go to the first row, to take that beeper, and turns around
	 * Post-Condition	: Karel has taken the leftover beepers
	 * Note				: In some maps, a beeper is left behind in the first row, so she
	 * 					  needs to get that beeper and go back to the midpoint of the map
	 */
	
	
	private void takeLeftoverBeeper(){
		forward();
		if(beepersPresent()){
			pickBeeper();
		}
		turnAround();
	}
	
}
