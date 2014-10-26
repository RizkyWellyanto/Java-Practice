package FHsd;

import java.text.*;
import java.util.*;

// ------------------------------------------------------
public class Foothill
{
   // ------- main --------------
   public static void main(String[] args) throws Exception
   {
      FHsdTree scene_tree = new FHsdTree();
      FHsdTreeNode tn;
      
      System.out.println("Starting tree empty? " + scene_tree.empty() + "\n");
      // create a scene in a room
      tn = scene_tree.addChild(null, "room");
      
      // add three objects to the scene tree
      scene_tree.addChild(tn, "Lily the canine");
      scene_tree.addChild(tn, "Miguel the human");
      scene_tree.addChild(tn, "table");
      // add some parts to Miguel
      tn = scene_tree.find("Miguel the human");
      
      // Miguel's left arm
      tn = scene_tree.addChild(tn, "torso");
      tn = scene_tree.addChild(tn, "left arm");
      tn = scene_tree.addChild(tn, "left hand");
      scene_tree.addChild(tn, "thumb");
      scene_tree.addChild(tn, "index finger");
      scene_tree.addChild(tn, "middle finger");
      scene_tree.addChild(tn, "ring finger");
      scene_tree.addChild(tn, "pinky");
      
      // Miguel's right arm
      tn = scene_tree.find("Miguel the human");
      tn = scene_tree.find(tn, "torso", 0);
      tn = scene_tree.addChild(tn, "right arm");
      tn = scene_tree.addChild(tn, "right hand");
      scene_tree.addChild(tn, "thumb");
      scene_tree.addChild(tn, "index finger");
      scene_tree.addChild(tn, "middle finger");
      scene_tree.addChild(tn, "ring finger");
      scene_tree.addChild(tn, "pinky");
      
      // add some parts to Lily
      tn = scene_tree.find("Lily the canine");
      tn = scene_tree.addChild(tn, "torso");
      scene_tree.addChild(tn, "right front paw");
      scene_tree.addChild(tn, "left front paw");
      scene_tree.addChild(tn, "right rear paw");
      scene_tree.addChild(tn, "left rear paw");
      scene_tree.addChild(tn, "spare mutant paw");
      scene_tree.addChild(tn, "wagging tail");
      
      // add some parts to table
      tn = scene_tree.find("table");
      scene_tree.addChild(tn, "north east leg");
      scene_tree.addChild(tn, "north west leg");
      scene_tree.addChild(tn, "south east leg");
      scene_tree.addChild(tn, "south west leg");
      
      System.out.println("\n------------ Loaded Tree ----------------- \n");
      scene_tree.display();
      
      scene_tree.remove("spare mutant paw");
      scene_tree.remove("Miguel the human");
      scene_tree.remove("an imagined higgs boson");
      
      System.out
            .println("\n------------ Virtual (soft) Tree --------------- \n");
      scene_tree.display();
      
      System.out
            .println("\n----------- Physical (hard) Display ------------- \n");
      scene_tree.displayPhysical();
      
      System.out
            .println("------- Testing Sizes (compare with above) -------- \n");
      System.out.println("virtual (soft) size: " + scene_tree.size());
      System.out.println("physiical (hard) size: " + scene_tree.sizePhysical());
      
      System.out.println("------------ Collecting Garbage ---------------- \n");
      System.out.println("found soft-deleted nodes? "
            + scene_tree.collectGarbage());
      System.out.println("immediate collect again? "
            + scene_tree.collectGarbage());
      
      System.out
            .println("--------- Hard Display after garb col ------------ \n");
      
      scene_tree.displayPhysical();
      
      System.out.println("Semi-deleted tree empty? " + scene_tree.empty()
            + "\n");
      scene_tree.remove("room");
      System.out.println("Completely-deleted tree empty? " + scene_tree.empty()
            + "\n");
      
      scene_tree.display();
   }
}
