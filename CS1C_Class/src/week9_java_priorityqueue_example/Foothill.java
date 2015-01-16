package week9_java_priorityqueue_example;

import cs_1c.*;
import java.util.*;

//Example of an Employee class ------------------------------------
class Employee implements Comparable<Employee>
{
   public static final int MAX_LEN = 50;

   protected String name;
   protected int ss;

   public Employee( String name , int ss)
   {
      this();
      setName(name);
      setSS(ss);
   }

   public Employee()
   {
      name = "undefined";
      ss = 0;
   }
   
   String getName() { return name; }
   int getSS() { return ss; }

   boolean setName( String name )
   {
      if (name == null)
         return false;
      if (name.length() > MAX_LEN)
         return false;
      this.name = name;
      return true;
   }
   
   boolean setSS( int ss )
   {
      if (ss < 0 || ss > 999999999 )
         return false;
      this.ss = ss;
      return true;
   }

   public String toString()
   {
      return name + " (" + ss + ")";
   }
   
   public boolean equals( Object rhs ) 
   {
      Employee other = (Employee)rhs;
      // return ss == other.ss;
      return name.equals(other.name);
   }

   public int hashCode()
   { 
      // return ss;
      return name.hashCode();
   }
   
   public int compareTo(Employee rhs)
   {
      return name.compareTo(rhs.name);
      // return ss - rhs.ss;
   }
};

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      PriorityQueue<Employee> company = new PriorityQueue<Employee>();
      
      Employee emp,
         e1 = new Employee("mike", 111111111), 
         e2 = new Employee("harvey", 333333333), 
         e3 = new Employee("don", 222222222);

      System.out.println( "---------- The 'loose' objects ------ \n" 
         + e1.toString() + " " + e2.toString() + " " + e3.toString() );

      // insert them into the set
      company.add(e1);  company.add(e2);  company.add(e3);

      company.add( new Employee("harvey", 123456789 ));  // duplicates ok

      System.out.println( "\n---------- Now, from the heap ------ ");
      while ( !company.isEmpty() )
      {
         emp = company.remove();
         System.out.println( emp.name + " " + emp.ss );
      }
   }
}