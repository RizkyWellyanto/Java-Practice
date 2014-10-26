/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 3 in CS 1B Jesse Cecil Winter 2014
 * this program is called Cellular Automata. this program is about to test the
 * understanding and implementation of complex combination of binary and
 * algorithms
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

// import necessary classes
import java.util.Scanner;

/**
 * Main client of the whole program
 * 
 * @author MuhammadRizky
 */
public class Foothill
{
   // declare instance variables and objects
   static Scanner inputStream;
   
   // main method of the whole program
   public static void main(String[] args)
   {
      // declare necessary variables and objects
      inputStream = new Scanner(System.in);  // instantiate scanner object
      Automaton automata1;                   // declare Automaton object
      int inputInteger;                      // to hold any integer
      String inputStr;                       // to hold any input string
      char QUIT = 'Q';                       // the quit character
      char RESUME = 'R';                     // the resume character
      boolean continuity = true;             // maintain the flow of the main
      
      // indefinite loop for multiple entries
      while (continuity == true)
      {
         // indefinite loop to promp the user
         while (true)
         {
            // prompt the user for input and filter the input
            try
            {
               createLine(80, "=");
               System.out.print("Please enter the rule (integer 0-255): ");
               
               inputStr = inputStream.nextLine();
               inputInteger = Integer.parseInt(inputStr);
               
               if (inputInteger < Automaton.MIN_RANGE
                     || inputInteger > Automaton.MAX_RANGE)
               {
                  continue;
               }
            }
            // if something wrong, give message and loop again
            catch (NumberFormatException error)
            {
               System.out.println("Error. Enter Integer only, between 0-255. "
                     + "Please Try Again");
               createLine(80, "=");
               
               continue;
            }
            
            // if everything is alright, move on
            break;
         }
         
         // instantiates the Automaton objects
         automata1 = new Automaton(inputInteger);
         
         // show the results
         for (int i = 0; i < 100; i++)
         {
            System.out.println(automata1.toStringCurrentGen());
            automata1.propagateNewGeneration();
         }
         
         // prompt the user whether they want to keep giving input or not
         while (true)
         {
            createLine(80, "=");
            System.out.print("Enter \"Q\" to quit. enter \"R\" to resume: ");
            inputStr = inputStream.nextLine();
            
            // if he user enter Q, end the program
            if (inputStr.toUpperCase().charAt(0) == QUIT)
            {
               continuity = false;
               break;
            }
            // if the user enter R, reloop to the main loop
            else if (inputStr.toUpperCase().charAt(0) == RESUME)
            {
               break;
            }
            // if user enters weird things, ask for input again
            else
            {
               continue;
            }
         }
         
      }
      
      // end the program
      createLine(80, "=");
      System.out.println("Program Shutted Down.");
      inputStream.close();
      createLine(80, "=");
   }
   
   // other helper methods
   /**
    * this method is just to make the program look "beautiful"
    * 
    * @param length the length of the line
    * @param shape the shape of the caption
    */
   private static void createLine(int length, String shape)
   {
      for (int counter = 0; counter < length; counter += 1)
      {
         System.out.print(shape);
      }
   }
}

class Automaton
{
   // class constants
   public final static int MIN_DISPLAY_WIDTH = 0;
   public final static int MAX_DISPLAY_WIDTH = 121;
   public final static int DEFAULT_WIDTH = 79;
   public static final int MAX_RANGE = 255;
   public static final int MIN_RANGE = 0;
   public static final int BINARY_BASE = 8;
   public static final char BIT_1 = '*';
   public static final char BIT_0 = ' ';
   
   // declare private members
   private boolean rules[] = new boolean[BINARY_BASE];// the sets of rules
   private String thisGen;                            // current generation
   private String extremeBit;                         // bit, "*" and " "
   private int displayWidth = DEFAULT_WIDTH;          // width. odd number
   
   // constructors
   public Automaton(int rule)
   {
      if (!setRule(rule))
      {
         setRule(0);
      }
      resetFirstGen();
   }
   
