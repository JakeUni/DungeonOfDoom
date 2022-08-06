//importing required libraries
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//This is the main class, this will be running throught the entirety of the program life
public class Main {
    //Initializing the map
    public static Map myMap;

    //This is the main loop which will repeat until the game ends
    public static void main(String[] args) {
        //The output at the start of the system
        System.out.print("______ _   _ _   _ _____  _____ _____ _   _   ___________  ______ _____  ________  ___\n" +
                "|  _  \\ | | | \\ | |  __ \\|  ___|  _  | \\ | | |  _  |  ___| |  _  \\  _  ||  _  |  \\/  |\n" +
                "| | | | | | |  \\| | |  \\/| |__ | | | |  \\| | | | | | |_    | | | | | | || | | | .  . |\n" +
                "| | | | | | | . ` | | __ |  __|| | | | . ` | | | | |  _|   | | | | | | || | | | |\\/| |\n" +
                "| |/ /| |_| | |\\  | |_\\ \\| |___\\ \\_/ / |\\  | \\ \\_/ / |     | |/ /\\ \\_/ /\\ \\_/ / |  | |\n" +
                "|___/  \\___/\\_| \\_/\\____/\\____/ \\___/\\_| \\_/  \\___/\\_|     |___/  \\___/  \\___/\\_|  |_/\n" +
                "                                                                                      \n");

        System.out.println("Please choose the map you wish to play");

        //Retrieving the names of all the text files
        List<String> textFiles = textFiles();
        int i = 0;

        //Loop for each text file found , displaying each with a number used as an identifier
        for (String file: textFiles){
            i++;
            System.out.println(i + " : " + file);
        }
        //new scanner to get the user input
        Scanner scan = new Scanner(System.in);
        int k = 0;
        //K is used as a check for input
        while (k == 0) {
            //while they havent picked a map check the next input to see if it is an integer
            //and check if this integer is in the range of maps we have
            String s = scan.next();
            try {
                //if the map is valid
                k = Integer.parseInt(s);
            } catch (NumberFormatException ex) {
                //if they choose a wrong map
                System.out.println("Please choose a valid map");
            }
            if (k < 0 || k > i) {
                k = 0;
                System.out.println("Please choose a valid map");
            }
        }
        //reading the map in the map class
        System.out.println("Map Selected : " + textFiles.get(k - 1));
        myMap = new Map(textFiles.get(k - 1));
        //creating the bot and the player as instances of actor
        Actor player = new Actor();
        Actor bot = new Actor();

        //Simple prints to show the user the commands available to them
        System.out.println("Type MOVE N to move North,MOVE S to move South, MOVE E to Move East, MOVE W to Move West");
        System.out.println("Type HELLO to see how much gold you need to get to WIN");
        System.out.println("Type PICKUP, to collect gold if you are on a gold tile, Type GOLD to see how much gold you have");
        System.out.println("Type QUIT if you are on an EXIT tile to finish the game if you have the required amount of gold");
        //if the user and bot have spawned in the same location change the players location until they are no longer the same
        while (bot.getLocation() == player.getLocation()) {
            player.randomLocation();
        }


        // marker to see when the game is over
        boolean GameOver = false;
        //declaring variables
        String input = "";
        input = scan.nextLine();
        int[] pLocation;
        //px,py is the player location and bx,by is the bot location
        int px;
        int py;
        int bx;
        int by;
        String output = "";
        Random rand = new Random();
        int botMove;
        while(!GameOver){
            //while the game is not over input the next command and get the user and bots locations
            input = scan.nextLine();
            pLocation = player.getLocation();
            px = pLocation[0];
            py = pLocation[1];
            pLocation = bot.getLocation();
            bx = pLocation[0];
            by = pLocation[1];
            //this is a switch statement for the input, this will check input against possible commands
            //anything which is not a command will result in the user losing their turn and the bot gaining advantage
            switch(input){
                //if the command is HELLO then we need to output how much gold is needed to win
                case "HELLO":
                    System.out.println("Gold to win: " +myMap.getGoldRequired());
                    break;
                    //Gold will display the amount of gold the user currently has
                case "GOLD":
                    System.out.println("Gold owned: " + player.getGold());
                    break;
                case "MOVE N":
                    //Prints SUCCESS or FAIL depending on if the user was able to move in the direction wanted
                    System.out.println(player.moveVertical(-1));
                    break;
                case "MOVE E":
                    System.out.println( player.moveHorizontal(1));
                    break;
                case "MOVE S":
                    System.out.println(player.moveVertical(1));
                    break;
                case "MOVE W":
                    System.out.println(player.moveHorizontal(-1));
                    break;
                case "PICKUP":
                    //if the user is on a gold tile then output success and their gold amount
                    //otherwise output fail and their gold amount
                    if (player.checkGold(px,py)) {
                        output = "SUCCESS : ";
                      }else{
                        output = "FAIL : ";
                    }
                    System.out.println( output + player.getGold());
                    break;
                case "LOOK":
                    //LOOK will display a 5x5 map
                    player.look(px,py,bx,by);
                    break;
                case "QUIT":
                    //if the user is on an exit tile then check if he can win or not, if he is not do nothing and lose a turn
                    if (player.checkExit()) {
                        if  (player.getGold() >= myMap.getGoldRequired()){
                            System.out.print("WIN");
                            System.exit(0);
                        }else{
                            System.out.print("LOSE");
                            System.exit(0);
                        }

                    }
                    else{
                        System.out.println("invalid input");
                    }
                    break;
                default:
                    System.out.println("invalid input");
            }
            //refresh the player location incase they have moved
            pLocation = player.getLocation();
            px = pLocation[0];
            py = pLocation[1];
            //compare the player and  bot location, if they are the same then the game is over
            //as the bot is on the player
            if ((px == bx)&&(py==by)) {
                GameOver = true;
            }
            //my bot will move randomly around the map
            //he will either move in any direction or stand still
            //he will stand still to simulate looking
            //a random number between 0 and 4 will decide what he does
            botMove = rand.nextInt(5 );
            switch (botMove){
                case 0:
                    bot.moveVertical(-1);
                    break;
                case 1:
                    bot.moveHorizontal(1);

                    break;
                case 2:
                    bot.moveVertical(1);
                    break;
                case 3:
                    bot.moveHorizontal(-1);
                    break;
                case 4:
                    //nothing bot is "looking"
                    break;

            }
            //refresh the bot location as he has most likely moved and again compare this to player location
            //if they are standing on the same tile then the game is over again
            pLocation = bot.getLocation();
            bx = pLocation[0];
            by = pLocation[1];
            if ((px == bx)&&(py==by)) {
                GameOver = true;
            }
        }
            // once the game is over output LOSE and end program
            System.out.println("LOSE");
            System.exit(0);

        }
    // this function is used to get the textFiles in the map file
    // this allows the user to then pick a file
    private static List<String> textFiles() {
        //here i am finding the directory of the map file which holds all the maps
        //by getting the directory of the Main class and replacing src (where main.java is stored)
        //with Maps which is where the ,maps are stored
        String directory = System.getProperty("user.dir");
        directory = directory.replace("src","Maps");
        List<String> textFiles = new ArrayList<String>();
        File dir = new File(directory);
        //For each file in this directory i add them to an array list which is then returned to main class
        //which will output them to the user at the start
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith((".txt"))) {
                textFiles.add(file.getName());
            }
        }
        return textFiles;
    }
}
