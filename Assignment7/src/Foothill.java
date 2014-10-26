/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * This program is due to Assignment_7 in course "FH CS 001 ML F13" this program
 * demonstrate the implementation of Object Oriented Programing. this assignment
 * creates the iTunes object and make it as if a music player library.
 * Created By: Muhammad Rizky Wellyanto 
 * Student ID: 20144588
 */

/*
 * NOTE : I'm not sure how much error testing should i present sir. So, I
 *        demonstrate everything through 10 arrays and manually hard coded 2
 *        objects.
 *        
 *        the arrays store the value of 10 instantiate iTunes object by the 
 *        user. 
 *        
 *        while the hard coded ones demonstrate the error testings. 
 *        
 *        even though i didn't see the requirement to put a sample run in this
 *        assignment, i present a sample run in the bottom of the source code
 *        just in case if you want to see it. 
 *        
 *        i Hope i wasn't adding too much stuff. looking forward for your 
 *        feedback. Thank you.
 */

// import scanner class for user input
import java.util.Scanner;

/**
 * main class
 * @author R12KY
 *
 */
public class Foothill
{
   // create a global scanner
   private static Scanner keyboard;
   
   // main method of the program
   public static void main(String[] args)
   {
      // declare local variables
      String name;
      String artist;
      int bitRate;
      int totalTime;
      
      // iTunes objects to demonstrate the mutators, error testing, etc.
      iTunes iTuneObject1;
      iTunes iTuneObject2;
      
      // create an iTune object arrays
      iTunes iTuneObjects[] = new iTunes [10];
      
      // create scanner object
      keyboard = new Scanner(System.in);
      
      createLine(80, "-");
      
      /*
       * demonstrations
       * instantiate 10 iTuneObjects using an array and a loop and
       * ask the user to set the values.
       */ 
      for(int counter = 0; counter < 10; counter +=1)
      {
         System.out.print("Please input the name title of the song: ");
         name = keyboard.nextLine();
         System.out.print("Please input the name of the artist: ");
         artist = keyboard.nextLine();
         System.out.print("Please input the bit rate of the song: ");
         bitRate = Integer.parseInt(keyboard.nextLine());
         System.out.print("Please input the duration of the song: ");
         totalTime = Integer.parseInt(keyboard.nextLine());
         
         createLine(80, "-");
         
         iTuneObjects[counter] = new iTunes(name, artist, bitRate, totalTime);
         iTuneObjects[counter].display();
         
         createLine(80, "-");
      }
      
      /*
       * another demonstrations
       * a demo of mutating and accessing iTuneObject1
       * this sequence only for error testing purpose
       */
      iTuneObject1 = new iTunes();
      
      iTuneObject1.display();
      
      displayProgress(iTuneObject1.setName("Breaking The Habit"));
      iTuneObject1.display();
      
      displayProgress(iTuneObject1.setName(""));
      iTuneObject1.display();
      
      displayProgress(iTuneObject1.setArtist("Linking Park"));
      iTuneObject1.display();
      
      displayProgress(iTuneObject1.setBitRate(12));
      iTuneObject1.display();
      
      displayProgress(iTuneObject1.setPlayTime(400000));
      iTuneObject1.display();
      
      displayProgress(iTuneObject1.setBitRate(300));
      iTuneObject1.display();
      
      System.out.println(iTuneObject1.getName());
      
      createLine(80, "-");
      
      /*
       * another demonstrations
       * a demo of mutating and accessing iTuneObject2
       * this sequence only for error testing purpose
       */
      iTuneObject2 = new iTunes("", "Oasis", 0, 200000);
      
      iTuneObject2.display();
      
      displayProgress(iTuneObject2.setName("Wonderwall"));
      iTuneObject2.display();
      
      displayProgress(iTuneObject2.setBitRate(800));
      iTuneObject2.display();
      
      displayProgress(iTuneObject2.setBitRate(700));
      iTuneObject2.display();
      
      System.out.println(iTuneObject2.getName());
      
      createLine(80, "-");
      
   }
   
   /**
    * a method that display the a value to the screen whether its true or false
    * this method is only for error testing purpose.
    * @param success
    */
   private static void displayProgress(boolean success)
   {
      createLine(80, "-");
      System.out.println("Trying to mutate the value...");
      createLine(80, "-");
      
      if (success == true)
      {
         System.out.println("Success to mutate the value!");
      }
      else
      {
         System.out.println("Fails to mutate the value!");
      }
      createLine(80, "-");
   }
   
   /**
    * this method is just to make the program look "beautiful"
    * 
    * @param length
    * @param shape
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
 * supporting class
 * @author R12KY
 *
 */
class iTunes
{
   // declare constants
   public static final int MIN_BITRATE = 64;
   public static final int MAX_BITRATE = 705;
   public static final int MIN_STR_LENGTH = 1;
   public static final int MAX_STR_LENGTH = 80;
   public static final int MIN_PLAY_TIME = 1000;
   public static final int MAX_PLAY_TIME = 1000*60*60;
   public static final int DEFAULT_BITRATE = 64;
   public static final int DEFAULT_PLAYTIME = 1000;
   public static final String DEFAULT_STRING = " (undefined) ";
   
