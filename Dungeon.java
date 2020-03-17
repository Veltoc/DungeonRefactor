import java.util.ArrayList;
import java.util.Random;

public class Dungeon {
    private int ySize = 5;
    private int xSize = 5;
    private Room[][] dungeon;
    private Room currentRoom;
    public Dungeon(){
        dungeon = new Room[xSize][ySize];
        currentRoom = null;
        createDungeon();

    }
    public void createDungeon(){
        Random rand = new Random();
        String[] specials = {"I","O","§","§","§","§"};
        ArrayList<Integer> specialLocations = new ArrayList<Integer>();
        int specialCount = 0;
        for (int i = 0; i < specials.length; i++) {
            int num = rand.nextInt(xSize*ySize);
            if(specialLocations.contains(num)) i--;
            else{
                specialLocations.add(num);
            }
        }
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                String specific = "E";
                //System.out.println(specialLocations.indexOf((x*ySize)+y)+" and "+x+" "+y);
                if(specialLocations.size()!= 0 && specialLocations.contains((x*ySize)+y)){
                    specific = specials[specialCount];
                    specialCount++;
                    specialLocations.remove(specialLocations.indexOf((x*ySize)+y));
                }
                Room tmRoom = new Room(x,y,specific);
                if (specific.equals("I")) currentRoom = tmRoom;
                dungeon[x][y] = tmRoom;
            }
        }
    }
    public Room move(String direction){
        switch (direction.toUpperCase()){
            case "N":
                if(currentRoom.getxPosition()!=0){
                    currentRoom=dungeon[currentRoom.getxPosition()-1][currentRoom.getyPosition()];
                    return currentRoom;
                }
                break;
            case "S":
                if(currentRoom.getxPosition()!=xSize-1){
                    currentRoom=dungeon[currentRoom.getxPosition()+1][currentRoom.getyPosition()];
                    return currentRoom;
                }
                break;
            case "W":
                if(currentRoom.getyPosition()!=0){
                    currentRoom=dungeon[currentRoom.getxPosition()][currentRoom.getyPosition()-1];
                    return currentRoom;
                }
                break;
            case "E":
                if(currentRoom.getyPosition()!=ySize-1){
                    currentRoom=dungeon[currentRoom.getxPosition()][currentRoom.getyPosition()+1];
                    return currentRoom;
                }
                break;
        }
        return null;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String printDungeon(int radius) {
        int upperbound = 0;
        int lowerbound = xSize;
        int leftbound = 0;
        int rightbound = ySize;
        if(radius!=-1){
            if(currentRoom.getxPosition()!=0)upperbound = currentRoom.getxPosition()-1;
            if(currentRoom.getxPosition()!=xSize-1)lowerbound = currentRoom.getxPosition()+2;
            if(currentRoom.getyPosition()!=0)leftbound = currentRoom.getyPosition()-1;
            if(currentRoom.getyPosition()!=ySize-1)rightbound = currentRoom.getyPosition()+2;
        }
        String printDungeon = "";
        for (int x = upperbound; x < lowerbound; x++) {
            String row1 = "";
            String row2 = "";
            String row3 = "";
            for (int y = leftbound; y < rightbound; y++) {
                String[][] temp = dungeon[x][y].getRoom();
                row1 += temp[0][0]+temp[0][1]+temp[0][2];
                row2 += temp[1][0]+" "+temp[1][1]+" "+temp[1][2];
                row3 += temp[2][0]+temp[2][1]+temp[2][2];
            }
            printDungeon += row1+"\n"+row2+"\n"+row3+"\n";
        }
        return printDungeon;
    }
    /*
    @Override
    public String toString() {
        String printDungeon = "";
        for (int x = 0; x < xSize; x++) {
            String row1 = "";
            String row2 = "";
            String row3 = "";
            for (int y = 0; y < ySize; y++) {
                String[][] temp = dungeon[x][y].getRoom();
                row1 += temp[0][0]+temp[0][1]+temp[0][2];
                row2 += temp[1][0]+" "+temp[1][1]+" "+temp[1][2];
                row3 += temp[2][0]+temp[2][1]+temp[2][2];
            }
            printDungeon += row1+"\n"+row2+"\n"+row3+"\n";
        }
        return printDungeon;
    }
     */
}
