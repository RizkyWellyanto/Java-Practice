package week9_hashmap_example;

import java.util.*;

//------------------------------------------------------
public class Foothill
{
 // -------  main --------------
 public static void main(String[] args) throws Exception
 {
    Scanner inputStream = new Scanner(System.in);
    Integer age;
    String searchName;

    HashMap<String, Integer> myFriends 
       = new HashMap<String, Integer>();
    
    myFriends.put("lisa", 49);
    myFriends.put("keith", 26);
    myFriends.put("keith", 27);  // should fail - "keith" already in table
    myFriends.put("adrian", 4);
    myFriends.put("jenna", 4);   // should succeed - 4 has no impact
         
    while (true)
    {
       System.out.print( "Enter a name (or q to quit): " );
       searchName = inputStream.nextLine();
       
       if (searchName.equals("q"))
          break;
       
       age = myFriends.get(searchName);

       if (age != null)
          System.out.println(searchName + " is " + age + " years old.");
       else
          System.out.println(searchName + " not in map.");
    }
 }
}