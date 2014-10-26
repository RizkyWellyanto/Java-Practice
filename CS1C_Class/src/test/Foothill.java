// 1C_A3, Ridge McGhee, Instructor Solution
import java.text.NumberFormat;
import java.util.ListIterator;
import java.util.Locale;
import java.io.*;
import java.util.*;
 
import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;
 
public class Foothill {
   public static void main(String[] args) throws Exception {
      partA_proof();
      partA_minMax(16, 3924); // either guess correctly or settle for less
      partA_genData();
 
      partB_proof();
      partB();
      System.out.println("Done.");
   }
 
   public static void partA_proof() {
      int matSize = 5;
      double[][] matAns = new double[matSize][matSize];
      double[][] matA =
            { { 1, 2, 3, 4, 5 }, { -1, -2, -3, -4, -5 }, { 1, 3, 1, 3, 1 },
                  { 0, 1, 0, 1, 0 }, { -1, -1, -1, -1, -1 } };
      double[][] matB =
            { { 2, 1, 5, 0, 2 }, { 1, 4, 3, 2, 7 }, { 4, 4, 4, 4, 4 },
                  { 7, 1, -1, -1, -1 }, { 0, 0, 8, -1, -6 } };
 
      System.out.println("Part A");
      System.out.println("\nProof of correctness for matrix mult");
      System.out.println("------------------------------------");
 
      System.out.println("Matrix A:");
      matShow(matA, 0, matSize);
      System.out.println("\nMatrix B:");
      matShow(matB, 0, matSize);
      matMult(matA, matB, matAns);
      System.out.println("\nDot Product:");
      matShow(matAns, 0, matSize);
   }
 
