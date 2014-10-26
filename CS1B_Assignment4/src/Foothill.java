/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 4 in CS 1B Jesse Cecil Winter 2014
 * this program is called Talent. this program is about to test the
 * understanding and implementation of Inheritance in Java.
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

// import necessary classes
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Main Class of the Program
 * 
 * @author MuhammadRizky
 * 
 */
public class Foothill
{
   /**
    * Main Method of the program
    * 
    * @param args
    */
   public static void main(String[] args)
   {
      // create necessary variables
      
      // create necessary objects
      Actor LeonardoDicaprio = new Actor("Leonardo", 1000000, 15, 'M', 30);
      Actor TomHanks = new Actor("Tom", 250000, 5, 'M', 40);
      Actor ScarletJohansson = new Actor("Scarlet", 2000000, 25, 'F', 25);
      
      Writer GeorgeOrwell = new Writer("George", 5000, 10, true, false, true,
            "co-producer");
      Writer JRRTolkiens = new Writer("Tolkiens", 10000, 1, true, true, true,
            "producer");
      Writer ChristopherPaolini = new Writer("Chris", 2000, 5, false, true,
            false, "executive producer");
      
      Agent WellyantoAgency = new Agent("Wellyanto Agency");
      Agent BigBucksAgency = new Agent("Big Bucks Agency");
      
      // title
      createLine(80, "=");
      System.out.println("Actors and Writers: ");
      createLine(80, "=");
      
      // show the objects
      System.out.println(LeonardoDicaprio.toString());
      createLine(80, "=");
      System.out.println(TomHanks.toString());
      createLine(80, "=");
      System.out.println(ScarletJohansson.toString());
      createLine(80, "=");
      System.out.println(GeorgeOrwell.toString());
      createLine(80, "=");
      System.out.println(JRRTolkiens.toString());
      createLine(80, "=");
      System.out.println(ChristopherPaolini.toString());
      createLine(80, "=");
      
      // add the actors and writers to the agents
      System.out.println("Adding Clents to Agents: ");
      createLine(80, "=");
      System.out.println(WellyantoAgency.addClient(LeonardoDicaprio));
      System.out.println(WellyantoAgency.addClient(ScarletJohansson));
      System.out.println(WellyantoAgency.addClient(JRRTolkiens));
      
      System.out.println(BigBucksAgency.addClient(TomHanks));
      System.out.println(BigBucksAgency.addClient(GeorgeOrwell));
      System.out.println(BigBucksAgency.addClient(ChristopherPaolini));
      createLine(80, "=");
      
      // show the agencies data
      System.out.println("Wellyanto Agency Data (Short): ");
      createLine(80, "=");
      System.out.println(WellyantoAgency.toStringClientsShort());
      createLine(80, "=");
      
      System.out.println("Big Bucks Agency Data (Short): ");
      createLine(80, "=");
      System.out.println(BigBucksAgency.toStringClientsShort());
      createLine(80, "=");
      
      System.out.println("Wellyanto Agency Data (Long): ");
      createLine(80, "=");
      System.out.println(WellyantoAgency.toStringClientsLong());
      createLine(80, "=");
      
      System.out.println("Big Bucks Agency Data (Long): ");
      createLine(80, "=");
      System.out.println(BigBucksAgency.toStringClientsLong());
      createLine(80, "=");
      
      // show the agencies income
      System.out.println("Wellyanto Agency Income: ");
      createLine(80, "=");
      System.out.println("Income: $" + WellyantoAgency.getIncome());
      createLine(80, "=");
      
      System.out.println("Big Bucks Agency Income: ");
      createLine(80, "=");
      System.out.println("Income: $" + BigBucksAgency.getIncome());
      createLine(80, "=");
      
      // end the program
      System.out.println("Program Shutted Down.");
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
 * The Client Class
 * 
 * @author MuhammadRizky
 * 
 */
class Client
{
   // constants
   private static final int MIN_NAME_LENGTH = 3;
   private static final long MIN_INCOME = 0;
   private static final double MIN_PERCENT_CUT = 0;
   
   private static final int MAX_NAME_LENGTH = 60;
   private static final long MAX_INCOME = 1000000000;
   private static final double MAX_PERCENT_CUT = 99.99;
   
   private static final String DEF_NAME = "Name Error";  // default name
   private static final long DEF_INCOME = 0;             // default income
   private static final double DEF_PERCENT_CUT = 10;     // default percent cut
   
   // member data
   protected String name;
   protected long incomeThisYear;
   protected double percentCut;
   
   // constructors
   Client()
   {
      this(DEF_NAME, DEF_INCOME, DEF_PERCENT_CUT);
   }
   
   Client(String name, long income, double percentCut)
   {
      if (!setName(name))
         this.name = DEF_NAME;
      
      if (!setIncomeThisYear(income))
         this.incomeThisYear = DEF_INCOME;
      
      if (!setPercentCut(percentCut))
         this.percentCut = DEF_PERCENT_CUT;
   }
   
   // mutators
   /**
    * Method that sets the name member data. this method is helped by isValid
    * method
    * 
    * @param name as input name
    * @return true if succcess, false otherwise
    */
   private boolean setName(String name)
   {
      if (isValid(name))
      {
         this.name = name;
         return true;
      }
      
      return false;
   }
   
   /**
    * Method that sets the incomeThisYear member data. this method is helped
    * by isValid method
    * 
    * @param income as the yearly income input
    * @return true if succcess, false otherwise
    */
   private boolean setIncomeThisYear(long income)
   {
      if (isValid(income))
      {
         this.incomeThisYear = income;
         return true;
      }
      
      return false;
   }
   
   /**
    * Method that sets the percentCut member data. this method is helped
    * by isValid method
    * 
    * @param percentCut as the percentage input
    * @return true if succcess, false otherwise
    */
   private boolean setPercentCut(double percentCut)
   {
      if (isValid(percentCut))
      {
         this.percentCut = percentCut;
         return true;
      }
      
      return false;
   }
   
   // accessors
   /**
    * Name member data accessor method
    * 
    * @return name member data
    */
   public String getName()
   {
      return this.name;
   }
   
   /**
    * incomeThisYear member data accessor
    * 
    * @return incomeThisYear member data
    */
   public long getIncomeThisYear()
   {
      return this.incomeThisYear;
   }
   
   /**
    * percentCut member data accessor
    * 
    * @return percentCut member data
    */
   public double getPercentCut()
   {
      return this.percentCut;
   }
   
   // other methods
   /**
    * validate the passed String
    * 
    * @param name as the passed String
    * @return true if in range, false otherwise
    */
   private static boolean isValid(String name)
   {
      if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
         return false;
      
      return true;
   }
   
   /**
    * validate the passed long
    * 
    * @param income as the passed long
    * @return true if in range, false otherwise
    */
   private static boolean isValid(long income)
   {
      if (income < MIN_INCOME || income > MAX_INCOME)
         return false;
      
      return true;
   }
   
   /**
    * validate the passed double
    * 
    * @param percentCut as the passed double
    * @return true if in range, false otherwise
    */
   private static boolean isValid(double percentCut)
   {
      if (percentCut < MIN_PERCENT_CUT || percentCut > MAX_PERCENT_CUT)
         return false;
      
      return true;
   }
   
   // toString method
   /**
    * A stringize method that returns a String representation of the object
    */
   public String toString()
   {
      StringBuilder str = new StringBuilder();
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      str.append("[Name: " + this.name + "]\n");
      str.append("[Income This Year: $" + tidy.format(this.incomeThisYear)
            + "]\n");
      str.append("[Percent Cut: " + this.percentCut + "%]\n");
      
      return str.toString();
   }
   
}

/**
 * The Writer Class. Sub class of Client()
 * 
 * @author MuhammadRizky
 * 
 */
class Writer extends Client
{
   // constants
   private static final boolean DEF_TECH = false;     // default bools
   private static final boolean DEF_GOV = false;      // default bools
   private static final boolean DEF_INTER = false;    // default bools
   private static final String DEF_RANK = "No Rank";  // default rank
   
   private static final String[] RANKS =
      { "staff writer", "story editor", "co-producer", "producer",
            "co-executive producer", "executive producer" };
   
   // member datas
   private boolean technical;
   private boolean government;
   private boolean international;
   private String rank;
   
   // constructors
   Writer()
   {
      this.technical = DEF_TECH;
      this.government = DEF_GOV;
      this.international = DEF_INTER;
      this.rank = DEF_RANK;
   }
   
   Writer(String name, long income, double percentCut, boolean isTech,
         boolean isGov, boolean isInter, String rank)
   {
      super(name, income, percentCut);
      
      set(isTech, isGov, isInter, rank);
   }
   
   // set methods
   public void set(boolean isTech, boolean isGov, boolean isInter, String rank)
   {
      this.technical = isTech;
      this.government = isGov;
      this.international = isInter;
      
      if (!setRank(rank))
         this.rank = DEF_RANK;
   }
   
   private boolean setRank(String rank)
   {
      for (String availableRank : RANKS)
      {
         if (rank.equals(availableRank))
         {
            this.rank = availableRank;
            return true;
         }
      }
      
      return false;
   }
   
   // toString Methods
   /**
    * A stringize method that returns a String representation of the object.
    * Overrides the Client class's toString() method
    */
   public String toString()
   {
      StringBuilder str = new StringBuilder();
      
      str.append(super.toString());
      str.append("[Technical Background: " + this.technical + "]\n");
      str.append("[Governmental Background: " + this.government + "]\n");
      str.append("[International Background: " + this.international + "]\n");
      str.append("[Rank: " + this.rank + "]\n");
      
      return str.toString();
   }
}

/**
 * The Actor Class. sub class of Client()
 * 
 * @author MuhammadRizky
 * 
 */
class Actor extends Client
{
   // constants
   private static final int MIN_AGE = 0;
   private static final int MAX_AGE = 150;
   
   private static final char MALE = 'M';
   private static final char FEMALE = 'F';
   
   private static final char DEF_GEN = MALE; // default gender
   private static final int DEF_AGE = 21;    // default age
   
   // member datas
   private char gender;
   private int age;
   
   // constructors
   Actor()
   {
      super();
      
      this.gender = DEF_GEN;
      this.age = DEF_AGE;
   }
   
   Actor(String name, long income, double percentCut, char gender, int age)
   {
      super(name, income, percentCut);
      
      if (!setGender(gender))
         this.gender = DEF_GEN;
      
      if (!setAge(age))
         this.age = DEF_AGE;
   }
   
   // mutator methods
   /**
    * Set the gender member data
    * 
    * @param gender as the passed parameter
    * @return true is success, false otherwise
    */
   public boolean setGender(char gender)
   {
      if (isValid(gender))
      {
         this.gender = gender;
         return true;
      }
      
      return false;
   }
   
   /**
    * Set the age member data
    * 
    * @param age as the passed parameter
    * @return true is success, false otherwise
    */
   public boolean setAge(int age)
   {
      if (isValid(age))
      {
         this.age = age;
         return true;
      }
      
      return false;
   }
   
   // accessor methods
   /**
    * gender member data accessor
    * 
    * @return gender member data
    */
   public char getGender()
   {
      return this.gender;
   }
   
   /**
    * age member data accessor
    * 
    * @return age member data
    */
   public int getAge()
   {
      return this.age;
   }
   
   // other methods
   /**
    * this method checks whether the input is out of range or not
    * 
    * @param gender as the passed input
    * @return true if its in range, false otherwise
    */
   private boolean isValid(char gender)
   {
      if (gender != MALE || gender != FEMALE)
         return false;
      
      return true;
   }
   
   /**
    * this method checks whether the input is out of range or not
    * 
    * @param age as the passed input
    * @return true if its in range, false otherwise
    */
   private boolean isValid(int age)
   {
      if (age < MIN_AGE || age > MAX_AGE)
         return false;
      
      return true;
   }
   
   // toString Methods
   /**
    * A stringize method that returns a String representation of the object.
    * Overrides the Client class's toString() method
    */
   public String toString()
   {
      StringBuilder str = new StringBuilder();
      
      str.append(super.toString());
      str.append("[Gender: " + this.gender + "]\n");
      str.append("[Age: " + this.age + "]\n");
      
      return str.toString();
   }
}

/**
 * The Agent Class
 * 
 * @author MuhammadRizky
 * 
 */
class Agent
{
   // constants
   private static final int MIN_NAME_LENGTH = 3;
   private static final int MAX_NAME_LENGTH = 60;
   private static final int MAX_CLIENTS = 100;
   
   private static final String DEF_NAME = "Name Error";  // default name
   
   // member datas
   private String name;
   private Client[] myClients;
   private int numClients;
   
   // constructors
   Agent(String name)
   {
      if (!setName(name))
         setName(DEF_NAME);
      
      myClients = new Client[MAX_CLIENTS];
      numClients = 0;
   }
   
   // mutator methods
   /**
    * Method that sets the name member data. this method is helped by isValid
    * method
    * 
    * @param name as input name
    * @return true if succcess, false otherwise
    */
   public boolean setName(String name)
   {
      if (isValid(name))
      {
         this.name = name;
         return true;
      }
      
      return false;
   }
   
   // accessor methods
   /**
    * Name member data accessor method
    * 
    * @return name member data
    */
   public String getName()
   {
      return this.name;
   }
   
   // other methods
   /**
    * validate the passed String
    * 
    * @param name as the passed String
    * @return true if in range, false otherwise
    */
   private boolean isValid(String name)
   {
      if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
         return false;
      
      return true;
   }
   
   /**
    * this method add a client object to this object's client array
    * 
    * @param client as the passed client object
    * @return true if it's a success, false if the array is full
    */
   public boolean addClient(Client client)
   {
      try
      {
         myClients[numClients] = client;
      }
      catch (ArrayIndexOutOfBoundsException error)
      {
         return false;
      }
      
      numClients++;
      return true;
   }
   
   /**
    * this method remove a client object in myClients[] array. and then squeeze
    * the whole array
    * 
    * @param client as the intended client
    * @return true if its success, false if client doesnt exist in array / error
    */
   public boolean removeClient(Client client)
   {
      for (int i = 0; i < numClients; i++)
      {
         if (myClients[i] == client)
         {
            for (int j = i + 1; j < numClients; j++)
            {
               myClients[i] = myClients[j];
            }
            
            numClients--;
            return true;
         }
      }
      
      return false;
   }
   
   /**
    * this method calculate the sum of money the agent get and return the value
    * 
    * @return the sum of the Agent's Income
    */
   public double getIncome()
   {
      double sumMoney = 0;
      
      for (int i = 0; i < numClients; i++)
      {
         sumMoney += myClients[i].getIncomeThisYear()
               * myClients[i].getPercentCut() / 100;
      }
      
      return sumMoney;
   }
   
   // toString Methods
   /**
    * A stringize method that returns a String representation of the object
    * 
    * @return the short string representation of the myClients array[]
    */
   public String toStringClientsShort()
   {
      StringBuilder str = new StringBuilder();
      
      for (int i = 0; i < numClients; i++)
      {
         str.append("[Name: " + myClients[i].getName() + "]\n");
      }
      
      return str.toString();
   }
   
   /**
    * A stringize method that returns a String representation of the object
    * 
    * @return the long string representation of the myClients array[]
    */
   public String toStringClientsLong()
   {
      StringBuilder str = new StringBuilder();
      
      for (int i = 0; i < numClients; i++)
      {
         str.append(myClients[i].toString());
      }
      
      return str.toString();
   }
}

/*----------------------------------Sample Run----------------------------------

================================================================================
Actors and Writers: 
================================================================================
[Name: Leonardo]
[Income This Year: $1,000,000]
[Percent Cut: 15.0%]
[Gender: M]
[Age: 30]

================================================================================
[Name: Tom]
[Income This Year: $250,000]
[Percent Cut: 5.0%]
[Gender: M]
[Age: 40]

================================================================================
[Name: Scarlet]
[Income This Year: $2,000,000]
[Percent Cut: 25.0%]
[Gender: M]
[Age: 25]

================================================================================
[Name: George]
[Income This Year: $5,000]
[Percent Cut: 10.0%]
[Technical Background: true]
[Governmental Background: false]
[International Background: true]
[Rank: co-producer]

================================================================================
[Name: Tolkiens]
[Income This Year: $10,000]
[Percent Cut: 1.0%]
[Technical Background: true]
[Governmental Background: true]
[International Background: true]
[Rank: producer]

================================================================================
[Name: Chris]
[Income This Year: $2,000]
[Percent Cut: 5.0%]
[Technical Background: false]
[Governmental Background: true]
[International Background: false]
[Rank: executive producer]

================================================================================
Adding Clents to Agents: 
================================================================================
true
true
true
true
true
true
================================================================================
Wellyanto Agency Data (Short): 
================================================================================
[Name: Leonardo]
[Name: Scarlet]
[Name: Tolkiens]

================================================================================
Big Bucks Agency Data (Short): 
================================================================================
[Name: Tom]
[Name: George]
[Name: Chris]

================================================================================
Wellyanto Agency Data (Long): 
================================================================================
[Name: Leonardo]
[Income This Year: $1,000,000]
[Percent Cut: 15.0%]
[Gender: M]
[Age: 30]
[Name: Scarlet]
[Income This Year: $2,000,000]
[Percent Cut: 25.0%]
[Gender: M]
[Age: 25]
[Name: Tolkiens]
[Income This Year: $10,000]
[Percent Cut: 1.0%]
[Technical Background: true]
[Governmental Background: true]
[International Background: true]
[Rank: producer]

================================================================================
Big Bucks Agency Data (Long): 
================================================================================
[Name: Tom]
[Income This Year: $250,000]
[Percent Cut: 5.0%]
[Gender: M]
[Age: 40]
[Name: George]
[Income This Year: $5,000]
[Percent Cut: 10.0%]
[Technical Background: true]
[Governmental Background: false]
[International Background: true]
[Rank: co-producer]
[Name: Chris]
[Income This Year: $2,000]
[Percent Cut: 5.0%]
[Technical Background: false]
[Governmental Background: true]
[International Background: false]
[Rank: executive producer]

================================================================================
Wellyanto Agency Income: 
================================================================================
Income: $650100.0
================================================================================
Big Bucks Agency Income: 
================================================================================
Income: $13100.0
================================================================================
Program Shutted Down.


 ------------------------------------------------------------------------------*/
