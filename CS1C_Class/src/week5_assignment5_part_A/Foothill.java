package week5_assignment5_part_A;

/**
 * CS 1C Assignment 5 - Splay Tree
 * ------------------------------------------------------
 * Name : Muhammad Rizky Wellyanto
 * CWID : 20144588
 * Parts : A
 * Description : This is the fifth assignment of CS_1C
 * ML F14. This program implements the Splay Tree Data structure.
 * this data structure is a variation of tree, which put the recent
 * active node as the root of the tree by tree rotation methods.
 */

import cs_1c.*;

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print(x + " ");
   }
};

public class Foothill
{
   public static void main(String[] args) throws Exception
   {
      int k;
      FHsplayTree<Integer> searchTree = new FHsplayTree<Integer>();
      PrintObject<Integer> intPrinter = new PrintObject<Integer>();
      
      searchTree.traverse(intPrinter);
      
      System.out.println("Initial size: " + searchTree.size());
      
      // test insert()
      System.out.println("------------------Test insert------------------");
      for (k = 1; k <= 32; k++)
         searchTree.insert(k);
      System.out.println("New size: " + searchTree.size());
      
      System.out.println("\nTraversal");
      searchTree.traverse(intPrinter);
      System.out.println();
      
      // test find()
      System.out.println("------------------Test Find------------------");
      for (k = -1; k < 10; k++)
      {
         try
         {
            searchTree.find(k);
         }
         catch (Exception e)
         {
            System.out.println(" oops ");
         }
         System.out.println("splay " + k + " --> root: "
               + searchTree.showRoot() + " height: " + searchTree.showHeight());
      }
      
      // test remove ()
      System.out.println("------------------Test Remove----------------");
      for (k = 20; k > 0; k -= 2)
      {
         if (!searchTree.remove(k))
            System.out.println(" oops ");
         else
            System.out.println("remove and splay " + k + " --> root: "
                  + searchTree.showRoot() + " height: "
                  + searchTree.showHeight());
      }
   }
}

class FHsplayTree<E extends Comparable<? super E>> extends FHsearch_tree<E>
{
   // public methods
   public boolean insert(E x)
   {
      FHs_treeNode<E> tempNode;
      int compareResult;
      
      if (this.mRoot == null)
      {
         this.mRoot = new FHs_treeNode<E>(x, null, null);
         this.mSize++;
         return true;
      }
      
      this.mRoot = splay(this.mRoot, x);
      
      compareResult = x.compareTo(this.mRoot.data);
      
      if (compareResult < 0)
      {
         tempNode = new FHs_treeNode<E>(x, this.mRoot.lftChild, this.mRoot);
         this.mRoot.lftChild = null;
      }
      else if (compareResult > 0)
      {
         tempNode = new FHs_treeNode<E>(x, this.mRoot, this.mRoot.rtChild);
         this.mRoot.rtChild = null;
      }
      else
      {
         return false;
      }
      
      this.mRoot = tempNode;
      this.mSize++;
      return true;
   }
   
   public boolean remove(E x)
   {
      FHs_treeNode<E> tempNode;
      int compareResult;
      
      if (this.mRoot == null)
         return false;
      
      this.mRoot = splay(this.mRoot, x);
      
      compareResult = x.compareTo(this.mRoot.data);
      
      if (compareResult != 0)
         return false;
      
      if (this.mRoot.lftChild == null)
      {
         tempNode = this.mRoot.rtChild;
      }
      else
      {
         tempNode = splay(this.mRoot.lftChild, x);
         tempNode.rtChild = this.mRoot.rtChild;
      }
      
      this.mSize--;
      this.mRoot = tempNode;
      return true;
      
   }
   
   public E showRoot()
   {
      if (this.mRoot == null)
         return null;
      else
         return this.mRoot.data;
   }
   
