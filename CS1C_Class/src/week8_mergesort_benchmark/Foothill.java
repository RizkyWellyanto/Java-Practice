package week8_mergesort_benchmark;

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      final int ARRAY_SIZE = 640000;
      int k, randomInt;
      long startTime, stopTime; 
      // for formatting output neatly
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      Integer[] arrayOfInts1 = new Integer[ARRAY_SIZE];
      Integer[] arrayOfInts2 = new Integer[ARRAY_SIZE];

      System.out.println("Array size: " + ARRAY_SIZE);
      
      // build two arrays for comparing sorts
      for (k = 0; k < ARRAY_SIZE; k++)
      {
         randomInt = (int) (Math.random() * ARRAY_SIZE);
         arrayOfInts1[k] = randomInt;
         arrayOfInts2[k] = randomInt;
      }
      startTime = System.nanoTime();  // ------------------ start 
      // sort using shellSort()
      FHsort.shellSort1(arrayOfInts1);
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("shellSort Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");

      startTime = System.nanoTime();  // ------------------ start 
      // sort using mergesort()
      FHsort.mergeSort(arrayOfInts2);
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("mergeSort Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");
      
      /*
      for (k = 0; k < ARRAY_SIZE; k+= ARRAY_SIZE/20)
      {
         System.out.println( "shell #" + k + ": " + arrayOfInts1[k] + ", ");
         System.out.println( "merge #" + k + ": " + arrayOfInts2[k] );
      }
      */
   }
}