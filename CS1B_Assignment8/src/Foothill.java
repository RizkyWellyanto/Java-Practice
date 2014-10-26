/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 8 in CS 1B Jesse Cecil Winter 2014
 * this program is called Managing a Sorted java.util List and java.util Stack.
 * this program use the previous concepts with the addition of abstract data
 * structures.
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

// import necessary classes
import java.util.*; // for the stack data structure

/**
 * Main Class of the Program
 * 
 * @author MuhammadRizky
 * 
 */
public class Foothill
{
   public static void main(String[] args)
   {
      // PART I
      createLine(80, "=");
      System.out.println("PART I");
      createLine(80, "=");
      
      // create a linked list and its iterator
      LinkedList<Card> list = new LinkedList<Card>();
      ListIterator<Card> iter;
      
      // temporary variable
      Card tempCard;
      
      // insert the cards to the list
      for (int i = 0; i < 10; i++)
      {
         tempCard = generateRandomCard();
         insert(list, tempCard);
         insert(list, tempCard);
      }
      
      System.out.println("Inserted Cards:");
      for (iter = list.listIterator(); iter.hasNext();)
         System.out.println(iter.next().toString());
      createLine(80, "-");
      
      try
      {
         for (int i = 0; i < 5; i++)
         {
            tempCard = list.peekFirst();
            System.out.println("Remove " + tempCard.toString() + ":");
            for (boolean bool = true; bool == true;)
            {
               bool = remove(list, tempCard);
            }
            
            for (iter = list.listIterator(); iter.hasNext();)
               System.out.println(iter.next().toString());
            createLine(80, "-");
         }
      }
      catch (Exception e)
      {
         // if error happens, it means there might be identical generated random
         // cards. which is harmless in this case
      }
      
      try
      {
         for (int i = 0; i < 5; i++)
         {
            tempCard = list.peekFirst();
            System.out.println("RemoveAll " + tempCard.toString() + ":");
            removeAll(list, tempCard);
            for (iter = list.listIterator(); iter.hasNext();)
               System.out.println(iter.next().toString());
            createLine(80, "-");
         }
      }
      catch (Exception e)
      {
         // if error happens, it means there might be identical generated random
         // cards. which is harmless in this case
      }
      
      // PART II
      createLine(80, "=");
      System.out.println("PART II");
      createLine(80, "=");
      
      // instantiate order arrays
      char[] valueRanks =
         { '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', '2' };
      Card.Suit[] suitRanks =
         { Card.Suit.diamonds, Card.Suit.clubs, Card.Suit.hearts,
               Card.Suit.spades };
      
      // create a default order array
      char[] defVal = new char[Card.numValsInOrderingArray];
      Card.Suit[] defSuit;
      
      // need to clone because we can't copy the reference
      defVal = Card.valueRanks.clone();
      defSuit = Card.suitRanks.clone();
      
      // instantiate lists
      LinkedList<Card> list1, list2;
      
      list1 = new LinkedList<Card>();
      list2 = new LinkedList<Card>();
      
      // push the default array to the stack
      CardWithOrderStk.pushOrdering(defVal, defSuit);
      
      // CardWithOrderStk.pushOrdering(Card.valueRanks, Card.suitRanks);
      
      // inserting the cards into the lists
      for (int i = 0; i < 20; i++)
      {
         tempCard = generateRandomCard();
         
         // insert the card to the first list with the normal ranks
         insert(list1, tempCard);
         
         // push a new rank to the card class
         CardWithOrderStk.pushOrdering(valueRanks, suitRanks);
         
         // insert the card to the second list with a different ranks
         insert(list2, tempCard);
         
         // pop the ranks
         CardWithOrderStk.popOrdering();
         
      }
      
      // show the list of list1
      System.out.println("List1:");
      for (iter = list1.listIterator(); iter.hasNext();)
         System.out.println(iter.next().toString());
      createLine(80, "-");
      
      // show the list of list2
      System.out.println("List2:");
      for (iter = list2.listIterator(); iter.hasNext();)
         System.out.println(iter.next().toString());
      createLine(80, "-");
      
   }
   
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
   
