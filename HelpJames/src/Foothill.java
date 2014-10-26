import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;

public class Foothill
{
   public static void main(String[] args)
   {
      // instantiate student objects
      Student Andrew = new Student("Andrew", 1001, "Andrew.gif");
      Student Bob = new Student("Bob", 1002, "Bob.gif");
      Student Charlie = new Student("Charlie", 1003, "Charlie.gif");
      
      Student[] studentArray = {Andrew, Bob, Charlie};
      
      // Create and set up the window.
      JFrame frame = new JFrame("Student Frame");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Create and set up the content pane.
      JComponent newContentPane = new MainFrame(studentArray);
      newContentPane.setOpaque(true); // content panes must be opaque
      frame.setContentPane(newContentPane);
      
      // Display the window.
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      
   }
}

class Student
{
   private static final int MIN_ID = 0;
   private static final int MAX_ID = 1000;
   
   private String name = "Error";
   private int ID = 1000;
   private ImageIcon pict = null;
   
   public Student(String name, int ID, String picturePath)
   {
      if (!setName(name))
         this.name = name;
      if (!setName(name))
         this.ID = ID;
      if (!setName(name))
         this.pict = new ImageIcon(picturePath);
   }
   
   public String getName()
   {
      return name;
   }
   
   public int getID()
   {
      return ID;
   }
   
   public ImageIcon getPict()
   {
      return this.pict;
   }
   
   public boolean setName(String name)
   {
      if (!validateString(name))
         return false;
      this.name = name;
      return true;
   }
   
   public boolean setID(int num)
   {
      if (!validateID(num))
         return false;
      this.ID = num;
      return true;
   }
   
   public boolean setPict(String path)
   {
      try
      {
         this.pict = new ImageIcon(path);
         return true;
      }
      catch (Exception e)
      {
         return false;
      }
   }
   
   public String toString()
   {
      String resultString;
      resultString = " ";
      return resultString;
   }
   
   private static boolean validateString(String testStr)
   {
      if (testStr != null && Character.isLetter(testStr.charAt(0)))
         return true;
      return false;
   }
   
   private static boolean validateID(int num)
   {
      if (num < MIN_ID || num > MAX_ID)
         return true;
      return false;
   }
}

class MainFrame extends JPanel implements ActionListener
{
   JLabel picture;
   
   public MainFrame(Student students[])
   {
      super(new BorderLayout());
      
      String studentStrings[] = new String[students.length];
      
      for (int i = 0; i < students.length; i++)
      {
         studentStrings[i] = students[i].getName();
      }
            
      // Create the combo box, with student names
      JComboBox studentList = new JComboBox(studentStrings);
      studentList.setSelectedIndex(0);
      studentList.addActionListener(this);
      
      picture = new JLabel();
      picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
      picture.setHorizontalAlignment(JLabel.CENTER);
      updateLabel(studentStrings[studentList.getSelectedIndex()]);
      picture.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      
      picture.setSize(new Dimension(500, 500));
      
      // Lay out the JObjects
      add(studentList, BorderLayout.PAGE_START);
      add(picture, BorderLayout.PAGE_END);
      setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      
   }
   
   // the listener of the frame
   public void actionPerformed(ActionEvent e)
   {
      JComboBox cb = (JComboBox) e.getSource();
      String studentName = (String) cb.getSelectedItem();
      updateLabel(studentName);
   }
   
   // method that will update the label
   protected void updateLabel(String name)
   {
      // updatae the images if the user change 
      ImageIcon icon = createImageIcon("images/" + name + ".png");
      picture.setIcon(icon);
      picture.setToolTipText("Picture of: " + name.toLowerCase());
      if (icon != null)
      {
         picture.setText(null);
      }
      else
      {
         picture.setText("Image not found");
      }
   }
   
   // method that will create the image icon
   protected static ImageIcon createImageIcon(String path)
   {
      // create images
      java.net.URL imgURL = MainFrame.class.getResource(path);
      if (imgURL != null)
      {
         return new ImageIcon(imgURL);
      }
      else
      {
         System.err.println("Couldn't find file: " + path);
         return null;
      }
   }   
}
