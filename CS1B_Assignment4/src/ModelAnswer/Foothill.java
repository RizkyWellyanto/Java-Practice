package ModelAnswer;
// CS 1B
// Assignment #4 - Instructor Solution

public class Foothill
{
   public static void main(String[] args)
   {
      Client cl1 = new Client("mike", 10000, 9.5), cl2 = new Client("irwin",
            20000, 10.5), cl3 = new Client();
      Writer wr1 = new Writer("joe writer", 22222, 9.7, true, false, true,
            "co-executive producer"), wr2 = new Writer("kim writer", 44444, 10,
            false, true, true, "story editor"), wr3 = new Writer();
      Actor ac1 = new Actor("sally actor", 12345, 9.1, 'F', 21), ac2 = new Actor(
            "jerry actor", 45721, 10, 'M', 15), ac3 = new Actor();
      
      // test the Client classes
      System.out.println(cl1.toString());
      System.out.println(cl2.toString());
      System.out.println(cl3.toString());
      System.out.println(wr1.toString());
      System.out.println(wr2.toString());
      System.out.println(wr3.toString());
      System.out.println(ac1.toString());
      System.out.println(ac2.toString());
      System.out.println(ac3.toString());
      
      // now the Agent class
      Agent ag1 = new Agent("Susan Bimell"), ag2 = new Agent("Jeff Benson");
      
      ag1.addClient(cl1);
      ag1.addClient(wr1);
      ag1.addClient(ac1);
      
      ag2.addClient(cl2);
      ag2.addClient(wr2);
      ag2.addClient(ac2);
      ag2.addClient(ac3);
      ag2.addClient(wr3);
      ag2.removeClient(ac2);
      
      System.out.println();
      
      System.out.println(ag1.getName() + " has the following clients:");
      System.out.println(ag1.toStringClientsShort());
      System.out.println(ag1.getName()
            + " is making the following off of his/her clients:");
      System.out.println("$" + ag1.getIncome());
      System.out.println();
      
      System.out.println(ag2.getName() + " has the following clients:");
      System.out.println(ag1.toStringClientsShort());
      System.out.println(ag2.getName()
            + " is making the following off of his/her clients:");
      System.out.println("$" + ag2.getIncome());
      System.out.println();
   }
}

// class Client ---------------------------------------------------
class Client
{
   private String name;
   private long incomeThisYear;
   private double percentCut;
   
   // int static consts can be initialized in-line like so:
   static final long MIN_INCOME_VAL = 0;
   static final long MAX_INCOME_VAL = 100000000000L;
   static final int MIN_NAME_LEN = 3;
   static final int MAX_NAME_LEN = 60;
   static final double MIN_CUT = 0.;
   static final double MAX_CUT = 99.99;
   
   // constructors
   public Client()
   {
      this("undefined client", 0, 10.0);
   }
   
   public Client(String name, long incomeThisYear, double percentCut)
   {
      if (!setIncome(incomeThisYear))
         setIncome(0);
      if (!setCut(percentCut))
         setCut(10.0);
      if (!setName(name))
         setName("client undefined");
   }
   
   // mutators
   public boolean setName(String nm)
   {
      if (!validName(nm))
         return false;
      name = nm;
      return true;
   }
   
   public boolean setIncome(long incomeThisYear)
   {
      if (!validIncome(incomeThisYear))
         return false;
      this.incomeThisYear = incomeThisYear;
      return true;
   }
   
   public boolean setCut(double percentCut)
   {
      if (!validCut(percentCut))
         return false;
      this.percentCut = percentCut;
      return true;
   }
   
   // helpers
   private static boolean validIncome(long n)
   {
      if (n < MIN_INCOME_VAL || n > MAX_INCOME_VAL)
         return false;
      return true;
   }
   
   private static boolean validCut(double cut)
   {
      // we will take percentages as percentages e.g. 10 or 7.5
      if (cut < MIN_CUT || cut >= MAX_CUT)
         return false;
      return true;
   }
   
   private static boolean validName(String name)
   {
      if (name.length() < MIN_NAME_LEN || name.length() > MAX_NAME_LEN)
         return false;
      return true;
   }
   
   // accessors (ok to use brief style in these
   public double getCut()
   {
      return percentCut;
   }
   
