package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dungeon.Hero;
import dungeon.Monster;
import dungeon.SpecialBackstab;
import dungeon.SpecialCrushingBlow;
import dungeon.SpecialCureWounds;
import dungeon.SpecialExplosion;
import dungeon.SpecialQuickAttack;
import dungeon.Attack;
import dungeon.CharacterCreator;

class CreatorTest
{
	Hero testHero;
	Monster testMonster;
	
	@BeforeEach
	void addHeroName()
	{
		InputStream fakeIn = new ByteArrayInputStream("bob\nbob\nbob\nbob\nbob".getBytes());
		System.setIn(fakeIn);
	}
	
	@Test
	void testHeroAlchemist()
	{
		testHero = CharacterCreator.createHero("Alchemist");
		assertEquals(100, testHero.getHitPoints());
		assertEquals(.4, testHero.getBlockChance());
		assertEquals(Attack.getAttack("fast"), testHero.getAttack());
		assertEquals("throws a bomb near", testHero.getWarcry());
		assertTrue(testHero.getSpecial() instanceof SpecialExplosion);
	}
	
	@Test
	void testHeroDuelist()
	{
		testHero = CharacterCreator.createHero("Duelist");
		assertEquals(80, testHero.getHitPoints());
		assertEquals(.4, testHero.getBlockChance());
		assertEquals(Attack.getAttack("normal"), testHero.getAttack());
		assertEquals("slashes a rapier at", testHero.getWarcry());
		assertTrue(testHero.getSpecial() instanceof SpecialQuickAttack);
	}
	
	@Test
	void testHeroSorceress()
	{
		testHero = CharacterCreator.createHero("Sorceress");
		assertEquals(75, testHero.getHitPoints());
		assertEquals(.3, testHero.getBlockChance());
		assertEquals(Attack.getAttack("normal"), testHero.getAttack());
		assertEquals("casts a fireball at", testHero.getWarcry());
		assertTrue(testHero.getSpecial() instanceof SpecialCureWounds);
	}
	
	@Test
	void testHeroThief()
	{
		testHero = CharacterCreator.createHero("Thief");
		assertEquals(75, testHero.getHitPoints());
		assertEquals(.5, testHero.getBlockChance());
		assertEquals(Attack.getAttack("fast"), testHero.getAttack());
		assertEquals("throws a dagger at", testHero.getWarcry());
		assertTrue(testHero.getSpecial() instanceof SpecialBackstab);
	}
	
	@Test
	void testHeroWarrior()
	{
		testHero = CharacterCreator.createHero("Warrior");
		assertEquals(125, testHero.getHitPoints());
		assertEquals(.2, testHero.getBlockChance());
		assertEquals(Attack.getAttack("heavy"), testHero.getAttack());
		assertEquals("swings a mighty sword toward", testHero.getWarcry());
		assertTrue(testHero.getSpecial() instanceof SpecialCrushingBlow);
	}
	
	@Test
	void testMonsterDrow()
	{
		testMonster = CharacterCreator.createMonster("Drow");
		assertEquals("Drizzt the Drow", testMonster.getName());
		assertEquals(80, testMonster.getHitPoints());
		assertEquals(.3, testMonster.getHealChance());
		assertEquals(25, testMonster.getMinHeal());
		assertEquals(45, testMonster.getMaxHeal());
		assertEquals(Attack.getAttack("fast"), testMonster.getAttack());
		assertEquals("fires an arrow at", testMonster.getWarcry());
	}
	
	@Test
	void testMonsterGremlin()
	{
		testMonster = CharacterCreator.createMonster("Gremlin");
		assertEquals("Gnarltooth the Gremlin", testMonster.getName());
		assertEquals(70, testMonster.getHitPoints());
		assertEquals(.4, testMonster.getHealChance());
		assertEquals(20, testMonster.getMinHeal());
		assertEquals(40, testMonster.getMaxHeal());
		assertEquals(Attack.getAttack("fast"), testMonster.getAttack());
		assertEquals("jabs his kris at", testMonster.getWarcry());
	}
	
	@Test
	void testMonsterOgre()
	{
		testMonster = CharacterCreator.createMonster("Ogre");
		assertEquals("Oscar the Ogre", testMonster.getName());
		assertEquals(200, testMonster.getHitPoints());
		assertEquals(.1, testMonster.getHealChance());
		assertEquals(30, testMonster.getMinHeal());
		assertEquals(50, testMonster.getMaxHeal());
		assertEquals(Attack.getAttack("heavy"), testMonster.getAttack());
		assertEquals("slowly swings a club toward", testMonster.getWarcry());
	}
	
	@Test
	void testMonsterSkeleton()
	{
		testMonster = CharacterCreator.createMonster("Skeleton");
		assertEquals("Sargath the Skeleton", testMonster.getName());
		assertEquals(100, testMonster.getHitPoints());
		assertEquals(.3, testMonster.getHealChance());
		assertEquals(30, testMonster.getMinHeal());
		assertEquals(50, testMonster.getMaxHeal());
		assertEquals(Attack.getAttack("normal"), testMonster.getAttack());
		assertEquals("slices his rusty blade at", testMonster.getWarcry());
	}
	
	@Test
	void testMonsterSlime()
	{
		testMonster = CharacterCreator.createMonster("Slime");
		assertEquals("Jerry the Slime", testMonster.getName());
		assertEquals(100, testMonster.getHitPoints());
		assertEquals(1, testMonster.getHealChance());
		assertEquals(10, testMonster.getMinHeal());
		assertEquals(30, testMonster.getMaxHeal());
		assertEquals(Attack.getAttack("normal"), testMonster.getAttack());
		assertEquals("swallows", testMonster.getWarcry());
	}
}
