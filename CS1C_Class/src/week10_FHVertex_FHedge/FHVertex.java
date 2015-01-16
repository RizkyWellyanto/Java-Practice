package week10_FHVertex_FHedge;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import cs_1c.Pair;

// --- FHvertex class ------------------------------------------------------
class FHvertex<E>
{
   public static Stack<Integer> keyStack = new Stack<Integer>();
   public static final int KEY_ON_DATA = 0, KEY_ON_DIST = 1;
   public static int keyType = KEY_ON_DATA;
   public static final double INFINITY = Double.MAX_VALUE;
   public HashSet<Pair<FHvertex<E>, Double>> adjList = new HashSet<Pair<FHvertex<E>, Double>>();
   public E data;
   public double dist;
   public FHvertex<E> nextInPath;  // for client-specific info
   
   public FHvertex(E x)
   {
      data = x;
      dist = INFINITY;
      nextInPath = null;
   }
   
   public FHvertex()
   {
      this(null);
   }
   
   public void addToAdjList(FHvertex<E> neighbor, double cost)
   {
      adjList.add(new Pair<FHvertex<E>, Double>(neighbor, cost));
   }
   
   public void addToAdjList(FHvertex<E> neighbor, int cost)
   {
      addToAdjList(neighbor, (double) cost);
   }
   
   public boolean equals(Object rhs)
   {
      FHvertex<E> other = (FHvertex<E>) rhs;
      switch (keyType)
      {
         case KEY_ON_DIST:
            return (dist == other.dist);
         case KEY_ON_DATA:
            return (data.equals(other.data));
         default:
            return (data.equals(other.data));
      }
   }
   
   public int hashCode()
   {
      switch (keyType)
      {
         case KEY_ON_DIST:
            Double d = dist;
            return (d.hashCode());
         case KEY_ON_DATA:
            return (data.hashCode());
         default:
            return (data.hashCode());
      }
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      FHvertex<E> new_object = (FHvertex<E>) super.clone();
      new_object.adjList = (HashSet<Pair<FHvertex<E>, Double>>) adjList.clone();
      return new_object;
   }
   
   public void showAdjList()
   {
      Iterator<Pair<FHvertex<E>, Double>> iter;
      Pair<FHvertex<E>, Double> pair;
      
      System.out.print("Adj List for " + data + ": ");
      for (iter = adjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         System.out.print(pair.first.data + "("
               + String.format("%3.1f", pair.second) + ") ");
      }
      System.out.println();
   }
   
   public static boolean setKeyType(int whichType)
   {
      switch (whichType)
      {
         case KEY_ON_DATA:
         case KEY_ON_DIST:
            keyType = whichType;
            return true;
         default:
            return false;
      }
   }
   
   public static void pushKeyType()
   {
      keyStack.push(keyType);
   }
   
   public static void popKeyType()
   {
      keyType = keyStack.pop();
   }
}
