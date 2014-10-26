package week2_FHArrayList_benchmark_example;

//Main file for FHarrayList vs. ArrayList
//CS 1C, Foothill College, Michael Loceff, creator

import java.util.*;
import java.text.*;
import cs_1c.*;

//------------------------------------------------------
public class Foothill
{
// -------  main --------------
public static void main(String[] args) throws Exception
{
   int ARRAY_SIZE = 10000000;
   int SMALL_ARRAY_SIZE = 10000;
   int k, mid, last;
   double avg;
   
   // for timing:
   long startTime, stopTime;
   NumberFormat tidy = NumberFormat.getInstance(Locale.US);
   tidy.setMaximumFractionDigits(4);

   // ArrayList using add() / get()
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
   System.out.println("\nElapsed time for ArrayList add/get "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");
   
   // FHarrayList using add() / get()
   FHarrayList<Integer> FHlistOfInts = new FHarrayList<Integer>(ARRAY_SIZE);
   
   // START TIME -------------------------------
   startTime = System.nanoTime();
   for (k = 0; k < ARRAY_SIZE; k++)
      FHlistOfInts.add( (int)(Math.random()*100) );
   
   for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
      avg += FHlistOfInts.get(k);
   avg/=ARRAY_SIZE;

   System.out.println("Average: " + avg );
   // end timing
   stopTime = System.nanoTime();

   // report algorithm time
   System.out.println("\nElapsed time for FHarrayList add/get "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");
   
   // ArrayList using set() / get()
   // START TIME -------------------------------
   startTime = System.nanoTime();
   for (k = 0; k < ARRAY_SIZE; k++)
      listOfInts.set(k, (int)(Math.random()*100) );
   
   for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
      avg += listOfInts.get(k);
   avg/=ARRAY_SIZE;

   System.out.println("Average: " + avg );
   // end timing
   stopTime = System.nanoTime();

   // report algorithm time
   System.out.println("\nElapsed time for ArrayList set/get "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");

   // FHarrayList using set() / get()
   
   // START TIME -------------------------------
   startTime = System.nanoTime();
   for (k = 0; k < ARRAY_SIZE; k++)
      FHlistOfInts.set(k, (int)(Math.random()*100) );
   
   for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
      avg += FHlistOfInts.get(k);
   avg/=ARRAY_SIZE;

   System.out.println("Average: " + avg );
   // end timing
   stopTime = System.nanoTime();

   // report algorithm time
   System.out.println("\nElapsed time for FHarrayList set/get "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");
   
   // ArrayList using remove( size - 1 )
   
   // START TIME -------------------------------
   startTime = System.nanoTime();
   
   while ( ( last = listOfInts.size() ) > SMALL_ARRAY_SIZE )
      listOfInts.remove( last - 1 );
   
   // end timing
   stopTime = System.nanoTime();
   // report algorithm time
   System.out.println("\nElapsed time for ArrayList remove(size) "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");
   
   // ArrayList using remove( size - 1 )
   
   // START TIME -------------------------------
   startTime = System.nanoTime();
   
   while ( ( last = FHlistOfInts.size() ) > SMALL_ARRAY_SIZE )
      FHlistOfInts.remove( last - 1 );
   
   // end timing
   stopTime = System.nanoTime();
   // report algorithm time
   System.out.println("\nElapsed time for FHarrayList remove(size) "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");
   
   // ArrayList using remove( size/2 )
   
   // START TIME -------------------------------
   startTime = System.nanoTime();
   
   while ( ( mid = listOfInts.size()/2 ) > 0)
      listOfInts.remove( mid );

   System.out.println("Average: " + avg );
   // end timing
   stopTime = System.nanoTime();

   // report algorithm time
   System.out.println("\nElapsed time for ArrayList remove(size/2) "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");

   // FHarrayList using remove( size/2 )
   
   // START TIME -------------------------------
   startTime = System.nanoTime();
   
   while ( ( mid = FHlistOfInts.size()/2 ) > 0)
      FHlistOfInts.remove( mid );

   System.out.println("Average: " + avg );
   // end timing
   stopTime = System.nanoTime();

   // report algorithm time
   System.out.println("\nElapsed time for FHarrayList remove(size/2) "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");
}
}

/* --- run 10,000,000 add/set/get, 10,000 remove(size/2) --------

Average: 49.5147923

Elapsed time for ArrayList add/get 0.2554 seconds.

Average: 49.5128034

Elapsed time for FHarrayList add/get 0.252 seconds.

Average: 49.4910738

Elapsed time for ArrayList set/get 0.2459 seconds.

Average: 49.4891074

Elapsed time for FHarrayList set/get 0.2456 seconds.


Elapsed time for ArrayList remove(size) 0.0189 seconds.


Elapsed time for FHarrayList remove(size) 0.0255 seconds.

Average: 49.4891074

Elapsed time for ArrayList remove(size/2) 0.0033 seconds.

Average: 49.4891074

Elapsed time for FHarrayList remove(size/2) 0.0033 seconds.



---------------------------------------------------------- */