   static void insert(LinkedList<Card> list, Card card)
   {
      ListIterator<Card> iter;
      Card tempCard;
      
      for (iter = list.listIterator(); iter.hasNext();)
      {
         tempCard = iter.next();
         if (card.compareTo(tempCard) <= 0)
         {
            iter.previous(); // back up one
            break;
         }
      }
      
      iter.add(card);
   }
   
   static boolean remove(LinkedList<Card> list, Card card)
   {
      ListIterator<Card> iter;
      
      for (iter = list.listIterator(); iter.hasNext();)
         if (iter.next().compareTo(card) == 0)
         {
            iter.remove();
            return true;   // we found, we removed, we return
         }
      
      return false;
   }
   
   static boolean removeAll(LinkedList<Card> list, Card card)
   {
      ListIterator<Card> iter;
      boolean bool = false;
      
      for (iter = list.listIterator(); iter.hasNext();)
         if (iter.next().compareTo(card) == 0)
         {
            iter.remove();
            bool = true;
         }
      
      return bool;
   }
   
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
 * the card class that was used in assignment 5, which is based on assignment 1.
 * and also present in the module 8 of the class.
 * 
 * @author MuhammadRizky
 * 
 */
class Card implements Comparable<Card>
{
   // type and constants
   public enum State
   {
      deleted, active
   } // not bool because later we may expand
   
   public enum Suit
   {
      clubs, diamonds, hearts, spades
   }
   
   // for sort.
   static char[] valueRanks =
      { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', 'X' };
   static Suit[] suitRanks =
      { Suit.clubs, Suit.diamonds, Suit.hearts, Suit.spades };
   static int numValsInOrderingArray = 13;  // maybe 14 if 'X' = Joker, or < 13
   
   // private data
   private char value;
   private Suit suit;
   State state;
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
      
      // can't really have an error here
      this.suit = suit;
      
      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);
      
      // check for validity
      if (upVal == 'A' || upVal == 'K' || upVal == 'Q' || upVal == 'J'
            || upVal == 'T' || upVal == 'X' || (upVal >= '2' && upVal <= '9'))
      {
         errorFlag = false;
         state = State.active;
         this.value = upVal;
      }
      else
      {
         errorFlag = true;
         return false;
      }
      
      return !errorFlag;
   }
   
   public void setState(State state)
   {
      this.state = state;
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
   
   public State getState()
   {
      return state;
   }
   
   public boolean getErrorFlag()
   {
      return errorFlag;
   }
   
   // stringizer
   public String toString()
   {
      String retVal;
      
      if (errorFlag)
         return "** illegal **";
      if (state == State.deleted)
         return "( deleted )";
      
      // else implied
      
      if (value != 'X')
      {
         // not a joker
         retVal = String.valueOf(value);
         retVal += " of ";
         retVal += String.valueOf(suit);
      }
      else
      {
         // joker
         retVal = "joker";
         
         if (suit == Suit.clubs)
            retVal += " 1";
         else if (suit == Suit.diamonds)
            retVal += " 2";
         else if (suit == Suit.hearts)
            retVal += " 3";
         else if (suit == Suit.spades)
            retVal += " 4";
      }
      
      return retVal;
   }
   
   public boolean equals(Card card)
   {
      if (this.value != card.value)
         return false;
      if (this.suit != card.suit)
         return false;
      if (this.errorFlag != card.errorFlag)
         return false;
      if (this.state != card.state)
         return false;
      return true;
   }
   
   // sort member methods
   public int compareTo(Card other)
   {
      if (this.value == other.value)
         return (getSuitRank(this.suit) - getSuitRank(other.suit));
      
      return (getValueRank(this.value) - getValueRank(other.value));
   }
   
   public static void setRankingOrder(char[] valueOrderArr, Suit[] suitOrdeArr,
         int numValsInOrderingArray)
   {
      int k;
      
      // expects valueOrderArr[] to contain only cards used per pack,
      // including jokers, needed to define order for the game environment
      
      if (numValsInOrderingArray < 0 || numValsInOrderingArray > 13)
         return;
      
      Card.numValsInOrderingArray = numValsInOrderingArray;
      
      for (k = 0; k < numValsInOrderingArray; k++)
         Card.valueRanks[k] = valueOrderArr[k];
      
      for (k = 0; k < 4; k++)
         Card.suitRanks[k] = suitOrdeArr[k];
   }
   
   public static int getSuitRank(Suit st)
   {
      int k;
      
      for (k = 0; k < 4; k++)
         if (suitRanks[k] == st)
            return k;
      
      // should not happen
      return 0;
   }
   
   public static int getValueRank(char val)
   {
      int k;
      
      for (k = 0; k < numValsInOrderingArray; k++)
         if (valueRanks[k] == val)
            return k;
      
      // should not happen
      return 0;
   }
   
   public static void arraySort(Card[] array, int arraySize)
   {
      for (int k = 0; k < arraySize; k++)
         if (!floatLargestToTop(array, arraySize - 1 - k))
            return;
   }
   
   private static boolean floatLargestToTop(Card[] array, int top)
   {
      boolean changed = false;
      Card temp;
      
      for (int k = 0; k < top; k++)
         if (array[k].compareTo(array[k + 1]) > 0)
         {
            temp = array[k];
            array[k] = array[k + 1];
            array[k + 1] = temp;
            changed = true;
         }
      ;
      return changed;
   }
}

class Deck
{
   // six full decks (no jokers) is enough for about any game
   private static final int MAX_CARDS_PER_DECK = 6 * 52;
   private static Card[] masterPack;  // one 52-Card master for initializing
                                     // decks
   
