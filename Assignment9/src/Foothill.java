/**
 * file: Foothill.java
 * -----------------------------------------------------------------------------
 * This program is due to Assignment_9 in course "FH CS 001 ML F13".
 * this program resemble the pizzeria order machine.
 * Created By: Muhammad Rizky Wellyanto 
 * Student ID: 20144588
 *
 */

import java.util.Scanner;

/**
 * main class of the program
 * @author R12KY
 * 
 */
public class Foothill
{
   // create global scanner object
   private static Scanner keyboard;
   
   // main method of the program
   public static void main(String[] args)
   {
      // declare local variables
      String inputStr;
      char inputCh;
      int inputInt;
      int orderNumber = 0;
      
      // create a scanner object
      keyboard = new Scanner(System.in);
      
      // create pizza object
      PizzaOrder pizza = new PizzaOrder();
      
      // indefinite loop for multiple iterations
      while (true)
      {
         // main menu
         displayMainMenu();
         
         // ask for input
         inputCh = getSizeFromUser();
         createLine(80, "-");
         
         // if the first char of the input is q then quit
         if (inputCh == 'Q')
            break;
         
         // if the first char of the input is in range
         if (pizza.setSize(inputCh))
         {
            // another iterations for multiple topping inputs
            while (true)
            {
               // display current state of the pizza
               System.out.println("CURRENT PIZZA: ");
               pizza.displayPizza();
               createLine(80, "-");
               
               // display toppings offered
               System.out.println("TOPPINGS OFFERED: ");
               pizza.displayAvailableToppings();
               createLine(80, "-");
               
               // prompt and input
               System.out.print("Select Toppings, '0' when done: ");
               inputStr = keyboard.nextLine();
               createLine(80, "-");
               
               // if the input is 0 then quit
               if (inputStr.equals("0"))
                  break;
               
               // try catch sentence in order to choose between user type input
               try
               {
                  // try parse the input to integer and pass it to addTopping
                  inputInt = Integer.parseInt(inputStr);
                  pizza.addTopping(inputInt);
               }
               catch (NumberFormatException e)
               {
                  // if it throws an error try the string parametered one
                  pizza.addTopping(inputStr);
               }
               
            }
            
            // display receipt
            System.out.println("RECEIPT: ");
            System.out.println("ORDER NUMBER: " + orderNumber);
            pizza.displayPizza();
            createLine(80, "-");
            
            // increase order number and reset pizza object
            pizza.resetPizza();
            orderNumber++;
         }
         
      }
      
      // close the keyboard and give a message
      keyboard.close();
      System.out.println("Program Shutted Down");
      createLine(80, "-");
   }
   
   /**
    * this method is just to make the program look "beautiful"
    * 
    * @param length
    * @param shape
    */
   private static void createLine(int length, String shape)
   {
      for (int counter = 0; counter < length; counter += 1)
      {
         System.out.print(shape);
      }
   }
   
   /**
    * and input processing method to analyze input from the user
    * 
    * @return
    */
   private static char getSizeFromUser()
   {
      return (Character.toUpperCase(keyboard.nextLine().charAt(0)));
   }
   
   /**
    * this method display the main menu of the pizza order
    */
   private static void displayMainMenu()
   {
      System.out.print("Size of pizza ('S', 'M', 'L') or 'Q' to quit: ");
   }
   
}

/**
 * the supporting class
 * 
 * @author R12KY
 * 
 */
class PizzaOrder
{
   
   // constants
   private static final int MAX_SIZE = 2;
   private static final int MIN_SIZE = 0;
   private static final int MAX_TOPPINGS = 20;
   private static final char SIZE_SMALL = 'S';
   private static final char SIZE_MEDIUM = 'M';
   private static final char SIZE_LARGE = 'L';
   private static final String STRING_SMALL = "Small";
   private static final String STRING_MEDIUM = "Medium";
   private static final String STRING_LARGE = "Large";
   public static final int DEFAULT_SIZE = 0;
   
