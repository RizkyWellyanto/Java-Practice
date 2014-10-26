/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 7 in CS 1B Jesse Cecil Winter 2014
 * this program is called GUI Cards.This assignment combines 2D arrays,
 * interfaces (including Cloneable), and a very active industrial application,
 * optical scanning and pattern recognition.
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

/**
 * main client
 * 
 * @author MuhammadRizky
 * 
 */
public class Foothill
{
   // main method
   public static void main(String[] args)
   {
      String[] sImageIn =
         { "                                      ",
               "                                      ",
               "                                      ",
               "* * * * * * * * * * * * * * * * *     ",
               "*                                *    ",
               "**** * ****** ** ****** *** ****      ",
               "* ********************************    ",
               "*    *   *  * *  *   *  *   *  *      ",
               "* **    *      *   **    *       *    ",
               "****** ** *** **  ***** * * *         ",
               "* ***  ****    * *  **        ** *    ",
               "* * *   * **   *  *** *   *  * **     ",
               "**********************************    " };
      
      String[] sImageIn_2 =
         { "                                          ",
               "                                          ",
               "* * * * * * * * * * * * * * * * * * *     ",
               "*                                    *    ",
               "**** *** **   ***** ****   *********      ",
               "* ************ ************ **********    ",
               "** *      *    *  * * *         * *       ",
               "***   *  *           * **    *      **    ",
               "* ** * *  *   * * * **  *   ***   ***     ",
               "* *           **    *****  *   **   **    ",
               "****  *  * *  * **  ** *   ** *  * *      ",
               "**************************************    " };
      
      BarcodeImage bc = new BarcodeImage(sImageIn);
      DataMatrix dm = new DataMatrix(bc);
      
      // First secret message
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
      // second secret message
      bc = new BarcodeImage(sImageIn_2);
      dm.scan(bc);
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
      // create your own message
      dm.readText("CIS 1B rocks more than Zeppelin");
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
   }
}

/**
 * barcode interface that will be implemented on DataMatrix class
 * 
 * @author MuhammadRizky
 * 
 */
interface BarcodeIO
{
   public boolean scan(BarcodeImage bc);
   
   public boolean readText(String text);
   
   public boolean generateImageFromText();
   
   public boolean translateImageToText();
   
   public void displayTextToConsole();
   
   public void displayImageToConsole();
}

/**
 * basic barcode class
 * 
 * @author MuhammadRizky
 * 
 */
class BarcodeImage implements Cloneable
{
   // statics
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   public static final char TRUE_CHAR = '*';
   public static final char FALSE_CHAR = ' ';
   
   // member data
   private boolean[][] image_data;
   
   // constructors
   BarcodeImage()
   {
      // local vars
      int row, col;
      
      // instantiate the member data array
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      
      // loop through every row
      for (row = 0; row < this.image_data.length; row++)
      {
         // loop through every column
         for (col = 0; col < this.image_data[row].length; col++)
         {
            // set all of the pixel to false
            this.image_data[row][col] = false;
         }
      }
   }
   
   BarcodeImage(String[] str_data)
   {
      // local vars
      int row, col;
      
      // instantiate member data array
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      
      // loop through every row
      for (row = 0; row < this.image_data.length; row++)
      {
         // loop through every column
         for (col = 0; col < this.image_data[row].length; col++)
         {
            // set the pixel from bottom left corner
            try
            {
               this.image_data[MAX_HEIGHT - 1 - row][col] = 
                     stringToBoolArray(str_data[str_data.length
                                                - 1 - row])[col];
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
               // just do nothing, if there's an error which means either the
               // input row or the input column is less than the maximum.
               // this is not a big deal.
            }
         }
      }
   }
   
   // mutator
   /**
    * set the boolean value on that particular pixel
    * 
    * @param row of the pixel
    * @param col column of the pixel
    * @param value boolean value
    * @return true if success, false otherwise
    */
   public boolean setPixel(int row, int col, boolean value)
   {
      // change the pixel value if its right
      if (isValid(row, col))
         this.image_data[row][col] = value;
      
      // false otherwise
      return false;
   }
   
   // accessor
   /**
    * get the boolean value on that particular pixel
    * 
    * @param row of the pixel
    * @param col column of the pixel
    * @param value boolean value
    * @return true if success, false otherwise
    */
   public boolean getPixel(int row, int col)
   {
      // if the input is in range return the designated pixel
      if (isValid(row, col))
         return this.image_data[row][col];
      
      // return if its not valid
      return false;
   }
   