   public Automaton()
   {
      this(0);
   }
   
   // mutators
   /**
    * this method get an integer as a parameter, filter the input, and then 
    * assign the value to the rule array
    * 
    * @param rule as the intended rule integer
    * @return true if it is a success, false otherwise
    */
   public boolean setRule(int rule)
   {
      // filter the input
      if (rule >= MIN_RANGE && rule <= MAX_RANGE)
      {
         // convert the input to bianry
         for (int i = 0; i < BINARY_BASE; i++)
         {
            rules[i] = ((1 << i & rule) != 0);
         }
         
         // return true if success
         return true;
      }
      
      // return false otherwise
      return false;
   }
   
   /**
    * this method get integer as a parameter, filter the input, and then assign
    * the value to the displayWidth member data
    * 
    * @param width as the intended width integer
    * @return true if it is a success, false otherwise
    */
   public boolean setDisplayWidth(int width)
   {
      // filter the input
      if (width > MIN_DISPLAY_WIDTH && width <= MAX_DISPLAY_WIDTH
            && width % 2 == 1)
      {
         // set the value if in range
         displayWidth = width;
         return true;
      }
      
      // return false otherwise
      return false;
   }
   
   // other public methods
   /**
    * this method reset the thisGen member data. and the extremeBit member data
    * to default values.
    */
   public void resetFirstGen()
   {
      thisGen = String.valueOf(BIT_1);
      extremeBit = String.valueOf(BIT_0);
   }
   
   /**
    * this method is the output method of the program. this method either add
    * characters to the current generation or truncated it, in order to fit
    * the line as long as the displayWidth member data
    * 
    * @return the thisGen string which is as long as displayWidth member data
    */
   public String toStringCurrentGen()
   {
      int halfLength;   // holds the supposed length of extremeBits
      String str = "";  // a variable that holds the output string
      StringBuilder tempStr = new StringBuilder(thisGen);
      
      // if the thisGen is longer than the displayWidth, truncate the string
      if (thisGen.length() > displayWidth)
      {
         while (tempStr.length() > displayWidth)
         {
            // delete the leftmost char
            tempStr.deleteCharAt(0);
            
            // if the string is still longer,
            if (tempStr.length() > displayWidth)
            {
               // delete the rightmost char
               tempStr.deleteCharAt(tempStr.length() - 1);
            }
         }
         
         // convert the StringBuilder into a String again
         str = tempStr.toString();
      }
      // if the thisGen is less than the displayWidth, add extremeBits
      else
      {
         // calculate the length
         halfLength = (displayWidth - thisGen.length()) / 2;
         
         // add extremeBits
         for (int i = 0; i < halfLength; i++)
         {
            str += extremeBit;
         }
         
         // add the current Generation
         str += thisGen;
         
         // append the rest of the extreme bits
         for (int i = 0; i < halfLength; i++)
         {
            str += extremeBit;
         }
      }
      
      return str;
   }
   
   /**
    * the process part of the program. it takes thisGen, extremeBit, and rules[]
    * to compute the next generation. then assign the next generation to the
    * thisGen member data.
    */
   public void propagateNewGeneration()
   {
      // create necessary local variables
      StringBuilder tempGen = new StringBuilder(thisGen);
      
      // holds the new generation
      StringBuilder newGen = new StringBuilder();
      
      // append 2 extreme bit at each end of the current generation
      for (int i = 0; i < 2; i++)
      {
         tempGen.insert(0, extremeBit);
         tempGen.append(extremeBit);
      }
      
      // dissect every 3 bits of the current generation
      for (int i = 0; i < thisGen.length() + 2; i++)
      {
         String str; // string to hold triple bits in the form of binary
         int index;  // int to hold the decimal value of the binary triple bits
         
         // take three substrings from tempGen. convert it to the form of binary
         str = tempGen.substring(i, i + 3).replace(BIT_0, '0')
               .replace(BIT_1, '1').toString();
         
         // convert binary string into decimal integer
         index = Integer.parseInt(str, 2);
         
         // look for the value in the rules array. append it to the newGen
         newGen.append(rules[index] ? "1" : "0");
      }
      
      // convert the binary to string. point the newGen as the thisGen
      thisGen = newGen.toString().replace('0', BIT_0).replace('1', BIT_1);
      
      String str = "";// string to hold a triplet of extremeBit
      int index;      // index to hold decimal value of the triple extremeBit
      
      // build a triple bit of extremeBit
      for (int i = 0; i < 3; i++)
      {
         str += extremeBit;
      }
      
      str = str.replace(BIT_0, '0').replace(BIT_1, '1').toString();
      
      // convert the binary string into decimal integer
      index = Integer.parseInt(str, 2);
      
      // assign the new string as the new extremeBit
      extremeBit = (rules[index]) ? String.valueOf(BIT_1) : String
            .valueOf(BIT_0);
   }
}

