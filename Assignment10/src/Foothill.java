/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment 10 - pseudo polygons. this program create
 * polygons based on points from cartesian graphs
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

/**
 * main class of the program
 * 
 * @author R12KY
 */
public class Foothill
{
   
   // main method
   /**
    * main method of the class
    * 
    * @param args
    */
   public static void main(String[] args)
   {
      // declare arrays to supply points coordinates
      double xVal1[] =
         { 3.9, -8, 89, 20, 27, 8, 2.3, 3.4, -2.8, -21, 1.65, 9.33, 7.43, 12,
               2.89 };
      double yVal1[] =
         { -4.6, 2.1, 3.5, -4.3, 2.22, 9.32, 22.2, -12.3, 0.01, 4.92, 3.21, -2,
               0.0, -2.4, 31 };
      double xVal2[] =
         { 2.3, 3.4, -2.8, -21, 1.65, 9.33, 7.43, 12, 2, 3.9, -8, 89, 20, 27,
               8.735 };
      double yVal2[] =
         { 3.21, -2, 0.0, -2.4, 31, 4.6, 2.1, 3.5, -4.3, 2.22, 9.32, 22.2,
               -12.3, 0.01, 4.92 };
      
      // run
      System.out.println("Polygon Machine: ");
      createLine(80, "=");
      
      System.out.println("polygon1 made by constructor: ");
      Polygon polygon1 = new Polygon(2, xVal2, yVal1);
      createLine(80, "-");
      
      System.out.println("display polygon 1: ");
      polygon1.showPolygon();
      createLine(80, "-");
      
      System.out.println("add a point object to polygon1: ");
      Point point1 = new Point(8.9, -2.34);
      polygon1.addPoint(point1);
      polygon1.showPolygon();
      createLine(80, "-");
      
      System.out.println("add point manually to the polygon1: ");
      polygon1.addPoint(7.23, 3.14);
      polygon1.showPolygon();
      createLine(80, "-");
      
      System.out.println("add a manual out of range point to the polygon1: ");
      polygon1.addPoint(27.23, 13.14);
      polygon1.showPolygon();
      createLine(80, "-");
      
      System.out.println("notice that its altered to default values "
            + "(in this case 0.0)");
      createLine(80, "-");
      
      System.out.println("change MIN_VAL to -10 and MAX_VAL to 18 in the Point "
                  + "class: ");
      System.out.println(Point.setRange(-10, 18));
      createLine(80, "-");
      
      System.out.println("Create another three polygons from arrays: ");
      Polygon polygon2 = new Polygon(7, xVal1, yVal1);
      Polygon polygon3 = new Polygon(4, xVal2, yVal2);
      Polygon polygon4 = new Polygon(13, xVal1, yVal2);
      createLine(80, "-");
      
      System.out.println("display Polygon 2: ");
      polygon2.showPolygon();
      createLine(80, "-");
      
      System.out.println("display Polygon 3: ");
      polygon3.showPolygon();
      createLine(80, "-");
      
      System.out.println("display Polygon 4: ");
      polygon4.showPolygon();
      createLine(80, "-");
      
      System.out.println("note that out of range values altered into default"
            + "(in this current case its 4.0).");
      createLine(80, "-");
      
      System.out.println("create another polygon from default constructor: ");
      Polygon polygon5 = new Polygon();
      createLine(80, "-");
      
      System.out.println("display Polygon 5: ");
      polygon5.showPolygon();
      createLine(80, "-");
      
      System.out.println("adding points to the polygon 5: ");
      Point point2 = new Point(7.45, 3.34);
      Point point3 = new Point(100, 100);
      polygon5.addPoint(3.2, -2);
      polygon5.addPoint(4.3, 2.9);
      polygon5.addPoint(point2);
      polygon5.addPoint(point3);
      createLine(80, "-");
      
      System.out.println("display Polygon 5: ");
      polygon5.showPolygon();
      createLine(80, "-");
      
      System.out.println("note that out of range values altered into default"
            + "(in this current case its 4.0) while the two other points are"
            + "succesfully set.");
      createLine(80, "-");
      
      System.out.println("create another polygon with bad values, polygon 6: ");
      Polygon polygon6 = new Polygon(-10, xVal1, yVal2);
      createLine(80, "-");
      
      System.out.println("display Polygon 6: ");
      polygon6.showPolygon();
      createLine(80, "-");
      
      System.out.println("note that the inputNum is out of range, so the"
            + " polygon is created using the default constructor instead. ");
      createLine(80, "-");
      
      System.out.println("adding points to the polygon 6: ");
      polygon6.addPoint(point2);
      polygon6.addPoint(23, -2);
      polygon6.addPoint(point1);
      createLine(80, "-");
      
      System.out.println("display Polygon 6: ");
      polygon6.showPolygon();
      createLine(80, "-");
      
      System.out.println("overwrite the points in polygon 6 using setPoints "
            + "method ");
      polygon6.setPoints(12, xVal1, yVal2);
      createLine(80, "-");
      
      System.out.println("display Polygon 6: ");
      polygon6.showPolygon();
      createLine(80, "-");
      
      System.out.println("overwrite the points in polygon 6 using setPoints "
            + "method again");
      polygon6.setPoints(4, xVal2, yVal1);
      createLine(80, "-");
      
      System.out.println("display Polygon 6: ");
      polygon6.showPolygon();
      createLine(80, "-");
      
      System.out.println("Polygon demonstration is done.");
      createLine(80, "=");
      
      System.out.println(polygon6);
      
   }
   
