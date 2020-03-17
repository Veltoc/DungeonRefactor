/**
 * Title: DungeonCharacter.java
 * <p>
 * Description: Abstract Base class for inheritance hierarchy for a
 * role playing game
 * <p>
 * class variables (all will be directly accessible from derived classes):
 * name (name of character)
 * hitPoints (points of damage a character can take before killed)
 * attackSpeed (how fast the character can attack)
 * chanceToHit (chance an attack will strike the opponent)
 * damageMin, damageMax (range of damage the character can inflict on
 * opponent)
 * <p>
 * class methods (all are directly accessible by derived classes):
 * DungeonCharacter(String name, int hitPoints, int attackSpeed,
 * double chanceToHit, int damageMin, int damageMax)
 * public String getName()
 * public int getHitPoints()
 * public int getAttackSpeed()
 * public void addHitPoints(int hitPoints)
 * public void subtractHitPoints(int hitPoints) -- this method will be
 * overridden
 * public boolean isAlive()
 * public void attack(DungeonCharacter opponent) -- this method will be
 * overridden
 * <p>
 * Copyright:    Copyright (c) 2001
 * Company:
 *
 * @author
 * @version 1.0
 */

abstract class DungeonCharacter {

    String name;
    int hitPoints;
    int attackSpeed;
    private double chanceToHit;
    private int damageMin;
    private int damageMax;
    private String callout;


    DungeonCharacter(String name, int hitPoints, int attackSpeed,
                     double chanceToHit, int damageMin, int damageMax, String callout) {

        this.name = name;
        this.hitPoints = hitPoints;
        this.attackSpeed = attackSpeed;
        this.chanceToHit = chanceToHit;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.callout = callout;

    }

    String getName() {
        return name;
    }

    int getHitPoints() {
        return hitPoints;
    }

    int getAttackSpeed() {
        return attackSpeed;
    }

    void addHitPoints(int hitPoints) {//adds hitpoints to the character
        if (hitPoints <= 0)
            System.out.println("Hitpoint amount must be positive.");
        else {
            this.hitPoints += hitPoints;
        }
    }

    void subtractHitPoints(int hitPoints) {//takes hitpoints and reports the damage and resulting health remaining
        if (hitPoints < 0)
            System.out.println("Hitpoint amount must be positive.");
        else if (hitPoints > 0) {
            this.hitPoints -= hitPoints;
            if (this.hitPoints < 0)
                this.hitPoints = 0;
            System.out.println(getName() + " hit " +
                    "for <" + hitPoints + "> points of damage.");
            System.out.println(getName() + " now has <" +
                    getHitPoints() + "> hit points remaining.");
            System.out.println();
        }

        if (this.hitPoints == 0)
            System.out.println(name + " has been killed :-(");


    }

    boolean isAlive() { //returns true if character is alive
        return (hitPoints > 0);
    }

    void attack(DungeonCharacter opponent) {//handles an attempt to attack, using chance to hit and damage.
        if (!callout.isEmpty())
            System.out.println(name + callout + opponent.getName() + ":");
        boolean canAttack;
        int damage;

        canAttack = Math.random() <= chanceToHit;

        if (canAttack) {
            damage = (int) (Math.random() * (damageMax - damageMin + 1))
                    + damageMin;
            opponent.subtractHitPoints(damage);


            System.out.println();
        } else {
            System.out.println(getName() + "'s attack on " + opponent.getName() +
                    " failed!");
            System.out.println();
        }
    }
}