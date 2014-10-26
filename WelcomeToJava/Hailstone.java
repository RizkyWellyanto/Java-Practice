/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		
		int val = readInt("Enter an initial Number: ");
		//Declare the variable val, and ask the user to store value in it
		
		int step = 0;
		//Declare the variable step and initialize it as 0
		
		while(val != 1){
		//While the value stored in val is not equal to 1, the process in this loop keeps going on and on
			
			if((val%2) != 0){
				
				print(val + " Is an odd number.");
				val = (3*val)+1;
				println("So, I make 3n+1: " + val);
				/*
				 * if the remainder of val/2 is not equal to 0 (which means the val is an odd,
				 * multiplied it by 3 then add one
				 */
				
			}else{
				
				print(val + " Is an even number.");
				val = val/2;
				println("So, I take half: " + val);
				/*
				 * if the remainder val/2 is 0 (which means the val is an even,
				 * half the value stored in val
				 */
						
			}
			
			step += 1;
			//Everytime the process occur, add one to the step
		}
		
		println("The process took " + step + " steps to reach 1");
		//Print the number of steps to the canvas
		
	}
}

