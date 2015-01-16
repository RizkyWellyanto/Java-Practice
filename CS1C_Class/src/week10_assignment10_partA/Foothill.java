package week10_assignment10_partA;

/**
 * CS 1C Assignment 10 - Star Near Earth Extra Credit
 * ------------------------------------------------------
 * Name : Muhammad Rizky Wellyanto
 * CWID : 20144588
 * Parts : A
 * Description : This is the the extra credit assignment for CS_1C
 * ML F14. This program extends the star near earth class. This program
 * can show take the input of star near earth data, and then show the output
 * in console. To show what we leanr in class, i use the sparse matrix, since
 * this structure fits the characteristic of this assignemnt.
 */

import java.text.NumberFormat;
import java.util.ListIterator;
import java.util.Locale;

import cs_1c.*;

public class Foothill
{
   public static void main(String[] args)
   {
      String outString;
      int k, arraySize, row, col;
      double maxX, minX, maxY, minY, maxZ, minZ,
      xRange, yRange, zRange, 
      xConversion, yConversion, zConversion;
      final int NUM_COLS = 70;
      final int NUM_ROWS = 35;
   
      StarNearEarthReader  starInput 
         = new StarNearEarthReader("nearest_stars.txt");

      if (starInput.readError())
      {
         System.out.println("couldn't open " + starInput.getFileName()
            + " for input.");
         return;
      }

      // do this just to see if our read went well
      System.out.println(starInput.getFileName());
      System.out.println(starInput.getNumStars());

      // create an array of objects for our own use:
      arraySize = starInput.getNumStars();
      SNE_Analyzer[] starArray = new SNE_Analyzer[arraySize];
      for (k = 0; k < arraySize; k++)
         starArray[k] =  new SNE_Analyzer( starInput.getStar(k) );

      // display cartesian coords
      for ( k = 0; k < arraySize; k++ )
         System.out.println( starArray[k].getNameCommon() + " " 
         + starArray[k].coordToString());
      
      // now for the graphing
      // get max and min coords for scaling
      maxX = minX = maxY = minY = maxZ = minZ = 0;
      for (k = 0; k < arraySize; k++)
      {
         if ( starArray[k].getX() > maxX )
            maxX = starArray[k].getX();
         else if ( starArray[k].getX() < minX )
            minX = starArray[k].getX();
         if ( starArray[k].getY() > maxY )
            maxY = starArray[k].getY();
         else if ( starArray[k].getY() < minY )
            minY = starArray[k].getY();
         if ( starArray[k].getZ() > maxZ )
            maxZ = starArray[k].getZ();
         else if ( starArray[k].getZ() < minZ )
            minZ = starArray[k].getZ();
      }
      xRange = maxX - minX;
      yRange = maxY - minY;
      zRange = maxZ - minZ;

      // form 50 x 25 grid for display: x-y projection
      xConversion = NUM_ROWS /xRange;
      yConversion = NUM_COLS / yRange;

      SparseMat<Character> starMap 
         = new SparseMat<Character>(NUM_ROWS, NUM_COLS, ' ');

      for (k = 0; k < arraySize; k++)
      {
         row = (int) ( (starArray[k].getX() - minX )* xConversion );
         col = (int) ( (starArray[k].getY() - minY) * yConversion );

         if ( starArray[k].getRank() < 10)
            starMap.set(row, col, (char)('0' + starArray[k].getRank()) );
         else
            starMap.set(row, col, '*');
      }

      // set sun at center
      row = (int) ( (0 - minX )* xConversion );
      col = (int)  ( (0 - minY) * yConversion );
      starMap.set( row, col, 'S' );

      for (row = 0; row < NUM_ROWS; row++)
      {
         outString = "";
         
         for(col = 0; col<NUM_COLS;col++)
            outString += starMap.get(row, col);
         
         System.out.println( outString );
      }
   }
}

/**
 * Main class of the program
 * 
 * @author MuhammadRizky
 *
 */
class SNE_Analyzer extends StarNearEarth
{
   private double x, y, z;
   
   public SNE_Analyzer()
   {
      super();
      x = 0;
      y = 0;
      z = 0;
   }
   
