/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * This program is due to Assignment_6 in course "FH CS 001 ML F13" this program
 * is basically a casino jackpot machine. this program demonstrate almost the
 * whole basic concept of object oriented programming.
 * Created By: Muhammad Rizky Wellyanto 
 * Student ID: 20144588
 */

// import Scanner to read user input.
import java.util.Scanner;

/**
 * main class of the program. in other words, the slot machine.
 * 
 * @author R12KY
 * 
 */
public class Foothill
{
   // declare constants
   private static int MAX_BET = 100;   // set the maximum bet
   private static Scanner keyboard;    // global scanner object
   private static String TAB = "   ";  // just for indentation
   
   // strings that might be out in the slot machine, as constants
   private static String BAR = "BAR";
   private static String CHERRIES = "Cherries";
   private static String SPACE = "Space";
   private static String SEVEN = "7";
   
   // multiplier of each Strings as constants
   private static int ONE_CHER = 5;       // one cherry at left
   private static int TWO_CHER = 15;      // two cherries at left and middle
   private static int THREE_CHER = 30;    // three cherries
   private static int THREE_BAR = 50;     // three bars
   private static int THREE_SEVEN = 100;  // jackpot!
   
   // main method of the program
   public static void main(String[] args)
   {
      // declare necessary variables
      int bet;
      int winnings;
      
      // create a scanner object
      keyboard = new Scanner(System.in);
      
      // create a TripleString reference
      TripleString thePull = new TripleString();
      
      // indefinite loop. for multiple iterations
      while (true)
      {
         createLine(80, "=");
         
         // get the value of bet
         bet = getBet();
         
         // the only way to get out of the loop is when bet is == 0
         if (bet == 0)
         {
            break;
         }
         
         // generate the Three strings
         thePull = pull();
         
         // calculate the value of winnings
         winnings = bet * getPayMultiplier(thePull);
         
         // display the result
         display(thePull, winnings);
      }
      
      // close the scanner object
      keyboard.close();
   }
   
   /**
    * input method of the foothill class. ask the user for input and return the
    * inputed value
    * 
    * @return
    */
   private static int getBet()
   {
      int bet;
      
      do
      {
         System.out.print("How much would you like to bet (1 - " + MAX_BET
               + ") or 0 to quit: ");
         bet = keyboard.nextInt();
         createLine(80, "=");
      }
      while (bet < 0 || bet > MAX_BET);
      
      return bet;
   }
   
   /**
    * method that will create the TripleString object. the method will call
    * randString() method then assign each strings to strings inside the
    * TripleString object. return a TripleString object
    * 
    * @return
    */
   private static TripleString pull()
   {
      TripleString tripleString = new TripleString();
      
      tripleString.setString1(randString());
      tripleString.setString2(randString());
      tripleString.setString3(randString());
      
      return tripleString;
   }
   
   /**
    * randomize strings (strings that declared as constants) then return the
    * string
    * 
    * @return
    */
   private static String randString()
   {
      double randomNum = 0;
      String strOut = "";
      randomNum = (Math.random() * 100);
      
      if (randomNum >= 0 && randomNum < 50)
      {
         strOut = BAR;
      }
      else if (randomNum >= 50 && randomNum < 75)
      {
         strOut = CHERRIES;
      }
      else if (randomNum >= 75 && randomNum < 87.5)
      {
         strOut = SPACE;
      }
      else if (randomNum >= 87.5 && randomNum < 100)
      {
         strOut = SEVEN;
      }
      
      return strOut;
   }
   
   /**
    * this method will define the bonus multiplier that the user will get. this
    * method takes the TripleString object as parameter, and then compare the
    * strings to designated bonus and return the bonus value. if the strings
    * doesnt match any of the criterias, it will return 0.
    * 
    * @param thePull
    * @return
    */
   private static int getPayMultiplier(TripleString thePull)
   {
      int multiplier = 0;
      
      if (thePull.getString1().equals(CHERRIES))
      {
         if (!thePull.getString2().equals(CHERRIES))
         {
            multiplier = ONE_CHER;
         }
      }
      
      if (thePull.getString1().equals(CHERRIES))
      {
         if (thePull.getString2().equals(CHERRIES))
         {
            multiplier = TWO_CHER;
         }
      }
      
      if (thePull.getString1().equals(CHERRIES))
      {
         if (thePull.getString2().equals(CHERRIES))
         {
            if (thePull.getString3().equals(CHERRIES))
            {
               multiplier = THREE_CHER;
            }
         }
      }

       if (thePull.getString1().equals(BAR))
       {
           if (thePull.getString2().equals(BAR))
           {
               if (thePull.getString3().equals(BAR))
               {
                   multiplier = THREE_BAR;
               }
           }
       }

      if (thePull.getString1().equals(SEVEN))
      {
         if (thePull.getString2().equals(SEVEN))
         {
            if (thePull.getString3().equals(SEVEN))
            {
               multiplier = THREE_SEVEN;
            }
         }
      }
      
      return multiplier;
   }
   
