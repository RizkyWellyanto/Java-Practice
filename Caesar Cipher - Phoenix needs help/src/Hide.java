/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JOptionPane;

/**
 * 
 * @author Haofei
 */
public class Hide
{
   
   /**
    * @param args
    *           the command line arguments
    */
   public static void main(String[] args)
   {
      project run = new project();
      run.interface1();
      // TODO code application logic here
   }
}

class project
{
   
   String input1, input2, input3;
   String[] all = new String[100];
   int[] phonenumber = new int[10];
   int[] code = new int[3];
   int firsttime = 0, secondtime = 0;
   int encode, add, id = 0, i;
   
   public void interface1()
   {
      
      String[] mmm =
      { "encrypt", "contacts" };
      int choice = JOptionPane.showOptionDialog(null, "encrypt or contacts?",
            "Choose an option", JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null, mmm, "encrypt");
      if (choice == 0)
      {
         encrypt();
      }
      else
      {
         contacts();
      }
   }
   
   public void encrypt()
   {
      if (firsttime == 0)
      {
         input2 = JOptionPane
               .showInputDialog("input encoding unmbesr(3 digits)");
         encode = Integer.parseInt(input2);
      }
      else
      {
         
         input2 = JOptionPane.showInputDialog("enter old encode");
         if (encode == Integer.parseInt(input2))
         {
            input2 = JOptionPane.showInputDialog("enter new encode");
            encode = Integer.parseInt(input2);
         }
         else
         {
            JOptionPane.showMessageDialog(null, "the old encode is not right");
         }
      }
      if (encode < 0 || encode > 999)
      {
         JOptionPane.showMessageDialog(null, "please enter 3 digits");
         encrypt();
      }
      firsttime = 1;
      interface1();
   }
   
   public void algri()
   {
      code[2] = encode % 10;
      code[0] = (encode - ((encode) % 100)) / 100;
      code[1] = (encode - code[0] * 100 - code[2]) / 10;
      char[] c = new char[10];
      c = input3.toCharArray();
      i = 0;
      for (i = 0; i < 10; i++)
      {
         phonenumber[i] = c[i];
      }
      i = 0;
      while (i < 3)
      {
         phonenumber[i] = (phonenumber[i] + code[0]) % 10;
         i++;
      }
      while (i < 6)
      {
         phonenumber[i] = (phonenumber[i] + code[1]) % 10;
         i++;
      }
      while (i < 10)
      {
         phonenumber[i] = (phonenumber[i] + code[2]) % 10;
         i++;
      }
      input3 = phonenumber.toString();
      
      all[id] = input1 + ":" + input3;
      
   }
   
   public void contacts()
   {
      
      if (secondtime == 1)
      {
         
         String[] m =
         { "add", "cancel" };
         int choce = JOptionPane.showOptionDialog(null, all,
               "Choose an option", JOptionPane.YES_NO_OPTION,
               JOptionPane.INFORMATION_MESSAGE, null, m, "add");
         if (choce == 0)
         {
            input1 = JOptionPane.showInputDialog("add your contact's name");
            input3 = JOptionPane
                  .showInputDialog("add his/her phone number?(10 digits)");
            algri();
            id = id + 1;
            interface1();
            
         }
         else
         {
            interface1();
         }
         
      }
      else
      {
         input1 = JOptionPane.showInputDialog("add your contact's name");
         input3 = JOptionPane
               .showInputDialog("add his/her phone number?(10 digits)");
         algri();
         id = id + 1;
         secondtime = 1;
         interface1();
      }
      
   }
}
