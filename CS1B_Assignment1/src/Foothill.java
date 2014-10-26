/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 1 in CS 1B Jesse Cecil Winter 2014
 * this program is called Game Basics. this program is about the basic of the
 * card game that demonstrate the Card as a class.
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

/**
 * client class of the program
 * 
 * @author MuhammadRizky
 */
public class Foothill
{
   // main method of the program
   public static void main(String[] args)
   {
      Card card1, card2, card3, card4, card5;
      Hand myHand;
      
      card1 = new Card();
      card2 = new Card('4', Card.Suit.hearts);
      card3 = new Card('Z');
      card4 = new Card('9', Card.Suit.diamonds);
      card5 = new Card('J');
      
      createLine(80, "=");
      System.out.println("Showing Individual Cards");
      createLine(80, "-");
      System.out.println(card1.toString());
      System.out.println(card2.toString());
      System.out.println(card3.toString());
      System.out.println(card4.toString());
      System.out.println(card5.toString());
      
      createLine(80, "=");
      System.out.println("Show the cards in the hand");
      createLine(80, "-");
      myHand = new Hand();
      System.out.println(myHand.toString());
      
      createLine(80, "=");
      System.out.println("Add cards to the hand");
      for (int count = 0; count < 15; count++)
      {
         myHand.takeCard(card1);
         myHand.takeCard(card2);
         myHand.takeCard(card3);
         myHand.takeCard(card4);
         myHand.takeCard(card5);
      }
      
      createLine(80, "=");
      System.out.println("Show Cards in the hand");
      createLine(80, "-");
      System.out.println(myHand.toString());
      
      createLine(80, "=");
      System.out.println("Inspect 60 cards");
      createLine(80, "-");
      for (int count = 1; count <= 60; count++)
      {
         System.out.println(myHand.inspectCard(count).toString());
      }
      
      createLine(80, "=");
      System.out.println("Playing the cards");
      createLine(80, "-");
      for (int count = 0; count < 60; count++)
      {
         System.out.println("Playing " + myHand.playCard().toString());
      }
      
      createLine(80, "=");
      System.out.println("Show the cards in the hand");
      createLine(80, "-");
      System.out.println(myHand.toString());
      
      createLine(80, "=");
      System.out.println("Add cards to the hand");
      for (int count = 0; count < 10; count++)
      {
         myHand.takeCard(card5);
         myHand.takeCard(card4);
         myHand.takeCard(card3);
         myHand.takeCard(card2);
         myHand.takeCard(card1);
      }
      
      createLine(80, "=");
      System.out.println("Show number of cards in the hand");
      createLine(80, "-");
      System.out.println(myHand.getNumCards());
      
      createLine(80, "=");
      System.out.println("Show the cards in the hand");
      createLine(80, "-");
      System.out.println(myHand.toString());
      
      createLine(80, "=");
      System.out.println("Remove cards from the hand");
      myHand.resetHand();
      
      createLine(80, "=");
      System.out.println("Show the cards in the hand");
      createLine(80, "-");
      System.out.println(myHand.toString());
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
      clubs, diamonds, hearts, spades
   };
   
   // create a pre-defined values as an array
   private static final char[] VALUES =
      { 'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2' };
   private static final char DEFAULT_VALUES = 'A';
   
   // declare private member datas
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   // default constructor
   Card()
   {
      this('A', Card.Suit.spades);
   }
   
