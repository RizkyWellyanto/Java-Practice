/*
 * File: LoopAndAHalf.java
 * -----------------------
 * a demonstration about an indefinite loop
 */

import acm.program.ConsoleProgram;	//Needed for all programs

public class LoopAndAHalf  extends ConsoleProgram {
	
	private static final int sentinel = 0;
	//Declare a variable sentinel and fix its value to 0
	
	public void run(){
		
		int total = 0;
		//Declare the total variable 
		
		while(true){
			int val = readInt("Enter next Integer: ");
			//Declare a variable val, and ask the user to input the integer
			if(val == sentinel)	break;
			//If the value stored is the same as the sentinel, break out of the loop
			total += val;
			//If the loop doesn't break yet, value stored in val will be added to total
		}
		println("The total value is: " + total + ".");
		//Print the total to the canvas
	}
	
}
