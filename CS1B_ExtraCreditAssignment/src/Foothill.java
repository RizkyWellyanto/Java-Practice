/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 10 in CS 1B Jesse Cecil Winter 2014.
 * this assignment is for extra credit purpose. this program is called
 * Jack-Poke-pot. this program is basically a pokemon themed jackpot machine.
 * this program implement GUI, Action Listeners, Inheritance, exceptions, 
 * and many more.
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */

// import necessary classes
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * the client class class
 * 
 * @author MuhammadRizky
 * 
 */
public class Foothill
{
   // main method of the program
   public static void main(String[] args)
   {
      // start the game
      Player currentPlayer = new Player();
      PlayerInterface login = new PlayerInterface(currentPlayer);
      
      // show message dialogue
      JOptionPane.showMessageDialog(null,
            "Welcome to Wellyanto's Jack-Poke-pot Machine");
      
      login.setVisible(true);
   }
}

/**
 * the player class, the goal is to create an object that have name and money
 * as it's member data, thus could be passed between objects, in this case
 * the player interface object and the jackpot machine object, also could be
 * further developed to store each players data to a txt file
 * 
 * @author MuhammadRizky
 * 
 */
class Player
{
   // constants
   public static final int MIN_NAME_LENGTH = 1;
   public static final int MAX_NAME_LENGTH = 100;
   public static final int MIN_MONEY = 1;
   public static final int MAX_MONEY = 1000000;
   public static final String DEF_NAME = "player";
   public static final int DEF_MONEY = 1000;
   
   // member data
   private String name;
   private int money;
   
   // constructors
   Player()
   {
      setName(DEF_NAME);
      setMoney(DEF_MONEY);
   }
   
   Player(String name, int money)
   {
      if (!setName(name))
         setName(DEF_NAME);
      
      if (!setMoney(money))
         setMoney(DEF_MONEY);
   }
   
   // mutator
   public boolean setName(String name)
   {
      if (validate(name))
      {
         this.name = name;
         return true;
      }
      
      return false;
   }
   
   public boolean setMoney(int money)
   {
      if (validate(money))
      {
         this.money = money;
         return true;
      }
      
      return false;
   }
   
   // accessor
   public String getName()
   {
      return this.name;
   }
   
   public int getMoney()
   {
      return this.money;
   }
   
   // other methods
   private boolean validate(String name)
   {
      if (name.length() < MIN_NAME_LENGTH || name.length() >= MAX_NAME_LENGTH)
      {
         return false;
      }
      
      return true;
   }
   
   private boolean validate(double money)
   {
      if (money < MIN_MONEY || money >= MAX_MONEY)
      {
         return false;
      }
      
      return true;
   }
}

/**
 * an extra class, just to make the player interface class play sound when
 * it first set to visible to the player
 * 
 * @author MuhammadRizky
 * 
 */
class MusicFrame extends JFrame
{
   private static String SOUND_PATH = "audio/Pokemon_Opening.wav";
   
   private boolean isMusicPlayed = false;
   
   MusicFrame(String str)
   {
      super(str);
   }
   
   public void setVisible(boolean b)
   {
      super.setVisible(b);
      
      // if it's the first time to call the setvisible method, play sound
      if (isMusicPlayed == false)
      {
         playSound();
         isMusicPlayed = true;
      }
   }
   
   // play the pokemon music, and loop it indefinitely
   private static void playSound()
   {
      try
      {
         AudioInputStream audioInputStream = AudioSystem
               .getAudioInputStream(new File(SOUND_PATH).getAbsoluteFile());
         Clip clip = AudioSystem.getClip();
         clip.open(audioInputStream);
         clip.loop(Clip.LOOP_CONTINUOUSLY);
         clip.start();
      }
      catch (Exception ex)
      {
         System.out.println("Error Playing the Sound");
      }
   }
}

/**
 * a frame that interacts with the player the first time the program is
 * started
 * 
 * @author MuhammadRizky
 * 
 */
