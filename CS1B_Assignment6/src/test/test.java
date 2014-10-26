package test;

/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 6 in CS 1B Jesse Cecil Winter 2014
 * this program is called GUI Cards. this program test the knowledge of multi
 * class programming with with the combination of inheritance and Graphics.
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

public class test
{
   public static void main(String[] args) throws Exception
   {
      createLine(80, "=");
      System.out.println("RUN 2 -> Integers");
      createLine(80, "=");
      
      FHsdTree card_tree = new FHsdTree();
      FHsdTreeNode card_Node;
      
      System.out.println("Starting tree empty? " + card_tree.empty() + "\n");
      // create a main root
      card_Node = card_tree.addChild(null, 1);
      
      // add Bunch of numbers...
      card_tree.addChild(card_Node, 2);
      card_tree.addChild(card_Node, 3);
      card_tree.addChild(card_Node, 4);
      card_Node = card_tree.find(5);
      card_Node = card_tree.addChild(card_Node, 6);
      card_Node = card_tree.addChild(card_Node, 7);
      card_Node = card_tree.addChild(card_Node, 8);
      card_tree.addChild(card_Node, 9);
      card_tree.addChild(card_Node, 10);
      card_tree.addChild(card_Node, 11);
      card_tree.addChild(card_Node, 12);
      card_tree.addChild(card_Node, 13);
      card_Node = card_tree.find(14);
      card_Node = card_tree.find(card_Node, 15, 0);
      card_Node = card_tree.addChild(card_Node, 16);
      card_Node = card_tree.addChild(card_Node, 17);
      card_tree.addChild(card_Node, 18);
      card_tree.addChild(card_Node, 19);
      card_tree.addChild(card_Node, 20);
      card_tree.addChild(card_Node, 21);
      card_tree.addChild(card_Node, 22);
      card_Node = card_tree.find(23);
      card_Node = card_tree.addChild(card_Node, 24);
      card_tree.addChild(card_Node, "right front paw");
      card_tree.addChild(card_Node, "left front paw");
      card_tree.addChild(card_Node, "right rear paw");
      card_tree.addChild(card_Node, "left rear paw");
      card_tree.addChild(card_Node, "spare mutant paw");
      card_tree.addChild(card_Node, "wagging tail");
      
      // add some parts to table
      card_Node = card_tree.find("table");
      card_tree.addChild(card_Node, "north east leg");
      card_tree.addChild(card_Node, "north west leg");
      card_tree.addChild(card_Node, "south east leg");
      card_tree.addChild(card_Node, "south west leg");
      
      System.out.println("\n------------ Loaded Tree ----------------- \n");
      card_tree.display();
      
      card_tree.remove("spare mutant paw");
      card_tree.remove("Miguel the human");
      card_tree.remove("an imagined higgs boson");
      
      System.out
            .println("\n------------ Virtual (soft) Tree --------------- \n");
      card_tree.display();
      
      System.out
            .println("\n----------- Physical (hard) Display ------------- \n");
      card_tree.displayPhysical();
      
      System.out
            .println("------- Testing Sizes (compare with above) -------- \n");
      System.out.println("virtual (soft) size: " + card_tree.size());
      System.out.println("physical (hard) size: " + card_tree.sizePhysical());
      
      System.out.println("------------ Collecting Garbage ---------------- \n");
      System.out.println("found soft-deleted nodes? "
            + card_tree.collectGarbage());
      System.out.println("immediate collect again? "
            + card_tree.collectGarbage());
      
      System.out
            .println("--------- Hard Display after garb col ------------ \n");
      
      card_tree.displayPhysical();
      
      card_tree.remove("room");
      
      System.out.println("Semi-deleted tree empty? " + card_tree.empty()
            + "\n");
      
      // empty the garbage afterward
      card_tree.collectGarbage();
      
      System.out.println("Completely-deleted tree empty? " + card_tree.empty()
            + "\n");
      
      card_tree.display();
      
   }
   
