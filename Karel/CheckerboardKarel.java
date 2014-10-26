/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	
	public void run(){
			if(leftIsBlocked()){
				oneRowMap();
			}else if(frontIsBlocked()){
				oneColoumnMap();
			}else{
				normalMap(); 
			}
	}
	
	/*
	 *oneRowMap
	 *After Karel know she's on a map that only has one row,
	 *she'll go to this line of code instead
	 */
	
	private void oneRowMap(){
		while(frontIsClear()){
			putBeeper();
			if(frontIsClear()){
				move();
			}
			if(frontIsClear()){
				move();
			}
		}
	}
	
	/*
	 *oneColoumnMap
	 *After Karel know she's on a map that only has one coloumn,
	 *she'll go to this line of code instead
	 */
	
	private void oneColoumnMap(){
		while(leftIsClear()){
			putBeeper();
			forward();
			forward();
			if(leftIsBlocked()){
				break;
			}
		}
	}
	
	/*
	 * normalMap
	 * If Karel knows she's on neither of those special maps, she'll
	 * go to this line of code instead
	 */
	
	private void normalMap(){
		while(leftIsClear()){
			putBeeper();
			forward();
			forward();
		}
		move();
		if(beepersPresent()){
			moveBackward();
		}else {
			moveBackward();
			oneRowMap(); //The algorithm of this code is the same as the code in oneRowMap
			putTheLastBeeper();
		}
	}
	
	/*
	 * forward
	 * Pre-Condition	: -
	 * Process			: Karel will move forward if front is clear, but will change
	 * 					  go to the upper tile and change directions if she hits a
	 * 					  wall
	 * Post-Condition	: -
	 * 	 */
	
	private void forward() {
		if(frontIsClear()) {
			move();
		}else{
			if(facingEast()){
				repositionFacingWest();
			}else if(facingWest()){
				repositionFacingEast();
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
		if(frontIsClear()){
			move();
		}
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
		if(frontIsClear()){
			move();
		}
		turnRight();
	}
	
	/*
	 * moveBackward
	 * Pre-Condition	: -
	 * Process			: Karel will turn around, move forward, then turns around once more
	 * Post-Condition	: Karel will be one tile behind where she was, facing the same
	 * 					  direction
	 */
	
	private void moveBackward(){
		turnAround();
		move();
		turnAround();
	}
	
	/*
	 * putTheLastBeeper
	 * Note	: Karel will put the last beeper, to avoid OBOB
	 */
	
	private void putTheLastBeeper(){
		moveBackward();
		if(beepersPresent()){
			move();
		}else{
			move();
			putBeeper();
		}
	}
	
}