   /**
    * the output method of the program. this method take the TripleString object
    * and winning bonuses as parameters. then display the results of the pull,
    * the bonus, and the win/lose statements.
    * 
    * @param thePull
    * @param winnings
    */
   private static void display(TripleString thePull, int winnings)
   {
      System.out.println("Whirrrr... and your pull is...");
      System.out.print("\n|" + TAB + thePull.getString1() + TAB);
      System.out.print("|" + TAB + thePull.getString2() + TAB);
      System.out.print("|" + TAB + thePull.getString3() + TAB + "|" + "\n\n");
      
      if (winnings != 0)
      {
         System.out.println("Congratulations, You've won: $" + winnings);
      }
      else
      {
         System.out.println("Sorry you Lose");
      }
      
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
   
}

/**
 * The helper class. this class will create a TripleString object, which holds
 * three string as its member data.
 * 
 * @author R12KY
 * 
 */
class TripleString
{
   // declare the max length of each strings as constants
   private static final int MAX_LEN = 20;       // maximum length of each string
   private static final String ERROR = "Error!";// if the string is out of range
   
   // declare instance variables (three strings) as a member data.
   private String string1;
   private String string2;
   private String string3;
   
   // this is the default constructor of the object
   TripleString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   
   // this is the constructer of the object
   TripleString(String str1, String str2, String str3)
   {
      string1 = str1;
      string2 = str2;
      string3 = str3;
   }
   
