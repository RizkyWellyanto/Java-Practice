/* File: Foothill.java
 * -----------------------------------------------------------------------------
 * This program is due to Assignment_4 in course "FH CS 001 ML F13"
 * This is more like a cashier program. it counts the yogurt(s) sold as well as 
 * the stamps. and give bonus yogurt for every ten stamps.
 * Created By: Muhammad Rizky Wellyanto
 * Student ID: 20144588
 */

//RECODE NEEDED!! 
//NO FILTERS!! IF INPUT IS WRONG -> ERROR MESSAGE -> GO BACK TO THE MAIN LOOP

// import necessary classes
import java.util.Scanner; // necessary to read user's input

public class Foothill2
{
   // declare necessary constants
   static final String INPUT_PROMPT =           // input prompt
         "   Input :";
   static final String SHUTDOWN_MESSAGE =       // shut down message
         "Program shutted down.";
   static final String CHOICE_DIRECTION =       // choice direction
         "DIRECTION: Please enter 'P' to process a purchase. Or 'S' to " +
         "shutdown.";
   static final String YOGURT_DIRECTION =       // yogurt direction
         "DIRECTION: Enter the amount of yogurt(S) to be purchased with " +
         "money. (Integer) :";
   static final String BONUS_YOGURT_DIRECTION = // bonus yogurt direction
         "DIRECTION: Enter the amount of yogurt(s) to be purchased with " +
         "stamps. Please make sure that you have enough stamps. Or else" +
         "it will be an Error. (Integer) :";
   static final String CUST_ID_DIRECTION =      // customer ID direction
         "DIRECTION: Enter the costumer's ID.";
   static final String NUM_CUST_DIRECTION =     // number of customer, direction
         "DIRECTION: Enter the number of customer(s) you have :";
      
   public static void main(String args[])
   {
      // declare local variables
      String inputString;           // hold any string the user inputed
      int cust_ID;                  // hold current costumer ID's
      int stamps[];                 // hold stamp records of each costumers
            
      // create a scanner object
      Scanner keyboard = new Scanner(System.in);
      
      // name and ID number
      createLine(80, "-");
      System.out.println("Last Name     : Wellyanto");
      System.out.println("Student ID    : 20144588");
      createLine(80, "-");
      
      // ask the user for the number of customers
      System.out.println(NUM_CUST_DIRECTION);
      createLine(80, "-");
      
      // filter the user's input
      do 
      {
         System.out.print(INPUT_PROMPT);
         inputString = keyboard.nextLine();
         
         // if the input is out of range, show error message
         if(Integer.parseInt(inputString) < 0)
         {
            error();
         }
      }
      while (Integer.parseInt(inputString) < 0);
      
      // preparing the cust_ID variable and stamps array
      cust_ID = Integer.parseInt(inputString);
      stamps = new int[cust_ID];
      setArray(Integer.parseInt(inputString), stamps);
      createLine(80, "-");
      
      // create an indefinite loop for multiple iterations
      while(true)
      {
         // make sure all local variable = 0 at the start of each iterations
         int decision = 0;
         int yogurt = 0;
         int bonusYogurt = 0;
         int totalYogurt = 0;
         
         // give the user 'P'urchase or 'S'hutdown choice
         System.out.println(CHOICE_DIRECTION);
         createLine(80, "-");
         
         // filter the user's input
         do 
         {
            System.out.print(INPUT_PROMPT);
            inputString = keyboard.nextLine();
            decision = checkDecision(inputString);
            
            // if the input is wrong, show error message
            if(decision == 0)
            {
               error();
            }
         } while (decision == 0);
         
         // if the user chose to shutdown, then exit the loop
         if(decision == -1) 
         {
            break;
         }
         createLine(80, "-");
         
         // ask the ID of the customer
         System.out.println(CUST_ID_DIRECTION);
         createLine(80, "-");
         
         // filter the user's input
         do 
         {
            System.out.print(INPUT_PROMPT);
            inputString = keyboard.nextLine();
            cust_ID = Integer.parseInt(inputString);
            
            // if the input is out of range, show error message
            if(cust_ID > stamps.length - 1 || cust_ID < 0)
            {
               error();
            }
         } while (cust_ID > stamps.length - 1 || cust_ID < 0);
         createLine(80, "-");
         
         // display customer's information
         displayCustomerInfo(cust_ID, stamps);
         
         // purchase yogurt with money
         System.out.println(YOGURT_DIRECTION);
         createLine(80, "-");
         
         // filter the user's input
         do 
         {
            System.out.print(INPUT_PROMPT);
            inputString = keyboard.nextLine();
            yogurt = Integer.parseInt(inputString);
            
            // if the input is out of range, show error message
            if(yogurt < 0) 
            {
               error();
            }
         } while (yogurt < 0);
         
         createLine(80, "-");
         
         // purchase yogurt with stamp
         System.out.println(BONUS_YOGURT_DIRECTION);
         createLine(80, "-");
         
         // filter the user's input
         do 
         {
            System.out.print(INPUT_PROMPT);
            inputString = keyboard.nextLine();
            bonusYogurt = Integer.parseInt(inputString);
            
            // if the input is out of range, show error message
            if(bonusYogurt < 0 || bonusYogurt > stamps[cust_ID] / 10)
            {
               error();
            }
         } while (bonusYogurt < 0 || bonusYogurt > stamps[cust_ID] / 10);
         createLine(80, "-");
         
         // calculate everything
         calculate(yogurt, bonusYogurt, stamps, cust_ID);
         totalYogurt = yogurt + bonusYogurt;
         
         // display result / receipt
         displayResult(cust_ID, totalYogurt, stamps);
         createLine(80, "-");
      }
      
      // end of the program
      keyboard.close();
      System.out.println(SHUTDOWN_MESSAGE);
      createLine(80, "-");
   }
   
