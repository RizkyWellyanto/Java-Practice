package week9_heapsort_vs_quicksort_benchmark;

//Comparison of heapSort and quickSort
import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class Foothill
{
// -------  main --------------
public static void main(String[] args) throws Exception
{
   final int ARRAY_SIZE = 400000;
   int k, randomInt;
   long startTime, stopTime; 
   // for formatting output neatly
   NumberFormat tidy = NumberFormat.getInstance(Locale.US);
   tidy.setMaximumFractionDigits(4);

   Integer[] arrayOfInts1 = new Integer[ARRAY_SIZE];
   Integer[] arrayOfInts2 = new Integer[ARRAY_SIZE]; 

   // build two arrays for comparing sorts
   for (k = 0; k < ARRAY_SIZE; k++)
   {
      randomInt = (int) (Math.random() * ARRAY_SIZE);
      arrayOfInts1[k] = randomInt;
      arrayOfInts2[k] = randomInt;
   }

   startTime = System.nanoTime();  // ------------------ start 
   FHsort.heapSort(arrayOfInts1);
   stopTime = System.nanoTime();    // ---------------------- stop
   System.out.println("heapSort Elapsed Time: "
         + tidy.format( (stopTime - startTime) / 1e9)
         + " seconds.");

   startTime = System.nanoTime();  // ------------------ start 
   FHsort.quickSort(arrayOfInts2);
   stopTime = System.nanoTime();    // ---------------------- stop
   System.out.println("quickSort Elapsed Time: "
         + tidy.format( (stopTime - startTime) / 1e9)
         + " seconds.");
   
   for (k = 0; k < ARRAY_SIZE; k+= ARRAY_SIZE/5)
   {
      System.out.print( "heapSort #" + k + ": " + arrayOfInts1[k] + ", ");
      System.out.println( "quickSort #" + k + ": " + arrayOfInts2[k] );
   }
}
}