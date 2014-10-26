package week3_star_near_earth_output;

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int k, arraySize;
      // ead the data from files
      StarNearEarthReader  starInput 
         = new StarNearEarthReader("nearest_stars.txt");

      if (starInput.readError())
      {
         System.out.println("couldn't open " + starInput.getFileName()
            + " for input.");
         return;
      }

      System.out.println(starInput.getFileName());
      System.out.println(starInput.getNumStars());

      // create an array of objects for our own use:
      arraySize = starInput.getNumStars();
      StarNearEarth [] starArray  = new StarNearEarth[arraySize];
      for (k = 0; k < arraySize; k++)
         starArray[k] = starInput.getStar(k);

      // display cartesion coords
      for ( k = 0; k < arraySize; k++ )
         System.out.println( starArray[k].getNameCommon() + " " 
            + starArray[k].getParallaxMean());
   }
}