class PlayerInterface extends MusicFrame
{
   // constants
   private static final int WIDTH = 400;
   private static final int LENGTH = 150;
   private static final String TEXT_PATH = "instructions/instructions.txt";
   
   // member field
   Player currentPlayer;
   
   // objects in the frame
   private JLabel labelName;
   private JLabel labelMoney;
   private JLabel labelTitle;
   private JTextField textName;
   private JTextField textMoney;
   private JButton buttonInstruction;
   private JButton buttonPlay;
   private JPanel panelNorth;
   private JPanel panelSouth;
   private JPanel panelCenter;
   private JPanel panelCenter1;
   private JPanel panelCenter2;
   
   // constructor
   PlayerInterface(Player currentPlayer)
   {
      super("Login Page");
      setLayout(new BorderLayout());
      setSize(WIDTH, LENGTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2
            - this.getSize().height / 2);
      
      // assign member data
      this.currentPlayer = currentPlayer;
      
      // create panels
      panelNorth = new imagePanel();
      
      panelSouth = new imagePanel();
      
      panelCenter = new imagePanel();
      
      panelCenter1 = new imagePanel();
      panelCenter1.setLayout(new BoxLayout(panelCenter1, BoxLayout.Y_AXIS));
      
      panelCenter2 = new imagePanel();
      panelCenter2.setLayout(new BoxLayout(panelCenter2, BoxLayout.Y_AXIS));
      
      // instantiate objects
      labelTitle = new JLabel("Welcome To Jack-Poke-pot");
      
      labelName = new JLabel("Your Name : ");
      labelName.setToolTipText("Enter your name here");
      
      labelMoney = new JLabel("Your Money : $");
      labelMoney.setToolTipText("Enter your money here");
      
      textName = new JTextField(20);
      
      textMoney = new JTextField(20);
      
      // instantiate button objects
      buttonInstruction = new JButton("Instruction");
      buttonPlay = new JButton("Play");
      
      // add the interactors
      panelSouth.add(buttonInstruction);
      panelSouth.add(buttonPlay);
      
      panelCenter1.add(labelName);
      panelCenter1.add(labelMoney);
      
      panelCenter2.add(textName);
      panelCenter2.add(textMoney);
      
      panelCenter.add(panelCenter1);
      panelCenter.add(panelCenter2);
      
      panelNorth.add(labelTitle);
      
      // add the panel to the layout
      add(panelNorth, BorderLayout.NORTH);
      add(panelSouth, BorderLayout.SOUTH);
      add(panelCenter, BorderLayout.CENTER);
      
      // add action listeners
      EventHandler handler = new EventHandler();
      
      buttonInstruction.addActionListener(handler);
      buttonPlay.addActionListener(handler);
   }
   
   // private class action listeners
   private class EventHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String lines;
         
         // if button instruction is clicked
         if (e.getSource() == buttonInstruction)
         {
            lines = readText(TEXT_PATH);
            
            JOptionPane.showMessageDialog(null, lines);
         }
         
         // if button play is clicked
         if (e.getSource() == buttonPlay)
         {
            // the name and money is valid, open a new jackpot frame
            if (currentPlayer.setName(textName.getText())
                  && currentPlayer.setMoney(Integer.parseInt(textMoney
                        .getText())))
            {
               setVisible(false);
               Jackpot jackpot = new Jackpot(currentPlayer);
               jackpot.setVisible(true);
            }
            // otherwise, give error message to the player
            else
            {
               JOptionPane.showMessageDialog(null,
                     "Please enter your name correctly("
                           + Player.MIN_NAME_LENGTH + "-"
                           + Player.MAX_NAME_LENGTH + "character) "
                           + "& enter your money in range ($"
                           + Player.MIN_MONEY + "-$" + Player.MAX_MONEY + ")");
            }
         }
      }
      
      // amethod that read every lines of the text
      private String readText(String filePath)
      {
         try
         {
            BufferedReader rd = new BufferedReader(new FileReader(filePath));
            String line = "";
            
            // read each line
            while (true)
            {
               // create a one big huge String
               String newLine = rd.readLine();
               if (newLine == null)
                  break;
               line = line + "\n" + newLine;
            }
            
            // return the string that have the file content
            return line;
         }
         catch (Exception e)
         {
            System.out.print("Error reading Text File");
            return "ERROR READING FILE!";
         }
      }
   }
}