   private Card[] cards;
   private int topCard;
   private int numPacks;
   
   private static boolean firstTime = true;  // avoid calling allcMstrPck > once
   
   public Deck(int numPacks)
   {
      allocateMasterPack();  // do not call from init()
      cards = new Card[MAX_CARDS_PER_DECK];
      init(numPacks);
   }
   
   static private void allocateMasterPack()
   {
      int j, k;
      Card.Suit st;
      char val;
      
      // we're in static method; only needed once per program: good for whole
      // class
      if (!firstTime)
         return;
      firstTime = false;
      
      // allocate
      masterPack = new Card[52];
      for (k = 0; k < 52; k++)
         masterPack[k] = new Card();
      
      // next set data
      for (k = 0; k < 4; k++)
      {
         // set the suit for this loop pass
         st = Card.Suit.values()[k];
         
         // now set all the values for this suit
         masterPack[13 * k].set('A', st);
         for (val = '2', j = 1; val <= '9'; val++, j++)
            masterPack[13 * k + j].set(val, st);
         masterPack[13 * k + 9].set('T', st);
         masterPack[13 * k + 10].set('J', st);
         masterPack[13 * k + 11].set('Q', st);
         masterPack[13 * k + 12].set('K', st);
      }
   }
   
   public Deck()
   {
      this(1);
   }
   
   // set deck from 1 to 6 packs, perfecly ordered
   public void init(int numPacks)
   {
      int k, pack;
      
      if (numPacks < 1 || numPacks > 6)
         numPacks = 1;
      
      // hand over the masterPack cards to our deck
      for (pack = 0; pack < numPacks; pack++)
         for (k = 0; k < 52; k++)
            cards[pack * 52 + k] = masterPack[k];
      
      // if something modified a card, we would be in trouble. fortunately,
      // we don't expect a card to ever be modified after instantiated
      // in the context of a deck. e.g. state would be for deck set-up only
      
      this.numPacks = numPacks;
      topCard = numPacks * 52;
   }
   
   public void init()
   {
      init(1);
   }
   
   public int getNumCards()
   {
      return topCard;
   }
   
   public void shuffle()
   {
      Card tempCard;
      int k, randInt;
      
      // topCard is size of deck
      for (k = 0; k < topCard; k++)
      {
         randInt = (int) (Math.random() * topCard);
         
         // swap cards k and randInt (sometimes k == randInt: okay)
         tempCard = cards[k];
         cards[k] = cards[randInt];
         cards[randInt] = tempCard;
      }
   }
   
   public Card dealCard()
   {
      // always deal the topCard.
      Card errorReturn = new Card('E', Card.Suit.spades); // in rare cases
      
      if (topCard == 0)
         return errorReturn;
      else
         return cards[--topCard];
   }
   
   public Card inspectCard(int k)
   {
      // return copy of card at position k.
      // if client tries to access out-of-bounds card, return error
      
      Card errorReturn = new Card('E', Card.Suit.spades); // in rare cases
      
      if (k < 0 || k >= topCard)
         return errorReturn;
      else
         return cards[k];
   }
}

class CardWithOrderStk extends Card
{
   private static Stack<OrderObject> rankList = new Stack<OrderObject>();
   