   // prices
   public static final double TOPPINGS_BASE_COST = 2;
   public static final double BASE_PRICE = 10;
   
   // size multipliers
   public static final double SMALL_MULTIPLIER = 1.0;
   public static final double MEDIUM_MULTIPLIER = 1.15;
   public static final double LARGE_MULTIPLIER = 1.25;
   
   // other constants
   private final String TOPPINGS_OFFERED[] =
      { "Onions", "Bell Peppers", "Olives", "Pepperoni", "Mushroom",
            "Meatloaf", "Cheese", "Chicken", "Veggie", "Paprika" };
   
   // private data
   private int size;
   private String toppings[] = new String[MAX_TOPPINGS];
   private int numToppings;
   
   /**
    * default constructor
    */
   PizzaOrder()
   {
      setSize(DEFAULT_SIZE);
   }
   
   /**
    * constructor with an int as its parameter
    * 
    * @param size
    */
   PizzaOrder(int size)
   {
      if (!setSize(size))
         setSize(DEFAULT_SIZE);
   }
   
   /**
    * constructor with a String as its parameter
    * 
    * @param size
    */
   PizzaOrder(String size)
   {
      if (!setSize(size))
         setSize(DEFAULT_SIZE);
   }
   
   // mutator methods
   /**
    * mutator of size field. filter the input. and set the value if its in range
    * then return true. or return false then not set the value
    * 
    * @param size
    * @return
    */
   public boolean setSize(int size)
   {
      if (size >= MIN_SIZE && size <= MAX_SIZE)
      {
         this.size = size;
         return true;
      }
      
      return false;
   }
   
   /**
    * an overloaded setSize() method with char as its parameter. mutator of size
    * field. filter the input. if its 'S' / 'M' / 'L' the size will be setted to
    * its corresponding value. if its none of those return false then not set
    * the value
    * 
    * @param size
    * @return
    */
   public boolean setSize(char size)
   {
      size = Character.toUpperCase(size);
      
      if (size == SIZE_SMALL)
      {
         this.size = 0;
         return true;
      }
      else if (size == SIZE_MEDIUM)
      {
         this.size = 1;
         return true;
      }
      else if (size == SIZE_LARGE)
      {
         this.size = 2;
         return true;
      }
      
      return false;
   }
   
   /**
    * an overloaded setSize() method with String as its parameter. mutator of
    * size field. take the first char of the string and pass it to setSize(char
    * size) method
    * 
    * @param size
    * @return
    */
   public boolean setSize(String size)
   {
      if (setSize(size.charAt(0)))
         return true;
      
      return false;
   }
   
   /**
    * mutator of the toppings array[]. filter the input. and set the value if
    * its in range then return true. or return false then not set the value
    * 
    * @param topping
    * @return
    */
   public boolean addTopping(String topping)
   {
      // loop through the TOPPINGS_OFFERED[] arrays
      for (int i = 0; i < TOPPINGS_OFFERED.length; i++)
      {
         // if the name matched one of the value in that array, then...
         if (topping.equals(TOPPINGS_OFFERED[i]))
         {
            // if the toppings[] array got empty topping slot
            if (numToppings < MAX_TOPPINGS)
            {
               // add the topping to the toppings array & add numTopping
               toppings[numToppings] = topping;
               numToppings++;
               return true;
            }
         }
      }
      
      return false;
   }
   
   /**
    * an overloaded addTopping() method that take int as its parameter mutator
    * of the toppings[] array. filter the input. and set the value if its in
    * range then return true. or return false then not set the value
    * 
    * @param numTopping
    * @return
    */
   public boolean addTopping(int numTopping)
   {
      // if the input number is correlated to toppings that are offered
      if (numTopping >= 0 && numTopping <= TOPPINGS_OFFERED.length)
      {
         // if the toppings[] array got empty topping slot
         if (numToppings < MAX_TOPPINGS)
         {
            // add the topping to the toppings[] array
            toppings[numToppings] = TOPPINGS_OFFERED[numTopping - 1];
            numToppings++;
            return true;
         }
      }
      
      return false;
   }
   
