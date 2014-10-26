package Phase1;

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

// import necessary classes
import javax.swing.*;
import java.awt.*;

/**
 * Main Class of the Program
 * 
 * @author MuhammadRizky
 * 
 */
public class Foothill
{
   // declare statics for each cards
   static final int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1back-of-card image
   static Icon[] icons;
   static JLabel[] labels;
   
   // main method
   public static void main(String[] args)
   {
      int k;
      
      // prepare the image icon array
      loadCardIcons();
      
      // establish main frame in which program will run
      JFrame frmMyWindow = new JFrame("Wellyanto's Cards");
      frmMyWindow.setSize(1150, 650);
      frmMyWindow.setLocationRelativeTo(null);
      frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);
      frmMyWindow.setLayout(layout);
      
      // prepare the image label array
      JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         labels[k] = new JLabel(icons[k]);
      
      // place your 3 controls into frame
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         frmMyWindow.add(labels[k]);
      
      // show everything to the user
      frmMyWindow.setVisible(true);
   }
   
   // other methods
   /**
    * this method build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif",
    * etc.)in a SHORT loop. For each file name, read it in and use it to
    * instantiate each of the 57 Icons in the icon[] array.
    */
   static void loadCardIcons()
   {
      // instantiate and load all cards except the back card
      for (int i = 0; i < icons.length - 1; i++)
      {
         icons[i] = new ImageIcon("images/" + turnIntIntoCardValue(i % 14)
               + turnIntIntoCardSuit(i / 14) + ".gif");
      }
      
      // instantiate and load the back card
      icons[NUM_CARD_IMAGES - 1] = new ImageIcon("images/BK.gif");
      
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
}
