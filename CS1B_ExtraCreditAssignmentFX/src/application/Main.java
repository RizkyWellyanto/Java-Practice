package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      try
      {
         BorderPane root = new BorderPane();
         Scene scene = new Scene(root, 400, 400);
         scene.getStylesheets().add(
               getClass().getResource("application.css").toExternalForm());
         primaryStage.setScene(scene);
         primaryStage.show();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
   
   public static void main(String[] args)
   {
      launch(args);
   }
}

package Jack_Poke_pot;

/**
 * File: Foothill.java
 * -----------------------------------------------------------------------------
 * this program is due to assignment number 10 in CS 1B Jesse Cecil Winter 2014.
 * this assignment is for extra credit purpose. this program is called
 * Jack-Poke-pot. this program is basically a pokemon themed jackpot machine.
 * this program implement GUI, Action Listeners, Media Player, Inheritance, File
 * Writer & Reader, exceptions, and many more.
 * 
 * Name : Muhammad Rizky Wellyanto
 * ID : 20144588
 */


import javafx.scene.media.MediaPlayer;

// import necessary classes
import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Foothill
{
   /**
    * main method
    * 
    * @param args
    */
   public static void main(String[] args)
   {
      // testing purposes
      PlayerInterface login = new PlayerInterface();
      
      // show message dialogue
      
      // present the PlayerInterface object
      
      // present the Jackpot object
   }
}

class Player
{
   // constants
   public static final int MIN_NAME_LENGTH = 1;
   public static final int MAX_NAME_LENGTH = 100;
   public static final double MIN_MONEY = 100;
   public static final double MAX_MONEY = 1000000;
   
   // member data
   private String name;
   private double money;
   
   // constructor
   Player(String name, double money) throws Exception
   {
      setName(name);
      setMoney(money);
   }
   
   // mutator
   public void setName(String name) throws nameOutOfRangeException
   {
      validate(name);
      this.name = name;
   }
   
   public void setMoney(double money) throws moneyOutOfRangeException
   {
      validate(money);
      this.money = money;
   }
   
   // accessor
   public String getName()
   {
      return this.name;
   }
   
   public double getMoney()
   {
      return this.money;
   }
   
   // other methods
   private void validate(String name) throws nameOutOfRangeException
   {
      if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
      {
         throw new nameOutOfRangeException();
      }
   }
   
   private void validate(double money) throws moneyOutOfRangeException
   {
      if (money < MIN_MONEY || money > MAX_MONEY)
      {
         throw new moneyOutOfRangeException();
      }
   }
   
   private class nameOutOfRangeException extends Exception
   {
   }
   
   private class moneyOutOfRangeException extends Exception
   {
   }
}

class PlayerInterface extends JFrame
{
   // constants
   private static final int WIDTH = 400;
   private static final int LENGTH = 150;
   
   // member fields
   private String name;
   private double money;
   
   // objects in the frame
   private JLabel labelName;
   private JLabel labelMoney;
   private JLabel labelTitle;
   private JTextField textName;
   private JTextField textMoney;
   private JButton buttonLoad;
   private JButton buttonRegister;
   private JPanel panelNorth;
   private JPanel panelSouth;
   private JPanel panelCenter;
   private JPanel panelCenter1;
   private JPanel panelCenter2;
   
   // constructor
   PlayerInterface()
   {
      super("Login Page");
      setLayout(new BorderLayout());
      setSize(WIDTH, LENGTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      setForeground(Color.RED);
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2
            - this.getSize().height / 2);
      
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
      buttonLoad = new JButton("Load Game");
      buttonRegister = new JButton("Register");
      
      // add the interactors
      panelSouth.add(buttonLoad);
      panelSouth.add(buttonRegister);
      
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
      
      buttonLoad.addActionListener(handler);
      buttonRegister.addActionListener(handler);
      
      // play the song
      playSound();
      
      // present the whole frame
      setVisible(true);
   }
   
