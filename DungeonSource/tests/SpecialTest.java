package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dungeon.Monster;
import dungeon.Special;
import dungeon.SpecialCrushingBlow;
import dungeon.SpecialCureWounds;
import dungeon.SpecialExplosion;
import dungeon.SpecialQuickAttack;

class SpecialTest {
	Special test;
	Monster actor; // Actor is a monster because Special doesn't care if actor is a hero
	Monster target;
	
	@BeforeEach
	void loadActors()
	{
		this.actor = new Monster("", 100, 0, 0, 0, "normal", "");
		this.target = new Monster("", 100, 0, 0, 0, "normal", "");
	}
	
	@Test
	void testDoCrushingBlowSuccess()
	{
		this.test = new SpecialCrushingBlow(1);
		int preAttackHP = this.target.getHitPoints();
		this.test.doSpecial(actor, target);
		assertTrue(preAttackHP > target.getHitPoints());
	}
	
	@Test
	void testDoCrushingBlowFail()
	{
		this.test = new SpecialCrushingBlow(0);
		int preAttackHP = this.target.getHitPoints();
		this.test.doSpecial(actor, target);
		assertTrue(preAttackHP == target.getHitPoints());
	}
	
	@Test
	void testDoCureWounds()
	{
		this.test = new SpecialCureWounds(10, 10);
		int preHealHP = this.actor.getHitPoints();
		this.test.doSpecial(actor);
		assertTrue(preHealHP < actor.getHitPoints());
	}
	
	@Test
	void testDoExplosionNoRecoil()
	{
		this.test = new SpecialExplosion(1, 10, 10);
		int preAttackHP = this.actor.getHitPoints();
		this.test.doSpecial(actor, target);
		assertTrue(preAttackHP == actor.getHitPoints());
	}
	
	@Test
	void testDoExplosionRecoil()
	{
		this.test = new SpecialExplosion(0, 10, 10);
		int preAttackHP = this.actor.getHitPoints();
		this.test.doSpecial(actor, target);
		assertTrue(preAttackHP > actor.getHitPoints());
	}
	
	@Test
	void testDoQuickAttack()
	{
		this.test = new SpecialQuickAttack();
		int preAttackUses = ((SpecialQuickAttack) this.test).getUses();
		this.test.doSpecial(actor, target);
		int postAttackUses = ((SpecialQuickAttack) this.test).getUses();
		assertTrue(preAttackUses == postAttackUses - 1);
	}
}
