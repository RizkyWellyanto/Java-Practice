package week9_flexible_ordering_technique;

import cs_1c.*;

import java.util.*;

class Star implements Comparable<Star>
{
   // comparable tools
   public static final int SORT_BY_NAME = 0;
   public static final int SORT_BY_MAG = 1;
   
   private static int sortKey = SORT_BY_NAME;

   public String name;
   public double mag;
   Star(String n, double m){ name = n; mag = m; }
   
   void show() 
   { 
      System.out.println( name + " " + mag);
   } 
   
   public static boolean setSortType( int whichType )
   {
      switch (whichType)
      {
      case SORT_BY_NAME: case SORT_BY_MAG:
         sortKey = whichType;
         return true;
      default:
         return false;
      }
   }
   
   public int compareTo(Star other)
   {
      switch (sortKey)
      {
      case SORT_BY_NAME:
         return (name.compareToIgnoreCase(other.name));
      case SORT_BY_MAG:
         return (int)( 1000 * (mag - other.mag) );
      default:
         return 0;
      }
   }
};