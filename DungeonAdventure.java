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

/*
 * This class is the driver file for the Heroes and Monsters project. It will do
 * the following:
 *
 * 1. Allow the user to choose a hero 2. Randomly select a monster 3. Allow the
 * hero to battle the monster
 *
 * Once a battle concludes, the user has the option of repeating the above
 *Hero theHero;
        Monster theMonster;

        do {
            theHero = chooseHero();
            theMonster = generateMonster();
            battle(theHero, theMonster);

        } while (playAgain());
 */

import java.util.Scanner;

class DungeonAdventure {
    private static Scanner keyboard = new Scanner(System.in);
    private static Hero theHero;
    private static String[] heroOptions = {
            "Warrior", "Sorceress", "Thief"
    };
    private static String[] monsterOptions = {
            "Gremlin", "Ogre", "Skeleton"
    };
    private static Dungeon dungeon;

    public static void main(String[] args) {
        do {
            theHero = chooseHero();
            introduction();
            dungeon = new Dungeon();

            Room currentRoom = null;
            boolean vision = false;
            boolean quit = false;
            do {
                currentRoom = dungeon.getCurrentRoom();
                if(vision){
                    System.out.println(dungeon.printDungeon(1));
                    vision = false;
                }else System.out.println(currentRoom);//would love to be able to clear
                System.out.println("What will you do?");
                String action = keyboard.nextLine();
                if(action.indexOf("drink")==0||action.indexOf("Drink")==0) {
                    vision = drink(action.substring(6,7));
                }else if(action.indexOf("move")==0||action.indexOf("Move")==0) {
                    quit = move(action.substring(5, 6));
                }else if(action.equalsIgnoreCase("Inv"))System.out.println(theHero);
                else if(action.equals("aDmin")){//to allow for testing, and by project requirements
                    theHero.addHealingPotion();
                    theHero.addVisionPotion();
                    System.out.println(dungeon.printDungeon(-1));
                }
                else System.out.println("Not a valid action");
            } while (theHero.hitPoints>0&&!quit);
            System.out.println(dungeon.printDungeon(-1));
        }while(playAgain());
    }// end main method
    private static boolean drink(String input){
        if(input.equalsIgnoreCase("V")) {
            if(theHero.drinkVision()){
                return true;
            }else System.out.println("Out of vision potions");
        }
        else if(input.equalsIgnoreCase("H")) {
            if(!theHero.drinkHealth()) System.out.println("Out of healing potions");
        }
        else System.out.println("not a valid potion");
        return false;
    }
    public static boolean move(String input){
        if (input.equalsIgnoreCase("N") ||
                input.equalsIgnoreCase("E") ||
                input.equalsIgnoreCase("S") ||
                input.equalsIgnoreCase("W")){
            Room currentRoom = dungeon.move(input);
            String[] contents = currentRoom.enter();
            for (String item : contents) {
                switch (item){
                    case "E": break;//to save runtime on empty rooms
                    case "V": theHero.addVisionPotion();
                    System.out.println("found vision potion!");
                    break;
                    case "H": theHero.addHealingPotion();
                        System.out.println("found health potion");
                        break;
                    case "§": theHero.addPillar();
                        System.out.println("found a pillar!");
                        break;
                    case "P": theHero.subtractHitPoints((int)(Math.random()*20)+1);
                    break;
                    case "X": battle(generateMonster());
                        if(theHero.getHitPoints()<=0){
                            return true;
                        }
                        break;
                    case "O": return exit();
                }
            }
        }else System.out.println("not a valid direction");
        return false;
    }
    public static boolean exit(){
        while(true){
            System.out.println("You have collected "+theHero.getPillars()+" of 4 pillars, do you want to exit? (y/n)");
            String temp = keyboard.nextLine();
            if(temp.equalsIgnoreCase("y")){
                if(theHero.getPillars()==4) System.out.println("Congratulations, you beat the dungeon!");
                else System.out.println("You made it out with your life, but you failed to collect the stolen artifacts. Better luck next time.");
                break;
            }else if(temp.equalsIgnoreCase("n")){
                return false;
            }
        }
        return true;
    }
    public static void introduction(){
        System.out.println("·▄▄▄▄  ▄• ▄▌ ▐ ▄  ▄▄ • ▄▄▄ .       ▐ ▄ ");
        System.out.println("██▪ ██ █▪██▌•█▌▐█▐█ ▀ ▪▀▄.▀·▪     •█▌▐█");
        System.out.println("▐█· ▐█▌█▌▐█▌▐█▐▐▌▄█ ▀█▄▐▀▀▪▄ ▄█▀▄ ▐█▐▐▌");
        System.out.println("██. ██ ▐█▄█▌██▐█▌▐█▄▪▐█▐█▄▄▌▐█▌.▐▌██▐█▌");
        System.out.println("▀▀▀▀▀•  ▀▀▀ ▀▀ █▪·▀▀▀▀  ▀▀▀  ▀█▄▀▪▀▀ █▪");
        System.out.println("__________________________________________");
        System.out.println("Welcome "+theHero.getName()+" \n" +
                "Your first quest to enter the guild is to enter the nearby dungeon and recover some lost artifacts\n" +
                "These artifacts will look like §, can't miss them\n" +
                "You may even find some health potions like this one 'H' which will recover some lost health if you get into a pinch\n" +
                "Not to forget vision potions 'V' which will let you see the surrounding rooms, but use it sparingly as it doesn't last\n " +
                "also keep an eye out for pits 'P' and monsters 'X' that will try to stop you but im sure you got this" +
                "Ah I almost forgot, the dungeon will be a series of rooms, make your way around the available doorways using 'N', 'S', 'E', 'W'\n" +
                "In order to complete the test you must recover all 4 artifacts and make your way to the exit 'O'" +
                "you can drink potions with just 'drink' followed by 'V' or 'vision' and 'H' or 'health' for health potion" +
                "for example, 'drink health' works. To move specify 'move' followed by the direction, same rules as potions so 'N' or 'North' work" +
                "Note: you can only move outside of combat");
    }
    /*-------------------------------------------------------------------
    chooseHero allows the user to select a hero, creates that hero, and
    returns it.  It utilizes a polymorphic reference (Hero) to accomplish
    this task
    ---------------------------------------------------------------------*/
    private static Hero chooseHero() {
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
    }// end chooseHero method

