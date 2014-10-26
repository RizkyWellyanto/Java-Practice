/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 6 in CS 1B Jesse Cecil Winter 2014
 * this program is called GUI Cards. this program test the knowledge of multi
 * class programming with with the combination of inheritance and Graphics.
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

// import necessary classes
import java.lang.Exception;

/**
 * main client
 * 
 * @author MuhammadRizky
 * 
 */
public class Foothill
{
   public static void main(String[] args)
   {
      // create bunch of cards
      createLine(80, "-");
      System.out.println("Create Cards");
      
      Card c0 = new Card('T', Card.Suit.spades);
      Card c1 = new Card('J', Card.Suit.clubs);
      Card c2 = new Card('Q', Card.Suit.diamonds);
      Card c3 = new Card('K', Card.Suit.hearts);
      Card c4 = new Card('A', Card.Suit.spades);
      
      createLine(80, "-");
      System.out.println("Add Cards to the CardQueue");
      
      CardQueue cq = new CardQueue();
      
      // add the cards
      cq.addCard(c0);
      cq.addCard(c1);
      cq.addCard(c2);
      cq.addCard(c3);
      cq.addCard(c4);
      
      createLine(80, "-");
      System.out.println("Show the whole queue:");
      
      // show the whole queue
      System.out.println(cq.toString());
      
      createLine(80, "-");
      System.out.println("Remove cards one by one, while printing the "
            + "returned card. removing cards for ten times:");
      
      // removing the cards
      for (int i = 0; i < 10; i++)
      {
         try
         {
            System.out.println(cq.removeCard());
         }
         catch (QueueEmptyException e) // catch the error
         {
            System.out.println("[Catched an error exception]");
         }
      }
      
      createLine(80, "-");
      System.out.println("Program shutted down");
      createLine(80, "-");
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
 * the Node class
 * 
 * @author MuhammadRizky
 * 
 */
class Node
{
   // member data that points another Node
   protected Node next;
   
   // constructor
   public Node()
   {
      next = null;
   }
   
   /**
    * return the string state of the node
    */
   public String toString()
   {
      return "[Basic Node] ";
   }
}

/**
 * the Queue class
 * 
 * @author MuhammadRizky
 * 
 */
class Queue
{
   // pointer to first node in queue
   private Node head;
   private Node tail;
   
   // constructor
   Queue()
   {
      head = null;
      tail = null;
   }
   
   /**
    * add a node to the queue. put it as the tail of the queue.
    * 
    * @param newNode as the intended node
    * @return true if success, false otherwise
    */
   public boolean add(Node newNode)
   {
      // emergency case
      if (newNode == null)
         return false;
      
      // set the initial head and tail, if the head is null
      if (head == null)
      {
         head = newNode;
         tail = newNode;
      }
      
      // set the new node to the tail of the queue
      tail.next = newNode;
      tail = newNode;
      
      return true;
   }
   
   /**
    * remove the first node of the queue.
    * 
    * @return the head node
    * @throws QueueEmptyException if the queue is empty
    */
   public Node remove() throws QueueEmptyException
   {
      // create a temporary node
      Node tempNode;
      
      tempNode = head;
      if (head != null)
      {
         head = head.next;
         tempNode.next = null; // don't give client access to queue!
      }
      else
      {
         // throw a queue exception of the node is null
         throw new QueueEmptyException();
      }
      
      // return the previous head node
      return tempNode;
   }
   
   // toString method
   /**
    * return string representation of the queue
    */
   public String toString()
   {
      // declare local variables
      Node nodes;
      StringBuilder str = new StringBuilder();
      
      // Display all the nodes in the queue
      for (nodes = head; nodes != null; nodes = nodes.next)
      {
         str.append(nodes.toString());
      }
      
      // return the string
      return str.toString();
   }
}

/**
 * an exception that specifically created for empty queue purpose
 * 
 * @author MuhammadRizky
 * 
 */
class QueueEmptyException extends Exception
{
   QueueEmptyException()
   {
      super("The Queue is Empty");
   }
}

/**
 * 
 * @author MuhammadRizky
 * 
 */
class CardNode extends Node
{
   // additional data for subclass
   private Card card;
   
   // constructor
   CardNode(char value, Card.Suit suit)
   {
      super();  // constructor call to base class
      card = new Card(value, suit);
   }
   
   // accessor
   /**
    * get the card object from the CardNode objbect
    * 
    * @return the card object
    */
   public Card getCard()
   {
      return card;
   }
   
   // toString method
   /**
    * stringize method
    */
   public String toString()
   {
      return card.toString() + "\n";
   }
}

/**
 * CardQueue class, extension of Queue class
 * 
 * @author MuhammadRizky
 * 
 */
class CardQueue extends Queue
{
   CardQueue()
   {
      super();
   }
   
   /**
    * add a card object to the queue
    * 
    * @param value the cards value
    * @param suit the cards suit
    */
   public void addCard(char value, Card.Suit suit)
   {
      // create a new CardNode
      CardNode cNode = new CardNode(value, suit);
      
      // add the CardNode onto the Queue (base class call)
      super.add(cNode);
   }
   
   /**
    * overloaded version of addCard
    * 
    * @param card the card object
    */
   public void addCard(Card card)
   {
      this.addCard(card.getVal(), card.getSuit());
   }
   
   /**
    * remove the first card, and return it
    * 
    * @return the head card
    * @throws QueueEmptyException if there's an error
    */
   public Card removeCard() throws QueueEmptyException
   {
      // remove a card node
      CardNode cNode = (CardNode) remove();
      
      return cNode.getCard();
   }
}

/**
 * Card class from week 5
 * 
 * @author MuhammadRizky
 * 
 */
// class Card ----------------------------------------------------------------
class Card
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
   public static char[] valueRanks =
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

/*------------------------------Sample Run--------------------------------------

--------------------------------------------------------------------------------
Create Cards
--------------------------------------------------------------------------------
Add Cards to the CardQueue
--------------------------------------------------------------------------------
Show the whole queue:
T of spades
J of clubs
Q of diamonds
K of hearts
A of spades

--------------------------------------------------------------------------------
Remove cards one by one, while printing the returned card. removing cards for te
n times:
T of spades
J of clubs
Q of diamonds
K of hearts
A of spades
[Catched an error exception]
[Catched an error exception]
[Catched an error exception]
[Catched an error exception]
[Catched an error exception]
--------------------------------------------------------------------------------
Program shutted down
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/
