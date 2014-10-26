// CS 1A Lab 3 Framework

import java.util.Scanner;

public class Test
{
   // food #1 constants
   static final String FOOD_1_NAME = "avocado";
   static final int FOOD_1_CALORIES_P100G = 160;  // in calories
   static final double FOOD_1_SOL_FIBER_P100G = 1.75;   // in grams
   
   // food #2 constants
   static final String FOOD_2_NAME = "tomato";
   static final int FOOD_2_CALORIES_P100G = 18;  // in calories
   static final double FOOD_2_SOL_FIBER_P100G = .12;   // in grams
   
   // food #3 constants
   static final String FOOD_3_NAME = "buffalo mozzarella";
   static final int FOOD_3_CALORIES_P100G = 282;  // in calories
   static final double FOOD_3_SOL_FIBER_P100G = 0.;   // in grams
   
   static final String INDENT = "   ";
   static final String SEPARATOR = "\n";
   
   public static void main(String[] args)
   {
      String recipeName, userInputStr;
      int userInputInt, totalCals;
      double totalSolFiber;
      Scanner inputStream = new Scanner(System.in);
      
      // initialize accumlator variables
      totalSolFiber  = 0.;
      totalCals =  0;
      
      // print menu
      System.out.println("---------- List of Possible Ingredients ---------");
      System.out.println(INDENT + "Food #1 :" + FOOD_1_NAME);
      System.out.println(INDENT + "Food #2 :" + FOOD_2_NAME);
      System.out.println(INDENT + "Food #3 :" + FOOD_3_NAME + SEPARATOR);      
    
      // name of recipe
      System.out.print("What are you calling this recipe ? ");
      recipeName  = inputStream.nextLine();
      
      // food #1 ---------------------------------------------------------
      System.out.print("How many grams of " + FOOD_1_NAME + "? ");
      userInputStr = inputStream.nextLine();
      userInputInt = Integer.parseInt(userInputStr);
      
      // update accumulators
      totalCals += userInputInt * (FOOD_1_CALORIES_P100G/100.);
      totalSolFiber  += userInputInt * (FOOD_1_SOL_FIBER_P100G/100.);

      // food #2 ---------------------------------------------------------
      System.out.print("How many grams of " + FOOD_2_NAME + "? ");
      userInputStr = inputStream.nextLine();
      userInputInt = Integer.parseInt(userInputStr);
      
      // update accumulators
      totalCals += userInputInt * (FOOD_2_CALORIES_P100G/100.);
      totalSolFiber  += userInputInt * (FOOD_2_SOL_FIBER_P100G/100.);

      // food #3 ---------------------------------------------------------
      System.out.print("How many grams of " + FOOD_3_NAME + "? ");
      userInputStr = inputStream.nextLine();
      userInputInt = Integer.parseInt(userInputStr);
      
      // update accumulators
      totalCals += userInputInt * (FOOD_3_CALORIES_P100G/100.);
      totalSolFiber  += userInputInt * (FOOD_3_SOL_FIBER_P100G/100.);   
      inputStream.close();
      
      // report results --------------------------------------------------
      System.out.println("\nNutrition for " + recipeName + "------------"); 
      System.out.println(INDENT + "Calories: " + totalCals);
      System.out.println(INDENT + "Soluble Fiber: " + totalSolFiber);      
   }
}