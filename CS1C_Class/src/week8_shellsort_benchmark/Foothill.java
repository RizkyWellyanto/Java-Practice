package week8_shellsort_benchmark;

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      final int ARRAY_SIZE = 50000;
      int k, randomInt;
      long startTime, stopTime; 
      // for formatting output neatly
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      Integer[] arrayOfInts1 = new Integer[ARRAY_SIZE];
      Integer[] arrayOfInts2 = new Integer[ARRAY_SIZE];
      Integer[] arrayOfInts3 = new Integer[ARRAY_SIZE];

      // build three arrays for comparing sorts
      for (k = 0; k < ARRAY_SIZE; k++)
      {
         randomInt = (int) (Math.random() * ARRAY_SIZE);
         arrayOfInts1[k] = randomInt;
         arrayOfInts2[k] = randomInt;
         arrayOfInts3[k] = randomInt;
      }
      startTime = System.nanoTime();  // ------------------ start 
      FHsort.insertionSort(arrayOfInts1);
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("insertionSort Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");

      startTime = System.nanoTime();  // ------------------ start   
      FHsort.shellSort1(arrayOfInts2);
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("shellSort Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");
      
      startTime = System.nanoTime();  // ------------------ start   
      Arrays.sort(arrayOfInts3);
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("Arrays.sort()  Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");

      /* for (k = 0; k < ARRAY_SIZE; k+= ARRAY_SIZE/10)
      {
         System.out.println( "bubble #" + k + ": " + arrayOfInts1[k] + ", ");
         System.out.println( "heap #" + k + ": " + arrayOfInts1[k] );
      } */
   }
}