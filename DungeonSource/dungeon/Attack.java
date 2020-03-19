package dungeon;

import java.io.Serializable;

public class Attack
implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final Attack[] ATTACKS = {
		new Attack(.75, 25, 50, 5), // Normal
		new Attack(.66, 40, 70, 4), // Heavy
		new Attack(.80, 15, 40, 6) // Light
	};
	private double hitChance;
	private int minDamage;
	private int maxDamage;
	private int speed;
	
	private Attack(double hitChance, int minDmg, int maxDmg, int speed)
	{
		this.hitChance = hitChance;
		this.minDamage = minDmg;
		this.maxDamage = maxDmg;
		this.speed = speed;
	}
	
	public static Attack getAttack(String type)
	{
		switch (type) {
			case "heavy":
				return ATTACKS[1];
			case "light":
				return ATTACKS[2];
			default: // Normal attack
				return ATTACKS[0];
		}
	}
	
	public static Attack[] getAttackTypes()
	{
		// Exists so unit tests can test flyweight works
		return ATTACKS;
	}
	
	public void doAttack(DungeonCharacter actor, DungeonCharacter target)
	{
		if (Math.random() <= this.hitChance) {
			int damage = (int) (Math.random() * (this.maxDamage - this.minDamage))
					+ 1 + this.minDamage;
			target.subtractHitPoints(damage);
		} else {
			System.out.println(
				actor.getName() + "'s attack on "
				+ target.getName() + " failed!");
		}
	}
	
	public int getAttackSpeed()
	{
		return this.speed;
	}
}
