package week3_assignment3_part_A;

/**
 * CS 1C Assignment 3 - Matrix Multiplication
 * ------------------------------------------------------
 * Name : Muhammad Rizky Wellyanto
 * CWID : 20144588
 * Parts : A,B
 * Description : This is the third assignment of CS_1C
 * ML F14. this matrix multiplication program, sharpens our skills in term of
 * designing efficient algorithm, and also practice investigating the time an
 * algorithm work.
 */

import java.text.NumberFormat;
import java.util.ListIterator;
import java.util.Locale;

import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;

/**
 * main client of the program
 * 
 * @author MuhammadRizky
 * 
 */
public class Foothill
{
   final static int MAT_SIZE = 1000;
   final static int SPARSENESS = 1;
   
   public static void main(String[] args) throws Exception
   {
      int r, randRow, randCol;
      long startTime, stopTime;
      double smallPercent;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      // init matrices
      double[][] mat, matAns;
      mat = new double[MAT_SIZE][MAT_SIZE];
      matAns = new double[MAT_SIZE][MAT_SIZE];
      
      // generate small% of non-default values bet 0 and 1
      smallPercent = MAT_SIZE * MAT_SIZE * SPARSENESS / 100;
      for (r = 0; r < smallPercent; r++)
      {
         randRow = (int) (Math.random() * MAT_SIZE);
         randCol = (int) (Math.random() * MAT_SIZE);
         mat[randRow][randCol] = Math.random();
      }
      
      // 10x10 submatrix in lower right
      matShow(mat, MAT_SIZE - 10, 10);
      
      startTime = System.nanoTime();
      matMult(mat, mat, matAns);
      stopTime = System.nanoTime();
      
      matShow(matAns, MAT_SIZE - 100, 10);
      
      System.out.println("\nSize = " + MAT_SIZE + ". Sparseness = "
            + SPARSENESS + "%. Mat. Mult. Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
      System.out.println();
      
      // test the part B
      SparseMatWMult n = new SparseMatWMult(MAT_SIZE, MAT_SIZE);
      SparseMatWMult m = new SparseMatWMult(MAT_SIZE, MAT_SIZE);
      SparseMatWMult matProduct = new SparseMatWMult(MAT_SIZE, MAT_SIZE);
      
      // generate values
      for (r = 0; r < smallPercent; r++)
      {
         randRow = (int) (Math.random() * MAT_SIZE);
         randCol = (int) (Math.random() * MAT_SIZE);
         n.set(randRow, randCol, Math.random());
         m.set(randRow, randCol, Math.random());
      }
      
      n.showSubSquare(MAT_SIZE - 10, 10);
      m.showSubSquare(MAT_SIZE - 10, 10);
      
      startTime = System.nanoTime();
      matProduct.matMult(n, m);
      stopTime = System.nanoTime();
      
      matProduct.showSubSquare(MAT_SIZE - 10, 10);
      
      System.out.println("\nSize = " + MAT_SIZE + ". Sparseness = "
            + SPARSENESS + "%. Generic Mat. Mult. Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
   }
   
   /**
    * take three input, the first two input as the two matrices that will be
    * multiplied to each other, the third one is the reference of the new
    * output matrix
    * 
    * @param mat1
    * @param mat2
    * @param mat3
    */
   public static void matMult(double[][] mat1, double[][] mat2, double[][] mat3)
   {
      // validate input, return if invalid
      if (mat1.length != mat2[0].length || mat1[0].length != mat2.length
            || mat3.length != mat1.length || mat3[0].length != mat2[0].length)
         return;
      
      // prepare output matrix
      for (int row = 0; row < mat3.length; row++)
         for (int col = 0; col < mat3[0].length; col++)
            mat3[row][col] = 0.0;
      
      // matrix multiplication
      for (int rowMat1 = 0; rowMat1 < mat1.length; rowMat1++)
         for (int colMat2 = 0; colMat2 < mat2[0].length; colMat2++)
            for (int i = 0; i < mat2[0].length; i++)
               mat3[rowMat1][colMat2] += mat1[rowMat1][colMat2]
                     * mat2[colMat2][i];
   }
   
   /**
    * show to the console the matrix, from start index, with size
    * 
    * @param matA
    * @param start
    * @param size
    */
   public static void matShow(double[][] mat, int start, int size)
   {
      // validate, return if invalid
      if (start < 0 || size < 0 || start + size > mat.length)
         return;
      
      // print each element
      for (int row = start; row < start + size; row++)
      {
         for (int col = start; col < start + size; col++)
            System.out.print(String.format("%4.1f", mat[row][col]));
         System.out.println();
      }
      System.out.println();
   }
}

/**
 * SparseMatWMult exist as a generic matrix multiplication
 * 
 * @author MuhammadRizky
 * 
 */
class SparseMatWMult extends SparseMat<Double>
{
   public SparseMatWMult(int numRows, int numCols)
   {
      super(numRows, numCols, 0.0);
   }
   
   /**
    * matrix multiplication algorithm. this is the best algorithm i can think
    * of. please tell me the best practice of doing the matrix multiplication
    * if my algorithm is not efficient enough. thank you.
    * 
    * @param matA
    * @param matB
    * @return
    */
   public boolean matMult(SparseMatWMult mat1, SparseMatWMult mat2)
   {
      // validate input
      if (mat1.rowSize != mat2.colSize || mat1.colSize != mat2.rowSize
            || this.rowSize != mat1.rowSize || this.colSize != mat2.colSize)
         return false;
      
      // prepare the output matrix
      this.clear();
      
      // matrix multiplication algorithm
      for (int row = 0; row < this.rowSize; row++)
         for (MatNode node1 : mat1.rows.get(row))
            for (MatNode node2 : mat2.rows.get(node1.col))
               set(row, node2.col, (node1.data * node2.data));
      
      return true;
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
   protected int rowSize, colSize;
   protected E defaultVal;
   protected FHarrayList<FHlinkedList<MatNode>> rows;
   
   public SparseMat(int numRows, int numCols, E defaultVal)
   {
      if (!(numRows >= 1 && numCols >= 1))
      {
         throw new IllegalArgumentException();
      }
      
      this.rowSize = numRows;
      this.colSize = numCols;
      this.defaultVal = defaultVal;
      
      allocateEmptyMatrix();
   }
   
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
      if (!validate(r, c))
         return false;
      
      ListIterator<MatNode> iter;
      iter = (ListIterator<MatNode>) rows.get(r).listIterator();
      
      for (; iter.hasNext();)
      {
         if (iter.next().col == c)
         {
            if (x.equals(defaultVal))
            {
               iter.remove();
               return true;
            }
            else
            {
               iter.previous().data = x;
               return true;
            }
         }
      }
      
      if (!(x.equals(defaultVal)))
         rows.get(r).add(new MatNode(c, x));
      
      return true;
   }
   
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
      if (!validate(r, c))
         throw new IndexOutOfBoundsException();
      
      ListIterator<MatNode> iter;
      iter = (ListIterator<MatNode>) rows.get(r).listIterator();
      
      for (; iter.hasNext();)
         if (iter.next().col == c)
            return iter.previous().data;
      
      return this.defaultVal;
   }
   
   public int getRowSize()
   {
      return this.rowSize;
   }
   
   public int getColumnSize()
   {
      return this.colSize;
   }
   
   /**
    * this method clears every row
    */
   public void clear()
   {
      for (int i = 0; i < this.rowSize; i++)
         this.rows.get(i).clear();
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
      if (start < 0 || size < 0 || start + size > this.colSize
            || start + size > this.rowSize)
         throw new IndexOutOfBoundsException();
      
      int sideLength = start + size;
      
      for (int i = start; i < sideLength; i++)
      {
         for (int j = start; j < sideLength; j++)
         {
            System.out.print(String.format("%4.1f", (Double) this.get(i, j))
                  + " ");
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
      rows = new FHarrayList<FHlinkedList<MatNode>>();
      
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
      SparseMat<E> newObject = (SparseMat<E>) super.clone();
      
      ListIterator<MatNode> iter;
      
      for (int i = 0; i < newObject.rowSize; i++)
      {
         iter = (ListIterator<MatNode>) this.rows.get(i).listIterator();
         
         FHlinkedList<MatNode> newObjectRow = newObject.rows.get(i);
         
         for (; iter.hasNext();)
         {
            newObjectRow.add((MatNode) iter.next().clone());
         }
      }
      
      return newObject;
   }
   
   /**
    * Inner Class, MatNode, which acts as the nodes of the sparse matrix
    * 
    * @author MuhammadRizky
    * 
    */
   protected class MatNode implements Cloneable
   {
      public int col;
      public E data;
      
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
         MatNode newObject = (MatNode) super.clone();
         return (Object) newObject;
      }
   }
}

/*---------------------------Question and Answers-------------------------------

PART A QUESTION AND ANSWERS-----------------------------------------------------

- What is the time complexity of the of this operation:
      O() = M^3
      because there are thre iteration in each mat mul process. the sample run
      also proves that by multiplying the input twice, results a multiplication
      of time by 8 fold

1. What was the smallest M that gave you a non-zero time?
      It's 10, with Sparseness 10%. its proven in the sample run

2. What happened when you doubled M, tripled it, quadrupeled it, etc? Give
   Several M values and their times in table.
      When i doubled M, the time multiplied approximately 8 fold.
      When i tripled M, the time multiplied approximately 27 fold.
      When i quadrupled M, the time multiplied approximately 64 fold.
      
      Sparseness = 10%
      
      Table:
      ------------------------------
            M     |     Time
      ------------------------------
       100        | 0.0197
       200        | 0.0306
       300        | 0.054
       400        | 0.1163
       500        | 0.1937
       600        | 0.345
       700        | 0.5478
       800        | 0.7557
       900        | 1.0422
      1000        | 1.3757
      2000        | 9.8271
      4000        | 75.7646
      8000        | 590.8899
      
3. How large an M can you use before the program refuses to run (exception or 
   run-time error due to memory overload) or it takes so long you can't wait 
   for the run?
      M = 15000
      it throws (Exception in thread "main" 
      java.lang.OutOfMemoryError: Java heap space
      at week3_assignment3_part_A.Foothill.main(Foothill.java:44))

4. How did the data agree or disagree with your original time complexity 
   estimate?
      Surprisingly very accurate. According to my calculation if the M is 
      doubled, the time it takes should multiply by 8 fold, by the proof of the
      table and the sample run, M = 2000 takes 9.8271 seconds to complete, which
      in my calculations, if i double M, it should takes 78.6168 seconds, yet
      the sample run of M = 4000 results a value of 75.7646 seconds. which is
      just 3.6279% less than my calculation. since the value is lower than the
      calculated estimate, this proves that the code is even more efficient than
      Order M^3

PART B QUESTION AND ANSWERS-----------------------------------------------------

1. Are the times longer or shorter than for dynamic matrices?
      from my experiment, if the spareness is low, i.e. 1% the dynamic matrices
      perform up to 40 times!! more faster than the basic one. however if the
      sparseness is high, i.e. 

2. Are the growth rates larger or smaller?  By how much?
      the growth rate are relatively small when the sparseness is small, i.e.
      in 1% sparseness, when i double the M, from 800 to 1600 the time only
      multiplied by 5 fold, on the other hand, the basic array multiplies by
      8 fold. however in higher sparseness value i.e. 15%, the basic array 
      growth remain multiplied by 8 if i double the M value. whereas in the 
      dynamic array it could multiply up to 40 fold.

3. Create a table and answer the same questions as before.
      Since the time growth differ based on the sparseness, i fixed the sparse-
      ness to be 1% and analyze the growth.
      
      Sparseness = 1%
      
      Table:
      ------------------------------
            M     |     Time
      ------------------------------
       100        | 0.0007
       200        | 0.0036
       300        | 0.0223
       400        | 0.222
       500        | 0.009
      1000        | 0.0598
      2000        | 0.666
      4000        | 24.2644
      8000        | 968.587
      
      it seems like the growth varies in some cases, when the M value is small
      i.e. from 100 to 200, or 500 to 1000 when the M doubles the time
      multiplied by 5 fold. in higher M values, i.e. 2000 to 4000, when the M
      doubles time multiplies by up to 40 fold.

4. What was the largest M you could use here, and was the reason the same or 
   different than for dynamic arrays?
      different, M = 15000 still works on this data structure. since M = 15000
      takes so much time to complete, i dont have much time to calculate the
      limit of the dynamic array. however it definitely > 15000. the reason is
      the dynamic array, only create a necessary node, thus only take a small
      amount of memory. on the other hand, the basic arrays create the whole
      matrix, and store the default value inside it, thus taking a huge amount
      of wasted data.
   
5. Try different sparseness values: 1%  5%  10%  0.2%, and report how the 
   growth rates behave in each case.
      M = 1000
      
      Table
      -------------------------------
         Sparseness  |  Time        
      -------------------------------
      1%             | 0.0876
      2%             | 0.4116
      5%             | 5.3431
      10%            | 20.7767
      15%            | 44.5777
      
      this shows that the growth of the time of the dynamic arrays increase
      really fast in respect to the sparseness. i believe it's because of the
      getting, searching, and setting the nodes takes more time rather than
      getting, searching, and setting an array. so it seems like in the dynamic
      array, the bottleneck is in the node operations.

------------------------------------------------------------------------------*/

/*--------------------------------Sample Run------------------------------------

 PART A SAMPLE RUN--------------------------------------------------------------

Size = 10. Sparseness = 10%. Mat. Mult. Elapsed Time: 0.0001 seconds.

 0.0 0.0 0.0 0.0 0.6 0.2 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.1
 0.0 0.0 0.7 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.1 0.0
 0.0 0.0 0.0 0.0 0.6 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.4 0.2 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.5 0.0 0.0 0.0 0.5 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 0.5 0.0 0.0 0.0
 0.0 0.7 0.0 0.0 0.0 0.0 0.0 0.7 0.1 0.0

 0.0 0.0 0.0 0.0 3.7 1.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 2.1 0.0 0.0 0.3
 0.0 0.0 3.9 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.6 0.0
 0.0 0.0 0.0 0.0 3.2 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 2.2 0.4 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 2.7 0.0 0.0 0.0 1.2 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 1.1 0.0 0.0 0.0
 0.0 1.8 0.0 0.0 0.0 0.0 0.0 2.1 0.7 0.0


Size = 100. Sparseness = 10%. Mat. Mult. Elapsed Time: 0.0197 seconds.

 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.6 0.0 0.0 0.2 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.8 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.4
 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.7 0.0 0.0 0.0 0.0 0.6 0.0 0.0 0.0 0.0
 0.3 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.4 0.8 0.0 0.0 0.0 0.0
 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0

 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 5.4 0.0 0.0 2.7 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 6.5 0.0 0.0 0.0 0.0 0.0 0.0 0.0 3.6
 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 5.9 0.0 0.0 0.0 0.0 5.5 0.0 0.0 0.0 0.0
 2.5 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 4.0 6.8 0.0 0.0 0.0 0.0
 7.9 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0


Size = 200. Sparseness = 10%. Mat. Mult. Elapsed Time: 0.0306 seconds.

Size = 300. Sparseness = 10%. Mat. Mult. Elapsed Time: 0.054 seconds.

Size = 400. Sparseness = 10%. Mat. Mult. Elapsed Time: 0.1163 seconds.

Size = 500. Sparseness = 10%. Mat. Mult. Elapsed Time: 0.1937 seconds.

Size = 600. Sparseness = 10%. Mat. Mult. Elapsed Time: 0.345 seconds.

Size = 700. Sparseness = 10%. Mat. Mult. Elapsed Time: 0.5478 seconds.

Size = 800. Sparseness = 10%. Mat. Mult. Elapsed Time: 0.7557 seconds.

Size = 900. Sparseness = 10%. Mat. Mult. Elapsed Time: 1.0422 seconds.

Size = 1000. Sparseness = 10%. Mat. Mult. Elapsed Time: 1.3757 seconds.

Size = 2000. Sparseness = 10%. Mat. Mult. Elapsed Time: 9.8271 seconds.

Size = 4000. Sparseness = 10%. Mat. Mult. Elapsed Time: 75.7646 seconds.

Size = 8000. Sparseness = 10%. Mat. Mult. Elapsed Time: 590.8899 seconds.

 PART B SAMPLE RUN--------------------------------------------------------------

0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 

 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 

 0.0  0.0  0.0  0.0  0.0  0.0  0.1  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.1  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.1  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.1  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 


Size = 800. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 0.0425 seconds.

0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  1.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.3  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.1  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.9  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 

 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.5  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.8  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  1.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 

 0.0  0.0  0.0  0.0  0.0  0.5  0.0  0.6  0.0  0.0 
 0.0  0.5  0.0  0.0  0.0  0.2  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.7  0.0  0.0  0.0  0.0  0.2  0.0 
 0.0  0.0  0.6  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.2  0.0  0.0  0.0  0.0  0.0  0.1 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.5  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.2  0.9  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 


Size = 1600. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 0.2094 seconds.

 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.7  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 

 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.3  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.8  0.0  0.0 

 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.2  0.0  0.1 
 0.1  0.0  0.0  0.0  0.0  0.5  0.1  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.1  0.1  0.1  0.0  0.0 
 0.2  0.0  0.0  0.0  0.0  0.0  0.0  0.4  0.0  0.0 
 0.0  0.0  0.0  0.2  0.0  0.1  0.0  0.0  0.0  0.3 
 0.1  0.0  0.2  0.2  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.4  0.0  0.0  0.0  0.0  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.2  0.5  0.0  0.0 
 0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.2  0.3 
 0.0  0.3  0.0  0.3  0.0  0.0  0.0  0.0  0.1  0.2 


Size = 3200. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 6.9888 seconds.

Size = 100. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 0.0007 seconds.

Size = 200. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 0.0036 seconds.

Size = 300. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 0.0223 seconds.

Size = 400. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 0.0222 seconds.

Size = 500. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 0.009 seconds.

Size = 1000. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 0.0598 seconds.

Size = 2000. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 0.666 seconds.

Size = 4000. Sparseness = 1%. Generic Mat. Mult. Elapsed Time: 24.2644 seconds.

------------------------------------------------------------------------------*/
