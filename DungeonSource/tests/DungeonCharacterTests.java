package tests;

import dungeon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class DungeonCharacterTests {
    Monster character;
    @BeforeEach
    void startup(){
        InputStream fakeIn = new ByteArrayInputStream("\nbob\nbob\nbob".getBytes());
        System.setIn(fakeIn);
        character = new Monster("Sargath the Skeleton",125, .3,30,50,"normal","slices his rusty blade at");

    }
    @Test
    void addHitPoints() {
        assertEquals(125,character.getHitPoints());
        character.addHitPoints(10);
        assertEquals(135,character.getHitPoints());
    }

    @Test
    void subtractHitPoints() {
        assertEquals(125,character.getHitPoints());
        character.subtractHitPoints(10);
        assertNotEquals(125,character.getHitPoints());
    }
}
