package dungeon;

public class Monster
extends DungeonCharacter
{
    private static final long serialVersionUID = 1L;
    
    private double chanceToHeal;
    private int minHeal;
    private int maxHeal;
    
    public Monster(String name, int hitPoints,
        double chanceToHeal, int minHeal, int maxHeal,
        String attackType, String warcry)
    {
        super(name, hitPoints, attackType, warcry);
        
        this.chanceToHeal = chanceToHeal;
        this.maxHeal = maxHeal;
        this.minHeal = minHeal;
    }
    
    private void heal()
    {
        if ((Math.random() <= chanceToHeal) && (this.getHitPoints() > 0)) {
            int healPoints = (int) (Math.random() * (maxHeal - minHeal + 1)) + minHeal;
            addHitPoints(healPoints);
        }
    }
    
    public void subtractHitPoints(int hitPoints)
    {
        super.subtractHitPoints(hitPoints);
        heal();
    }
    
    public double getBlockChance()
    {
    	// Exists so unit tests can validate stats
    	return this.chanceToHeal;
    }
    
    public int getMinHeal()
    {
    	// Exists so unit tests can validate stats
    	return this.minHeal;
    }
    
    public int getMaxHeal()
    {
    	// Exists so unit tests can validate stats
    	return this.maxHeal;
    }
}