   // other helper methods
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

/**
 * sub class of the program that creates a Polygon object that consist of Point
 * objects
 * 
 * @author R12KY
 */
class Polygon
{
   // constants
   private static final int MAX_POINTS = 100;
   private static final int DEFAULT_NUM = 0;
   
   // member fields
   private int numPoints;
   private Point points[] = new Point[MAX_POINTS];
   
   // default constructor
   /**
    * default constructor
    */
   Polygon()
   {
      numPoints = DEFAULT_NUM;
   }
   
   // constructors
   /**
    * constructor
    * 
    * @param numPoints as the number of points that want to be added
    * @param x array as the x coordinates of the points
    * @param y array as the y coordinates of the points
    */
   Polygon(int numPoints, double x[], double y[])
   {
      setAll(numPoints, x, y);
   }
   
   // check range method
   /**
    * method that validate the input whether the input is out of range or not
    * 
    * @param inputNum as the input number
    * @return true if its in range, false if its not
    */
   private boolean checkInputNum(int inputNum)
   {
      if (points.length > inputNum && inputNum > 0)
         return true;
      
      return false;
   }
   
   /**
    * similar to checkInputNum, but this one check whether the numPoint have
    * reached the limit of the points array (max points).
    * 
    * @param numPoints
    * @return
    */
   private boolean checkNumPoints(int numPoints)
   {
      if (points.length > numPoints && numPoints >= 0)
         return true;
      
      return false;
   }
   
   // mutators methods
   /**
    * method that set the points of the polygon, help the constructors
    * 
    * @param numPoints as the number of points that exist in the polygon
    * @param x array as the x coordinates of the points
    * @param y array as the y coordinates of the points
    */
   private void setAll(int numPoints, double x[], double y[])
   {
      if (!setPoints(numPoints, x, y))
         numPoints = DEFAULT_NUM;
   }
   
   /**
    * mutator method that overwrite the current polygon and set the points
    * 
    * @param inputNum the input number of points that want to be implemented
    * @param xValues the array of x values
    * @param yValues the array of y values
    * @return false if its out of range, true if its in range and successful
    */
   public boolean setPoints(int inputNum, double xValues[], double yValues[])
   {
      if (!checkInputNum(inputNum))
         return false;
      
      // check whether the array supply the points (avoid NullPointerException)
      if (xValues.length < inputNum || yValues.length < inputNum)
         return false;

      this.numPoints = DEFAULT_NUM;
      
      for (int i = 0; i < inputNum; i++)
      {
         points[i] = new Point(xValues[i], yValues[i]);
         
         this.numPoints++;
      }
      
      return true;
   }
   
