/*
 * File: CleanUpKarel.java
 * -----------------------
 * Karel will clean up the world,
 * Karel will pickup every Beeper that present on the world
 * Beeper is present randomly everywhere, but only 1 Beeper
 * at a time
 */

import stanford.karel.*;

public class CleanUpKarel extends SuperKarel {
	
	public void run(){
		cleanRow();
		while(leftIsClear()){
			repositionFacingWest();
			cleanRow();
			if(rightIsClear()){
				repositionFacingEast();
				cleanRow();
			}
			/*
			 * Note: This else statements over here is critical, it makes Karel
			 * breaks out of the while loop, by turning around, she'll know
			 * that there's a ceiling on top of her, so the condition in the
			 * while loop is no longer satisfied. thus she'll break out of the
			 * loop, then stops.
			 */
			else {
			turnAround();	
			}
		}
	}
	
	/*
	 * cleanRow
	 * Pre-Condition	: Karel is at the starting point of a new row
	 * Process			: She'll move forward until she hit a wall, while
	 * 					  picking up beepers along the row (if any)
	 * Post-Condition	: There's a wall in front of her, and she had cleaned
	 * 					  all of the beepers in that current row (if any)
	 */
	
	private void cleanRow(){
		if(beepersPresent()){
			pickBeeper();
		}
		while(frontIsClear()){
			move();
			if(beepersPresent()){
				pickBeeper();
			}
		}
	}
	
	/*
	 * repositionFacingWest
	 * Pre-Condition	: There's a wall infront of Karel, and She's facing East
	 * Process			: Karel turn left, move to the upper row, then facing west
	 * Post-Condition	: Karel is facing west at the start of a new row
	 */
	
	private void repositionFacingWest(){
		turnLeft();
		move();
		turnLeft();
	}
	
	/*
	 * repositionFacingEast
	 * Pre-Condition	: There's a wall infront of Karel, and She's facing west
	 * Process			: Karel turn right, move to the upper row, then facing east
	 * Post-Condition	: Karel is facing east at the start of a new row
	 */
	
	private void repositionFacingEast(){
		turnRight();
		move();
		turnRight();
	}
	
}
