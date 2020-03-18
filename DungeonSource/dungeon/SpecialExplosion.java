package dungeon;

public class SpecialExplosion implements Special {
    private double chance;
    private int minDamage;
    private int maxDamage;
    
    public SpecialExplosion(double chance, int minDmg, int maxDmg)
    {
        this.chance = chance;
        this.minDamage = minDmg;
        this.maxDamage = maxDmg;
    }
    
    @Override
    public String getName() {
        return "Explosion";
    }

    @Override
    public void doSpecial(DungeonCharacter actor, DungeonCharacter target) {
        double result = Math.random();
        
        if (result <= .05) {
            System.out.printf("%s blew %s to smithereens!%n",
                actor.getName(), target.getName());
            
            target.subtractHitPoints(Integer.MAX_VALUE);
        } else if (result <= this.chance + .05) {
            System.out.printf("%s managed to dodge the bomb, but %s didn't!%n",
                actor.getName(), target.getName());
            
            target.subtractHitPoints(getDamage());
        } else {
            System.out.printf("Both %s and %s were hit by the explosion!%n",
                actor.getName(), target.getName());
            
            int damage = getDamage();
            actor.subtractHitPoints(damage/3);
            target.subtractHitPoints(damage);
        }
    }
    
    private int getDamage()
    {
        return (int) (Math.random() * (this.maxDamage - this.minDamage + 1)) + minDamage;
    }
}
