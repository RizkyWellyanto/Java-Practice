
public class Main
{
   public static void main(String[] args)
   {
      Clock clockObject = new Clock();
      System.out.println(clockObject.toMilitary());
      
      clockObject.setTime(12, 34, 56);
      System.out.println(clockObject.toMilitary());
   }
}
