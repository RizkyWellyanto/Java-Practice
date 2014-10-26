package Phase2;

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
import javax.swing.border.TitledBorder;

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
      
      // establish main frame in which program will run
      CardTable myCardTable = new CardTable("CS 1B CardTable",
            NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // CREATE LABELS ----------------------------------------------------
      for (k = 0; k < computerLabels.length; k++)
         computerLabels[k] = new JLabel(GUICard.getBackCardIcon());
      
      for (k = 0; k < computerLabels.length; k++)
      {
         tempIcon = GUICard.getIcon(generateRandomCard());
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
   
   /**
    * basically just converting integer to its respective suit enum
    * 
    * @param inputInt
    * @return suit enum
    */
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
   
   /**
    * basically just converting integer to its respective value
    * 
    * @param inputInt
    * @return char value
    */
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

class CardTable extends JFrame
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

class GUICard
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

// Card class
// --------------------------------------------------------------------
class Card
{
   // type and constants
   public enum Suit
   {
      clubs, diamonds, hearts, spades
   }
   
   // private data
   private char value;
   private Suit suit;
   boolean errorFlag;
   
   // 4 overloaded constructors
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   public Card(char value)
   {
      this(value, Suit.spades);
   }
   
   public Card()
   {
      this('A', Suit.spades);
   }
   
   // copy constructor
   public Card(Card card)
   {
      this(card.value, card.suit);
   }
   
   // mutators
   public boolean set(char value, Suit suit)
   {
      char upVal;            // for upcasing char
      
      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);
      
      if (!isValid(upVal, suit))
      {
         errorFlag = true;
         return false;
      }
      
      // else implied
      errorFlag = false;
      this.value = upVal;
      this.suit = suit;
      return true;
   }
   
   // accessors
   public char getVal()
   {
      return value;
   }
   
   public Suit getSuit()
   {
      return suit;
   }
   
   public boolean getErrorFlag()
   {
      return errorFlag;
   }
   
   public boolean equals(Card card)
   {
      if (this.value != card.value)
         return false;
      if (this.suit != card.suit)
         return false;
      if (this.errorFlag != card.errorFlag)
         return false;
      return true;
   }
   
   // stringizer
   public String toString()
   {
      String retVal;
      
      if (errorFlag)
         return "** illegal **";
      
      // else implied
      
      retVal = String.valueOf(value);
      retVal += " of ";
      retVal += String.valueOf(suit);
      
      return retVal;
   }
   
   // helper
   private boolean isValid(char value, Suit suit)
   {
      char upVal;
      
      // convert to uppercase to simplify (need #include <cctype>)
      upVal = Character.toUpperCase(value);
      
      // check for validity
      if (upVal == 'A' || upVal == 'K' || upVal == 'Q' || upVal == 'J'
            || upVal == 'T' || (upVal >= '2' && upVal <= '9'))
         return true;
      else
         return false;
   }
}

// class Hand ----------------------------------------------------------------
class Hand
{
   public static final int MAX_CARDS_PER_HAND = 100;  // should cover any game
   
   private Card[] myCards;
   private int numCards;
   
   // constructor
   public Hand()
   {
      // careful - we are only allocating the references
      myCards = new Card[MAX_CARDS_PER_HAND];
      resetHand();
   }
   
   // mutators
   public void resetHand()
   {
      numCards = 0;
   }
   
   public boolean takeCard(Card card)
   {
      if (numCards >= MAX_CARDS_PER_HAND)
         return false;
      
      // be frugal - only allocate when needed
      if (myCards[numCards] == null)
         myCards[numCards] = new Card();
      
      // don't just assign: mutator assures active/undeleted
      myCards[numCards++].set(card.getVal(), card.getSuit());
      return true;
   }
   
   public Card playCard()
   {
      // always play highest card in array. client will prepare this position.
      // in rare case that client tries to play from a spent hand, return error
      
      Card errorReturn = new Card('E', Card.Suit.spades); // in rare cases
      
      if (numCards == 0)
         return errorReturn;
      else
         return myCards[--numCards];
   }
   
   // accessors
   public String toString()
   {
      int k;
      String retVal = "Hand =  ( ";
      
      for (k = 0; k < numCards; k++)
      {
         retVal += myCards[k].toString();
         if (k < numCards - 1)
            retVal += ", ";
      }
      retVal += " )";
      return retVal;
   }
   
   int getNumCards()
   {
      return numCards;
   }
   
   Card inspectCard(int k)
   {
      // return copy of card at position k.
      // if client tries to access out-of-bounds card, return error
      
      Card errorReturn = new Card('E', Card.Suit.spades); // in rare cases
      
      if (k < 0 || k >= numCards)
         return errorReturn;
      else
         return myCards[k];
   }
}
