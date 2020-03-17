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


class Hero extends DungeonCharacter {
    private static Scanner keyboard = new Scanner(System.in);
    private double chanceToBlock;
    private int numTurns;
    private Special special;

    Hero(int hitPoints, int attackSpeed,
         double chanceToHit, int damageMin, int damageMax,
         double chanceToBlock, String callout, Special special) {
        super(null, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax, callout);
        this.chanceToBlock = chanceToBlock;
        this.special = special;
        readName();
    }

    private void readName() {//gets name from user
        System.out.print("Enter character name: ");
        name = keyboard.next();
    }

    void addTurn() {
        this.numTurns++;
    }


    private boolean defend() {//handles if the her blocks the attack
        return Math.random() <= chanceToBlock;

    }

    public void subtractHitPoints(int hitPoints) {//handles hero getting attacked
        if (defend()) {
            System.out.println(name + " BLOCKED the attack!");
        } else {
            super.subtractHitPoints(hitPoints);
        }
    }

    private void special(DungeonCharacter opponent) {
        this.special.doSpecial(this, opponent);
    }

    void battleChoices(DungeonCharacter opponent) {//handles heros turn choices each round
        int choice;

        numTurns = attackSpeed / opponent.getAttackSpeed();

        if (numTurns == 0)
            numTurns++;

        System.out.println("Number of turns this round is: " + numTurns);
        do {

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
            }

            numTurns--;
            if (numTurns > 0 && opponent.isAlive())
                System.out.println("Number of turns remaining is: " + numTurns);

        } while (numTurns > 0 && opponent.isAlive());
    }
}