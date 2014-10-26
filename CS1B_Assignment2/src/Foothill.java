/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 2 in CS 1B Jesse Cecil Winter 2014
 * this program is called Game Basics. this program is about the basic of the
 * card game that demonstrate the Card, Hand, and Deck as a classes.
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

// import necessary classes
import java.util.Random;
import java.util.Scanner;

/**
 * client class of the program
 * 
 * @author MuhammadRizky
 */
public class Foothill
{
   // declare instance variables
   private static Scanner keyboard;
   private static final int MIN_PLAYER = 1;
   private static final int MAX_PLAYER = 10;
   
   // main method of the program
   public static void main(String[] args)
   {
      // declare necessary objects, variables or arrays
      keyboard = new Scanner(System.in);           // for input
      Hand[] playersHands = new Hand[MAX_PLAYER];  // for hand objects
      Deck dealersDeck;                            // for the dealer's deck
      String input;                                // holds input from the user
      int numPlayers = 1;                          // the number of the players
      int counter;                                 // flexible integer variable
      boolean quit = false;                        // for quitting the loop
      
      // instantiate the hand array
      for (int i = 0; i < MAX_PLAYER; i++)
      {
         playersHands[i] = new Hand();
      }
      
      while (!quit)
      {
         // greetings and user prompt
         createLine(80, "=");
         System.out.println("Welcome to Foothill Card Game");
         createLine(80, "=");
         System.out.print("Please enter the number of player(s) or \"Q\" to "
               + "quit: ");
         
         // filter the user's input
         while (!quit)
         {
            try
            // to filter non integer inputs, make use of NumForException
            {
               input = keyboard.nextLine();
               
               // if the user input is q then quit the loop
               input = input.toUpperCase();
               if (input.charAt(0) == 'Q')
               {
                  quit = true;
                  continue;
               }
               
               // assign the user input to numPlayers
               numPlayers = Integer.parseInt(input);
               
               // if the input integer is out of range, prompt again
               if (numPlayers < MIN_PLAYER || numPlayers > MAX_PLAYER)
               {
                  System.out.print("The integer must between 1-10 only: ");
                  continue;
               }
            }
            catch (NumberFormatException error)
            {
               // if the user input is other than integer prompt again
               System.out.print("Please enter integers only and try again: ");
               continue;
            }
            break;
         }
         
         // if quit is true then quit the loop
         if (quit == true)
         {
            continue;
         }
         
         createLine(80, "=");
         
         // instantiate dealer's deck object
         dealersDeck = new Deck();
         
         // set the counter to zero. dealersDeck give cards to the hands
         counter = 0;
         while (dealersDeck.getTopCard() != 0)
         {
            playersHands[counter % numPlayers].takeCard(dealersDeck.dealCard());
            counter++;
         }
         
         // show the cards inside each players
         for (int i = 0; i < numPlayers; i++)
         {
            System.out.println(playersHands[i].toString() + "\n");
         }
         createLine(80, "=");
         
         // initialize dealersDeck and resets players hand
         dealersDeck.init();
         for (int i = 0; i < MAX_PLAYER; i++)
         {
            playersHands[i].resetHand();
         }
         
         // shuffle the dealersDeck
         dealersDeck.shuffle();
         
         // set the counter to zero. dealersDeck give cards to the hands
         counter = 0;
         while (dealersDeck.getTopCard() != 0)
         {
            playersHands[counter % numPlayers].takeCard(dealersDeck.dealCard());
            counter++;
         }
         
         // show the cards inside each players
         for (int i = 0; i < numPlayers; i++)
         {
            System.out.println(playersHands[i].toString() + "\n");
         }
      }
      
      // shut down indicator
      System.out.println("Program Shutted Down");
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

/**
 * card class
 * 
 * @author MuhammadRizky
 */
class Card
{
   // create suits enum data type
   public enum Suit
   {
      Clubs, Diamonds, Hearts, Spades
   };
   
   // create a pre-defined values as an array
   public static final char[] VALUES =
      { 'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2' };
   private static final char DEFAULT_VALUES = 'A';
   
   // declare private member datas
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   // default constructor
   Card()
   {
      this('A', Card.Suit.Spades);
   }
   
   // constructors
   Card(char value)
   {
      this(value, Card.Suit.Spades);
   }
   
   Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   Card(Card card)
   {
      this(card.value, card.suit);
   }
   
   // mutator methods
   /**
    * this is the most important method of this class, that sets the values and
    * the suits. this method is helped by isValid() method.
    * 
    * @param value the passed Card's value
    * @param suit the passed Card's suit
    * @return true if the input is valid. false, otherwise.
    */
   public boolean set(char value, Suit suit)
   {
      char upVal = Character.toUpperCase(value); // uppercased value
      
      // since we use enum just assign the suit value
      this.suit = suit;
      
      // check for validity
      if (isValid(upVal))
      {
         this.errorFlag = false;
         this.value = upVal;
         return true;
      }
      else
      {
         this.errorFlag = true;
         this.value = DEFAULT_VALUES;
         return false;
      }
   }
   
   // accessor methods
   /**
    * member data value's accessor.
    * 
    * @return the object's value member field
    */
   public char getValue()
   {
      return this.value;
   }
   
   /**
    * member data suit's accessor.
    * 
    * @return the object's suit member field
    */
   public Suit getSuit()
   {
      return this.suit;
   }
   
   /**
    * member data errorFlag's accessor.
    * 
    * @return the object's errorFlag member field, true if the the values passed
    *         to the setter were valid. false, otherwise.
    */
   public boolean getErrorFlag()
   {
      return this.errorFlag;
   }
   
   // other methods
   /**
    * this method that compare two card object.
    * 
    * @param card as a Card class object
    * @return true, if both cards got same values for ALL of their member data
    */
   public boolean equals(Card card)
   {
      if (this.errorFlag == card.errorFlag)
      {
         if (this.suit == card.suit)
         {
            if (this.value == card.value)
            {
               return true;
            }
         }
      }
      
      return false;
   }
   
   /**
    * this method checks the validity of a passed parameter values. this method
    * check if the value passed is exist in the pre-defined static final vales[]
    * array then it will return true. false, otherwise. this is the heper method
    * of set() method.
    * 
    * @param value passed as parameter
    * @return return true if the value is valid. false, otherwise.
    */
   private boolean isValid(char value)
   {
      for (char valElement : VALUES)
      {
         if (value == valElement)
         {
            return true;
         }
      }
      
      return false;
   }
   
   // stringize method
   /**
    * output method of this class, return all member data as a String.
    */
   public String toString()
   {
      if (errorFlag == true)
      {
         return "[invalid]";
      }
      
      return value + " of " + suit;
   }
   
}

/**
 * the hand class
 * 
 * @author MuhammadRizky
 */
class Hand
{
   // set the maximum of cards in hand to 50, just in case of errors
   public static final int MAX_CARD = 50;
   public static final char ERROR_CHAR = 'E';
   
   // declare private member datas
   private Card[] MyCards;
   private int numCards;
   
   // default constructors
   Hand()
   {
      this.MyCards = new Card[MAX_CARD];
      resetHand();
   }
   
   // mutator methods
   /**
    * method that adds card to the hand object.
    * 
    * @param card that is passed as parameter to be added to the card array
    * @return true if its a success, false otherwise.
    */
   public boolean takeCard(Card card)
   {
      Card newCard = new Card(card.getValue(), card.getSuit());
      
      try
      // if the client add cards when maximum is reached, return false
      {
         this.MyCards[this.numCards] = newCard;
         this.numCards++;
         return true;
      }
      catch (ArrayIndexOutOfBoundsException error)
      {
         return false;
      }
   }
   
   /**
    * method that plays the card on the hand, which means removes the most
    * recent card to from the hand.
    * 
    * @return the card which is removed
    */
   public Card playCard()
   {
      Card returnCard;
      
      try
      // if the array is empty or error, catch the exception
      {
         returnCard = this.MyCards[this.numCards - 1];
      }
      catch (ArrayIndexOutOfBoundsException error)
      {
         return new Card(ERROR_CHAR); // to give the card an errorFlag
      }
      
      this.numCards--;
      return returnCard;
   }
   
   // accessor methods
   /**
    * return the value of numCards member field.
    * 
    * @return numCards member field
    */
   public int getNumCards()
   {
      return this.numCards;
   }
   
   /**
    * inspect a card in the card array according to the number passed in.
    * 
    * @param inputNum as the # cards that want to e checked
    * @return return the card if success, return an error card if not
    */
   public Card inspectCard(int inputNum)
   {
      try
      // if the array is empty or error, catch the exception
      {
         return MyCards[inputNum - 1];
      }
      catch (ArrayIndexOutOfBoundsException error)
      {
         return new Card(ERROR_CHAR); // to give the card an errorFlag
      }
   }
   
   // other methods
   /**
    * reset the cards array and numCard member field, and also delete all
    * element in myCards array
    */
   public void resetHand()
   {
      this.numCards = 0;
   }
   
   // stringize method
   /**
    * return the string version of this hand object
    */
   public String toString()
   {
      String outputString = "Hand [" + numCards + "] = {";
      
      for (int counter = 0; counter < numCards; counter++)
      {
         try
         // if the array is empty, return nothing
         {
            outputString += MyCards[counter].toString();
            
            if (counter != MyCards.length - 1)
            {
               outputString += ", ";
            }
         }
         catch (NullPointerException error)
         {
            
         }
      }
      
      outputString += "}";
      
      return outputString;
   }
}

/**
 * the Deck class
 * 
 * @author MuhammadRizky
 */
class Deck
{
   // constants
   public static final int DECK_SIZE = 52;
   public static final int MAX_CARD = 6 * DECK_SIZE;
   public static final char ERROR_CHAR = 'E';
   
   // the master deck
   private static Card[] masterPack = new Card[DECK_SIZE];
   
   // declare private member of the class
   private Card[] cards = new Card[MAX_CARD];
   private int topCard;
   private int numPacks;
   
   // default constructor
   Deck()
   {
      this(1);
   }
   
   // constructors
   Deck(int numPacks)
   {
      allocateMasterPack();
      init(numPacks);
   }
   
   // public methods
   /**
    * this method initialize the cards in the deck object. and copy the object
    * from the masterpack class to the deck object.
    * 
    * @param numPacks to determine how much cards in that deck object.
    * 
    */
   public void init(int numPacks)
   {
      this.numPacks = numPacks;
      this.topCard = 0;
      
      for (int packs = 0; packs < this.numPacks; packs++)
      {
         for (Card card : masterPack)
         {
            this.cards[topCard] = new Card(card);
            this.topCard++;
         }
      }
   }
   
   /**
    * just another overloaded version of init method
    */
   public void init()
   {
      init(this.numPacks);
   }
   
   /**
    * this method shuffle the cards inside the deck array, with the use of
    * java Random class. this method swap cards a couple of times randomly.
    * this method implements the Fisher-Yates Algorithm.
    */
   public void shuffle()
   {
      int randomIndex;
      Card temporaryCard;
      Random randomGenerator = new Random();
      
      for (int counter = 0; counter < topCard; counter++)
      {
         // randomize index between 0 and (DECK_SIZE * numPacks) - 1
         randomIndex = randomGenerator.nextInt(DECK_SIZE * numPacks);
         
         // swap the cards[counter] & cards[randomIndex]
         temporaryCard = cards[counter];
         cards[counter] = cards[randomIndex];
         cards[randomIndex] = temporaryCard;
      }
   }
   
   /**
    * this method return the top card of the deck.
    * 
    * @return a Card at that index if successful, return an error card otherwise
    */
   public Card dealCard()
   {
      Card returnCard;
      
      try
      // if the array is empty or error, catch the exception
      {
         returnCard = this.cards[this.topCard - 1];
      }
      catch (ArrayIndexOutOfBoundsException error)
      {
         return new Card(ERROR_CHAR);        // to give the card an errorFlag
      }
      
      this.topCard--;
      
      return returnCard;
   }
   
   /**
    * this method inspect individual card on the cards array inside the deck
    * object.
    * 
    * @param inputNum as the intended check index
    * @return a Card at that index if successful, return an error card otherwise
    */
   public Card inspectCard(int inputNum)
   {
      try
      // if the array is empty or error, catch the exception
      {
         return cards[inputNum - 1];
      }
      catch (ArrayIndexOutOfBoundsException error)
      {
         return new Card(ERROR_CHAR);        // to give the card an errorFlag
      }
   }
   
   // accessor
   /**
    * the accessor of topCard member field.
    * 
    * @return topCard
    */
   public int getTopCard()
   {
      return this.topCard;
   }
   
   /**
    * initiate masterPack array if the masterPack array is still empty. this
    * method is intentionally work only once at the first initiation of the
    * first deck object.
    */
   // private methods
   private static void allocateMasterPack()
   {
      // if the masterPack array is still empty, then initiate it
      if (masterPack[0] == null)
      {
         int index = 0;
         // assign the cards
         for (Card.Suit suits : Card.Suit.values())
         {
            for (char values : Card.VALUES)
            {
               masterPack[index] = new Card(values, suits);
               index++;
            }
         }
      }
   }
}

/*---------------------------------Sample Run-----------------------------------

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: A
Please enter integers only and try again: s
Please enter integers only and try again: z
Please enter integers only and try again: B
Please enter integers only and try again: c
Please enter integers only and try again: -1
The integer must between 1-10 only: 0
The integer must between 1-10 only: 1
================================================================================
Hand [50] = {2 of Spades, 3 of Spades, 4 of Spades, 5 of Spades, 6 of Spades, 7 
of Spades, 8 of Spades, 9 of Spades, T of Spades, J of Spades, Q of Spades, K of
 Spades, A of Spades, 2 of Hearts, 3 of Hearts, 4 of Hearts, 5 of Hearts, 6 of H
earts, 7 of Hearts, 8 of Hearts, 9 of Hearts, T of Hearts, J of Hearts, Q of Hea
rts, K of Hearts, A of Hearts, 2 of Diamonds, 3 of Diamonds, 4 of Diamonds, 5 of
 Diamonds, 6 of Diamonds, 7 of Diamonds, 8 of Diamonds, 9 of Diamonds, T of Diam
onds, J of Diamonds, Q of Diamonds, K of Diamonds, A of Diamonds, 2 of Clubs, 3 
of Clubs, 4 of Clubs, 5 of Clubs, 6 of Clubs, 7 of Clubs, 8 of Clubs, 9 of Clubs
, T of Clubs, J of Clubs, Q of Clubs}

================================================================================
Hand [50] = {4 of Diamonds, Q of Clubs, T of Diamonds, J of Hearts, J of Spades,
 9 of Spades, K of Diamonds, A of Diamonds, 6 of Diamonds, 9 of Hearts, 5 of Spa
des, 8 of Diamonds, A of Hearts, 6 of Hearts, Q of Spades, J of Clubs, 4 of Hear
ts, 7 of Diamonds, K of Hearts, 3 of Spades, K of Clubs, 7 of Spades, 3 of Heart
s, 9 of Diamonds, Q of Diamonds, 9 of Clubs, 5 of Clubs, 4 of Clubs, J of Diamon
ds, A of Clubs, Q of Hearts, T of Hearts, 6 of Spades, 2 of Clubs, 8 of Hearts, 
8 of Spades, 8 of Clubs, 4 of Spades, T of Clubs, 5 of Diamonds, 2 of Spades, 6 
of Clubs, 7 of Hearts, 7 of Clubs, 3 of Diamonds, 2 of Diamonds, T of Spades, K 
of Spades, A of Spades, 5 of Hearts}

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: 2
================================================================================
Hand [50] = {4 of Diamonds, Q of Clubs, T of Diamonds, J of Hearts, J of Spades,
 9 of Spades, K of Diamonds, A of Diamonds, 6 of Diamonds, 9 of Hearts, 5 of Spa
des, 8 of Diamonds, A of Hearts, 6 of Hearts, Q of Spades, J of Clubs, 4 of Hear
ts, 7 of Diamonds, K of Hearts, 3 of Spades, K of Clubs, 7 of Spades, 3 of Heart
s, 9 of Diamonds, Q of Diamonds, 9 of Clubs, 5 of Clubs, 4 of Clubs, J of Diamon
ds, A of Clubs, Q of Hearts, T of Hearts, 6 of Spades, 2 of Clubs, 8 of Hearts, 
8 of Spades, 8 of Clubs, 4 of Spades, T of Clubs, 5 of Diamonds, 2 of Spades, 6 
of Clubs, 7 of Hearts, 7 of Clubs, 3 of Diamonds, 2 of Diamonds, T of Spades, K 
of Spades, A of Spades, 5 of Hearts}

Hand [26] = {3 of Spades, 5 of Spades, 7 of Spades, 9 of Spades, J of Spades, K 
of Spades, 2 of Hearts, 4 of Hearts, 6 of Hearts, 8 of Hearts, T of Hearts, Q of
 Hearts, A of Hearts, 3 of Diamonds, 5 of Diamonds, 7 of Diamonds, 9 of Diamonds
, J of Diamonds, K of Diamonds, 2 of Clubs, 4 of Clubs, 6 of Clubs, 8 of Clubs, 
T of Clubs, Q of Clubs, A of Clubs, }

================================================================================
Hand [26] = {3 of Hearts, 5 of Hearts, 6 of Clubs, A of Clubs, Q of Hearts, T of
 Spades, Q of Spades, 5 of Diamonds, 2 of Spades, K of Clubs, T of Diamonds, 2 o
f Clubs, 5 of Spades, A of Spades, 4 of Hearts, J of Diamonds, 3 of Clubs, 3 of 
Spades, 8 of Hearts, 8 of Clubs, 2 of Hearts, 9 of Diamonds, 5 of Clubs, 9 of Sp
ades, A of Diamonds, 7 of Clubs, }

Hand [26] = {Q of Clubs, 7 of Spades, 6 of Spades, 6 of Hearts, 7 of Diamonds, J
 of Spades, 8 of Diamonds, 8 of Spades, J of Hearts, T of Hearts, K of Diamonds,
 4 of Spades, 7 of Hearts, K of Hearts, 9 of Clubs, Q of Diamonds, A of Hearts, 
4 of Clubs, K of Spades, 2 of Diamonds, 4 of Diamonds, 3 of Diamonds, 9 of Heart
s, 6 of Diamonds, T of Clubs, J of Clubs, }

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: 3
================================================================================
Hand [44] = {3 of Hearts, 5 of Hearts, 6 of Clubs, A of Clubs, Q of Hearts, T of
 Spades, Q of Spades, 5 of Diamonds, 2 of Spades, K of Clubs, T of Diamonds, 2 o
f Clubs, 5 of Spades, A of Spades, 4 of Hearts, J of Diamonds, 3 of Clubs, 3 of 
Spades, 8 of Hearts, 8 of Clubs, 2 of Hearts, 9 of Diamonds, 5 of Clubs, 9 of Sp
ades, A of Diamonds, 7 of Clubs, 2 of Spades, 5 of Spades, 8 of Spades, J of Spa
des, A of Spades, 4 of Hearts, 7 of Hearts, T of Hearts, K of Hearts, 3 of Diamo
nds, 6 of Diamonds, 9 of Diamonds, Q of Diamonds, 2 of Clubs, 5 of Clubs, 8 of C
lubs, J of Clubs, A of Clubs, }

Hand [43] = {Q of Clubs, 7 of Spades, 6 of Spades, 6 of Hearts, 7 of Diamonds, J
 of Spades, 8 of Diamonds, 8 of Spades, J of Hearts, T of Hearts, K of Diamonds,
 4 of Spades, 7 of Hearts, K of Hearts, 9 of Clubs, Q of Diamonds, A of Hearts, 
4 of Clubs, K of Spades, 2 of Diamonds, 4 of Diamonds, 3 of Diamonds, 9 of Heart
s, 6 of Diamonds, T of Clubs, J of Clubs, 3 of Spades, 6 of Spades, 9 of Spades,
 Q of Spades, 2 of Hearts, 5 of Hearts, 8 of Hearts, J of Hearts, A of Hearts, 4
 of Diamonds, 7 of Diamonds, T of Diamonds, K of Diamonds, 3 of Clubs, 6 of Club
s, 9 of Clubs, Q of Clubs, }

Hand [17] = {4 of Spades, 7 of Spades, T of Spades, K of Spades, 3 of Hearts, 6 
of Hearts, 9 of Hearts, Q of Hearts, 2 of Diamonds, 5 of Diamonds, 8 of Diamonds
, J of Diamonds, A of Diamonds, 4 of Clubs, 7 of Clubs, T of Clubs, K of Clubs, 
}

================================================================================
Hand [18] = {4 of Diamonds, A of Diamonds, 4 of Hearts, 3 of Spades, K of Spades
, 7 of Clubs, 7 of Spades, K of Clubs, 4 of Clubs, 9 of Spades, 9 of Diamonds, J
 of Hearts, 8 of Diamonds, 7 of Diamonds, T of Diamonds, T of Spades, J of Clubs
, 2 of Hearts, }

Hand [17] = {A of Hearts, 6 of Spades, A of Spades, 3 of Diamonds, 9 of Clubs, Q
 of Clubs, 7 of Hearts, 3 of Clubs, 5 of Clubs, 6 of Clubs, 8 of Spades, T of He
arts, K of Diamonds, Q of Diamonds, 2 of Spades, 4 of Spades, 3 of Hearts, }

Hand [17] = {Q of Hearts, 2 of Diamonds, 8 of Clubs, T of Clubs, 6 of Hearts, 6 
of Diamonds, K of Hearts, 5 of Diamonds, 5 of Spades, Q of Spades, A of Clubs, 5
 of Hearts, 9 of Hearts, 2 of Clubs, 8 of Hearts, J of Spades, J of Diamonds, }

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: 4
================================================================================
Hand [31] = {4 of Diamonds, A of Diamonds, 4 of Hearts, 3 of Spades, K of Spades
, 7 of Clubs, 7 of Spades, K of Clubs, 4 of Clubs, 9 of Spades, 9 of Diamonds, J
 of Hearts, 8 of Diamonds, 7 of Diamonds, T of Diamonds, T of Spades, J of Clubs
, 2 of Hearts, 2 of Spades, 6 of Spades, T of Spades, A of Spades, 5 of Hearts, 
9 of Hearts, K of Hearts, 4 of Diamonds, 8 of Diamonds, Q of Diamonds, 3 of Club
s, 7 of Clubs, J of Clubs, }

Hand [30] = {A of Hearts, 6 of Spades, A of Spades, 3 of Diamonds, 9 of Clubs, Q
 of Clubs, 7 of Hearts, 3 of Clubs, 5 of Clubs, 6 of Clubs, 8 of Spades, T of He
arts, K of Diamonds, Q of Diamonds, 2 of Spades, 4 of Spades, 3 of Hearts, 3 of 
Spades, 7 of Spades, J of Spades, 2 of Hearts, 6 of Hearts, T of Hearts, A of He
arts, 5 of Diamonds, 9 of Diamonds, K of Diamonds, 4 of Clubs, 8 of Clubs, Q of 
Clubs, }

Hand [30] = {Q of Hearts, 2 of Diamonds, 8 of Clubs, T of Clubs, 6 of Hearts, 6 
of Diamonds, K of Hearts, 5 of Diamonds, 5 of Spades, Q of Spades, A of Clubs, 5
 of Hearts, 9 of Hearts, 2 of Clubs, 8 of Hearts, J of Spades, J of Diamonds, 4 
of Spades, 8 of Spades, Q of Spades, 3 of Hearts, 7 of Hearts, J of Hearts, 2 of
 Diamonds, 6 of Diamonds, T of Diamonds, A of Diamonds, 5 of Clubs, 9 of Clubs, 
K of Clubs, }

Hand [13] = {5 of Spades, 9 of Spades, K of Spades, 4 of Hearts, 8 of Hearts, Q 
of Hearts, 3 of Diamonds, 7 of Diamonds, J of Diamonds, 2 of Clubs, 6 of Clubs, 
T of Clubs, A of Clubs, }

================================================================================
Hand [13] = {4 of Clubs, 5 of Spades, J of Hearts, 2 of Hearts, 2 of Clubs, Q of
 Spades, 2 of Diamonds, 8 of Clubs, 6 of Clubs, K of Hearts, 5 of Diamonds, K of
 Clubs, 9 of Clubs, }

Hand [13] = {J of Diamonds, 7 of Diamonds, J of Clubs, A of Spades, 3 of Diamond
s, 6 of Hearts, 7 of Spades, 8 of Diamonds, 4 of Spades, T of Hearts, A of Clubs
, 9 of Spades, 6 of Spades, }

Hand [13] = {A of Hearts, 4 of Diamonds, 3 of Hearts, Q of Clubs, T of Spades, 2
 of Spades, K of Spades, 9 of Diamonds, Q of Hearts, K of Diamonds, T of Diamond
s, 3 of Spades, 8 of Spades, }

Hand [13] = {4 of Hearts, 6 of Diamonds, J of Spades, T of Clubs, 3 of Clubs, 9 
of Hearts, Q of Diamonds, 7 of Clubs, 7 of Hearts, A of Diamonds, 8 of Hearts, 5
 of Hearts, 5 of Clubs, }

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: 5
================================================================================
Hand [24] = {4 of Clubs, 5 of Spades, J of Hearts, 2 of Hearts, 2 of Clubs, Q of
 Spades, 2 of Diamonds, 8 of Clubs, 6 of Clubs, K of Hearts, 5 of Diamonds, K of
 Clubs, 9 of Clubs, 2 of Spades, 7 of Spades, Q of Spades, 4 of Hearts, 9 of Hea
rts, A of Hearts, 6 of Diamonds, J of Diamonds, 3 of Clubs, 8 of Clubs, K of Clu
bs, }

Hand [24] = {J of Diamonds, 7 of Diamonds, J of Clubs, A of Spades, 3 of Diamond
s, 6 of Hearts, 7 of Spades, 8 of Diamonds, 4 of Spades, T of Hearts, A of Clubs
, 9 of Spades, 6 of Spades, 3 of Spades, 8 of Spades, K of Spades, 5 of Hearts, 
T of Hearts, 2 of Diamonds, 7 of Diamonds, Q of Diamonds, 4 of Clubs, 9 of Clubs
, A of Clubs, }

Hand [23] = {A of Hearts, 4 of Diamonds, 3 of Hearts, Q of Clubs, T of Spades, 2
 of Spades, K of Spades, 9 of Diamonds, Q of Hearts, K of Diamonds, T of Diamond
s, 3 of Spades, 8 of Spades, 4 of Spades, 9 of Spades, A of Spades, 6 of Hearts,
 J of Hearts, 3 of Diamonds, 8 of Diamonds, K of Diamonds, 5 of Clubs, T of Club
s, }

Hand [23] = {4 of Hearts, 6 of Diamonds, J of Spades, T of Clubs, 3 of Clubs, 9 
of Hearts, Q of Diamonds, 7 of Clubs, 7 of Hearts, A of Diamonds, 8 of Hearts, 5
 of Hearts, 5 of Clubs, 5 of Spades, T of Spades, 2 of Hearts, 7 of Hearts, Q of
 Hearts, 4 of Diamonds, 9 of Diamonds, A of Diamonds, 6 of Clubs, J of Clubs, }

Hand [10] = {6 of Spades, J of Spades, 3 of Hearts, 8 of Hearts, K of Hearts, 5 
of Diamonds, T of Diamonds, 2 of Clubs, 7 of Clubs, Q of Clubs, }

================================================================================
Hand [11] = {T of Clubs, A of Clubs, K of Diamonds, 5 of Hearts, 4 of Spades, J 
of Diamonds, 6 of Spades, 9 of Hearts, J of Clubs, A of Diamonds, Q of Clubs, }

Hand [11] = {J of Hearts, 3 of Diamonds, 2 of Diamonds, 3 of Hearts, 9 of Diamon
ds, 4 of Clubs, 6 of Hearts, 9 of Clubs, K of Clubs, 3 of Spades, 5 of Diamonds,
 }

Hand [10] = {5 of Clubs, 3 of Clubs, Q of Spades, 7 of Hearts, 8 of Hearts, K of
 Hearts, 6 of Diamonds, 7 of Clubs, A of Hearts, Q of Hearts, }

Hand [10] = {8 of Clubs, 8 of Spades, Q of Diamonds, K of Spades, 4 of Diamonds,
 5 of Spades, T of Diamonds, A of Spades, 2 of Hearts, 4 of Hearts, }

Hand [10] = {2 of Clubs, 7 of Spades, T of Spades, 8 of Diamonds, 7 of Diamonds,
 2 of Spades, T of Hearts, J of Spades, 6 of Clubs, 9 of Spades, }

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: 6
================================================================================
Hand [20] = {T of Clubs, A of Clubs, K of Diamonds, 5 of Hearts, 4 of Spades, J 
of Diamonds, 6 of Spades, 9 of Hearts, J of Clubs, A of Diamonds, Q of Clubs, 2 
of Spades, 8 of Spades, A of Spades, 7 of Hearts, K of Hearts, 6 of Diamonds, Q 
of Diamonds, 5 of Clubs, J of Clubs, }

Hand [20] = {J of Hearts, 3 of Diamonds, 2 of Diamonds, 3 of Hearts, 9 of Diamon
ds, 4 of Clubs, 6 of Hearts, 9 of Clubs, K of Clubs, 3 of Spades, 5 of Diamonds,
 3 of Spades, 9 of Spades, 2 of Hearts, 8 of Hearts, A of Hearts, 7 of Diamonds,
 K of Diamonds, 6 of Clubs, Q of Clubs, }

Hand [19] = {5 of Clubs, 3 of Clubs, Q of Spades, 7 of Hearts, 8 of Hearts, K of
 Hearts, 6 of Diamonds, 7 of Clubs, A of Hearts, Q of Hearts, 4 of Spades, T of 
Spades, 3 of Hearts, 9 of Hearts, 2 of Diamonds, 8 of Diamonds, A of Diamonds, 7
 of Clubs, K of Clubs, }

Hand [19] = {8 of Clubs, 8 of Spades, Q of Diamonds, K of Spades, 4 of Diamonds,
 5 of Spades, T of Diamonds, A of Spades, 2 of Hearts, 4 of Hearts, 5 of Spades,
 J of Spades, 4 of Hearts, T of Hearts, 3 of Diamonds, 9 of Diamonds, 2 of Clubs
, 8 of Clubs, A of Clubs, }

Hand [18] = {2 of Clubs, 7 of Spades, T of Spades, 8 of Diamonds, 7 of Diamonds,
 2 of Spades, T of Hearts, J of Spades, 6 of Clubs, 9 of Spades, 6 of Spades, Q 
of Spades, 5 of Hearts, J of Hearts, 4 of Diamonds, T of Diamonds, 3 of Clubs, 9
 of Clubs, }

Hand [8] = {7 of Spades, K of Spades, 6 of Hearts, Q of Hearts, 5 of Diamonds, J
 of Diamonds, 4 of Clubs, T of Clubs, }

================================================================================
Hand [9] = {J of Diamonds, 7 of Clubs, 5 of Spades, J of Clubs, 7 of Spades, 7 o
f Hearts, 7 of Diamonds, 6 of Diamonds, J of Spades, }

Hand [9] = {T of Clubs, 2 of Diamonds, K of Clubs, 8 of Spades, 8 of Diamonds, 3
 of Diamonds, Q of Hearts, 4 of Diamonds, 6 of Spades, }

Hand [9] = {5 of Diamonds, K of Diamonds, 2 of Spades, 6 of Hearts, 9 of Hearts,
 8 of Clubs, A of Spades, 9 of Spades, 5 of Hearts, }

Hand [9] = {A of Diamonds, T of Spades, K of Spades, 6 of Clubs, 9 of Clubs, 3 o
f Hearts, T of Diamonds, 3 of Spades, 2 of Hearts, }

Hand [8] = {4 of Hearts, 4 of Spades, Q of Clubs, 8 of Hearts, J of Hearts, 9 of
 Diamonds, Q of Diamonds, Q of Spades, }

Hand [8] = {2 of Clubs, A of Hearts, 4 of Clubs, A of Clubs, K of Hearts, 5 of C
lubs, 3 of Clubs, T of Hearts, }

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: 7
================================================================================
Hand [17] = {J of Diamonds, 7 of Clubs, 5 of Spades, J of Clubs, 7 of Spades, 7 
of Hearts, 7 of Diamonds, 6 of Diamonds, J of Spades, 2 of Spades, 9 of Spades, 
3 of Hearts, T of Hearts, 4 of Diamonds, J of Diamonds, 5 of Clubs, Q of Clubs, 
}

Hand [17] = {T of Clubs, 2 of Diamonds, K of Clubs, 8 of Spades, 8 of Diamonds, 
3 of Diamonds, Q of Hearts, 4 of Diamonds, 6 of Spades, 3 of Spades, T of Spades
, 4 of Hearts, J of Hearts, 5 of Diamonds, Q of Diamonds, 6 of Clubs, K of Clubs
, }

Hand [17] = {5 of Diamonds, K of Diamonds, 2 of Spades, 6 of Hearts, 9 of Hearts
, 8 of Clubs, A of Spades, 9 of Spades, 5 of Hearts, 4 of Spades, J of Spades, 5
 of Hearts, Q of Hearts, 6 of Diamonds, K of Diamonds, 7 of Clubs, A of Clubs, }

Hand [16] = {A of Diamonds, T of Spades, K of Spades, 6 of Clubs, 9 of Clubs, 3 
of Hearts, T of Diamonds, 3 of Spades, 2 of Hearts, 5 of Spades, Q of Spades, 6 
of Hearts, K of Hearts, 7 of Diamonds, A of Diamonds, 8 of Clubs, }

Hand [15] = {4 of Hearts, 4 of Spades, Q of Clubs, 8 of Hearts, J of Hearts, 9 o
f Diamonds, Q of Diamonds, Q of Spades, 6 of Spades, K of Spades, 7 of Hearts, A
 of Hearts, 8 of Diamonds, 2 of Clubs, 9 of Clubs, }

Hand [15] = {2 of Clubs, A of Hearts, 4 of Clubs, A of Clubs, K of Hearts, 5 of 
Clubs, 3 of Clubs, T of Hearts, 7 of Spades, A of Spades, 8 of Hearts, 2 of Diam
onds, 9 of Diamonds, 3 of Clubs, T of Clubs, }

Hand [7] = {8 of Spades, 2 of Hearts, 9 of Hearts, 3 of Diamonds, T of Diamonds,
 4 of Clubs, J of Clubs, }

================================================================================
Hand [8] = {T of Spades, Q of Spades, A of Spades, 6 of Diamonds, 8 of Clubs, T 
of Diamonds, 5 of Diamonds, 8 of Spades, }

Hand [8] = {A of Clubs, 3 of Clubs, 7 of Spades, 2 of Diamonds, 3 of Diamonds, 5
 of Hearts, J of Clubs, Q of Clubs, }

Hand [8] = {2 of Clubs, 4 of Diamonds, 9 of Hearts, 6 of Hearts, 8 of Hearts, J 
of Hearts, 9 of Spades, 9 of Clubs, }

Hand [7] = {J of Diamonds, 3 of Spades, 4 of Hearts, 7 of Clubs, 3 of Hearts, K 
of Diamonds, 2 of Hearts, }

Hand [7] = {7 of Hearts, Q of Diamonds, T of Hearts, A of Diamonds, Q of Hearts,
 5 of Spades, K of Spades, }

Hand [7] = {J of Spades, K of Hearts, 4 of Clubs, 4 of Spades, 7 of Diamonds, A 
of Hearts, 6 of Clubs, }

Hand [7] = {9 of Diamonds, 6 of Spades, 2 of Spades, K of Clubs, 8 of Diamonds, 
T of Clubs, 5 of Clubs, }

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: 8
================================================================================
Hand [15] = {T of Spades, Q of Spades, A of Spades, 6 of Diamonds, 8 of Clubs, T
 of Diamonds, 5 of Diamonds, 8 of Spades, 2 of Spades, T of Spades, 5 of Hearts,
 K of Hearts, 8 of Diamonds, 3 of Clubs, J of Clubs, }

Hand [15] = {A of Clubs, 3 of Clubs, 7 of Spades, 2 of Diamonds, 3 of Diamonds, 
5 of Hearts, J of Clubs, Q of Clubs, 3 of Spades, J of Spades, 6 of Hearts, A of
 Hearts, 9 of Diamonds, 4 of Clubs, Q of Clubs, }

Hand [15] = {2 of Clubs, 4 of Diamonds, 9 of Hearts, 6 of Hearts, 8 of Hearts, J
 of Hearts, 9 of Spades, 9 of Clubs, 4 of Spades, Q of Spades, 7 of Hearts, 2 of
 Diamonds, T of Diamonds, 5 of Clubs, K of Clubs, }

Hand [14] = {J of Diamonds, 3 of Spades, 4 of Hearts, 7 of Clubs, 3 of Hearts, K
 of Diamonds, 2 of Hearts, 5 of Spades, K of Spades, 8 of Hearts, 3 of Diamonds,
 J of Diamonds, 6 of Clubs, A of Clubs, }

Hand [13] = {7 of Hearts, Q of Diamonds, T of Hearts, A of Diamonds, Q of Hearts
, 5 of Spades, K of Spades, 6 of Spades, A of Spades, 9 of Hearts, 4 of Diamonds
, Q of Diamonds, 7 of Clubs, }

Hand [13] = {J of Spades, K of Hearts, 4 of Clubs, 4 of Spades, 7 of Diamonds, A
 of Hearts, 6 of Clubs, 7 of Spades, 2 of Hearts, T of Hearts, 5 of Diamonds, K 
of Diamonds, 8 of Clubs, }

Hand [13] = {9 of Diamonds, 6 of Spades, 2 of Spades, K of Clubs, 8 of Diamonds,
 T of Clubs, 5 of Clubs, 8 of Spades, 3 of Hearts, J of Hearts, 6 of Diamonds, A
 of Diamonds, 9 of Clubs, }

Hand [6] = {9 of Spades, 4 of Hearts, Q of Hearts, 7 of Diamonds, 2 of Clubs, T 
of Clubs, }

================================================================================
Hand [7] = {T of Spades, 9 of Hearts, 2 of Diamonds, 4 of Clubs, J of Clubs, 3 o
f Spades, 8 of Hearts, }

Hand [7] = {A of Hearts, 4 of Diamonds, 7 of Diamonds, 6 of Hearts, 7 of Clubs, 
K of Diamonds, 5 of Clubs, }

Hand [7] = {2 of Clubs, 9 of Spades, 8 of Clubs, Q of Hearts, J of Spades, A of 
Diamonds, K of Hearts, }

Hand [7] = {T of Hearts, 5 of Spades, 5 of Diamonds, 7 of Hearts, Q of Spades, Q
 of Diamonds, J of Hearts, }

Hand [6] = {2 of Spades, 6 of Spades, 8 of Diamonds, 3 of Diamonds, 9 of Diamond
s, 6 of Clubs, }

Hand [6] = {T of Diamonds, A of Spades, K of Clubs, Q of Clubs, J of Diamonds, 4
 of Spades, }

Hand [6] = {A of Clubs, 5 of Hearts, 6 of Diamonds, 4 of Hearts, K of Spades, 8 
of Spades, }

Hand [6] = {3 of Clubs, 2 of Hearts, 7 of Spades, 3 of Hearts, T of Clubs, 9 of 
Clubs, }

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: 9
================================================================================
Hand [13] = {T of Spades, 9 of Hearts, 2 of Diamonds, 4 of Clubs, J of Clubs, 3 
of Spades, 8 of Hearts, 2 of Spades, J of Spades, 7 of Hearts, 3 of Diamonds, Q 
of Diamonds, 8 of Clubs, }

Hand [13] = {A of Hearts, 4 of Diamonds, 7 of Diamonds, 6 of Hearts, 7 of Clubs,
 K of Diamonds, 5 of Clubs, 3 of Spades, Q of Spades, 8 of Hearts, 4 of Diamonds
, K of Diamonds, 9 of Clubs, }

Hand [13] = {2 of Clubs, 9 of Spades, 8 of Clubs, Q of Hearts, J of Spades, A of
 Diamonds, K of Hearts, 4 of Spades, K of Spades, 9 of Hearts, 5 of Diamonds, A 
of Diamonds, T of Clubs, }

Hand [13] = {T of Hearts, 5 of Spades, 5 of Diamonds, 7 of Hearts, Q of Spades, 
Q of Diamonds, J of Hearts, 5 of Spades, A of Spades, T of Hearts, 6 of Diamonds
, 2 of Clubs, J of Clubs, }

Hand [12] = {2 of Spades, 6 of Spades, 8 of Diamonds, 3 of Diamonds, 9 of Diamon
ds, 6 of Clubs, 6 of Spades, 2 of Hearts, J of Hearts, 7 of Diamonds, 3 of Clubs
, Q of Clubs, }

Hand [12] = {T of Diamonds, A of Spades, K of Clubs, Q of Clubs, J of Diamonds, 
4 of Spades, 7 of Spades, 3 of Hearts, Q of Hearts, 8 of Diamonds, 4 of Clubs, K
 of Clubs, }

Hand [12] = {A of Clubs, 5 of Hearts, 6 of Diamonds, 4 of Hearts, K of Spades, 8
 of Spades, 8 of Spades, 4 of Hearts, K of Hearts, 9 of Diamonds, 5 of Clubs, A 
of Clubs, }

Hand [11] = {3 of Clubs, 2 of Hearts, 7 of Spades, 3 of Hearts, T of Clubs, 9 of
 Clubs, 9 of Spades, 5 of Hearts, A of Hearts, T of Diamonds, 6 of Clubs, }

Hand [5] = {T of Spades, 6 of Hearts, 2 of Diamonds, J of Diamonds, 7 of Clubs, 
}

================================================================================
Hand [6] = {K of Hearts, 7 of Diamonds, 2 of Diamonds, Q of Hearts, 3 of Spades,
 A of Hearts, }

Hand [6] = {6 of Spades, 9 of Spades, A of Diamonds, 6 of Hearts, K of Spades, 2
 of Clubs, }

Hand [6] = {9 of Diamonds, T of Clubs, 4 of Diamonds, 4 of Clubs, 8 of Spades, 7
 of Hearts, }

Hand [6] = {J of Spades, T of Diamonds, 9 of Hearts, J of Diamonds, 9 of Clubs, 
4 of Spades, }

Hand [6] = {8 of Hearts, 2 of Hearts, 5 of Clubs, 7 of Spades, 3 of Hearts, Q of
 Spades, }

Hand [6] = {5 of Hearts, 8 of Clubs, K of Diamonds, A of Spades, T of Spades, 3 
of Diamonds, }

Hand [6] = {2 of Spades, 5 of Spades, 8 of Diamonds, Q of Clubs, A of Clubs, 7 o
f Clubs, }

Hand [5] = {J of Hearts, 6 of Clubs, K of Clubs, T of Hearts, J of Clubs, }

Hand [5] = {3 of Clubs, Q of Diamonds, 5 of Diamonds, 4 of Hearts, 6 of Diamonds
, }

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: 10
================================================================================
Hand [12] = {K of Hearts, 7 of Diamonds, 2 of Diamonds, Q of Hearts, 3 of Spades
, A of Hearts, 2 of Spades, Q of Spades, 9 of Hearts, 6 of Diamonds, 3 of Clubs,
 K of Clubs, }

Hand [12] = {6 of Spades, 9 of Spades, A of Diamonds, 6 of Hearts, K of Spades, 
2 of Clubs, 3 of Spades, K of Spades, T of Hearts, 7 of Diamonds, 4 of Clubs, A 
of Clubs, }

Hand [11] = {9 of Diamonds, T of Clubs, 4 of Diamonds, 4 of Clubs, 8 of Spades, 
7 of Hearts, 4 of Spades, A of Spades, J of Hearts, 8 of Diamonds, 5 of Clubs, }

Hand [11] = {J of Spades, T of Diamonds, 9 of Hearts, J of Diamonds, 9 of Clubs,
 4 of Spades, 5 of Spades, 2 of Hearts, Q of Hearts, 9 of Diamonds, 6 of Clubs, 
}

Hand [11] = {8 of Hearts, 2 of Hearts, 5 of Clubs, 7 of Spades, 3 of Hearts, Q o
f Spades, 6 of Spades, 3 of Hearts, K of Hearts, T of Diamonds, 7 of Clubs, }

Hand [11] = {5 of Hearts, 8 of Clubs, K of Diamonds, A of Spades, T of Spades, 3
 of Diamonds, 7 of Spades, 4 of Hearts, A of Hearts, J of Diamonds, 8 of Clubs, 
}

Hand [11] = {2 of Spades, 5 of Spades, 8 of Diamonds, Q of Clubs, A of Clubs, 7 
of Clubs, 8 of Spades, 5 of Hearts, 2 of Diamonds, Q of Diamonds, 9 of Clubs, }

Hand [10] = {J of Hearts, 6 of Clubs, K of Clubs, T of Hearts, J of Clubs, 9 of 
Spades, 6 of Hearts, 3 of Diamonds, K of Diamonds, T of Clubs, }

Hand [10] = {3 of Clubs, Q of Diamonds, 5 of Diamonds, 4 of Hearts, 6 of Diamond
s, T of Spades, 7 of Hearts, 4 of Diamonds, A of Diamonds, J of Clubs, }

Hand [5] = {J of Spades, 8 of Hearts, 5 of Diamonds, 2 of Clubs, Q of Clubs, }

================================================================================
Hand [6] = {5 of Hearts, J of Spades, Q of Diamonds, 2 of Hearts, 2 of Diamonds,
 5 of Clubs, }

Hand [6] = {6 of Spades, A of Diamonds, 3 of Diamonds, 7 of Spades, 4 of Clubs, 
5 of Diamonds, }

Hand [5] = {8 of Spades, K of Hearts, 3 of Hearts, 7 of Diamonds, 4 of Spades, }

Hand [5] = {T of Spades, Q of Spades, 5 of Spades, 9 of Hearts, 3 of Clubs, }

Hand [5] = {K of Clubs, K of Diamonds, 8 of Hearts, 2 of Spades, 9 of Clubs, }

Hand [5] = {3 of Spades, A of Spades, 7 of Hearts, 6 of Clubs, K of Spades, }

Hand [5] = {9 of Diamonds, T of Clubs, 2 of Clubs, 6 of Diamonds, A of Clubs, }

Hand [5] = {A of Hearts, J of Clubs, 8 of Clubs, 9 of Spades, 7 of Clubs, }

Hand [5] = {Q of Clubs, 4 of Hearts, T of Hearts, Q of Hearts, T of Diamonds, }

Hand [5] = {8 of Diamonds, 6 of Hearts, 4 of Diamonds, J of Hearts, J of Diamond
s, }

================================================================================
Welcome to Foothill Card Game
================================================================================
Please enter the number of player(s) or "Q" to quit: 11
The integer must between 1-10 only: e
Please enter integers only and try again: asd
Please enter integers only and try again: Quit
Program Shutted Down
================================================================================

------------------------------------------------------------------------------*/