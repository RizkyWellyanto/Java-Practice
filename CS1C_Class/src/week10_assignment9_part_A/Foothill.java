package week10_assignment9_part_A;

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

import java.util.*;

import cs_1c.*;

public class Foothill
{
   public static void main(String[] args) throws Exception
   {
      double finalFlow;
      
      // TABLE 1
      System.out.println("===========TABLE #1============");
      
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
      
      // TABLE 2
      System.out.println("===========TABLE #2============");
      
      myG.clear();
      myG.addEdge("s", "a", 4);
      myG.addEdge("s", "b", 2);
      myG.addEdge("a", "b", 1);
      myG.addEdge("a", "c", 2);
      myG.addEdge("a", "d", 4);
      myG.addEdge("b", "d", 2);
      myG.addEdge("c", "t", 3);
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
      
      // TABLE 3
      System.out.println("===========TABLE #3============");
      
      myG.clear();
      myG.addEdge("s", "A", 1);
      myG.addEdge("s", "D", 4);
      myG.addEdge("s", "G", 6);
      myG.addEdge("A", "B", 2);
      myG.addEdge("A", "E", 2);
      myG.addEdge("B", "C", 2);
      myG.addEdge("C", "t", 4);
      myG.addEdge("D", "E", 3);
      myG.addEdge("D", "A", 3);
      myG.addEdge("E", "C", 2);
      myG.addEdge("E", "F", 3);
      myG.addEdge("E", "I", 3);
      myG.addEdge("F", "C", 1);
      myG.addEdge("F", "t", 3);
      myG.addEdge("G", "D", 2);
      myG.addEdge("G", "E", 1);
      myG.addEdge("G", "H", 6);
      myG.addEdge("H", "E", 2);
      myG.addEdge("H", "I", 6);
      myG.addEdge("I", "F", 1);
      myG.addEdge("I", "t", 4);
      
      // show the original flow graph
      myG.showResAdjTable();
      myG.showFlowAdjTable();
      
      myG.setStartVert("s");
      myG.setEndVert("t");
      finalFlow = myG.findMaxFlow();
      
      System.out.println("Final flow: " + finalFlow);
      
      myG.showResAdjTable();
      myG.showFlowAdjTable();
      
      // TABLE 4
      System.out.println("===========TABLE #4============");
      
      myG.clear();
      myG.addEdge("s", "h", 5);
      myG.addEdge("h", "i", 5);
      myG.addEdge("i", "b", 5);
      myG.addEdge("a", "b", 5);
      myG.addEdge("s", "a", 5);
      myG.addEdge("s", "c", 5);
      myG.addEdge("s", "d", 5);
      myG.addEdge("a", "e", 5);
      myG.addEdge("e", "j", 5);
      myG.addEdge("j", "t", 5);
      myG.addEdge("d", "g", 5);
      myG.addEdge("g", "t", 5);
      myG.addEdge("b", "t", 5);
      myG.addEdge("c", "t", 5);
      myG.addEdge("d", "c", 5);
      
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
   
   public HashSet<Pair<FHflowVertex<E>, Double>> flowAdjList = 
         new HashSet<Pair<FHflowVertex<E>, Double>>();
   public HashSet<Pair<FHflowVertex<E>, Double>> resAdjList = 
         new HashSet<Pair<FHflowVertex<E>, Double>>();
   
   public E data;
   public double dist;
   public FHflowVertex<E> nextInPath;
   
   public FHflowVertex(E x)
   {
      this.data = x;
      this.dist = INFINITY;
      nextInPath = null;
   }
   
   public FHflowVertex()
   {
      this(null);
   }
   
   public void addToFlowAdjList(FHflowVertex<E> neighbor, double cost)
   {
      this.flowAdjList.add(new Pair<FHflowVertex<E>, Double>(neighbor, cost));
   }
   
   public void addToFlowAdjList(FHflowVertex<E> neighbor, int cost)
   {
      addToFlowAdjList(neighbor, (double) cost);
   }
   
   public void addToResAdjList(FHflowVertex<E> neighbor, double cost)
   {
      this.resAdjList.add(new Pair<FHflowVertex<E>, Double>(neighbor, cost));
   }
   
   public void addToResAdjList(FHflowVertex<E> neighbor, int cost)
   {
      addToResAdjList(neighbor, (double) cost);
   }
   
   public boolean equals(Object rhs)
   {
      FHflowVertex<E> other = (FHflowVertex<E>) rhs;
      switch (keyType)
      {
         case KEY_ON_DIST:
            return (this.dist == other.dist);
         case KEY_ON_DATA:
            return (this.data.equals(other.data));
         default:
            return (this.data.equals(other.data));
      }
   }
   
   public int hashCode()
   {
      switch (keyType)
      {
         case KEY_ON_DIST:
            Double d = this.dist;
            return (d.hashCode());
         case KEY_ON_DATA:
            return (this.data.hashCode());
         default:
            return (this.data.hashCode());
      }
   }
   
   public void showFlowAdjList()
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
      
      System.out.print("Adj Flow List for " + this.data + ": ");
      for (iter = flowAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         System.out.print(pair.first.data + "("
               + String.format("%3.1f", pair.second) + ") ");
      }
      System.out.println();
   }
   
