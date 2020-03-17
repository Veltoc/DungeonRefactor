import java.util.ArrayList;
/*
extra idea: make it a maze, along with a system to check if the exit is reachable using recusion. add a check for if a room has been checked to speed it up
 */

public class Room {
    private ArrayList<String> contents;
    private boolean haveEntered;
    private String symbol;
    private int ySize = 5;
    private int xSize = 5;
    private int xPosition;
    private int yPosition;
    String[][] room = new String[3][3];
    Room(int x, int y, String specific){
        this.xPosition = x;
        this.yPosition = y;
        haveEntered = false;
        room[0][0] = "╔";
        room[2][2] = "╝";
        room[0][2] = "╗";
        room[2][0] = "╚";
        room[1][0] = " ";
        room[1][2] = " ";
        room[0][1] = "   ";
        room[2][1] = "   ";
        if(x == 0) room[0][1] = "═══";
        else if(x==xSize-1) room[2][1] = "═══";
        if(y == 0) room[1][0] = "║";
        else if(y==ySize-1) room[1][2] = "║";
        symbol = "E";
        generateContents(specific);
        room[1][1] = symbol;
    }
    public String[][] getRoom(){
        return room;
    }

    public int getyPosition() {
        return yPosition;
    }
    public int getxPosition() {
        return xPosition;
    }

    public String[] enter(){
        haveEntered = true;//for later, special text for going back
        String[] temp = new String[contents.size()];
        int count = 0;
        if(symbol.equals("M")) {
            for (int i = 0; i < contents.size(); i++){
                if(contents.get(i).equals("H")||contents.get(i).equals("V")||contents.get(i).equals("§")||contents.get(i).equals("X")){
                    temp[count]=contents.remove(i);
                    i--;
                }else{
                    temp[count]=contents.get(i);
                }
                count++;
            }
        }else if(contents.size()!=0){
            if(contents.get(0).equals("H")||contents.get(0).equals("V")||contents.get(0).equals("§")||contents.get(0).equals("X")){
                temp[count]=contents.remove(0);
            }else{
                temp[count]=contents.get(0);
            }
        }
        if(contents.size()==0) {
            contents.add("E");
        }
        symbol = contents.get(0);
        return temp;
    }

    private void generateContents(String specific) {
        contents = new ArrayList<String>();
        if(!specific.equals("E")){
            contents.add(specific);
            symbol = specific;
        }
        if(specific.equals("I") || specific.equals("O"))return;//explictly only them in room
        if(Math.random()<=0.1) contents.add("V");
        if(Math.random()<=0.1) contents.add("H");
        if(Math.random()<=0.1) contents.add("P");
        else if (Math.random()<=0.1) contents.add("X");
        if(contents.size()>1) {
            symbol = "M";
            System.out.println(contents);
        }
        else if(contents.size()==0) symbol = "E";
        else symbol = contents.get(contents.size()-1);
    }

    @Override
    public String toString() {
        return room[0][0]+room[0][1]+room[0][2]+"\n"
                +room[1][0]+" "+room[1][1]+" "+room[1][2]+"\n"
                +room[2][0]+room[2][1]+room[2][2];
    }
}
