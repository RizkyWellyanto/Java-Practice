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
import java.awt.GridLayout;

import javax.swing.*;

/**
 * Main Class of the Program
 * 
 * @author MuhammadRizky
 * 
 */
public class Foothill
{
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
   static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];
   
   public static void main(String[] args)
   {
      int k;
      Icon tempIcon;
      int numPacksPerDeck = 1;
      int numJokersPerPack = 0;
      int numUnusedCarsPerPack = 0;
      Card[] unusedCardsPerPack = null;
      
      CardGameFramework toyGame = new CardGameFramework(numPacksPerDeck,
            numJokersPerPack, numUnusedCarsPerPack, unusedCardsPerPack,
            NUM_PLAYERS, NUM_CARDS_PER_HAND);
      
      // establish main frame in which program will run
      CardTable myCardTable = new CardTable("CS 1B CardTable",
            NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      toyGame.deal();
      
      // CREATE LABELS ----------------------------------------------------
      for (k = 0; k < computerLabels.length; k++)
         computerLabels[k] = new JLabel(GUICard.getBackCardIcon());
      
      for (k = 0; k < computerLabels.length; k++)
      {
         tempIcon = GUICard.getIcon(toyGame.getHand(1).inspectCard(k));
         humanLabels[k] = new JLabel(tempIcon);
      }
      
      // ADD LABELS TO PANELS -----------------------------------------
      for (k = 0; k < computerLabels.length; k++)
         myCardTable.compTablePanel.add(computerLabels[k]);
      
      for (k = 0; k < humanLabels.length; k++)
         myCardTable.humanTablePanel.add(humanLabels[k]);
      
      // and two random cards in the play region (simulating a computer/hum ply)
      myCardTable.playTablePanel.setLayout(new GridLayout(2,2));
      
      tempIcon = GUICard.getIcon(generateRandomCard());
      myCardTable.playTablePanel.add(new JLabel(tempIcon));
      
      tempIcon = GUICard.getIcon(generateRandomCard());
      myCardTable.playTablePanel.add(new JLabel(tempIcon));
      
      JLabel compLabel = new JLabel("Computer", JLabel.CENTER);
      JLabel humLabel = new JLabel("You", JLabel.CENTER);
      myCardTable.playTablePanel.add(compLabel);
      myCardTable.playTablePanel.add(humLabel);
      
      // show everything to the user
      myCardTable.setVisible(true);
   }
   
   // other methods
   static Card generateRandomCard()
   {
      Card.Suit suit;
      char val;
      
      int suitSelector, valSelector;
      
      // get random suit and value
      suitSelector = (int) (Math.random() * 4);
      valSelector = (int) (Math.random() * 13);
      
      // pick suit
      suit = turnIntIntoSuit(suitSelector);
      val = turnIntIntoVal(valSelector);
      
      return new Card(val, suit);
   }
   
   static Card.Suit turnIntIntoSuit(int inputInt)
   {
      if (inputInt == 0)
         return Card.Suit.clubs;
      else if (inputInt == 1)
         return Card.Suit.diamonds;
      else if (inputInt == 2)
         return Card.Suit.hearts;
      else
         return Card.Suit.spades;
   }
   
   static char turnIntIntoVal(int inputInt)
   {
      if (inputInt == 0)
         return 'A';
      else if (inputInt == 9)
         return 'T';
      else if (inputInt == 10)
         return 'J';
      else if (inputInt == 11)
         return 'Q';
      else if (inputInt == 12)
         return 'K';
      else
         return Character.forDigit((inputInt + 1), 10);
   }
}