   // other methods
   /**
    * just a validate method
    * 
    * @param row input row
    * @param col input column
    * @return true if the inut is in range, false otherwise
    */
   private boolean isValid(int row, int col)
   {
      // check the input row and column range
      if (row <= this.image_data.length && row >= 0)
         if (col <= this.image_data[0].length && col >= 0)
            return true; // true if its in range
            
      // false if its out of range
      return false;
   }
   
   /**
    * a display method just for testing purpose
    */
   private void displayToConsole()
   {
      int row, col;
      
      // top row border
      System.out.println();
      for (col = 0; col < BarcodeImage.MAX_WIDTH + 2; col++)
         System.out.print("-");
      System.out.println();
      
      // now each row from 0 to MAX_WIDTH, adding border chars
      for (row = 0; row < BarcodeImage.MAX_HEIGHT; row++)
      {
         System.out.print("|");
         for (col = 0; col < BarcodeImage.MAX_WIDTH; col++)
            System.out.print(boolToString(this.image_data[row][col]));
         System.out.println("|");
      }
      
      // bottom
      for (col = 0; col < BarcodeImage.MAX_WIDTH + 2; col++)
         System.out.print("-");
      System.out.println();
   }
   
   /**
    * inherited clone method
    */
   public BarcodeImage clone() throws CloneNotSupportedException
   {
      int row, col;
      
      // always do this first - parent will clone its data correctly
      BarcodeImage newBc = (BarcodeImage) super.clone();
      
      // now do the immediate class member objects
      newBc.image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      for (row = 0; row < MAX_HEIGHT; row++)
         for (col = 0; col < MAX_WIDTH; col++)
            newBc.image_data[row][col] = this.image_data[row][col];
      
      return newBc;
   }
   
   // additional helper method
   /**
    * convert string into boolean array
    * 
    * @param data the string data
    * @return a boolean array
    */
   private boolean[] stringToBoolArray(String data)
   {
      // create the output boolean with the length of the string
      boolean[] bool = new boolean[data.length()];
      
      // instantiate the bool array
      for (int i = 0; i < bool.length; i++)
         bool[i] = false;
      
      // create a true false version of that string in the bool array
      for (int i = 0; i < data.length(); i++)
      {
         if (data.charAt(i) == TRUE_CHAR)
         {
            bool[i] = true;
         }
         else
         {
            bool[i] = false;
         }
      }
      
      // return the bool array
      return bool;
   }
   
   /**
    * convert boolean to string
    * 
    * @param bool the boolean value
    * @return string valueof that boolean
    */
   private String boolToString(boolean bool)
   {
      // local vars
      StringBuilder str = new StringBuilder("");
      
      // if true append *, append blank otherwise
      if (bool == true)
      {
         str.append(TRUE_CHAR);
      }
      else
      {
         str.append(FALSE_CHAR);
      }
      
      // return str
      return str.toString();
   }
   
}

/**
 * DataMatrix class
 * 
 * @author MuhammadRizky
 * 
 */
class DataMatrix implements BarcodeIO
{
   // statics
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   
   // member datas
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;
   
   // default datas
   public static final int DEF_HEIGHT = 0;
   public static final int DEF_WIDTH = 0;
   public static final String DEF_STRING = "";
   public static final int MAX_STR_LEN = 100;
   public static final int BINARY_LENGTH = 8;
   
   // constructors
   DataMatrix()
   {
      this(new BarcodeImage(), DEF_STRING);
   }
   
   DataMatrix(BarcodeImage image)
   {
      this(image, DEF_STRING);
   }
   
   DataMatrix(String text)
   {
      this(new BarcodeImage(), text);
   }
   
   DataMatrix(BarcodeImage image, String text)
   {
      if (!scan(image))
         this.image = new BarcodeImage();
      if (!readText(text))
         this.text = DEF_STRING;
      
   }
   
   // mutators
   /**
    * read the text and assign it to the member data
    */
   public boolean readText(String text)
   {
      if (isValid(text))
      {
         this.text = text;
         return true;
      }
      
      return false;
   }
   
   /**
    * scan a BarcodeImage object and clone it, also set the actual width and
    * height
    */
   public boolean scan(BarcodeImage image)
   {
      // try to clone the BarcodeImage Object
      try
      {
         this.image = (BarcodeImage) image.clone();
      }
      // return false if there's an error
      catch (CloneNotSupportedException e)
      {
         return false;
      }
      
      // set the actual width and height
      this.actualWidth = computeSignalWidth();
      this.actualHeight = computeSignalHeight();
      
      // return true if everything is fine
      return true;
   }
   
