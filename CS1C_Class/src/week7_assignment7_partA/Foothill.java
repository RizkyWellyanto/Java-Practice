package week7_assignment7_partA;

import java.util.*;
import cs_1c.*;
import java.text.*;

/**
 * CS 1C Assignment 7 - ShellSort
 * ------------------------------------------------------
 * Name : Muhammad Rizky Wellyanto
 * CWID : 20144588
 * Parts : A
 * Description : This is the seventh assignment of CS_1C
 * ML F14. This assignment test the differece of Gap Sequences of the
 * ShellSort Algorithm. I found a couple Gap Sequences defined on the Intrnet
 * such as Knuth's, Sedgewick's, Pratt's, and demonstrate it, while also
 * analyzing it.
 */

public class Foothill
{
   /**
    * the shell sort function of the program.
    * 
    * @param data
    * @param seq
    */
   public static <E extends Comparable<? super E>> void shellSortX(E[] data,
         int[] seq)
   {
      int gap, i, k, pos;
      E tmp;
      
      for (i = seq.length - 1; i >= 0; i--)
      {
         gap = seq[i];
         
         for (pos = gap; pos < data.length; pos++)
         {
            tmp = data[pos];
            for (k = pos; k >= gap && tmp.compareTo(data[k - gap]) < 0; k 
                  -= gap)
               data[k] = data[k - gap];
            data[k] = tmp;
         }
      }
   }
   
