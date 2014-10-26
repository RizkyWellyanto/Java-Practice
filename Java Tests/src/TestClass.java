public class TestClass
{
   public static void main(String[] args)
   {
      boolean[] rules = {true,true,true,true,true,true,true,true};
      StringBuilder newGen = new StringBuilder();
      String extremeBit = " ";
      String thisGen = "*";
      int someInt;
      
      StringBuffer tempGen = new StringBuffer(thisGen);
      
      tempGen.insert(0, extremeBit);
      tempGen.insert(0, extremeBit);
      
      tempGen.append(extremeBit);
      tempGen.append(extremeBit);
            
      for (int i = 0; i < thisGen.length() + 2; i++)
      {
         String str; // string to hold triple bits in the form of binary
         int index;  // int to hold the decimal value of the binary triple bits
         
         // take three substrings from tempGen. convert it to the form of binary
         str = tempGen.substring(i, i + 3).replace(' ', '0')
               .replace('*', '1').toString();
                 
         // convert binary string into decimal integer
         index = Integer.parseInt(str, 2);
         
         // look for the value in the rules array. append it to the newGen
         newGen.append(rules[index] ? "1" : "0");
         
      }
      
      System.out.println(newGen.toString());

   }
   
   private static boolean[] toBinary(int number, int base) {
      final boolean[] ret = new boolean[base];
      for (int i = 0; i < base; i++) {
          ret[base - 1 - i] = (1 << i & number) != 0;
      }
      return ret;
  }

}
