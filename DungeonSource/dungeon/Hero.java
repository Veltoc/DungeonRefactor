package dungeon;
import java.util.Scanner;

public class Hero extends DungeonCharacter//made public to unit test
{
    private static final long serialVersionUID = 1L;
    private static Scanner keyboard = new Scanner(System.in);
    
    private double chanceToBlock;
    private int numTurns;
    private Special special;
    
    private int healthPotions;
    private int visionPotions;
    private int pillarsFound;
    private int visionRadius;
    
    public Hero(int hitPoints, double chanceToBlock,
                String attackType, String warcry, Special special)
    {
        super(null, hitPoints, attackType, warcry);
        
        this.chanceToBlock = chanceToBlock;
        this.special = special;
        readName();
        
        this.healthPotions = 0;
        this.visionPotions = 0;
        this.pillarsFound = 0;
        this.visionRadius = 0;
    }
    
    @Override
    public String toString() {
        return String.format("Name: %s%nHealth Potions: %d%nVision Potions: %d%nPillars Found: %d",
            this.getName(), this.healthPotions, this.visionPotions, this.pillarsFound);
    }
    
    public void addHealthPotion()
    {
        this.healthPotions++;
    }
    
    public void drinkHealth()
    {
        if (this.healthPotions > 0){
            this.healthPotions--;
            
            int life = (int) (Math.random()*10) + 6;
            addHitPoints(life);
            
            System.out.printf("Healed %d hit points.%nYou have %d health potions left.%n",
                life, this.healthPotions);
        } else {
            System.out.println("Out of health potions!");
        }
    }
    
    public void addVisionPotion()
    {
    	this.visionPotions++;
    }
    
    public void drinkVision()
    {
        if (visionPotions > 0) {
            visionPotions--;
            
            this.visionRadius++;
            
            System.out.printf("You feel your senses sharpen.%nYou have %d vision potions left.%n",
                this.visionPotions);
        } else {
        	System.out.println("Out of vision potions!");
        }
    }
    
    public int getVision()
    {
        return this.visionRadius;
    }
    public int getHealthPotions()
    {
        return healthPotions;
    }
    public int getVisionPotions()
    {
        return visionPotions;
    }
    public void resetVision()
    {
        this.visionRadius = 0;
    }
    
    public void addPillar()
    {
    	pillarsFound++;
    }
    
    public int getPillars()
    {
        return pillarsFound;
    }
    
    private void readName()
    {
        System.out.print("Enter character name: ");
        this.setName(keyboard.nextLine());
    }
    
    void addTurn()
    {
        this.numTurns++;
    }
    
    private boolean defend()
    {
        return Math.random() <= chanceToBlock;
    }
    
    public void subtractHitPoints(int hitPoints)
    {
        if (defend()) {
            System.out.println(this.getName() + " blocked the damage!");
        } else {
            super.subtractHitPoints(hitPoints);
        }
    }
    
    public void setSpecial(Special special)
    {
        this.special = special;
    }

    private void special(DungeonCharacter opponent)
    {
        this.special.doSpecial(this, opponent);
    }
    
    void battleChoices(DungeonCharacter opponent)
    {
        int choice;
        this.numTurns = this.getAttackSpeed() / opponent.getAttackSpeed();
        if (this.numTurns == 0)
            this.numTurns++;

        System.out.println("Number of turns this round is: " + this.numTurns);
        while (numTurns > 0 && opponent.isAlive()) {
        	// Displaying options
            System.out.println("1. Attack Opponent");
            System.out.println("2. " + this.special.getName());
            System.out.print("Choose an option: ");
            choice = keyboard.nextInt();
            
            // Handling action
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
            
            // Checking if done
            numTurns--;
            if (numTurns > 0 && opponent.isAlive())
                System.out.println("Number of turns remaining is: " + numTurns);
        }
    }
}