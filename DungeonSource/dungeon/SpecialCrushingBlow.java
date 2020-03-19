package dungeon;

public class SpecialCrushingBlow implements Special {
    private static final long serialVersionUID = 1L;
    private double chance;

    public SpecialCrushingBlow(double chance) {
        this.chance = chance;
    }

    @Override
    public String getName() {
        return "Crushing Blow";
    }

    @Override
    public void doSpecial(DungeonCharacter actor, DungeonCharacter target) {
        if (Math.random() <= this.chance) {
            int blowPoints = (int) (Math.random() * 76) + 100;

            System.out.println(actor.getName() + " lands a CRUSHING BLOW for " + blowPoints + " damage!");
            target.subtractHitPoints(blowPoints);
        } else {
            System.out.println(actor.getName() + " failed to land a crushing blow");
            System.out.println();
        }
    }
}
