package week4_tree_search_example;

//Main file for EBookEntry project.  See Read Me file for details
//CS !C, Foothill College, Michael Loceff, creator

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class Foothill
{
// -------  main --------------
public static void main(String[] args) throws Exception
{
   // how we read the data from files
   EBookEntryReader bookInput = new EBookEntryReader("catalog-short4.txt");
   int arraySize;

   // how we test the success of the read:
   if (bookInput.readError())
   {
      System.out.println("couldn't open " + bookInput.getFileName()
         + " for input.");
      return;
   }

   System.out.println(bookInput.getFileName());
   System.out.println(bookInput.getNumBooks());

   // create an array of objects for our own use:
   arraySize = bookInput.getNumBooks();
   EBookEntry[] bookArray = new EBookEntry[arraySize];
   for (int k = 0; k < arraySize; k++)
      bookArray[k] = bookInput.getBook(k);

   // how we time our algorithms -------------------------
   Date startTime, stopTime;
   NumberFormat tidy = NumberFormat.getInstance(Locale.US);
   tidy.setMaximumFractionDigits(4);
   
   // display first 5 books (only) unsorted
   for (int k = 0; k < 5; k++)
      System.out.print(bookArray[k]);
   
   //get start time
   startTime = new Date();

   // sort
   EBookEntry.setSortType(EBookEntry.SORT_BY_TITLE);
   System.out.println( " Sorting ...\n" );
   FoothillSort.arraySort(bookArray);
   System.out.println( " Sorted!\n" );
   // how we determine the time elapsed -------------------
   stopTime = new Date();

   // display first 40 books (only) sorted
   for (int k = 0; k < 40; k++)
      System.out.print(bookArray[k]);

   // report algorithm time
   System.out.println("Algorithm Elapsed Time: "
      + tidy.format((stopTime.getTime() - startTime.getTime()) / 1000.)
      + " seconds.");
}
}