   // accessor methods
   /**
    * a method that calculate all of the price.
    * 
    * @return the total price
    */
   public double getPrice()
   {
      double sizeMultiplier;
      
      if (size == 2)
      {
         sizeMultiplier = LARGE_MULTIPLIER;
      }
      else if (size == 1)
      {
         sizeMultiplier = MEDIUM_MULTIPLIER;
      }
      else
      {
         sizeMultiplier = SMALL_MULTIPLIER;
      }
      
      return (BASE_PRICE + TOPPINGS_BASE_COST * numToppings) * sizeMultiplier;
      
   }
   
   /**
    * return the string version of private data size.
    * 
    * @return
    */
   public String stringizeSize()
   {
      String output;
      
      if (size == 2)
      {
         output = STRING_LARGE;
      }
      else if (size == 1)
      {
         output = STRING_MEDIUM;
      }
      else
      {
         output = STRING_SMALL;
      }
      
      return output;
   }
   
   /**
    * return the list of toppings that stored inside toppings[] array
    * 
    * @return
    */
   public String getToppings()
   {
      String output = "";
      
      if (numToppings > 0 && numToppings <= MAX_TOPPINGS)
      {
         for (int i = 0; i < numToppings; i++)
         {
            output += "      " + (i + 1) + ". " + toppings[i] + "\n";
         }
      }
      else
      {
         output += "         -NONE-";
      }
      
      return output;
   }
   
   /**
    * return the numToppings field
    * 
    * @return
    */
   public int getNumToppings()
   {
      return numToppings;
   }
   
   // void methods
   /**
    * display the data stored in the PizzaOrder object
    */
   public void displayPizza()
   {
      System.out.println("   Size: " + stringizeSize());
      System.out.println("   Topping(s):\n" + getToppings());
      System.out.println("   Total Price: " + getPrice());
   }
   
   /**
    * reset the value of numToppings and set all the value of the toppings[]
    * array to null.
    */
   public void resetTopping()
   {
      for (int i = 0; i < toppings.length; i++)
      {
         toppings[i] = null;
      }
      
      numToppings = 0;
   }
   
   /**
    * reset the size and the toppings value of the pizza
    */
   public void resetPizza()
   {
      setSize(DEFAULT_SIZE);
      resetTopping();
   }
   
   /**
    * display the list of available toppings that offered.
    */
   public void displayAvailableToppings()
   {
      for (int i = 0; i < TOPPINGS_OFFERED.length; i++)
      {
         System.out.println("      " + (i + 1) + ". " + TOPPINGS_OFFERED[i]);
      }
   }
   
}

