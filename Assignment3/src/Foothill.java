/* File: Foothill.java
 * -----------------------------------------------------------------------------
 * This program is due to Assignment_3 in course "FH CS 001 ML F13"
 * This program calculates the nutrition of each recipe. by calculating the
 * nutrition of its each ingredients
 * Created By: Muhammad Rizky Wellyanto
 * Student ID: 20144588
 */

import java.util.Scanner;              // needed for scanning input from user
import java.text.DecimalFormat;        // just to make the display 'beautiful'

public class Foothill
{
   /* declare necessary constants-----------------------------------------------
    * all datas are from: http://nutritiondata.self.com
    * all of the nutrients data are in 100 gram serving size
    */
   static final String INGREDIENTS[] = // name of the ingredients
      {"Apple", "Avocado", "Orange", "Banana", "Strawberry", 
      "Dark Chocolate Bar", "Milk", "Ice Cream", "Sugar", "Carrot"};
   static final double CALORIES[] =    // calories (calories)
      {52, 160, 47, 89, 32, 556, 97, 162, 389, 41};
   static final double PROTEIN[] =     // protein (grams)
      {0.3, 2, 0.9, 1.1, 0.7, 5.5, 3.7, 4.8, 0, 0.9};
   static final double CARBOHYDRATE[] =// carbohydrate (grams)
      {13.8, 8.5, 11.7, 22.8, 7.7, 60.5, 5.2, 25.3, 99.6, 9.6};
   static final double FAT[] =         // fat (grams)
      {0.2, 14.7, 0.1, 0.3, 0.3, 32.4, 6.9, 4.6, 0.1, 0.2};
   static final double VIT_A[] =       // vitamin A (IU / International Units)
      {54, 146, 225, 64, 12, 0, 178, 437, 0, 16705};
   static final double VIT_C[] =       // vitamin C (milligrams)
      {4.6, 10, 53.2, 8.7, 58.8, 2.3, 1.1, 0, 1, 5.9};
   static final String TAB = "   ";    // just for indentation
   
   // declare variables that can be accessed from all methods-------------------
   static String RECIPE_NAME;          // hold the recipe name
   static double TOTAL_CALORIES = 0;   // total calories of the recipe
   static double TOTAL_PROTEIN = 0;    // total soluble fiber of the recipe
   static double TOTAL_CARBO = 0;      // total carbohydrate of the recipe
   static double TOTAL_FAT = 0;        // total fat of the recipe
   static double TOTAL_VIT_A = 0;      // total Vitamin A for the recipe
   static double TOTAL_VIT_C = 0;      // total Vitamin C for the recipe
   static int  SERVINGS = 0;           // how many serving the recipe is
   