   // private class action listeners
   private class EventHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent arg0)
      {
         //
      }
   }
   
   // other private class
   private class imagePanel extends JPanel
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
   
   public void playSound()
   {
      Media hit = new Media("audio/pokemon_opening_trap.mp3");
      MediaPlayer mediaPlayer = new MediaPlayer(hit);
      mediaPlayer.play();
   }
   
}

class Jackpot extends JFrame
{
   // constants
   private static final int WIDTH = 450;
   private static final int LENGTH = 300;
   private static final double MAX_MONEY = Player.MAX_MONEY;
   
   // member fields
   private int money = 1000;
   private int bet = 0;
   
   // multiplier of each Strings as constants
   private static int ONE_CHER = 5;       // one cherry at left
   private static int TWO_CHER = 15;      // two cherries at left and middle
   private static int THREE_CHER = 30;    // three cherries
   private static int THREE_BAR = 50;     // three bars
   private static int THREE_SEVEN = 100;  // jackpot!
   
   // objects in the frame
   private JLabel labelMoney;
   private JLabel labelBet;
   private JLabel labelPercentMin;
   private JLabel labelPercentMax;
   private JButton buttonRoll;
   private JSlider sliderPercentage;
   private JLabel labelNum1;
   private JLabel labelNum2;
   private JLabel labelNum3;
   private JPanel panelNorth;
   private JPanel panelSouth;
   private JPanel panelCenter;
   
   // static members for the jackpot pulls
   private static String BAR = "BAR";
   private static String CHERRIES = "Cherries";
   private static String SPACE = "Space";
   private static String SEVEN = "7";
   
   // Constructor of the frame
   Jackpot()
   {
      super("Wellyanto's Jackpot Game");
      setLayout(new BorderLayout());
      setSize(WIDTH, LENGTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      setForeground(Color.RED);
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2
            - this.getSize().height / 2);
      
      // create panels
      panelNorth = new JPanel();
      panelNorth.setBackground(Color.YELLOW);
      
      panelSouth = new JPanel();
      panelSouth.setBackground(Color.YELLOW);
      
      panelCenter = new JPanel();
      panelCenter.setLocation(this.getWidth(), this.getHeight());
      panelCenter.setBackground(Color.RED);
      
      // instantiate label objects
      labelMoney = new JLabel("Your Money : $ " + money);
      labelMoney.setToolTipText("Current money");
      
      labelBet = new JLabel("Current Bet : $ 0");
      labelBet.setToolTipText("Slide the money Bet on the slider");
      
      labelPercentMin = new JLabel("0%");
      labelPercentMin.setToolTipText("Min Bet");
      
      labelPercentMax = new JLabel("100%");
      labelPercentMax.setToolTipText("Max Bet");
      
      labelNum1 = new JLabel(SEVEN);
      
      labelNum2 = new JLabel(SEVEN);
      
      labelNum3 = new JLabel(SEVEN);
      
      // instantiate button objects
      buttonRoll = new JButton("Roll");
      
      // instantiate slider object
      sliderPercentage = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
      sliderPercentage.setMajorTickSpacing(10);
      sliderPercentage.setPaintTicks(true);
      sliderPercentage.setBackground(Color.YELLOW);
      
      // add the interactors
      panelNorth.add(labelMoney);
      panelNorth.add(labelBet);
      panelSouth.add(labelPercentMin);
      panelSouth.add(sliderPercentage);
      panelSouth.add(labelPercentMax);
      panelSouth.add(buttonRoll);
      
      panelCenter.add(labelNum1);
      panelCenter.add(labelNum2);
      panelCenter.add(labelNum3);
      
      // add the panel to the layout
      add(panelNorth, BorderLayout.NORTH);
      add(panelSouth, BorderLayout.SOUTH);
      
      add(panelCenter, BorderLayout.CENTER);
      
      // add action listeners
      EventHandler handler = new EventHandler();
      buttonRoll.addActionListener(handler);
      
      ChangeHandler chHandler = new ChangeHandler();
      sliderPercentage.addChangeListener(chHandler);
      
      // present the frame
      setVisible(true);
   }
   
