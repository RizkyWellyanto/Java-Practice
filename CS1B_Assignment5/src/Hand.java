// class Hand ----------------------------------------------------------------
public class Hand
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
   
   void sort()
   {
      // assumes that Card class has been sent ordering (if default not correct)
      Card.arraySort(myCards, numCards);
   }
}
