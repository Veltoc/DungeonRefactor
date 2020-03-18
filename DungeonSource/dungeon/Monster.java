package dungeon;

class Monster extends DungeonCharacter
{
    private double chanceToHeal;
    private int minHeal;
    private int maxHeal;
    
    Monster(String name, int hitPoints,
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
}