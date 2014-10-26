/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 5 in CS 1B Jesse Cecil Winter 2014
 * this program is called GUI Cards. this program test the knowledge of multi
 * class programming with Graphical User Interaction and also combined with
 * inheritance. this is the first program to combine all of these above.
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

/*
 * This class is the benefactor of most of the GUI machinery we tested in Phase
 * 1. It will read the image files and store them in a static Icon array. Rather
 * than a 1-D array of Phase 1 (if you followed my earlier outline), this will
 * be a 2-D array to facilitate addressing the value and suit of a Card in order
 * get its Icon. While simple in principle (just read the Icons and store them
 * in an array for client use), the details are subtle. We have to be able to
 * convert from chars and suits to ints, and back again, in order to find the
 * Icon for any given Card object. The overview of the class data and methods,
 * shown below, will suggest the right approach and should take the mystery out
 * of this class.
 */

// import necessary classes
import javax.swing.*;

public class GUICard
{
   // member datas
   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K +
                                                             // joker
   private static Icon iconBack;
   static boolean iconsLoaded = false;
   
   /*
    * The difference here is that we are storing the Icons in a 2-D array. So
    * you have to use a nested for-loop, suits and values, to generate the 2-D
    * index for each Icon in the array. Another suggestion: I'd like you to not
    * require the client to call this method. Think about where you would need
    * to call it and how can you avoid having the method reload the icons after
    * it has already loaded them once. The hint is in the static boolean
    * iconsLoaded = false;, above. Hint: Call this method any time you might
    * need an Icon, but make sure that it loads the entire array the first time
    * it is called, and does nothing any later time.
    */
   
   /**
    * this method build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif",
    * etc.)in a SHORT loop. For each file name, read it in and use it to
    * instantiate each of the 57 Icons in the icon[] array.
    */
   static void loadCardIcons()
   {
      while (iconsLoaded == false)
      {
         // instantiate and load all cards except the back card
         for (int i = 0; i < iconCards.length - 1; i++)
         {
            for (int j = 0; j < iconCards[0].length; j++)
            {
               iconCards[i][j] = new ImageIcon("images/"
                     + turnIntIntoCardValue(i) + turnIntIntoCardSuit(j)
                     + ".gif");
            }
         }
         
         // instantiate and load the back card
         iconBack = new ImageIcon("images/BK.gif");
         
         // make the code only run once
         iconsLoaded = true;
      }
   }
   
   /**
    * turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
    * 
    * @param k
    * @return card values
    */
   static String turnIntIntoCardValue(int k)
   {
      // declare necessary variables
      String str;
      
      // process the input
      if (k == 0)
         str = "A";
      else if (k == 9)
         str = "T";
      else if (k == 10)
         str = "J";
      else if (k == 11)
         str = "Q";
      else if (k == 12)
         str = "K";
      else if (k == 13)
         str = "X";
      else
         str = String.valueOf(k);
      
      // return the output
      return str;
   }
   
   /**
    * turns 0 - 3 into "C", "D", "H", "S"
    * 
    * @param j
    * @return card suits
    */
   static String turnIntIntoCardSuit(int j)
   {
      // declare necessary variables
      String str;
      
      // process the input
      if (j == 0)
         str = "C";
      else if (j == 1)
         str = "D";
      else if (j == 2)
         str = "H";
      else
         str = "S";
      
      // return the output
      return str;
   }
   
   /**
    * This method takes a Card object from the client, and returns the Icon for
    * that card. It would be used when the client needs to instantiate or change
    * a JLabel
    * 
    * @param card
    * @return
    */
   static public Icon getIcon(Card card)
   {
      loadCardIcons(); // will not load twice, so no worries.
      return iconCards[valueAsInt(card)][suitAsInt(card)];
   }
   
   /**
    * return the CardIcon
    * 
    * @return
    */
   static public Icon getBackCardIcon()
   {
      loadCardIcons();
      return iconBack;
   }
   
   /**
    * return an integer of that card's value
    * 
    * @param card
    * @return integer of the card's value
    */
   static int valueAsInt(Card card)
   {
      if (card.getVal() == 'A')
         return 0;
      else if (card.getVal() == 'T')
         return 9;
      else if (card.getVal() == 'J')
         return 10;
      else if (card.getVal() == 'Q')
         return 11;
      else if (card.getVal() == 'K')
         return 12;
      else
         return Character.getNumericValue(card.getVal());
      
   }
   
   /**
    * return an integer of that card's suit
    * 
    * @param card
    * @return integer of the card's suit
    */
   static int suitAsInt(Card card)
   {
      if (card.getSuit() == Card.Suit.clubs)
         return 0;
      else if (card.getSuit() == Card.Suit.diamonds)
         return 1;
      else if (card.getSuit() == Card.Suit.hearts)
         return 2;
      else
         return 3;
   }
   
}