   public SNE_Analyzer(StarNearEarth sne)
   {
      setRank(sne.getRank()); 
      setNameCns(sne.getNameCns());
      setNumComponents(sne.getNumComponents());
      setNameLhs(sne.getNameLhs());
      setRAsc(sne.getRAsc());
      setDec(sne.getDec());
      setPropMotionMag(sne.getPropMotionMag());
      setPropMotionDir(sne.getPropMotionDir());
      setParallaxMean(sne.getParallaxMean());
      setParallaxVariance(sne.getParallaxVariance());
      SetBWhiteDwarfFlag(sne.getWhiteDwarfFlag());
      setSpectralType(sne.getSpectralType());
      setMagApparent(sne.getMagApparent());
      setMagAbsolute(sne.getMagAbsolute());
      setMass(sne.getMass());
      setNotes(sne.getNotes());
      setNameCommon(sne.getNameCommon()); 
      calcCartCoords();
   }
   
   double getX()
   {
      return x;
   }
   
   double getY()
   {
      return y;
   }
   
   double getZ()
   {
      return z;
   }
   
   public void calcCartCoords()
   {
      double dist;   // in light year
      double ra;     // in radian
      double dec;    // in radian
      
      dist = 3.262 / getParallaxMean();
      ra = getRAsc() * Math.PI / 180;
      dec = getDec() * Math.PI / 180;
      
      this.x = dist * Math.cos(dec) * Math.cos(ra);
      this.y = dist * Math.cos(dec) * Math.sin(ra);
      this.z = dist * Math.sin(dec);
   }
   
   public String coordToString()
   {
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      return new String("(" + tidy.format(this.x) + ", " + tidy.format(this.y)
            + ", " + tidy.format(this.z) + ")");
   }
}

/**
 * SparseMatrix class copied from the previous assignment #2
 * 
 * @author MuhammadRizky
 *
 * @param <E>
 */
class SparseMat<E> implements Cloneable
{
   // protected enables us to safely make col/data public
   protected class MatNode implements Cloneable
   {
      public int col;
      public E data;
      
      // we need a default constructor for lists
      MatNode()
      {
         col = 0;
         data = null;
      }

      MatNode(int cl, E dt)
      {
         col = cl;
         data = dt;
      }
      
      public Object clone() throws CloneNotSupportedException
      {
         // shallow copy
         MatNode newObject = (MatNode)super.clone();
         return (Object) newObject;
      }
   };
   
   protected int rowSize, colSize;
   protected E defaultVal;
   protected FHarrayList < FHlinkedList< MatNode > > rows;
   
   public int getRowSize() { return rowSize; }
   public int getColSize() { return colSize; }
   
   // constructor creates an empty Sublist (no indices)
   public SparseMat( int numRows, int numCols, E defaultVal)
   {
      if ( numRows < 1 || numCols < 1 )
         throw new IllegalArgumentException();
      
      rowSize = numRows;
      colSize = numCols;
      allocateEmptyMatrix();
      this.defaultVal = defaultVal;
   }
   
   protected void allocateEmptyMatrix()
   {
      int r;
      rows = new FHarrayList < FHlinkedList< MatNode > >();
      for (r = 0; r < rowSize; r++)
         rows.add( new FHlinkedList< MatNode >());   // add a blank row
   }
   
   public void clear()
   {
      int r;

      for (r = 0; r < rowSize; r++)
         rows.get(r).clear();
   }
   
   // optional method - good practice for java programmers
   public Object clone() throws CloneNotSupportedException
   {
      int r;
      ListIterator<MatNode> iter;
      FHlinkedList < MatNode > newRow;
      
      // shallow copy
      SparseMat<E> newObject = (SparseMat<E>)super.clone();
      
      // create all new lists for the new object
      newObject.allocateEmptyMatrix();
     
      // clone
      for (r = 0; r < rowSize; r++)
      {
         newRow = newObject.rows.get(r);
         // iterate along the row, looking for column c
         for (
               iter =
                  (ListIterator<MatNode>)rows.get(r).listIterator();
            iter.hasNext(); )
         {
            newRow.add( (MatNode) iter.next().clone() );
         }
      }
      
      return newObject;
   }
   
   protected boolean valid(int r, int c)
   {
      if (r >= 0 && r < rowSize && c >= 0 && c < colSize)
         return true;
      return false;
   }
   
