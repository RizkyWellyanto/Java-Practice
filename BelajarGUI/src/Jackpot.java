/**
 * File: Jackpot.java
 * -------------------------------------------------------------
 * this program will be the frame of the jackpot program
 */

// import necessary classes
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// main class
public class Jackpot extends JFrame
{
   // member fields
   private static final int WIDTH = 450;
   private static final int LENGTH = 300;
   private static final int MAX_MONEY = 1000000;
   private int money = 1000;
   private int bet = 0;

   // multiplier of each Strings as constants
   private static int ONE_CHER = 5;       // one cherry at left
   private static int TWO_CHER = 15;      // two cherries at left and middle
   private static int THREE_CHER = 30;    // three cherries
   private static int THREE_BAR = 50;     // three bars
   private static int THREE_SEVEN = 100;  // jackpot!

   // items in the frame
   private JLabel labelMoney;
   private JLabel labelBet;
   private JLabel labelPercentMin;
   private JLabel labelPercentMax;
   private JButton buttonRoll;
   private JSlider sliderPercentage;
   private JLabel labelNum1;
   private JLabel labelNum2;
   private JLabel labelNum3;

   // static members for the jackpot pulls
   private static String BAR = "BAR";
   private static String CHERRIES = "Cherries";
   private static String SPACE = "Space";
   private static String SEVEN = "7";

   /**
    * the jackpot method
    */
   public Jackpot()
   {
      // title and properties of the frame
      super("Wellyanto's Jackpot Game");
      setLayout(new BorderLayout());
      setSize(WIDTH, LENGTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      setForeground(Color.RED);
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

      // create panels
      JPanel panel1 = new JPanel();
      panel1.setBackground(Color.YELLOW);

      JPanel panel2 = new JPanel();
      panel2.setBackground(Color.YELLOW);

      JPanel centerPanel = new JPanel();
      centerPanel.setLocation(this.getWidth(), this.getHeight());
      centerPanel.setBackground(Color.RED);

      // create label objects
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

      // create button objects
      buttonRoll = new JButton("Roll");

      // create slider object
      sliderPercentage = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
      sliderPercentage.setMajorTickSpacing(10);
      sliderPercentage.setPaintTicks(true);
      sliderPercentage.setBackground(Color.YELLOW);

      // add the interactors
      panel1.add(labelMoney);
      panel1.add(labelBet);
      panel2.add(labelPercentMin);
      panel2.add(sliderPercentage);
      panel2.add(labelPercentMax);
      panel2.add(buttonRoll);

      centerPanel.add(labelNum1);
      centerPanel.add(labelNum2);
      centerPanel.add(labelNum3);

      // add the panel to the layout
      add(panel1, BorderLayout.NORTH);
      add(panel2, BorderLayout.SOUTH);

      add(centerPanel, BorderLayout.CENTER);

      // add action listeners
      EventHandler handler = new EventHandler();
      buttonRoll.addActionListener(handler);

      ChangeHandler chHandler = new ChangeHandler();
      sliderPercentage.addChangeListener(chHandler);

      // present the frame
      setVisible(true);
   }

   /**
    * action listener class
    */
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

   /**
    * end the game if the user either have 0 or too much money...
    */
   private void endGame()
   {
      if (money == 0)
      {
         JOptionPane.showMessageDialog(null, "HAHAHA!! You lose ALL of your money!! Now Get Lost and only come back when you got more money!!");
         System.exit(0);
      } else if (money >= MAX_MONEY)
      {
         JOptionPane.showMessageDialog(null, "Holy Fuck! i mean, congratulations... you win all of our money... now we're fucking bankrupt... get lost!!");
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
         JOptionPane.showMessageDialog(null, "HAHAHA! You LOSE!! which means I WIN!! Luck is on MY side~ gimme that $ " + bet);
      } else
      {
         JOptionPane.showMessageDialog(null, "Oh shit... i mean, congratulations... you win : $ " + (bet * winMultiplier));
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
      } else if (randomNum >= 50 && randomNum < 75)
      {
         strOut = CHERRIES;
      } else if (randomNum >= 75 && randomNum < 87.5)
      {
         strOut = SPACE;
      } else if (randomNum >= 87.5 && randomNum < 100)
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

   /**
    * main method
    *
    * @param args
    */
   public static void main(String args[])
   {
      Jackpot jackpotFrame = new Jackpot();

      JOptionPane.showMessageDialog(null, "Hello, Welcome to the Wellyanto's Jackpot Program");
   }

}

