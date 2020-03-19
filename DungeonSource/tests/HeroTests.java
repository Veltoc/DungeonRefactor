package tests;
import dungeon.Hero;
import dungeon.SpecialCrushingBlow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
public class HeroTests {
    private Hero hero;
    @BeforeEach
    void startup(){
        InputStream fakeIn = new ByteArrayInputStream("bob\nbob\nbob".getBytes());
        System.setIn(fakeIn);
        hero = new Hero(125,.2,"heavy","swings a mighty sword toward",new SpecialCrushingBlow(.4));

    }
    @Test
    void TestHealthPotions(){
        hero.addHealthPotion();
        assertEquals(1,hero.getHealthPotions());
        hero.drinkHealth();
        assertEquals(0,hero.getHealthPotions());
    }
    @Test
    void TestVisionPotions(){
        hero.addVisionPotion();
        assertEquals(1,hero.getVisionPotions());
        hero.drinkVision();
        assertEquals(0,hero.getVisionPotions());
    }
    @Test
    void TestPillars(){
        assertEquals(0,hero.getPillars());
        hero.addPillar();
        assertEquals(1,hero.getPillars());
    }
}