   public boolean set(int r, int c, E x)
   {
      if (!valid(r, c))
         return false;

      ListIterator<MatNode> iter;

      // iterate along the row, looking for column c
      for (iter =
         (ListIterator<MatNode>)rows.get(r).listIterator();
         iter.hasNext(); )
      {
         if ( iter.next().col == c )
         {
            if ( x.equals(defaultVal) )
               iter.remove();
            else
               iter.previous().data = x;
            return true;
         }
      }

      // not found
      if ( !x.equals(defaultVal) )
         rows.get(r).add( new MatNode(c, x) );
      return true;
   }
   
   public E get(int r, int c)
   {
      if (!valid(r, c))
         throw new IndexOutOfBoundsException();

      ListIterator<MatNode> iter;

      // iterate along the row, looking for column c
      for (iter =
         (ListIterator<MatNode>)rows.get(r).listIterator();
         iter.hasNext(); )
      {
         if ( iter.next().col == c )
            return iter.previous().data;
      }
      // not found
      return defaultVal;
   }
   
   public void showSubSquare(int start, int size)
   {
      int r, c;

      if (start < 0 || size < 0
         || start + size > rowSize
         || start + size > colSize )
         return;

      for (r = start; r < start + size; r++)
      {
         for (c = start; c < start + size; c++)
            System.out.print( String.format("%4.1f", (Double)get(r, c)) + " " );
         System.out.println();
      }
      System.out.println();
   }
}