   // declare instance variables
   private String name;
   private String artist;
   private int bitRate;
   private int totalTime;
   
   /**
    * Default constructor
    */
   iTunes()
   {
      name = DEFAULT_STRING;
      artist = DEFAULT_STRING;
      bitRate = DEFAULT_BITRATE;
      totalTime = DEFAULT_PLAYTIME;
   }
   
   /**
    * Constructor
    * @param name
    * @param artist
    * @param bitRate
    * @param totalTime
    */
   iTunes(String name, String artist, int bitRate, int totalTime)
   {
      if (!setName(name))
      {
         this.name = DEFAULT_STRING;
      }
      if (!setArtist(artist))
      {
         this.artist = DEFAULT_STRING;
      }
      if (!setBitRate(bitRate))
      {
         this.bitRate = DEFAULT_BITRATE;
      }
      if (!setPlayTime(totalTime))
      {
         this.totalTime = DEFAULT_PLAYTIME;
      }
   }
   
   /**
    * a mutator that set the name if the input is in range and also return
    * boolean true. if its out of range, does not set the value and return false
    * instead
    * @param name
    * @return
    */
   public boolean setName(String name)
   {
      if (name.length() >= MIN_STR_LENGTH && name.length() <= MAX_STR_LENGTH)
      {
         this.name = name;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**
    * a mutator that set the artist if the input is in range and also return
    * boolean true. if its out of range, does not set the value and return false
    * instead
    * @param artist
    * @return
    */
   public boolean setArtist(String artist)
   {
      if (artist.length() >= MIN_STR_LENGTH && 
            artist.length() <= MAX_STR_LENGTH)
      {
         this.artist = artist;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**
    * a mutator that set the bitRate if the input is in range and also return
    * boolean true. if its out of range, does not set the value and return false
    * instead
    * @param bitRate
    * @return
    */
   public boolean setBitRate(int bitRate)
   {
      if (bitRate >= MIN_BITRATE && bitRate <= MAX_BITRATE)
      {
         this.bitRate = bitRate;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**
    * a mutator that set the totalTime if the input is in range and also return
    * boolean true. if its out of range, does not set the value and return false
    * instead
    * @param totalTime
    * @return
    */
   public boolean setPlayTime(int totalTime)
   {
      if (totalTime >= MIN_PLAY_TIME && totalTime <= MAX_PLAY_TIME)
      {
         this.totalTime = totalTime;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**
    * accessor method, return the field that stored in name variable
    * @return
    */
   public String getName()
   {
      return name;
   }
   
   /**
    * accessor method, return the field that stored in artist variable
    * @return
    */
   public String getArtist()
   {
      return artist;
   }
   
   /**
    * accessor method, return the field that stored in bitRate variable
    * @return
    */
   public int getBitRate()
   {
      return bitRate;
   }
   
   /**
    * accessor method, return the field that stored in totalTime variable
    * @return
    */
   public int getTotalTime()
   {
      return totalTime;
   }
   
   /**
    * a method that contains all of the objects fields
    * @param string
    * @return
    */
   public String toString()
   {
      String output = "";
      
      output += "   Title: " + name;
      output += "\n   Artist: " + artist;
      output += "\n   Bit Rate: " + bitRate;
      output += "\n   Play Time: " + timeInMinutesAndSeconds();
      
      return output;
   }
   
   private String timeInMinutesAndSeconds()
   {
      int minute = totalTime / 60000;
      int second = (totalTime % 60000) / 1000 ;
            
      return String.format("%02d:%02d", minute, second);
   }
   
   /**
    * a method that calls the toString() method and then display the result to
    * the screen
    */
   public void display()
   {
      System.out.println("SONG DESCRIPTION");
      System.out.println(toString());
   }
}

/*-------------------------------SAMPLE RUN-------------------------------------

--------------------------------------------------------------------------------
Please input the name title of the song: I Could Be The One
Please input the name of the artist: Avicii & Nicky Romero
Please input the bit rate of the song: 750
Please input the duration of the song: 2200000
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: I Could Be The One
   Artist: Avicii & Nicky Romero
   Bit Rate: 64
   Play Time: 36:40
--------------------------------------------------------------------------------
Please input the name title of the song: Niggas In Paris
Please input the name of the artist: Jay-Z & Kanye West
Please input the bit rate of the song: 100
Please input the duration of the song: 300000
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Niggas In Paris
   Artist: Jay-Z & Kanye West
   Bit Rate: 100
   Play Time: 05:00
--------------------------------------------------------------------------------
Please input the name title of the song: Dear God
Please input the name of the artist: Avenged Sevenfold
Please input the bit rate of the song: 585
Please input the duration of the song: 385453
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Dear God
   Artist: Avenged Sevenfold
   Bit Rate: 585
   Play Time: 06:25
--------------------------------------------------------------------------------
Please input the name title of the song: 
Please input the name of the artist: Maliq and the Essentials
Please input the bit rate of the song: 493
Please input the duration of the song: 289341
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title:  (undefined) 
   Artist: Maliq and the Essentials
   Bit Rate: 493
   Play Time: 04:49
--------------------------------------------------------------------------------
Please input the name title of the song: Stairway to Heaven
Please input the name of the artist: 
Please input the bit rate of the song: 730
Please input the duration of the song: 2000500
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Stairway to Heaven
   Artist:  (undefined) 
   Bit Rate: 64
   Play Time: 33:20
--------------------------------------------------------------------------------
Please input the name title of the song: Dear Boy
Please input the name of the artist: Avicii
Please input the bit rate of the song: 700
Please input the duration of the song: -20
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Dear Boy
   Artist: Avicii
   Bit Rate: 700
   Play Time: 00:01
--------------------------------------------------------------------------------
Please input the name title of the song: This Love
Please input the name of the artist: Maroon 5
Please input the bit rate of the song: -1000
Please input the duration of the song: 230000
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: This Love
   Artist: Maroon 5
   Bit Rate: 64
   Play Time: 03:50
--------------------------------------------------------------------------------
Please input the name title of the song: Love Song
Please input the name of the artist: The Cure
Please input the bit rate of the song: 200
Please input the duration of the song: 472894
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Love Song
   Artist: The Cure
   Bit Rate: 200
   Play Time: 07:52
--------------------------------------------------------------------------------
Please input the name title of the song: The Spirit Carries On
Please input the name of the artist: Dream Theater
Please input the bit rate of the song: 699
Please input the duration of the song: 238718
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: The Spirit Carries On
   Artist: Dream Theater
   Bit Rate: 699
   Play Time: 03:58
--------------------------------------------------------------------------------
Please input the name title of the song: Mammoth
Please input the name of the artist: Dmitri Vegas & Like Mike
Please input the bit rate of the song: 400
Please input the duration of the song: 320189
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Mammoth
   Artist: Dmitri Vegas & Like Mike
   Bit Rate: 400
   Play Time: 05:20
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title:  (undefined) 
   Artist:  (undefined) 
   Bit Rate: 64
   Play Time: 00:01
--------------------------------------------------------------------------------
Trying to mutate the value...
--------------------------------------------------------------------------------
Success to mutate the value!
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Breaking The Habit
   Artist:  (undefined) 
   Bit Rate: 64
   Play Time: 00:01
--------------------------------------------------------------------------------
Trying to mutate the value...
--------------------------------------------------------------------------------
Fails to mutate the value!
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Breaking The Habit
   Artist:  (undefined) 
   Bit Rate: 64
   Play Time: 00:01
--------------------------------------------------------------------------------
Trying to mutate the value...
--------------------------------------------------------------------------------
Success to mutate the value!
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Breaking The Habit
   Artist: Linking Park
   Bit Rate: 64
   Play Time: 00:01
--------------------------------------------------------------------------------
Trying to mutate the value...
--------------------------------------------------------------------------------
Fails to mutate the value!
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Breaking The Habit
   Artist: Linking Park
   Bit Rate: 64
   Play Time: 00:01
--------------------------------------------------------------------------------
Trying to mutate the value...
--------------------------------------------------------------------------------
Success to mutate the value!
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Breaking The Habit
   Artist: Linking Park
   Bit Rate: 64
   Play Time: 06:40
--------------------------------------------------------------------------------
Trying to mutate the value...
--------------------------------------------------------------------------------
Success to mutate the value!
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Breaking The Habit
   Artist: Linking Park
   Bit Rate: 300
   Play Time: 06:40
Breaking The Habit
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title:  (undefined) 
   Artist: Oasis
   Bit Rate: 64
   Play Time: 03:20
--------------------------------------------------------------------------------
Trying to mutate the value...
--------------------------------------------------------------------------------
Success to mutate the value!
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Wonderwall
   Artist: Oasis
   Bit Rate: 64
   Play Time: 03:20
--------------------------------------------------------------------------------
Trying to mutate the value...
--------------------------------------------------------------------------------
Fails to mutate the value!
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Wonderwall
   Artist: Oasis
   Bit Rate: 64
   Play Time: 03:20
--------------------------------------------------------------------------------
Trying to mutate the value...
--------------------------------------------------------------------------------
Success to mutate the value!
--------------------------------------------------------------------------------
SONG DESCRIPTION
   Title: Wonderwall
   Artist: Oasis
   Bit Rate: 700
   Play Time: 03:20
Wonderwall
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/