   /**
    * a helper function that the mutators can use to determine whether a String
    * is legal. This method returns true if both the string is not null and its
    * length <= MAX_LEN and false, otherwise.
    * 
    * @param str
    * @return
    */
   boolean validString(String str)
   {
      if (!str.equals(null) && str.length() <= MAX_LEN)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**
    * the mutator of the string1, set the value of string1 if the string is
    * valid.
    * 
    * @param str
    * @return
    */
   public boolean setString1(String str)
   {
      if (validString(str) == true)
      {
         string1 = str;
         return true;
      }
      else
      {
         string1 = ERROR;
         return false;
      }
   }
   
   /**
    * the mutator of the string2, set the value of string2 if the string is
    * valid.
    * 
    * @param str
    * @return
    */
   public boolean setString2(String str)
   {
      if (validString(str) == true)
      {
         string2 = str;
         return true;
      }
      else
      {
         string2 = ERROR;
         return false;
      }
   }
   
   /**
    * the mutator of the string3, set the value of string3 if the string is
    * valid.
    * 
    * @param str
    * @return
    */
   public boolean setString3(String str)
   {
      if (validString(str) == true)
      {
         string3 = str;
         return true;
      }
      else
      {
         string3 = ERROR;
         return false;
      }
   }
   
   /**
    * the accessor of string1. return the value of string1
    * 
    * @return
    */
   public String getString1()
   {
      return string1;
   }
   
   /**
    * the accessor of string2. return the value of string2
    * 
    * @return
    */
   public String getString2()
   {
      return string2;
   }
   
   /**
    * the accessor of string3. return the value of string3
    * 
    * @return
    */
   public String getString3()
   {
      return string3;
   }
   
}

/*--------------------------------Sample Run------------------------------------

================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 1
================================================================================
Whirrrr... and your pull is...

|   7   |   BAR   |   Cherries   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 2
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   BAR   |   Space   |

Congratulations, You've won: $10
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 3
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   Space   |   7   |

Congratulations, You've won: $15
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 4
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   Cherries   |   BAR   |

Congratulations, You've won: $60
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 5
================================================================================
Whirrrr... and your pull is...

|   BAR   |   Space   |   BAR   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 6
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   7   |   Cherries   |

Congratulations, You've won: $30
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 7
================================================================================
Whirrrr... and your pull is...

|   BAR   |   BAR   |   BAR   |

Congratulations, You've won: $350
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 8
================================================================================
Whirrrr... and your pull is...

|   BAR   |   BAR   |   BAR   |

Congratulations, You've won: $400
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 9
================================================================================
Whirrrr... and your pull is...

|   BAR   |   Cherries   |   Space   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 10
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   BAR   |   7   |

Congratulations, You've won: $50
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 91
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   Cherries   |   BAR   |

Congratulations, You've won: $1365
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 92
================================================================================
Whirrrr... and your pull is...

|   BAR   |   Space   |   BAR   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 93
================================================================================
Whirrrr... and your pull is...

|   BAR   |   Space   |   BAR   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 94
================================================================================
Whirrrr... and your pull is...

|   BAR   |   BAR   |   BAR   |

Congratulations, You've won: $4700
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 95
================================================================================
Whirrrr... and your pull is...

|   Space   |   BAR   |   Space   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 96
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   BAR   |   BAR   |

Congratulations, You've won: $480
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 97
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   BAR   |   BAR   |

Congratulations, You've won: $485
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 98
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   BAR   |   BAR   |

Congratulations, You've won: $490
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 99
================================================================================
Whirrrr... and your pull is...

|   BAR   |   Cherries   |   Space   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 100
================================================================================
Whirrrr... and your pull is...

|   7   |   BAR   |   Cherries   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 101
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 102
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 103
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 104
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 105
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: -5
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: -4
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: -3
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: -2
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: -1
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 834
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 87
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   BAR   |   7   |

Congratulations, You've won: $435
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 23
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   BAR   |   BAR   |

Congratulations, You've won: $115
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 64
================================================================================
Whirrrr... and your pull is...

|   Space   |   Cherries   |   Space   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 54
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   Cherries   |   BAR   |

Congratulations, You've won: $810
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 23
================================================================================
Whirrrr... and your pull is...

|   BAR   |   Cherries   |   Cherries   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 67
================================================================================
Whirrrr... and your pull is...

|   BAR   |   Cherries   |   Space   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 23
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   BAR   |   BAR   |

Congratulations, You've won: $115
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 76
================================================================================
Whirrrr... and your pull is...

|   BAR   |   BAR   |   7   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 12
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   7   |   Cherries   |

Congratulations, You've won: $60
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 56
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   Cherries   |   Cherries   |

Congratulations, You've won: $1680
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 97
================================================================================
Whirrrr... and your pull is...

|   BAR   |   BAR   |   7   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 43
================================================================================
Whirrrr... and your pull is...

|   BAR   |   BAR   |   BAR   |

Congratulations, You've won: $2150
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 65
================================================================================
Whirrrr... and your pull is...

|   BAR   |   BAR   |   BAR   |

Congratulations, You've won: $3250
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 76
================================================================================
Whirrrr... and your pull is...

|   Cherries   |   Cherries   |   BAR   |

Congratulations, You've won: $1140
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 23
================================================================================
Whirrrr... and your pull is...

|   BAR   |   BAR   |   BAR   |

Congratulations, You've won: $1150
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 76
================================================================================
Whirrrr... and your pull is...

|   BAR   |   7   |   BAR   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 45
================================================================================
Whirrrr... and your pull is...

|   7   |   Cherries   |   BAR   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 98
================================================================================
Whirrrr... and your pull is...

|   BAR   |   7   |   BAR   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 23
================================================================================
Whirrrr... and your pull is...

|   BAR   |   BAR   |   BAR   |

Congratulations, You've won: $1150
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 65
================================================================================
Whirrrr... and your pull is...

|   BAR   |   7   |   BAR   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 345
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 63
================================================================================
Whirrrr... and your pull is...

|   7   |   Cherries   |   BAR   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 72
================================================================================
Whirrrr... and your pull is...

|   BAR   |   BAR   |   BAR   |

Congratulations, You've won: $3600
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 82
================================================================================
Whirrrr... and your pull is...

|   BAR   |   Space   |   7   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 100
================================================================================
Whirrrr... and your pull is...

|   7   |   7   |   Space   |

Sorry you Lose
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 101
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: -1
================================================================================
How much would you like to bet (1 - 100) or 0 to quit: 0
================================================================================

------------------------------------------------------------------------------*/