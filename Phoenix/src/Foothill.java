/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;
import javax.swing.JFileChooser;

/**
 * 
 * @author Haofei
 */
public class Foothill
{
   
   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) throws FileNotFoundException
   {
      
      JFileChooser open = new JFileChooser();
      if (open.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
      {
         java.io.File weblog = open.getSelectedFile();
         String[] store = new String[2990];
         int i = 0;
         Scanner input = new Scanner(weblog);
         while (input.hasNext())
         {
            
            String ipAddr = input.next();
            String exclude1 = input.next();
            String exclude2 = input.next();
            String check = input.next();
            while (!check.substring(check.length() - 1, check.length()).equals(
                  "]"))
            {
               check = check + " " + input.next();
            }
            String date = check;
            check = input.next();
            while (!check.substring(check.length() - 1, check.length()).equals(
                  "\""))
            {
               check = check + " " + input.next();
            }
            String pagereq = check;
            String http = input.next();
            String bytelgt = input.next();
            check = input.next();
            while (!check.substring(check.length() - 1, check.length()).equals(
                  "\""))
            {
               check = check + " " + input.next();
            }
            String refsite = check;
            check = input.next();
            while (!check.substring(check.length() - 1, check.length()).equals(
                  "\""))
            {
               check = check + input.next();
            }
            String reqsoft = check;
            
            String inputStore = "";
            inputStore += ipAddr + " " + exclude1 + " " + exclude2 + " " + date
                  + " " + pagereq + " " + http + " " + bytelgt + " " + refsite
                  + " " + reqsoft;
            
            store[i] = inputStore;
            i++;
                        

            
         }
         
         Arrays.sort(store);
         System.out.println("===========================================================================================================================================================");
        
         int j = 0;
         
         while(true)
         {
            
            
            System.out.println(store[j]);
            j++;
            
            if (j == 2000)
               break;
         }
         
         i = 0;
         while (i < 2990)
         {
            System.out.println(store[i]);
            i++;
         }
      }
      
      // TODO code application logic here
   }
}

class ReadFile
{
   
}

class split
{
   
   public void splitline() throws FileNotFoundException
   {
   }
}
