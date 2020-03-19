package dungeon;

import java.io.Serializable;

public abstract class DungeonCharacter
implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int hitPoints;
    private Attack attack;
    private String warcry;
    
    public DungeonCharacter(String name, int hitPoints,//made public to test
      String attackType, String warcry)
    {
        this.name = name;
        this.hitPoints = hitPoints;
        this.attack = Attack.getAttack(attackType);
        this.warcry = warcry;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    protected void setName(String name)
    {
        this.name = name;
    }
    
    public int getHitPoints()
    {
        return this.hitPoints;
    }
    
    public void addHitPoints(int hitPoints)
    {
        this.hitPoints += hitPoints;
        
        System.out.printf("%s healed <%d> hit points!%n",
            this.name, hitPoints);
        System.out.printf("%s has <%d> hit points remaining.%n%n",
            this.name, this.hitPoints);
    }
    
    public void subtractHitPoints(int hitPoints)
    {
        this.hitPoints -= hitPoints;
        if (this.hitPoints < 0)
            this.hitPoints = 0;
        
        System.out.printf("%s was hit for <%d> points of damage!%n",
            this.name, hitPoints);
        System.out.printf("%s has <%d> hit points remaining.%n%n",
            this.name, this.hitPoints);
        
        if (this.hitPoints == 0)
            System.out.println(name + " has been killed :-(");
    }
    
    public boolean isAlive()
    {
        return this.hitPoints > 0;
    }
    
    public int getAttackSpeed()
    {
        return this.attack.getAttackSpeed();
    }
    
    protected String getWarcry()
    {
        return this.warcry;
    }
    
    protected Attack getAttack()
    {
    	return this.attack;
    }
    
    protected void setAttack(Attack atk)
    {
    	this.attack = atk;
    }
    
    public void attack(DungeonCharacter opponent)
    {
        if (!this.warcry.isEmpty()) {
            System.out.printf("%s %s %s:%n",
                this.name, this.warcry, opponent.getName());
        }
        
        this.attack.doAttack(this, opponent);
    }
}