   public void showResAdjList()
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
      
      System.out.print("Adj Res List for " + this.data + ": ");
      for (iter = resAdjList.iterator(); iter.hasNext();)
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
   };
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
   protected HashSet<FHflowVertex<E>> vertexSet;
   protected FHflowVertex<E> startVert, endVert;
   
   public FHflowGraph()
   {
      this.vertexSet = new HashSet<FHflowVertex<E>>();
      this.startVert = null;
      this.endVert = null;
   }
   
   public boolean setStartVert(E x)
   {
      if (x == null)
         return false;
      this.startVert = getVertexWithThisData(x);
      if (startVert == null)
         return false;
      return true;
   }
   
   public boolean setEndVert(E x)
   {
      if (x == null)
         return false;
      this.endVert = getVertexWithThisData(x);
      if (endVert == null)
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
      addEdge(source, dest, (double) cost);
   }
   
   public FHflowVertex<E> addToVertexSet(E x)
   {
      FHflowVertex<E> retVal, vert;
      boolean successfulInsertion;
      Iterator<FHflowVertex<E>> iter;
      
      FHflowVertex.pushKeyType();
      FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);
      
      retVal = new FHflowVertex<E>(x);
      successfulInsertion = this.vertexSet.add(retVal);
      
      FHflowVertex.popKeyType();
      
      if (successfulInsertion)
         return retVal;
      ;
      
      for (iter = this.vertexSet.iterator(); iter.hasNext();)
      {
         vert = iter.next();
         if (vert.equals(retVal))
            return vert;
      }
      
      return null;
   }
   
   public void showFlowAdjTable()
   {
      Iterator<FHflowVertex<E>> iter;
      
      System.out.println("-----------Flow Adj Table------------- ");
      for (iter = this.vertexSet.iterator(); iter.hasNext();)
         (iter.next()).showFlowAdjList();
      System.out.println();
   }
   
   public void showResAdjTable()
   {
      Iterator<FHflowVertex<E>> iter;
      
      System.out.println("-----------Res Adj Table------------- ");
      for (iter = this.vertexSet.iterator(); iter.hasNext();)
         (iter.next()).showResAdjList();
      System.out.println();
   }
   
   public void clear()
   {
      this.vertexSet.clear();
      this.startVert = null;
      this.endVert = null;
   }
   
   protected FHflowVertex<E> getVertexWithThisData(E x)
   {
      FHflowVertex<E> searchVert, vert;
      Iterator<FHflowVertex<E>> iter;
      
      FHflowVertex.pushKeyType();
      FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);
      
      searchVert = new FHflowVertex<E>(x);
      
      for (iter = this.vertexSet.iterator(); iter.hasNext();)
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
      
      if (this.startVert == null || this.endVert == null)
         return 0;
      
      while (establishNextFlowPath())
      {
         limittingCost = getLimitingFlowOnResPath();
         
         if (!adjustPathByCost(limittingCost))
            break;
      }
      
      totalFlow = 0;
      for (iter = this.startVert.flowAdjList.iterator(); iter.hasNext();)
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
      
      Deque<FHflowVertex<E>> partiallyProcessedVerts = 
            new LinkedList<FHflowVertex<E>>();
      
      for (iter = this.vertexSet.iterator(); iter.hasNext();)
      {
         vert = iter.next();
         vert.dist = FHflowVertex.INFINITY;
         vert.nextInPath = null;
      }
      
      this.startVert.dist = 0;
      partiallyProcessedVerts.addLast(this.startVert);
      
      while (!partiallyProcessedVerts.isEmpty())
      {
         v = partiallyProcessedVerts.removeFirst();
         
         for (edgeIter = v.resAdjList.iterator(); edgeIter.hasNext();)
         {
            edge = edgeIter.next();
            w = edge.first;
            costVW = edge.second;
            
            if (costVW <= 0)
               continue;
            
            if (v.dist + costVW < w.dist)
            {
               w.dist = v.dist + costVW;
               w.nextInPath = v;
               
               if (w == endVert)
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
      
      if (this.startVert == null || this.endVert == null)
         return 0;
      
      limittingFlow = FHflowVertex.INFINITY;
      for (vert = this.endVert; vert != this.startVert; vert = vert.nextInPath)
      {
         if (vert.nextInPath == null)
            return 0;
         
         edgeCost = getCostOfResEdge(vert.nextInPath, vert);
         
         if (edgeCost < limittingFlow)
            limittingFlow = edgeCost;
      }
      
      return limittingFlow;
   }
   
   protected double getCostOfResEdge(FHflowVertex<E> src, FHflowVertex<E> dst)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
      
      if (src == null || dst == null)
         return 0;
      
      for (iter = src.resAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         
         if (pair.first == dst)
            return pair.second;
      }
      return 0;
   }
   
   protected boolean adjustPathByCost(double cost)
   {
      FHflowVertex<E> vert = this.endVert;
      
      while (vert != this.startVert)
      {
         if (vert.nextInPath == null)
            return false;
         
         if (!addCostToFlowEdge(vert.nextInPath, vert, cost))
            return false;
         if (!addCostToResEdge(vert, vert.nextInPath, cost))
            return false;
         if (!addCostToResEdge(vert.nextInPath, vert, -cost))
            return false;
         
         vert = vert.nextInPath;
      }
      
      return true;
   }
   
   protected boolean addCostToFlowEdge(FHflowVertex<E> src,
         FHflowVertex<E> dst, double cost)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
      
      if (src == null || dst == null)
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
      Pair<FHflowVertex<E>, Double> pair;
      
      if (src == null || dst == null)
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

/*-------------------------------Sample Run-------------------------------------

===========TABLE #1============
-----------Res Adj Table------------- 
Adj Res List for a: b(1.0) s(0.0) c(3.0) d(4.0) 
Adj Res List for b: a(0.0) s(0.0) d(2.0) 
Adj Res List for s: a(3.0) b(2.0) 
Adj Res List for c: a(0.0) t(2.0) 
Adj Res List for d: a(0.0) b(0.0) t(3.0) 
Adj Res List for t: c(0.0) d(0.0) 

-----------Flow Adj Table------------- 
Adj Flow List for a: b(0.0) c(0.0) d(0.0) 
Adj Flow List for b: d(0.0) 
Adj Flow List for s: a(0.0) b(0.0) 
Adj Flow List for c: t(0.0) 
Adj Flow List for d: t(0.0) 
Adj Flow List for t: 

Final flow: 5.0
-----------Res Adj Table------------- 
Adj Res List for a: b(1.0) s(3.0) c(1.0) d(3.0) 
Adj Res List for b: a(0.0) s(2.0) d(0.0) 
Adj Res List for s: a(0.0) b(0.0) 
Adj Res List for c: a(2.0) t(0.0) 
Adj Res List for d: a(1.0) b(2.0) t(0.0) 
Adj Res List for t: c(2.0) d(3.0) 

-----------Flow Adj Table------------- 
Adj Flow List for a: b(0.0) c(2.0) d(1.0) 
Adj Flow List for b: d(2.0) 
Adj Flow List for s: a(3.0) b(2.0) 
Adj Flow List for c: t(2.0) 
Adj Flow List for d: t(3.0) 
Adj Flow List for t: 

===========TABLE #2============
-----------Res Adj Table------------- 
Adj Res List for a: b(1.0) s(0.0) c(2.0) d(4.0) 
Adj Res List for b: a(0.0) s(0.0) d(2.0) 
Adj Res List for s: a(4.0) b(2.0) 
Adj Res List for c: a(0.0) t(3.0) 
Adj Res List for d: a(0.0) b(0.0) t(3.0) 
Adj Res List for t: c(0.0) d(0.0) 

-----------Flow Adj Table------------- 
Adj Flow List for a: b(0.0) c(0.0) d(0.0) 
Adj Flow List for b: d(0.0) 
Adj Flow List for s: a(0.0) b(0.0) 
Adj Flow List for c: t(0.0) 
Adj Flow List for d: t(0.0) 
Adj Flow List for t: 

Final flow: 5.0
-----------Res Adj Table------------- 
Adj Res List for a: b(1.0) s(3.0) c(0.0) d(3.0) 
Adj Res List for b: a(0.0) s(2.0) d(0.0) 
Adj Res List for s: a(1.0) b(0.0) 
Adj Res List for c: a(2.0) t(1.0) 
Adj Res List for d: a(1.0) b(2.0) t(0.0) 
Adj Res List for t: c(2.0) d(3.0) 

-----------Flow Adj Table------------- 
Adj Flow List for a: b(0.0) c(2.0) d(1.0) 
Adj Flow List for b: d(2.0) 
Adj Flow List for s: a(3.0) b(2.0) 
Adj Flow List for c: t(2.0) 
Adj Flow List for d: t(3.0) 
Adj Flow List for t: 

===========TABLE #3============
-----------Res Adj Table------------- 
Adj Res List for A: B(2.0) s(0.0) D(0.0) E(2.0) 
Adj Res List for B: A(0.0) C(2.0) 
Adj Res List for s: A(1.0) D(4.0) G(6.0) 
Adj Res List for C: B(0.0) t(4.0) E(0.0) F(0.0) 
Adj Res List for D: A(3.0) s(0.0) E(3.0) G(0.0) 
Adj Res List for t: C(0.0) F(0.0) I(0.0) 
Adj Res List for E: A(0.0) C(2.0) D(0.0) F(3.0) G(0.0) H(0.0) I(3.0) 
Adj Res List for F: C(1.0) t(3.0) E(0.0) I(0.0) 
Adj Res List for G: s(0.0) D(2.0) E(1.0) H(6.0) 
Adj Res List for H: E(2.0) G(0.0) I(6.0) 
Adj Res List for I: t(4.0) E(0.0) F(1.0) H(0.0) 

-----------Flow Adj Table------------- 
Adj Flow List for A: B(0.0) E(0.0) 
Adj Flow List for B: C(0.0) 
Adj Flow List for s: A(0.0) D(0.0) G(0.0) 
Adj Flow List for C: t(0.0) 
Adj Flow List for D: A(0.0) E(0.0) 
Adj Flow List for t: 
Adj Flow List for E: C(0.0) F(0.0) I(0.0) 
Adj Flow List for F: C(0.0) t(0.0) 
Adj Flow List for G: D(0.0) E(0.0) H(0.0) 
Adj Flow List for H: E(0.0) I(0.0) 
Adj Flow List for I: t(0.0) F(0.0) 

Final flow: 11.0
-----------Res Adj Table------------- 
Adj Res List for A: B(0.0) s(1.0) D(3.0) E(0.0) 
Adj Res List for B: A(2.0) C(0.0) 
Adj Res List for s: A(0.0) D(0.0) G(0.0) 
Adj Res List for C: B(2.0) t(0.0) E(2.0) F(0.0) 
Adj Res List for D: A(0.0) s(4.0) E(0.0) G(2.0) 
Adj Res List for t: C(4.0) F(3.0) I(4.0) 
Adj Res List for E: A(2.0) C(0.0) D(3.0) F(0.0) G(1.0) H(2.0) I(0.0) 
Adj Res List for F: C(1.0) t(0.0) E(3.0) I(0.0) 
Adj Res List for G: s(6.0) D(0.0) E(0.0) H(3.0) 
Adj Res List for H: E(0.0) G(3.0) I(5.0) 
Adj Res List for I: t(0.0) E(3.0) F(1.0) H(1.0) 

-----------Flow Adj Table------------- 
Adj Flow List for A: B(2.0) E(2.0) 
Adj Flow List for B: C(2.0) 
Adj Flow List for s: A(1.0) D(4.0) G(6.0) 
Adj Flow List for C: t(4.0) 
Adj Flow List for D: A(3.0) E(3.0) 
Adj Flow List for t: 
Adj Flow List for E: C(2.0) F(3.0) I(3.0) 
Adj Flow List for F: C(0.0) t(3.0) 
Adj Flow List for G: D(2.0) E(1.0) H(3.0) 
Adj Flow List for H: E(2.0) I(1.0) 
Adj Flow List for I: t(4.0) F(0.0) 

===========TABLE #4============
-----------Res Adj Table------------- 
Adj Res List for a: b(5.0) s(0.0) e(5.0) 
Adj Res List for b: a(0.0) t(5.0) i(0.0) 
Adj Res List for s: a(5.0) c(5.0) d(5.0) h(5.0) 
Adj Res List for c: s(0.0) t(5.0) d(0.0) 
Adj Res List for d: s(0.0) c(5.0) g(5.0) 
Adj Res List for t: b(0.0) c(0.0) g(0.0) j(0.0) 
Adj Res List for e: a(0.0) j(5.0) 
Adj Res List for g: d(0.0) t(5.0) 
Adj Res List for h: s(0.0) i(5.0) 
Adj Res List for i: b(5.0) h(0.0) 
Adj Res List for j: t(5.0) e(0.0) 

-----------Flow Adj Table------------- 
Adj Flow List for a: b(0.0) e(0.0) 
Adj Flow List for b: t(0.0) 
Adj Flow List for s: a(0.0) c(0.0) d(0.0) h(0.0) 
Adj Flow List for c: t(0.0) 
Adj Flow List for d: c(0.0) g(0.0) 
Adj Flow List for t: 
Adj Flow List for e: j(0.0) 
Adj Flow List for g: t(0.0) 
Adj Flow List for h: i(0.0) 
Adj Flow List for i: b(0.0) 
Adj Flow List for j: t(0.0) 

Final flow: 20.0
-----------Res Adj Table------------- 
Adj Res List for a: b(5.0) s(5.0) e(0.0) 
Adj Res List for b: a(0.0) t(0.0) i(5.0) 
Adj Res List for s: a(0.0) c(0.0) d(0.0) h(0.0) 
Adj Res List for c: s(5.0) t(0.0) d(0.0) 
Adj Res List for d: s(5.0) c(5.0) g(0.0) 
Adj Res List for t: b(5.0) c(5.0) g(5.0) j(5.0) 
Adj Res List for e: a(5.0) j(0.0) 
Adj Res List for g: d(5.0) t(0.0) 
Adj Res List for h: s(5.0) i(0.0) 
Adj Res List for i: b(0.0) h(5.0) 
Adj Res List for j: t(0.0) e(5.0) 

-----------Flow Adj Table------------- 
Adj Flow List for a: b(0.0) e(5.0) 
Adj Flow List for b: t(5.0) 
Adj Flow List for s: a(5.0) c(5.0) d(5.0) h(5.0) 
Adj Flow List for c: t(5.0) 
Adj Flow List for d: c(0.0) g(5.0) 
Adj Flow List for t: 
Adj Flow List for e: j(5.0) 
Adj Flow List for g: t(5.0) 
Adj Flow List for h: i(5.0) 
Adj Flow List for i: b(5.0) 
Adj Flow List for j: t(5.0) 



------------------------------------------------------------------------------*/
