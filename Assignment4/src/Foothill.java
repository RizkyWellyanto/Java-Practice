/* File: Foothill.java
 * -----------------------------------------------------------------------------
 * This program is due to Assignment_4 in course "FH CS 001 ML F13"
 * This is more like a cashier program. it counts the yogurt(s) sold as well as 
 * the stamps. and give bonus yogurt for every ten stamps.
 * Created By: Muhammad Rizky Wellyanto
 * Student ID: 20144588
 */

// import necessary classes
import java.util.Scanner; // necessary to read user's input

public class Foothill
{
   // declare necessary constants
   static final String TAB =                 // just for indentations
         "                             ";
   static final String MENU_TITLE =          // menu title
         "MAIN MENU";
   static final String SHUTDOWN_MESSAGE =    // shut down prompt
         "Program shutted down.";
   static final String CHOICE_PROMPT =       // choice prompt
         "Please enter 'P' to process a purchase. Or 'S' to shutdown :";
   static final String YOGURT_PROMP =        // yogurt direction
         "Enter the amount of yogurt(S) to be purchased (Integer) :";
   static final String BONUS_YOGURT_PROMPT = // bonus yogurt prompt
         "Enter the amount of bonus yogurt (if customer want some) :";
   static final String CUST_ID_PROMPT =      // customer ID prompt
         "Enter the costumer's ID :";
   static final String NUM_CUST_PROMPT =     // number of customer promp
         "Enter the number of customer(s) you have :";
   static final String NUM_CUST_ERROR =      // number of customer error message
         "Number of consumer(s) cannot be less than one";
      
