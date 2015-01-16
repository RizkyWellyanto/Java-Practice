package week6_assignment6_part_A;

import cs_1c.*;

import java.util.*;

/**
 * CS 1C Assignment 6 - Quadratic Probing Hash Table with a find()
 * ------------------------------------------------------
 * Name : Muhammad Rizky Wellyanto
 * CWID : 20144588
 * Parts : A
 * Description : This is the sixth assignment of CS_1C
 * ML F14. This program implements the Quadratic Probing Hash Table
 * with the addition of a find method.
 */

public class Foothill
{
   public static final int NUM_RANDOM_INDICES = 25;
   
   public static void main(String[] args) throws Exception
   {
      EBookEntryReader bookInput = new EBookEntryReader("catalog-short4.txt");
      int index;
      EBookCompInt bookResult;
      // EBookCompString bookResult;
      
      System.out.println("EBook File Name: " + bookInput.getFileName());
      System.out.println("Number of Books: " + bookInput.getNumBooks());
      
      FHhashQPwFind< Integer, EBookCompInt> hashTable = 
            new FHhashQPwFind<Integer, EBookCompInt>();
      
      //FHhashQPwFind<String, EBookCompString> hashTable = 
      //      new FHhashQPwFind<String, EBookCompString>();
      
      if (bookInput.readError())
      {
         System.out.println("couldn't open " + bookInput.getFileName()
               + " for input.");
      }
      
      // create a QP hash table of EBooks
      int arraySize = bookInput.getNumBooks();
      for (int i = 0; i < arraySize; i++)
      {
         // for integer
         hashTable.insert(new EBookCompInt(bookInput.getBook(i)));
         
         // for string
         // hashTable.insert(new EBookCompString(bookInput.getBook(i)));
      }
      System.out.println();
      
      // generate some random indices into the EBookEntryReader vector ...
      System.out.print("Generate random indices into the EbookEntryReader");
      int[] randomIndices = new int[NUM_RANDOM_INDICES];
      for (int i = 0; i < NUM_RANDOM_INDICES; i++)
      {
         randomIndices[i] = (int) (Math.random() * arraySize);
         System.out.print(randomIndices[i] + " ");
      }
      System.out.println("\n");
      
      // display NUM_RANDOM_INDICES books from array ...
      System.out.println("Random Books: ");
      for (int i = 0; i < NUM_RANDOM_INDICES; i++)
      {
         System.out.println(bookInput.getBook(i));
      }
      
      // attempt to find on the selected key
      System.out.println("The same random books from the hash table ");
      for (int k = 0; k < NUM_RANDOM_INDICES; k++)
      {
         System.out.println("Book Num: " + randomIndices[k] + " ");
         try
         {
            // bookResult = hashTable.find(
            // bookInput.getBook(randomIndices[k]).getCreator() );
            bookResult = hashTable.find(bookInput.getBook(randomIndices[k])
                  .getETextNum());
            System.out.println("Success : " + bookResult);;
         }
         catch (NoSuchElementException e)
         {
            System.out.println("ERROR");
         }
      }
            
      // test known successes failures exceptions:
      try
      {
         // bookResult = hashTable.find( "Jack Kerouac" );
         bookResult = hashTable.find(-3);
         
         System.out.println("Success: " + bookResult.data.getTitle());
      }
      catch (NoSuchElementException e)
      {
         System.out.println("ERROR");
      }
      
      // more failures
      try
      {
         //bookResult = hashTable.find("GAME OF THRONES");
         bookResult = hashTable.find(1000000);
         
         System.out.println("Success: " + bookResult.data.getTitle());
      }
      catch (NoSuchElementException e)
      {
         //bookResult = hashTable.find("GANDALF: YOU SHALL NOT PASS");
         System.out.println("ERROR");
      }
      
      try
      {
         //bookResult = hashTable.find("Majalah Trubus");
         bookResult = hashTable.find(123412);
      }
      catch (NoSuchElementException e)
      {
         System.out.println("ERROR");
      }
   }
}

/**
 * core of the assignment
 * 
 * @author MuhammadRizky
 * 
 * @param <KeyType>
 * @param <E>
 */
class FHhashQPwFind<KeyType, E extends Comparable<KeyType>> extends FHhashQP<E>
{
   public E find(KeyType key)
   {
      int index = findPosKey(key);
      HashEntry<E> result = this.mArray[index];
      
      if (result.state != ACTIVE)
         throw new NoSuchElementException();
      
      return result.data;
   }
   
   protected int myHashKey(KeyType key)
   {
      int hashVal = key.hashCode() % this.mTableSize;
      
      if (hashVal < 0)
         hashVal += this.mTableSize;
      
      return hashVal;
   }
   
   protected int findPosKey(KeyType key)
   {
      int kthOddNum = 1;
      int index = myHashKey(key);
      
      while (mArray[index].state != EMPTY
            && this.mArray[index].data.compareTo(key) != 0)
      {
         index += kthOddNum; // k squared = (k-1) squared + kth odd #
         kthOddNum += 2;     // compute next odd #
         if (index >= mTableSize)
            index -= mTableSize;
      }
      return index;
   }
}