   /**
    * end the game if the player is either out of money or gained a lot of money
    */
   private void endGame()
   {
      if (money == 0)
      {
         JOptionPane.showMessageDialog(null, "HAHAHA!! You lose ALL of your "
               + "money!! Now Get Lost and only come back when you got "
               + "more money!!");
         System.exit(0);
      }
      else if (money >= MAX_MONEY)
      {
         JOptionPane.showMessageDialog(null, "Holy Fuck! i mean, "
               + "congratulations... you win all of our money... "
               + "now we're fucking bankrupt... get lost!!");
         System.exit(0);
      }
   }
   
   /**
    * give message to the player how much they win or lose
    * 
    * @param winMultiplier
    */
   private void message(int winMultiplier)
   {
      if (winMultiplier == 0)
      {
         JOptionPane.showMessageDialog(null, "HAHAHA! You LOSE!! "
               + "which means I WIN!! Luck is on MY side~ gimme that $ " + bet);
      }
      else
      {
         JOptionPane.showMessageDialog(null, "Oh shit... i mean, "
               + "congratulations... you win : $ " + (bet * winMultiplier));
      }
   }
   
   /**
    * the method that check the result of the pull and multiplier the bonus
    */
   private int multiplier()
   {
      int multiplier = 0;
      
      if (labelNum1.getText().equals(CHERRIES))
      {
         if (!labelNum2.getText().equals(CHERRIES))
         {
            multiplier = ONE_CHER;
         }
      }
      
      if (labelNum1.getText().equals(CHERRIES))
      {
         if (labelNum2.getText().equals(CHERRIES))
         {
            multiplier = TWO_CHER;
         }
      }
      
      if (labelNum1.getText().equals(CHERRIES))
      {
         if (labelNum2.getText().equals(CHERRIES))
         {
            if (labelNum3.getText().equals(CHERRIES))
            {
               multiplier = THREE_CHER;
            }
         }
      }
      
      if (labelNum1.getText().equals(BAR))
      {
         if (labelNum2.getText().equals(BAR))
         {
            if (labelNum3.getText().equals(BAR))
            {
               multiplier = THREE_BAR;
            }
         }
      }
      
      if (labelNum1.getText().equals(SEVEN))
      {
         if (labelNum2.getText().equals(SEVEN))
         {
            if (labelNum3.getText().equals(SEVEN))
            {
               multiplier = THREE_SEVEN;
            }
         }
      }
      
      return multiplier;
   }
   
   /**
    * the method that randomize the output, "pull" the lever
    */
   private String pull()
   {
      double randomNum;
      String strOut = "";
      randomNum = (Math.random() * 100);
      
      if (randomNum >= 0 && randomNum < 50)
      {
         strOut = BAR;
      }
      else if (randomNum >= 50 && randomNum < 75)
      {
         strOut = CHERRIES;
      }
      else if (randomNum >= 75 && randomNum < 87.5)
      {
         strOut = SPACE;
      }
      else if (randomNum >= 87.5 && randomNum < 100)
      {
         strOut = SEVEN;
      }
      
      return strOut;
   }
   
   /**
    * change listener class
    */
   private class ChangeHandler implements ChangeListener
   {
      public void stateChanged(ChangeEvent event)
      {
         bet = (sliderPercentage.getValue() * money / 100);
         
         labelBet.setText("Current Bet : $ " + bet);
         labelMoney.setText("Current Money : $ " + (money - bet));
      }
   }
   
   private class EventHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         if (event.getSource() == buttonRoll)
         {
            money -= bet;
            
            labelNum1.setText(pull());
            labelNum2.setText(pull());
            labelNum3.setText(pull());
            
            message(multiplier());
            money += (bet * multiplier());
            
            bet = 0;
            labelMoney.setText("Your Money : $ " + money);
            labelBet.setText("Current Bet : $ 0");
            sliderPercentage.setValue(0);
            
            endGame();
         }
      }
   }
}

