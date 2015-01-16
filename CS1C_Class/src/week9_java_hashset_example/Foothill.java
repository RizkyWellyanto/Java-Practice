package week9_java_hashset_example;

import java.util.*;

//Example of an Employee class ------------------------------------
class Employee
{
 public static final int MAX_LEN = 50;

 protected String name;
 protected int ss;

 public Employee( String name , int ss )
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
};

//------------------------------------------------------
public class Foothill
{
 // -------  main --------------
 public static void main(String[] args) throws Exception
 {
    HashSet<Employee> company = new HashSet<Employee>();
    Iterator<Employee> iter;
    
    Employee
       e1 = new Employee("mike", 35), 
       e2 = new Employee("harvey", 5), 
       e3 = new Employee("don", 2), emp;

    System.out.println( "---------- The 'loose' objects ------ \n" 
       + e1.toString() + " " + e2.toString() + " " + e3.toString() );

    // insert them into the set
    company.add(e1);  company.add(e2);  company.add(e3);

    System.out.println( "---------- The contents of the Set ------ "); 
    for ( iter = company.iterator(); iter.hasNext(); )
    {
       emp = iter.next();
       System.out.println( emp.toString() );
    }

    // create a "mike" with a new ss# and try to insert
    company.add( new Employee("mike", 99) );  
    
    System.out.println( "---- After trying to insert 'mike/99' ----- " );
    for ( iter = company.iterator(); iter.hasNext(); )
    {
       emp = iter.next();
       System.out.println( emp.toString() );
    }
 }
}