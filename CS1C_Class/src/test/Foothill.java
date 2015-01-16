/**
 * CS 1C Assignment 9 - Solving the Maximum Flow Problem
 * ------------------------------------------------------
 * Name : Muhammad Rizky Wellyanto
 * CWID : 20144588
 * Parts : A
 * Description : This is the ninth assignment of CS_1C
 * ML F14. This is one of the hardest assignment i've ever seen on the class.
 * This program take a graph and presents the Maximum Flow output out of it.
 * We can figure it out by analyzing and reverse engineering the Dijkstra's
 * algotrithm program to accomplish our goal. Due to the time constraint, i
 * chose to reverse engineer FHgraph.java and cannibalize it, turning it into
 * the FHflowGraph.java.
 */
package test;

import java.util.*;

import cs_1c.*;

public class Foothill
{
   public static void main(String[] args) throws Exception
   {
      double finalFlow;
      
      // build graph
      FHflowGraph<String> myG = new FHflowGraph<String>();
      
      myG.addEdge("s", "a", 3);
      myG.addEdge("s", "b", 2);
      myG.addEdge("a", "b", 1);
      myG.addEdge("a", "c", 3);
      myG.addEdge("a", "d", 4);
      myG.addEdge("b", "d", 2);
      myG.addEdge("c", "t", 2);
      myG.addEdge("d", "t", 3);
      
      // show the original flow graph
      myG.showResAdjTable();
      myG.showFlowAdjTable();
      
      myG.setStartVert("s");
      myG.setEndVert("t");
      finalFlow = myG.findMaxFlow();
      
      System.out.println("Final flow: " + finalFlow);
      
      myG.showResAdjTable();
      myG.showFlowAdjTable();
      
      
      // 2 
      myG.clear();
      myG.addEdge("s", "a", 100000);
      myG.addEdge("s", "b", 100000);
      myG.addEdge("a", "b", 1);
      myG.addEdge("a", "t", 100000);
      myG.addEdge("b", "t", 100000);
      
      // show the original flow graph
      myG.showResAdjTable();
      myG.showFlowAdjTable();
      
      myG.setStartVert("s");
      myG.setEndVert("t");
      finalFlow = myG.findMaxFlow();
      
      System.out.println("Final flow: " + finalFlow);
      
      myG.showResAdjTable();
      myG.showFlowAdjTable();
      
      // 3
      myG.clear();
      myG.addEdge("a", "c", 5);
      myG.addEdge("a", "b", 5);
      myG.addEdge("b", "d", 3);
      myG.addEdge("b", "e", 6);
      myG.addEdge("c", "e", 3);
      myG.addEdge("c", "d", 1);
      myG.addEdge("d", "z", 6);
      myG.addEdge("e", "z", 6);
      
      
      // show the original flow graph
      myG.showResAdjTable();
      myG.showFlowAdjTable();
      
      myG.setStartVert("a");
      myG.setEndVert("z");
      finalFlow = myG.findMaxFlow();
      
      System.out.println("Final flow: " + finalFlow);
      
      myG.showResAdjTable();
      myG.showFlowAdjTable();
      
      // 4
      myG.clear();
      myG.addEdge("s","a", 3); myG.addEdge("s","b", 2);
      myG.addEdge("a","b", 1); myG.addEdge("a","c", 3); myG.addEdge("a","d", 4);
      myG.addEdge("b","d", 2);
      myG.addEdge("c","t", 2);
      myG.addEdge("d","t", 3);
      
      // show the original flow graph
      myG.showResAdjTable();
      myG.showFlowAdjTable();
      
      myG.setStartVert("s");
      myG.setEndVert("t");
      finalFlow = myG.findMaxFlow();
      
      System.out.println("Final flow: " + finalFlow);
      
      myG.showResAdjTable();
      myG.showFlowAdjTable();
   }
}

/**
 * FHflow vertex, modified from the FHgraph.java.
 * 
 * @author MuhammadRizky
 *
 * @param <E>
 */
class FHflowVertex<E>
{
   public static Stack<Integer> keyStack = new Stack<Integer>();
   public static final int KEY_ON_DATA = 0, KEY_ON_DIST = 1;
   public static int keyType = KEY_ON_DATA;
   public static final double INFINITY = Double.MAX_VALUE;
   
   public HashSet< Pair<FHflowVertex<E>, Double> > flowAdjList 
      = new HashSet< Pair<FHflowVertex<E>, Double> >();
   public HashSet< Pair<FHflowVertex<E>, Double> > resAdjList 
   = new HashSet< Pair<FHflowVertex<E>, Double> >();
   
   public E data;
   public double dist;
   public FHflowVertex<E> nextInPath;
   
   public FHflowVertex( E x )
   {
      data = x;
      dist = INFINITY;
      nextInPath = null;
   }
   public FHflowVertex() { this(null); }