   public static void partA_minMax(int minGuess, int maxGuess) {
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
 
      int matSize;
      double[][] mat = null;
      double[][] matAns = null;
 
      // This is a little tricky because allocation cannot fail if we want
      // maximum allocation. The gc runs just before an OutOfMemoryError is
      // thrown but the heap loses about 32Mb of capacity.  So, we have to
      // guess correctly the first time or we have to settle for much less
      // (3924 is max on 256m JVM but we settle for 36xx after gc runs).
      // Since the gc is SLOW, linear search would take too long so we use
      // binary search (and settle for whatever works).
      final int MAX_POSSIBLE = 4000;   // with heap size set to 256mb
      final int FLOOR = 3000;
      if (maxGuess >= MAX_POSSIBLE) {
         maxGuess = FLOOR;
      }
      int best = 0;
      System.out.println("\nDetermining maximum matSize...");
      int low = maxGuess - (MAX_POSSIBLE - maxGuess) + 1;   // use guess
      int high = MAX_POSSIBLE;
      while (low <= high) {
         matSize = (low + high) / 2;
         mat = null;
         matAns = null;
         try {
            mat = new double[matSize][matSize];
            matAns = new double[matSize][matSize];
            if (matSize > best) {
               best = matSize;
            }
            low = matSize + 1;
         } catch (OutOfMemoryError e) {
            high = matSize - 1;
            if (best == 0)
               low = FLOOR;
         }
      }
      System.out.println("MaxSize: " + best);
      
      
      System.out.println("\nDetermining minimum matSize...");
      matSize = minGuess;
 
      for (; matSize > 0; matSize--) {
         mat = new double[matSize][matSize];
         matAns = new double[matSize][matSize];
 
         long startTime = System.nanoTime();
         matMult(mat, mat, matAns);
         long stopTime = System.nanoTime();
 
         System.out.println("Size = " + matSize + " MatMult Elapsed Time: "
               + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
 
         if ((stopTime - startTime) < 1e5) {
            break;
         }
      }
      System.out.println("MinSize: " + matSize);
   }
 
   public static void partA_genData() {
      for (int matSize = 100; matSize <= 3200; matSize *= 2) {
         partA_slave(matSize);
      }
   }
   
   public static void partA_slave(int matSize) {
      int r, randRow, randCol;
      long startTime, stopTime;
      double smallPercent;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
 
      // dynamic matrices
      double[][] mat, matAns;
 
      // allocate matrices
      mat = new double[matSize][matSize];
      matAns = new double[matSize][matSize];
 
      // generate small% of non-default values bet 0 and 1
      smallPercent = matSize / 10. * matSize;
      for (r = 0; r < smallPercent; r++) {
         randRow = (int) (Math.random() * matSize);
         randCol = (int) (Math.random() * matSize);
         mat[randRow][randCol] = Math.random();
      }
 
      // 10x10 submatrix in lower right (10% sparseness)
      if (matSize <= 100) {
         System.out.println("\nMultiplier Submatrix:");
         matShow(mat, matSize - 10, 10);
      }
 
      startTime = System.nanoTime();
      matMult(mat, mat, matAns);
      stopTime = System.nanoTime();
 
      if (matSize <= 100) {
         System.out.println("\nProduct Submatrix:");
         matShow(matAns, matSize - 10, 10);
      }
 
      System.out.println("Size = " + matSize + " Mat. Mult. Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
   }
 
   public static void
         matMult(double[][] matA, double[][] matB, double[][] matP) {
 
      if (matA.length != matB[0].length || matA[0].length != matB.length
            || matP.length != matA.length || matP[0].length != matB[0].length)
         return;
 
      int rows = matA.length;
      int cols = matB[0].length;
 
      // zero out product matrix
      for (int r = 0; r < rows; r++) {
         for (int c = 0; c < cols; c++) {
            matP[r][c] = 0;
         }
      }
 
      for (int r = 0; r < rows; r++) {
         for (int ac = 0; ac < cols; ac++) {
            for (int bc = 0; bc < cols; bc++) {
               matP[r][bc] += matA[r][ac] * matB[ac][bc];
            }
         }
      }
   }
 
   public static void matShow(double[][] mat, int start, int size) {
      if (start < 0 || size < 0 || start + size > mat.length)
         return;
 
      for (int r = start; r < start + size; r++) {
         for (int c = start; c < start + size; c++) {
            System.out.printf("%6.1f ", mat[r][c]);
         }
         System.out.println();
      }
   }
 
   public static void partB_proof() {
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      // allocate the regular matrices for proof of correctness
      int matSize = 5;
      double[][] matAns = new double[matSize][matSize];
      double[][] matA = {{1, 2, 3, 4, 5},
                         {-1, -2, -3, -4, -5},
                         {1, 3, 1, 3, 1},
                         {0, 1, 0, 1, 0},
                         {-1, -1, -1, -1, -1}};
      double[][] matB = {{2, 1, 5, 0, 2},
                         {1, 4, 3, 2, 7},
                         {4, 4, 4, 4, 4},
                         {7, 1, -1, -1, -1},
                         {0, 0, 8, -1, -6}};
      
      System.out.println("Part B");
      System.out.println("\nProof of correctness for sparse matrix mult");
      System.out.println("-------------------------------------------");
     
      
      // 5 x 5 sparse matrix 
      SparseMatWMult sMatA = new SparseMatWMult(matSize, matSize);
      SparseMatWMult sMatB = new SparseMatWMult(matSize, matSize);
      SparseMatWMult sMatAns = new SparseMatWMult(matSize, matSize);
      
      for (int r=0; r < matSize; r++) {
         for (int c=0; c < matSize; c++) {
            sMatA.set(r, c, matA[r][c]);
            sMatB.set(r, c, matB[r][c]);
         }
      }
      
      System.out.println("\nMultiplicand sparse matrix:");
      sMatA.showSubSquare(0, matSize);
      
      System.out.println("\nMultiplier sparse matrix:");
      sMatB.showSubSquare(0, matSize);
      
      //sMatAns.matMult_naive(sMatA, sMatB);
      sMatAns.matMult_faster(sMatA, sMatB);
      
      System.out.println("\nProduct sparse matrix:");
      sMatAns.showSubSquare(0, matSize);
   }
 
   public static void partB() {
      double [] percents = {.002, .01, .05, .1};
      for (int i=0; i < percents.length; i++) {
         System.out.println("\n" + (percents[i] * 100) + "%:");
         System.out.println("-------------------------------------------");
         for (int size=100; size <= 1600; size*=2) {
            //if (i > 0 && size > 800) continue;
            partB_slave(size, percents[i]);
         }
      }
   }
   
   public static void partB_slave(int matSize, double percent) {
      int k, randRow, randCol;
      long startTime, stopTime;
      int count;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      // allocate the sparse matrices
      SparseMatWMult sMatA = new SparseMatWMult(matSize, matSize);
      SparseMatWMult sMatB = new SparseMatWMult(matSize, matSize);
      SparseMatWMult sMatAns = new SparseMatWMult(matSize, matSize);
      
      // ok, assuming all ok above, we're ready for timing
      // generate small% of non-default values bet 0 and 1
      count = (int)(percent * matSize * matSize);
      for (k = 0; k < count; k++) {
         randRow = (int)(Math.random() * matSize);
         randCol = (int)(Math.random() * matSize);
         sMatA.set(randRow, randCol, Math.random());
         sMatB.set(randRow, randCol, Math.random());
      }
      
      if (percent == .002 && matSize == 100) {
         // 10x10 submatrix 
         System.out.println("\nMultiplier Submatrix:");
         sMatA.showSubSquare(0, 10);
 
         // 10x10 submatrix 
         System.out.println("\nMultiplicand Submatrix:");
         sMatB.showSubSquare(0, 10);
      }
      
      startTime = System.nanoTime();
      //sMatAns.matMult_naive(sMatA, sMatB);
      sMatAns.matMult_faster(sMatA, sMatB);
      stopTime = System.nanoTime();
 
      if (percent == .002 && matSize == 100) {
         System.out.println("\nProduct Submatrix:");
         sMatAns.showSubSquare(0, 10);
      }
      
      System.out.println("Size = " + matSize + " Mat. Mult. Elapsed Time: "
         + tidy.format( (stopTime - startTime) / 1e9)
         + " seconds.");
   }
}
 
class SparseMatWMult extends SparseMat<Double> {
   // constructor
   public SparseMatWMult(int maxRows, int maxCols) {
      super(maxRows, maxCols, 0.0);
   }
   
   // multiply
   public boolean matMult_naive(SparseMatWMult matA, SparseMatWMult matB) {
      // rowSize and colSize already known to be >= 1 (constructor)
      if (matA.rowSize != matB.colSize || matA.colSize != matB.rowSize
            || rowSize != matA.rowSize || colSize != matB.colSize)
         return false;
      
      // product matrix (this object)
      allocateEmptyMatrix();
      
      // multiply
      for (int r=0; r < rowSize; r++) {
         FHlinkedList<MatNode> list = (FHlinkedList<MatNode>)matA.rows.get(r);
         // anything to do?
         if (list.size() == 0) 
            continue;
 
         ListIterator<MatNode> iter = list.listIterator();
         while (iter.hasNext()) {
            MatNode nodeA = iter.next();
            int col = nodeA.col;
            for (int c=0; c < colSize; c++) {
               double val = (Double)matB.get(col, c);
               if (val != 0.0) {
                   set(r, c, (Double)get(r, c) + nodeA.data * val);
               }
            }
         }
      }
      return true;
   }
   
   public boolean matMult_faster(SparseMatWMult matA, SparseMatWMult matB)
   {
      if (matA.rowSize != matB.colSize || matA.colSize != matB.rowSize
            || rowSize != matA.rowSize || colSize != matB.colSize)
         return false;
 
      clear();
      for (int r = 0; r < rowSize; r++) {
         for (MatNode nodeA : matA.rows.get(r)) {
            for (MatNode nodeB : matB.rows.get(nodeA.col)) {
               set(r, nodeB.col, get(r, nodeB.col) + nodeA.data * nodeB.data);
               // Even faster: use addTo instead of set
               //addTo(r, nodeB.col, nodeA.data * nodeB.data);
            }
         }
      }
 
      return true;
   }
}
 
class SparseMat<E> implements Cloneable {
   protected class MatNode implements Cloneable {
      public int col;
      public E data;
 
      MatNode() {
         col = 0;
         data = null;
      }
 
      MatNode(int col, E data) {
         this.col = col;
         this.data = data;
      }
 
      public Object clone() throws CloneNotSupportedException {
         // only shallow copy needed for MatNode
         MatNode newObject = (MatNode) super.clone();
         return newObject;
      }
 
      public String toString() {
         return "MatNode: col=" + col + ", data=" + data;
      }
   }
   
   protected FHarrayList<FHlinkedList<MatNode>> rows;
   protected int rowSize, colSize;
   protected E defaultVal;
 
   public SparseMat(int maxRows, int maxCols, E defaultVal) {
      if (maxRows < 1 || maxCols < 1)
         throw new IllegalArgumentException("dimensions must be positive");
      
      rowSize = maxRows;
      colSize = maxCols;
      this.defaultVal = defaultVal;
      allocateEmptyMatrix();
   }
 
   int getRowSize() { return rowSize; }
   int getColSize() { return colSize; }
   int getNumRows() { return rowSize; }
   int getNumCols() { return colSize; }
   
   protected void allocateEmptyMatrix() {
      rows = new FHarrayList<FHlinkedList<MatNode>>(rowSize);
      for (int k = 0; k < rowSize; k++) {
         rows.add(new FHlinkedList<MatNode>());
      }
   }
 
   void clear() {
      for (int r = 0; r < rowSize; r++) {
         rows.get(r).clear();
      }
   }
   
   void showSubSquare(int start, int size) {
      // This is mostly for debugging purposes since, in general, we
      // cannot see the entire matrix at once.
      if (start < 0 || size < 0 || start+size > rowSize || start+size > colSize)
         return;
      
      for (int r=start; r < start+size; r++) {
         for (int c=start; c < start+size; c++) {
            System.out.printf("%6.1f ", get(r, c));
         }
         System.out.println();
      }
   }
 
    public E get(int r, int c) {
      if (r < 0 || r >= rowSize || c < 0 || c >= colSize)
         throw new IndexOutOfBoundsException("Invalid parameter(s)");
 
      for (MatNode node : rows.get(r)) {
         if (node.col >= c) {
            if (node.col == c)
               return node.data;
            break;     // assumes sorted columns
         }
      }
 
      return defaultVal;
   }
 
   public boolean set(int r, int c, E x) {
      // If x is the default value, remove any existing node at that column.
      // Otherwise, if necessary, create a node for column c, and store x in it.
      if (r < 0 || r >= rowSize || c < 0 || c >= colSize)
         return false;
 
      ListIterator<MatNode> iter = rows.get(r).listIterator();
 
      MatNode node = null;
      while (iter.hasNext()) {
         node = iter.next();
         if (node.col >= c) {
            if (node.col > c) { // maintain sorting order
               break;
            }
            if (x.equals(defaultVal))
               iter.remove();
            else
               node.data = x;
            return true;
         }
      }
 
      if (!x.equals(defaultVal)) {
         if (node != null && node.col > c)
            iter.previous();
         iter.add(new MatNode(c, x));
      }
 
      return true;
   }
   
   public boolean addTo(int r, int c, E x) {
      // If x is the default value, remove any existing node at that column.
      // Otherwise, if necessary, create a node for column c, and store x in it.
      if (r < 0 || r >= rowSize || c < 0 || c >= colSize)
         return false;
 
      MatNode node = null;
      
      ListIterator<MatNode> iter = rows.get(r).listIterator();
      while (iter.hasNext()) {
         node = iter.next();
         
         if (node.col >= c) {
            if (node.col > c) { // maintain sorting order
               break;
            }
            x = (E)(Double)((Double)node.data + (Double)x);
            if (x.equals(defaultVal))
               iter.remove();
            else
               node.data = x;
            return true;
         }
      }
 
      if (!x.equals(defaultVal)) {
         if (node != null && node.col > c)
            iter.previous();
         iter.add(new MatNode(c, x));
      }
 
      return true;
   }
 
   public Object clone() throws CloneNotSupportedException {
      // shallow copy
      SparseMat<E> newMat = (SparseMat<E>) super.clone();
      newMat.allocateEmptyMatrix();
 
      // deep copy
      ListIterator<FHlinkedList<MatNode>> newRowIter =
            newMat.rows.listIterator();
      
      ListIterator<FHlinkedList<MatNode>> rowIter = rows.listIterator();
      while (rowIter.hasNext()) {
         FHlinkedList<MatNode> newRow = newRowIter.next();
         ListIterator<MatNode> colIter = rowIter.next().listIterator();
         while (colIter.hasNext()) {
            newRow.add((MatNode) (colIter.next().clone()));
         }
      }
 
      return newMat;
   }
}
 
/*
Part A
 
Proof of correctness for matrix mult
------------------------------------
Matrix A:
   1.0    2.0    3.0    4.0    5.0 
  -1.0   -2.0   -3.0   -4.0   -5.0 
   1.0    3.0    1.0    3.0    1.0 
   0.0    1.0    0.0    1.0    0.0 
  -1.0   -1.0   -1.0   -1.0   -1.0 
 
Matrix B:
   2.0    1.0    5.0    0.0    2.0 
   1.0    4.0    3.0    2.0    7.0 
   4.0    4.0    4.0    4.0    4.0 
   7.0    1.0   -1.0   -1.0   -1.0 
   0.0    0.0    8.0   -1.0   -6.0 
 
Dot Product:
  44.0   25.0   59.0    7.0   -6.0 
 -44.0  -25.0  -59.0   -7.0    6.0 
  30.0   20.0   23.0    6.0   18.0 
   8.0    5.0    2.0    1.0    6.0 
 -14.0  -10.0  -19.0   -4.0   -6.0 
 
Determining maximum matSize...
MaxSize: 3924
 
Determining minimum matSize...
Size = 16 MatMult Elapsed Time: 0.0003 seconds.
Size = 15 MatMult Elapsed Time: 0.0003 seconds.
Size = 14 MatMult Elapsed Time: 0.0002 seconds.
Size = 13 MatMult Elapsed Time: 0.0002 seconds.
Size = 12 MatMult Elapsed Time: 0.0001 seconds.
Size = 11 MatMult Elapsed Time: 0.0001 seconds.
MinSize: 11
 
Multiplier Submatrix:
   0.0    0.0    0.1    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.5    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.5    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.5    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.7    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.7    0.0    0.0    0.9    0.0    0.4    0.0 
   0.0    0.0    0.0    0.4    0.0    0.0    0.0    0.0    0.0    0.7 
 
Product Submatrix:
   0.3    0.4    0.0    0.7    0.3    0.0    0.0    0.0    0.0    1.0 
   0.0    0.0    0.6    0.9    0.1    0.1    0.0    1.4    0.5    0.1 
   0.0    0.8    0.2    0.0    0.0    0.8    0.0    0.0    0.0    0.4 
   0.1    0.3    0.0    1.1    0.7    0.7    0.6    0.3    0.4    0.0 
   0.0    0.1    0.0    0.1    0.2    0.7    0.0    0.0    0.1    0.0 
   0.0    0.0    0.0    0.0    0.8    0.0    0.6    0.4    0.0    0.0 
   0.0    0.4    0.0    0.8    0.0    0.0    0.0    0.2    0.1    0.6 
   0.0    0.5    0.1    0.2    0.6    0.8    0.6    0.0    0.1    0.4 
   0.6    0.0    0.7    1.0    0.0    0.4    0.5    0.3    0.9    0.1 
   0.0    0.0    0.4    0.6    0.2    0.0    0.0    0.0    0.8    0.7 
Size = 100 Mat. Mult. Elapsed Time: 0.0104 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0109 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.076 seconds.
Size = 800 Mat. Mult. Elapsed Time: 0.8794 seconds.
Size = 1600 Mat. Mult. Elapsed Time: 7.3719 seconds.
Size = 3200 Mat. Mult. Elapsed Time: 56.9615 seconds.
 
Run #2:
 
Size = 100 Mat. Mult. Elapsed Time: 0.0159 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0167 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.0976 seconds.
Size = 800 Mat. Mult. Elapsed Time: 0.8624 seconds.
Size = 1600 Mat. Mult. Elapsed Time: 7.0155 seconds.
Size = 3200 Mat. Mult. Elapsed Time: 55.0579 seconds.
 
Part A Analysis:
 
Time Complexity
 
Matrix multiply is O(N-cubed):
N operations are needed to multiply 1 row by 1 column (inner loop)
Each column (N) must be multiplied by each row (middle loop)
Finally, there are N rows in the matrix (outer loop).
So, there are N x N x N (or N^3) operations.
 
Both Big-Oh and Theta-Oh are both N-cubed since there are exactly
N-cubed operations, independent of the sparseness of the data.
 
Reasoning:
If time is proportional to M^3, then doubling M means time is
proportional to (2M)^3, which is the same as 2^3 * M^3. Since 2^3 is 8,
the growth rate is 8 * M^3.
So, increasing the size by 2x should result in increasing the time by 8x.
 
From the table above:
 0.0167/0.0159 = 1.050
 0.0967/0.0167 = 5.844 
 0.8624/0.0967 = 8.918
 7.0155/0.8624 = 8.130
55.0579/7.0155 = 7.848
The ratios shown are reasonably close to the expected value (8).
-------------------------------------------------------------
 
Part B
 
Proof of correctness for sparse matrix mult
-------------------------------------------
Multiplicand sparse matrix:
   1.0    2.0    3.0    4.0    5.0 
  -1.0   -2.0   -3.0   -4.0   -5.0 
   1.0    3.0    1.0    3.0    1.0 
   0.0    1.0    0.0    1.0    0.0 
  -1.0   -1.0   -1.0   -1.0   -1.0 
 
Multiplier sparse matrix:
   2.0    1.0    5.0    0.0    2.0 
   1.0    4.0    3.0    2.0    7.0 
   4.0    4.0    4.0    4.0    4.0 
   7.0    1.0   -1.0   -1.0   -1.0 
   0.0    0.0    8.0   -1.0   -6.0 
 
Product sparse matrix:
  44.0   25.0   59.0    7.0   -6.0 
 -44.0  -25.0  -59.0   -7.0    6.0 
  30.0   20.0   23.0    6.0   18.0 
   8.0    5.0    2.0    1.0    6.0 
 -14.0  -10.0  -19.0   -4.0   -6.0 
 
Multiply Example shown in first multiply:
 
0.2%:
-------------------------------------------
 
Multiplier Submatrix:
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
 
Multiplicand Submatrix:
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
 
Product Submatrix:
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
   0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0    0.0 
Size = 100 Mat. Mult. Elapsed Time: 0.0002 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0004 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.0014 seconds.
Size = 800 Mat. Mult. Elapsed Time: 0.0109 seconds.
Size = 1600 Mat. Mult. Elapsed Time: 0.0355 seconds.
Size = 3200 Mat. Mult. Elapsed Time: 0.058 seconds.
Size = 6400 Mat. Mult. Elapsed Time: 1.3862 seconds.
Size = 12800 Mat. Mult. Elapsed Time: 19.823 seconds.
 
1.0%:
-------------------------------------------
Size = 100 Mat. Mult. Elapsed Time: 0.0001 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0001 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.001 seconds.
Size = 800 Mat. Mult. Elapsed Time: 0.0164 seconds.
Size = 1600 Mat. Mult. Elapsed Time: 0.203 seconds.
Size = 3200 Mat. Mult. Elapsed Time: 6.8053 seconds.
 
5.0%:
-------------------------------------------
Size = 100 Mat. Mult. Elapsed Time: 0.0003 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0033 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.0632 seconds.
Size = 800 Mat. Mult. Elapsed Time: 1.7236 seconds.
Size = 1600 Mat. Mult. Elapsed Time: 35.1911 seconds.
Size = 3200 Mat. Mult. Elapsed Time: 1,305.2175 seconds.
 
10.0%:
-------------------------------------------
Size = 100 Mat. Mult. Elapsed Time: 0.0013 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0217 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.6267 seconds.
Size = 800 Mat. Mult. Elapsed Time: 9.056 seconds.
Size = 1600 Mat. Mult. Elapsed Time: 171.0296 seconds.
Size = 3200 Mat. Mult. Elapsed Time: 4,375.3453 seconds.
Done.
 
 
Part B
 
Analysis:
 
Are the times longer or shorter than for dynamic matrices?
> It depends on the sparseness. For .2% and 1%, the times are shorter,
> For 5% and 10%, the times are longer.
 
Are the growth rates larger or smaller?  By how much?
> Growth rates are larger for sparse matrices
(double for 1% (16x), triple for 10% (25x))
 
Create a table and answer the same questions as before.
>
   See below.
   
What was the smallest M that gave you a non-zero time?
>
   100 at 1%
   
Give several M values and their times in a table.
>
   
Try different sparseness values: 0.2% 1%  5% 10%, and report how
the growth rates behave in each case.
>
At .2% sparseness:
Size = 100 Mat. Mult. Elapsed Time: 0.0002 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0003 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.0013 seconds.
Size = 800 Mat. Mult. Elapsed Time: 0.0124 seconds.
Size = 1600 Mat. Mult. Elapsed Time: 0.0315 seconds.
Size = 3200 Mat. Mult. Elapsed Time: 0.0739 seconds.
Size = 6400 Mat. Mult. Elapsed Time: 2.6504 seconds.
Size = 12800 Mat. Mult. Elapsed Time: 19.8829 seconds.
 
At 1% sparseness:
Size = 100 Mat. Mult. Elapsed Time: 0.0001 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0001 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.001 seconds.
Size = 800 Mat. Mult. Elapsed Time: 0.0164 seconds.
Size = 1600 Mat. Mult. Elapsed Time: 0.203 seconds.
Size = 3200 Mat. Mult. Elapsed Time: 6.8053 seconds.
 
Growth ranged from 1x to 33.5x for an average of about 16x:
 0.0001/0.0001 = 1.0x
 0.0010/0.0001 = 10x
 0.0164/0.0010 = 16x
 0.2030/0.0164 = 12x
 6.8053/0.2030 = 33.5x
 
At 5% sparseness:
Size = 100 Mat. Mult. Elapsed Time: 0.0003 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0033 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.0632 seconds.
Size = 800 Mat. Mult. Elapsed Time: 1.7236 seconds.
Size = 1600 Mat. Mult. Elapsed Time: 35.1911 seconds.
Size = 3200 Mat. Mult. Elapsed Time: 1,305.2175 seconds.
 
At 10% sparseness:
Size = 100 Mat. Mult. Elapsed Time: 0.0013 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0217 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.6267 seconds.
Size = 800 Mat. Mult. Elapsed Time: 9.056 seconds.
Size = 1600 Mat. Mult. Elapsed Time: 171.0296 seconds.
Size = 3200 Mat. Mult. Elapsed Time: 4,375.3453 seconds
 
Growth ranged from 14x to 46x for an average of perhaps 25x:
 0.0217/0.0013 = 17x
 0.6267/0.0217 = 46x
 9.0560/0.6267 = 14x
 171.0/9.1     = 19x
 4,375/171     = 26x
  
What happened when you doubled M, tripled it, quadrupled it, etc?
   Of course, times got longer as size increased. However, as reported
   above, the growth rate was not M-cubed, as expected.  It was closer
   to 20x than 8x.
 
How large an M can you use before the program refuses to run
(exception or run-time error due to memory overload)
or it takes so long you can't wait for the run?
>  
   At 10% sparseness, at M=1600, multiply took almost 3 minutes
   so I'd say about 1600.
   
   At 1% sparseness, at M=3200, multiply took only 6 seconds
   so I'd say perhaps 6400. 
   
   At .2% sparseness, at M=12800, multiply took 19 seconds
   so I wouldn't want to try more than about 15000.
   
   Memory overload would not be expected to be a problem when matrices are
   very sparse since not much memory is used.  Memory overload is not much
   of a problem at 10% either.  It takes too long to multiply (due to
   overhead of list look-ups) before memory overload becomes an issue.
   
How did the data agree or disagree with your original time complexity
estimate? 
>
   It grew faster than I originally thought it would.
   I estimated that it would increase 8-fold with each doubling of the
   matrix size (N) since the time complexity was determined to be N-cubed.
   However, it appears to have averaged about a 20-fold increase.
   
   This probably means that memory usage is not efficient.  Perhaps I could
   come closer to 8x growth with some more work on memory optimization.
   I suspect the longer than expected times are due to either system calls
   (new) to allocate memory or page faults during processing since each node
   is individually allocated.  The general plan would be to allocate a large
   block of nodes so they would be concentrated in as few pages as possible.
   This would also solve the system call problem since there would only be
   one allocation. Then, I would just need to call my allocator instead of new.
 
What was the largest M you could use here, and was the reason the same
or different than for dynamic arrays?
>
   For sparse arrays, the largest M is about 1600 for 10%, 2000 for 5%,
   3200 for 1% and 15000 for 0.2%
   
   These data make sense for sparse arrays, since the sparser the data,
   the less operations are performed, the less time it should take and so,
   the larger the matrix size before it takes too long.
   
   For dynamic arrays, the largest M was about 3200 (55 seconds) after which
   it would take too much time (roughly 8 minutes).
 
   This makes sense for dynamic arrays, since sparseness has no influence.
   The same number of operations are completed no matter how sparse the data.
*/

Comments
Hi Kevin,
 
Ok, your multiply works.
I'll provide some feedback for you:
 
matMult: You didn't validate your parameters.
         You didn't zero your matrix. What happens if you call it again?
         Your tests are not sufficient to constitute a proof that it works.
-3
 
Part A
Estimating Time Complexity: Good
Tabled Data: Good
 
Questions:
1. What was the smallest M that gave you a non-zero time?
2. What happened when you doubled M, tripled it, quadrupled it, etc?  Give several M values and their times in a table.
3. How large an M can you use before the program refuses to run (exception or run-time error due to memory overload) or it takes so long you can't wait for the run?
4. How did the data agree or disagree with your original time complexity estimate?
 
1 - 12 ok.
2 - Ok
3 - 3200, hmm, seems too small (How much total memory do your matrices require?)
4 - ok
 
Late: -1
Score: 16
