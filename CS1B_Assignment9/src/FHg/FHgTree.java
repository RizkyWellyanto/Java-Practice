package FHg;


// file FHgTree.java

public class FHgTree<E> implements Cloneable
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
