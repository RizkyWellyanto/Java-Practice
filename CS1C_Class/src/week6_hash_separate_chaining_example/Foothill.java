package week6_hash_separate_chaining_example;

import cs_1c.*;

//Example of an Employee class
class Employee
{
 public static final int MAX_LEN = 50;

 private String name;
 private int ss;

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
 public boolean equals( Employee rhs ) 
 {
    return ss == rhs.ss;
    // return name.equals(rhs.name);
 }

 public int hashCode()
 { 
    return ss;
    // return name.hashCode();
 }
};

//------------------------------------------------------
public class Foothill
{
 // -------  main --------------
 public static void main(String[] args) throws Exception
 {
    // first set of tests --------------------
    FHhashSC<Employee> hashTable = new FHhashSC<Employee>();
    
    Employee 
       e1 = new Employee("Jane Austin", 1), 
       e2 = new Employee("Rene Descartes", 2), 
       e3 = new Employee("John Locke", 3);

    if (  hashTable.insert(e1) )
       System.out.println( "inserted " + e1 );
    if (  hashTable.insert(e1) )
       System.out.println( "inserted " + e1 );
    if (  hashTable.insert(e2) )
       System.out.println( "inserted " + e2 );
    if (  hashTable.insert(e2) )
       System.out.println( "inserted " + e2 );
    if (  hashTable.insert(e3) )
       System.out.println( "inserted " + e3 );
    if (  hashTable.insert(e3) )
       System.out.println( "inserted " + e3 );

    System.out.println( hashTable.size() );

    if (  hashTable.contains(e3) )
       System.out.println( "contains " + e3 );

    if (  hashTable.remove(e1) )
       System.out.println( "removed " + e1 );
    if (  hashTable.remove(e1) )
       System.out.println( "removed " + e1 );
    if (  hashTable.remove(e2) )
       System.out.println( "removed " + e2 );
    if (  hashTable.remove(e2) )
       System.out.println( "removed " + e2 );
    if (  hashTable.remove(e3) )
       System.out.println( "removed " + e3 );
    if (  hashTable.remove(e3) )
       System.out.println( "removed " + e3 );
    System.out.println( hashTable.size() );
  
    if (  hashTable.contains(e3) )
       System.out.println( "contains " + e3 );

    // second set of tests --------------------

    FHhashSC<String> hashTable2 = new FHhashSC<String>();
    String substrate = 
       "asdlkfj asdoiBIUYVuwer slkdjLJfwoe89)B)(798rjMG0293lkJLJ42lk3j)(*";
    String[] strArray = new String[500];
    int k, limit;

    substrate = substrate + substrate;

    for (k = 0; k < substrate.length() - 6; k++)
       strArray[k] = substrate.substring(k, k + 5);
    limit = k;

    hashTable2.setMaxLambda(.5);
    for (k = 0; k < limit; k++)
       if ( hashTable2.insert(strArray[k]) )
          System.out.println( "inserted #" + k + " " + strArray[k] );
    System.out.println( "\n#strings generated: " + limit 
       + "\n#elements in ht: " + hashTable2.size() );
 }
}