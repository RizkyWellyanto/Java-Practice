/* File: Foothill.java
 * -----------------------------------------------------------------------------
 * This program is due to Assignment_2 in course "FH CS 001 ML F13"
 * This program demonstrate the accuracy of Stirling's approximation.
 * Created By: Muhammad Rizky Wellyanto
 * Student ID: 20144588
 */

// import necessary classes
import java.util.Scanner;                 // needed for users input
import java.text.DecimalFormat;           // needed to format the decimals

public class Foothill
{

   public static void main(String args[])
   {
      // declare necessary variables
      int startNum = 0;
      int maxNum = 0;
      String input;
      Scanner keyboard;
      DecimalFormat formater;
      
      // name and ID number
      makeLine(80, "-");
      System.out.println("Last Name     : Wellyanto");
      System.out.println("Student ID    : 20144588");
      makeLine(80, "-");
      
      // ask the user for the inputs
      System.out.print("Please insert the minimum/start Number : ");
      keyboard = new Scanner(System.in);
      input = keyboard.nextLine();
      startNum = Integer.parseInt(input);
      System.out.print("Please insert the maximum/end Number   : ");
      keyboard = new Scanner(System.in);
      maxNum = keyboard.nextInt();
      
      // check whether the range is make sense
      while (startNum >= maxNum)
      {
         System.out.println("Error! the start number musn't exceed the " +
               "max number");
         // ask the user for the inputs again
         System.out.print("Please insert the minimum/start Number : ");
         keyboard = new Scanner(System.in);
         input = keyboard.nextLine();
         startNum = Integer.parseInt(input);
         System.out.print("Please insert the maximum/end Number   : ");
         keyboard = new Scanner(System.in);
         maxNum = keyboard.nextInt();
      }
      
      // create the table
      makeLine(80, "-");
      System.out.print("n          ");
      System.out.print("n!                ");
      System.out.print("Stirling's Approximation          ");
      System.out.println("Error");
      makeLine(80, "-");
      
      // display the results
      while (startNum <= maxNum)
      {
         formater = new DecimalFormat("0.0000E0");
         System.out.print(startNum + 
               "           "); 
         System.out.print(formater.format(factorial(startNum)) + 
               "           "); 
         System.out.print(formater.format(stirlingFormula(startNum)) + 
               "                          "); 
         formater = new DecimalFormat("0.00%");
         System.out.println(formater.format(error(factorial(startNum), 
               stirlingFormula(startNum))));
         startNum++;
      }
      
   }
   
   /* factorial method
    * pre-cond    : throw in a variable "n"
    * post-cond   : calculate "n"! using a for loop and return the value
    */
   private static double factorial(double number) 
   {
      double output = 1;
      for(double counter = 1; counter <= number; counter += 1) 
      {
         output *= counter;
      }
      return output;
   }
   
   /* calculates the stirling formula
    * pre-cond    : throw in a variable "n"
    * post-cond   : calculate "n" using Stirling's formula and return the value
    */
   private static double stirlingFormula(double number)
   {
      number = (Math.pow((number/Math.E), number))*Math.sqrt(2*Math.PI*number);
      return number;
   }
   
   /* calculates the error
    * pre-cond    : throw in the factorial answer and Stirling's formula
    * post-cond   : calculate the difference and return it as %
    */
   private static double error(double factorial, double stirlingFormula)
   {
      double output;
      output = (factorial - stirlingFormula)/(double)factorial;
      return output;
   }
   
   
   /* create a line
    * pre-cond    : throw in a "length" value and string "shape"
    * post-cond   : create a line with the length "length" and shape "shape"
    */
   private static void makeLine(int length, String shape)
   {
      for(int counter = 0; counter < length; counter += 1)
      {
         System.out.print(shape);
      }
   }
}

