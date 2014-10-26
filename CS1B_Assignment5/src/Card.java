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
      
      return changed;
   }
   
   public static void arraySort(Card[] array, int arraySize)
   {
      for (int k = 0; k < arraySize; k++)
         if (!floatLargestToTop(array, arraySize - 1 - k))
            return;
   }
}
