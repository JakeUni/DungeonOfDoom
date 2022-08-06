//importing relevant libraries
import java.util.Random;

//this is the actor class, which is instantiated as bot and player
public class Actor {
    //this is the X and Y
    private int X;
    private int Y;
    //this is the amount of gold they have
    private int goldOwned;
    public Actor(){
        //giving the actor a random location
        randomLocation();
    }
    //this function allows it to move horizontally, in the x direction
    protected String moveHorizontal(int nx){
        //create a tempx which is used to store the value of X if it is valid
        int tempX = X + nx;
        //if the coords are valid then assign this value to X
        if (checkCoords(tempX, Y) == true){
            X = tempX;
            //return Success as it worked
            return "SUCCESS";
        }else{
            //return FAIL if it is not right
            return ("FAIL");
        }
    }
    //the same as above but for y
    protected String moveVertical(int ny){
        int tempY = Y + ny;
        if (checkCoords(X, tempY) == true){
            Y = tempY;
            return "SUCCESS";
        }else{
            return ("FAIL");

        }
    }
    //return the location of the file
    protected int[] getLocation(){
        return new int[] {X, Y};
    }
    //return the amount of gold
    protected int getGold(){
        return goldOwned;
    }
    //this gives the actor a random location
    protected void randomLocation(){

        Random rand = new Random();
        boolean works = false;
        //gets the map
        char[][] map = Main.myMap.getMap();

        //loops until works is true
        while(works == false){
             //assigns a random value to X and Y in the range of the maps x, y
             X = rand.nextInt(map.length - 1 ) + 1;
             Y = rand.nextInt(map[1].length - 1 ) + 1;
             //if it is not a # or a G then change it to true
            if ((checkCoords(X,Y)==true) && (checkGold(X, Y)==false)){
                works = true;
            }
        }

    }

    protected void look(int x1,int y1, int x2, int y2){
        //getting the map
        char[][] map = Main.myMap.getMap();
        //loop 5 times
        for(int k = y1-2; k < y1+3; k++){
            //loop another 5 times
            for(int l = x1-2; l < x1+3; l++){
                try {
                    //if the value of the current position is the same as player then print P
                    if ((l == x1) && (k == y1)){
                        System.out.print("P" + " ");
                        //if the value is the same as the bots location print B
                    }else if((l == x2) && (k == y2)){
                        System.out.print("B" + " ");
                    }else{
                        //try to print location
                        System.out.print(map[l][k] + " ");
                    }

                }
                catch(ArrayIndexOutOfBoundsException e) {
                    System.out.print("# ");
                    //if it cannot print any of the above statements then it is out of range and will need to print #
                }
            }
            //new line
            System.out.println();
        }
    }


    //check if the current tile is an exit tile by getting it and comparing it to 'E'
    protected boolean checkExit(){
        char[][] map = Main.myMap.getMap();
        if ((map[X][Y] == 'E')) {
            return true;
        }
        return false;
    }
    // check if the current tile is a gold tile , if it is then return true if it isnt return false
    protected boolean checkGold(int tX,int tY){
        char[][] map = Main.myMap.getMap();
        if ((map[tX][tY] == 'G')) {
            goldOwned ++;
            map[tX][tY] = '.';
            return true;
        }
        return false;
    }

    //check if the current x,y is on a #
    private boolean checkCoords(int tX, int tY){
        char[][] map = Main.myMap.getMap();
        if ((map[tX][tY] == '#')) {
            return false;
        }
        return true;
    }

}
