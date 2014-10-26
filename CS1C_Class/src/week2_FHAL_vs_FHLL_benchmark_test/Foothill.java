package week2_FHAL_vs_FHLL_benchmark_test;

import java.text.NumberFormat;
import java.util.*;
import cs_1c.*;

public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int SIZE = 2000000;
      int k;
      
      // for timing:
      long startTime, stopTime;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      System.out.println("\nList size: " + SIZE);
      
      // LinkedList
      LinkedList<Integer> listOfInts = new LinkedList<Integer>();
      // START TIME -------------------------------
      startTime = System.nanoTime();
      for (k = 0; k < SIZE; k++)
      {
         listOfInts.addFirst(-k);
         listOfInts.addLast(k);
      }
      for (k = 0; k < SIZE; k++)
      {
         listOfInts.getFirst();  listOfInts.getLast();
         listOfInts.removeFirst();
         listOfInts.removeLast();
      }
      // end timing
      stopTime = System.nanoTime();
      
      // report algorithm time
      System.out.println("\nElapsed time for LinkedList: "
         + tidy.format((stopTime - startTime) / 1e9)
         + " seconds.");

      // FHlinkedList
      FHlinkedList<Integer> fhListOfInts = new FHlinkedList<Integer>();
      // START TIME -------------------------------
      startTime = System.nanoTime();
      for (k = 0; k < SIZE; k++)
      {
         fhListOfInts.addFirst(-k);
         fhListOfInts.addLast(k);
      }
      for (k = 0; k < SIZE; k++)
      {
         fhListOfInts.getFirst();  fhListOfInts.getLast();
         fhListOfInts.removeFirst();
         fhListOfInts.removeLast();
      }
      // end timing
      stopTime = System.nanoTime();
      
      System.out.println("\nElapsed time for FHlinkedList: "
            + tidy.format((stopTime - startTime) / 1e9)
            + " seconds.");
   }
}

/* ---------------------- RUNS ------------------------------
List size: 200000

Elapsed time for LinkedList: 0.0283 seconds.

Elapsed time for FHlinkedList: 0.0278 seconds.

----------------------
List size: 2000000

Elapsed time for LinkedList: 1.4961 seconds.

Elapsed time for FHlinkedList: 0.8352 seconds.

------------------------------------------------------------ */