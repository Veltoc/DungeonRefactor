# DungeonRefactor
- (V) Refactor #1: Each class (except thief) overrode attack just to print a line, pushed that up and passed the line at construction to DungeonCharacter to call.
For example, 
In Warrior:

	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " swings a mighty sword at " +
							opponent.getName() + ":");
		super.attack(opponent);
	}//end override of attack method
In Sorceress:
	
	public void attack(DungeonCharacter opponent){
		System.out.println(name + " casts a spell of fireball at " +
							opponent.getName() + ":");
		super.attack(opponent);
	}//end override of attack method


- (V) Refactor #2: each hero overrode BattleChoices with the only difference being a print with the name of the special and the method the special was. Made the methods general and override a special method in Hero, passed the special name and pushed up the battleChoices to hero.

For example, 
In Warrior

	public void battleChoices(DungeonCharacter opponent)
	{
		int choice;
		super.battleChoices(opponent);
		do
		{
		    System.out.println("1. Attack Opponent");
		    System.out.println("2. Crushing Blow on Opponent");
...

	switch (choice){
	case 1: attack(opponent);
	break;
	case 2: crushingBlow(opponent);...
where the only change in the class was crushing blow and the method called. so instead made its calls general and part pushed the duplicate code to Hero. slightly redone by refactor 7


- (V) Refactor #3: Indecent Exposure code smell, while much of the items were atleast protected or final to make it more secure from editing, the classes were still exposing contents that didn't need to. So lowered the access of fields and methods across the project

  in monster for example, these fields could be private:
  protected double chanceToHeal;
	protected int minHeal, maxHeal; 
  
  as well as these in Sorceress:
  public final int MIN_ADD = 25;
	public final int MAX_ADD = 50;
  
  or dungeonCharacter:
  protected String name;
  protected int hitPoints;
  protected int attackSpeed;
  protected double chanceToHit;
  rotected int damageMin, damageMax;
  
- (M) Refactor #4: DungeonCharacter implements Comparable and compareTo, but compareTo is never used - removed Comparable implementation
	public abstract class DungeonCharacter implements Comparable...
	public int compareTo(Object o)
	{
		return 1;
	}
not only was compareTo dead code, if it was in use then it would fail to do its job, only ever returning 1. Pointless and dead code

- (V) Refactor #5: Keyboard is a class within the project that does the same job as the Scanner class created in Java 7. With most of it being unused or outdated it is best to replace with Scanner. Doing so also allows us to get a line and allow enter as a key to continue

unused:

	public static int getErrorCount()
	public static void resetErrorCount (int count)
	public static boolean getPrintErrors()
	public static void setPrintErrors
	public static String readWord()
	public static boolean readBoolean()
	public static long readLong()
	public static float readFloat()
	public static double readDouble()

depricated:

	((delimiters.indexOf (token) >= 0) && skip))
	value = (new Double(token)).doubleValue();
	value = (new Float(token)).floatValue();

- (M) Refactor #6: Improved user experience (not continuing turn when monster is dead; not asking to quit after winning; formatting)

	//let the player bail out if desired
			System.out.print("\n-->q to quit, anything else to continue: ");
			pause = Keyboard.readChar();

		}//end battle loop
    this was called regardless of if the battle was over, better experience to end the turn when the game is won.
- (M) Refactor #7: Create classes for special abilities (Strategy 'em up)
Theifs: public void surpriseAttack(DungeonCharacter opponent)...
Sorrcess: public void increaseHitPoints()...
Warror: public void crushingBlow(DungeonCharacter opponent)...
Hero: public void special(DungeonCharacter opponent){special();}
    public void special(){}
- (M) Refactor #8: Create builder for monsters and heroes. Instead of building with constructors, using the builder pattern to create our hero and monster subclasses. After refactor 1,2,7 the only thing left was the constructor
Ogre: public Ogre() {
        super("Oscar the Ogre", 200, 2, .6, .1, 30, 50, 30, 50);
    }//end constructor
Gremlin:     public Gremlin()
	{
		super("Gnarltooth the Gremlin", 70, 5, .8, .4, 15, 30, 20, 40);
    }//end constructor
Skeleton:     public Skeleton()
	{
		super("Sargath the Skeleton", 100, 3, .8, .3, 30, 50, 30, 50);
    }//end constructor
Sorceress: public Sorceress()
	{
		super("Sorceress", 75, 5, .7, 25, 50, .3);
    }
Theif:  public Thief()
	{
		super("Thief", 75, 6, .8, 20, 40, .5);
    }//end constructor
Warrior: public Warrior()
	{
		super("Warrior", 125, 4, .8, 35, 60, .2);
    }//end constructor