   // constructors
   Card(char value)
   {
      this(value, Card.Suit.spades);
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

/*-------------------------------Sample Run-------------------------------------

================================================================================
Showing Individual Cards
--------------------------------------------------------------------------------
A of spades
4 of hearts
[invalid]
9 of diamonds
J of spades
================================================================================
Show the cards in the hand
--------------------------------------------------------------------------------
Hand [0] = {}
================================================================================
Add cards to the hand
================================================================================
Show Cards in the hand
--------------------------------------------------------------------------------
Hand [50] = {A of spades, 4 of hearts, A of spades, 9 of diamonds, J of spades, 
A of spades, 4 of hearts, A of spades, 9 of diamonds, J of spades, A of spades, 
4 of hearts, A of spades, 9 of diamonds, J of spades, A of spades, 4 of hearts, 
A of spades, 9 of diamonds, J of spades, A of spades, 4 of hearts, A of spades, 
9 of diamonds, J of spades, A of spades, 4 of hearts, A of spades, 9 of diamonds
, J of spades, A of spades, 4 of hearts, A of spades, 9 of diamonds, J of spades
, A of spades, 4 of hearts, A of spades, 9 of diamonds, J of spades, A of spades
, 4 of hearts, A of spades, 9 of diamonds, J of spades, A of spades, 4 of hearts
, A of spades, 9 of diamonds, J of spades}
================================================================================
Inspect 60 cards
--------------------------------------------------------------------------------
A of spades
4 of hearts
A of spades
9 of diamonds
J of spades
A of spades
4 of hearts
A of spades
9 of diamonds
J of spades
A of spades
4 of hearts
A of spades
9 of diamonds
J of spades
A of spades
4 of hearts
A of spades
9 of diamonds
J of spades
A of spades
4 of hearts
A of spades
9 of diamonds
J of spades
A of spades
4 of hearts
A of spades
9 of diamonds
J of spades
A of spades
4 of hearts
A of spades
9 of diamonds
J of spades
A of spades
4 of hearts
A of spades
9 of diamonds
J of spades
A of spades
4 of hearts
A of spades
9 of diamonds
J of spades
A of spades
4 of hearts
A of spades
9 of diamonds
J of spades
[invalid]
[invalid]
[invalid]
[invalid]
[invalid]
[invalid]
[invalid]
[invalid]
[invalid]
[invalid]
================================================================================
Playing the cards
--------------------------------------------------------------------------------
Playing J of spades
Playing 9 of diamonds
Playing A of spades
Playing 4 of hearts
Playing A of spades
Playing J of spades
Playing 9 of diamonds
Playing A of spades
Playing 4 of hearts
Playing A of spades
Playing J of spades
Playing 9 of diamonds
Playing A of spades
Playing 4 of hearts
Playing A of spades
Playing J of spades
Playing 9 of diamonds
Playing A of spades
Playing 4 of hearts
Playing A of spades
Playing J of spades
Playing 9 of diamonds
Playing A of spades
Playing 4 of hearts
Playing A of spades
Playing J of spades
Playing 9 of diamonds
Playing A of spades
Playing 4 of hearts
Playing A of spades
Playing J of spades
Playing 9 of diamonds
Playing A of spades
Playing 4 of hearts
Playing A of spades
Playing J of spades
Playing 9 of diamonds
Playing A of spades
Playing 4 of hearts
Playing A of spades
Playing J of spades
Playing 9 of diamonds
Playing A of spades
Playing 4 of hearts
Playing A of spades
Playing J of spades
Playing 9 of diamonds
Playing A of spades
Playing 4 of hearts
Playing A of spades
Playing [invalid]
Playing [invalid]
Playing [invalid]
Playing [invalid]
Playing [invalid]
Playing [invalid]
Playing [invalid]
Playing [invalid]
Playing [invalid]
Playing [invalid]
================================================================================
Show the cards in the hand
--------------------------------------------------------------------------------
Hand [0] = {}
================================================================================
Add cards to the hand
================================================================================
Show number of cards in the hand
--------------------------------------------------------------------------------
50
================================================================================
Show the cards in the hand
--------------------------------------------------------------------------------
Hand [50] = {J of spades, 9 of diamonds, A of spades, 4 of hearts, A of spades, 
J of spades, 9 of diamonds, A of spades, 4 of hearts, A of spades, J of spades, 
9 of diamonds, A of spades, 4 of hearts, A of spades, J of spades, 9 of diamonds
, A of spades, 4 of hearts, A of spades, J of spades, 9 of diamonds, A of spades
, 4 of hearts, A of spades, J of spades, 9 of diamonds, A of spades, 4 of hearts
, A of spades, J of spades, 9 of diamonds, A of spades, 4 of hearts, A of spades
, J of spades, 9 of diamonds, A of spades, 4 of hearts, A of spades, J of spades
, 9 of diamonds, A of spades, 4 of hearts, A of spades, J of spades, 9 of diamon
ds, A of spades, 4 of hearts, A of spades}
================================================================================
Remove cards from the hand
================================================================================
Show the cards in the hand
--------------------------------------------------------------------------------
Hand [0] = {}
================================================================================

------------------------------------------------------------------------------*/
