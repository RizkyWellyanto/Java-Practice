/*
 * File: DoubleBeeperKarel.java
 * ----------------------------
 * Karel will double the Beeper, that existed infront of him
 */

import stanford.karel.*;

public class DoubleBeeperKarel extends SuperKarel {
	
	public void run(){
		move();
		doubleTheBeeper();
		moveBackward();
	}
	
	/*
	 * moveBackward
	 * Pre-Condition	: Karel is facing east
	 * Process			: he will turn around, then move, then turns around once more
	 * Post-Condition	: Karel will face east, a tile behind where he was
	 */
	
	private void moveBackward(){
		turnAround();
		move();
		turnAround();
	}
	
	/*
	 * doubleTheBeeper
	 * Pre-Condition	: Karel is on the Beeper pile wich will be doubled
	 * Process			: put the beeper to the next tile, then moves backward
	 * Post-Condition	: Karel will face the same direction, at the same place he was
	 * 						  standing
	 */
	
	private void doubleTheBeeper(){
		while(beepersPresent()){
			pickBeeper();
			putToNextPile();
			moveBackward();
		}
		
		takeBeepersBack();
	}
	
	/*
	 * putToNextTile
	 * Pre-Condition	: Karel is facing east, at the fist pile
	 * Process			: he will move to the pile in front of him,
	 * 					  then put two Beeper
	 * Post-Condition	: Karel will face east, at the Second Pile
	 */
	
	private void putToNextPile(){
		move();
		putBeeper();
		putBeeper();
	}
	
	/*
	 * takeBeepersBack
	 * Pre-Condition	: Karel is facing east, at the first Pile
	 * Process			: he will move to the second pile, take a beeper,
	 * 					  go to the first pile, then put the Beeper
	 * Post-Condition	: Karel will face east, at the first pile
	 */
	
	private void takeBeepersBack(){
		move();
		while(beepersPresent()){
			pickBeeper();
			moveBackward();
			putBeeper();
			move();
		}
		moveBackward();
	}
	
}
