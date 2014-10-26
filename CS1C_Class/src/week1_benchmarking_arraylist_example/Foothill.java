package week1_benchmarking_arraylist_example;

//necessary library / classes
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
      
      // the list
      ArrayList<Integer> listOfInts = new ArrayList<Integer>(ARRAY_SIZE);
      
   // START TIME -------------------------------
      startTime = System.nanoTime();
      for (k = 0; k < ARRAY_SIZE; k++)
         listOfInts.add( (int)(Math.random()*100) );
      
      for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
         avg += listOfInts.get(k);
      avg/=ARRAY_SIZE;

      System.out.println("Average: " + avg );
      // end timing
      stopTime = System.nanoTime();

      // report algorithm time
      System.out.println("\nElapsed time for Time for ArrayList add/get "
         + tidy.format((stopTime - startTime) / 1e9)
         + " seconds.\n");
   }
}
