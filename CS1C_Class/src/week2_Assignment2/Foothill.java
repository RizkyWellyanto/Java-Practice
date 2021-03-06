package week2_Assignment2;

/**
 * CS 1C Assignment 2 - Sparse Matrix
 * ------------------------------------------------------
 * Name : Muhammad Rizky Wellyanto
 * CWID : 20144588
 * Parts : A,B
 * Description : This is the second assignment of CS_1C
 * ML F14. this sparse matrix program, test our understanding about ArrayList
 * and LinkedList data structure.
 */

// necessary classes
import cs_1c.*;
import java.util.*;

/**
 * client class, with the main method
 * 
 * @author MuhammadRizky
 * 
 */
public class Foothill
{
   final static int MAT_SIZE = 100000;
   
   // ------- main --------------
   public static void main(String[] args) throws Exception
   {
      // 100000 x 100000 filled with 0
      int k;
      SparseMat<Double> mat = new SparseMat<Double>(MAT_SIZE, MAT_SIZE, 0.);
      
      // test mutators
      for (k = 0; k < 10; k++)
      {
         mat.set(k, k, k * 1.);
         mat.set(4, k, k * 10.);
         mat.set(k, 4, -k * 10.);
      }
      mat.showSubSquare(0, 12);
      System.out.println();
      
      // test clone
      SparseMat<Double> mat2 = (SparseMat<Double>) mat.clone();
      
      // test the setters
      for (k = 0; k < 10; k++)
      {
         mat.set(k, k, 1.);
         mat.set(4, k, 10.);
         mat.set(k, 4, -10.);
      }
      
      // test the showSub squares
      mat.showSubSquare(0, 12);
      System.out.println();
      mat2.showSubSquare(0, 12);
      
      // test the getters
      System.out.println(mat.get(0, 0));
      System.out
            .println(mat.get(mat.getRowSize() - 1, mat.getColumnSize() - 1));
      System.out.println();
      
      // test clear
      mat.clear();
      mat.showSubSquare(0, 12);
      
      // test invalid argument on the constructor
      try
      {
         SparseMat<Double> mat3 = new SparseMat<Double>(-1, -1, 0.);
         
         // if the code doesnt get error, it will print success 
         System.out.println("SUCCESS");
      }
      catch(IllegalArgumentException e)
      {
         //otherwise print error
         System.out.println("ERROR!");
      }
      System.out.println();
      
      // test invalid argument on the setter
      System.out.println(mat2.set(0, 0, 10.0));
      System.out.println(mat2.set(2000000, 2000000, 10.0));
      System.out.println();
      
      // test invalid argument on the getter
      try
      {
         mat2.get(200000, 200000);
         
         // if the code doesnt get error, it will print success 
         System.out.println("SUCCESS");
      }
      catch(IndexOutOfBoundsException e)
      {
         //otherwise print error
         System.out.println("ERROR!");
      }
      System.out.println();
      
      // test invalid argument on the showSubSquare
      try
      {
         mat2.showSubSquare(-1, 2000000);
         
         // if the code doesnt get error, it will print success 
         System.out.println("SUCCESS");
      }
      catch(IndexOutOfBoundsException e)
      {
         //otherwise print error
         System.out.println("ERROR!");
      }
      System.out.println();
   }
}

/**
 * the generic class, sparse matrix
 * 
 * @author MuhammadRizky
 * 
 * @param <E>
 */
class SparseMat<E> implements Cloneable
{
   // private data
   protected int rowSize, colSize;
   protected E defaultVal;
   protected FHarrayList<FHlinkedList<MatNode>> rows;
   
   public SparseMat(int numRows, int numCols, E defaultVal)
   {
      // validate the input
      if (!(numRows >= 1 && numCols >= 1))
      {
         // throw exception, to tell client that something's wrong
         throw new IllegalArgumentException();
      }
      
      // set the member data
      this.rowSize = numRows;
      this.colSize = numCols;
      this.defaultVal = defaultVal;
      
      // call the allocateEmptyMatrix to avoid code duplication
      allocateEmptyMatrix();
   }
   
   // mutator
   /**
    * set an object to the matrix. returns true if success, false otherwise
    * 
    * @param r
    * @param c
    * @param x
    * @return
    */
   public boolean set(int r, int c, E x)
   {
      // validate the input
      if (!validate(r, c))
         return false;
      
      // create an iterator on that specific row that goes through the column
      ListIterator<MatNode> iter;
      iter = (ListIterator<MatNode>) rows.get(r).listIterator();
      
      // iterate through
      for (; iter.hasNext();)
      {
         // if found
         if (iter.next().col == c)
         {
            // if the input is the deault value, simply remove the node
            if (x.equals(defaultVal))
            {
               iter.remove();
               return true;
            }
            // if its not the default value, add data's reference to the node
            else
            {
               iter.previous().data = x;
               return true;
            }
         }
      }
      
      // if node with that column not found. add a new node with c and x.
      // however the new data should not be defaultValue
      if (!(x.equals(defaultVal)))
         rows.get(r).add(new MatNode(c, x));
      
      return true;
   }
   
   // accessor
   /**
    * get an object from specific indexes. throw exception if argument is
    * invalid. returns the object if found
    * 
    * @param r
    * @param c
    * @return
    */
   public E get(int r, int c)
   {
      // validate the input
      if (!validate(r, c))
         throw new IndexOutOfBoundsException();
      
      // create aan iterator
      ListIterator<MatNode> iter;
      iter = (ListIterator<MatNode>) rows.get(r).listIterator();
      
      // iterate through
      for (; iter.hasNext();)
      {
         if (iter.next().col == c)
         {
            return iter.previous().data;
         }
      }
      
      // if not found
      return this.defaultVal;
   }
   
