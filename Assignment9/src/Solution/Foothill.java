package Solution;

/* CS 1A Lab 9
 * Instructor Solution
 */

import java.text.*;
import java.util.*;

// main class ------------------------------------------------------
public class Foothill
{
   static Scanner input;
   
   public static void main(String[] args)
   {
      PizzaOrder pizza;
      String response;
      char letter;
      boolean done;
      int nChoice;
      input = new Scanner(System.in);
         
      pizza = new PizzaOrder();
      while (true)
      {
         letter = getSizeFromUser();
         if (letter == 'q' || letter == 'Q')
         {
            System.out.println("Loceff's Pizzaria is now closed.  Thanks.");
            break;
         }
         
         pizza.setSizeAsChar(letter);
         pizza.resetToppings();

         // loop for this pizza, getting toppings
         for (done = false; !done; )
         {
            // show what they have ordered so far
            System.out.print("\nCurrent Pizza: ");
            pizza.displayPizzaWithoutPrice();

            // get a new topping or 0
            displayMainMenu();
            System.out.print("Selection: ");
            response = input.nextLine();
            nChoice = Integer.parseInt(response);

            if (nChoice == 0)
            {
               done = true;
               pizza.displayPizza();
               continue;
            }
            pizza.addTopping(nChoice);
         }
      } 
      input.close();
   }
   
   // print menu
   static void displayMainMenu()
   {
      System.out.println("Select an item by number (0 when done):");
      for (int k = 0; k < PizzaOrder.toppingsOffered.length; k++)
         System.out.println("  " + (k+1) + ". " 
               + PizzaOrder.toppingsOffered[k]); 
   }
   
   static char getSizeFromUser()
   {
      char letter;
      String response;
      
      do
      {
         System.out.print(
               "\nSize of pizza ('s', 'm', 'l') or 'q' to quit: ");
         response = input.nextLine();
         letter = response.charAt(0);
         letter = Character.toLowerCase(letter);
      } while (letter != 's' && letter != 'm' && letter != 'l'
            && letter != 'q');
      return letter;
   }
}

//class PizzaOrder ------------------------------------
class PizzaOrder
{
   // static public members
   public static final String toppingsOffered[] =
      { "onions", "bell peppers", "olives", "pepperoni" };
   public static final double toppingBaseCost = 2.50;
   public static final double basePrice = 12.95;
   public static final int small = 0;
   public static final int medium = 1;
   public static final int large = 2;
   public static final int MAX_TOPPINGS = 20;
   
   // instance members
   private int size;
   private String toppings[];
   private int numToppings;

   // constructors
   public PizzaOrder(int size)
   {
      setSize(size);
      numToppings = 0;
      toppings = new String[MAX_TOPPINGS];
   } 
   
   public PizzaOrder()
   {
      this(small);
   }
   
   // mutators, accessors
   public boolean setSize(int size)
   {
      if (size >=small && size <= large)
      {
         this.size = size;
         return true;
      }
      else
      {
         this.size = small;
         return false;
      }
   }
   
   public boolean setSizeAsChar( char sz )
   {
      if (sz == 's' || sz == 'S')
         size = small;
      else if (sz == 'm' || sz == 'M')
         size = medium;
      else if (sz == 'l' || sz == 'L')
        size = large;
      else
      {
         // bad parameter
         size = small;
         return false;
      }
      return true;
   }
   
   public int getSize() 
   { 
      return size; 
   }
   
   public boolean addTopping(String topping)
   {
     if (numToppings >= MAX_TOPPINGS)
        return false;
     if (IsLegalTopping( topping ) )
     {
        toppings[numToppings++] = topping;
        return true;
     }
     else
        return false;
   }

   public boolean addTopping(int nTopping)
   {
      nTopping--; // the menu went from 1 to num legal toppings
      if (nTopping >= 0 && nTopping < toppingsOffered.length )
         return addTopping(toppingsOffered[nTopping]);
  
      else
         return false;
   }

   public  double getPrice()
   {
      double total;
      total = basePrice + numToppings * toppingBaseCost;
      if (size == medium)
         total = total * 1.15;
      else if (size == large)
         total = total * 1.25;
      return total;
   }
   
   public String stringizeSize()
   {
      if (size == small )
         return "small";
      else if (size == medium)
         return "medium";
      else if (size == large)
         return "large";
      else
         return "mal-formed pizza size";
   }
   
   public String getToppings()
   {
      String listOfToppings = "";
      for (int k = 0; k < numToppings ; k++)
         listOfToppings = listOfToppings + " + " + toppings[k];
      return listOfToppings;
   }
   
   public void displayPizza()
   {
      displayPizzaWithoutPrice();
      NumberFormat bucks 
         = NumberFormat.getCurrencyInstance(Locale.US);
      System.out.println( "Total Price: " + bucks.format(getPrice()) + "\n");
   }
   
   public void displayPizzaWithoutPrice()
   {
      String listOfToppings;

      listOfToppings = getToppings();
      System.out.println( stringizeSize() + " " 
         + listOfToppings);
   }
   
   public void resetToppings() { numToppings = 0; }
   
   private boolean IsLegalTopping(String test)
   {
      for (int k = 0;  k < toppingsOffered.length; k++)
      {
         if (toppingsOffered[k].equalsIgnoreCase(test))
            return true;
      }
      return false;
   }
}
/* ------------------ Output of Above ------------------------------


Size of pizza ('s', 'm', 'l') or 'q' to quit: e

Size of pizza ('s', 'm', 'l') or 'q' to quit: 9

Size of pizza ('s', 'm', 'l') or 'q' to quit: jump

Size of pizza ('s', 'm', 'l') or 'q' to quit: M

Current Pizza: medium 
Select an item by number (0 when done):
  1. onions
  2. bell peppers
  3. olives
  4. pepperoni
Selection: 2

Current Pizza: medium  + bell peppers
Select an item by number (0 when done):
  1. onions
  2. bell peppers
  3. olives
  4. pepperoni
Selection: 2

Current Pizza: medium  + bell peppers + bell peppers
Select an item by number (0 when done):
  1. onions
  2. bell peppers
  3. olives
  4. pepperoni
Selection: 4

Current Pizza: medium  + bell peppers + bell peppers + pepperoni
Select an item by number (0 when done):
  1. onions
  2. bell peppers
  3. olives
  4. pepperoni
Selection: 0
medium  + bell peppers + bell peppers + pepperoni
Total Price: $23.52


Size of pizza ('s', 'm', 'l') or 'q' to quit: l

Current Pizza: large 
Select an item by number (0 when done):
  1. onions
  2. bell peppers
  3. olives
  4. pepperoni
Selection: 1

Current Pizza: large  + onions
Select an item by number (0 when done):
  1. onions
  2. bell peppers
  3. olives
  4. pepperoni
Selection: 2

Current Pizza: large  + onions + bell peppers
Select an item by number (0 when done):
  1. onions
  2. bell peppers
  3. olives
  4. pepperoni
Selection: 0
large  + onions + bell peppers
Total Price: $22.44


Size of pizza ('s', 'm', 'l') or 'q' to quit: Q
Loceff's Pizzaria is now closed.  Thanks.


------------------------------------------------------------------- */
