package dungeon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Dungeon
implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    static final int YSIZE = 5;
    static final int XSIZE = 5;
    
    private Room[][] dungeon;
    private Room currentRoom;
    
    public Dungeon()
    {
        this.dungeon = new Room[XSIZE][YSIZE];
        this.currentRoom = null;
        createDungeon();
    }
    
    @Override
    public String toString()
    {
        return String.format("Dungeon:%n%sCurrent Room: (%d, %d)",
            printDungeon(-1), this.currentRoom.getX(), this.currentRoom.getY());
    }
    
    public void createDungeon()
    {
        Random rand = new Random();
        char[] specialRoomTypes = {'I', 'O', '§', '§', '§', '§'};
        
        // Getting locations for special room types
        ArrayList<Integer> specialLocations = new ArrayList<Integer>();
        int num;
        
        for (int i = 0; i < specialRoomTypes.length; i++) {
            num = rand.nextInt(XSIZE * YSIZE);
            if (specialLocations.contains(num)) {
            	i--;
            } else {
                specialLocations.add(num);
            }
        }
        
        // Building rooms
        char roomType;
        Room room;
        int specialCount = 0;
        
        for (int x = 0; x < XSIZE; x++) {
            for (int y = 0; y < YSIZE; y++) {
            	roomType = 'E'; // Defaulting to empty
                
                // Placing specials first
                if(specialLocations.size() > 0 && specialLocations.contains((x * YSIZE) + y)){
                	roomType = specialRoomTypes[specialCount];
                    specialCount++;
                    specialLocations.remove(specialLocations.indexOf((x * YSIZE) + y));
                }
                
                // Building room
                room = new Room(x, y, roomType);
                this.dungeon[x][y] = room;
                
                // Checking for entrance
                if (roomType == 'I') {
                	this.currentRoom = room;
                }
            }
        }
    }
    
    public Room move(String direction)
    {
        int dx = 0;
        int dy = 0;
        boolean invalidRoom = false;
        
        // Getting new room
        switch (direction) {
            case "n":
                if (this.currentRoom.getY() != 0) {
                    dy = -1;
                } else {
                    invalidRoom = true;
                }
                break;
            case "s":
                if (currentRoom.getY() != XSIZE - 1) {
                    dy = 1;
                } else {
                    invalidRoom = true;
                }
                break;
            case "w":
                if (currentRoom.getX() != 0){
                    dx = -1;
                } else {
                    invalidRoom = true;
                }
                break;
            case "e":
                if (currentRoom.getX() != YSIZE - 1){
                    dx = 1;
                } else {
                    invalidRoom = true;
                }
                break;
        }
        
        if (invalidRoom) {
            System.out.println("Cannot move in that direction!");
            return this.currentRoom;
        } else {
            this.currentRoom = this.dungeon[this.currentRoom.getX() + dx][this.currentRoom.getY() + dy];
            return this.currentRoom;
        }
    }
    
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public String printDungeon(int radius)
    {
        // Finding boundaries
        int xLow;
        int xHigh;
        int yLow;
        int yHigh;
        
        if (radius == -1) {
            xLow = 0;
            yLow = 0;
            xHigh = XSIZE - 1;
            yHigh = YSIZE - 1;
        } else {
            xLow = this.currentRoom.getX() - radius;
            if (xLow < 0) {xLow = 0;}
            yLow = this.currentRoom.getY() - radius;
            if (yLow < 0) {yLow = 0;}
            
            xHigh = this.currentRoom.getX() + radius;
            if (xHigh >= XSIZE) {xHigh = XSIZE - 1;}
            yHigh = this.currentRoom.getY() + radius;
            if (yHigh >= YSIZE) {yHigh = YSIZE - 1;}
        }
        
        String[] rows = new String[3];
        String dungeonDisplay = "";
        for (int y = yLow; y <= yHigh; y++) {
            rows[0] = "";
            rows[1] = "";
            rows[2] = "";
            for (int x = xLow; x <= xHigh; x++) {
                String[] room = dungeon[x][y].getRoomDisplay();
                rows[0] += room[0];
                rows[1] += room[1];
                rows[2] += room[2];
            }
            dungeonDisplay += rows[0] + "\n" + rows[1] + "\n" + rows[2] + "\n";
        }
        return dungeonDisplay;
    }
}
