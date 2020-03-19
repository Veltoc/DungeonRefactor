package tests;

import static org.junit.jupiter.api.Assertions.*;

import dungeon.Monster;
import org.junit.jupiter.api.Test;
import dungeon.DungeonAdventure;

import java.io.*;

public class DungeonAdventureTests {
    @Test
    void CreateMonsterSuccess()  {
       Monster monster =  DungeonAdventure.generateMonster();
       assertNotNull(monster);
    }
    @Test
    void TestPlayagain_True(){
        InputStream fakeIn = new ByteArrayInputStream("y\nn".getBytes());
        System.setIn(fakeIn);
        assertTrue(DungeonAdventure.playAgain());
        assertFalse(DungeonAdventure.playAgain());
    }
}