   public static void main(String args[])
   {
      // declare necessary variables--------------------------------------------
      String inputString;              // hold input strings from the user
      double inputNum1;                // hold any input numbers from the user
      double inputNum2;                // hold any input numbers from the user
      
      // create a scanner object------------------------------------------------
      Scanner keyboard = new Scanner(System.in);  
      
      // name and ID number-----------------------------------------------------
      createLine(80, "-");
      System.out.println("Last Name     : Wellyanto");
      System.out.println("Student ID    : 20144588");
      createLine(80, "-"); 
      
      // show the list of ingredients-------------------------------------------
      System.out.println("List of Possible Ingredients");
      createLine(80, "-");
      for (int counter = 0; counter < INGREDIENTS.length; counter += 1)
      {
         System.out.print(TAB + "Ingredients #" + (counter + 1) + " :");
         System.out.println(INGREDIENTS[counter]);
      }
      createLine(80, "-");
      
      // ask user the recipe name-----------------------------------------------
      System.out.print("Enter the name of your recipe: ");
      inputString = keyboard.nextLine();
      RECIPE_NAME = inputString;
      createLine(80, "-");
      
      // ask the user how many the serving is-----------------------------------
      System.out.print("Enter the number of servings (Integer): ");
      inputString = keyboard.nextLine();
      inputNum1 = Integer.parseInt(inputString);
      if (inputNum1 < 1 || inputNum1 > 10) outOfRangeError();      //check range
      SERVINGS = (int) inputNum1;
      createLine(80, "-");
      
      // display direction / guide to the user----------------------------------
      System.out.println("DIRECTION: Enter the name of the ingredient(s) that" +
      		" you want to use. ");
      System.out.print("Enter 'Done' if you're done. ");
      System.out.print("Enter 'Exit' if you want to shut down the program. ");
      System.out.println("(very case sensitive; please type correctly) :");
      createLine(80, "-");
      
      // ask the user for inputs (main logic part of the program)---------------
      while (true)         // needed for multiple inputs
      {
         do                // do input iterations, but at least ask once
         {
            checkInputErrors(inputNum1);
            System.out.print("Ingredient / Done / Exit :");
            inputString = keyboard.nextLine();
            if(inputString.equals("Done")) break;
            if(inputString.equals("Exit")) exit();
            inputNum1 = checkAnswer(inputString);
         }  while (inputNum1 < 0); // keep asking the user if the value is < 0
         if(inputString.equals("Done")) break;
         if(inputString.equals("Exit")) exit();
         System.out.print("Enter the number of grams of " + 
               INGREDIENTS[(int) inputNum1] + " (Double) :");
         inputString = keyboard.nextLine();
         inputNum2 = Double.parseDouble(inputString);
         if (inputNum2 < 0 || inputNum2 > 1000) outOfRangeError(); //check range
         calculate(inputNum2, (int) inputNum1);
      }
      keyboard.close();
      
      // display the report-----------------------------------------------------
      createLine(80, "-");
      format();
      System.out.println("Nutritional report of '" + RECIPE_NAME + 
            "' recipe: ");
      System.out.println(TAB + "Total Calories (kal)     : " + TOTAL_CALORIES);
      System.out.println(TAB + "Total Protein (g)        : " + TOTAL_PROTEIN);
      System.out.println(TAB + "Total Carbohydrate (g)   : " + TOTAL_CARBO);
      System.out.println(TAB + "Total Fat (g)            : " + TOTAL_FAT);
      System.out.println(TAB + "Total Vitamin A (IU)     : " + TOTAL_VIT_A);
      System.out.println(TAB + "Total Vitamin C (mg)     : " + TOTAL_VIT_C);
      createLine(80, "-");
   }
   
   /* create a line-------------------------------------------------------------
    * pre-cond    : throw in a "length" value and string "shape"
    * post-cond   : create a line with the length "length" and shape "shape"
    */
   private static void createLine(int length, String shape)
   {
      for(int counter = 0; counter < length; counter += 1)
      {
         System.out.print(shape);
      }
   }
   
   /* calculate and sum up the total nutrients fiber----------------------------
    * pre-cond    : add up every ingredient's nutrient
    * post-cond   : stored all the ingredient's nutrient in respective total 
    *               variables
    */
   private static void calculate(double input1, int input2)
   {
      TOTAL_CALORIES += input1 / 100 * CALORIES[input2] / SERVINGS;
      TOTAL_PROTEIN += input1 / 100 * PROTEIN[input2] / SERVINGS;
      TOTAL_CARBO += input1 / 100 * CARBOHYDRATE[input2] / SERVINGS;
      TOTAL_FAT += input1 / 100 * FAT[input2] / SERVINGS;
      TOTAL_VIT_A += input1 / 100 * VIT_A[input2] / SERVINGS;
      TOTAL_VIT_C += input1 / 100 * VIT_C[input2] / SERVINGS;
   }
   
   /* format method-------------------------------------------------------------
    * pre-cond    : -
    * post-cond   : format the output values
    */
   private static void format()
   {
      DecimalFormat formater = new DecimalFormat("0.00");
      TOTAL_CALORIES = Double.parseDouble(formater.format(TOTAL_CALORIES));
      TOTAL_PROTEIN = Double.parseDouble(formater.format(TOTAL_PROTEIN));
      TOTAL_CARBO = Double.parseDouble(formater.format(TOTAL_CARBO));
      TOTAL_FAT = Double.parseDouble(formater.format(TOTAL_FAT));
      TOTAL_VIT_A = Double.parseDouble(formater.format(TOTAL_VIT_A));
      TOTAL_VIT_C = Double.parseDouble(formater.format(TOTAL_VIT_C));
   }
   
   /* errorOutOfRange method----------------------------------------------------
    * pre-cond    : -
    * post-cond   : display an error message and shut down the program
    */
   private static void outOfRangeError()
   {
      System.out.println("Error! The input you entered is out of range.");
      exit();
   }
   