class EBookCompInt implements Comparable<Integer>
{
   EBookEntry data;
   
   public EBookCompInt(EBookEntry e)
   {
      this.data = e;
   }
   
   public String toString()
   {
      return this.data.toString();
   }
   
   public int compareTo(Integer key)
   {
      return this.data.getETextNum() - key;
   }
   
   public boolean equals(EBookCompInt e)
   {
      return this.data.equals(e.data);
   }
   
   public int hashCode()
   {
      return this.data.getETextNum();
   }
}

class EBookCompString implements Comparable<String>
{
   EBookEntry data;
   
   public EBookCompString(EBookEntry e)
   {
      this.data = e;
   }
   
   public String toString()
   {
      return this.data.toString();
   }
   
   public int compareTo(String key)
   {
      return this.data.getCreator().compareTo(key);
   }
   
   public boolean equals(EBookCompString e)
   {
      return this.data.equals(e.data);
   }
   
   public int hashCode()
   {
      return this.data.getCreator().hashCode();
   }
}

/*---------------------------------SAMPLE RUN-----------------------------------

EBook File Name: catalog-short4.txt
Number of Books: 4863

Generate random indices into the EbookEntryReader1441 3630 2314 656 4185 4492 21
1 4511 1107 1643 16 2884 1653 1605 4791 1637 1070 3015 3357 4477 2954 1449 350 1
359 1741 

Random Books: 
   # 30170  ---------------
   "Lonesome Hearts"
   by Winterbotham, R. R. (Russell Robert), 1904-1971
   re: Science fiction


   # 30169  ---------------
   "The Story of the White Mouse"
   by Unknown
   re: Conduct of life -- Juvenile fiction


   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714


   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.


   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction


   # 28547  ---------------
   "The Words of Jesus"
   by Macduff, John R. (John Ross), 1818-1895
   re: Devotional exercises


   # 30168  ---------------
   "A History of Giggleswick SchoolFrom its Foundation, 1499 to 1912"
   by Bell, Edward Allen
   re: Giggleswick School (Giggleswick, England) -- History


   # 28712  ---------------
   "The American Missionary — Volume 54, No. 3, October, 1900"
   by Various
   re: Congregational churches -- Missions -- Periodicals


   # 28548  ---------------
   "Gipsy Lifebeing an account of our Gipsies and their children, with suggestio
ns for their improvement"
   by Smith, George, 1831-1895
   re: Romanies -- Great Britain


   # 29171  ---------------
   "The Carroll Girls"
   by Quiller-Couch, Mabel, 1866-1924
   re: Girls -- Conduct of life -- Juvenile fiction


   # 28821  ---------------
   "The Works of Charles Dudley WarnerProject Gutenberg Editions"
   by Warner, Charles Dudley, 1829-1900
   re: Indexes


   # 28549  ---------------
   "A Treatise on Foreign TeasAbstracted From An Ingenious Work, Lately Publishe
d,Entitled An Essay On the Nerves"
   by Smith, Hugh, 1736?-1789
   re: Tea -- Health aspects -- China


   # 30167  ---------------
   "Royal Children of English History"
   by Nesbit, E. (Edith), 1858-1924
   re: Princes -- Great Britain -- Biography


   # 30166  ---------------
   "Astounding Stories, March, 1931"
   by Various
   re: Science fiction -- Periodicals


   # 28822  ---------------
   "The Works Of Winston ChurchillA Linked Index Of The Project Gutenberg Editio
ns"
   by Churchill, Winston, 1871-1947
   re: Indexes


   # 28823  ---------------
   "The Works Of George MeredithA Linked Index to the Project Gutenberg Editions
"
   by Meredith, George, 1828-1909
   re: Indexes


   # 28716  ---------------
   "Little Mittens for The Little DarlingsBeing the Second Book of the Series"
   by Fanny, Aunt, 1822-1894
   re: Children's stories


   # 28824  ---------------
   "The Mermaid's Prophecyand Other Songs Relating to Queen Dagmar"
   by Borrow, George Henry, 1803-1881
   re: PR


   # 29776  ---------------
   "Pretty Madcap DorothyOr, How She Won a Lover"
   by Libbey, Laura Jean, 1862-1924
   re: Women employees -- New York (State) -- New York -- Fiction


   # 28825  ---------------
   "The Verner Raven; The Count of Vendel's Daughterand Other Ballads"
   by Borrow, George Henry, 1803-1881
   re: PR


   # 29177  ---------------
   "The Pygmy Planet"
   by Williamson, Jack, 1908-2006
   re: Science fiction


   # 28826  ---------------
   "The Song of Deirdra, King Byrge and his Brothersand Other Ballads"
   by Borrow, George Henry, 1803-1881
   re: PR


   # 29178  ---------------
   "Literary Tours in The Highlands and Islands of Scotland"
   by Holmes, Daniel Turner
   re: Scottish literature -- History and criticism


   # 29180  ---------------
   "Harper's Young People, October 12, 1880An Illustrated Weekly"
   by Various
   re: Children's periodicals, American


   # 28830  ---------------
   "The Songs of Ranild"
   by Borrow, George Henry, 1803-1881
   re: PR


The same random books from the hash table 
Book Num: 1441 
Success :    # 28274  ---------------
   "The Beauties of Natureand the Wonders of the World We Live In"
   by Lubbock, John, Sir, 1834-1913
   re: Natural history


Book Num: 3630 
Success :    # 26640  ---------------
   "The Humbugs of the WorldAn Account of Humbugs, Delusions, Impositions, Quack
eries,Deceits and Deceivers Generally, in All Ages"
   by Barnum, P. T. (Phineas Taylor), 1810-1891
   re: Impostors and imposture


Book Num: 2314 
Success :    # 352  ---------------
   "Buttered Side Down: Stories"
   by Ferber, Edna, 1885-1968
   re: Short stories


Book Num: 656 
Success :    # 27987  ---------------
   "Glory and the Other Girl"
   by Donnell, Annie Hamilton, 1862-
   re: Girls -- Juvenile fiction


Book Num: 4185 
Success :    # 25854  ---------------
   "The Letters of Charles DickensVol. 3 (of 3), 1836-1870"
   by Dickens, Charles, 1812-1870
   re: Novelists, English -- 19th century -- Correspondence


Book Num: 4492 
Success :    # 25429  ---------------
   "The Peril Finders"
   by Fenn, George Manville, 1831-1909
   re: PR


Book Num: 211 
Success :    # 28161  ---------------
   "The Master Mummer"
   by Oppenheim, E. Phillips (Edward Phillips), 1866-1946
   re: Detective and mystery stories


Book Num: 4511 
Success :    # 25387  ---------------
   "Mathematical Essays and Recreations"
   by Schubert, Hermann Cäsar Hannibal, 1848-1911
   re: (no data found)


Book Num: 1107 
Success :    # 22790  ---------------
   "The accomplisht cookor, The art &amp; mystery of cookery"
   by May, Robert
   re: Cookery, English -- Early works to 1800


Book Num: 1643 
Success :    # 28406  ---------------
   "Why I Believe in Scouting for Girls"
   by Rinehart, Mary Roberts, 1876-1958
   re: Girl Scouts


Book Num: 16 
Success :    # 28716  ---------------
   "Little Mittens for The Little DarlingsBeing the Second Book of the Series"
   by Fanny, Aunt, 1822-1894
   re: Children's stories


Book Num: 2884 
Success :    # 29402  ---------------
   "The French ImmortalsQuotes And Images"
   by Various
   re: PQ


Book Num: 1653 
Success :    # 28698  ---------------
   "The Crystal Crypt"
   by Dick, Philip K., 1928-1982
   re: Science fiction


Book Num: 1605 
Success :    # 28984  ---------------
   "Harper's Young People, June 8, 1880An Illustrated Weekly"
   by Various
   re: Children's periodicals, American


Book Num: 4791 
Success :    # 24859  ---------------
   "Turned Adrift"
   by Collingwood, Harry, 1851-1922
   re: Survival after airplane accidents, shipwrecks, etc. -- Fiction


Book Num: 1637 
Success :    # 28399  ---------------
   "Four Plays of Gil Vicente"
   by Vicente, Gil, 1465-1537
   re: Portuguese drama


Book Num: 1070 
Success :    # 28016  ---------------
   "In the Tail of the Peacock"
   by Savory, Isabel
   re: Morocco -- Description and travel


Book Num: 3015 
Success :    # 27709  ---------------
   "Five O'Clock TeaFarce"
   by Howells, William Dean, 1837-1920
   re: PS


Book Num: 3357 
Success :    # 27101  ---------------
   "Life of Rear Admiral John Randolph Tucker"
   by Rochelle, James Henry, 1826-1889
   re: United States -- History -- Civil War, 1861-1865 -- Naval operations


Book Num: 4477 
Success :    # 25454  ---------------
   "Introduction of the Locomotive Safety TruckContributions from the Museum of 
History and Technology: Paper 24"
   by White, John H., 1933-
   re: Locomotives


Book Num: 2954 
Success :    # 27812  ---------------
   "Buchanan's Journal of Man, January 1888Volume 1, Number 12"
   by (no data found)
   re: Science -- Periodicals


Book Num: 1449 
Success :    # 28116  ---------------
   "Currie, John Allister, 1866-"
   by Currie, John Allister, 1866-
   re: World War, 1914-1918 -- Personal narratives


Book Num: 350 
Success :    # 29579  ---------------
   "Watchbird"
   by Sheckley, Robert, 1928-2005
   re: Science fiction


Book Num: 1359 
Success :    # 30099  ---------------
   "HypochondriasisA Practical Treatise (1766)"
   by Hill, John, 1714?-1775
   re: Medicine -- Early works to 1800


Book Num: 1741 
Success :    # 29195  ---------------
   "It's All Yours"
   by Merwin, Sam, 1910-1996
   re: Science fiction


ERROR
ERROR
ERROR


------------------------------------------------------------------------------*/