   // validate methods
   /**
    * just a validate method
    * 
    * @param str the input
    * @return true if it's in range, false otherwise
    */
   private boolean isValid(String str)
   {
      // return true if variables are in range
      if (str != null && str.length() < MAX_STR_LEN)
         return true;
      
      // return false otherwise
      return false;
   }
   
   // accessors
   /**
    * return the actual width member data
    * 
    * @return
    */
   public int actualWidth()
   {
      return this.actualWidth;
   }
   
   /**
    * return the actual height member data
    * 
    * @return
    */
   public int actualHeight()
   {
      return this.actualHeight;
   }
   
   // other methods
   /**
    * compute the width
    * 
    * @return the width of the BarcodeImage
    */
   private int computeSignalWidth()
   {
      // local vars
      int num = 0;
      
      // compute the length of the spine
      while (image.getPixel(BarcodeImage.MAX_HEIGHT - 1, num) == true)
      {
         num++;
      }
      
      // return the counter variable
      return num;
   }
   
   /**
    * compute the height
    * 
    * @return the height value of the BarcodeImage
    */
   private int computeSignalHeight()
   {
      // local vars
      int num = 0;
      
      // compute the height using the spine
      while (image.getPixel(BarcodeImage.MAX_HEIGHT - 1 - num, 0) == true)
      {
         num++;
      }
      
      // return the value
      return num;
   }
   
   /**
    * convert a text into a BarcodeImage
    */
   public boolean generateImageFromText()
   {
      // clear the member data first
      this.clearImage();
      
      // set the new width and height
      this.actualWidth = this.text.length() + 2;
      this.actualHeight = BINARY_LENGTH + 2;
      
      // set the left spine
      for (int i = 0; i < this.actualHeight; i++)
      {
         image.setPixel(BarcodeImage.MAX_HEIGHT - 1 - i, 0, true);
      }
      
      // set the content of the barcode, helped by WriteCharToCol method
      for (int i = 0; i < this.text.length(); i++)
      {
         WriteCharToCol(i + 1, text.charAt(i));
      }
      
      try
      {
         // set the right open border
         for (int i = 0; i < this.actualHeight; i++)
         {
            if (!(i % 2 == 1))
               image.setPixel(BarcodeImage.MAX_HEIGHT - 1 - i,
                     this.actualWidth - 1, true);
         }
      }
      catch (ArrayIndexOutOfBoundsException e)
      {
         // if something bad happened return false
         return false;
      }
      
      // return true if everything goes well
      return true;
   }
   
   /**
    * convert BarcodeImage to a text
    */
   public boolean translateImageToText()
   {
      // local variables
      StringBuilder str = new StringBuilder("");
      
      try
      {
         // read the columns, and append the char to the str
         for (int i = 1; i < this.actualWidth - 1; i++)
         {
            str.append(readCharFromCol(i));
         }
      }
      catch (Exception e)
      {
         // if there'are any error exception throwed, return false
         return false;
      }
      
      // set the str to the member data
      this.text = str.toString();
      
      // return true if everything is ok
      return true;
   }
   
   /**
    * display the text member data to the console
    */
   public void displayTextToConsole()
   {
      // just print the member data
      System.out.print(this.text);
   }
   
   /**
    * display the image member data to the console
    */
   public void displayImageToConsole()
   {
      // local variables
      int row, col;
      
      // top row border
      System.out.println();
      for (col = 0; col < this.actualWidth + 2; col++)
         System.out.print("-");
      System.out.println();
      
      // now each row from 0 to MAX_WIDTH, adding border chars
      for (row = this.actualHeight; row > 0; row--)
      {
         System.out.print("|");
         for (col = 0; col < this.actualWidth; col++)
            System.out.print(boolToString(this.image.getPixel(
                  BarcodeImage.MAX_HEIGHT - row, col)));
         System.out.println("|");
      }
      
      // bottom
      for (col = 0; col < this.actualWidth + 2; col++)
         System.out.print("-");
      System.out.println();
   }
   
   /**
    * read a coloumn and return a char representation of it
    * 
    * @param col the designated column
    * @return the char representation of that column
    */
   private char readCharFromCol(int col)
   {
      // local vars
      int someNum;
      StringBuilder str = new StringBuilder("");
      
      // check every row of that column and check if its true or false
      for (int i = 1; i < this.actualHeight - 1; i++)
      {
         // if its true, append 1 to the str. 0 otherwise
         str.insert(
               0,
               ((image.getPixel
                     (BarcodeImage.MAX_HEIGHT - 1 - i, col) == true) ? 1
                           : 0));
      }
      
      // convert the binary string into decimal integer
      someNum = Integer.parseInt(str.toString(), 2);
      
      // convert the decimal integer to char, and return it
      return (char) someNum;
   }
   