   /* errorOutOfRange method----------------------------------------------------
    * pre-cond    : check whether the inputed number is less than 0 or not
    * post-cond   : if its less than 0, which mean the program doesn't
    *               recognize the user's input, the program will display 
    *               an error message as well as feedback message
    *               if its not, everything goes fine
    */
   private static void checkInputErrors(double input)
   {
      if (input < 0)
      {
         System.out.println("Error! wrong input. please read the " +
               "direction above and try again.");
      }
   }
   
   /* exit method---------------------------------------------------------------
    * pre-cond    : -
    * post-cond   : display shut down message then exit the program
    */
   private static void exit()
   {
      System.out.println("Program shutted down.");
      System.exit(1);
   }
   
   /* check the users answer, whether it's 'Exit' or any Ingredients------------
    * pre-cond    : throw in the inputed string from the user
    * post-cond   : this method will check every name of the Ingredients
    *               by every iterations until it find the matches one, thus
    *               will return the respective value of the ingredient that
    *               stored inside the array. if it doesn't match any of the 
    *               names in the ingredients it will return a value equal 
    *               to (-1)
    */
   private static int checkAnswer(String input)
   {
      int output = -1;
      for (int counter = 0; counter <= INGREDIENTS.length - 1; counter += 1)
      {
         if (input.equals(INGREDIENTS[counter]))
         {
            output = counter;
         }
      }
      return output;
   }
}

/* Note: Sorry sir, if the program is over-embellised... I kind of got too
 *       excited and keep adding more stuff to make it more "perfect" ahaha~
 */

