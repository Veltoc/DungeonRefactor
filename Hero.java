import java.util.Scanner;

/**
 * Title: Hero.java
 * <p>
 * Description: Abstract base class for a hierarchy of heroes.  It is derived
 * from DungeonCharacter.  A Hero has battle choices: regular attack and a
 * special skill which is defined by the classes derived from Hero.
 * <p>
 * class variables (all are directly accessible from derived classes):
 * chanceToBlock -- a hero has a chance to block an opponents attack
 * numTurns -- if a hero is faster than opponent, their is a possibility
 * for more than one attack per round of battle
 * <p>
 * class methods (all are public):
 * public Hero(String name, int hitPoints, int attackSpeed,
 * double chanceToHit, int damageMin, int damageMax,
 * double chanceToBlock)
 * public void readName()
 * public boolean defend()
 * public void subtractHitPoints(int hitPoints)
 * public void battleChoices(DungeonCharacter opponent)
 * <p>
 * Copyright:    Copyright (c) 2001
 * Company:
 *
 * @author
 * @version 1.0
 */


class  Hero extends DungeonCharacter {
    private double chanceToBlock;
    private int numTurns;
    private Special special;
    private static Scanner keyboard = new Scanner(System.in);
    private int healPotions;
    private int visionPotions;
    private int pillars;


    //calls base constructor and gets name of hero from user
    Hero(int hitPoints, int attackSpeed,
         double chanceToHit, int damageMin, int damageMax,
         double chanceToBlock, String callout, Special special) {
        super(null, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax, callout);
        this.chanceToBlock = chanceToBlock;
        this.special = special;
        healPotions = 0;
        visionPotions = 0;
        pillars = 0;
        readName();
    }
   public void addHealingPotion(){
        healPotions++;
   }
   public boolean drinkHealth(){
        if(healPotions>0){
            healPotions--;
            addHitPoints((int)(Math.random()*11)+5);
            return true;
        }
        return false;
   }
   public boolean drinkVision(){
       if(visionPotions>0){
           visionPotions--;
           return true;
       }
       return false;
   }
   public void addVisionPotion(){
        visionPotions++;
   }
   public void addPillar(){
        pillars++;
   }

    public int getPillars() {
        return pillars;
    }

    /*-------------------------------------------------------
        readName obtains a name for the hero from the user

        Receives: nothing
        Returns: nothing

        This method calls: nothing
        This method is called by: hero constructor
        ---------------------------------------------------------*/
    private void readName() {
        System.out.print("Enter character name: ");
        name = keyboard.next();
    }//end readName method

    void addTurn() {
        this.numTurns++;
    }

    /*-------------------------------------------------------
    defend determines if hero blocks attack

    Receives: nothing
    Returns: true if attack is blocked, false otherwise

    This method calls: Math.random()
    This method is called by: subtractHitPoints()
    ---------------------------------------------------------*/
    private boolean defend() {
        return Math.random() <= chanceToBlock;

    }//end defend method

    /*-------------------------------------------------------
    subtractHitPoints checks to see if hero blocked attack, if so a message
    is displayed, otherwise base version of this method is invoked to
    perform the subtraction operation.  This method overrides the method
    inherited from DungeonCharacter promoting polymorphic behavior

    Receives: hit points to subtract
    Returns: nothing

    This method calls: defend() or base version of method
    This method is called by: attack() from base class
    ---------------------------------------------------------*/
    public void subtractHitPoints(int hitPoints) {
        if (defend()) {
            System.out.println(name + " BLOCKED the attack!");
        } else {
            super.subtractHitPoints(hitPoints);
        }


    }//end method

    public void setSpecial(Special special) {
        this.special = special;
    }

    private void special(DungeonCharacter opponent) {
        this.special.doSpecial(this, opponent);
    }

    /*-------------------------------------------------------
    battleChoices will be overridden in derived classes.  It computes the
    number of turns a hero will get per round based on the opponent that is
    being fought.  The number of turns is reported to the user.  This stuff might
    go better in another method that is invoked from this one...

    Receives: opponent
    Returns: nothing

    This method calls: getAttackSpeed()
    This method is called by: external sources
    ---------------------------------------------------------*/
    void battleChoices(DungeonCharacter opponent) {
        int choice;

        numTurns = attackSpeed / opponent.getAttackSpeed();

        if (numTurns == 0)
            numTurns++;

        System.out.println("Number of turns this round is: " + numTurns);
        do //(V) refactor #2, pulled up and generalized for all hero subclasses
        {

            System.out.println("1. Attack Opponent");
            System.out.println("2. " + this.special.getName());
            System.out.print("Choose an option: ");
            choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    attack(opponent);
                    break;
                case 2:
                    special(opponent);
                    break;
                default:
                    System.out.println("invalid choice!");
            }//end switch

            numTurns--;
            if (numTurns > 0 && opponent.isAlive())
                System.out.println("Number of turns remaining is: " + numTurns);

        } while (numTurns > 0 && opponent.isAlive());

    }//end battleChoices

    @Override
    public String toString() {
        return "Name: "+name
                +"\nHitpoints: "+hitPoints+
                "\nTotal Healing Potions: "+healPotions+
                "\nTotal Vision Potions: "+visionPotions+
                "\nTotal Pillars of OO Found: "+pillars;
    }
}//end Hero class