   /**
    * this method add a point to the polygon object
    * 
    * @param x the x coordinate of the point
    * @param y the y coordinate of the point
    * @return true if everything go as planned, false if its not
    */
   public boolean addPoint(double x, double y)
   {
      if (!checkNumPoints(numPoints))
         return false;
      
      points[numPoints] = new Point(x, y);
      numPoints++;
      
      return true;
   }
   
   /**
    * this method add a point object to the polygon object
    * 
    * @param point the Point object
    * @return true if everything go as planned, false if its not
    */
   public boolean addPoint(Point point)
   {
      if (!checkNumPoints(numPoints))
         return false;
      
      points[numPoints] = new Point(point.getX(), point.getY());
      numPoints++;
      
      return true;
   }
   
   // void methods
   /**
    * show the points of the polygons to the screen
    */
   public void showPolygon()
   {
      System.out.print("{");
      
      for (int i = 0; i < numPoints; i++)
      {
         points[i].displayPoint();
         
         if (i != numPoints - 1)
            System.out.print(", ");
      }
      
      System.out.print("}\n");
   }
}

/**
 * sub class of the program that creates a Point Object
 * 
 * @author R12KY
 */
class Point
{
   // constants
   private static double MIN_VAL = -10;
   private static double MAX_VAL = 10;
   
   // member fields
   private double x;
   private double y;
   
   // default constructor
   /**
    * default constructor
    */
   Point()
   {
      x = (MAX_VAL + MIN_VAL) / 2;
      y = (MAX_VAL + MIN_VAL) / 2;
   }
   
   // constructors
   /**
    * constructor
    * 
    * @param x as the x coordinate of the point
    * @param y as the y coordinate of the point
    */
   Point(double x, double y)
   {
      setAll(x, y);
   }
   
   // check range method
   /**
    * validate the input number, whether it is in range or not
    * 
    * @param inputNum the input number
    * @return true if its in range, false if its out of range
    */
   private boolean validate(double inputNum)
   {
      if (inputNum >= MIN_VAL && inputNum <= MAX_VAL)
         return true;
      
      return false;
   }
   
   // setter method
   /**
    * set the value of the point, this method help the constructors
    * 
    * @param x as the x coordinate of the point
    * @param y as the y coordinate of the point
    */
   private void setAll(double x, double y)
   {
      if (!set(x, y))
         set((MAX_VAL + MIN_VAL) / 2, (MAX_VAL + MIN_VAL) / 2);
   }
   
   // mutators
   /**
    * the mutator method of the whole class
    * 
    * @param x as the x coordinate of the point
    * @param y as the y coordinate of the point
    * @return true if the value is successfully set, false if its not
    */
   public boolean set(double x, double y)
   {
      if (validate(x) && validate(y))
      {
         this.x = x;
         this.y = y;
         return true;
      }
      
      return false;
   }
   
   /**
    * the mutator method for range constants
    * 
    * @param newMinVal the soon to be new MIN_VAL
    * @param newMaxVal the soon to be new MAX_VAL
    * @return true if the value is successfully set, false if its not
    */
   public static boolean setRange(double newMinVal, double newMaxVal)
   {
      if (newMinVal < newMaxVal)
      {
         MIN_VAL = newMinVal;
         MAX_VAL = newMaxVal;
         return true;
      }
      
      return false;
   }
   
   // accessors
   /**
    * accessor method for x member data
    * 
    * @return the value of x member data
    */
   public double getX()
   {
      return x;
   }
   
   /**
    * accessor method for y member data
    * 
    * @return the value of y member data
    */
   public double getY()
   {
      return y;
   }
   
