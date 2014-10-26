/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 9 in CS 1B Jesse Cecil Winter 2014
 * this program is called GUI Cards. this program use the tree sata structure
 * and a combination of other fundamentals of java. in this assignment i opt
 * to use Option B-2
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

public class Foothill
{
   public static void main(String[] args) throws Exception
   {
      createLine(80, "=");
      System.out.println("RUN 1 -> Default Main");
      createLine(80, "=");
      
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
      System.out.println("physical (hard) size: " + scene_tree.sizePhysical());
      
      System.out.println("------------ Collecting Garbage ---------------- \n");
      System.out.println("found soft-deleted nodes? "
            + scene_tree.collectGarbage());
      System.out.println("immediate collect again? "
            + scene_tree.collectGarbage());
      
      System.out
            .println("--------- Hard Display after garb col ------------ \n");
      
      scene_tree.displayPhysical();
      
      scene_tree.remove("room");
      
      System.out.println("Semi-deleted tree empty? " + scene_tree.empty()
            + "\n");
      
      // empty the garbage afterward
      scene_tree.collectGarbage();
      
      System.out.println("Completely-deleted tree empty? " + scene_tree.empty()
            + "\n");
      
      scene_tree.display();
      
      createLine(80, "=");
      System.out.println("RUN 2 -> Integers");
      createLine(80, "=");
      
      FHsdTree int_tree = new FHsdTree();
      FHsdTreeNode int_Node;
      
      System.out.println("Starting tree empty? " + int_tree.empty() + "\n");
      // create a main root
      int_Node = int_tree.addChild(null, 1);
      
      // add Bunch of numbers...
      int_tree.addChild(int_Node, 2);
      int_tree.addChild(int_Node, 3);
      int_tree.addChild(int_Node, 4);
      int_Node = int_tree.find(4);
      int_Node = int_tree.addChild(int_Node, 6);
      int_Node = int_tree.addChild(int_Node, 7);
      int_Node = int_tree.addChild(int_Node, 8);
      int_tree.addChild(int_Node, 9);
      int_tree.addChild(int_Node, 10);
      int_tree.addChild(int_Node, 11);
      int_tree.addChild(int_Node, 12);
      int_tree.addChild(int_Node, 13);
      int_Node = int_tree.find(10);
      int_Node = int_tree.find(int_Node, 15, 0);
      int_Node = int_tree.addChild(int_Node, 16);
      int_Node = int_tree.addChild(int_Node, 17);
      int_tree.addChild(int_Node, 18);
      int_tree.addChild(int_Node, 19);
      int_tree.addChild(int_Node, 20);
      int_tree.addChild(int_Node, 21);
      int_tree.addChild(int_Node, 22);
      int_Node = int_tree.find(20);
      int_Node = int_tree.addChild(int_Node, 24);
      int_tree.addChild(int_Node, 25);
      int_tree.addChild(int_Node, 26);
      int_tree.addChild(int_Node, 27);
      int_tree.addChild(int_Node, 28);
      int_tree.addChild(int_Node, 29);
      int_tree.addChild(int_Node, 30);
      int_Node = int_tree.find(2);
      int_tree.addChild(int_Node, 5);
      int_tree.addChild(int_Node, 14);
      int_tree.addChild(int_Node, 23);
      int_tree.addChild(int_Node, 0);
      
      System.out.println("\n------------ Loaded Tree ----------------- \n");
      int_tree.display();
      
      int_tree.remove(4);
      int_tree.remove(14);
      int_tree.remove(55);
      
      System.out
            .println("\n------------ Virtual (soft) Tree --------------- \n");
      int_tree.display();
      
      System.out
            .println("\n----------- Physical (hard) Display ------------- \n");
      int_tree.displayPhysical();
      
      System.out
            .println("------- Testing Sizes (compare with above) -------- \n");
      System.out.println("virtual (soft) size: " + int_tree.size());
      System.out.println("physical (hard) size: " + int_tree.sizePhysical());
      
      System.out.println("------------ Collecting Garbage ---------------- \n");
      System.out.println("found soft-deleted nodes? "
            + int_tree.collectGarbage());
      System.out.println("immediate collect again? "
            + int_tree.collectGarbage());
      
      System.out
            .println("--------- Hard Display after garb col ------------ \n");
      
      int_tree.displayPhysical();
      
      int_tree.remove(1);
      
      System.out.println("Semi-deleted tree empty? " + int_tree.empty() + "\n");
      
      // empty the garbage afterward
      int_tree.collectGarbage();
      
      System.out.println("Completely-deleted tree empty? " + int_tree.empty()
            + "\n");
      
      int_tree.display();
      
      createLine(80, "=");
      System.out.println("RUN 3 -> Card Objects");
      createLine(80, "=");
      
      FHsdTree card_tree = new FHsdTree();
      FHsdTreeNode card_Node;
      
      // create an array of random cards
      Card[] cardArray = new Card[35];
      
      for (int i = 0; i < cardArray.length; i++)
      {
         cardArray[i] = generateRandomCard();
      }
      
      System.out.println("Starting tree empty? " + card_tree.empty() + "\n");
      // create a main root
      card_Node = card_tree.addChild(null, cardArray[0]);
      
      // add Bunch of Cards...
      card_tree.addChild(card_Node, cardArray[1]);
      card_tree.addChild(card_Node, cardArray[2]);
      card_tree.addChild(card_Node, cardArray[3]);
      card_Node = card_tree.find(cardArray[2]);
      card_Node = card_tree.addChild(card_Node, cardArray[4]);
      card_Node = card_tree.addChild(card_Node, cardArray[5]);
      card_Node = card_tree.addChild(card_Node, cardArray[6]);
      card_tree.addChild(card_Node, cardArray[7]);
      card_tree.addChild(card_Node, cardArray[8]);
      card_tree.addChild(card_Node, cardArray[9]);
      card_tree.addChild(card_Node, cardArray[10]);
      card_tree.addChild(card_Node, cardArray[11]);
      card_Node = card_tree.find(cardArray[8]);
      card_Node = card_tree.find(card_Node, cardArray[12], 0);
      card_Node = card_tree.addChild(card_Node, cardArray[13]);
      card_Node = card_tree.addChild(card_Node, cardArray[14]);
      card_tree.addChild(card_Node, cardArray[15]);
      card_tree.addChild(card_Node, cardArray[16]);
      card_tree.addChild(card_Node, cardArray[17]);
      card_tree.addChild(card_Node, cardArray[18]);
      card_tree.addChild(card_Node, cardArray[19]);
      card_Node = card_tree.find(cardArray[15]);
      card_Node = card_tree.addChild(card_Node, cardArray[20]);
      card_tree.addChild(card_Node, cardArray[21]);
      card_tree.addChild(card_Node, cardArray[22]);
      card_tree.addChild(card_Node, cardArray[23]);
      card_tree.addChild(card_Node, cardArray[24]);
      card_tree.addChild(card_Node, cardArray[25]);
      card_tree.addChild(card_Node, cardArray[26]);
      card_Node = card_tree.find(cardArray[4]);
      card_tree.addChild(card_Node, cardArray[27]);
      card_tree.addChild(card_Node, cardArray[28]);
      card_tree.addChild(card_Node, cardArray[29]);
      card_tree.addChild(card_Node, cardArray[30]);
      
      System.out.println("\n------------ Loaded Tree ----------------- \n");
      card_tree.display();
      
      card_tree.remove(cardArray[6]);
      card_tree.remove(cardArray[4]);
      card_tree.remove(cardArray[34]);
      
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
      
      card_tree.remove(cardArray[0]);
      
      System.out
            .println("Semi-deleted tree empty? " + card_tree.empty() + "\n");
      
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
   
   static Card generateRandomCard()
   {
      Card.Suit suit;
      char val;
      
      int suitSelector, valSelector;
      
      // get random suit and value
      suitSelector = (int) (Math.random() * 4);
      valSelector = (int) (Math.random() * 13);
      
      // pick suit
      suit = turnIntIntoSuit(suitSelector);
      val = turnIntIntoVal(valSelector);
      
      return new Card(val, suit);
   }
   
   static Card.Suit turnIntIntoSuit(int inputInt)
   {
      if (inputInt == 0)
         return Card.Suit.clubs;
      else if (inputInt == 1)
         return Card.Suit.diamonds;
      else if (inputInt == 2)
         return Card.Suit.hearts;
      else
         return Card.Suit.spades;
   }
   
   static char turnIntIntoVal(int inputInt)
   {
      if (inputInt == 0)
         return 'A';
      else if (inputInt == 9)
         return 'T';
      else if (inputInt == 10)
         return 'J';
      else if (inputInt == 11)
         return 'Q';
      else if (inputInt == 12)
         return 'K';
      else
         return Character.forDigit((inputInt + 1), 10);
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

/**
 * the card class that was used in assignment 5, which is based on assignment 1.
 * and also present in the module 8 of the class.
 * 
 * @author MuhammadRizky
 * 
 */
class Card implements Comparable<Card>
{
   // type and constants
   public enum State
   {
      deleted, active
   } // not bool because later we may expand
   
   public enum Suit
   {
      clubs, diamonds, hearts, spades
   }
   
   // for sort.
   static char[] valueRanks =
      { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', 'X' };
   static Suit[] suitRanks =
      { Suit.clubs, Suit.diamonds, Suit.hearts, Suit.spades };
   static int numValsInOrderingArray = 13;  // maybe 14 if 'X' = Joker, or < 13
   
   // private data
   private char value;
   private Suit suit;
   State state;
   boolean errorFlag;
   
   // 4 overloaded constructors
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   public Card(char value)
   {
      this(value, Suit.spades);
   }
   
   public Card()
   {
      this('A', Suit.spades);
   }
   
   // copy constructor
   public Card(Card card)
   {
      this(card.value, card.suit);
   }
   
   // mutators
   public boolean set(char value, Suit suit)
   {
      char upVal;            // for upcasing char
      
      // can't really have an error here
      this.suit = suit;
      
      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);
      
      // check for validity
      if (upVal == 'A' || upVal == 'K' || upVal == 'Q' || upVal == 'J'
            || upVal == 'T' || upVal == 'X' || (upVal >= '2' && upVal <= '9'))
      {
         errorFlag = false;
         state = State.active;
         this.value = upVal;
      }
      else
      {
         errorFlag = true;
         return false;
      }
      
      return !errorFlag;
   }
   
   public void setState(State state)
   {
      this.state = state;
   }
   
   // accessors
   public char getVal()
   {
      return value;
   }
   
   public Suit getSuit()
   {
      return suit;
   }
   
   public State getState()
   {
      return state;
   }
   
   public boolean getErrorFlag()
   {
      return errorFlag;
   }
   
   // stringizer
   public String toString()
   {
      String retVal;
      
      if (errorFlag)
         return "** illegal **";
      if (state == State.deleted)
         return "( deleted )";
      
      // else implied
      
      if (value != 'X')
      {
         // not a joker
         retVal = String.valueOf(value);
         retVal += " of ";
         retVal += String.valueOf(suit);
      }
      else
      {
         // joker
         retVal = "joker";
         
         if (suit == Suit.clubs)
            retVal += " 1";
         else if (suit == Suit.diamonds)
            retVal += " 2";
         else if (suit == Suit.hearts)
            retVal += " 3";
         else if (suit == Suit.spades)
            retVal += " 4";
      }
      
      return retVal;
   }
   
   public boolean equals(Card card)
   {
      if (this.value != card.value)
         return false;
      if (this.suit != card.suit)
         return false;
      if (this.errorFlag != card.errorFlag)
         return false;
      if (this.state != card.state)
         return false;
      return true;
   }
   
   // sort member methods
   public int compareTo(Card other)
   {
      if (this.value == other.value)
         return (getSuitRank(this.suit) - getSuitRank(other.suit));
      
      return (getValueRank(this.value) - getValueRank(other.value));
   }
   
   public static void setRankingOrder(char[] valueOrderArr, Suit[] suitOrdeArr,
         int numValsInOrderingArray)
   {
      int k;
      
      // expects valueOrderArr[] to contain only cards used per pack,
      // including jokers, needed to define order for the game environment
      
      if (numValsInOrderingArray < 0 || numValsInOrderingArray > 13)
         return;
      
      Card.numValsInOrderingArray = numValsInOrderingArray;
      
      for (k = 0; k < numValsInOrderingArray; k++)
         Card.valueRanks[k] = valueOrderArr[k];
      
      for (k = 0; k < 4; k++)
         Card.suitRanks[k] = suitOrdeArr[k];
   }
   
   public static int getSuitRank(Suit st)
   {
      int k;
      
      for (k = 0; k < 4; k++)
         if (suitRanks[k] == st)
            return k;
      
      // should not happen
      return 0;
   }
   
   public static int getValueRank(char val)
   {
      int k;
      
      for (k = 0; k < numValsInOrderingArray; k++)
         if (valueRanks[k] == val)
            return k;
      
      // should not happen
      return 0;
   }
   
   public static void arraySort(Card[] array, int arraySize)
   {
      for (int k = 0; k < arraySize; k++)
         if (!floatLargestToTop(array, arraySize - 1 - k))
            return;
   }
   
   private static boolean floatLargestToTop(Card[] array, int top)
   {
      boolean changed = false;
      Card temp;
      
      for (int k = 0; k < top; k++)
         if (array[k].compareTo(array[k + 1]) > 0)
         {
            temp = array[k];
            array[k] = array[k + 1];
            array[k + 1] = temp;
            changed = true;
         }
      ;
      return changed;
   }
}

/*-----------------------------------Sample Run---------------------------------

================================================================================
RUN 1 -> Default Main
================================================================================
Starting tree empty? true


------------ Loaded Tree ----------------- 

room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Miguel the human
  torso
   right arm
    right hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
   left arm
    left hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
 Lily the canine
  torso
   wagging tail
   spare mutant paw
   left rear paw
   right rear paw
   left front paw
   right front paw

------------ Virtual (soft) Tree --------------- 

room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw

----------- Physical (hard) Display ------------- 

room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Miguel the human
  torso
   right arm
    right hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
   left arm
    left hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
 Lily the canine
  torso
   wagging tail
   spare mutant paw
   left rear paw
   right rear paw
   left front paw
   right front paw
------- Testing Sizes (compare with above) -------- 

virtual (soft) size: 13
physical (hard) size: 30
------------ Collecting Garbage ---------------- 

found soft-deleted nodes? true
immediate collect again? false
--------- Hard Display after garb col ------------ 

room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw
Semi-deleted tree empty? false

Completely-deleted tree empty? true

================================================================================
RUN 2 -> Integers
================================================================================
Starting tree empty? true


------------ Loaded Tree ----------------- 

1
 4
  6
   7
    8
     13
     12
     11
     10
     9
 3
 2
  0
  23
  14
  5

------------ Virtual (soft) Tree --------------- 

1
 3
 2
  0
  23
  5

----------- Physical (hard) Display ------------- 

1
 4
  6
   7
    8
     13
     12
     11
     10
     9
 3
 2
  0
  23
  14
  5
------- Testing Sizes (compare with above) -------- 

virtual (soft) size: 6
physical (hard) size: 16
------------ Collecting Garbage ---------------- 

found soft-deleted nodes? true
immediate collect again? false
--------- Hard Display after garb col ------------ 

1
 3
 2
  0
  23
  5
Semi-deleted tree empty? false

Completely-deleted tree empty? true

================================================================================
RUN 3 -> Card Objects
================================================================================
Starting tree empty? true


------------ Loaded Tree ----------------- 

8 of hearts
 4 of hearts
 5 of spades
  6 of clubs
   3 of spades
   7 of clubs
   K of spades
   T of spades
   2 of hearts
    J of diamonds
     9 of hearts
     5 of clubs
     6 of hearts
     4 of spades
     J of hearts
 Q of hearts

------------ Virtual (soft) Tree --------------- 

8 of hearts
 4 of hearts
 5 of spades
 Q of hearts

----------- Physical (hard) Display ------------- 

8 of hearts
 4 of hearts
 5 of spades
  6 of clubs
   3 of spades
   7 of clubs
   K of spades
   T of spades
   2 of hearts
    J of diamonds
     9 of hearts
     5 of clubs
     6 of hearts
     4 of spades
     J of hearts
 Q of hearts
------- Testing Sizes (compare with above) -------- 

virtual (soft) size: 4
physical (hard) size: 16
------------ Collecting Garbage ---------------- 

found soft-deleted nodes? true
immediate collect again? false
--------- Hard Display after garb col ------------ 

8 of hearts
 4 of hearts
 5 of spades
 Q of hearts
Semi-deleted tree empty? false

Completely-deleted tree empty? true

------------------------------------------------------------------------------*/
