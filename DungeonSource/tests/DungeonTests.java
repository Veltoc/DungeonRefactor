package tests;

import dungeon.Dungeon;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DungeonTests {
    @Test
    void createsDungeon_PlacedAtStart(){
        Dungeon dungeon = new Dungeon();
        assertTrue(dungeon.toString().contains("I"));
    }
    @Test
    void TestGetCurrentRoom(){
        Dungeon dungeon = new Dungeon();
        assertTrue(dungeon.getCurrentRoom().getRoomDisplay()[1].contains("I"));
    }
    @Test
    void PrintsDungeon_ContainsEverything(){
        Dungeon dungeon = new Dungeon();
        String[] list = {"I", "O", "§", "§", "§", "§"};
        String fullDungeon = dungeon.printDungeon(-1);
        int index = 0;
        for (String item: list) {
            if(item.equals("§")){
                index = fullDungeon.indexOf("§",index+1);
                assertNotEquals(-1,index);
            }else {
                assertTrue(fullDungeon.contains(item));
            }
        }
    }
}
