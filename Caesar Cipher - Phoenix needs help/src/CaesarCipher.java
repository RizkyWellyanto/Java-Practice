/**
 * File: CaesarCipher.java
 * -----------------------------------------------------------------------------
 * this program is the basic algorithm of the caesar cipher. it use a shift key
 * and encrypt the plain text. the key could also be used to decrypt a caesar
 * cipher.
 * 
 */

/*
 * Note: 
 * 
 * yo, phoenix, this is the algorithm i can think of dude.
 * 
 * -Rizky
 *
 */

/**
 * just the main class
 * @author R12KY
 *
 */
public class CaesarCipher
{
   // main method
   public static void main(String[] args)
   {
      // any phone number, just put it here.
      String phoneNumber = "0123456789";
      
      // try any number you want to encrypt here!!!
      
      // encrypt the phone number
      phoneNumber = encrypt(phoneNumber, 3);
      System.out.println(phoneNumber);
      
      // it can also be used to decrypt the cipher, just use negative number
      phoneNumber = encrypt(phoneNumber, -3);
      System.out.println(phoneNumber);
   }
   
   /**
    * the encrypt algorithm
    * plain text as the text you want to encrypt
    * shift is the "key" or the number of the value is shifted
    * @param plainText
    * @param shift
    * @return
    */
   public static String encrypt(String plainText, int shift)
   {
      // set an output string
      String output = "";
      
      // make the shift could be used to decrypt the cipher
      if(shift < 0)
      {
         shift = 10 - ( -shift % 10);
      }
      
      // loop for every char of the plainText
      for(int counter = 0; counter < plainText.length(); counter += 1)
      {
         // create a char variable and take every piece of the plainText
         char ch = plainText.charAt(counter);
         
         // main calculation
         ch = (char) ( '0' + ((ch - '0' + shift) % 10));
         
         // append the number to construct the new output
         output += Character.toString(ch);
      }
      
      // return the ciphered text
      return output;
   }
}