/*---------------------------------SAMPLE RUN-----------------------------------

Size of pizza ('S', 'M', 'L') or 'Q' to quit: med
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Medium
   Topping(s):
         -NONE-
   Total Price: 11.5
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 3
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Medium
   Topping(s):
      1. Olives

   Total Price: 13.799999999999999
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 6
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Medium
   Topping(s):
      1. Olives
      2. Meatloaf

   Total Price: 16.099999999999998
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 20
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Medium
   Topping(s):
      1. Olives
      2. Meatloaf

   Total Price: 16.099999999999998
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 2000
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Medium
   Topping(s):
      1. Olives
      2. Meatloaf

   Total Price: 16.099999999999998
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: pepperoni
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Medium
   Topping(s):
      1. Olives
      2. Meatloaf

   Total Price: 16.099999999999998
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: Pepperoni
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Medium
   Topping(s):
      1. Olives
      2. Meatloaf
      3. Pepperoni

   Total Price: 18.4
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 0
--------------------------------------------------------------------------------
RECEIPT: 
ORDER NUMBER: 0
   Size: Medium
   Topping(s):
      1. Olives
      2. Meatloaf
      3. Pepperoni

   Total Price: 18.4
--------------------------------------------------------------------------------
Size of pizza ('S', 'M', 'L') or 'Q' to quit: k
--------------------------------------------------------------------------------
Size of pizza ('S', 'M', 'L') or 'Q' to quit: j
--------------------------------------------------------------------------------
Size of pizza ('S', 'M', 'L') or 'Q' to quit: l
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Large
   Topping(s):
         -NONE-
   Total Price: 12.5
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: Mushroom
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Large
   Topping(s):
      1. Mushroom

   Total Price: 15.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 8
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Large
   Topping(s):
      1. Mushroom
      2. Chicken

   Total Price: 17.5
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 12
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Large
   Topping(s):
      1. Mushroom
      2. Chicken

   Total Price: 17.5
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: veggie
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Large
   Topping(s):
      1. Mushroom
      2. Chicken

   Total Price: 17.5
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: Vegg
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Large
   Topping(s):
      1. Mushroom
      2. Chicken

   Total Price: 17.5
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 0
--------------------------------------------------------------------------------
RECEIPT: 
ORDER NUMBER: 1
   Size: Large
   Topping(s):
      1. Mushroom
      2. Chicken

   Total Price: 17.5
--------------------------------------------------------------------------------
Size of pizza ('S', 'M', 'L') or 'Q' to quit: SmaLl
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Small
   Topping(s):
         -NONE-
   Total Price: 10.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 3
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Small
   Topping(s):
      1. Olives

   Total Price: 12.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 7
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Small
   Topping(s):
      1. Olives
      2. Cheese

   Total Price: 14.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 3
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Small
   Topping(s):
      1. Olives
      2. Cheese
      3. Olives

   Total Price: 16.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: Cheese
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Small
   Topping(s):
      1. Olives
      2. Cheese
      3. Olives
      4. Cheese

   Total Price: 18.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: akjwegu
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Small
   Topping(s):
      1. Olives
      2. Cheese
      3. Olives
      4. Cheese

   Total Price: 18.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 0
--------------------------------------------------------------------------------
RECEIPT: 
ORDER NUMBER: 2
   Size: Small
   Topping(s):
      1. Olives
      2. Cheese
      3. Olives
      4. Cheese

   Total Price: 18.0
--------------------------------------------------------------------------------
Size of pizza ('S', 'M', 'L') or 'Q' to quit: dhei
--------------------------------------------------------------------------------
Size of pizza ('S', 'M', 'L') or 'Q' to quit: s
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Small
   Topping(s):
         -NONE-
   Total Price: 10.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: v
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Small
   Topping(s):
         -NONE-
   Total Price: 10.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 9
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Small
   Topping(s):
      1. Veggie

   Total Price: 12.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 3
--------------------------------------------------------------------------------
CURRENT PIZZA: 
   Size: Small
   Topping(s):
      1. Veggie
      2. Olives

   Total Price: 14.0
--------------------------------------------------------------------------------
TOPPINGS OFFERED: 
      1. Onions
      2. Bell Peppers
      3. Olives
      4. Pepperoni
      5. Mushroom
      6. Meatloaf
      7. Cheese
      8. Chicken
      9. Veggie
      10. Paprika
--------------------------------------------------------------------------------
Select Toppings, '0' when done: 0
--------------------------------------------------------------------------------
RECEIPT: 
ORDER NUMBER: 3
   Size: Small
   Topping(s):
      1. Veggie
      2. Olives

   Total Price: 14.0
--------------------------------------------------------------------------------
Size of pizza ('S', 'M', 'L') or 'Q' to quit: k
--------------------------------------------------------------------------------
Size of pizza ('S', 'M', 'L') or 'Q' to quit: x
--------------------------------------------------------------------------------
Size of pizza ('S', 'M', 'L') or 'Q' to quit: exit
--------------------------------------------------------------------------------
Size of pizza ('S', 'M', 'L') or 'Q' to quit: quit
--------------------------------------------------------------------------------
Program Shutted Down
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/