   public void addToFlowAdjList(FHflowVertex<E> neighbor, double cost)
   {
      flowAdjList.add( new Pair<FHflowVertex<E>, Double> (neighbor, cost) );
   }
   
   public void addToFlowAdjList(FHflowVertex<E> neighbor, int cost)
   {
      addToFlowAdjList( neighbor, (double)cost );
   }
   
   public void addToResAdjList(FHflowVertex<E> neighbor, double cost)
   {
      resAdjList.add( new Pair<FHflowVertex<E>, Double> (neighbor, cost) );
   }
   
   public void addToResAdjList(FHflowVertex<E> neighbor, int cost)
   {
      addToResAdjList( neighbor, (double)cost );
   }
   
   public boolean equals(Object rhs)
   {
      FHflowVertex<E> other = (FHflowVertex<E>)rhs;
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

   public void showFlowAdjList()
   {
      Iterator< Pair<FHflowVertex<E>, Double> > iter ;
      Pair<FHflowVertex<E>, Double> pair;

      System.out.print( "Adj List for " + data + ": ");
      for (iter = flowAdjList.iterator(); iter.hasNext(); )
      {
         pair = iter.next();
         System.out.print( pair.first.data + "(" 
            + String.format("%3.1f", pair.second)
            + ") " );
      }
      System.out.println();
   }
   
   public void showResAdjList()
   {
      Iterator< Pair<FHflowVertex<E>, Double> > iter ;
      Pair<FHflowVertex<E>, Double> pair;

      System.out.print( "Adj List for " + data + ": ");
      for (iter = resAdjList.iterator(); iter.hasNext(); )
      {
         pair = iter.next();
         System.out.println("Warning Rizky was Here");
         System.out.print( pair.first.data + "(" 
            + String.format("%3.1f", pair.second)
            + ") " );
      }
      System.out.println();
   }
   
   public static boolean setKeyType( int whichType )
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
   public static void pushKeyType() { keyStack.push(keyType); }
   public static void popKeyType() { keyType = keyStack.pop(); };
}

/**
 * The essential FHflowGraph class. Modified from Dijkstra algorithm's program.
 * This class will need FHflowVertex as supplement. 
 * 
 * @author MuhammadRizky
 *
 * @param <E>
 */
class FHflowGraph<E>
{
   protected HashSet< FHflowVertex<E> > vertexSet;
   protected FHflowVertex<E> startVert, endVert;
   
   public FHflowGraph ()
   {
      vertexSet = new HashSet< FHflowVertex<E> >();
      startVert = null;
      endVert = null;
   }
   
   public boolean setStartVert (E x)
   {
      if (x== null)
         return false;
      startVert = getVertexWithThisData(x);
      if(startVert == null)
         return false;
      return true;
   }
   
   public boolean setEndVert (E x)
   {
      if (x== null)
         return false;
      endVert = getVertexWithThisData(x);
      if(endVert == null)
         return false;
      return true;
   }
   
   public void addEdge(E source, E dest, double cost)
   {
      FHflowVertex<E> src, dst;

      src = addToVertexSet(source);
      dst = addToVertexSet(dest);

      src.addToResAdjList(dst, cost);
      dst.addToResAdjList(src, 0); // cost should be 0 initially
            
      src.addToFlowAdjList(dst, 0); // cost should be 0 initially
   }
   
   public void addEdge(E source, E dest, int cost)
   {
      addEdge(source, dest, (double)cost);
   }
   
   public FHflowVertex<E> addToVertexSet(E x)
   {
      FHflowVertex<E> retVal, vert;
      boolean successfulInsertion;
      Iterator< FHflowVertex<E> > iter;

      FHflowVertex.pushKeyType();
      FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);

      retVal = new FHflowVertex<E>(x);
      successfulInsertion = vertexSet.add(retVal);
      
      FHflowVertex.popKeyType();
      
      if ( successfulInsertion )
         return retVal;;

      for (iter = vertexSet.iterator(); iter.hasNext(); )
      {
         vert = iter.next();
         if (vert.equals(retVal))
            return vert;
      }
      
      return null;
   }
   
   
   public void showFlowAdjTable()
   {
      Iterator< FHflowVertex<E> > iter;

      System.out.println( "------------------------ ");
      for (iter = vertexSet.iterator(); iter.hasNext(); )
         (iter.next()).showFlowAdjList();
      System.out.println();
   }
   
   public void showResAdjTable()
   {
      Iterator< FHflowVertex<E> > iter;

      System.out.println( "------------------------ ");
      for (iter = vertexSet.iterator(); iter.hasNext(); )
         (iter.next()).showResAdjList();
      System.out.println();
   }
   
   public void clear()
   {
      vertexSet.clear();
      startVert = null;
      endVert = null;
   }
   
   protected FHflowVertex<E> getVertexWithThisData(E x)
   {
      FHflowVertex<E> searchVert, vert;
      Iterator< FHflowVertex<E> > iter;

      FHflowVertex.pushKeyType();
      FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);