   public long getIncome()
   {
      return incomeThisYear;
   }
   
   public String getName()
   {
      return name;
   }
   
   // others
   public String toString()
   {
      String retVal;
      
      retVal = "Client Name: " + name + "\n" + "   income: $" + incomeThisYear
            + "\n" + "   percent cut: " + percentCut + "\n";
      
      return retVal;
   }
}

// class Writer ---------------------------------------------------
class Writer extends Client
{
   private boolean technical;
   private boolean government;
   private boolean international;
   private String rank;
   
   public static final String validRanks[] =
      { "staff writer", "story editor", "co-producer", "producer",
            "co-executive producer", "executive producer" };
   
   // mutators
   public Writer()
   {
      this("undefined writer", 0, 10.0, false, false, false, validRanks[0]);
   }
   
   public Writer(String name, long incomeThisYear, double percentCut,
         boolean technical, boolean government, boolean international,
         String rank)
   {
      // We could use super.setAll() or chain (we chained):
      super(name, incomeThisYear, percentCut);
      setTech(technical);
      setGovt(government);
      setInter(international);
      if (!setRank(rank))
         setRank(validRanks[0]);
   }
   
   // mutators
   public void setTech(boolean technical)
   {
      this.technical = technical;
   }
   
   public void setGovt(boolean government)
   {
      this.government = government;
   }
   
   public void setInter(boolean international)
   {
      this.international = international;
   }
   
   public boolean setRank(String rank)
   {
      if (!validRank(rank))
         return false;
      this.rank = rank;
      return true;
   }
   
   private static boolean validRank(String rank)
   {
      for (int k = 0; k < validRanks.length; k++)
         if (rank == validRanks[k])
            return true;
      return false;
   }
   
   // others
   public String toString()
   {
      String retVal;
      
      retVal = super.toString()         // chain
            + "   Writer Details:"
            + "\n      tech: "
            + (technical ? "yes" : "no")
            + "\n      govt: "
            + (government ? "yes" : "no")
            + "\n      intl: "
            + (international ? "yes" : "no") + "\n      rank: " + rank + "\n\n";
      
      return retVal;
   }
}

// class Actor ---------------------------------------------------
class Actor extends Client
{
   private char gender;
   private int age;
   public static final int MIN_AGE = 0;
   public static final int MAX_AGE = 130; // sanity check
   
   // constructors
   public Actor()
   {
      this("undefined actor", 0, 10.0, 'F', 30);
   }
   
   public Actor(String name, long incomeThisYear, double percentCut,
         char gender, int age)
   {
      // chain to base class constructor
      super(name, incomeThisYear, percentCut);
      
      // for derived member init
      if (!setGender(gender))
         setGender('F');
      if (!setAge(age))
         setAge(30);
   }
   
   public boolean setGender(char gender)
   {
      if (!validGender(gender))
         return false;
      this.gender = gender;
      return true;
   }
   
   public boolean setAge(int age)
   {
      if (!validAge(age))
         return false;
      this.age = age;
      return true;
   }
   
   // accessors
   char getGender()
   {
      return gender;
   }
   
   int getAge()
   {
      return age;
   }
   
   // others
   public String toString()
   {
      String retVal;
      
      retVal = super.toString()         // chain
            + "   Actor Details: " + "\n      gender: "
            + gender
            + "\n      age: " + age + "\n\n";
      
      return retVal;
   }
   
   // helpers
   private static boolean validGender(char gender)
   {
      char theChar = Character.toLowerCase(gender);
      if (theChar != 'm' && theChar != 'f')
         return false;
      return true;
   }
   
   private static boolean validAge(int age)
   {
      if (age < MIN_AGE || age > MAX_AGE)
         return false;
      return true;
   }
}

// class Agent ---------------------------------------------------
class Agent
{
   public static final int MAXClientS = 10;
   public static final int MIN_NAME_LEN = 3;
   public static final int MAX_NAME_LEN = 60;
   
   private String name;
   private Client[] myClients;
   private int numClients;
   
   // constructors
   public Agent()
   {
      this("undefined agent");   // chain
   }
   
   public Agent(String name)
   {
      // initialize client list - don't allocate individual objects
      myClients = new Client[MAXClientS];
      numClients = 0;
      if (!setName(name))
         setName("undefined agent");
   }
   