   public static void main(String args[])
   {
      // declare local variables
      String inputString;           // hold any string the user inputed
      int cust_ID;                  // hold current costumer ID's
      int stamps[];                 // hold stamp records of each costumers
      
      // create a scanner object
      Scanner keyboard = new Scanner(System.in);
      
      // name and ID number
      createLine(80, "=");
      System.out.println("Last Name     : Wellyanto");
      System.out.println("Student ID    : 20144588");
      createLine(80, "=");
            
      // filter the user's input
      do 
      {
         System.out.print(NUM_CUST_PROMPT);
         inputString = keyboard.nextLine();
         
         // if the input is less than one, show error message
         if(Integer.parseInt(inputString) <= 0)
         {
            System.out.println(NUM_CUST_ERROR);
         }
      } while (Integer.parseInt(inputString) <= 0);
      
      // preparing the cust_ID variable and stamps array
      cust_ID = Integer.parseInt(inputString);
      stamps = new int[cust_ID];
      setArray(Integer.parseInt(inputString), stamps);
      
      // create an indefinite loop for multiple inputs
      while(true)
      {
         // reset necessary local variables at the start of each iterations
         boolean continuity = true;
         int yogurt = 0;
         int bonusYogurt = 0;
         int totalYogurt = 0;
         
         // display main menu sign
         createLine(80, "=");
         System.out.println(TAB + MENU_TITLE);
         createLine(80, "=");
         
         // Main menu option
         System.out.print(CHOICE_PROMPT);
         inputString = keyboard.nextLine();
         
         // check input
         if (inputString.charAt(0) == 'P' || inputString.charAt(0) == 'p')
         {
            continuity = true;
         }
         else if (inputString.charAt(0) == 'S' || inputString.charAt(0) == 's')
         {
            break;
         }
         else
         {
            error();
            continuity = false;
         }
         
         if (continuity == true)
         {
            // ask the ID of the customer
            createLine(80, "-");
            System.out.print(CUST_ID_PROMPT);
            inputString = keyboard.nextLine();
            cust_ID = Integer.parseInt(inputString);
            
            // check input
            if(cust_ID > stamps.length - 1 || cust_ID < 0)
            {
               error();
               continuity = false;
            }
         }
         
         if (continuity == true)
         {
            // display customer's information
            createLine(80, "-");
            displayCustomerInfo(cust_ID, stamps);
            
            // purchase yogurt with money
            System.out.print(YOGURT_PROMP);
            inputString = keyboard.nextLine();
            yogurt = Integer.parseInt(inputString);
            
            // check input
            if(yogurt < 0) 
            {
               error();
               continuity = false;
            }
         }
         
         if (continuity == true && (stamps[cust_ID] / 10) > 1)
         {
            // purchase yogurt with stamp
            createLine(80, "-");
            System.out.print(BONUS_YOGURT_PROMPT);
            inputString = keyboard.nextLine();
            bonusYogurt = Integer.parseInt(inputString);
            
            // check input
            if(bonusYogurt < 0 || bonusYogurt > stamps[cust_ID] / 10)
            {
               error();
               continuity = false;
            }
         }
         
         if (continuity == true)
         {
            // calculate everything
            createLine(80, "-");
            calculate(yogurt, bonusYogurt, stamps, cust_ID);
            totalYogurt = yogurt + bonusYogurt;
            
            // display result / receipt
            displayResult(cust_ID, totalYogurt, stamps);
         }
      }
      
      // end of the program
      keyboard.close();
      System.out.println(SHUTDOWN_MESSAGE);
      createLine(80, "=");
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
      
   /* error message method
    * pre-cond    : -
    * post-cond   : display an error dialogue
    */
   private static void error()
   {
      System.out.println("ERROR! Your input is incorrect. It might be out " +
      		"of range, mistype, etc. Next time, please read the prompt " +
      		"carefully. Program will go back to main menu.");
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
      System.out.println("RECEIPT      :");
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
 *       2. expand the if's (sometime i forgot about this)
 *       3. exit the program only from main
 *       this time i tried so hard not to over embellish the code hahaha~
 *       Is there still any room for improvement?
 *       Really looking forward for your next feedback.
 */

/*---------------------------Sample Run-----------------------------------------

================================================================================
Last Name     : Wellyanto
Student ID    : 20144588
================================================================================
Enter the number of customer(s) you have :10
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :t
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :p
--------------------------------------------------------------------------------
Enter the costumer's ID :200
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :Pro
--------------------------------------------------------------------------------
Enter the costumer's ID :-1
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :P
--------------------------------------------------------------------------------
Enter the costumer's ID :0
--------------------------------------------------------------------------------
CUSTOMER INFO:
   Costumer ID           :0
   Remaining stamps      :0
   Possible Free Yogurt  :0
--------------------------------------------------------------------------------
Enter the amount of yogurt(S) to be purchased (Integer) :1000
--------------------------------------------------------------------------------
RECEIPT      :
   Costumer ID           :0
   Total yogurt bought   :1000
   Remaining stamps      :1000
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :pRoCEeD!~
--------------------------------------------------------------------------------
Enter the costumer's ID :1
--------------------------------------------------------------------------------
CUSTOMER INFO:
   Costumer ID           :1
   Remaining stamps      :0
   Possible Free Yogurt  :0
--------------------------------------------------------------------------------
Enter the amount of yogurt(S) to be purchased (Integer) :91286301
--------------------------------------------------------------------------------
RECEIPT      :
   Costumer ID           :1
   Total yogurt bought   :91286301
   Remaining stamps      :91286301
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :p
--------------------------------------------------------------------------------
Enter the costumer's ID :10
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :9
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :9
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :P
--------------------------------------------------------------------------------
Enter the costumer's ID :9
--------------------------------------------------------------------------------
CUSTOMER INFO:
   Costumer ID           :9
   Remaining stamps      :0
   Possible Free Yogurt  :0
--------------------------------------------------------------------------------
Enter the amount of yogurt(S) to be purchased (Integer) :273572
--------------------------------------------------------------------------------
RECEIPT      :
   Costumer ID           :9
   Total yogurt bought   :273572
   Remaining stamps      :273572
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :P
--------------------------------------------------------------------------------
Enter the costumer's ID :10
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :p
--------------------------------------------------------------------------------
Enter the costumer's ID :0
--------------------------------------------------------------------------------
CUSTOMER INFO:
   Costumer ID           :0
   Remaining stamps      :1000
   Possible Free Yogurt  :100
--------------------------------------------------------------------------------
Enter the amount of yogurt(S) to be purchased (Integer) :0
--------------------------------------------------------------------------------
Enter the amount of bonus yogurt (if customer want some) :12
--------------------------------------------------------------------------------
RECEIPT      :
   Costumer ID           :0
   Total yogurt bought   :12
   Remaining stamps      :880
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :1
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :p
--------------------------------------------------------------------------------
Enter the costumer's ID :1
--------------------------------------------------------------------------------
CUSTOMER INFO:
   Costumer ID           :1
   Remaining stamps      :91286301
   Possible Free Yogurt  :9128630
--------------------------------------------------------------------------------
Enter the amount of yogurt(S) to be purchased (Integer) :90
--------------------------------------------------------------------------------
Enter the amount of bonus yogurt (if customer want some) :8768
--------------------------------------------------------------------------------
RECEIPT      :
   Costumer ID           :1
   Total yogurt bought   :8858
   Remaining stamps      :91198711
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :9
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :p
--------------------------------------------------------------------------------
Enter the costumer's ID :9
--------------------------------------------------------------------------------
CUSTOMER INFO:
   Costumer ID           :9
   Remaining stamps      :273572
   Possible Free Yogurt  :27357
--------------------------------------------------------------------------------
Enter the amount of yogurt(S) to be purchased (Integer) :-1
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :p
--------------------------------------------------------------------------------
Enter the costumer's ID :9
--------------------------------------------------------------------------------
CUSTOMER INFO:
   Costumer ID           :9
   Remaining stamps      :273572
   Possible Free Yogurt  :27357
--------------------------------------------------------------------------------
Enter the amount of yogurt(S) to be purchased (Integer) :12
--------------------------------------------------------------------------------
Enter the amount of bonus yogurt (if customer want some) :30000
ERROR! Your input is incorrect. It might be out of range, mistype, etc. Next tim
e, please read the prompt carefully. Program will go back to main menu.
================================================================================
                             MAIN MENU
================================================================================
Please enter 'P' to process a purchase. Or 'S' to shutdown :ShUttDoWnZzZ
Program shutted down.
================================================================================

------------------------------------------------------------------------------*/