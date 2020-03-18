package dungeon;
import java.util.Scanner;

class DungeonAdventure
{
    private static Scanner keyboard = new Scanner(System.in);
    private static String[] heroOptions = {
            "Warrior", "Sorceress", "Thief"
    };
    private static String[] monsterOptions = {
            "Gremlin", "Ogre", "Skeleton"
    };
    private static Dungeon dungeon;
    private static Hero theHero;
    
    public static void main(String[] args)
    {
        do {
            theHero = chooseHero();
            introduction();
            dungeon = new Dungeon();
            
            // Displaying initial view
            System.out.println(dungeon.printDungeon(theHero.getVision()));
            
            // Game loop
            boolean quit = false;
            while (theHero.getHitPoints() > 0 && !quit) {
                // Resetting vision
                theHero.resetVision();
                
                // Getting action
                System.out.print("What will you do? ");
                String action = keyboard.next().toLowerCase();
                
                // Handling action
                if (action.equals("drink")) {
                    drink(keyboard.next().toLowerCase());
                } else if (action.equals("move")) {
                    quit = move(keyboard.next().toLowerCase());
                } else if (action.equals("inv")) {
                	System.out.println(theHero);
                } else if(action.equals("admin")){//to allow for testing, and by project requirements
                    theHero.addHealthPotion();
                    theHero.addVisionPotion();
                    System.out.println(dungeon.printDungeon(-1));
                } else {
                	System.out.println("Invalid action!");
                }
                
                keyboard.nextLine(); // Clearing buffer
            }
        } while(playAgain());
    }// end main method
    
    private static boolean drink(String potion)
    {
        // Using a vision potion
        if(potion.charAt(0) == 'v') {
            theHero.drinkVision();
            System.out.println(dungeon.printDungeon(theHero.getVision()));
        } else if(potion.charAt(0) == 'h') {
            theHero.drinkHealth();
        } else {
        	System.out.println("not a valid potion");
        }
        
        return false;
    }
    
    public static boolean move(String dir)
    {
        // Checking for invalid direction
        if (!(dir.charAt(0) == 'n'
              || dir.charAt(0) == 's'
              || dir.charAt(0) == 'e'
              || dir.charAt(0) == 'w'))
        {
            System.out.println("Invalid location!");
            return false;
        }
        
        // Entering room
        Room currentRoom = dungeon.move(dir);
        System.out.println(dungeon.printDungeon(theHero.getVision()));
        
        // Displaying contents
        for (char item : currentRoom.getContents()) {
            switch (item){
                case 'P':
                    System.out.println("Fell in a pit!");
                    theHero.subtractHitPoints((int) (Math.random() * 20) + 1);
                    break;
                case 'V':
                    theHero.addVisionPotion();
                    System.out.println("Found a vision potion!");
                    break;
                case 'H':
                    theHero.addHealthPotion();
                    System.out.println("Found a health potion!");
                    break;
                case '§':
                    theHero.addPillar();
                    System.out.println("Found one of the pillars!");
                    break;
                case 'X':
                    battle(generateMonster());
                    break;
                case 'O':
                    return exit();
            }
        }
        
        if (theHero.getHitPoints() > 0) {
            return false;
        } else {
            System.out.println("You've been defeated...");
            return true;
        }
    }
    
    public static boolean exit()
    {
        int numPillars = theHero.getPillars();
        
        if (numPillars == 4) {
            System.out.println("Congratulations! You collected the artifacts and joined the guild!");
        } else {
            System.out.println("You have collected " + Integer.toString(numPillars) + " of 4 pillars.");
            System.out.print("Are you sure you want to exit? (y/n) ");
            
            if (keyboard.next().toLowerCase().charAt(0) == 'n') {
                return false;
            } else {
                System.out.println("You made it out with your life, but you failed to collect the artifacts.");
                System.out.println("You may try again, but you must collect the artifacts to join the guild.");
            }
        }
        
        return true;
    }
    
    public static void introduction()
    {
        System.out.println("·▄▄▄▄  ▄• ▄▌ ▐ ▄  ▄▄ • ▄▄▄ .       ▐ ▄ ");
        System.out.println("██▪ ██ █▪██▌•█▌▐█▐█ ▀ ▪▀▄.▀·▪     •█▌▐█");
        System.out.println("▐█· ▐█▌█▌▐█▌▐█▐▐▌▄█ ▀█▄▐▀▀▪▄ ▄█▀▄ ▐█▐▐▌");
        System.out.println("██. ██ ▐█▄█▌██▐█▌▐█▄▪▐█▐█▄▄▌▐█▌.▐▌██▐█▌");
        System.out.println("▀▀▀▀▀•  ▀▀▀ ▀▀ █▪·▀▀▀▀  ▀▀▀  ▀█▄▀▪▀▀ █▪");
        System.out.println("__________________________________________");
        System.out.println("Welcome " + theHero.getName() + " \n" +
                "Your first quest to enter the guild is to enter the nearby dungeon and recover some lost artifacts '§'.\n" +
                "You may even find some potions in your quest. Health potions 'H' will recover some health,\n" +
                "while vision potions 'V' will allow you to see the surrounding rooms. Use them sparingly, as they don't last long.\n" +
                "Keep an eye out for pits 'P' and monsters 'X' that will hinder your progress.\n" +
                "The dungeon itself will be a series of rooms, and to complete your quest,\n" +
                "you must recover the four artifacts and make your way to the exit 'O'.\n" +
                "You can drink potions with 'drink' followed by 'V' or 'vision' for a vision potion and 'H' or 'health' for health potion\n" +
                "For example, 'drink health'.\nYou can move with 'move' followed by the direction.\n" +
                "You can use the first letter or the full name of the cardinal direction. For example, 'move north'.\n" +
                "You will only be able to move outside of combat.");
        
        System.out.print("Press RETURN when you are ready to begin");
        keyboard.nextLine();
    }
    
    private static Hero chooseHero()
    {
    	// Displaying options
        System.out.println("Choose a hero:");
        for (int i = 0; i < heroOptions.length; i++) {
            System.out.printf("%d. %s%n", i + 1, heroOptions[i]);
        }
        
        // Getting user choice
        int choice = keyboard.nextInt() - 1;
        keyboard.nextLine();
        if (choice < 0 || choice >= heroOptions.length) {
            System.out.printf("Invalid option! Choosing %s.%n", heroOptions[0]);
            choice = 0;
        }
        
        return CharacterCreator.createHero(heroOptions[choice]);
    }
    
    private static Monster generateMonster()
    {
        int choice = (int) (Math.random() * monsterOptions.length);
        return CharacterCreator.createMonster(monsterOptions[choice]);
    }
    
    private static boolean playAgain()
    {
        System.out.print("Play again? (y/n)");
        
        String again = keyboard.nextLine().toLowerCase();
        return again.charAt(0) == 'y';
    }
    
    private static void battle(Monster theMonster)
    {
        String pause = "p";
        System.out.println(theHero.getName() + " battles " + theMonster.getName());
        System.out.println("---------------------------------------------");
        
        // Doing battle
        while (theHero.isAlive() && theMonster.isAlive() && !pause.equalsIgnoreCase("q")) {
            theHero.battleChoices(theMonster);
            
            if (theMonster.isAlive()) {
                theMonster.attack(theHero);
            }
        }
        
        // Displaying results
        if (theHero.isAlive()) {
            System.out.println(theHero.getName() + " was victorious!");
        }
    }
}
