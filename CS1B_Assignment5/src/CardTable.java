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
 * The second part creates a separate CardTable class that extends JFrame.
 * This class will control the positioning of the panels and cards of the GUI.
 * We also create a new GUICard class that manages the reading and building of
 * the card image Icons. As a result, some of the machinery and statics that
 * we debugged in the first phase of the main, Foothill class, will be moved
 * into one or the other of these two new classes.
 */

// import necessary class
import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class CardTable extends JFrame
{
   // static variables
   static int MAX_CARDS_PER_HAND = 57;
   static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
   
   // constants ad defaults
   static final int DEF_NUM_CARDS_PER_HAND = 7;
   static final int DEF_NUM_PLAYERS = 1;
   
   static final int MIN_NUM_CARDS = 0;
   static final int MAX_NUM_CARDS = 56;
   static final int MIN_NUM_PLAYERS = 1;
   static final int MAX_NUM_PLAYERS = 5;
   
   // private data
   private int numCardsPerHand;
   private int numPlayers;
   
   // public data
   public JPanel humanTablePanel;
   public JPanel playTablePanel;
   public JPanel compTablePanel;
   public JPanel[] additionalPlayer = new JPanel[MAX_NUM_PLAYERS - 2];
   
   /**
    * The constructor filters input, adds any panels to the JFrame, and
    * establishes layouts according to the general description below.
    * 
    * three JPanels, one for each hand (player-bottom and computer-top)
    * and a middle "playing" JPanel. The client (below) will generate the
    * human's cards at random and will be visible in the bottom JPanel, while
    * the computer's cards will be chosen (again, by the client) to be all
    * back-of-card images in the top JPanel. The middle JPanel will display
    * cards that are "played" by the computer and human during the conflict.
    * Let's assume that each player plays one card per round, so for a
    * 2-person game (computer + human) there will be exactly two cards played
    * in the central region per round of battle. My client chose a joker for
    * the two central cards, just so we would have something to see in the
    * playing region. No game is being played in this assignment, so which
    * cards are displayed is immaterial.
    * 
    * @param title
    * @param numCardsPerHand
    * @param numPlayers
    */
   CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      super(title);
      
      // filters input
      if (numCardsPerHand < MIN_NUM_CARDS || numCardsPerHand > MAX_NUM_CARDS)
         this.numCardsPerHand = DEF_NUM_CARDS_PER_HAND;
      else
         this.numCardsPerHand = numCardsPerHand;
      
      if (numPlayers < MIN_NUM_PLAYERS || numPlayers > MAX_NUM_PLAYERS)
         this.numPlayers = DEF_NUM_PLAYERS;
      else
         this.numPlayers = numPlayers;
      
      // create panels
      humanTablePanel = new JPanel();
      playTablePanel = new JPanel();
      compTablePanel = new JPanel();
      
      for (int i = 0; i < additionalPlayer.length; i++)
         additionalPlayer[i] = new JPanel();
      
      // create borders
      compTablePanel.setBorder(new TitledBorder("Computer Hand"));
      playTablePanel.setBorder(new TitledBorder("Playing Area"));
      humanTablePanel.setBorder(new TitledBorder("Player's Hand"));
      
      for (int i = 2; i < this.numPlayers; i++)
         additionalPlayer[i].setBorder(new TitledBorder("Player's " + i
               + " Hand"));
      
      compTablePanel.setLayout(new GridLayout(1, this.numCardsPerHand, 10, 10));
      humanTablePanel
            .setLayout(new GridLayout(1, this.numCardsPerHand, 10, 10));
      
      for (int i = 2; i < this.numPlayers; i++)
         additionalPlayer[i].setLayout(new GridLayout(1, this.numCardsPerHand,
               10, 10));
      
      // set up layout
      setLayout(new GridLayout(this.numPlayers + 1, 1));
      
      // add panels to the frame
      add(compTablePanel);
      add(playTablePanel);
      add(humanTablePanel);
      
   }
   
   // Accessors for the two instance members
   /**
    * accessors of numCardsPerHand member data
    * 
    * @return numCardsPerHand
    */
   public int getNumCardsPerHand()
   {
      return this.numCardsPerHand;
   }
   
   /**
    * accessors of numPlayers member data
    * 
    * @return numPlayers
    */
   public int getNumPlayers()
   {
      return this.numPlayers;
   }
   
}