   /* create a line
    * pre-cond    : throw in a "length" int and string "shape"
    * post-cond   : create a line with the length "length" and shape "shape"
    */
   private static void createLine(int length, String shape)
   {
      for(int counter = 0; counter < length; counter += 1)
      {
         System.out.print(shape);
      }
   }
   
   /* preparing the array
    * pre-cond    : throw in an integer
    * post-cond   : prepare the array, and set all of the values to zero
    */
   private static void setArray(int input, int stamps[])
   {
      stamps = new int [input];
      for(int counter = 0; counter < stamps.length - 1; counter += 1)
      {
         stamps[counter] = 0;
      }
   }
   
   /* check users input method
    * pre-cond    : throws in inputString as a parameter
    * post-cond   : check the user's first char from the input. if its P/p 
    *               this method will return 1. if its S/s this method will
    *               return -1. if the char is neither of both solution. it will
    *               return 0.
    */
   private static int checkDecision(String inputString)
   {
      char keyword;
      int output = 0;
      keyword = inputString.charAt(0);
      if(keyword == 'P' || keyword == 'p')
      {
         output = 1;
      }
      else if(keyword == 'S' || keyword == 's')
      {
         output = -1;
      }
      return output;
   }
   
   /* error message method
    * pre-cond    : -
    * post-cond   : display an error dialogue
    */
   private static void error()
   {
      System.out.println("Error! Your input is incorrect. It might be out " +
            "of range, mistype, etc. Please read the 'DIRECTION' and try " +
            "again.");
   }
   
   /* display customer's info
    * pre-cond    : -
    * post-cond   : read the current customer's record of stamps then display it
    */
   private static void displayCustomerInfo(int cust_ID, int stamps[])
   {
      System.out.println("CUSTOMER INFO:");
      System.out.println("   Costumer ID           :" + cust_ID);
      System.out.println("   Remaining stamps      :" + stamps[cust_ID]);
      System.out.println("   Possible Free Yogurt  :" + stamps[cust_ID] / 10);
      createLine(80, "-");
   }
   
   private static void displayResult(int cust_ID, int totalYogurt, int stamps[])
   {
      System.out.println("RECEIPT   :");
      System.out.println("   Costumer ID           :" + cust_ID);
      System.out.println("   Total yogurt bought   :" + totalYogurt);
      System.out.println("   Remaining stamps      :" + stamps[cust_ID]);
   }

   /* calculate stuffs
    * pre-cond    : throws in the value of yogurt, bonusYogurt, stamps array, 
    *               customer ID.
    * post-cond   : calculate the remaining stamp
    */
   private static int calculate(int yogurt, int bonusYogurt, int stamps[], 
         int cust_ID)
   {
      stamps[cust_ID] = stamps[cust_ID] - (bonusYogurt * 10) + yogurt;
      return cust_ID;
   }
}

/* Note: I learn a lot from my last week mistakes sir. you really gave me an
 *       important feedback. I will try to code better:
 *       1. use only static finals as globals
 *       2. expand the if's
 *       3. exit the program from main
 *       this time i tried so hard not to over embellish the code hahaha~.
 *       Is there still any room for improvement?
 *       Really looking forward for the next feedback.
 */

/*---------------------------Sample Run-----------------------------------------



------------------------------------------------------------------------------*/