/*---------------------------Sample Run #1--------------------------------------

--------------------------------------------------------------------------------
Last Name     : Wellyanto
Student ID    : 20144588
--------------------------------------------------------------------------------
List of Possible Ingredients
--------------------------------------------------------------------------------
   Ingredients #1 :Apple
   Ingredients #2 :Avocado
   Ingredients #3 :Orange
   Ingredients #4 :Banana
   Ingredients #5 :Strawberry
   Ingredients #6 :Dark Chocolate Bar
   Ingredients #7 :Milk
   Ingredients #8 :Ice Cream
   Ingredients #9 :Sugar
   Ingredients #10 :Carrot
--------------------------------------------------------------------------------
Enter the name of your recipe: Chocolate Milk Shake
--------------------------------------------------------------------------------
Enter the number of servings (Integer): 3
--------------------------------------------------------------------------------
DIRECTION: Enter the name of the ingredient(s) that you want to use. 
Enter 'Done' if you're done. Enter 'Exit' if you want to shut down the program. 
(very case sensitive; please type correctly) :
--------------------------------------------------------------------------------
Ingredient / Done / Exit :dark chocolate bar
Error! wrong input. please read the direction above and try again.
Ingredient / Done / Exit :Dark Chocolate Bar
Enter the number of grams of Dark Chocolate Bar (Double) :29.58
Ingredient / Done / Exit :Milk
Enter the number of grams of Milk (Double) :473.18
Ingredient / Done / Exit :Ice Cream
Enter the number of grams of Ice Cream (Double) :477.87
Ingredient / Done / Exit :Sugar
Enter the number of grams of Sugar (Double) :32.94
Ingredient / Done / Exit :done
Error! wrong input. please read the direction above and try again.
Ingredient / Done / Exit :Done
--------------------------------------------------------------------------------
Nutritional report of 'Chocolate Milk Shake' recipe: 
   Total Calories (kal)     : 508.58
   Total Protein (g)        : 14.02
   Total Carbohydrate (g)   : 65.4
   Total Fat (g)            : 21.42
   Total Vitamin A (IU)     : 976.85
   Total Vitamin C (mg)     : 2.07
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/


/*---------------------------Sample Run #2--------------------------------------

--------------------------------------------------------------------------------
Last Name     : Wellyanto
Student ID    : 20144588
--------------------------------------------------------------------------------
List of Possible Ingredients
--------------------------------------------------------------------------------
   Ingredients #1 :Apple
   Ingredients #2 :Avocado
   Ingredients #3 :Orange
   Ingredients #4 :Banana
   Ingredients #5 :Strawberry
   Ingredients #6 :Dark Chocolate Bar
   Ingredients #7 :Milk
   Ingredients #8 :Ice Cream
   Ingredients #9 :Sugar
   Ingredients #10 :Carrot
--------------------------------------------------------------------------------
Enter the name of your recipe: Carrot Juice
--------------------------------------------------------------------------------
Enter the number of servings (Integer): 2
--------------------------------------------------------------------------------
DIRECTION: Enter the name of the ingredient(s) that you want to use. 
Enter 'Done' if you're done. Enter 'Exit' if you want to shut down the program. 
(very case sensitive; please type correctly) :
--------------------------------------------------------------------------------
Ingredient / Done / Exit :Carot
Error! wrong input. please read the direction above and try again.
Ingredient / Done / Exit :Carrot
Enter the number of grams of Carrot (Double) :469.73
Ingredient / Done / Exit :Sugar
Enter the number of grams of Sugar (Double) :36.17
Ingredient / Done / Exit :Done
--------------------------------------------------------------------------------
Nutritional report of 'Carrot Juice' recipe: 
   Total Calories (kal)     : 166.65
   Total Protein (g)        : 2.11
   Total Carbohydrate (g)   : 40.56
   Total Fat (g)            : 0.49
   Total Vitamin A (IU)     : 39234.2
   Total Vitamin C (mg)     : 14.04
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/


/*---------------------------Sample Run #3--------------------------------------

--------------------------------------------------------------------------------
Last Name     : Wellyanto
Student ID    : 20144588
--------------------------------------------------------------------------------
List of Possible Ingredients
--------------------------------------------------------------------------------
   Ingredients #1 :Apple
   Ingredients #2 :Avocado
   Ingredients #3 :Orange
   Ingredients #4 :Banana
   Ingredients #5 :Strawberry
   Ingredients #6 :Dark Chocolate Bar
   Ingredients #7 :Milk
   Ingredients #8 :Ice Cream
   Ingredients #9 :Sugar
   Ingredients #10 :Carrot
--------------------------------------------------------------------------------
Enter the name of your recipe: Strawberry Milk Shake
--------------------------------------------------------------------------------
Enter the number of servings (Integer): 5
--------------------------------------------------------------------------------
DIRECTION: Enter the name of the ingredient(s) that you want to use. 
Enter 'Done' if you're done. Enter 'Exit' if you want to shut down the program. 
(very case sensitive; please type correctly) :
--------------------------------------------------------------------------------
Ingredient / Done / Exit :Strawberry
Enter the number of grams of Strawberry (Double) :223.98
Ingredient / Done / Exit :Milk
Enter the number of grams of Milk (Double) :893.78
Ingredient / Done / Exit :Ice Cream
Enter the number of grams of Ice Cream (Double) :928.47
Ingredient / Done / Exit :Sugar
Enter the number of grams of Sugar (Double) :51.32
Ingredient / Done / Exit :Done
--------------------------------------------------------------------------------
Nutritional report of 'Strawberry Milk Shake' recipe: 
   Total Calories (kal)     : 528.48
   Total Protein (g)        : 15.84
   Total Carbohydrate (g)   : 69.95
   Total Fat (g)            : 21.02
   Total Vitamin A (IU)     : 1135.04
   Total Vitamin C (mg)     : 28.41
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/


/*---------------------------Sample Run #4--------------------------------------

--------------------------------------------------------------------------------
Last Name     : Wellyanto
Student ID    : 20144588
--------------------------------------------------------------------------------
List of Possible Ingredients
--------------------------------------------------------------------------------
   Ingredients #1 :Apple
   Ingredients #2 :Avocado
   Ingredients #3 :Orange
   Ingredients #4 :Banana
   Ingredients #5 :Strawberry
   Ingredients #6 :Dark Chocolate Bar
   Ingredients #7 :Milk
   Ingredients #8 :Ice Cream
   Ingredients #9 :Sugar
   Ingredients #10 :Carrot
--------------------------------------------------------------------------------
Enter the name of your recipe: Fruit Punch
--------------------------------------------------------------------------------
Enter the number of servings (Integer): 4
--------------------------------------------------------------------------------
DIRECTION: Enter the name of the ingredient(s) that you want to use. 
Enter 'Done' if you're done. Enter 'Exit' if you want to shut down the program. 
(very case sensitive; please type correctly) :
--------------------------------------------------------------------------------
Ingredient / Done / Exit :Apple
Enter the number of grams of Apple (Double) :277.28
Ingredient / Done / Exit :Avocado
Enter the number of grams of Avocado (Double) :277.28
Ingredient / Done / Exit :Banana
Enter the number of grams of Banana (Double) :277.28
Ingredient / Done / Exit :Strawberry
Enter the number of grams of Strawberry (Double) :277.28
Ingredient / Done / Exit :Done
--------------------------------------------------------------------------------
Nutritional report of 'Fruit Punch' recipe: 
   Total Calories (kal)     : 230.84
   Total Protein (g)        : 2.84
   Total Carbohydrate (g)   : 36.6
   Total Fat (g)            : 10.74
   Total Vitamin A (IU)     : 191.32
   Total Vitamin C (mg)     : 56.91
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/


/*---------------------------Sample Run #5--------------------------------------

--------------------------------------------------------------------------------
Last Name     : Wellyanto
Student ID    : 20144588
--------------------------------------------------------------------------------
List of Possible Ingredients
--------------------------------------------------------------------------------
   Ingredients #1 :Apple
   Ingredients #2 :Avocado
   Ingredients #3 :Orange
   Ingredients #4 :Banana
   Ingredients #5 :Strawberry
   Ingredients #6 :Dark Chocolate Bar
   Ingredients #7 :Milk
   Ingredients #8 :Ice Cream
   Ingredients #9 :Sugar
   Ingredients #10 :Carrot
--------------------------------------------------------------------------------
Enter the name of your recipe: Banana Split
--------------------------------------------------------------------------------
Enter the number of servings (Integer): 3
--------------------------------------------------------------------------------
DIRECTION: Enter the name of the ingredient(s) that you want to use. 
Enter 'Done' if you're done. Enter 'Exit' if you want to shut down the program. 
(very case sensitive; please type correctly) :
--------------------------------------------------------------------------------
Ingredient / Done / Exit :Banana
Enter the number of grams of Banana (Double) :387.64
Ingredient / Done / Exit :ice cream
Error! wrong input. please read the direction above and try again.
Ingredient / Done / Exit :Ice Cream
Enter the number of grams of Ice Cream (Double) :697.81
Ingredient / Done / Exit :Milk
Enter the number of grams of Milk (Double) :39.23
Ingredient / Done / Exit :Done
--------------------------------------------------------------------------------
Nutritional report of 'Banana Split' recipe: 
   Total Calories (kal)     : 504.5
   Total Protein (g)        : 13.07
   Total Carbohydrate (g)   : 88.99
   Total Fat (g)            : 11.99
   Total Vitamin A (IU)     : 1122.45
   Total Vitamin C (mg)     : 11.39
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/

/*---------------------------Sample Run #6--------------------------------------

--------------------------------------------------------------------------------
Last Name     : Wellyanto
Student ID    : 20144588
--------------------------------------------------------------------------------
List of Possible Ingredients
--------------------------------------------------------------------------------
   Ingredients #1 :Apple
   Ingredients #2 :Avocado
   Ingredients #3 :Orange
   Ingredients #4 :Banana
   Ingredients #5 :Strawberry
   Ingredients #6 :Dark Chocolate Bar
   Ingredients #7 :Milk
   Ingredients #8 :Ice Cream
   Ingredients #9 :Sugar
   Ingredients #10 :Carrot
--------------------------------------------------------------------------------
Enter the name of your recipe: Apple Juice
--------------------------------------------------------------------------------
Enter the number of servings (Integer): 13
Error! The input you entered is out of range.
Program shutted down.

------------------------------------------------------------------------------*/


