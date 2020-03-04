
public interface Special
{
	public String getName();

	default public void doSpecial(DungeonCharacter actor, DungeonCharacter target)
	{
		doSpecial(actor);
	}

	default public void doSpecial(DungeonCharacter actor)
	{
		
	}
}