   /**
    * write a barcode column from a single char
    * 
    * @param col the designated column
    * @param code the char that willbe converted
    * @return true if suceess, false otherwise
    */
   private boolean WriteCharToCol(int col, int code)
   {
      if (col > this.actualWidth || code < 0 || code > 128)
         return false;
      
      // local vars
      int someNum = code;
      int tempNum = 0;
      StringBuilder str = new StringBuilder("");
      
      // convert the decimal integer into binary string
      while (someNum > 0)
      {
         tempNum = someNum % 2;
         str.insert(0, tempNum);
         someNum = someNum / 2;
      }
      
      // add zeros until the binary string is 8 digit, else there'll be errors
      while (str.length() < BINARY_LENGTH)
      {
         str.insert(0, 0);
      }
      
      // set the bottom spine
      image.setPixel(BarcodeImage.MAX_HEIGHT - 1, col, true);
      
      try
      {
         // set pixels based on the binary string
         for (int i = 1; i < this.actualHeight - 2; i++)
         {
            image.setPixel(BarcodeImage.MAX_HEIGHT - 1 - i, col, (str
                  .toString().charAt(i) == '1' ? true : false));
         }
      }
      catch (ArrayIndexOutOfBoundsException e)
      {
         // return false is something wrong
         return false;
      }
      
      // if its even set an open border on top
      if (!(col % 2 == 1))
         image.setPixel(BarcodeImage.MAX_HEIGHT - this.actualHeight, col, true);
      
      // returns true if everything is alright
      return true;
   }
   
   /**
    * display the raw BarcodeImage of the image member data
    */
   public void displayRawImage()
   {
      // local variables
      int row, col;
      
      // top row border
      System.out.println();
      for (col = 0; col < BarcodeImage.MAX_WIDTH + 2; col++)
         System.out.print("-");
      System.out.println();
      
      // now each row from 0 to MAX_WIDTH, adding border chars
      for (row = 0; row < BarcodeImage.MAX_HEIGHT; row++)
      {
         System.out.print("|");
         for (col = 0; col < BarcodeImage.MAX_WIDTH; col++)
            System.out.print(boolToString(this.image.getPixel(row, col)));
         System.out.println("|");
      }
      
      // bottom
      for (col = 0; col < BarcodeImage.MAX_WIDTH + 2; col++)
         System.out.print("-");
      System.out.println();
   }
   
   /**
    * clear the image member data of the object
    */
   private void clearImage()
   {
      // go to each pixel and set it to false
      for (int row = 0; row < BarcodeImage.MAX_HEIGHT; row++)
      {
         for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++)
         {
            this.image.setPixel(row, col, false);
         }
      }
   }
   
   /**
    * convert boolean to a string
    * 
    * @param bool the boolean
    * @return the string representation of that boolean
    */
   private String boolToString(boolean bool)
   {
      // local variables
      String str = "";
      
      // convert the bool to corresponding char
      if (bool == true)
      {
         str += BLACK_CHAR;
      }
      else
      {
         str += WHITE_CHAR;
      }
      
      // return the string
      return str;
   }
}

/*----------------------------------Sample Run----------------------------------

Don't forget to remove the tabs!
------------------------------------
|* * * * * * * * * * * * * * * * * |
|*                                *|
|**** * ****** ** ****** *** ****  |
|* ********************************|
|*    *   *  * *  *   *  *   *  *  |
|* **    *      *   **    *       *|
|****** ** *** **  ***** * * *     |
|* ***  ****    * *  **        ** *|
|* * *   * **   *  *** *   *  * ** |
|**********************************|
------------------------------------
You did it!  Great work.  Celebrate.
----------------------------------------
|* * * * * * * * * * * * * * * * * * * |
|*                                    *|
|**** *** **   ***** ****   *********  |
|* ************ ************ **********|
|** *      *    *  * * *         * *   |
|***   *  *           * **    *      **|
|* ** * *  *   * * * **  *   ***   *** |
|* *           **    *****  *   **   **|
|****  *  * *  * **  ** *   ** *  * *  |
|**************************************|
----------------------------------------
CIS 1B rocks more than Zeppelin
-----------------------------------
|* * * * * * * * * * * * * * * *  |
|*                               *|
|**** *   **** ** *   *   *  * *  |
|** *  * *****  **     * *      **|
|*        *    ** * *  *  *  ** * |
|* *      * *  **    * * *    ****|
|*  * *  *   *   *  *    * **     |
|*   ** ***************** ********|
|****  * ***** **** **** ******** |
|*********************************|
-----------------------------------

------------------------------------------------------------------------------*/