/*------------------------------Sample Run--------------------------------------

================================================================================
Please enter the rule (integer 0-255): 4
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
================================================================================
Enter "Q" to quit. enter "R" to resume: r
================================================================================
Please enter the rule (integer 0-255): 126
                                       *                                       
                                      ***                                      
                                     ** **                                     
                                    *******                                    
                                   **     **                                   
                                  ****   ****                                  
                                 **  ** **  **                                 
                                ***************                                
                               **             **                               
                              ****           ****                              
                             **  **         **  **                             
                            ********       ********                            
                           **      **     **      **                           
                          ****    ****   ****    ****                          
                         **  **  **  ** **  **  **  **                         
                        *******************************                        
                       **                             **                       
                      ****                           ****                      
                     **  **                         **  **                     
                    ********                       ********                    
                   **      **                     **      **                   
                  ****    ****                   ****    ****                  
                 **  **  **  **                 **  **  **  **                 
                ****************               ****************                
               **              **             **              **               
              ****            ****           ****            ****              
             **  **          **  **         **  **          **  **             
            ********        ********       ********        ********            
           **      **      **      **     **      **      **      **           
          ****    ****    ****    ****   ****    ****    ****    ****          
         **  **  **  **  **  **  **  ** **  **  **  **  **  **  **  **         
        ***************************************************************        
       **                                                             **       
      ****                                                           ****      
     **  **                                                         **  **     
    ********                                                       ********    
   **      **                                                     **      **   
  ****    ****                                                   ****    ****  
 **  **  **  **                                                 **  **  **  ** 
****************                                               ****************
*              **                                             **              *
**            ****                                           ****            **
 **          **  **                                         **  **          ** 
****        ********                                       ********        ****
   **      **      **                                     **      **      **   
  ****    ****    ****                                   ****    ****    ****  
 **  **  **  **  **  **                                 **  **  **  **  **  ** 
************************                               ************************
                       **                             **                       
                      ****                           ****                      
                     **  **                         **  **                     
                    ********                       ********                    
                   **      **                     **      **                   
                  ****    ****                   ****    ****                  
                 **  **  **  **                 **  **  **  **                 
                ****************               ****************                
*              **              **             **              **              *
**            ****            ****           ****            ****            **
 **          **  **          **  **         **  **          **  **          ** 
****        ********        ********       ********        ********        ****
   **      **      **      **      **     **      **      **      **      **   
  ****    ****    ****    ****    ****   ****    ****    ****    ****    ****  
 **  **  **  **  **  **  **  **  **  ** **  **  **  **  **  **  **  **  **  ** 
*******************************************************************************
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
*                                                                             *
**                                                                           **
 **                                                                         ** 
****                                                                       ****
   **                                                                     **   
  ****                                                                   ****  
 **  **                                                                 **  ** 
********                                                               ********
       **                                                             **       
      ****                                                           ****      
     **  **                                                         **  **     
    ********                                                       ********    
================================================================================
Enter "Q" to quit. enter "R" to resume: r
================================================================================
Please enter the rule (integer 0-255): 130
                                       *                                       
                                      *                                        
                                     *                                         
                                    *                                          
                                   *                                           
                                  *                                            
                                 *                                             
                                *                                              
                               *                                               
                              *                                                
                             *                                                 
                            *                                                  
                           *                                                   
                          *                                                    
                         *                                                     
                        *                                                      
                       *                                                       
                      *                                                        
                     *                                                         
                    *                                                          
                   *                                                           
                  *                                                            
                 *                                                             
                *                                                              
               *                                                               
              *                                                                
             *                                                                 
            *                                                                  
           *                                                                   
          *                                                                    
         *                                                                     
        *                                                                      
       *                                                                       
      *                                                                        
     *                                                                         
    *                                                                          
   *                                                                           
  *                                                                            
 *                                                                             
*                                                                              
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
================================================================================
Enter "Q" to quit. enter "R" to resume: r
================================================================================
Please enter the rule (integer 0-255): 124
                                       *                                       
                                       **                                      
                                       ***                                     
                                       * **                                    
                                       *****                                   
                                       *   **                                  
                                       **  ***                                 
                                       *** * **                                
                                       * *******                               
                                       ***     **                              
                                       * **    ***                             
                                       *****   * **                            
                                       *   **  *****                           
                                       **  *** *   **                          
                                       *** * ****  ***                         
                                       * *****  ** * **                        
                                       ***   ** ********                       
                                       * **  ****      **                      
                                       ***** *  **     ***                     
                                       *   **** ***    * **                    
                                       **  *  *** **   *****                   
                                       *** ** * *****  *   **                  
                                       * ********   ** **  ***                 
                                       ***      **  ****** * **                
                                       * **     *** *    *******               
                                       *****    * ****   *     **              
                                       *   **   ***  **  **    ***             
                                       **  ***  * ** *** ***   * **            
                                       *** * ** ****** *** **  *****           
                                       * ********    *** ***** *   **          
                                       ***      **   * ***   ****  ***         
                                       * **     ***  *** **  *  ** * **        
                                       *****    * ** * ***** ** ********       
                                       *   **   ********   ******      **      
                                       **  ***  *      **  *    **     ***     
                                       *** * ** **     *** **   ***    * **    
                                       * **********    * *****  * **   *****   
                                       ***        **   ***   ** *****  *   **  
                                       * **       ***  * **  ****   ** **  *** 
                                       *****      * ** ***** *  **  ****** * **
                                       *   **     ******   **** *** *    ******
                                       **  ***    *    **  *  *** ****   *     
                                       *** * **   **   *** ** * ***  **  **    
                                       * *******  ***  * ******** ** *** ***   
                                       ***     ** * ** ***      ****** *** **  
                                       * **    ********* **     *    *** ***** 
                                       *****   *       *****    **   * ***   **
                                       *   **  **      *   **   ***  *** **  * 
                                       **  *** ***     **  ***  * ** * ***** **
                                       *** * *** **    *** * ** ********   ****
                                       * ***** *****   * ********      **  *   
                                       ***   ***   **  ***      **     *** **  
                                       * **  * **  *** * **     ***    * ***** 
                                       ***** ***** * *******    * **   ***   **
                                       *   ***   *****     **   *****  * **  **
                                       **  * **  *   **    ***  *   ** ***** * 
                                       *** ***** **  ***   * ** **  ****   ****
                                       * ***   ***** * **  ******** *  **  *  *
                                       *** **  *   ******* *      **** *** ** *
                                       * ***** **  *     ****     *  *** ******
                                       ***   ***** **    *  **    ** * ***     
                                       * **  *   *****   ** ***   ****** **    
                                       ***** **  *   **  **** **  *    *****   
                                       *   ***** **  *** *  ***** **   *   **  
                                       **  *   ***** * **** *   *****  **  *** 
                                       *** **  *   *****  ****  *   ** *** * **
                                       * ***** **  *   ** *  ** **  **** ******
                                       ***   ***** **  ***** ****** *  ***     
                                       * **  *   ***** *   ***    **** * **    
                                       ***** **  *   ****  * **   *  *******   
                                       *   ***** **  *  ** *****  ** *     **  
                                       **  *   ***** ** ****   ** *****    *** 
                                       *** **  *   ******  **  ****   **   * **
                                       * ***** **  *    ** *** *  **  ***  ****
                                       ***   ***** **   **** **** *** * ** *   
                                       * **  *   *****  *  ***  *** *********  
                                       ***** **  *   ** ** * ** * ***       ** 
                                       *   ***** **  ************** **      ***
                                       **  *   ***** *            *****     * *
                                       *** **  *   ****           *   **    ***
                                       * ***** **  *  **          **  ***   *  
                                       ***   ***** ** ***         *** * **  ** 
                                       * **  *   ****** **        * ******* ***
                                       ***** **  *    *****       ***     *** *
                                       *   ***** **   *   **      * **    * ***
                                       **  *   *****  **  ***     *****   *** *
                                       *** **  *   ** *** * **    *   **  * ***
                                       * ***** **  **** *******   **  *** ***  
                                       ***   ***** *  ***     **  *** * *** ** 
                                       * **  *   **** * **    *** * ***** *****
                                       ***** **  *  *******   * *****   ***   *
                                       *   ***** ** *     **  ***   **  * **  *
                                       **  *   *******    *** * **  *** ***** *
                                       *** **  *     **   * ******* * ***   ***
                                       * ***** **    ***  ***     ***** **  *  
                                       ***   *****   * ** * **    *   ***** ** 
                                       * **  *   **  **********   **  *   *****
                                       ***** **  *** *        **  *** **  *   *
                                       *   ***** * ****       *** * ***** **  *
                                       **  *   *****  **      * *****   ***** *
================================================================================
Enter "Q" to quit. enter "R" to resume: r
================================================================================
Please enter the rule (integer 0-255): 94
                                       *                                       
                                      ***                                      
                                     ** **                                     
                                    *** ***                                    
                                   ** * * **                                   
                                  *** * * ***                                  
                                 ** * * * * **                                 
                                *** * * * * ***                                
                               ** * * * * * * **                               
                              *** * * * * * * ***                              
                             ** * * * * * * * * **                             
                            *** * * * * * * * * ***                            
                           ** * * * * * * * * * * **                           
                          *** * * * * * * * * * * ***                          
                         ** * * * * * * * * * * * * **                         
                        *** * * * * * * * * * * * * ***                        
                       ** * * * * * * * * * * * * * * **                       
                      *** * * * * * * * * * * * * * * ***                      
                     ** * * * * * * * * * * * * * * * * **                     
                    *** * * * * * * * * * * * * * * * * ***                    
                   ** * * * * * * * * * * * * * * * * * * **                   
                  *** * * * * * * * * * * * * * * * * * * ***                  
                 ** * * * * * * * * * * * * * * * * * * * * **                 
                *** * * * * * * * * * * * * * * * * * * * * ***                
               ** * * * * * * * * * * * * * * * * * * * * * * **               
              *** * * * * * * * * * * * * * * * * * * * * * * ***              
             ** * * * * * * * * * * * * * * * * * * * * * * * * **             
            *** * * * * * * * * * * * * * * * * * * * * * * * * ***            
           ** * * * * * * * * * * * * * * * * * * * * * * * * * * **           
          *** * * * * * * * * * * * * * * * * * * * * * * * * * * ***          
         ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * **         
        *** * * * * * * * * * * * * * * * * * * * * * * * * * * * * ***        
       ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **       
      *** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ***      
     ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **     
    *** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ***    
   ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **   
  *** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ***  
 ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ** 
*** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ***
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
================================================================================
Enter "Q" to quit. enter "R" to resume: quit
================================================================================
Program Shutted Down.
================================================================================

------------------------------------------------------------------------------*/