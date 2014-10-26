package week1_assignment1_part_B;

import cs_1c.*;
import java.text.*;
import java.util.*;

// ------------------------------------------------------
public class Foothill
{
   // ------- main --------------
   public static void main(String[] args) throws Exception
   {
      int TARGET = 1000;
      ArrayList<iTunesEntry> dataSet = new ArrayList<iTunesEntry>();
      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      int k, j, numSets, max, kBest, arraySize, masterSum;
      boolean foundPerfect;
      
      // for formatting and timing
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long startTime, stopTime;
      
      // read the iTunes Data
      iTunesEntryReader tunesInput = new iTunesEntryReader("itunes_file.txt");
      
      // test the success of the read:
      if (tunesInput.readError())
      {
         System.out.println("couldn't open " + tunesInput.getFileName()
               + " for input.");
         return;
      }
      
      // load the dataSet ArrayList with the iTunes:
      arraySize = tunesInput.getNumTunes();
      for (k = 0; k < arraySize; k++)
         dataSet.add(tunesInput.getTune(k));
      
      choices.clear();
      System.out.println("Target time: " + TARGET);
      
      // first of all check whether the sum of the list is equal to target
      masterSum = (int) calculateSum(dataSet);
      if (masterSum <= TARGET)
      {
         // if the sum doesn't even reach or equal to the target
         System.out.println("Solution is the entire master set with sum of = "
               + masterSum);
         
         // just show the whole thing
         showList(dataSet);
         
         // exit main
         return;
      }
      
      // START TIMIG THE ALGORITHM
      startTime = System.nanoTime();
      
      // add an empty sublist that represents empty set
      choices.add(new Sublist(dataSet));
      
      foundPerfect = false;
      // loop through the dataSet
      for (k = 0; k < dataSet.size() && !foundPerfect; k++)
      {
         // initial size
         numSets = choices.size();
         for (j = 0; j < numSets && !foundPerfect; j++)
         {
            // calculate the sum of the subset + new int from dataSet
            int tempSum = choices.get(j).getSum() + dataSet.get(k).getTime();
            
            // if tempSum <= target, add the item to the subset
            if (tempSum <= TARGET)
               choices.add(choices.get(j).addItem(k));
            
            // if the tempSum turns out to be perfect
            if (tempSum == TARGET)
            {
               // change the found perfect value to true, then break;
               foundPerfect = true;
            }
         }
      }
      
      // choose the best k, kBest
      max = -1; // must be below 0, bcs sum of the subset could be zero
      kBest = 0;
      for (k = 0; k < choices.size(); k++)
      {
         // if the sum is greater than the current sum
         // notice that the sign is greater than, so the initial max hv to be -1
         if (choices.get(k).getSum() > max)
         {
            // set the new max, and choose that k
            max = choices.get(k).getSum();
            kBest = k;
         }
      }
      
      // END TIMING
      stopTime = System.nanoTime();
      
      choices.get(kBest).showSublist();
      
      // report algorithm time
      System.out.println("Algorithm Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
   }
   
   /**
    * compute the sum of the list, returns the sum
    * 
    * @param data
    * @return
    */
   static double calculateSum(ArrayList<iTunesEntry> data)
   {
      // local variables
      int k, numSet;
      double sum;
      
      // process / calculation
      numSet = data.size();
      sum = 0;
      
      for (k = 0; k < numSet; k++)
         sum += data.get(k).getTime();
      
      // return the sum
      return sum;
   }
   
   /**
    * show the entire list / vector to the console
    * 
    * @param data
    */
   static void showList(ArrayList<iTunesEntry> data)
   {
      // local var
      int k, numSet;
      
      numSet = data.size();
      
      // process
      for (k = 0; k < numSet; k++)
         System.out.println("   array[" + k + "] = " + data.get(k));
   }
}

/**
 * sublist class. represent a sublist of a set. ArrayList is implemented.
 * 
 * @author MuhammadRizky
 * 
 */
class Sublist implements Cloneable
{
   // private data of the sublist
   private int sum = 0;
   private ArrayList<iTunesEntry> originalObjects;
   private ArrayList<Integer> indices;
   
   // constructor creates an empty Sublist (no indices)
   public Sublist(ArrayList<iTunesEntry> orig)
   {
      sum = 0;
      originalObjects = orig;
      indices = new ArrayList<Integer>();
   }
   
   int getSum()
   {
      return sum;
   }
   
   // I have done the clone() for you, since you will need clone() inside
   // addItem().
   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      Sublist newObject = (Sublist) super.clone();
      // deep copy
      newObject.indices = (ArrayList<Integer>) indices.clone();
      
      return newObject;
   }
   