/*------------------------------Sample Run--------------------------------------

nearest_stars.txt
100
Proxima Centauri (-1.5463, -1.1835, -3.7693)
Barnard's Star (-0.0571, -5.9434, 0.488)
Wolf 359 (-7.4303, 2.1137, 0.9505)
Lalande 21185 (-6.5064, 1.6423, 4.87)
Sirius (-1.6085, 8.0621, -2.4689)
BL Ceti (7.541, 3.4771, -2.6902)
Ross 154 (1.91, -8.6481, -3.9129)
Ross 248 (7.3806, -0.5841, 7.1935)
epsilon Eridani (6.2136, 8.3146, -1.7292)
Lacaille 9352 (8.4657, -2.0376, -6.2923)
Ross 128 (-10.9032, 0.5841, -0.1533)
EZ Aquarii A (10.1891, -3.7817, -2.9736)
Procyon (-4.7679, 10.3068, 1.0385)
61 Cygni A (6.4749, -6.0972, 7.1379)
(no common name) (1.0811, -5.7264, 9.9448)
GX Andromedae (8.3326, 0.6692, 8.0791)
epsilon Indi A (5.6566, -3.1565, -9.8938)
DX Cancri (-6.4213, 8.3823, 5.3285)
tau Ceti (10.2729, 5.0141, -3.2644)
Henry et al. 1997, Henry et al. 2006 (5.027, 6.918, -8.4073)
YZ Ceti (11.0277, 3.6097, -3.5473)
Luyten's Star (-4.5837, 11.4311, 1.1264)
Henry et al. 2006 (8.7233, 8.2061, 3.6345)
Henry et al. 2006 (1.0786, -5.4123, -11.2968)
Kapteyn's Star (1.8909, 8.8328, -9.0387)
AX Microscopii (7.5992, -6.5335, -8.0771)
Kruger 60 A (6.4687, -2.7464, 11.1147)
Jao et al. 2005, Costa et al. 2005 (-9.6063, 3.1105, -8.4532)
Ross 614 A (-1.7049, 13.2247, -0.6554)
Wolf 1061 (-5.1443, -12.4655, -3.0298)
van Maanen's Star (13.6846, 2.9806, 1.3211)
(no common name) (11.3095, 0.2665, -8.6359)
Wolf 424 A (-13.9875, -2.0457, 2.2442)
TZ Arietis (12.2353, 7.0795, 3.2771)
(no common name) (-0.5609, -5.4319, 13.7496)
(no common name) (-13.8115, 4.4743, -2.9108)
(no common name) (-1.3801, -10.0259, -10.8132)
G 208-044 A (5.0449, -9.3012, 10.3675)
WD 1142-645 (-6.3908, 0.3993, -13.6332)
(no common name) (15.1756, 0.4449, -2.0094)
Ross 780 (14.2451, -4.2692, -3.7805)
Henry et al. 2006 (-7.1126, 2.4369, -13.6817)
(no common name) (-11.1566, 2.7059, 10.9043)
(no common name) (-9.1672, 4.7029, 12.0439)
(no common name) (-13.5783, 6.3605, 5.4188)
(no common name) (8.4702, -6.2924, -12.1422)
(no common name) (7.5895, 10.7972, -9.3889)
Costa et al. 2005 (7.9766, 7.6393, -11.85)
(no common name) (-1.1686, -11.631, -11.415)
omicron 2 Eridani (7.1684, 14.5789, -2.1829)
EV Lacertae (11.1861, -3.6985, 11.5109)
70 Ophiuchi A (0.3954, -16.6257, 0.7261)
Altair (7.6831, -14.6368, 2.5793)
EI Cancri (-11.2654, 11.4404, 5.7684)
Henry et al. 2006 (-0.0037, 17.0657, 0.8068)
Henry et al. 2006 (4.3179, 16.6812, -2.0993)
(no common name) (-3.4371, 0.1849, 17.2119)
Wolf 498 (-15.3247, -7.617, 4.5507)
(no common name) (11.7114, -12.4986, -5.2283)
Stein 2051 (3.5173, 8.617, 15.4759)
(no common name) (-3.5981, 14.7553, 9.9643)
(no common name) (2.3992, -15.3135, 10.0653)
Wolf 1453 (2.309, 18.4396, -1.1943)
(no common name) (8.1371, 16.5581, -3.115)
sigma Draconis (2.5612, -6.0083, 17.6198)
(no common name) (-0.8057, 17.4634, -7.0152)
(no common name) (-0.601, -10.2413, -15.9916)
Wolf 1055 (6.2567, -17.9375, 1.7185)
Ross 47 (1.4545, 18.6368, 4.1406)
(no common name) (-12.7815, -12.502, -7.0124)
Jao et al. 2005 (4.6659, -12.6815, -13.7781)
(no common name) (19.3194, -0.9111, 0.811)
eta Cassiopei A (10.0831, 2.1939, 16.3959)
(no common name) (-8.7531, -11.6368, -12.7814)
(no common name) (18.6028, 1.2573, -5.3936)
Ross 882 (-8.5694, 17.441, 1.2064)
36 Ophiuchi A (-3.3708, -17.0825, -8.7203)
(no common name) (8.6347, -13.4006, -11.6253)
82 Eridani (9.2867, 11.0593, -13.4997)
(no common name) (-0.3368, -6.4803, 18.7125)
delta Pavonis (4.2835, -6.8076, -18.2207)
QY Aurigae A (-4.7108, 14.9369, 12.4713)
HN Librae (-15.2883, -12.19, -4.3418)
(no common name) (-14.1725, 10.1497, 9.8693)
(no common name) (-9.1472, 8.0667, 16.0018)
(no common name) (7.8699, -11.9032, -14.3515)
Wolf 562 (-13.0823, -15.5128, -2.7517)
EQ Pegasi (19.2964, -2.3807, 7.0524)
Henry et al. 2006 (-13.6718, 13.6261, 7.734)
Henry et al. 2006 (-16.0765, -2.7492, -12.9185)
(no common name) (-3.0247, -14.2659, 14.926)
(no common name) (-13.1998, -12.8176, -9.8507)
(no common name) (-5.9686, -14.6438, 13.6811)
WD 0552-041 (0.4441, 20.9847, -1.5308)
Wolf 630 A (-5.7872, -20.0066, -3.0518)
(no common name) (11.2897, -2.3337, 17.8668)
Jao et al. 2005 (-6.3421, 4.3276, -19.8823)
GL Virginis (-20.8614, -1.7334, 4.1165)
(no common name) (-5.0301, -11.486, 17.4528)
Ross 104 (-19.3405, 5.1762, 8.4297)
                               *                                      
                                           *                          
                                                                      
                                                                      
             *       *       *                                        
                              *                    *                  
       *    *                            *   *           *            
            *                                                         
                                   *  *              *                
                                       *                              
              *                           *    *               *      
                                     3*                               
         *                        * 4    *      *                     
*           * *                                                       
                                                   * *     *          
    *    *                        *                        *          
                 *              1              5        *             
              * *      **                                      *      
     *                  2         S                            *      
                   7    *                        *               *    
        *              *                                         *    
                      *                         *             *       
            *     *                          *                        
   *                   *    **                  9                     
         *             *         8      6           *      *          
           * *         *      *    *           **             *       
                                     *               *                
                           *            * *                           
            *                 *   *           *                       
                                                                      
                          *            *                              
                                  *                                   
                                                                      
                                                                      
                              *     *                                 


------------------------------------------------------------------------------*/
