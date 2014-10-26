package FHsd;

import FHg.FHgTree;
import FHg.FHgTreeNode;
import FHg.TraverserG;

/**
 * FHsdTree class, subclass of FHgTree class
 * 
 * @author MuhammadRizky
 * 
 * @param <E>
 */
public class FHsdTree<E> extends FHgTree<E> implements Cloneable
{
   // member data that over shadow the FHgTree's member data
   protected FHsdTreeNode<E> mRoot;
   
   // just for indentation
   final static String blankString = "                                    ";
   
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
         mRoot = new FHsdTreeNode<E>(x, null, null, null, null);
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
   public FHsdTreeNode<E> find(E x)
   {
      // call the recursive find method
      return find(mRoot, x, 0);
   }
   
   public FHsdTreeNode<E> find(FHsdTreeNode<E> root, E x, int level)
   {
      FHsdTreeNode<E> retval;
      
      // if the tree is empty return null
      if (mSize == 0 || root == null)
         return null;
      
      // if we found a perfect match and boolean deleted is not true, return it
      if (root.getData().equals(x) && root.getDel() != true)
         return root;
      
      // otherwise, recurse. don't process sibs if this was the original call
      if (level > 0 && (retval = find(root.getSib(), x, level)) != null)
         return retval;
      
      return find(root.getFirstChild(), x, ++level);
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
   public boolean remove(E x)
   {
      // call the recursive remove method
      return remove(mRoot, x);
   }
   
   public boolean remove(FHsdTreeNode<E> root, E x)
   {
      FHsdTreeNode<E> tn = null;
      
      // if the tree is empty or it is the end of the tree, return false
      if (mSize == 0 || root == null)
         return false;
      
      // if node is not null, flag the node, recurse down the tree
      if ((tn = find(root, x, 0)) != null)
      {
         flagNodeSD(tn);
         return true;
      }
      
      return false;
   }
   
   private void flagNodeSD(FHsdTreeNode<E> nodeToDelete)
   {
      // if the tree is empty or end of the tree, return false
      if (nodeToDelete == null || mRoot == null)
         return;
      
      // weird error, if the node doesnt belong to the tree, return false
      if (nodeToDelete.getMyRoot() != mRoot)
         return;
      
      // flag all the children of this node, recurse down the tree
      while (nodeToDelete.getFirstChild() != null)
         flagNodeSD(nodeToDelete.getFirstChild());
      
      // implement the soft deletion
      if (nodeToDelete.getPrev() == null)
         mRoot.setDel(true);  // last node in tree
         
      // decrease the virtual size
      --mSize;
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
      displayPhysical(this.mRoot, 0);
   }
   
   public void displayPhysical(FHsdTreeNode<E> treeNode, int level)
   {
      FHsdTreeNode<E> child;
      String indent;
      String del = "";
      
      if (treeNode == null)
         return;
      
      // stop runaway indentation/recursion
      if (level > (int) blankString.length() - 1)
      {
         System.out.println(blankString + " ... ");
         return;
      }
      
      // adding a deleted marker on the node if its flagged deleted
      if (treeNode.getDel() == true)
      {
         del = " (D)";
      }
      
      // indentation of the display
      indent = blankString.substring(0, level);
      
      // pre-order processing done here ("visit")
      System.out.println(indent + treeNode.getData() + del);
      
      // recursive step done here
      for (child = treeNode.getFirstChild(); child != null; child = child
            .getSib())
         display(child, level + 1);
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
   public <F extends TraverserG<? super E>> boolean collectGarbage()
   {
      softDelete(this.mRoot);
      
      return false;
   }
   
   public <F extends TraverserG<? super E>> boolean collectGarbage(
         FHsdTreeNode<E> treeNode)
   {
      softDelete(treeNode);
      
      return false;
   }
   
   private void removeNode(FHsdTreeNode<E> nodeToDelete)
   {
      // if the tree, or the subtree is empty, return back
      if (nodeToDelete == null || mRoot == null)
         return;
      
      // weird error, if the node doesnt belong to this subtree, reutrn
      if (nodeToDelete.getMyRoot() != mRoot)
         return;  // silent error, node does not belong to this tree
         
      // remove all the children of this node
      while (nodeToDelete.getFirstChild() != null)
         removeNode(nodeToDelete.getFirstChild());
      
      if (nodeToDelete.getPrev() == null)
         mRoot = null;  // last node in tree
      // adjust siblings
      else if (nodeToDelete.getPrev().getSib() == nodeToDelete)
         nodeToDelete.getPrev().setSibling(nodeToDelete.getSib());
      // adjust parent
      else
         nodeToDelete.getPrev().setFirstChild(nodeToDelete.getSib());
      
      // adjust the successor sib's prev pointer
      if (nodeToDelete.getSib() != null)
         nodeToDelete.getSib().setPrev(nodeToDelete.getPrev());
   }
   
   protected <F extends TraverserG<? super E>> void softDelete(
         FHsdTreeNode<E> treeNode)
   {
      FHsdTreeNode<E> child;
      if (treeNode == null)
         return;
      
      if (treeNode.getDel() == true)
      {
         removeNode(treeNode);
      }
      
      for (child = treeNode.firstChild; child != null; child = child.sib)
         softDelete(child);
   }
   
   public void display()
   {
      display(mRoot, 0);
   }
   
   protected void display(FHsdTreeNode<E> treeNode, int level)
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
      System.out.println(indent + treeNode.getData());
      
      // recursive step done here
      for (child = treeNode.firstChild; child != null; child = child.sib)
         display(child, level + 1);
   }
   
}
