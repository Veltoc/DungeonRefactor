package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dungeon.Attack;

class AttackTest
{
	Attack test;
	
	@Test
	void testGetNormal()
	{
		test = Attack.getAttack("normal");
		assertEquals(Attack.getAttackTypes()[0], test);
	}
	
	@Test
	void testGetHeavy()
	{
		test = Attack.getAttack("heavy");
		assertEquals(Attack.getAttackTypes()[1], test);
	}
	
	@Test
	void testGetLight()
	{
		test = Attack.getAttack("light");
		assertEquals(Attack.getAttackTypes()[2], test);
	}
}