      searchVert = new FHflowVertex<E>(x);


      for (iter = vertexSet.iterator(); iter.hasNext(); )
      {
         vert = iter.next();
         if (vert.equals(searchVert))
         {
            FHflowVertex.popKeyType();
            return vert;
         }
      }
      
      FHflowVertex.popKeyType();
      return null;
   }
   
   // essential methods for the algorithm, and it's helper methods:
   
   public double findMaxFlow()
   {
      double limittingCost, totalFlow;
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      
      if (startVert == null || endVert == null)
         return 0;
      
      while(establishNextFlowPath())
      {
         limittingCost = getLimitingFlowOnResPath();
         if(!adjustPathByCost(limittingCost))
            break;
         else if (limittingCost == 0)
            break;
      }
      
      totalFlow= 0;
      for (iter = startVert.flowAdjList.iterator(); iter.hasNext();)
      {
         totalFlow += iter.next().second;
      }
      
      return totalFlow;
   }
   
   protected boolean establishNextFlowPath()
   {
      FHflowVertex<E> vert, v, w;
      double costVW;
      Pair<FHflowVertex<E>, Double> edge;
      Iterator<FHflowVertex<E>> iter;
      Iterator<Pair<FHflowVertex<E>, Double>> edgeIter;
      
      Deque<FHflowVertex<E>> partiallyProcessedVerts = new LinkedList
            <FHflowVertex<E>>();
      
      for(iter = vertexSet.iterator(); iter.hasNext();)
      {
         vert = iter.next();
         vert.dist = FHflowVertex.INFINITY;
         vert.nextInPath = null;
      }
      
      startVert.dist = 0;
      partiallyProcessedVerts.addLast(startVert);
      
      while(!partiallyProcessedVerts.isEmpty())
      {
         v = partiallyProcessedVerts.removeFirst();
         
         for(edgeIter = v.resAdjList.iterator(); edgeIter.hasNext();)
         {
            edge = edgeIter.next();
            w = edge.first;
            costVW = edge.second;
            
            if (costVW <= 0)
               continue;
            
            if(v.dist + costVW < w.dist)
            {
               w.dist = v.dist + costVW;
               w.nextInPath = v;
               
               if(w == endVert)
                  return true;
               
               partiallyProcessedVerts.addLast(w);
            }
         }
      }
      return false;
   }
   
   protected double getLimitingFlowOnResPath()
   {
      FHflowVertex<E> vert;
      double limittingFlow, edgeCost;

      if (startVert == null || endVert == null)
         return 0;
      
      limittingFlow = FHflowVertex.INFINITY;
      for (vert = endVert; vert != startVert; vert = vert.nextInPath)
      {
         if ( vert.nextInPath == null )
            return 0;
         edgeCost = getCostOfResEdge(vert.nextInPath, vert);
         if (edgeCost < limittingFlow)
            limittingFlow = edgeCost;
      }

      return limittingFlow;
   }

   protected double getCostOfResEdge(FHflowVertex<E> src, FHflowVertex<E> dst)
   {
      Iterator< Pair <FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
      
      if(src == null || dst == null)
         return 0;
      
      for (iter = src.resAdjList.iterator();iter.hasNext();)
      {
         pair = iter.next();
         
         if (pair.first == dst)
            return pair.second;
      }
      return 0;
   }
   
   protected boolean adjustPathByCost(double cost)
   {
      FHflowVertex<E> vert;
      
      vert = endVert;
      
      while (vert != startVert)
      {
         if (vert.nextInPath == null)
            return false;
         
         if (!addCostToFlowEdge(vert.nextInPath, vert, cost))
            return false;
         if (!addCostToResEdge(vert,vert.nextInPath,cost))
            return false;
         if(!addCostToResEdge(vert.nextInPath,vert, -cost))
            return false;
         
         vert = vert.nextInPath;
      }
      
      return true;
   }
   
   protected boolean addCostToFlowEdge(FHflowVertex<E> src, FHflowVertex<E> dst,
         double cost)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair <FHflowVertex<E>, Double> pair;
      
      if (src== null|| dst == null)
         return false;
      
      for (iter = src.flowAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         
         if (pair.first == dst)
         {
            pair.second += cost;
            return true;
            
         }
      }
      
      for (iter = dst.flowAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         
         if (pair.first == src)
         {
            pair.second -= cost;
            return true;
            
         }
      }
      
      return false;
   }
   
   protected boolean addCostToResEdge(FHflowVertex<E> src, FHflowVertex<E> dst,
         double cost)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair <FHflowVertex<E>, Double> pair;
      
      if (src== null|| dst == null)
         return false;
      
      for (iter = src.resAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         
         if (pair.first == dst)
         {
            pair.second += cost;
            return true;
            
         }
      }
               
      return false;   
   }
   
}
