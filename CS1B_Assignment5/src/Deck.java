// class Deck ----------------------------------------------------------------
class Deck
{
   // six full decks (with jokers) is enough for about any game
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
   
   public Card takeACard()
   {
      return new Card();
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
   
   public boolean removeCard(Card card)
   {
      int k;
      boolean foundAtLeastOne;
      
      foundAtLeastOne = false;
      for (k = 0; k < topCard; k++)
      {
         // care: use while, not if, in case we copy to-be-removed from top to k
         while (cards[k].equals(card))
         {
            // overwrite card[k] with top of deck, then decrement topCard
            cards[k] = cards[topCard - 1];
            topCard--;
            foundAtLeastOne = true;
            // test because "while" causes topCard to decrease, possibly below k
            if (k >= topCard)
               break;
         }
      }
      // did above work if k == topCard-1? think about it
      return foundAtLeastOne;
   }
   
   public boolean addCard(Card card)
   {
      // don't allow too many copies of this card in the deck
      if (numOccurrences(card) >= numPacks)
         return false;
      
      cards[topCard++] = card;
      return true;
   }
   
   public int numOccurrences(Card card)
   {
      int retVal, k;
      
      retVal = 0;
      
      // assumption: card is a default item: not deleted and state=active)
      for (k = 0; k < topCard; k++)
      {
         if (inspectCard(k).equals(card))
            retVal++;
      }
      return retVal;
   }
   
}
