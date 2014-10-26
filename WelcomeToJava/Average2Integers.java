/*
 * file: Average2Integersjava
 * --------------------------
 * average the two integer that given by the user
 */

import acm.program.*;
//Needed for all program

public class Average2Integers extends ConsoleProgram{
	public void run(){
	
		int n1 = readInt("Input the first integer: ");
		int n2 = readInt("Input the Second integer: ");
		//ask the user to input a value on the n1, and n2 variable, then store it to that variable
		
		double average = (double)(n1+n2)/2;
		//takes the average out of those two variable
		
		println("The average of that two integer is: " + average + ".");
		//print the value on the canvas
		
	}
}