/**
 * the main frame of the program, will be called by the playerinterface frame.
 * this is the main jackpot part of the game
 * 
 * @author MuhammadRizky
 * 
 */
class Jackpot extends JFrame
{
   // constants
   private static final int WIDTH = 600;
   private static final int LENGTH = 350;
   
   // member fields
   Player currentPlayer;
   private int bet;
   
   // multiplier of each Strings as constants
   private static int ONE_CHAR = 5;       // one cherry at left
   private static int TWO_CHAR = 15;      // two cherries at left and middle
   private static int THREE_CHAR = 30;    // three cherries
   private static int THREE_BUL = 50;     // three bars
   private static int THREE_MEW = 100;  // jackpot!
   
   // objects in the frame
   private JLabel labelMoney;
   private JLabel labelBet;
   private JLabel labelPercentMin;
   private JLabel labelPercentMax;
   private JButton buttonRoll;
   private JSlider sliderPercentage;
   private JLabel labelLeft;
   private JLabel labelCenter;
   private JLabel labelRight;
   private JPanel panelNorth;
   private JPanel panelSouth;
   private JPanel panelCenter;
   
   // static members for the jackpot pulls
   private static Icon BULBASAUR = new ImageIcon("images/bulbasaur.png");
   private static Icon CHARMANDER = new ImageIcon("images/charmander.png");
   private static Icon SQUIRTLE = new ImageIcon("images/squirtle.png");
   private static Icon MEW = new ImageIcon("images/mew.png");
   
   // Constructor of the frame
   Jackpot(Player currentPlayer)
   {
      super("Wellyanto's Jackpot Game");
      setLayout(new BorderLayout());
      setSize(WIDTH, LENGTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2
            - this.getSize().height / 2);
      
      // assign member data
      this.currentPlayer = currentPlayer;
      
      // create panels
      panelNorth = new imagePanel();
      
      panelSouth = new JPanel();
      panelSouth.setBackground(Color.LIGHT_GRAY);
      
      panelCenter = new imagePanel();
      panelCenter.setLocation(this.getWidth(), this.getHeight());
      panelCenter.setLayout(new GridLayout(1, 3));
      
      // instantiate label objects
      labelMoney = new JLabel("Your Money : $" + currentPlayer.getMoney());
      labelMoney.setToolTipText("Current money");
      
      labelBet = new JLabel("Current Bet : $ 0");
      labelBet.setToolTipText("Slide the money Bet on the slider");
      
      labelPercentMin = new JLabel("0%");
      labelPercentMin.setToolTipText("Min Bet");
      
      labelPercentMax = new JLabel("100%");
      labelPercentMax.setToolTipText("Max Bet");
      
      labelLeft = new JLabel(MEW);
      
      labelCenter = new JLabel(MEW);
      
      labelRight = new JLabel(MEW);
      
      // instantiate button objects
      buttonRoll = new JButton("Roll");
      
      // instantiate slider object
      sliderPercentage = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
      sliderPercentage.setMajorTickSpacing(10);
      sliderPercentage.setPaintTicks(true);
      sliderPercentage.setBackground(Color.LIGHT_GRAY);
      
      // add the interactors
      panelNorth.add(labelMoney);
      panelNorth.add(labelBet);
      panelSouth.add(labelPercentMin);
      panelSouth.add(sliderPercentage);
      panelSouth.add(labelPercentMax);
      panelSouth.add(buttonRoll);
      
      panelCenter.add(labelLeft);
      panelCenter.add(labelCenter);
      panelCenter.add(labelRight);
      
      // add the panel to the layout
      add(panelNorth, BorderLayout.NORTH);
      add(panelSouth, BorderLayout.SOUTH);
      
      add(panelCenter, BorderLayout.CENTER);
      
      // add action listeners
      EventHandler handler = new EventHandler();
      buttonRoll.addActionListener(handler);
      
      ChangeHandler chHandler = new ChangeHandler();
      sliderPercentage.addChangeListener(chHandler);
      
   }
   
