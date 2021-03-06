# Dungeon Adventure
Extra credit(save) was done, all Mathews
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
'admin' will give the user a vision and health potion while also giving the full dungeon
Dungeon is modular, messing with XSIZE and YSIZE in the code can allow the changing of the size of the dungeon.


# work distribution:
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

# Additional specifications:
1. Matthew as part of dungeon refractor assignement
2. Matthew
3. Matthew
4. Matthew
5. Mason
6. Matthew

UML: Matthew

Instead of splitting workload by class, due to conflicting schedules Mason got the primary project running with the exception of most of the extras such as attack and extra characters. Matthew cleaned it up, fixed a couple missed bugs and completed the additional specifications as well as UML and showing output

Note on tests: Due to the randomizing nature of this project we are limited on tests. Furthermore our abstraction had to be broken to test what little we could, those cases are noted. THE UML DOES NOT REFLECT THIS

Below is previous assignment

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
  		protected int damageMin, damageMax;
  
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

		System.out.print("\n-->q to quit, anything else to continue: ");
			pause = Keyboard.readChar();
		}//end battle loop
    this was called regardless of if the battle was over, better experience to end the turn when the game is won.
- (M) Refactor #7: Create classes for special abilities (Strategy 'em up)
Theifs: 

		public void surpriseAttack(DungeonCharacter opponent)...
Sorrcess: 

		public void increaseHitPoints()...
Warror: 

		public void crushingBlow(DungeonCharacter opponent)...
Hero: 

		public void special(DungeonCharacter opponent){special();}
   		public void special(){}
- (M) Refactor #8: Create factory for monsters and heroes. Instead of creating with constructors, using the simple factory pattern to create our hero and monster subclasses. After refactor 1,2,7 the only thing left was the constructor
Ogre: 

		public Ogre() {
  	      super("Oscar the Ogre", 200, 2, .6, .1, 30, 50, 30, 50);
  	  }//end constructor
Gremlin:     

		public Gremlin()
		{
			super("Gnarltooth the Gremlin", 70, 5, .8, .4, 15, 30, 20, 40);
  	  }//end constructor
Skeleton:     

		public Skeleton()
		{
			super("Sargath the Skeleton", 100, 3, .8, .3, 30, 50, 30, 50);
 	   }//end constructor
Sorceress: 

		public Sorceress()
		{
			super("Sorceress", 75, 5, .7, 25, 50, .3);
 	   }
Theif:  

		public Thief()
		{
			super("Thief", 75, 6, .8, 20, 40, .5);
  	  }//end constructor
Warrior: 
	
		public Warrior()
		{
			super("Warrior", 125, 4, .8, 35, 60, .2);
 	   }//end constructor

-(V) Refactor #9 There was a considerable overdose of comments, sized the blocks down to simple descriptions, and removed stuff like "end constructor" that are just unnecessary.

Ex from Hero:

		 /*-------------------------------------------------------
    readName obtains a name for the hero from the user
    Receives: nothing
    Returns: nothing
    This method calls: nothing
    This method is called by: hero constructor
    ---------------------------------------------------------*/
large comment block for a method that just sets name from user input, more lines in the comment than there is code in the method.
