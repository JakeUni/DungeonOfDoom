How to run :
	Have java installed
	navigate to the src folder 
	compile using "javac *.java"
	run using "java Main"
	The program will now run 
How to play:
	You will be prompted with a series of maps, enter the number of the map you wish to play and you will start
	Type MOVE followed by : N,E,S,W to move north east south or west
	Type HELLO to get the amount of gold you need to complete the map
	Type GOLD to get the amount of gold that you currently have 
	Type PICKUP to pickup gold if you are on a gold tile 
	Type LOOK to see a 5x5 area around the player
	Type QUIT to end the game if you are on an exit tile
	Any other command will result in you losing your turn , commands are case sensitive
Ending The game
	The game will end if you type QUIT on an exit tile, if you have enough gold to beat the map you will win
	otherwise you will lose
	The game will also end if the bot catches you resulting in a loss
Look function
	'.' is an empty tile which you can move onto
	'#' is a wall that you cannot move onto
	'P' this is the player (you)
	'B' this is the bot (enemy)
	'G' this is a gold tile (you want to collect these)
	'E' this is an exit tile (where you type "QUIT")
Adding MAPS
	If you would like to add a custom map, please follow the format of the other maps
	The first line will be the maps name
	The second line will be the amount of gold to win
	The rest will be the map
	The map must be a rectangle in shape