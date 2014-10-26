package week1_benchmarking_array_example;

// necessary library / classes
import java.util.*;
import java.text.*;
import cs_1c.*;

public class Foothill
{
   public static void main (String[] args)
   {
      // for formatting output neatly
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      // instantiate local variables
      final int ARRAY_SIZE = 20000000;
      int k;
      double avg;
      long startTime, stopTime; 
      
      // the array
      final int [] arrayOfInts = new int [ARRAY_SIZE];
      
      // START TIME -------------------------------
      startTime = System.nanoTime();
      
      for (k = 0; k < ARRAY_SIZE; k++)
         arrayOfInts[k] = (int)(Math.random()*100);

      for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
         avg += arrayOfInts[k];
      avg/=ARRAY_SIZE;

      System.out.println("Average: " + avg );
      // end timing
      stopTime = System.nanoTime();

      // report algorithm time
      System.out.println("\nElapsed time for Time for simple array "
         + tidy.format((stopTime - startTime) / 1e9)
         + " seconds.\n");
   }
}
