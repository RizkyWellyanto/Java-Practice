/* File: Foothill.java
 * -----------------------------------------------------------------------------
 * This program is due to Assignment_2B in course "FH CS 001 ML F13"
 * This program computes
 * Created By: Muhammad Rizky Wellyanto
 * Student ID: 20144588
 */

// import necessary classes
import java.util.Scanner;        // needed for scan the user's input

public class Foothill
{
   public static void main(String args[])
   {
      // declare necessary variables
      double input;              // hold whatever the input value is
      double doubleNum;          // the inputed value as a double
      long longNum;              // the inputed value as a long
      double outputExp1Double;   // output of expression 1 as a double
      double outputExp2Double;   // output of expression 2 as a double
      long outputExp1Long;       // output of expression 1 as a long
      long outputExp2Long;       // output of expression 2 as a long
      String message;            // message about inputting a value
      String errorMessage;       // message when user input a wrong value
      String messageExp1;        // output message of expression 1
      String messageExp2;        // output message of expression 2
      Scanner keyboard;          // input from the user through keyboard
      
      // name and ID number
      makeLine(80);
      System.out.println("Last Name     : Wellyanto");
      System.out.println("Student ID    : 20144588");
      makeLine(80);
      
      // create messages
      message = "Please enter a number. it can be a double. it can not be 3: ";
      errorMessage = "ERROR!!! the number can not be 3";
      messageExp1 = "(n to the n-1) to the n-2 for doubles is:     ";
      messageExp2 = "n to the (n-1 to the n-2) for doubles is:     ";
      
      // create an indefinite loop, so users can calculate multiple of times
      while (true)
      {
         // ask the user for an input
         System.out.print(message);
         keyboard = new Scanner(System.in);
         input = keyboard.nextDouble();
         
         // check whether the inputed number is 3 or not
         while (input == 3)
         {
            System.out.print(errorMessage + "\n" + message);
            keyboard = new Scanner(System.in);
            input = keyboard.nextDouble();
         }
         
         // assign the inputed number to the variables
         doubleNum = input;
         longNum = (long) input;
         
         // calculate everything
         outputExp1Double = exp1Double(doubleNum);
         outputExp1Long = exp1Long(longNum);
         outputExp2Double = exp2Double(doubleNum);
         outputExp2Long = exp2Long(longNum);
         
         // display the answer
         makeLine(80);
         System.out.println(messageExp1 + outputExp1Double);
         System.out.println(messageExp1 + outputExp1Long);
         System.out.println(messageExp2 + outputExp2Double);
         System.out.println(messageExp2 + outputExp2Long);
         makeLine(80);
      }
      
   }
   
   /* this method is just about creating a line with with n as parameter/length
    * just to make the program looks more "beautiful"
    */
   private static void makeLine(int n)
   {
      for(int i = 0; i < n; i++)
      {
         System.out.print("-");
      }
   }
   
   /* method name : exp1Double
    * this method calculates the number as a double using the first expression
    * post-cond   : throw in the doubleNum value as a parameter
    * pre-cond    : return the calculated value
    */
   private static double exp1Double(double doubleNum)
   {
      double result = 0.0;
      result = Math.pow((Math.pow(doubleNum, (doubleNum-1))),(doubleNum-2));
      return result;
   }
   
   /* method name : exp1Long
    * this method calculates the number as a float using the first expression
    * post-cond   : throw in the longNum value as a parameter
    * pre-cond    : return the calculated value
    */
   private static long exp1Long(long longNum)
   {
      long result = 0;
      result = (long) 
            Math.pow((Math.pow(longNum, (longNum-1f))),(longNum-2f));
      return result;
   }
   
   /* method name : exp2Double
    * this method calculates the number as a double using the second expression
    * post-cond   : throw in the doubleNum value as a parameter
    * pre-cond    : return the calculated value
    */
   private static double exp2Double(double doubleNum)
   {
      double result = 0.0;
      result = Math.pow(doubleNum, (Math.pow((doubleNum-1), (doubleNum-2))));
      return result;
   }
   
   /* method name : exp2Long
    * this method calculates the number as a float using the second expression
    * post-cond   : throw in the longNum value as a parameter
    * pre-cond    : return the calculated value
    */
   private static long exp2Long(long floatNum)
   {
      long result = 0;
      result = (long) 
            Math.pow(floatNum, (Math.pow((floatNum-1f), (floatNum-2f))));
      return result;
   }
}

/*---------------------------Sample Run-----------------------------------------



------------------------------------------------------------------------------*/

/*----------------------Questions and Answers-----------------------------------

Question 1  : What is the largest n that the user can enter and still get an 
approximately accurate double answer?
Answer      :

Question 2  : What is the largest n that the user can enter and still get an
approximately accurate long answer?
Answer      :

Question 3  : How do you know?
Answer      :

------------------------------------------------------------------------------*/

/* Note: actually, i want to make the program shorter, by reducing the used
 *       variable, declaring some variables at once, etc. but for the sake
 *       of clearance and readability, i tried my best to be as clean and as
 *       descriptive as i can. 
 */