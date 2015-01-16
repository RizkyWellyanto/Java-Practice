package week10_FHVertex_FHedge;

public class FHedge<E> implements Comparable< FHedge<E> >
{
   FHvertex source, dest;
   double cost;
 
   FHedge( FHvertex src, FHvertex dst, Double cst)
   {
      source = src;
      dest = dst;
      cost = cst;
   }
   
   FHedge( FHvertex src, FHvertex dst, Integer cst)
   {
      this (src, dst, cst.doubleValue());
   }
   
   FHedge()
   {
      this(null, null, 1.);
   }
   
   public int compareTo( FHedge rhs ) 
   {
      return (cost < rhs.cost? -1 : cost > rhs.cost? 1 : 0);
   }
}