   static private class OrderObject
   {
      private char[] valueRanks;
      private Card.Suit[] suitRanks;
      
      OrderObject(char[] valueRanks, Card.Suit[] suitRanks)
      {
         this.valueRanks = valueRanks;
         this.suitRanks = suitRanks;
      }
   }
   
   public static void pushOrdering(char[] valueRanks, Card.Suit[] suitRanks)
   {
      rankList.push(new OrderObject(valueRanks, suitRanks));
      
      Card.setRankingOrder(valueRanks, suitRanks, Card.numValsInOrderingArray);
   }
   
   public static void popOrdering()
   {
      rankList.pop();
      
      Card.setRankingOrder(rankList.peek().valueRanks,
            rankList.peek().suitRanks, Card.numValsInOrderingArray);
   }
}

/*----------------------------------Sample Run----------------------------------

================================================================================
PART I
================================================================================
Inserted Cards:
2 of hearts
2 of hearts
4 of spades
4 of spades
5 of hearts
5 of hearts
5 of spades
5 of spades
6 of clubs
6 of clubs
7 of hearts
7 of hearts
T of hearts
T of hearts
Q of diamonds
Q of diamonds
A of clubs
A of clubs
A of spades
A of spades
--------------------------------------------------------------------------------
Remove 2 of hearts:
4 of spades
4 of spades
5 of hearts
5 of hearts
5 of spades
5 of spades
6 of clubs
6 of clubs
7 of hearts
7 of hearts
T of hearts
T of hearts
Q of diamonds
Q of diamonds
A of clubs
A of clubs
A of spades
A of spades
--------------------------------------------------------------------------------
Remove 4 of spades:
5 of hearts
5 of hearts
5 of spades
5 of spades
6 of clubs
6 of clubs
7 of hearts
7 of hearts
T of hearts
T of hearts
Q of diamonds
Q of diamonds
A of clubs
A of clubs
A of spades
A of spades
--------------------------------------------------------------------------------
Remove 5 of hearts:
5 of spades
5 of spades
6 of clubs
6 of clubs
7 of hearts
7 of hearts
T of hearts
T of hearts
Q of diamonds
Q of diamonds
A of clubs
A of clubs
A of spades
A of spades
--------------------------------------------------------------------------------
Remove 5 of spades:
6 of clubs
6 of clubs
7 of hearts
7 of hearts
T of hearts
T of hearts
Q of diamonds
Q of diamonds
A of clubs
A of clubs
A of spades
A of spades
--------------------------------------------------------------------------------
Remove 6 of clubs:
7 of hearts
7 of hearts
T of hearts
T of hearts
Q of diamonds
Q of diamonds
A of clubs
A of clubs
A of spades
A of spades
--------------------------------------------------------------------------------
RemoveAll 7 of hearts:
T of hearts
T of hearts
Q of diamonds
Q of diamonds
A of clubs
A of clubs
A of spades
A of spades
--------------------------------------------------------------------------------
RemoveAll T of hearts:
Q of diamonds
Q of diamonds
A of clubs
A of clubs
A of spades
A of spades
--------------------------------------------------------------------------------
RemoveAll Q of diamonds:
A of clubs
A of clubs
A of spades
A of spades
--------------------------------------------------------------------------------
RemoveAll A of clubs:
A of spades
A of spades
--------------------------------------------------------------------------------
RemoveAll A of spades:
--------------------------------------------------------------------------------
================================================================================
PART II
================================================================================
List1:
2 of spades
3 of diamonds
4 of clubs
4 of diamonds
4 of spades
5 of hearts
6 of hearts
7 of hearts
8 of spades
9 of clubs
9 of spades
T of clubs
T of spades
K of diamonds
K of hearts
K of hearts
K of spades
K of spades
A of clubs
A of clubs
--------------------------------------------------------------------------------
List2:
3 of diamonds
4 of diamonds
4 of clubs
4 of spades
5 of hearts
6 of hearts
7 of hearts
8 of spades
9 of clubs
9 of spades
T of clubs
T of spades
K of diamonds
K of hearts
K of hearts
K of spades
K of spades
A of clubs
A of clubs
2 of spades
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/
