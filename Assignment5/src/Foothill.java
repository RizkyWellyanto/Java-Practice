/* File: Foothill.java
 * -----------------------------------------------------------------------------
 * This program is due to Assignment_5 in course "FH CS 001 ML F13"
 * this program get input keychar and input string from the user, then modified
 * the inputed string based on the input keychar. its basically about text
 * editing program in java
 * Created By: Muhammad Rizky Wellyanto
 * Student ID: 20144588
 */

// import necessary classes
import java.util.Scanner;

public class Foothill
{
   static final int MAX_STR_LEN = 80;  // maximum length of target string
   static final int MIN_STR_LEN = 4;   // minimum length of target string
   static final int MIN_KEY_LEN = 0;   // minimum length of the keyword
   static final int MAX_KEY_LEN = 1;   // maximum length of the keyword
   
   static Scanner keyboard;            // create a global scanner
   
   public static void main(String[] args)
   {
      // declare necessary variable
      String targetString, maskedString, removedCharString;
      char keyCharacter;
      int keyCount;
      
      // create scanner object
      keyboard = new Scanner(System.in);
      
      // create title
      createLine(80, "-");
      System.out.println("TEXT PROCESSING PROGRAM~");
      createLine(80, "-");
      
      // get crucial inputs
      keyCharacter = getKeyCharacter();
      createLine(80, "-");
      targetString = getString();
      createLine(80, "-");
      
      // display the occurrence of the key character
      keyCount = countKey(targetString, keyCharacter);
      System.out.println("   # of Key Character : " + keyCount);
      createLine(80, "-");
      
      // display the masked string
      maskedString = maskCharacter(targetString, keyCharacter);
      System.out.println("   String with '" + keyCharacter + "' masked : " );
      System.out.println(maskedString);
      createLine(80, "-");
      
      // display the removed char string
      removedCharString = removeCharacter(targetString, keyCharacter);
      System.out.println("   String with '" + keyCharacter + "' removed : " );
      System.out.println(removedCharString);
      createLine(80, "-");
      
      // close the keyboard
      keyboard.close();
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
   
   /* getKeyCharacter spec:
    * This method requests a single character from the user and continues to 
    * ask for it in a loop until the user gets it right:  this method will 
    * test to make sure the user only types one single character.  0, 2, 3 or 
    * more characters will be flagged as an error and the method will keep at 
    * the user until he types just one character.  You are not required to use 
    * a char as an input variable -- in fact, you cannot solve the problem 
    * using a char as input (you must think about this and make the appropriate 
    * choice here).  What matters is that a char is returned, as a functional 
    * return, to the client, main().
    */
   private static char getKeyCharacter()
   {
      String inputString;
      char keyChar;
      
      do 
      {
         System.out.print("Please enter a single letter as a key: ");
         inputString = keyboard.nextLine();
      } while (inputString.length() < MIN_KEY_LEN || 
            inputString.length() > MAX_KEY_LEN);
      
      keyChar = inputString.charAt(0);
      
      return keyChar;
   }
   
   /* getString spec:
    * This method requests a string from the user and continues to ask for it 
    * in a loop until the user gets it right:  this method will test to make 
    * sure the user only types a string that has at least 4 letters.  The 
    * acquired string will be returned as a functional return. 
    */
   private static String getString()
   {
      String theString;
      
      do 
      {
         System.out.println("Please enter a phrase or sentence: ");
         theString = keyboard.nextLine();
      } while (theString.length() < MIN_STR_LEN || 
            theString.length() > MAX_STR_LEN);
      
      return theString;
   }
   
   /* maskCharacter spec:
    * This method will take both a string and a character as parameters 
    * and return a new string that has each occurrence of the key character 
    * replaced by a dash, '-'.
    */
   private static String maskCharacter(String theString, char keyCharacter)
   {
      String maskedString = "";
      
      for(int counter = 0; counter < theString.length(); counter += 1)
      {
         if(theString.charAt(counter) == keyCharacter)
         {
            maskedString += "-";
         }
         else
         {
            maskedString += theString.charAt(counter);
         }
      }
      
      return maskedString;
   }
   
   /* removeCharacter spec:
    * This method will take both a string and a character as parameters and 
    * return a new string that has each occurrence of the key character 
    * removed, but all other characters left intact. This will result in a 
    * string which is shorter than the original by the number of occurances of 
    * the key character.
    */
   private static String removeCharacter(String theString, char keyCharacter)
   {
      String removedCharString = "";
      
      for(int counter = 0; counter < theString.length(); counter += 1)
      {
         if(theString.charAt(counter) == keyCharacter)
         {
            removedCharString += "";
         }
         else
         {
            removedCharString += theString.charAt(counter);
         }
      }
      
      return removedCharString;
   }
   
   /* countKey spec:
    * This method will take both a string and a character as parameters, 
    * and return the number of key character that appear in the string 
    * (case sensitive).
    */
   private static int countKey(String theString, char keyCharacter)
   {
      int keyCount = 0;
      
      for(int counter = 0; counter < theString.length(); counter += 1)
      {
         if(theString.charAt(counter) == keyCharacter)
         {
            keyCount += 1;
         }
      }
      
      return keyCount;
   }
   
}

/* Note: I learned my lesson again Mr. Loceff. 
 *       - this time i make sure no tabs slipped into my code... (sob... T_T)
 *       - and provide the number of sample run needed. (i forgot to add one 
 *       the last time...)
 *       - i want to try the harder options but i'm afraid i don't have much 
 *       time (my english class stole a lot of my time...)
 *       
 *       is there any room for improvement sir?
 */

/*---------------------------Sample Run 1---------------------------------------

--------------------------------------------------------------------------------
TEXT PROCESSING PROGRAM~
--------------------------------------------------------------------------------
Please enter a single letter as a key: hehe
Please enter a single letter as a key: haha
Please enter a single letter as a key: im going nuts
Please enter a single letter as a key: okey
Please enter a single letter as a key: o
--------------------------------------------------------------------------------
Please enter a phrase or sentence: 
mama romama gaga OOh Laa laa haha Oo0o0hohow woo)o0OhoOoo--12.~
--------------------------------------------------------------------------------
   # of Key Character : 11
--------------------------------------------------------------------------------
   String with 'o' masked : 
mama r-mama gaga OOh Laa laa haha O-0-0h-h-w w--)-0Oh-O----12.~
--------------------------------------------------------------------------------
   String with 'o' removed : 
mama rmama gaga OOh Laa laa haha O00hhw w)0OhO--12.~
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/

/*---------------------------Sample Run 2---------------------------------------

--------------------------------------------------------------------------------
TEXT PROCESSING PROGRAM~
--------------------------------------------------------------------------------
Please enter a single letter as a key: W
--------------------------------------------------------------------------------
Please enter a phrase or sentence: 
my
Please enter a phrase or sentence: 
my name is Muhammad Rizky Wellyanto. Wellyanto WITH "W"
--------------------------------------------------------------------------------
   # of Key Character : 4
--------------------------------------------------------------------------------
   String with 'W' masked : 
my name is Muhammad Rizky -ellyanto. -ellyanto -ITH "-"
--------------------------------------------------------------------------------
   String with 'W' removed : 
my name is Muhammad Rizky ellyanto. ellyanto ITH ""
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/

/*---------------------------Sample Run 3---------------------------------------

--------------------------------------------------------------------------------
TEXT PROCESSING PROGRAM~
--------------------------------------------------------------------------------
Please enter a single letter as a key: hfwiuehwuhe
Please enter a single letter as a key: -00
Please enter a single letter as a key: true
Please enter a single letter as a key: .
--------------------------------------------------------------------------------
Please enter a phrase or sentence: 
do.dont.doood... i want to try the. harder. assignment. but.dont. have. time...
--------------------------------------------------------------------------------
   # of Key Character : 14
--------------------------------------------------------------------------------
   String with '.' masked : 
do-dont-doood--- i want to try the- harder- assignment- but-dont- have- time---
--------------------------------------------------------------------------------
   String with '.' removed : 
dodontdoood i want to try the harder assignment butdont have time
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/

/*---------------------------Sample Run 4---------------------------------------

--------------------------------------------------------------------------------
TEXT PROCESSING PROGRAM~
--------------------------------------------------------------------------------
Please enter a single letter as a key: hd
Please enter a single letter as a key: a
--------------------------------------------------------------------------------
Please enter a phrase or sentence: 
he who laugh hahahaha lalala blablabla okay he im going crazy
--------------------------------------------------------------------------------
   # of Key Character : 13
--------------------------------------------------------------------------------
   String with 'a' masked : 
he who l-ugh h-h-h-h- l-l-l- bl-bl-bl- ok-y he im going cr-zy
--------------------------------------------------------------------------------
   String with 'a' removed : 
he who lugh hhhh lll blblbl oky he im going crzy
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/

/*---------------------------Sample Run 5---------------------------------------

--------------------------------------------------------------------------------
TEXT PROCESSING PROGRAM~
--------------------------------------------------------------------------------
Please enter a single letter as a key: skhkjhf
Please enter a single letter as a key: 1
--------------------------------------------------------------------------------
Please enter a phrase or sentence: 
3hiu3iug981701981iaibsiu1
--------------------------------------------------------------------------------
   # of Key Character : 4
--------------------------------------------------------------------------------
   String with '1' masked : 
3hiu3iug98-70-98-iaibsiu-
--------------------------------------------------------------------------------
   String with '1' removed : 
3hiu3iug987098iaibsiu
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/
