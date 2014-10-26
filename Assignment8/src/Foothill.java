/**
 * file: Foothill.java
 * -----------------------------------------------------------------------------
 * This program is due to Assignment_8 in course "FH CS 001 ML F13".
 * this program basically resembles the online dating system.
 * Created By: Muhammad Rizky Wellyanto 
 * Student ID: 20144588
 *
 */

/*
 * Note: I instantiate the DateProfile object using arrays. in the main i
 *       also present the values that every object store. in this example i
 *       create a variety of 8 DateProfile object, testing the fit value even 
 *       if the participant is looking for the same gender.
 */

// import java.text for DecimalFormat class
import java.text.*;

/**
 * main class / client class
 * 
 * @author R12KY
 * 
 */
public class Foothill
{
   
   // main method
   public static void main(String[] args)
   {
      int totalParticipant;
      
      // create an array and set the initial datas of the applicants
      String name[] =
      { "Rizky", "Aragorn", "Arwen", "Faramir", "Eowyn", "Johnny Depp", 
            "A Gay Person", "Taylor Swift" };
      // note: "A Gay Person" is just for test sake, no discrimination intended
      
      char gender[] =
      { 'M', 'M', 'F', 'M', 'F', 'M', 'M', 'F' };
      char searchGender[] =
      { 'F', 'F', 'M', 'F', 'M', 'F', 'M', 'M' };
      double romance[] =
      { 10.0, 6.93456, 7.3465, 8.9375, 7.3725, 0, 0, 10 };
      double finance[] =
      { 0.0, 3.9872, 3.7543, 7.4372, 8.9382, 0, 0, 0 };
      
      // create an applicant array
      totalParticipant = name.length;
      DateProfile applicant[] = new DateProfile[totalParticipant];
      
      // create a formatter object
      DecimalFormat formatter = new DecimalFormat("#0.00");
      
      // title, showing the data of the applicants
      createLine(80, "=");
      System.out.println("APPLICANTS: ");
      createLine(80, "=");
      
      // assign the data to the arrays
      for (int counter = 0; counter < applicant.length; counter += 1)
      {
         applicant[counter] = new DateProfile();
         applicant[counter].setName(name[counter]);
         applicant[counter].setGender(gender[counter]);
         applicant[counter].setSearchGender(searchGender[counter]);
         applicant[counter].setRomance(romance[counter]);
         applicant[counter].setFinance(finance[counter]);
         
         // show the data, actually meant for checking / error testing only
         System.out.println(applicant[counter].toString());
         
         createLine(80, "-");
      }
      
      // title, mathcing the fit value one applicant to another
      createLine(80, "=");
      System.out.println("MATCHING THE APPLICANTS: ");
      createLine(80, "=");
      
      // show the value of the fit value of each applicant
      for (int counter = 0; counter < applicant.length; counter += 1)
      {
         System.out.println(applicant[counter].getName()
               + "'s Matching Percentage <3 : ");
         
         for (int counter2 = 0; counter2 < applicant.length; counter2 += 1)
         {
            System.out.print("-> " + applicant[counter2].getName() + ": ");
            System.out.println(formatter.format(applicant[counter]
                  .fitValue(applicant[counter2]) * 100) + "%");
         }
         
         createLine(80, "-");
      }
      
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
 * 
 * @author R12KY
 * 
 */
//class DateProfile
{
   // declare constants
   private static final char MALE = 'M';
   private static final char FEMALE = 'F';
   private static final double MIN_DOUBLE = 0.0;
   private static final double MAX_DOUBLE = 10.0;
   private static final int MIN_STR_LEN = 0;
   private static final int MAX_STR_LEN = 200;
   
   // declare default variables
   private static final String DEFAULT_STRING = "unnamed";
   private static final char DEFAULT_CHAR = '?';
   private static final double DEFAULT_DOUBLE = 0.0;
   
   // declare member data
   private char gender = DEFAULT_CHAR;
   private char searchGender = DEFAULT_CHAR;
   private double romance = DEFAULT_DOUBLE;
   private double finance = DEFAULT_DOUBLE;
   private String name = DEFAULT_STRING;
   
   /**
    * default constructor
    */
   DateProfile()
   {
      gender = DEFAULT_CHAR;
      searchGender = DEFAULT_CHAR;
      romance = DEFAULT_DOUBLE;
      finance = DEFAULT_DOUBLE;
      name = DEFAULT_STRING;
   }
   
   /**
    * constructor
    * 
    * @param gender
    * @param searchGender
    * @param romance
    * @param finance
    * @param name
    */
   DateProfile(char gender, char searchGender, double romance, double finance,
         String name)
   {
      setGender(gender);
      setSearchGender(searchGender);
      setRomance(romance);
      setFinance(finance);
      setName(name);
   }
   
   // mutator methods
   /**
    * this method will try to set the value of gender member data. if the input
    * is in range, set the gender and return true. if its not, return false
    * 
    * @param gender
    * @return
    */
   public boolean setGender(char gender)
   {
      boolean success;
      
      if (gender == MALE || gender == FEMALE)
      {
         this.gender = gender;
         success = true;
      }
      else
      {
         success = false;
      }
      
      return success;
   }
   
   /**
    * this method will try to set the value of searchGender member data. if the
    * input is in range, set the searchGender and return true. if its not,
    * return false
    * 
    * @param searchGender
    * @return
    */
   public boolean setSearchGender(char searchGender)
   {
      boolean success;
      
      if (searchGender == MALE || searchGender == FEMALE)
      {
         this.searchGender = searchGender;
         success = true;
      }
      else
      {
         success = false;
      }
      
      return success;
   }
   
   /**
    * this method will try to set the value of romance member data. if the input
    * is in range, set the romance and return true. if its not, return false
    * 
    * @param romance
    * @return
    */
   public boolean setRomance(double romance)
   {
      boolean success;
      
      if (romance >= MIN_DOUBLE && romance <= MAX_DOUBLE)
      {
         this.romance = romance;
         success = true;
      }
      else
      {
         success = false;
      }
      
      return success;
   }
   
   /**
    * this method will try to set the value of finance member data. if the input
    * is in range, set the finance and return true. if its not, return false
    * 
    * @param finance
    * @return
    */
   public boolean setFinance(double finance)
   {
      boolean success;
      
      if (finance >= MIN_DOUBLE && finance <= MAX_DOUBLE)
      {
         this.finance = finance;
         success = true;
      }
      else
      {
         success = false;
      }
      
      return success;
   }
   
   /**
    * this method will try to set the value of name member data. if the input is
    * in range, set the name and return true. if its not, return false
    * 
    * @param name
    * @return
    */
   public boolean setName(String name)
   {
      boolean success;
      
      if (name.length() >= MIN_STR_LEN && name.length() <= MAX_STR_LEN)
      {
         this.name = name;
         success = true;
      }
      else
      {
         success = false;
      }
      
      return success;
   }
   
   // accessor methods
   /**
    * return values stored in gender field
    * 
    * @return
    */
   public char getGender()
   {
      return gender;
   }
   
   /**
    * return values stored in searchGender field
    * 
    * @return
    */
   public char getSearchGender()
   {
      return searchGender;
   }
   
   /**
    * return values stored in romance field
    * 
    * @return
    */
   public double getRomance()
   {
      return romance;
   }
   
   /**
    * return values stored in finance field
    * 
    * @return
    */
   public double getFinance()
   {
      return finance;
   }
   
   /**
    * return values stored in name field
    * 
    * @return
    */
   public String getName()
   {
      return name;
   }
   
   // comparative methods
   /**
    * compare the gender of two DateProfile object
    * 
    * @param partner
    * @return
    */
   private double determineGenderFit(DateProfile partner)
   {
      double output = 0;
      
      if (this.searchGender == partner.gender)
      {
         output = 1;
      }
      
      return output;
   }
   
   /**
    * compare the romance of two DateProfile object
    * 
    * @param partner
    * @return
    */
   private double determineRomanceFit(DateProfile partner)
   {
      double output;
      
      output = 1 - Math.abs(((romance - partner.romance) / 10.0));
      
      return output;
   }
   
   /**
    * compare the finance of two DateProfile object
    * 
    * @param partner
    * @return
    */
   private double determineFinanceFit(DateProfile partner)
   {
      double output;
      
      output = 1 - Math.abs(((finance - partner.finance) / 10.0));
      
      return output;
   }
   
   /**
    * calculate the "matchness" / "chemistry" of both DateProfile object
    * 
    * @param partner
    * @return
    */
   public double fitValue(DateProfile partner)
   {
      double output = 0.0;
      
      if (this != partner)
      {
         output = determineGenderFit(partner) * determineRomanceFit(partner)
               * determineFinanceFit(partner);
      }
      
      return output;
   }
   
   // to string method
   /**
    * built-in toString method that will return string form of the data
    */
   public String toString()
   {
      String output;
      
      output = "Name: " + getName() + "\nGender: " + getGender()
            + "\nInterested In: " + getSearchGender() + "\nRomance: "
            + getRomance() + "\nFinance: " + getFinance();
      
      return output;
   }
   
}

/*----------------------------------Sample Run----------------------------------

================================================================================
APPLICANTS: 
================================================================================
Name: Rizky
Gender: M
Interested In: F
Romance: 10.0
Finance: 0.0
--------------------------------------------------------------------------------
Name: Aragorn
Gender: M
Interested In: F
Romance: 6.93456
Finance: 3.9872
--------------------------------------------------------------------------------
Name: Arwen
Gender: F
Interested In: M
Romance: 7.3465
Finance: 3.7543
--------------------------------------------------------------------------------
Name: Faramir
Gender: M
Interested In: F
Romance: 8.9375
Finance: 7.4372
--------------------------------------------------------------------------------
Name: Eowyn
Gender: F
Interested In: M
Romance: 7.3725
Finance: 8.9382
--------------------------------------------------------------------------------
Name: Johnny Depp
Gender: M
Interested In: F
Romance: 0.0
Finance: 0.0
--------------------------------------------------------------------------------
Name: A Gay Person
Gender: M
Interested In: M
Romance: 0.0
Finance: 0.0
--------------------------------------------------------------------------------
Name: Taylor Swift
Gender: F
Interested In: M
Romance: 10.0
Finance: 0.0
--------------------------------------------------------------------------------
================================================================================
MATCHING THE APPLICANTS: 
================================================================================
Rizky's Matching Percentage <3 : 
-> Rizky: 0.00%
-> Aragorn: 0.00%
-> Arwen: 45.88%
-> Faramir: 0.00%
-> Eowyn: 7.83%
-> Johnny Depp: 0.00%
-> A Gay Person: 0.00%
-> Taylor Swift: 100.00%
--------------------------------------------------------------------------------
Aragorn's Matching Percentage <3 : 
-> Rizky: 0.00%
-> Aragorn: 0.00%
-> Arwen: 93.65%
-> Faramir: 0.00%
-> Eowyn: 48.28%
-> Johnny Depp: 0.00%
-> A Gay Person: 0.00%
-> Taylor Swift: 41.70%
--------------------------------------------------------------------------------
Arwen's Matching Percentage <3 : 
-> Rizky: 45.88%
-> Aragorn: 93.65%
-> Arwen: 0.00%
-> Faramir: 53.12%
-> Eowyn: 0.00%
-> Johnny Depp: 16.57%
-> A Gay Person: 16.57%
-> Taylor Swift: 0.00%
--------------------------------------------------------------------------------
Faramir's Matching Percentage <3 : 
-> Rizky: 0.00%
-> Aragorn: 0.00%
-> Arwen: 53.12%
-> Faramir: 0.00%
-> Eowyn: 71.69%
-> Johnny Depp: 0.00%
-> A Gay Person: 0.00%
-> Taylor Swift: 22.91%
--------------------------------------------------------------------------------
Eowyn's Matching Percentage <3 : 
-> Rizky: 7.83%
-> Aragorn: 48.28%
-> Arwen: 0.00%
-> Faramir: 71.69%
-> Eowyn: 0.00%
-> Johnny Depp: 2.79%
-> A Gay Person: 2.79%
-> Taylor Swift: 0.00%
--------------------------------------------------------------------------------
Johnny Depp's Matching Percentage <3 : 
-> Rizky: 0.00%
-> Aragorn: 0.00%
-> Arwen: 16.57%
-> Faramir: 0.00%
-> Eowyn: 2.79%
-> Johnny Depp: 0.00%
-> A Gay Person: 0.00%
-> Taylor Swift: 0.00%
--------------------------------------------------------------------------------
A Gay Person's Matching Percentage <3 : 
-> Rizky: 0.00%
-> Aragorn: 18.43%
-> Arwen: 0.00%
-> Faramir: 2.72%
-> Eowyn: 0.00%
-> Johnny Depp: 100.00%
-> A Gay Person: 0.00%
-> Taylor Swift: 0.00%
--------------------------------------------------------------------------------
Taylor Swift's Matching Percentage <3 : 
-> Rizky: 100.00%
-> Aragorn: 41.70%
-> Arwen: 0.00%
-> Faramir: 22.91%
-> Eowyn: 0.00%
-> Johnny Depp: 0.00%
-> A Gay Person: 0.00%
-> Taylor Swift: 0.00%
--------------------------------------------------------------------------------

------------------------------------------------------------------------------*/
