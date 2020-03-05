/**
 * Title: Dungeon.java
 * <p>
 * Description: Driver file for Heroes and Monsters project
 * <p>
 * Copyright: Copyright (c) 2001 Company: Code Dogs Inc. I.M. Knurdy
 * <p>
 * History: 11/4/2001: Wrote program --created DungeonCharacter class --created
 * Hero class --created Monster class --had Hero battle Monster --fixed attack
 * quirks (dead monster can no longer attack) --made Hero and Monster abstract
 * --created Warrior --created Ogre --made Warrior and Ogre battle --added
 * battleChoices to Hero --added special skill to Warrior --made Warrior and
 * Ogre battle --created Sorceress --created Thief --created Skeleton --created
 * Gremlin --added game play features to Dungeon.java (this file) 11/27/2001:
 * Finished documenting program version 1.0
 */

/*1. Allow the user to choose a hero 2. Randomly select a monster 3. Allow the
 * hero to battle the monster 4. allow user to repeat once battle has concluded
 */

import java.util.Scanner;

class Dungeon {
    private static Scanner keyboard = new Scanner(System.in);
    private static String[] heroOptions = {
            "Warrior", "Sorceress", "Thief"
    };
    private static String[] monsterOptions = {
            "Gremlin", "Ogre", "Skeleton"
    };

    public static void main(String[] args) {

        Hero theHero;
        Monster theMonster;

        do {
            theHero = chooseHero();
            theMonster = generateMonster();
            battle(theHero, theMonster);

        } while (playAgain());

    }
    private static Hero chooseHero() { //handles user selecting a hero
        int choice;

        System.out.println("Choose a hero:");
        for (int i = 0; i < heroOptions.length; i++) {
            System.out.printf("%d. %s%n", i + 1, heroOptions[i]);
        }

        choice = keyboard.nextInt() - 1;
        if (choice < 0 || choice >= heroOptions.length) {
            System.out.printf("Error! Invalid option! Giving %s%n", heroOptions[0]);
            choice = 0;
        }

        return CharacterCreator.createHero(heroOptions[choice]);
    }

    private static Monster generateMonster() {//generates a random monster
        int choice = (int) (Math.random() * monsterOptions.length);
        return CharacterCreator.createMonster(monsterOptions[choice]);
    }

    private static boolean playAgain() {//asks user if they want to play again returning true if they say y (yes)

        System.out.println("Play again (y/n)?");
        String again = keyboard.nextLine();

        return (again.equalsIgnoreCase("y"));
    }

    private static void battle(Hero theHero, Monster theMonster) {//handles the combat in rounds, asking if user wants to quit each round.
        String pause = "p";
        System.out.println(theHero.getName() + " battles " + theMonster.getName());
        System.out.println("---------------------------------------------");

        while (theHero.isAlive() && theMonster.isAlive() && !pause.equalsIgnoreCase("q")) {
            theHero.battleChoices(theMonster);

            if (theMonster.isAlive()) {
                theMonster.attack(theHero);

                System.out.print("\n-->q to quit, anything else to continue: ");
                pause = keyboard.nextLine();
            }
        }

        if (!theMonster.isAlive())
            System.out.println(theHero.getName() + " was victorious!");
        else if (!theHero.isAlive())
            System.out.println(theHero.getName() + " was defeated :-(");
        else
            System.out.println("Quitters never win ;-)");
    }
}