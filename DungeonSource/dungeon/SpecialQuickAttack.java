package dungeon;

public class SpecialQuickAttack implements Special {
    private static final long serialVersionUID = 1L;
    private int uses;
    
    public SpecialQuickAttack()
    {
        this.uses = 0;
    }
    
    @Override
    public String getName() {
        return "Quick Attack";
    }

    @Override
    public void doSpecial(DungeonCharacter actor, DungeonCharacter target) {
    	this.uses++;
        int damage = (int) (Math.random() * 20) + 1;
        
        System.out.printf("%s swipes at %s!%n", actor.getName(), target.getName());
        target.subtractHitPoints(damage * this.uses);
    }
    
    public int getUses()
    {
    	// Exists so unit test can validate increase
    	return this.uses;
    }
}
