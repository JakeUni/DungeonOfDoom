//importing the neccessary libraries
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//this is the map class which will create and store a map
public class Map {
    /* Representation of the map */
    private char[][] map;
    /* Map name */
    private String mapName;
    /* Gold required for the human player to win */
    private int goldRequired;

    //upon creation of an instance read the file passed
    public Map(String fileName) {
        readMap(fileName);
    }
    //This is a getter which returns the amount of gold required
    protected int getGoldRequired() {
        return goldRequired;
    }
    //This will return the map as a 2d array
    protected char[][] getMap() {
        return map;
    }

    //This is the function which will read the file
    protected void readMap(String fileName) {
        //creating the path for the directory
        String directory = System.getProperty("user.dir");
        directory = directory.replace("src","Maps/");
        directory = directory + fileName;

        //creating the reader and some counter variables
        BufferedReader reader;
        int i = 0;
        int j = 0;
        int k = 0;
        String gold = "" ;

        try {
            // passsing the directory to the reader
            reader = new BufferedReader(new FileReader(directory));
            //skipping the first line as it is irrelevant
            String line = reader.readLine();
            line = reader.readLine();
            //this is the line which has the amount of gold needed to win on it
            //we start where the amount of gold is and read every character after
            //this is incase the amount of gold is double figure or even triple (as a just incase)
            for (int m = 4; m < line.length();m++){
                gold = gold + line.charAt(m);
            }
            //this is then passed to gold required
            goldRequired = Integer.parseInt(gold);
            line = reader.readLine();
            k = line.length();
            //k is the line length meaning it is the amount of x
            while (line != null) {
                //for every line increment i (the amount of y)
                line = reader.readLine();
                i++;
            }
            //restart the reader now that we know the amount of x and y
            reader = new BufferedReader(new FileReader(directory));
            line = reader.readLine();
            line = reader.readLine();
            line = reader.readLine();
            //skip the first 2 lines as they are not the map

            //creating a new map with the x,y found above
            map = new char[k][i];

            for(int a = 0; a < i ; a++){
                //loop for every y

                for (int b = 0; b < k; b++){
                    //loop for each x in y
                    //assign the x,y to map
                    map[b][a] = line.charAt(b);
                }
                //next y
                line = reader.readLine();
            }
            //close the reader
            reader.close();
        } catch (IOException e) {
            //if there is an error inform the user to contact the admin
            System.out.print("Map error, please contact administrator");
        }
    }

}
