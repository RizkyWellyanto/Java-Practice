/*
 * File: SteepleChaseKarel.java
 * ----------------------------
 * Karel will do some steeple chase!
 * Karel will overcome any obstacle
 */

import stanford.karel.*;

public class SteepleChaseKarel extends SuperKarel {
	public void run(){
		for(int x = 0; x < 8; x++){
			if(frontIsClear()){
				move();
			}else {
				jumpHurdle();
			}
		}	
	}
	
	/*
	 * jumpHudle
	 * Pre-Condition	: theres a hurdle infront of Karel
	 * Process			: Karel will ascend, move, then descend
	 * Post-Condition	: Karel has jumped a hurdle
	 */
	
	private void jumpHurdle(){
		ascend();
		move();
		descend();
	}
	
	/*
	 * ascend
	 * Pre-Condition	: theres a hurdle infront of Karel
	 * Process			: Karel will turn left, move alongside the hurdle, then turn right
	 * Post-Condition	: Karel is on top of the hurdle, facing east
	 */
	
	private void ascend(){
		turnLeft();
		while(rightIsBlocked()){
			move();
		}
		turnRight();
	}
	
	/*
	 * descend
	 * Pre-Condition	: Karel is on top of the hurdle, facing east
	 * Process			: Karel will turn right, moves forward until it hits a wall,
	 * 					  then turn left
	 * Post-Condition	: Karel is at the bottom, and has jumped over the hurdle
	 */
	
	private void descend(){
		turnRight();
		forward();
		turnLeft();
	}
	
	/*
	 * forward
	 * Pre-Condition	: theres no wall infront of Karel
	 * Process			: Karel will keep moving until it hits a wall
	 * Post-Condition	: theres a wall infront of karel
	 */
	
	private void forward(){
		while(frontIsClear()){
			move();
		}
	}
}
