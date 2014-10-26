package SubmittedProgram;

import FHg.TraverserG;
import FHsd.FHsdTree;
import FHsd.FHsdTreeNode;

public class Foothill
{
   public static void main(String[] args) throws Exception
   {
      FHsdTree scene_tree = new FHsdTree();
      FHsdTreeNode tn;
      
      System.out.println("Starting tree empty? " + scene_tree.empty() + "\n");
      // create a scene in a room
      tn = scene_tree.addChild(null, "room");
      
      // add three objects to the scene tree
      scene_tree.addChild(tn, "Lily the canine");
      scene_tree.addChild(tn, "Miguel the human");
      scene_tree.addChild(tn, "table");
      // add some parts to Miguel
      tn = scene_tree.find("Miguel the human");
      
      // Miguel's left arm
      tn = scene_tree.addChild(tn, "torso");
      tn = scene_tree.addChild(tn, "left arm");
      tn = scene_tree.addChild(tn, "left hand");
      scene_tree.addChild(tn, "thumb");
      scene_tree.addChild(tn, "index finger");
      scene_tree.addChild(tn, "middle finger");
      scene_tree.addChild(tn, "ring finger");
      scene_tree.addChild(tn, "pinky");
      
      // Miguel's right arm
      tn = scene_tree.find("Miguel the human");
      tn = scene_tree.find(tn, "torso", 0);
      tn = scene_tree.addChild(tn, "right arm");
      tn = scene_tree.addChild(tn, "right hand");
      scene_tree.addChild(tn, "thumb");
      scene_tree.addChild(tn, "index finger");
      scene_tree.addChild(tn, "middle finger");
      scene_tree.addChild(tn, "ring finger");
      scene_tree.addChild(tn, "pinky");
      
      // add some parts to Lily
      tn = scene_tree.find("Lily the canine");
      tn = scene_tree.addChild(tn, "torso");
      scene_tree.addChild(tn, "right front paw");
      scene_tree.addChild(tn, "left front paw");
      scene_tree.addChild(tn, "right rear paw");
      scene_tree.addChild(tn, "left rear paw");
      scene_tree.addChild(tn, "spare mutant paw");
      scene_tree.addChild(tn, "wagging tail");
      
      // add some parts to table
      tn = scene_tree.find("table");
      scene_tree.addChild(tn, "north east leg");
      scene_tree.addChild(tn, "north west leg");
      scene_tree.addChild(tn, "south east leg");
      scene_tree.addChild(tn, "south west leg");
      
      System.out.println("\n------------ Loaded Tree ----------------- \n");
      scene_tree.display();
      
      scene_tree.remove("spare mutant paw");
      scene_tree.remove("Miguel the human");
      scene_tree.remove("an imagined higgs boson");
      
      System.out
            .println("\n------------ Virtual (soft) Tree --------------- \n");
      scene_tree.display();
      
      System.out
            .println("\n----------- Physical (hard) Display ------------- \n");
      scene_tree.displayPhysical();
      
      System.out
            .println("------- Testing Sizes (compare with above) -------- \n");
      System.out.println("virtual (soft) size: " + scene_tree.size());
      System.out.println("physiical (hard) size: " + scene_tree.sizePhysical());
      
      System.out.println("------------ Collecting Garbage ---------------- \n");
      System.out.println("found soft-deleted nodes? "
            + scene_tree.collectGarbage());
      System.out.println("immediate collect again? "
            + scene_tree.collectGarbage());
      
      System.out
            .println("--------- Hard Display after garb col ------------ \n");
      
      scene_tree.displayPhysical();
      
      System.out.println("Semi-deleted tree empty? " + scene_tree.empty()
            + "\n");
      scene_tree.remove("room");
      System.out.println("Completely-deleted tree empty? " + scene_tree.empty()
            + "\n");
      
      scene_tree.display();
   }
}

class FHgTreeNode<E>
{
   // use protected access so the tree, in the same package, 
   // or derived classes can access members 
   protected FHgTreeNode<E> firstChild, sib, prev;
   protected E data;
   protected FHgTreeNode<E> myRoot;  // needed to test for certain error

   public FHgTreeNode( E d, FHgTreeNode<E> sb, FHgTreeNode<E> chld, FHgTreeNode<E> prv )
   {
      firstChild = chld; 
      sib = sb;
      prev = prv;
      data = d;
      myRoot = null;
   }
   
   public FHgTreeNode()
   {
      this(null, null, null, null);
   }
   
   public E getData() { return data; }

   // for use only by FHtree (default access)
   protected FHgTreeNode( E d, FHgTreeNode<E> sb, FHgTreeNode<E> chld, 
         FHgTreeNode<E> prv, FHgTreeNode<E> root )
   {
      this(d, sb, chld, prv);
      myRoot = root;
   }
}

