package week1_generics_example;
public class Foothill
{
   public static void main(String[] args)
   {
      int x = 9;
      double some_dub = 33.1;

      displayInAsterisks(x);
      displayInAsterisks("hi mom");
      displayInAsterisks(some_dub);
    }
   
   static <E> void displayInAsterisks(E x)
   {
      System.out.println(" *** " + x + " ***");
   }
}
/*  ----------------- run ----------------

 *** 9 ***
 *** hi mom ***
 *** 33.1 ***
 
---------------------------------------- */