/*---------------------------Sample Run-----------------------------------------

--------------------------------------------------------------------------------
Last Name     : Wellyanto
Student ID    : 20144588
--------------------------------------------------------------------------------
Please insert the minimum/start Number : 200
Please insert the maximum/end Number   : 1
Error! the start number musn't exceed the max number
Please insert the minimum/start Number : 1
Please insert the maximum/end Number   : 200
--------------------------------------------------------------------------------
n          n!                Stirling's Approximation          Error
--------------------------------------------------------------------------------
1           1.0000E0           9.2214E-1                          7.79%
2           2.0000E0           1.9190E0                          4.05%
3           6.0000E0           5.8362E0                          2.73%
4           2.4000E1           2.3506E1                          2.06%
5           1.2000E2           1.1802E2                          1.65%
6           7.2000E2           7.1008E2                          1.38%
7           5.0400E3           4.9804E3                          1.18%
8           4.0320E4           3.9902E4                          1.04%
9           3.6288E5           3.5954E5                          0.92%
10           3.6288E6           3.5987E6                          0.83%
11           3.9917E7           3.9616E7                          0.75%
12           4.7900E8           4.7569E8                          0.69%
13           6.2270E9           6.1872E9                          0.64%
14           8.7178E10           8.6661E10                          0.59%
15           1.3077E12           1.3004E12                          0.55%
16           2.0923E13           2.0814E13                          0.52%
17           3.5569E14           3.5395E14                          0.49%
18           6.4024E15           6.3728E15                          0.46%
19           1.2165E17           1.2111E17                          0.44%
20           2.4329E18           2.4228E18                          0.42%
21           5.1091E19           5.0889E19                          0.40%
22           1.1240E21           1.1198E21                          0.38%
23           2.5852E22           2.5759E22                          0.36%
24           6.2045E23           6.1830E23                          0.35%
25           1.5511E25           1.5460E25                          0.33%
26           4.0329E26           4.0200E26                          0.32%
27           1.0889E28           1.0855E28                          0.31%
28           3.0489E29           3.0398E29                          0.30%
29           8.8418E30           8.8164E30                          0.29%
30           2.6525E32           2.6452E32                          0.28%
31           8.2228E33           8.2008E33                          0.27%
32           2.6313E35           2.6245E35                          0.26%
33           8.6833E36           8.6614E36                          0.25%
34           2.9523E38           2.9451E38                          0.24%
35           1.0333E40           1.0309E40                          0.24%
36           3.7199E41           3.7113E41                          0.23%
37           1.3764E43           1.3733E43                          0.22%
38           5.2302E44           5.2188E44                          0.22%
39           2.0398E46           2.0354E46                          0.21%
40           8.1592E47           8.1422E47                          0.21%
41           3.3453E49           3.3385E49                          0.20%
42           1.4050E51           1.4022E51                          0.20%
43           6.0415E52           6.0298E52                          0.19%
44           2.6583E54           2.6532E54                          0.19%
45           1.1962E56           1.1940E56                          0.19%
46           5.5026E57           5.4927E57                          0.18%
47           2.5862E59           2.5817E59                          0.18%
48           1.2414E61           1.2392E61                          0.17%
49           6.0828E62           6.0725E62                          0.17%
50           3.0414E64           3.0363E64                          0.17%
51           1.5511E66           1.5486E66                          0.16%
52           8.0658E67           8.0529E67                          0.16%
53           4.2749E69           4.2682E69                          0.16%
54           2.3084E71           2.3049E71                          0.15%
55           1.2696E73           1.2677E73                          0.15%
56           7.1100E74           7.0994E74                          0.15%
57           4.0527E76           4.0468E76                          0.15%
58           2.3506E78           2.3472E78                          0.14%
59           1.3868E80           1.3849E80                          0.14%
60           8.3210E81           8.3094E81                          0.14%
61           5.0758E83           5.0689E83                          0.14%
62           3.1470E85           3.1428E85                          0.13%
63           1.9826E87           1.9800E87                          0.13%
64           1.2689E89           1.2672E89                          0.13%
65           8.2477E90           8.2371E90                          0.13%
66           5.4434E92           5.4366E92                          0.13%
67           3.6471E94           3.6426E94                          0.12%
68           2.4800E96           2.4770E96                          0.12%
69           1.7112E98           1.7092E98                          0.12%
70           1.1979E100           1.1964E100                          0.12%
71           8.5048E101           8.4948E101                          0.12%
72           6.1234E103           6.1164E103                          0.12%
73           4.4701E105           4.4650E105                          0.11%
74           3.3079E107           3.3042E107                          0.11%
75           2.4809E109           2.4782E109                          0.11%
76           1.8855E111           1.8834E111                          0.11%
77           1.4518E113           1.4503E113                          0.11%
78           1.1324E115           1.1312E115                          0.11%
79           8.9462E116           8.9368E116                          0.11%
80           7.1569E118           7.1495E118                          0.10%
81           5.7971E120           5.7912E120                          0.10%
82           4.7536E122           4.7488E122                          0.10%
83           3.9455E124           3.9416E124                          0.10%
84           3.3142E126           3.3110E126                          0.10%
85           2.8171E128           2.8143E128                          0.10%
86           2.4227E130           2.4204E130                          0.10%
87           2.1078E132           2.1057E132                          0.10%
88           1.8548E134           1.8531E134                          0.09%
89           1.6508E136           1.6493E136                          0.09%
90           1.4857E138           1.4843E138                          0.09%
91           1.3520E140           1.3508E140                          0.09%
92           1.2438E142           1.2427E142                          0.09%
93           1.1568E144           1.1557E144                          0.09%
94           1.0874E146           1.0864E146                          0.09%
95           1.0330E148           1.0321E148                          0.09%
96           9.9168E149           9.9082E149                          0.09%
97           9.6193E151           9.6110E151                          0.09%
98           9.4269E153           9.4189E153                          0.08%
99           9.3326E155           9.3248E155                          0.08%
100           9.3326E157           9.3248E157                          0.08%
101           9.4259E159           9.4182E159                          0.08%
102           9.6145E161           9.6066E161                          0.08%
103           9.9029E163           9.8949E163                          0.08%
104           1.0299E166           1.0291E166                          0.08%
105           1.0814E168           1.0805E168                          0.08%
106           1.1463E170           1.1454E170                          0.08%
107           1.2265E172           1.2256E172                          0.08%
108           1.3246E174           1.3236E174                          0.08%
109           1.4439E176           1.4428E176                          0.08%
110           1.5882E178           1.5870E178                          0.08%
111           1.7630E180           1.7616E180                          0.08%
112           1.9745E182           1.9730E182                          0.07%
113           2.2312E184           2.2295E184                          0.07%
114           2.5436E186           2.5417E186                          0.07%
115           2.9251E188           2.9230E188                          0.07%
116           3.3931E190           3.3907E190                          0.07%
117           3.9699E192           3.9671E192                          0.07%
118           4.6845E194           4.6812E194                          0.07%
119           5.5746E196           5.5707E196                          0.07%
120           6.6895E198           6.6849E198                          0.07%
121           8.0943E200           8.0887E200                          0.07%
122           9.8750E202           9.8683E202                          0.07%
123           1.2146E205           1.2138E205                          0.07%
124           1.5061E207           1.5051E207                          0.07%
125           1.8827E209           1.8814E209                          0.07%
126           2.3722E211           2.3706E211                          0.07%
127           3.0127E213           3.0107E213                          0.07%
128           3.8562E215           3.8537E215                          0.07%
129           4.9745E217           4.9713E217                          0.06%
130           6.4669E219           6.4627E219                          0.06%
131           8.4716E221           8.4662E221                          0.06%
132           1.1182E224           1.1175E224                          0.06%
133           1.4873E226           1.4863E226                          0.06%
134           1.9929E228           1.9917E228                          0.06%
135           2.6905E230           2.6888E230                          0.06%
136           3.6590E232           3.6568E232                          0.06%
137           5.0129E234           5.0098E234                          0.06%
138           6.9178E236           6.9136E236                          0.06%
139           9.6157E238           9.6100E238                          0.06%
140           1.3462E241           1.3454E241                          0.06%
141           1.8981E243           1.8970E243                          0.06%
142           2.6954E245           2.6938E245                          0.06%
143           3.8544E247           3.8521E247                          0.06%
144           5.5503E249           5.5471E249                          0.06%
145           8.0479E251           8.0433E251                          0.06%
146           1.1750E254           1.1743E254                          0.06%
147           1.7272E256           1.7263E256                          0.06%
148           2.5563E258           2.5549E258                          0.06%
149           3.8089E260           3.8068E260                          0.06%
150           5.7134E262           5.7102E262                          0.06%
151           8.6272E264           8.6224E264                          0.06%
152           1.3113E267           1.3106E267                          0.05%
153           2.0063E269           2.0053E269                          0.05%
154           3.0898E271           3.0881E271                          0.05%
155           4.7891E273           4.7866E273                          0.05%
156           7.4711E275           7.4671E275                          0.05%
157           1.1730E278           1.1723E278                          0.05%
158           1.8533E280           1.8523E280                          0.05%
159           2.9467E282           2.9452E282                          0.05%
160           4.7147E284           4.7123E284                          0.05%
161           7.5907E286           7.5868E286                          0.05%
162           1.2297E289           1.2291E289                          0.05%
163           2.0044E291           2.0034E291                          0.05%
164           3.2872E293           3.2855E293                          0.05%
165           5.4239E295           5.4212E295                          0.05%
166           9.0037E297           8.9992E297                          0.05%
167           1.5036E300           1.5029E300                          0.05%
168           2.5261E302           2.5248E302                          0.05%
169           4.2691E304           4.2670E304                          0.05%
170           7.2574E306           7.2539E306                          0.05%
171           ?           ?                          ?
172           ?           ?                          ?
173           ?           ?                          ?
174           ?           ?                          ?
175           ?           ?                          ?
176           ?           ?                          ?
177           ?           ?                          ?
178           ?           ?                          ?
179           ?           ?                          ?
180           ?           ?                          ?
181           ?           ?                          ?
182           ?           ?                          ?
183           ?           ?                          ?
184           ?           ?                          ?
185           ?           ?                          ?
186           ?           ?                          ?
187           ?           ?                          ?
188           ?           ?                          ?
189           ?           ?                          ?
190           ?           ?                          ?
191           ?           ?                          ?
192           ?           ?                          ?
193           ?           ?                          ?
194           ?           ?                          ?
195           ?           ?                          ?
196           ?           ?                          ?
197           ?           ?                          ?
198           ?           ?                          ?
199           ?           ?                          ?
200           ?           ?                          ?

------------------------------------------------------------------------------*/

/*----------------------Questions and Answers-----------------------------------

Question : How much higher than 100 can you go before you exceed the 
computational limit of your computer?
Answer   : 170

------------------------------------------------------------------------------*/