/*---------------------------Sample Run #7--------------------------------------
--------------------------------------------------------------------------------
Last Name     : Wellyanto
Student ID    : 20144588
--------------------------------------------------------------------------------
List of Possible Ingredients
--------------------------------------------------------------------------------
   Ingredients #1 :Apple
   Ingredients #2 :Avocado
   Ingredients #3 :Orange
   Ingredients #4 :Banana
   Ingredients #5 :Strawberry
   Ingredients #6 :Dark Chocolate Bar
   Ingredients #7 :Milk
   Ingredients #8 :Ice Cream
   Ingredients #9 :Sugar
   Ingredients #10 :Carrot
--------------------------------------------------------------------------------
Enter the name of your recipe: strawBerry juiCe
--------------------------------------------------------------------------------
Enter the number of servings (Integer): 3
--------------------------------------------------------------------------------
DIRECTION: Enter the name of the ingredient(s) that you want to use. 
Enter 'Done' if you're done. Enter 'Exit' if you want to shut down the program. 
(very case sensitive; please type correctly) :
--------------------------------------------------------------------------------
Ingredient / Done / Exit :whoops i input the wrong name
Error! wrong input. please read the direction above and try again.
Ingredient / Done / Exit :lets exit the program and restart
Error! wrong input. please read the direction above and try again.
Ingredient / Done / Exit :Exit
Program shutted down.

------------------------------------------------------------------------------*/