   // mutators
   public boolean setName(String name)
   {
      if (!validName(name))
         return false;
      this.name = name;
      return true;
   }
   
   public boolean addClient(Client client)
   {
      int k;
      
      if (numClients + 1 >= MAXClientS || client == null)
         return false;
      
      // now test for duplicates:
      for (k = 0; k < numClients; k++)
      {
         if (myClients[k] == client)
            return false;
      }
      
      // We must be careful. We may prefer another method
      // which clones the argument passed in. Here we simply point
      // to it and realize that if the main/client changes it, it will
      // be changed in our list as well. See constructor for same assumption
      myClients[numClients++] = client;
      return true;
   }
   
   public boolean removeClient(Client client)
   {
      int k, toDeleteK;
      
      if (client == null)
         return false;
      
      // find client
      for (k = 0; k < numClients; k++)
         if (client == myClients[k])
            break;
      toDeleteK = k;
      
      // if we went all the way through the list, not found
      if (toDeleteK == MAXClientS)
         return false;
      
      // client found -- squeeze him out of list
      numClients--;
      for (k = toDeleteK + 1; k < numClients; k++)
         myClients[k - 1] = myClients[k];
      return true;
   }
   
   // accessors
   public String getName()
   {
      return name;
   }
   
   // others
   public double getIncome()
   {
      long income = 0;
      
      for (int k = 0; k < numClients; k++)
         income += myClients[k].getIncome() * myClients[k].getCut() * .01;
      return income;
   }
   
   public String toStringClientsLong()
   {
      String retVal;
      
      retVal = " CLIENT LIST ------------------ \n";
      
      for (int k = 0; k < numClients; k++)
         retVal += myClients[k].toString();
      retVal += "--------------------------------\n";
      
      return retVal;
   }
   
   public String toStringClientsShort()
   {
      String retVal;
      
      retVal = " CLIENT LIST (names only)-------- \n";
      
      for (int k = 0; k < numClients; k++)
         retVal = retVal + myClients[k].getName() + "\n";
      retVal += "--------------------------------\n";
      
      return retVal;
   }
   
   // helpers
   private static boolean validName(String name)
   {
      if (name.length() < MIN_NAME_LEN || name.length() > MAX_NAME_LEN)
         return false;
      return true;
   }
}

/*
 * ------------------ Output of Above ------------------------------
 * Client Name: mike
 * income: $10000
 * percent cut: 9.5
 * 
 * Client Name: irwin
 * income: $20000
 * percent cut: 10.5
 * 
 * Client Name: undefined client
 * income: $0
 * percent cut: 10.0
 * 
 * Client Name: joe writer
 * income: $22222
 * percent cut: 9.7
 * Writer Details:
 * tech: yes
 * govt: no
 * intl: yes
 * rank: co-executive producer
 * 
 * 
 * Client Name: kim writer
 * income: $44444
 * percent cut: 10.0
 * Writer Details:
 * tech: no
 * govt: yes
 * intl: yes
 * rank: story editor
 * 
 * 
 * Client Name: undefined writer
 * income: $0
 * percent cut: 10.0
 * Writer Details:
 * tech: no
 * govt: no
 * intl: no
 * rank: staff writer
 * 
 * 
 * Client Name: sally actor
 * income: $12345
 * percent cut: 9.1
 * Actor Details:
 * gender: F
 * age: 21
 * 
 * 
 * Client Name: jerry actor
 * income: $45721
 * percent cut: 10.0
 * Actor Details:
 * gender: M
 * age: 15
 * 
 * 
 * Client Name: undefined actor
 * income: $0
 * percent cut: 10.0
 * Actor Details:
 * gender: F
 * age: 30
 * 
 * 
 * 
 * Susan Bimell has the following clients:
 * CLIENT LIST (names only)--------
 * mike
 * joe writer
 * sally actor
 * --------------------------------
 * 
 * Susan Bimell is making the following off of his/her clients:
 * $4228.0
 * 
 * Jeff Benson has the following clients:
 * CLIENT LIST (names only)--------
 * mike
 * joe writer
 * sally actor
 * --------------------------------
 * 
 * Jeff Benson is making the following off of his/her clients:
 * $6544.0
 * 
 * 
 * -------------------------------------------------------------------
 */