   /**
    * adding item to the new sublist, return the new sublist
    * 
    * @param indexOfItemToAdd
    * @return
    */
   Sublist addItem(int indexOfItemToAdd)
   {
      Sublist newSubList;
      
      // try catch clause just in case Clone Exception is thrown
      try
      {
         // create a new list then clone the sublist to the new list
         newSubList = (Sublist) this.clone();
         
         // add the new item to the cloned sublist
         newSubList.indices.add(indexOfItemToAdd);
         
         // change the sum of the new sublist
         newSubList.sum += originalObjects.get(indexOfItemToAdd).getTime();
         
         // return the new sublist
         return newSubList;
      }
      catch (CloneNotSupportedException e)
      {
         // just return null (i guess)
         return null;
      }
   }
   
   /**
    * show the element of the sublist. for output purpose.
    */
   void showSublist()
   {
      int k;
      
      System.out.println("====SUBLIST====");
      System.out.println(" SUM = " + this.sum);
      // loop through all item in the sublist and print it
      for (k = 0; k < this.indices.size(); k++)
         System.out.println("  array[" + indices.get(k) + "] = "
               + originalObjects.get(indices.get(k)));
   }
};

/*-------------------------------Test Run---------------------------------------

Target time: 1000
====SUBLIST====
 SUM = 1000
  array[3] = Foo Fighters | All My Life |  4:23
  array[5] = Eric Clapton | Pretending |  4:43
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[14] = Janiva Magness | You Were Never Mine |  4:36
Algorithm Elapsed Time: 0.0108 seconds.

Target time: 2000
====SUBLIST====
 SUM = 2000
  array[2] = Rihanna | Russian Roulette |  3:48
  array[3] = Foo Fighters | All My Life |  4:23
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[6] = Eric Clapton | Bad Love |  5:08
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[8] = Howlin' Wolf | Well That's All Right |  2:55
  array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
  array[10] = Reverend Gary Davis | Twelve Sticks |  3:14
  array[11] = Roy Buchanan | Hot Cha |  3:28
Algorithm Elapsed Time: 0.0151 seconds.

Target time: 3000
====SUBLIST====
 SUM = 3000
  array[0] = Carrie Underwood | Cowboy Casanova |  3:56
  array[1] = Carrie Underwood | Quitter |  3:40
  array[2] = Rihanna | Russian Roulette |  3:48
  array[3] = Foo Fighters | All My Life |  4:23
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[6] = Eric Clapton | Bad Love |  5:08
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
  array[10] = Reverend Gary Davis | Twelve Sticks |  3:14
  array[11] = Roy Buchanan | Hot Cha |  3:28
  array[12] = Roy Buchanan | Green Onions |  7:23
  array[14] = Janiva Magness | You Were Never Mine |  4:36
Algorithm Elapsed Time: 0.0778 seconds.

Target time: 4000
====SUBLIST====
 SUM = 4000
  array[0] = Carrie Underwood | Cowboy Casanova |  3:56
  array[1] = Carrie Underwood | Quitter |  3:40
  array[2] = Rihanna | Russian Roulette |  3:48
  array[3] = Foo Fighters | All My Life |  4:23
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[5] = Eric Clapton | Pretending |  4:43
  array[6] = Eric Clapton | Bad Love |  5:08
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[8] = Howlin' Wolf | Well That's All Right |  2:55
  array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
  array[11] = Roy Buchanan | Hot Cha |  3:28
  array[12] = Roy Buchanan | Green Onions |  7:23
  array[13] = Janiva Magness | I'm Just a Prisoner |  3:50
  array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02
  array[17] = Snoop Dogg | That's The Homie |  5:43
  array[18] = Snoop Dogg | Gangsta Luv |  4:17
Algorithm Elapsed Time: 0.2746 seconds.

Target time: 5000
====SUBLIST====
 SUM = 5000
  array[0] = Carrie Underwood | Cowboy Casanova |  3:56
  array[2] = Rihanna | Russian Roulette |  3:48
  array[3] = Foo Fighters | All My Life |  4:23
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[5] = Eric Clapton | Pretending |  4:43
  array[6] = Eric Clapton | Bad Love |  5:08
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[8] = Howlin' Wolf | Well That's All Right |  2:55
  array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
  array[10] = Reverend Gary Davis | Twelve Sticks |  3:14
  array[11] = Roy Buchanan | Hot Cha |  3:28
  array[12] = Roy Buchanan | Green Onions |  7:23
  array[13] = Janiva Magness | I'm Just a Prisoner |  3:50
  array[15] = John Lee Hooker | Hobo Blues |  3:07
  array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02
  array[17] = Snoop Dogg | That's The Homie |  5:43
  array[18] = Snoop Dogg | Gangsta Luv |  4:17
  array[23] = Berliner Philharmoniker | Brahms: Symphony No. 1 in C Minor Op. 68
 |  13:59
Algorithm Elapsed Time: 15.2574 seconds.

 -----------------------------------------------------------------------------*/
