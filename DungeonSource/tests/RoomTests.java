package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dungeon.Room;

class RoomTests {

    @Test
    void CreateRoomSuccess() {
        Room room = new Room(1,2,'I');
        assertEquals(1,room.getX());
        assertEquals(2,room.getY());
        assertTrue(room.toString().contains("I"));
    }
    @Test
    void getContents_removesItems() {
        Room room = new Room(1,2,'§');
        char[] check1 = room.getContents();
        char[] check2 = room.getContents();
        assertNotEquals(check1.length,check2.length);
        assertTrue(check2.length==0);
    }
    @Test
    void calculatesCornersSuccess(){
        Room room = new Room(0,0,'I');//top left
        String[] rm = room.getRoomDisplay();
        assertEquals(rm[0],"╔═╗");
        assertEquals(rm[1],"║I ");
        assertEquals(rm[2],"╚ ╝");

        room = new Room(0,4,'I');//top right
        rm = room.getRoomDisplay();
        assertEquals(rm[0],"╔ ╗");
        assertEquals(rm[1],"║I ");
        assertEquals(rm[2],"╚═╝");

        room = new Room(4,0,'I');//bottom left
        rm = room.getRoomDisplay();
        assertEquals(rm[0],"╔═╗");
        assertEquals(rm[1]," I║");
        assertEquals(rm[2],"╚ ╝");

        room = new Room(4,4,'I');//bottom right
        rm = room.getRoomDisplay();
        assertEquals(rm[0],"╔ ╗");
        assertEquals(rm[1]," I║");
        assertEquals(rm[2],"╚═╝");
    }

}