   // message the player whether he wins or lose
   private void message(int winMultiplier)
   {
      if (winMultiplier == 0)
      {
         JOptionPane.showMessageDialog(null, "You lose! you've lost $ " + bet);
      }
      else
      {
         String str = "ERROR";
         
         // Check why did the player win
         str = pullResultResponse(winMultiplier);
         
         // tell the player why did he/she win
         JOptionPane.showMessageDialog(null, str + " you've won : $"
               + (bet * winMultiplier));
      }
   }
   
   // just a method that helps message method
   private String pullResultResponse(int inputInt)
   {
      String str = "";
      
      if (inputInt == ONE_CHAR)
      {
         str = "Congratulations, Your Pull is ONE CHARMANDER!";
      }
      else if (inputInt == TWO_CHAR)
      {
         str = "Congratulations, Your Pull is TWO CHARMANDER!";
      }
      else if (inputInt == THREE_CHAR)
      {
         str = "Wow, Your Pull is THREE CHARMANDER!!!";
      }
      else if (inputInt == THREE_BUL)
      {
         str = "Congratulations, Your Pull is THREE BULBASAUR!";
      }
      else if (inputInt == THREE_MEW)
      {
         str = "JACKPOT!!!, Your Pull is THREE MEW!!!!!";
      }
      
      return str;
   }
   
   // a method that calculate the bet multiplier from the pull output
   private int multiplier()
   {
      int multiplier = 0;
      
      // if label 1 is charmander, and label 2 is not charmander
      if (labelLeft.getIcon().equals(CHARMANDER))
      {
         if (!labelCenter.getIcon().equals(CHARMANDER))
         {
            if (!labelRight.getIcon().equals(CHARMANDER))
            {
               multiplier = ONE_CHAR;
            }
         }
      }
      
      // if label 2 is charmander, and label 1 and 3 is not charmander
      if (!labelLeft.getIcon().equals(CHARMANDER))
      {
         if (labelCenter.getIcon().equals(CHARMANDER))
         {
            if (!labelRight.getIcon().equals(CHARMANDER))
            {
               multiplier = ONE_CHAR;
            }
         }
      }
      
      // if label 3 is charmander, and label 1 and 2 is not charmander
      if (!labelLeft.getIcon().equals(CHARMANDER))
      {
         if (!labelCenter.getIcon().equals(CHARMANDER))
         {
            if (labelRight.getIcon().equals(CHARMANDER))
            {
               multiplier = ONE_CHAR;
            }
         }
      }
      
      // if label1 and label2 is charmander
      if (labelLeft.getIcon().equals(CHARMANDER))
      {
         if (labelCenter.getIcon().equals(CHARMANDER))
         {
            if (!labelRight.getIcon().equals(CHARMANDER))
            {
               multiplier = TWO_CHAR;
            }
         }
      }
      
      // if label1 and label3 is charmander
      if (labelLeft.getIcon().equals(CHARMANDER))
      {
         if (!labelCenter.getIcon().equals(CHARMANDER))
         {
            if (labelRight.getIcon().equals(CHARMANDER))
            {
               multiplier = TWO_CHAR;
            }
         }
      }
      
      // if label2 and label3 is charmander
      if (!labelLeft.getIcon().equals(CHARMANDER))
      {
         if (labelCenter.getIcon().equals(CHARMANDER))
         {
            if (labelRight.getIcon().equals(CHARMANDER))
            {
               multiplier = TWO_CHAR;
            }
         }
      }
      
      // if label1,2, and 3 are charmanders
      if (labelLeft.getIcon().equals(CHARMANDER))
      {
         if (labelCenter.getIcon().equals(CHARMANDER))
         {
            if (labelRight.getIcon().equals(CHARMANDER))
            {
               multiplier = THREE_CHAR;
            }
         }
      }
      
      // if three of the label are bulbasaur
      if (labelLeft.getIcon().equals(BULBASAUR))
      {
         if (labelCenter.getIcon().equals(BULBASAUR))
         {
            if (labelRight.getIcon().equals(BULBASAUR))
            {
               multiplier = THREE_BUL;
            }
         }
      }
      
      // if three of the label are mew, jack-poke-pot!
      if (labelLeft.getIcon().equals(MEW))
      {
         if (labelCenter.getIcon().equals(MEW))
         {
            if (labelRight.getIcon().equals(MEW))
            {
               multiplier = THREE_MEW;
            }
         }
      }
      
      return multiplier;
   }
   