   public static void main(String[] args) throws Exception
   {
      final int ARRAY_SIZE = 1600000;
      
      int i, k, rdm;
      long startTime, stopTime;
      
      Integer[] arrayOfInts0 = new Integer[ARRAY_SIZE];
      Integer[] arrayOfInts1 = new Integer[ARRAY_SIZE];
      Integer[] arrayOfInts2 = new Integer[ARRAY_SIZE];
      Integer[] arrayOfInts3 = new Integer[ARRAY_SIZE];
      Integer[] arrayOfInts4 = new Integer[ARRAY_SIZE];
      Integer[] arrayOfInts5 = new Integer[ARRAY_SIZE];
      
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      int[] gapArray =
         { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192,
               16384, 32768, 65536, 131072, 262144, 524288, 1048576 };
      int[] sedgewickArray = new int[30];
      int[] knuthArray = new int[30];
      int[] hibbardArray = new int[30];
      
      // got this from the internet. no need to calculate
      int[] prattArray =
         { 1, 2, 3, 4, 6, 8, 9, 12, 16, 18, 24, 27, 32, 36, 48, 54, 64, 72, 81,
               96, 108, 128, 144, 162, 192, 216, 243, 256, 288, 324 }; 
      
      for (i = 0; i < ARRAY_SIZE; i++)
      {
         rdm = (int) (Math.random() * ARRAY_SIZE);
         arrayOfInts0[i] = rdm;
         arrayOfInts1[i] = rdm;
         arrayOfInts2[i] = rdm;
         arrayOfInts3[i] = rdm;
         arrayOfInts4[i] = rdm;
         arrayOfInts5[i] = rdm;
      }
      
      // fill sedgewick
      for (i = 0; i < 15; i++)
      {
         sedgewickArray[i * 2] = (int) (9 * (Math.pow(4, i) 
               - Math.pow(2, i)) + 1);
      }
      
      for (i = 2; i < 30; i++)
      {
         if (2 * i - 3 > 30)
            break;
         sedgewickArray[2 * i - 3] = (int) (Math.pow(4, i)
               - (3 * Math.pow(2, i)) + 1);
      }
      
      // fill hibbard
      for (i = 0; i < 30; i++)
      {
         hibbardArray[i] = (int) (Math.pow(2, i));
      }
      
      // fill knuth
      for (i = 0, k = 1; i < 30; i++)
      {
         knuthArray[i] = k;
         k = 3 * k + 1;
         if (k < 0)
            break;
      }
      
      // fill pratt
      
      
      // sorting and benchmarking
      System.out.println("SHELL SORT");
      System.out.println("---------------------------------------------------");
      System.out.println("Array Size : " + ARRAY_SIZE);
      System.out.println("---------------------------------------------------");
      System.out.println("Gap Sequence               ||      Time");
      System.out.println("---------------------------------------------------");
      
      startTime = System.nanoTime();  // ------------------ start
      FHsort.shellSort1(arrayOfInts0);  // time this
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("FH shellSort1              ||      "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
      
      startTime = System.nanoTime();  // ------------------ start
      shellSortX(arrayOfInts1, gapArray);  // time this
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("shellsortX + gapArray      ||      "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
      
      startTime = System.nanoTime();  // ------------------ start
      shellSortX(arrayOfInts2, sedgewickArray);  // time this
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("Sedgewick                  ||      "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
      
      startTime = System.nanoTime();  // ------------------ start
      shellSortX(arrayOfInts3, hibbardArray);
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("Hibbard                    ||      "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
      
      startTime = System.nanoTime();  // ------------------ start
      shellSortX(arrayOfInts4, knuthArray);
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("Knuth                      ||      "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
      
      startTime = System.nanoTime();  // ------------------ start
      shellSortX(arrayOfInts5, prattArray);
      stopTime = System.nanoTime();    // ---------------------- stop
      System.out.println("Pratt                      ||      "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
      
      /* // testing whether the array is sorted properly
      for (i = 0; i < ARRAY_SIZE-1; i++)
      {
         if (arrayOfInts0[i] > arrayOfInts1[i+1])
            System.out.println("Error @ arrayOfInts0. Index:" + i);
         if (arrayOfInts1[i] > arrayOfInts1[i+1])
            System.out.println("Error @ arrayOfInts1. Index:" + i);
         if (arrayOfInts2[i] > arrayOfInts2[i+1])
            System.out.println("Error @ arrayOfInts2. Index:" + i);
         if (arrayOfInts3[i] > arrayOfInts3[i+1])
            System.out.println("Error @ arrayOfInts3. Index:" + i);
         if (arrayOfInts4[i] > arrayOfInts4[i+1])
            System.out.println("Error @ arrayOfInts4. Index:" + i);
         if (arrayOfInts5[i] > arrayOfInts5[i+1])
            System.out.println("Error @ arrayOfInts5. Index:" + i);
      }*/
   }
}

/*------------------------ QUESTION / ANSWERS ----------------------------------

QUESTIONS:
1. Why does Shell's gap sequence implied by shellSort1() give a different 
   timing result than the explicit array described above and passed to 
   shellSortX()?  
2. Which is faster and why?

ANSWERS:
1. It's different because both have different characteristic regarding on the
   time complexity. The shellSortX() which uses explicit gap array of 2^(N)
   always give us the worst case scenario (all of the numbers are even, except
   one). The worst case which is O(N^(2)). On the shellSort1(), it doesn't use
   explicit gap sequence, it uses implicit gap, which depends on the size of N.
   It doesn't always give us the worst case scenario, but it might if the array
   size is 2^(n).
2. shellSort1() is faster. because of the time complexity difference that i
   explained on number 1. shellSort1() doesn't always give the worst case time
   complexity (and the time complexity is not tight), whereas the shellSortX() 
   give the worst case (and it's pretty tight). moreover, shellSortX() use more
   variables than shellSort1() that could make shellSortX() relatively slower
   compared to shellSort1(). 

------------------------------------------------------------------------------*/

/*---------------------------- SAMPLE RUN --------------------------------------

SHELL SORT
---------------------------------------------------
Array Size : 10000
---------------------------------------------------
Gap Sequence               ||      Time
---------------------------------------------------
FH shellSort1              ||      0.0377 seconds.
shellsortX + gapArray      ||      0.0432 seconds.
Sedgewick                  ||      0.0034 seconds.
Hibbard                    ||      0.0069 seconds.
Knuth                      ||      0.0036 seconds.
Pratt                      ||      0.0063 seconds.

SHELL SORT
---------------------------------------------------
Array Size : 20000
---------------------------------------------------
Gap Sequence               ||      Time
---------------------------------------------------
FH shellSort1              ||      0.0391 seconds.
shellsortX + gapArray      ||      0.0472 seconds.
Sedgewick                  ||      0.0088 seconds.
Hibbard                    ||      0.0152 seconds.
Knuth                      ||      0.0071 seconds.
Pratt                      ||      0.0132 seconds.

SHELL SORT
---------------------------------------------------
Array Size : 50000
---------------------------------------------------
Gap Sequence               ||      Time
---------------------------------------------------
FH shellSort1              ||      0.0892 seconds.
shellsortX + gapArray      ||      0.1004 seconds.
Sedgewick                  ||      0.0355 seconds.
Hibbard                    ||      0.0685 seconds.
Knuth                      ||      0.0383 seconds.
Pratt                      ||      0.0526 seconds.

SHELL SORT
---------------------------------------------------
Array Size : 100000
---------------------------------------------------
Gap Sequence               ||      Time
---------------------------------------------------
FH shellSort1              ||      0.1865 seconds.
shellsortX + gapArray      ||      0.2245 seconds.
Sedgewick                  ||      0.0818 seconds.
Hibbard                    ||      0.1492 seconds.
Knuth                      ||      0.0916 seconds.
Pratt                      ||      0.3196 seconds.

SHELL SORT
---------------------------------------------------
Array Size : 200000
---------------------------------------------------
Gap Sequence               ||      Time
---------------------------------------------------
FH shellSort1              ||      0.2736 seconds.
shellsortX + gapArray      ||      0.4783 seconds.
Sedgewick                  ||      0.1688 seconds.
Hibbard                    ||      0.336 seconds.
Knuth                      ||      0.2151 seconds.
Pratt                      ||      0.8241 seconds.

SHELL SORT
---------------------------------------------------
Array Size : 400000
---------------------------------------------------
Gap Sequence               ||      Time
---------------------------------------------------
FH shellSort1              ||      0.6925 seconds.
shellsortX + gapArray      ||      1.3791 seconds.
Sedgewick                  ||      0.3649 seconds.
Hibbard                    ||      0.9472 seconds.
Knuth                      ||      0.4682 seconds.
Pratt                      ||      3.1975 seconds.

SHELL SORT
---------------------------------------------------
Array Size : 1600000
---------------------------------------------------
Gap Sequence               ||      Time
---------------------------------------------------
FH shellSort1              ||      2.9709 seconds.
shellsortX + gapArray      ||      9.0492 seconds.
Sedgewick                  ||      1.5813 seconds.
Hibbard                    ||      8.9441 seconds.
Knuth                      ||      2.1464 seconds.
Pratt                      ||      47.3613 seconds.

------------------------------------------------------------------------------*/