   /**
    * this method is just to make the program look "beautiful"
    * 
    * @param length the length of the line
    * @param shape the shape of the caption
    */
   private static void createLine(int length, String shape)
   {
      for (int counter = 0; counter < length; counter += 1)
      {
         System.out.print(shape);
      }
   }
}

class FHsdTreeNode<E>
{
   // use protected access so the tree, in the same package,
   // or derived classes can access members
   protected FHsdTreeNode<E> firstChild, sib, prev;
   protected E data;
   protected FHsdTreeNode<E> myRoot;  // needed to test for certain error
   protected boolean deleted;
   
   public FHsdTreeNode(E d, FHsdTreeNode<E> sb, FHsdTreeNode<E> chld,
         FHsdTreeNode<E> prv)
   {
      firstChild = chld;
      sib = sb;
      prev = prv;
      data = d;
      myRoot = null;
      deleted = false;
   }
   
   public FHsdTreeNode()
   {
      this(null, null, null, null);
   }
   
   public E getData()
   {
      return data;
   }
   
   // for use only by FHtree (default access)
   protected FHsdTreeNode(E d, FHsdTreeNode<E> sb, FHsdTreeNode<E> chld,
         FHsdTreeNode<E> prv, FHsdTreeNode<E> root)
   {
      this(d, sb, chld, prv);
      myRoot = root;
   }
}

class FHsdTree<E> implements Cloneable
{
   protected int mSize;
   protected FHsdTreeNode<E> mRoot;
   
   public FHsdTree()
   {
      clear();
   }
   
   public boolean empty()
   {
      return (mSize == 0);
   }
   
   public int size()
   {
      return size(mRoot, 1); // root is counted as one, so start from one
   }
   
   private int size(FHsdTreeNode<E> treeNode, int startSize)
   {
      FHsdTreeNode<E> child;
      int virtSize = startSize;
      
      // if tree is empty return 0 rightaway
      if (treeNode == null)
         return 0;
      
      // recursive step done here
      for (child = treeNode.firstChild; child != null; child = child.sib)
      {
         if (child.deleted == false)
         {
            virtSize += size(child, 1);
         }
      }
      
      return virtSize;
   }
   
   public void clear()
   {
      mSize = 0;
      mRoot = null;
   }
   
   public FHsdTreeNode<E> find(E x)
   {
      return find(mRoot, x, 0);
   }
   
   public void display()
   {
      display(mRoot, 0);
   }
   
   public boolean remove(E x)
   {
      return remove(mRoot, x);
   }
   
   /*
    * not much difference from old add_child(). In particular, we do not get
    * fancy and try to see if the data is already in the tree as deleted and get
    * clever. We always add the node, exactly as if this were an ordinary tree.
    */
   public FHsdTreeNode<E> addChild(FHsdTreeNode<E> treeNode, E x)
   {
      // empty tree? - create a root node if user passes in null
      if (mSize == 0)
      {
         if (treeNode != null)
            return null; // error something's fishy. treeNode can't right
         mRoot = new FHsdTreeNode<E>(x, null, null, null);
         mRoot.myRoot = mRoot;
         mSize = 1;
         return mRoot;
      }
      if (treeNode == null)
         return null; // error inserting into non_null tree with a null parent
      if (treeNode.myRoot != mRoot)
         return null;  // silent error, node does not belong to this tree
         
      // push this node into the head of the sibling list; adjust prev pointers
      FHsdTreeNode<E> newNode = new FHsdTreeNode<E>(x, treeNode.firstChild,
            null, treeNode, mRoot);  // sb, chld, prv, rt
      treeNode.firstChild = newNode;
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      ++mSize;
      return newNode;
   }
   
