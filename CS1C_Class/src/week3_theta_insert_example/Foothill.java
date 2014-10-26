package week3_theta_insert_example;

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int k, arraySize, numInsertions, attempt, writePosition;
      double elapsedTime;
      Integer[] arrayOfInts;
      int[] somePositions = {3, 67, 20, 15, 59  };
      int numPositions = somePositions.length;

      // to time the algorithm -------------------------
      long startTime, stopTime;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      elapsedTime = 0;
      for (arraySize = 100, numInsertions = 50; elapsedTime < 10; 
         arraySize*=2, numInsertions*=3)
      {
         // allocate array and stuff will values
         arrayOfInts = new Integer[arraySize + 1];
         for (k = 0; k < arraySize; k++)
            arrayOfInts[k] = (int)(Math.random() * arraySize);

         startTime = System.nanoTime();

         // we will do numInsertions insertions, throwing away tunes that run off the top
         for (attempt = 0; attempt < numInsertions; attempt++)
         {
            writePosition = somePositions[ attempt % numPositions ];

            // move everything up one 
            for (k = arraySize; k > writePosition; k--)
               arrayOfInts[k] = arrayOfInts[k-1];
            // now put a new number into the free position
            arrayOfInts[writePosition] = 51;
         }

         stopTime = System.nanoTime();
         elapsedTime = (stopTime - startTime) / 1e9;
         
         System.out.println("N: " + arraySize
            + " M: " + numInsertions
            + " Insertion time: "
            + tidy.format(elapsedTime)
            + " seconds.");
      } 
   }
}

/* ----------------- RUN --------------------------

N: 100 M: 50 Insertion time: 0.0001 seconds.
N: 200 M: 150 Insertion time: 0.0022 seconds.
N: 400 M: 450 Insertion time: 0.001 seconds.
N: 800 M: 1350 Insertion time: 0.0062 seconds.
N: 1600 M: 4050 Insertion time: 0.0377 seconds.
N: 3200 M: 12150 Insertion time: 0.2228 seconds.
N: 6400 M: 36450 Insertion time: 1.3076 seconds.
N: 12800 M: 109350 Insertion time: 7.9108 seconds.
N: 25600 M: 328050 Insertion time: 47.4575 seconds.

----------------------------------------------- */


/* ----------------- RUN --------------------------

N: 100 M: 50 Insertion time: 0.0001 seconds.
N: 200 M: 150 Insertion time: 0.0005 seconds.
N: 400 M: 450 Insertion time: 0.0029 seconds.
N: 800 M: 1350 Insertion time: 0.0139 seconds.
N: 1600 M: 4050 Insertion time: 0.0068 seconds.
N: 3200 M: 12150 Insertion time: 0.0415 seconds.
N: 6400 M: 36450 Insertion time: 0.2489 seconds.
N: 12800 M: 109350 Insertion time: 1.4894 seconds.
N: 25600 M: 328050 Insertion time: 9.2591 seconds.
N: 51200 M: 984150 Insertion time: 53.5858 seconds.

----------------------------------------------- */