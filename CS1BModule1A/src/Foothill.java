public class Foothill
{
   public static void main(String[] args)
   {
      Card card1, card2, card3, card4, card5;
    
      card1 = new Card();
      card2 = new Card('5');
      card3 = new Card('9', Card.hearts);
      card4 = new Card('j', Card.clubs);
      card5 = new Card('3', 5);
      
      Card new_card = new Card(card4);
      
      String report = card1.toString() + ", " + card2.toString() 
         + ", " + card3.toString() + ", " + card4.toString() + ", "
         + card5.toString() + ", " + new_card.toString() + ". ";

      System.out.println(report);
      
      if ( ! card1.set('1', Card.clubs) )
         System.out.println("Set failed:  card1.set('1', clubs)");
      if ( ! card1.set('T', Card.clubs) )
         System.out.println("Set failed:  card1.set('T', clubs)");
      
      card1 = card3;  
      
      // test assignment operator for objects
      System.out.println("card1: " + card1.toString() + "     card3: "
            + card3.toString());

     // notice the assignment operator is not a reference copy (unlike C++)
      card1.set('Q', Card.spades);
      System.out.println("card1: " + card1.toString() + "     card3: "
            + card3.toString());
   }
}
   
class Card
{   
   // private data
   private char value;
   private int suit;
   
   // static class constants (for suits)
   public static final int clubs = 0;
   public static final int diamonds = 1;
   public static final int hearts = 2;
   public static final int spades = 3;
   
   // 4 overloaded constructors
   public Card(char value, int suit)
   {
      set(value, suit);
   }
   public Card(char value)
   {
      this(value, spades);
   }
   public Card()
   {
      this('A', spades);
   }
   // copy constructor
   public Card(Card card)
   {
      this.suit = card.suit;
      this.value = card.value;
   }

   // mutator
   public boolean set(char value, int suit)
   {
      char upVal;            // for upcasing char
      boolean valid = true;   // return value
      
      // filter out bad suit input:

      if (suit == clubs || suit == diamonds 
            || suit == hearts || suit == spades)
         this.suit = suit;
      else
      {
         valid = false;
         this.suit = spades;
      }
   
      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);
      // check for validity
      if (
         upVal == 'A' || upVal == 'K'
         || upVal == 'Q' || upVal == 'J'
         || upVal == 'T'
         || (upVal >= '2' && upVal <= '9')
         )
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
   public int getSuit()
   {
      return suit;
   }
      
   // stringizer
   public String toString()
   {
      String retVal;

      // convert from char to String
      retVal =  String.valueOf(value);

      if (suit == spades)
         retVal += " of Spades";
      else if (suit == hearts)
         retVal += " of Hearts";
      else if (suit == diamonds)
         retVal += " of Diamonds";
      else if (suit == clubs)
         retVal += " of Clubs";

      return retVal;
   }
}