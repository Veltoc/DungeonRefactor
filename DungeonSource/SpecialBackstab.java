
public class SpecialBackstab
implements Special
{
	@Override
	public String getName()
	{
		return "Backstab";
	}
	
	@Override
	public void doSpecial(DungeonCharacter actor, DungeonCharacter target)
	{
		Hero hero = (Hero) actor;
		double surprise = Math.random();

		if (surprise <= .4) {
			System.out.println("Surprise attack was successful!\n" + hero.getName() + " gets an additional turn.");
			hero.addTurn();
			hero.attack(target);
		} // end surprise
		else if (surprise >= .9) {
			System.out.println("Uh oh! " + target.getName() + " saw you and" + " blocked your attack!");
		} else
			hero.attack(target);
	}
}
