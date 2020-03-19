/* DOCUMENTATION
# Dungeon Adventure
Extra credit(save) was done
Rooms looks like this:
╔═╗
║I
╚ ╝
where ' ' are doorways; ║, ═ being walls, and ╚,╝,╔,╗, being corners. Gives a better feel to the dungeon
- M - Multiple Items
- P - Pit
- I - Entrance (In)
- O - Exit (Out)
- V - Vision Potion
- H - Healing Potion
- § - OO Pillar
- E - Empty Room
- X - Monster
You can drink potions with 'drink' followed by 'V' or 'vision' for a vision potion and 'H' or 'health' for health potion
For example, 'drink health'. this works with any combination so long as the first letter h. if this project were to be expanded that would need to be more strict
You can move with 'move' followed by the direction using the first letter or the full name of the cardinal direction. For example, 'move north'.
You will only be able to move outside of combat.

work distribution:
Room: Mason for general requirements, cleaned up by Matthew
Hero: Mason then Matthew made the getvision to track potion use
Dungeon: Mason, altered by Matthew to adhere to what he changed with room as well as cleaning up code
DungeonAdventure: mason, Matthew cleaned up, redid move except for the contents case loop. Mathew also did the extra credit required for saving and added the extra monsters
Dungeon character: Matthew added 2 new monsters and 2 new heros
SpecialExplosion: Matthew, for a new hero Alchemist
SpecialQuickAttack: Matthew, for new hero Duelist
Attack: all Matthew
Tests:
DungeonAdventureTests: Mason
DungeonCharacterTests: Mason
DungeonTests: Mason
HeroTests: Mason
MonsterTests: Mason
RoomTests: Mason
AttackTest: Matthew
CreatorTest: Matthew
SpecialTest: Matthew

Additional specifications:
1. Matthew as part of dungeon refractor assignement
2. Matthew
3. Matthew
4. Matthew
5. Mason
6. Matthew

UML: Matthew

Instead of splitting workload by class, due to conflicting schedules Mason got the primary project running with the exception of most of the extras such as attack and extra characters. Matthew cleaned it up, fixed a couple missed bugs and completed the additional specifications as well as UML and showing output

Note on tests: Due to the randomizing nature of this project we are limited on tests. Furthermore our abstraction had to be broken to test what little we could, those cases are noted. THE UML DOES NOT REFLECT THIS
*/



package dungeon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DungeonAdventure//made public to be able to unit test
{
    private static Scanner keyboard = new Scanner(System.in);
    private static String[] heroOptions = {
            "Warrior", "Sorceress", "Thief", "Alchemist", "Duelist"
    };
    private static String[] monsterOptions = {
            "Gremlin", "Ogre", "Skeleton", "Slime", "Drow"
    };
    private static Dungeon dungeon;
    private static Hero theHero;
    
    public static void main(String[] args)
    {
        dungeon = null;
        
        // Game loop
        do {
        	// Loading a save
            System.out.print("Do you want to load a save? (y/n) ");
            if (keyboard.nextLine().toLowerCase().charAt(0) == 'y') {
                loadGame();
            }
            
            // Checking if loaded save
            if (dungeon == null) {
                theHero = chooseHero();
                introduction();
                dungeon = new Dungeon();
            }
            
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
                } else if (action.equals("admin")) {//to allow for testing, and by project requirements
                    theHero.addHealthPotion();
                    theHero.addVisionPotion();
                    System.out.println(dungeon.toString());
                } else if (action.equals("save")) {
                    saveGame();
                } else if (action.equals("q")){
                    break;
                }else {
                    System.out.println("Invalid action!");
                }
                
                keyboard.nextLine(); // Clearing buffer
            }
            
            System.out.println(dungeon.printDungeon(-1));
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
                "You will only be able to move outside of combat.\n\n" +
                "Additionally, you can save your progress with 'save'");
        
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
    
    public static Monster generateMonster()//made public to test
    {
        int choice = (int) (Math.random() * monsterOptions.length);
        return CharacterCreator.createMonster(monsterOptions[choice]);
    }
    
    public static boolean playAgain()//made public to test
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
            if (Math.random() <= .1) {
                System.out.println("You found a health potion of the monsters corpse");
                theHero.addHealthPotion();
            }
        }
    }
    
    private static void saveGame()
    {
        try {
            File file = new File(theHero.getName() + "_save.dgn");
            
            // Writing to file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(dungeon);
            out.writeObject(theHero);
            out.close();
            
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }
    
    private static void loadGame()
    {
        // Getting filename
        System.out.print("Character Name: ");
        String name = keyboard.nextLine();
        
        try {
            File file = new File(name + "_save.dgn");
            
            // Reading file
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            dungeon = ((Dungeon) input.readObject());
            theHero = ((Hero) input.readObject());
            input.close();
        } catch (FileNotFoundException e) {
            System.out.printf("Couldn't find a save for %s. Starting default game.%n", name);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