class FHgTree<E> implements Cloneable
{
   protected int mSize;
   protected FHgTreeNode<E> mRoot;
   
   public FHgTree() { clear(); }
   public boolean empty() { return (mSize == 0); }
   public int size() { return mSize; }
   public void clear() { mSize = 0; mRoot = null; }
   
   public FHgTreeNode<E> find(E x) { return find(mRoot, x, 0); }
   public boolean remove(E x) { return remove(mRoot, x); }
   public void  display()  { display(mRoot, 0); }
   
   public FHgTreeNode<E> addChild( FHgTreeNode<E> treeNode,  E x )
   {
      // empty tree? - create a root node if user passes in null
      if (mSize == 0)
      {
         if (treeNode != null)
            return null; // error something's fishy.  treeNode can't right
         mRoot = new FHgTreeNode<E>(x, null, null, null);
         mRoot.myRoot = mRoot;
         mSize = 1;
         return mRoot;
      }
      if (treeNode == null)
         return null; // error inserting into non_null tree with a null parent
      if (treeNode.myRoot != mRoot)
         return null;  // silent error, node does not belong to this tree

      // push this node into the head of the sibling list; adjust prev pointers
      FHgTreeNode<E> newNode = new FHgTreeNode<E>(x, 
         treeNode.firstChild, null, treeNode, mRoot);  // sb, chld, prv, rt
      treeNode.firstChild = newNode;
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      ++mSize;
      return newNode;  
   }
   
   public FHgTreeNode<E> find(FHgTreeNode<E> root, E x, int level)
   {
      FHgTreeNode<E> retval;

      if (mSize == 0 || root == null)
         return null;

      if (root.data.equals(x))
         return root;

      // otherwise, recurse.  don't process sibs if this was the original call
      if ( level > 0 && (retval = find(root.sib, x, level)) != null )
         return retval;
      return find(root.firstChild, x, ++level);
   }
   
   public boolean remove(FHgTreeNode<E> root, E x)
   {
      FHgTreeNode<E> tn = null;

      if (mSize == 0 || root == null)
         return false;
     
      if ( (tn = find(root, x, 0)) != null )
      {
         removeNode(tn);
         return true;
      }
      return false;
   }
   
   private void removeNode(FHgTreeNode<E> nodeToDelete )
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
   
   public Object clone() throws CloneNotSupportedException
   {
      FHgTree<E> newObject = (FHgTree<E>)super.clone();
      newObject.clear();  // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      newObject.setMyRoots(newObject.mRoot);
      
      return newObject;
   }
   
   private FHgTreeNode<E> cloneSubtree(FHgTreeNode<E> root)
   {
      FHgTreeNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new FHgTreeNode<E>
      (
         root.data, 
         cloneSubtree(root.sib), cloneSubtree(root.firstChild),
         null
      );
      
      // the prev pointer is set by parent recursive call ... this is the code:
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      if (newNode.firstChild != null)
         newNode.firstChild.prev = newNode;
      return newNode;
   }
   
   // recursively sets all myRoots to mRoot
   private void setMyRoots(FHgTreeNode<E> treeNode)
   {
      FHgTreeNode<E> child;

      if (mRoot == null)
         return;

      treeNode.myRoot = mRoot;
      for (child = treeNode.firstChild; child != null; child = child.sib)
         setMyRoots(child);
   }
   
   // define this as a static member so recursive display() does not need
   // a local version
   final static String blankString = "                                    ";
   
   protected void  display(FHgTreeNode<E> treeNode, int level) 
   {
      FHgTreeNode<E> child;
      String indent;

      if (treeNode == null)
         return;

      // stop runaway indentation/recursion
      if  (level > (int)blankString.length() - 1)
      {
         System.out.println( blankString + " ... " );
         return;
      }

      indent = blankString.substring(0, level);

      // pre-order processing done here ("visit")
      System.out.println( indent + treeNode.data ) ;
      
      // recursive step done here
      for (child = treeNode.firstChild; 
           child != null; 
           child = child.sib)
         display(child, level+1);
   }
   
   public < F extends TraverserG< ? super E > > 
   void traverse(F func)
   {
      traverse(func, mRoot);
   }
   
   protected <F extends TraverserG<? super E>> 
   //  public <F extends TraverserG<E>>  // also works but less flexibly
   void traverse(F func, FHgTreeNode<E> treeNode)
   {
      FHgTreeNode<E> child;
      if (treeNode == null)
         return;

      func.visit(treeNode.data);

      for (child = treeNode.firstChild; child != null; child = child.sib)
         traverse(func, child);
   }
}
