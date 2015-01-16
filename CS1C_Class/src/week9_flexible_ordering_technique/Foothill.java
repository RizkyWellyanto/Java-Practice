package week9_flexible_ordering_technique;

import cs_1c.*;
import java.util.*;


//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      PriorityQueue<Star> milkyWay = new PriorityQueue<Star>();
      
      Star
         sir = new Star("sirius", -1.42), 
         can = new Star("canopus", -0.72), 
         arc = new Star("archerner", 0.45),
         sun = new Star("sun", -26.7);

      System.out.println( "------- unordered, before pri_q ---- ");
      sir.show();  can.show(); arc.show(); sun.show();
      
      System.out.println( "Queing stars by name ----");
      Star.setSortType(Star.SORT_BY_NAME);
      milkyWay.add(sir); 
      milkyWay.add(can); 
      milkyWay.add(arc); 
      milkyWay.add(sun);
      while ( !milkyWay.isEmpty() )
      {
         System.out.println( milkyWay.peek().name + " " 
               + milkyWay.peek().mag );
         milkyWay.remove();
      }

      System.out.println( "Queing stars by mag ----");
      Star.setSortType(Star.SORT_BY_MAG);
      milkyWay.add(sir); 
      milkyWay.add(can); 
      milkyWay.add(arc); 
      milkyWay.add(sun);
      while ( !milkyWay.isEmpty() )
      {
         System.out.println( milkyWay.peek().name + " " 
               + milkyWay.peek().mag );
         milkyWay.remove();
      }
   }
}