   /*
    * Two versions, just like the old implementation: one non-recursive takes
    * only the data, x, and the other which takes two more parameters for
    * recursion. The non-recursive calls the recursive. The recursive method
    * should check the deleted flag of any data match (a "found" x) and, if
    * true, should return as if the data were not in the tree. If the deleted
    * flag is false, then you would return the node as before, a "good find".
    */
   public FHsdTreeNode<E> find(FHsdTreeNode<E> root, E x, int level)
   {
      FHsdTreeNode<E> retval;
      
      if (mSize == 0 || root == null)
         return null;
      
      if (root.data.equals(x) && root.deleted != true)
         return root;
      
      // otherwise, recurse. don't process sibs if this was the original call
      if (level > 0 && (retval = find(root.sib, x, level)) != null)
         return retval;
      
      return find(root.firstChild, x, ++level);
   }
   
   /*
    * Two versions, just like the previous implementation of a general tree,
    * however, the deleted flag of the appropriate node is set to true. The node
    * is not physically removed from the tree. Note the meaning: this has the
    * same meaning as the old remove(). If a node is marked as deleted, then the
    * entire child sub-tree is considered gone. Those nodes, while not marked
    * themselves (big error to waste time marking them), are no longer in the
    * tree. Caution: While you might think it best to mark the first_child of
    * the deleted node to null, thereby allowing the Java garbage collector to
    * get rid of that subtree for you, do not do this. Leave the first_child,
    * and all the children still in the physical tree. In CS 1C, we will see how
    * we can reuse deleted nodes left in the tree to save work. This forces us
    * to write our other methods (like display() and find()) to obey the deleted
    * flag and consciously skip processing children of a deleted node.
    */
   public boolean remove(FHsdTreeNode<E> root, E x)
   {
      FHsdTreeNode<E> tn = null;
      
      if (mSize == 0 || root == null)
         return false;
      
      if ((tn = find(root, x, 0)) != null)
      {
         flagNode(tn);
         return true;
      }
      return false;
   }
   
   private void flagNode(FHsdTreeNode<E> nodeToDelete)
   {
      // if the tree or subtree is empty, return
      if (nodeToDelete == null || mRoot == null)
         return;
      
      // weird error, node does not belong to this tree, return
      if (nodeToDelete.myRoot != mRoot)
         return;
      
      // flag the node to as deleted
      nodeToDelete.deleted = true;
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      FHsdTree<E> newObject = (FHsdTree<E>) super.clone();
      newObject.clear();  // can't point to other's data
      
      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      newObject.setMyRoots(newObject.mRoot);
      
      return newObject;
   }
   
   private FHsdTreeNode<E> cloneSubtree(FHsdTreeNode<E> root)
   {
      FHsdTreeNode<E> newNode;
      if (root == null)
         return null;
      
      // does not set myRoot which must be done by caller
      newNode = new FHsdTreeNode<E>(root.data, cloneSubtree(root.sib),
            cloneSubtree(root.firstChild), null);
      
      // the prev pointer is set by parent recursive call ... this is the code:
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      if (newNode.firstChild != null)
         newNode.firstChild.prev = newNode;
      return newNode;
   }
   
   // recursively sets all myRoots to mRoot
   private void setMyRoots(FHsdTreeNode<E> treeNode)
   {
      FHsdTreeNode<E> child;
      
      if (mRoot == null)
         return;
      
      treeNode.myRoot = mRoot;
      for (child = treeNode.firstChild; child != null; child = child.sib)
         setMyRoots(child);
   }
   
   // define this as a static member so recursive display() does not need
   // a local version
   final static String blankString = "                                    ";
   
   // should only show the non-deleted nodes
   protected void display(FHsdTreeNode<E> treeNode, int level)
   {
      FHsdTreeNode<E> child;
      String indent;
      
      // if the node is null, return
      if (treeNode == null)
         return;
      
      // if the main root is deleted, there's no point on continuing
      if (treeNode.deleted == true)
      {
         return;
      }
      
      // stop runaway indentation/recursion
      if (level > (int) blankString.length() - 1)
      {
         System.out.println(blankString + " ... ");
         return;
      }
      
      // for indent purpose
      indent = blankString.substring(0, level);
      
      // pre-order processing done here ("visit")
      System.out.println(indent + treeNode.data);
      
      // recursive step done here
      for (child = treeNode.firstChild; child != null; child = child.sib)
      {
         if (child.deleted == false)
         {
            display(child, level + 1);
         }
      }
   }
   