   // a method that randomize the pictures
   private Icon pull()
   {
      double randomNum;
      Icon iconOut = new ImageIcon();
      randomNum = (Math.random() * 100);
      
      // bulbasaur chance is 50%
      if (randomNum >= 0 && randomNum < 50)
      {
         iconOut = BULBASAUR;
      }
      
      // charmander chance is 25%
      else if (randomNum >= 50 && randomNum < 75)
      {
         iconOut = CHARMANDER;
      }
      
      // squirtle chance is 12.5%
      else if (randomNum >= 75 && randomNum < 87.5)
      {
         iconOut = SQUIRTLE;
      }
      
      // mew chance is 12.5%
      else if (randomNum >= 87.5 && randomNum < 100)
      {
         iconOut = MEW;
      }
      
      return iconOut;
   }
   
   /**
    * change listener class, to handle the slider
    */
   private class ChangeHandler implements ChangeListener
   {
      // make the slider change the value of players money and bet
      public void stateChanged(ChangeEvent event)
      {
         bet = (sliderPercentage.getValue() * currentPlayer.getMoney() / 100);
         
         labelBet.setText("Current Bet : $ " + bet);
         labelMoney.setText("Current Money : $"
               + (currentPlayer.getMoney() - bet));
      }
   }
   
   /**
    * action listener class, handle the buttons
    */
   private class EventHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         // if buttonRoll is clicked,
         if (event.getSource() == buttonRoll)
         {
            int multiplier;
            
            // deduct the players money
            currentPlayer.setMoney(currentPlayer.getMoney() - bet);
            
            // randomize three of the icons
            labelLeft.setIcon(pull());
            labelCenter.setIcon(pull());
            labelRight.setIcon(pull());
            
            multiplier = multiplier();
            
            // give message about the multiplier
            message(multiplier);
            
            // set the new money
            if (currentPlayer.setMoney(currentPlayer.getMoney()
                  + (bet * multiplier)))
            {
               // if set money success, set the interactors to its normal values
               bet = 0;
               labelMoney.setText("Your Money : $ " + currentPlayer.getMoney());
               labelBet.setText("Current Bet : $ 0");
               sliderPercentage.setValue(0);
            }
            // if player's money is out of range, quit the game
            else
            {
               // if player is out of money, kick him
               if (currentPlayer.getMoney() == 0)
               {
                  JOptionPane.showMessageDialog(null,
                        "You've lost all of your money, get lost!");
                  System.exit(0);
               }
               // if player money exceed the max money, jackpot machine broke
               else
               {
                  int totalMoney = currentPlayer.getMoney()
                        + (bet * multiplier);
                  
                  JOptionPane.showMessageDialog(
                        null,
                        "congratulations... you win all of our money... "
                              + "this jackpot machine is out of money..."
                              + "You've won total money: $"
                              + currentPlayer.getMoney() + " + $"
                              + (bet * multiplier) + " = $" + totalMoney);
                  System.exit(0);
               }
            }
         }
      }
   }
}

/**
 * a class that modify the panel's background image. just for graphic purpose
 * 
 * @author MuhammadRizky
 * 
 */
class imagePanel extends JPanel
{
   protected void paintComponent(Graphics g)
   {
      BufferedImage img = null;
      
      try
      {
         img = ImageIO.read(new File("images/background.jpg"));
      }
      catch (IOException e)
      {
      }
      
      super.paintComponent(g);
      g.drawImage(img, 0, 0, null);
   }
}
