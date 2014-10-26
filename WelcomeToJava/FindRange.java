/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	private static final int sentinel = 0;
	//Declare a variable sentinel and fix its value to 0
		
	public void run(){ 

		int max = 0;
		//Declare and Initialize variable max as 0
		int min = 0;
		//Declare and Initialize variable min as 0
		
		while(true){
			int val = readInt("Enter next Integer: ");
			//Declare a variable val, and ask the user to input the integer
					
			if(max == 0){
				if(min == 0){
					max = val;
					min = val;
					if(val == sentinel){
						println("Error, the sentinel value has entered");
					}
				}
			}
			/*
			 * If the max and min variable is still 0 (which means just as the first time the
			 * program started), Initialize the first integer as the first main point of the range 
			 */
			
			if(val == sentinel)	break;
			//If the value stored is the same as the sentinel, break out of the loop
			
			if(val < min){
				min = val;
			}else{
				if(val > max){
					max = val;
				}
			}
			
		}
		/*
		 * Main Process
		 * ------------
		 * if value stored in variable val is less than the value that stored in min,
		 * set that value to the min variable. if the value stored in val is more than
		 * the value stored in max, set that value to the max variable. if the stored value
		 * in val variable is neither less than min nor more than max, that value 
		 * will be ignored.
		 */
				
		if(min == 0){
			if(max == 0){
			}else{
				println("The Maximum Range of these Integers Is: " + max);
				//Print the variable max on the console
				println("The Minimum Range of these Integers Is: " + min);
				//Print the variable min on the console
			}
		}
		
		/*
		 * Note: if the user happen to enter the sentinel value while the value stored in
		 * min variable and max variable is still zero (which means just as the program first
		 * started), skip the printline method of both min and max value.
		 */
		
	}
}