   /*
    * returns the actual, physical size. This is easy: just like our old size()
    * from the lectures. It just has a new name in this regime since the old
    * name, size(), is now used to return the virtual size of the tree (a count
    * of non-deleted nodes).
    */
   public int sizePhysical()
   {
      return this.mSize;
   }
   
   /*
    * like the old display() from the lectures. Ignores the deleted flag.
    * Optionally, place a " (D)" after any node that has deleted == true. Note:
    * you don't have to add the " (D)" if the node is not marked as deleted,
    * even though it might actually be deleted by virtue of its being in a
    * subtree of a deleted node.
    */
   public void displayPhysical()
   {
      displayPhysical(mRoot, 0);
   }
   
   public void displayPhysical(FHsdTreeNode<E> treeNode, int level)
   {
      FHsdTreeNode<E> child;
      String indent;
      
      if (treeNode == null)
         return;
      
      // stop runaway indentation/recursion
      if (level > (int) blankString.length() - 1)
      {
         System.out.println(blankString + " ... ");
         return;
      }
      
      indent = blankString.substring(0, level);
      
      // pre-order processing done here ("visit")
      System.out.println(indent + treeNode.data);
      
      // recursive step done here
      for (child = treeNode.firstChild; child != null; child = child.sib)
         displayPhysical(child, level + 1);
   }
   
   /*
    * physically removes all nodes that are marked as deleted. After this method
    * is called, the physical and virtual size of the tree would be the same. A
    * physical and virtual display() would also be the same after a call to
    * collectGarbage() (at least temporarily, until more items are remove()ed
    * from the tree). This method does exactly what it says: it takes out the
    * garbage. Hint: make use of remove_node(), which can be preserved from the
    * original general tree class.
    */
   public boolean collectGarbage()
   {
      return collectGarbage(mRoot, false);
   }
   
   public boolean collectGarbage(FHsdTreeNode<E> root, boolean bool)
   {
      FHsdTreeNode<E> child;
      boolean garbageCollected = bool;
      
      if (root == null)
         return false;
      
      if (root.deleted == true)
      {
         removeNode(root);
         garbageCollected = true;
      }
      
      // recursive step done here
      for (child = root.firstChild; child != null; child = child.sib)
      {
         garbageCollected = collectGarbage(child, garbageCollected);
      }
      
      return garbageCollected;
   }
   
   private void removeNode(FHsdTreeNode<E> nodeToDelete)
   {
      if (nodeToDelete == null || mRoot == null)
         return;
      if (nodeToDelete.myRoot != mRoot)
         return;  // silent error, node does not belong to this tree
         
      // remove all the children of this node
      // (but for decrementing mSize, this loop might be unnecessary)
      while (nodeToDelete.firstChild != null)
         removeNode(nodeToDelete.firstChild);
      
      if (nodeToDelete.prev == null)
         mRoot = null;  // last node in tree
      else if (nodeToDelete.prev.sib == nodeToDelete)
         nodeToDelete.prev.sib = nodeToDelete.sib; // adjust left sibling
      else
         nodeToDelete.prev.firstChild = nodeToDelete.sib;  // adjust parent
         
      // adjust the successor sib's prev pointer
      if (nodeToDelete.sib != null)
         nodeToDelete.sib.prev = nodeToDelete.prev;
      
      --mSize;
   }
   
   public <F extends TraverserG<? super E>> void traverse(F func)
   {
      traverse(func, mRoot);
   }
   
   protected <F extends TraverserG<? super E>>
   // public <F extends TraverserG<E>> // also works but less flexibly
   void traverse(F func, FHsdTreeNode<E> treeNode)
   {
      FHsdTreeNode<E> child;
      if (treeNode == null)
         return;
      
      func.visit(treeNode.data);
      
      for (child = treeNode.firstChild; child != null; child = child.sib)
         traverse(func, child);
   }
}

interface TraverserG<E>
{
   public void visit(E x);
}