    /*-------------------------------------------------------------------
    generateMonster randomly selects a Monster and returns it.  It utilizes
    a polymorphic reference (Monster) to accomplish this task.
    ---------------------------------------------------------------------*/
    private static Monster generateMonster() {
        int choice = (int) (Math.random() * monsterOptions.length);
        return CharacterCreator.createMonster(monsterOptions[choice]);
    }// end generateMonster method

    /*-------------------------------------------------------------------
    playAgain allows gets choice from user to play another game.  It returns
    true if the user chooses to continue, false otherwise.
    ---------------------------------------------------------------------*/
    private static boolean playAgain() {

        System.out.println("Play again (y/n)?");
        String again = keyboard.nextLine();

        return (again.equalsIgnoreCase("y"));
    }// end playAgain method

    /*-------------------------------------------------------------------
    battle is the actual combat portion of the game.  It requires a Hero
    and a Monster to be passed in.  Battle occurs in rounds.  The Hero
    goes first, then the Monster.  At the conclusion of each round, the
    user has the option of quitting.
    ---------------------------------------------------------------------*/
    private static void battle(Monster theMonster) {
        String pause = "p";
        System.out.println(theHero.getName() + " battles " + theMonster.getName());
        System.out.println("---------------------------------------------");

        // do battle
        while (theHero.isAlive() && theMonster.isAlive() && !pause.equalsIgnoreCase("q")) {
            // hero goes first
            theHero.battleChoices(theMonster);

            // monster's turn (provided it's still alive!)
            if (theMonster.isAlive()) {
                theMonster.attack(theHero);

                // let the player bail out if desired
                //System.out.print("\n-->q to quit, anything else to continue: ");
                //pause = keyboard.nextLine();
            }

        } // end battle loop

        if (!theMonster.isAlive())
            System.out.println(theHero.getName() + " was victorious!");
        else if (!theHero.isAlive())
            System.out.println(theHero.getName() + " was defeated :-(");
        //else// both are alive so user quit the game
           // System.out.println("Quitters never win ;-)");

    }// end battle method

}// end Dungeon class