package FHsd;

import FHg.FHgTreeNode;

/**
 * FHsdTreeNode Class, subclass of FHgTreeNode
 * 
 * @author MuhammadRizky
 * 
 * @param <E>
 */
public class FHsdTreeNode<E> extends FHgTreeNode<E>
{
   // member data that shadow the base class's member data
   protected FHsdTreeNode<E> firstChild, sib, prev;
   protected FHsdTreeNode<E> myRoot;
   protected boolean deleted;
   
   // default constructor
   public FHsdTreeNode()
   {
      this(null, null, null, null, null);
   }
   
   // constructors
   public FHsdTreeNode(E d, FHsdTreeNode<E> sib, FHsdTreeNode<E> child,
         FHsdTreeNode<E> prev, FHsdTreeNode<E> root)
   {
      super(d, sib, child, prev, root);
      this.deleted = false;
   }
   
   public FHsdTreeNode(E d, FHsdTreeNode<E> sb, FHsdTreeNode<E> chld,
         FHsdTreeNode<E> prv, FHsdTreeNode<E> root, boolean del)
   {
      super(d, sb, chld, prv, root);
      this.deleted = del;
   }
   
   // mutator, set the respective member data
   public void setFirstChild(FHsdTreeNode<E> chld)
   {
      this.firstChild = chld;
   }
   
   public void setSibling(FHsdTreeNode<E> sib)
   {
      this.sib = sib;
   }
   
   public void setPrev(FHsdTreeNode<E> prev)
   {
      this.prev = prev;
   }
   
   public void setRoot(FHsdTreeNode<E> root)
   {
      this.myRoot = root;
   }
   
   public void setDel(boolean bool)
   {
      this.deleted = bool;
   }
   
   // accessor, return respective member data
   public FHsdTreeNode<E> getFirstChild()
   {
      return this.firstChild;
   }
   
   public FHsdTreeNode<E> getPrev()
   {
      return this.prev;
   }
   
   public FHsdTreeNode<E> getMyRoot()
   {
      return this.myRoot;
   }
   
   public FHsdTreeNode<E> getSib()
   {
      return this.sib;
   }
   
   public boolean getDel()
   {
      return this.deleted;
   }
   
}
