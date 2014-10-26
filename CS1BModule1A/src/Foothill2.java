public class Foothill2
{
   public static void main(String[] args)
   {
      Card card1, card2, card3, card4, card5;

      card1 = new Card();
      card2 = new Card('5');
      card3 = new Card('9', Suit.hearts);
      card4 = new Card('j', Suit.clubs);
      card5 = new Card('3', Suit.diamonds);

      Card new_card = new Card(card4);

      String report = card1.toString() + ", " + card2.toString() + ", "
            + card3.toString() + ", " + card4.toString() + ", "
            + card5.toString() + ", " + new_card.toString() + ". ";

      System.out.println(report);

      if (!card1.set('1', Suit.clubs))
         System.out.println("Set failed:  card1.set('1', Suit.clubs)");
      if (!card1.set('T', Suit.clubs))
         System.out.println("Set failed:  card1.set('T', Suit.clubs)");

      card1 = card3;
      
      // test assignment operator for objects
      System.out.println("card1: " + card1.toString() + "     card3: "
            + card3.toString());

      // notice the assignment operator is not a reference copy (unlike C++)
      card1.set('Q', Suit.spades);
      System.out.println("card1: " + card1.toString() + "     card3: "
            + card3.toString());
   }
}

// enum type constants (for suits)
enum Suit
{
   clubs, diamonds, hearts, spades
}

class Card
{
   // private data
   private char value;
   private Suit suit;

   // 4 overloaded constructors
   public Card( char value, Suit suit)
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
      this.suit = card.suit;
      this.value = card.value;
   }

   // mutator
   public boolean set(char value, Suit suit)
   {
      char upVal; // for upcasing char
      boolean valid = true; // return value

      // suit is enum, so no test needed: all suits allowed

      this.suit = suit;
      
      // test val:  convert to uppercase to simplify
      upVal = Character.toUpperCase(value);
      // check for validity
      if (upVal == 'A' || upVal == 'K' || upVal == 'Q' || upVal == 'J'
            || upVal == 'T' || (upVal >= '2' && upVal <= '9'))
         this.value = upVal;
      else
      {
         valid = false;
         this.value = 'A';
      }
      return valid;
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

   // stringizer
   public String toString()
   {
      String retVal;

      retVal = String.valueOf(value) + " of " + suit;
      return retVal;
   }
}