   // void methods
   /**
    * display the point to the screen
    */
   public void displayPoint()
   {
      System.out.print("(" + x + ", " + y + ")");
   }
}

/*--------------------------------SAMPLE RUN------------------------------------

Polygon Machine: 
================================================================================
polygon1 made by constructor: 
--------------------------------------------------------------------------------
display polygon 1: 
{(2.3, -4.6), (3.4, 2.1)}
--------------------------------------------------------------------------------
add a point object to polygon1: 
{(2.3, -4.6), (3.4, 2.1), (8.9, -2.34)}
--------------------------------------------------------------------------------
add point manually to the polygon1: 
{(2.3, -4.6), (3.4, 2.1), (8.9, -2.34), (7.23, 3.14)}
--------------------------------------------------------------------------------
add a manual out of range point to the polygon1: 
{(2.3, -4.6), (3.4, 2.1), (8.9, -2.34), (7.23, 3.14), (0.0, 0.0)}
--------------------------------------------------------------------------------
notice that its altered to default values (in this case 0.0)
--------------------------------------------------------------------------------
change MIN_VAL to -10 and MAX_VAL to 18 in the Point class: 
true
--------------------------------------------------------------------------------
Create another three polygons from arrays: 
--------------------------------------------------------------------------------
display Polygon 2: 
{(3.9, -4.6), (-8.0, 2.1), (4.0, 4.0), (4.0, 4.0), (4.0, 4.0), (8.0, 9.32), (4.0
, 4.0)}
--------------------------------------------------------------------------------
display Polygon 3: 
{(2.3, 3.21), (3.4, -2.0), (-2.8, 0.0), (4.0, 4.0)}
--------------------------------------------------------------------------------
display Polygon 4: 
{(3.9, 3.21), (-8.0, -2.0), (4.0, 4.0), (4.0, 4.0), (4.0, 4.0), (8.0, 4.6), (2.3
, 2.1), (3.4, 3.5), (-2.8, -4.3), (4.0, 4.0), (1.65, 9.32), (4.0, 4.0), (4.0, 4.
0)}
--------------------------------------------------------------------------------
note that out of range values altered into default(in this current case its 4.0)
.
--------------------------------------------------------------------------------
create another polygon from default constructor: 
--------------------------------------------------------------------------------
display Polygon 5: 
{}
--------------------------------------------------------------------------------
adding points to the polygon 5: 
--------------------------------------------------------------------------------
display Polygon 5: 
{(3.2, -2.0), (4.3, 2.9), (7.45, 3.34), (4.0, 4.0)}
--------------------------------------------------------------------------------
note that out of range values altered into default(in this current case its 4.0)
 while the two other points aresuccesfully set.
--------------------------------------------------------------------------------
create another polygon with bad values, polygon 6: 
--------------------------------------------------------------------------------
display Polygon 6: 
{}
--------------------------------------------------------------------------------
note that the inputNum is out of range, so the polygon is created using the defa
ult constructor instead. 
--------------------------------------------------------------------------------
adding points to the polygon 6: 
--------------------------------------------------------------------------------
display Polygon 6: 
{(7.45, 3.34), (4.0, 4.0), (8.9, -2.34)}
--------------------------------------------------------------------------------
overwrite the points in polygon 6 using setPoints method 
--------------------------------------------------------------------------------
display Polygon 6: 
{(3.9, 3.21), (-8.0, -2.0), (4.0, 4.0), (4.0, 4.0), (4.0, 4.0), (8.0, 4.6), (2.3
, 2.1), (3.4, 3.5), (-2.8, -4.3), (4.0, 4.0), (1.65, 9.32), (4.0, 4.0)}
--------------------------------------------------------------------------------
overwrite the points in polygon 6 using setPoints method again
--------------------------------------------------------------------------------
display Polygon 6: 
{(2.3, -4.6), (3.4, 2.1), (-2.8, 3.5), (4.0, 4.0)}
--------------------------------------------------------------------------------
Polygon demonstration is done.
================================================================================

------------------------------------------------------------------------------*/