
public class SpecialCureWounds
implements Special
{
	private int minHealth;
	private int maxHealth;

	public SpecialCureWounds(int min, int max)
	{
		this.minHealth = min;
		this.maxHealth = max;
	}

	@Override
	public String getName()
	{
		return "Cure Wounds";
	}

	@Override
	public void doSpecial(DungeonCharacter actor)
	{
		int hPoints;

		hPoints = (int)(Math.random() * (this.minHealth - this.maxHealth + 1)) + minHealth;
		actor.addHitPoints(hPoints);
		System.out.println(actor.getName() + " added <" + hPoints + "> points.\n"
				+ "Total hit points remaining are: "
				+ actor.getHitPoints());
		System.out.println();
	}
}