   // not required, but i think it might be useful to have getters for size
   public int getRowSize()
   {
      return this.rowSize;
   }
   
   public int getColumnSize()
   {
      return this.colSize;
   }
   
   // other basic methods
   /**
    * this method clears every row
    */
   public void clear()
   {
      // loop through the row
      for (int i = 0; i < this.rowSize; i++)
      {
         // clear the rows, making it virtually defaultVal
         this.rows.get(i).clear();
      }
   }
   
   /**
    * validate method, to avoid code duplication. check the validity of the
    * input integer, row and column
    * 
    * @param r
    * @param c
    * @return
    */
   protected boolean validate(int r, int c)
   {
      if ((r >= 0 && r < this.rowSize) && (c >= 0 && c < this.colSize))
         return true;
      return false;
   }
   
   /**
    * output method, shows stuff to the console
    * 
    * @param start
    * @param size
    */
   public void showSubSquare(int start, int size)
   {
      // validate input
      if (start < 0 || size < 0 || start + size > this.colSize
            || start + size > this.rowSize)
         throw new IndexOutOfBoundsException();
      
      int sideLength = start + size;
      
      // loop through row,
      for (int i = start; i < sideLength; i++)
      {
         for (int j = start; j < sideLength; j++)
         {
            System.out.print((Double) this.get(i, j) + " ");
         }
         System.out.println();
      }
      System.out.println();
   }
   
   /**
    * this method instantiate the rows member data, and also adding the rest
    * of the FHlinkedList to the FHarrayList
    */
   protected void allocateEmptyMatrix()
   {
      // instantiate the rows member data
      rows = new FHarrayList<FHlinkedList<MatNode>>();
      
      // add the rest
      for (int i = 0; i < this.rowSize; i++)
      {
         rows.add(new FHlinkedList<MatNode>());
      }
   }
   
   /**
    * clone the matrix. since the test main casts the returned object to MatNode
    * i guess, returning Object is fine. please correct me if i'm wrong, i'm not
    * a hundred percent sure, but based on my CS 1B notes, this is the proper
    * way to clone an object.
    */
   public Object clone() throws CloneNotSupportedException
   {
      // always do this first, copy the parent data. this is shallow copy
      SparseMat<E> newObject = (SparseMat<E>) super.clone();
      
      // clone the deep data by iterating through all of the item inside Sparse
      // Matrix. this includes the FHarrayList and FHlinkedList
      newObject.allocateEmptyMatrix(); // make the SparseMatrix ready
      
      // create iterator to iterate through FHlinkedList
      ListIterator<MatNode> iter;
      
      // goes through every item on the FHarrayList
      for (int i = 0; i < newObject.rowSize; i++)
      {
         // instantiate the iterator
         iter = (ListIterator<MatNode>) this.rows.get(i).listIterator();
         
         // prepare the new row
         FHlinkedList<MatNode> newObjectRow = newObject.rows.get(i);
         
         // loop through the FHlinkedList
         for (; iter.hasNext();)
         {
            // add the old row to the new row
            newObjectRow.add((MatNode) iter.next().clone());
         }
      }
      
      // return the newObject
      return newObject;
   }
   
   /**
    * Inner Class, MatNode, which acts as the nodes of the sparse matrix
    * 
    * @author MuhammadRizky
    * 
    */
   // protected enables us to safely make col/data public
   protected class MatNode implements Cloneable
   {
      public int col;
      public E data;
      
      // we need a default constructor for lists
      MatNode()
      {
         col = 0;
         data = null;
      }
      
      MatNode(int cl, E dt)
      {
         col = cl;
         data = dt;
      }
      
      public Object clone() throws CloneNotSupportedException
      {
         // shallow copy
         MatNode newObject = (MatNode) super.clone();
         return (Object) newObject;
      }
   };
}

/*------------------------------------------------------------------------------

0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 1.0 0.0 0.0 -10.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 2.0 0.0 -20.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 3.0 -30.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 10.0 20.0 30.0 -40.0 50.0 60.0 70.0 80.0 90.0 0.0 0.0 
0.0 0.0 0.0 0.0 -50.0 5.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -60.0 0.0 6.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -70.0 0.0 0.0 7.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -80.0 0.0 0.0 0.0 8.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -90.0 0.0 0.0 0.0 0.0 9.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 


1.0 0.0 0.0 0.0 -10.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 1.0 0.0 0.0 -10.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 1.0 0.0 -10.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 1.0 -10.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
10.0 10.0 10.0 10.0 -10.0 10.0 10.0 10.0 10.0 10.0 0.0 0.0 
0.0 0.0 0.0 0.0 -10.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -10.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -10.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -10.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -10.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 


0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 1.0 0.0 0.0 -10.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 2.0 0.0 -20.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 3.0 -30.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 10.0 20.0 30.0 -40.0 50.0 60.0 70.0 80.0 90.0 0.0 0.0 
0.0 0.0 0.0 0.0 -50.0 5.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -60.0 0.0 6.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -70.0 0.0 0.0 7.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -80.0 0.0 0.0 0.0 8.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 -90.0 0.0 0.0 0.0 0.0 9.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 

1.0
0.0

0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 

ERROR!

true
false

ERROR!

ERROR!

 ------------------------------------------------------------------------------*/
