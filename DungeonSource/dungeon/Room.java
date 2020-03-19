package dungeon;
import java.io.Serializable;
import java.util.ArrayList;
/*
extra idea: make it a maze, along with a system to check if the exit is reachable using recusion. add a check for if a room has been checked to speed it up
 */

public class Room
implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final char[] POS_ITEMS = {'P', 'V', 'H', 'X'};
    
    private int xPosition;
    private int yPosition;
    private char[] doors;
    private char symbol;
    private ArrayList<Character> items;
    
    public Room(int x, int y, char roomType)
    {
        this.xPosition = x;
        this.yPosition = y;
        this.doors = new char[4];
        generateContents(roomType);
        
        // Creating doors
        for (int i = 0; i < this.doors.length; i++) {
        	this.doors[i] = ' ';
        }
        if (x == 0) {
        	this.doors[1] = '║';
        } else if (x == Dungeon.XSIZE - 1) {
        	this.doors[2] = '║';
        }
        if (y == 0) {
        	this.doors[0] = '═';
        } else if (y == Dungeon.YSIZE - 1) {
        	this.doors[3] = '═';
        }
    }
    
    @Override
    public String toString()
    {
        String[] display = getRoomDisplay();
        return display[0] + "\n" + display[1] + "\n" + display[2];
    }
    
    private void generateContents(char roomType)
    {
        this.items = new ArrayList<Character>();
        this.symbol = roomType;
        
        // Adding room items
        if(roomType == 'I' || roomType == 'O') {
            return; // No items in entrance or exit
        } else if (roomType == '§') {
            this.items.add('§');
            return;
        }
        for (char item: POS_ITEMS) {
            if (Math.random() <= .1) {
                this.items.add(item);
            }
        }
        
        // Updating symbol
        if(this.items.size() > 1) {
            this.symbol = 'M';
        } else if (this.items.size() == 1) {
            this.symbol = this.items.get(0);
        } else {
            this.symbol = 'E';
        }
    }
    
    public String[] getRoomDisplay()
    {
        String[] display = {
            "╔" + this.doors[0] + "╗",
            "" + this.doors[1] + this.symbol + this.doors[2],
            "╚" + this.doors[3] + "╝"
        };
        return display;
    }

    public int getY()
    {
        return yPosition;
    }
    
    public int getX()
    {
        return xPosition;
    }
    
    public char[] getContents()
    {
        // Getting contents
        char[] contents;
        if (this.symbol == 'O') {
            contents = new char[1];
            contents[0] = 'O';
        } else {
            contents = new char[this.items.size()];
            for (int i = 0; i < this.items.size(); i++) {
                contents[i] = this.items.remove(i);
            }
        }
        
        // Updating symbol
        if (!(this.symbol == 'I' || this.symbol == 'O')) {
            this.symbol = 'E';
        }
        
        return contents;
    }
}
