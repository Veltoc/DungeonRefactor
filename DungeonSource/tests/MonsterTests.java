package tests;

import dungeon.Monster;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MonsterTests {
    @Test
    void TestSubtractHealthPoints(){
        Monster monster = new Monster("Sargath the Skeleton",100, .3,30,50,"normal","slices his rusty blade at");
        int inital = monster.getHitPoints();
        monster.subtractHitPoints(10);
        int damaged = monster.getHitPoints();
        assertNotEquals(inital,damaged);
    }
}
