/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;				//Needed for all programs

public class PythagoreanTheorem extends ConsoleProgram {
	
	public void run() {
		println("Enter Values to complete the Pythagorean Theorem");
		double a;					//Declare variable a as a Double
		double b;					//Declare variable b as a Double
		
		a = readDouble("a: ");		//Ask the user to input the length of one of the triangle
		b = readDouble("b: ");		//Ask the user to input the other length of the triangle
		
		a = a*a;					//Square the variable stored in a
		b = b*b;					//Square the Variable stored in b
		
		Double c = Math.sqrt(a+b);	//Squareroot the sum of a and b
		println("c: " + c);			//print the number stored in c
		
	}
}
