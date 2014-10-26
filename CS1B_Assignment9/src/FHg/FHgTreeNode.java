package FHg;
// file FHgTreeNode.java --------------------------------------

public class FHgTreeNode<E>
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