   // rotate and splay methods
   /**
    * the main splay method of the program
    * 
    * @param root
    * @param x
    * @return
    */
   protected FHs_treeNode<E> splay(FHs_treeNode<E> root, E x)
   {
      FHs_treeNode<E> 
         rightTreeRoot = null, 
         leftTreeRoot = null, 
         rightTreeEnd = null, 
         leftTreeEnd = null;
      int compareResult;
      
      while (root != null)
      {
         compareResult = x.compareTo(root.data);
         
         // x on the left relative to root
         if (compareResult < 0)
         {
            if (root.lftChild == null)
               break;
            
            // zig-zig case
            if (x.compareTo(root.lftChild.data) < 0)
            {
               root = rotateWithLeftChild(root);
               if (root.lftChild == null)
                  break;
            }
            
            // move root to right tree
            if (rightTreeRoot == null)
               rightTreeRoot = root;
            else
               rightTreeEnd.lftChild = root;
            rightTreeEnd = root;
            root = root.lftChild;
            
         }
         // x on the right relative to root
         else if (compareResult > 0)
         {
            if (root.rtChild == null)
               break;
            
            // zig-zig case
            if (x.compareTo(root.rtChild.data) > 0)
            {
               root = rotateWithRightChild(root);
               if (root.rtChild == null)
                  break;
            }
            
            // move root to left tree
            if (leftTreeRoot == null)
               leftTreeRoot = root;
            else
               leftTreeEnd.rtChild = root;
            leftTreeEnd = root;
            root = root.rtChild;
         }
         else
            break;
      }
      
      // relocate root's child and connect the trees
      if (leftTreeRoot != null)
      {
         leftTreeEnd.rtChild = root.lftChild;
         root.lftChild = leftTreeRoot;
      }
      if (rightTreeRoot != null)
      {
         rightTreeEnd.lftChild = root.rtChild;
         root.rtChild = rightTreeRoot;
      }
      
      return root;
   }
   
   protected FHs_treeNode<E> rotateWithLeftChild(FHs_treeNode<E> k2)
   {
      FHs_treeNode<E> k1 = k2.lftChild;
      k2.lftChild = k1.rtChild;
      k1.rtChild = k2;
      return k1;
   }
   
   protected FHs_treeNode<E> rotateWithRightChild(FHs_treeNode<E> k2)
   {
      FHs_treeNode<E> k1 = k2.rtChild;
      k2.rtChild = k1.lftChild;
      k1.lftChild = k2;
      return k1;
   }
   
   protected FHs_treeNode<E> find(FHs_treeNode<E> root, E x)
   {
      int compareResult;
      
      this.mRoot = splay(this.mRoot, x);
      
      compareResult = x.compareTo(this.mRoot.data);
      
      if (compareResult != 0)
         return null;
      
      return this.mRoot;
   }
}

/*-----------------------------SAMPLE RUN---------------------------------------

Initial size: 0
------------------Test insert------------------
New size: 32

Traversal
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
 31 32 
------------------Test Find------------------
 oops 
splay -1 --> root: 1 height: 16
 oops 
splay 0 --> root: 1 height: 16
splay 1 --> root: 1 height: 16
splay 2 --> root: 2 height: 9
splay 3 --> root: 3 height: 6
splay 4 --> root: 4 height: 6
splay 5 --> root: 5 height: 5
splay 6 --> root: 6 height: 6
splay 7 --> root: 7 height: 6
splay 8 --> root: 8 height: 7
splay 9 --> root: 9 height: 8
------------------Test Remove----------------
remove and splay 20 --> root: 19 height: 10
remove and splay 18 --> root: 17 height: 9
remove and splay 16 --> root: 15 height: 10
remove and splay 14 --> root: 13 height: 9
remove and splay 12 --> root: 11 height: 9
remove and splay 10 --> root: 9 height: 10
remove and splay 8 --> root: 7 height: 11
remove and splay 6 --> root: 5 height: 12
remove and splay 4 --> root: 3 height: 13
remove and splay 2 --> root: 1 height: 14

------------------------------------------------------------------------------*/