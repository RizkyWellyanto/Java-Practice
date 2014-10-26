/* CS 1A Lab 8
 * Instructor Solution
 */

public class Solution
{
   public static void main(String[] args)
   {
      DateProfile app1 = new DateProfile('m', 'f', 2, 8, "Joe Somebody"),
      app2 = new DateProfile('m', 'f', 5, 5, "Steve Nobody"),
      app3 = new DateProfile('f', 'm', 1, 7, "Jane Peabody"),
      app4 = new DateProfile('f', 'm', 4, 9, "Helen Anybody");

      // compare everyone to Joe
      System.out.println( app1.getName());
      System.out.println(" " + app1.getName() + " fit: " + app1.fitValue(app1));
      System.out.println(" " + app2.getName() + " fit: " + app1.fitValue(app2));
      System.out.println(" " + app3.getName() + " fit: " + app1.fitValue(app3));
      System.out.println(" " + app4.getName() + " fit: " + app1.fitValue(app4));

      // compare everyone to Steve
      System.out.println(app2.getName());
      System.out.println(" " + app1.getName() + " fit: " + app2.fitValue(app1));
      System.out.println(" " + app2.getName() + " fit: " + app2.fitValue(app2));
      System.out.println(" " + app3.getName() + " fit: " + app2.fitValue(app3));
      System.out.println(" " + app4.getName() + " fit: " + app2.fitValue(app4));

      // compare everyone to Jane
      System.out.println(app3.getName());
      System.out.println(" " + app1.getName() + " fit: " + app3.fitValue(app1));
      System.out.println(" " + app2.getName() + " fit: " + app3.fitValue(app2));
      System.out.println(" " + app3.getName() + " fit: " + app3.fitValue(app3));
      System.out.println(" " + app4.getName() + " fit: " + app3.fitValue(app4));

      // compare everyone to Helen
      System.out.println(app4.getName());
      System.out.println(" " + app1.getName() + " fit: " + app4.fitValue(app1));
      System.out.println(" " + app2.getName() + " fit: " + app4.fitValue(app2));
      System.out.println(" " + app3.getName() + " fit: " + app4.fitValue(app3));
      System.out.println(" " + app4.getName() + " fit: " + app4.fitValue(app4));
      
      // prove a mutator
      if ( app4.setGender('Q') )
         System.out.println("Q accepted as gender");
      else
         System.out.println("Q reject4ed as gender");

   }
}

// class DateProfile ---------------------------------------------------
class DateProfile
{
   // private data
   private char gender;
   private char searchGender;
   private int romance;
   private int finance;
   private String name;

   //static constants;
   public static final int MIN_VAL = 1;
   public static final int MAX_VAL = 10;
   public static final int MIN_NAME_LENGTH = 3;
   public static final int MAX_NAME_LENGTH = 100;

   // constructors
   DateProfile()
   {
      setAll('F', 'M', 0, 0, "undefined applicant");
   }

   DateProfile(char gen, char srchGen, int rom, int fin, String nm)
   {
      setAll(gen, srchGen, rom, fin, nm);
   }

   // mutators
   void setAll(char gen, char srchGen, int rom, int fin, String nm)
   {
      if (!setGender(gen))
         setGender('F');
      if (!setSearchGender(srchGen))
         setSearchGender('M');
      if (!setRomance(rom))
         setRomance(0);
      if (!setFinance(fin))
         setFinance(0);
      if (!setName(nm))
         setName("applicant undefined");
   }


   boolean setGender(char gen)
   {
      if ( !validGender(gen) )
         return false;
      // always store gender as upper case
      gender = Character.toUpperCase(gen);
      return true;
   }

   boolean setSearchGender(char gen)
   {
      if ( !validGender(gen) )
         return false;
      searchGender = Character.toUpperCase(gen);
      return true;
   }

   boolean setRomance(int val)
   {
      if ( val < MIN_VAL || val > MAX_VAL )
         return false;
      romance = val;
      return true;
   }

   boolean setFinance(int val)
   {
      if ( val < MIN_VAL || val > MAX_VAL )
         return false;
      finance = val;
      return true;
   }

   boolean setName(String nm)
   {
      if (nm.length() < MIN_NAME_LENGTH || nm.length() > MAX_NAME_LENGTH )
         return false;
      name = nm; // this could have been name = new Name(nm);
      return true;
   }


   // accessors (ok to use brief style in these
   char getGender() { return gender; }
   char getSearchGender() { return searchGender; }
   int getRomance() { return romance; }
   int getFinance() { return finance; }
   String getName() { return name; }

   private boolean validGender(char gen)
   {
      char newGender = Character.toLowerCase(gen);

      if ( newGender != 'm' && newGender != 'f' )
         return false;
      else
         return true;
   }

   // helpers
   private double determineFinanceFit(DateProfile partner)
   {
      int myVal, partnersVal, diff;
      double fit;

      myVal = finance;
      partnersVal = partner.finance;
      diff = Math.abs(myVal - partnersVal);
      fit = MAX_VAL - 1 - diff; // 9 is largest and 0 is the smallest
      fit = fit / (double)(MAX_VAL-1); // now goes from 0.0 to 1.0

      return fit;
   }

   private double determineRomanceFit(DateProfile partner)
   {
      int myVal, partnersVal, diff;
      double fit;

      myVal = romance;
      partnersVal = partner.romance;
      diff = Math.abs(myVal - partnersVal);
      fit = MAX_VAL - 1 - diff; // 9 is largest and 0 is the smallest
      fit = fit / (double)(MAX_VAL-1); // now goes from 0.0 to 1.0

      return fit;
   }

   private double determineGenderFit(DateProfile partner)
   {
      if (searchGender != partner.gender)
         return 0.0;
      if (partner.searchGender != gender)
         return 0.0;
      return 1.0;
   }

   // computational
   public double fitValue(DateProfile partner)
   {
      return determineGenderFit(partner) * determineRomanceFit(partner)
      * determineFinanceFit(partner);
   }

}
/* ------------------ Output of Above ------------------------------

Joe Somebody
 Joe Somebody fit: 0.0
 Steve Nobody fit: 0.0
 Jane Peabody fit: 0.7901234567901234
 Helen Anybody fit: 0.691358024691358
Steve Nobody
 Joe Somebody fit: 0.0
 Steve Nobody fit: 0.0
 Jane Peabody fit: 0.4320987654320988
 Helen Anybody fit: 0.49382716049382713
Jane Peabody
 Joe Somebody fit: 0.7901234567901234
 Steve Nobody fit: 0.4320987654320988
 Jane Peabody fit: 0.0
 Helen Anybody fit: 0.0
Helen Anybody
 Joe Somebody fit: 0.691358024691358
 Steve Nobody fit: 0.49382716049382713
 Jane Peabody fit: 0.0
 Helen Anybody fit: 0.0
Q reject4ed as gender

------------------------------------------------------------------- */