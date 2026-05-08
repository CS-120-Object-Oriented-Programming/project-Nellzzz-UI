package edu.kings;

import java.util.HashMap;

/**
 * This class is the main class of the "Campus of Kings" application.
 * "Campus of Kings" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * This game class creates and initializes all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class Game {
	private final World world;
	private final Player player;
	private final HashMap<String, NPC> npcs;

	/**
	 * Create the game and initialize its internal map.
	 */
	public Game() {
		world = new World();
		player = new Player(world.getRoom("mirror chamber"));
		npcs = new HashMap<>();
		createNPCs();
	}

	/**
	 * Creates all NPCs and their riddles.
	 */
	private void createNPCs() {
		NPC cheshireCat = new NPC(
			"Cheshire Cat",
			"I am not myself for im a picture of that self not in myself. What am I?, camera + mirror + image + itself",
			"b",
			5,
			"Cheshire Cat"
		);
		npcs.put("cheshirecat", cheshireCat);

		NPC whiteRabbit = new NPC(
			"White Rabbit",
			"I run through time itself, But i always seem to try to be beside my Timly clock.But could never Gain The lively clock. Its odd my calender has the right date. MMXXVI+1 + MMV-1 + 2+MMXXVI + 2-MMV",
			"d",
			5,
			"keyboard"
		);
		npcs.put("whiterabbit", whiteRabbit);

		NPC madHatter = new NPC(
			"Mad Hatter",
			"There is no room at my table! What must you do to make room?",
			"drop",
			5,
			"inventory"
		);
		npcs.put("madhatter", madHatter);

		NPC caterpillar = new NPC(
			"Caterpillar",
			"Who are YOU? Speak your name as it would appear reversed in a mirror!",
			"",
			5,
			"backward"
		);
		npcs.put("caterpillar", caterpillar);

		NPC javaCoffee = new NPC(
			"Java Coffee Drinker",
			"What does Java programming language need to wake up in the morning?",
			"beans",
			5,
			"standard"
		);
		npcs.put("javacoffee", javaCoffee);

		NPC legRiddle = new NPC(
			"Leg Riddler",
			"What has a bottom at the top? Think of a body part...",
			"legs",
			5,
			"standard"
		);
		npcs.put("legs", legRiddle);

		NPC tweedleDee = new NPC(
			"Tweedledee",
			"The mirror shows north as south. If the mirror is wrong, which way is right?",
			"south",
			5,
			"standard"
		);
		npcs.put("tweedledee", tweedleDee);

		NPC queenOfHearts = new NPC(
			"Queen of Hearts",
			"Who is the true ruler of this twisted world?",
			"queen",
			5,
			"standard"
		);
		npcs.put("queenofhearts", queenOfHearts);
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();
		
		Writer.println();
		Writer.println("Press ENTER to begin the tutorial demo...");
		Reader.getResponse(); // Wait for player to press enter
		
		runDemo();

		// Enter the main game loop. Here we repeatedly read commands and
		// execute them until the game is over.
		boolean wantToQuit = false;
		while (!wantToQuit) {
			Command command = Reader.getCommand();
			wantToQuit = processCommand(command);
			
		}
		printGoodbye();
	}

	/**
	 * Run an interactive demo showcasing all available commands
	 */
	private void runDemo() {
		Writer.println();
		Writer.println("╔════════════════════════════════════════════════════════╗");
		Writer.println("║                  QUICK COMMAND DEMO                    ║");
		Writer.println("╚════════════════════════════════════════════════════════╝");
		Writer.println();
		Writer.println("Let me show you what you can do in this twisted world...");
		Writer.println();
		
		// DEMO 1: LOOK command
		Writer.println("[Demo 1/10] Examining your current location with LOOK:");
		Writer.println(">look");
		printLocationInformation();
		Writer.println();
		
		// DEMO 2: STATUS command
		Writer.println("[Demo 2/10] Checking your status:");
		Writer.println("> status");
		status();
		Writer.println();
		
		// DEMO 3: INVENTORY command
		Writer.println("[Demo 3/10] Checking your inventory:");
		Writer.println("> inventory");
		showInventory();
		Writer.println();
		
		// DEMO 4: SCORE command
		Writer.println("[Demo 4/10] Checking your score:");
		Writer.println("> score");
		showScore();
		Writer.println();
		
		// DEMO 5: TURNS command
		Writer.println("[Demo 5/10] Checking turn count:");
		Writer.println("> turns");
		showTurns();
		Writer.println();
		
		// DEMO 6: EXAMINE command
		Writer.println("[Demo 6/10] Examining an item in the room (candle):");
		Writer.println("> examine candle");
		Item candle = player.getCurrentRoom().getItem("candle");
		if (candle != null) {
			Writer.println("You examine the " + candle.getName() + ".");
			Writer.println(candle.getDescription());
		} else {
			Writer.println("(No candle found in this room)");
		}
		Writer.println();
		
		// DEMO 7: TAKE command
		Writer.println("[Demo 7/10] Taking an item from the room:");
		Writer.println("> take candle");
		if (player.getCurrentRoom().getItem("candle") != null) {
			Item item = player.getCurrentRoom().removeItem("candle");
			player.addItem(item);
			Writer.println("You take the " + item.getName() + ".");
		}
		Writer.println();
		
		// DEMO 8: GO command
		Writer.println("[Demo 8/10] Moving to another room (north):");
		Writer.println("> go north");
		Door doorway = player.getCurrentRoom().getExit("north");
		if (doorway != null) {
			Room newRoom = doorway.getDestination();
			Room oldRoom = player.getCurrentRoom();
			player.setCurrentRoom(newRoom);
			Writer.println(newRoom.toString());
			player.setCurrentRoom(oldRoom);
		}
		Writer.println();
		
		// DEMO 9: BACK command
		Writer.println("[Demo 9/10] Using BACK command (if you've moved):");
		Writer.println("> back");
		Writer.println("(Demonstrating - you would return to previous room)");
		Writer.println();
		
		// DEMO 10: HELP command
		Writer.println("[Demo 10/10] Getting help with available commands:");
		Writer.println("> help");
		Writer.println();
		Writer.println("AVAILABLE COMMANDS:");
		Writer.println("Movement: go <dir>, back");
		Writer.println("Exploration: look, examine <item>, help");
		Writer.println("Inventory: inventory, take <item>, drop <item>");
		Writer.println("Containers: pack <item> in <container>, unpack <item> from <container>");
		Writer.println("Doors: lock <direction>, unlock <direction>");
		Writer.println("Items: drink <item>, eat <item>");
		Writer.println("NPCs: riddle <npc>");
		Writer.println("Status: status, score, turns");
		Writer.println("Other: quit");
		Writer.println();
		
		Writer.println();
		Writer.println("╔════════════════════════════════════════════════════════╗");
		Writer.println("║              Demo Complete! Now go explore!            ║");
		Writer.println("║    Solve riddles, find items, and escape this world!   ║");
		Writer.println("╚════════════════════════════════════════════════════════╝");
		Writer.println();
		Writer.println("Type your first command and press ENTER:");
		Writer.println();
	}

	/**
	 * Prints out the current location and exits.
	 */
	private void printLocationInformation() {
		Writer.println(player.getCurrentRoom().toString());
	}
	private void back(){
		Room previousRoom = player.getPreviousRoom();
		if (previousRoom == null) {
			Writer.println("You haven't moved yet!");
		} else {
			player.setCurrentRoom(previousRoom);
			printLocationInformation();
		}
	}


	///////////////////////////////////////////////////////////////////////////
	// Helper methods for processing the commands

	/**
	 * Given a command, process (that is: execute) the command.
	 *
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;
		if (command.isUnknown()) {
			Writer.println("I don't know what you mean...");
			return false;
		}

		switch (command.getCommandWord()) {
			case GO:
				goRoom(command);
				break;
			case QUIT:
				wantToQuit = quit(command);
				break;
			case HELP:
				printHelp();
				break;
			case LOOK:
				printLocationInformation();
				break;
			case STATUS:
				status();
				break;
			case BACK:
				back();
				break;
			case DROP:
				drop(command);
				break;
			case EXAMINE:
				examine(command);
				break;
			case INVENTORY:
				showInventory();
				break;
			case TAKE:
				take(command);
				break;
			case SCORE:
				showScore();
				break;
			case TURNS:
				showTurns();
				break;
			case LOCK:
				lock(command);
				break;
			case UNLOCK:
				unlock(command);
				break;
			case DRINK:
				drink(command);
				break;
			case EAT:
				eat(command);
				break;
			case RIDDLE:
				riddle(command);
				break;
			default:
				Writer.println("I don't know what you mean...");
		}
		return wantToQuit;
	}

	////////////////////////////////////////////////////////////////////////////
	// Helper methods for implementing all of the commands.
	// It helps if you organize these in alphabetical order.

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void goRoom(Command command) {
    if (!command.hasSecondWord()) {
        Writer.println("Go where? Use: north, south, east, west (or up/down for elevator)");
        return;
			// if there is no second word, we don't know where to go...
		} else {
			String direction = command.getRestOfLine().toLowerCase();

			// Try to leave current.
			Door doorway = player.getCurrentRoom().getExit(direction);

			if (doorway == null) {
				Writer.println("There is no door to the " + direction + "!");
				Writer.println("Available exits: " + getAvailableExits());
			} else {
				Room newRoom = doorway.getDestination();
				player.setCurrentRoom(newRoom);
				Writer.println();
				Writer.println("You travel " + direction + "...");
				Writer.println();
				Writer.println(newRoom.toString());
		}
	}
}
	/**
	 * Print out the closing message for the player.
	 */
	private void printGoodbye() {
		Writer.println();
		Writer.println("╔════════════════════════════════════════════════════════╗");
		Writer.println("║          YOU ESCAPE FROM WONDERLAND                   ║");
		Writer.println("╚════════════════════════════════════════════════════════╝");
		Writer.println();
		Writer.println("You stumble back through the mirror...");
		Writer.println();
		Writer.println("Thank you for playing Alex's Twisted Wonderland!");
		Writer.println();
		Writer.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		Writer.println("Final Statistics:");
		Writer.println("  Points Earned: " + player.getScore());
		Writer.println("  Turns Taken: " + player.getTurns());
		Writer.println("  Final Sanity: " + player.getSanity() + "/100");
		Writer.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		Writer.println();
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		Writer.println();
		Writer.println("╔════════════════════════════════════════════════════════╗");
		Writer.println("║                  AVAILABLE COMMANDS                    ║");
		Writer.println("╚════════════════════════════════════════════════════════╝");
		Writer.println();
		Writer.println("Movement:");
		Writer.println("  go 			        - Move in a direction (north, south, east, west)");
		Writer.println("  back                 - Return to the previous room");
		Writer.println();
		Writer.println("Exploration:");
		Writer.println("  look                 - Examine the current room");
		Writer.println("  examine 		        - Look closely at an item");
		Writer.println();
		Writer.println("Inventory:");
		Writer.println("  inventory            - Show what you're carrying");
		Writer.println("  take                 - Pick up an item");
		Writer.println("  drop                 - Drop an item");
		Writer.println();
		Writer.println("Containers:");
		Writer.println("  pack        in               - Store an item");
		Writer.println("  unpack      from             - Retrieve an item");
		Writer.println();
		Writer.println("Doors:");
		Writer.println("  lock                 - Lock a door");
		Writer.println("  unlock               - Unlock a door with a key");
		Writer.println();
		Writer.println("Consumables:");
		Writer.println("  drink                - Drink a potion (resize yourself)");
		Writer.println("  eat                  - Eat food (resize yourself)");
		Writer.println();
		Writer.println("NPCs:");
		Writer.println("  riddle <npc>         - Talk to an NPC and solve their riddle");
		Writer.println();
		Writer.println("Status:");
		Writer.println("  status               - View your stats (name, sanity, size, score)");
		Writer.println("  score                - View your current score");
		Writer.println("  turns                - View number of turns taken");
		Writer.println();
		Writer.println("Other:");
		Writer.println("  help                 - Show this help message");
		Writer.println("  quit                 - Exit the game");
		Writer.println();
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		Writer.println();
		Writer.println("╔════════════════════════════════════════════════════════╗");
		Writer.println("║   WELCOME TO ALEX'S TWISTED WONDERLAND                 ║");
		Writer.println("║   A Text-Based Adventure Through Madness               ║");
		Writer.println("╚════════════════════════════════════════════════════════╝");
		Writer.println();
		Writer.println("You wake up in front of a strange mirror. Nothing seems right.");
		Writer.println("The air is thick with mystery and your reflection stares back");
		Writer.println("With unsettling wonder...");
		Writer.println();
		Writer.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		Writer.println();
		
		Writer.print("But first... what is your name, brave traveler? ");
		String name = Reader.getResponse();
		player.setPlayerName(name);
		
		Writer.println();
		Writer.println("╔════════════════════════════════════════════════════════╗");
		Writer.println("║                    Welcome, " + padString(name, 35) +   "║");
		Writer.println("╚════════════════════════════════════════════════════════╝");
		Writer.println();
		Writer.println("Sanity Meter: " + player.getSanity() + "/100");
		Writer.println("Current Size: " + player.getSize());
		Writer.println();
		Writer.println("You stand in the Mirror Chamber, facing an gimmering mirror.");
		Writer.println();
	}
	/**
	 * Print out the player's current status, including their current location and inventory.
	 */
	private void status() {
		Writer.println();
		Writer.println("╔════════════════════════════════════════════════════════╗");
		Writer.println("║                      YOUR STATUS                       ║");
		Writer.println("╚════════════════════════════════════════════════════════╝");
		Writer.println("  Name:     " + player.getPlayerName());
		Writer.println("  Sanity:   " + player.getSanity() + "/100");
		Writer.println("  Size:     " + player.getSize());
		Writer.println("  Score:    " + player.getScore());
		Writer.println("  Turns:    " + player.getTurns());
		Writer.println("╔════════════════════════════════════════════════════════╗");
		Writer.println();
    	Writer.println();
    	printLocationInformation();
	}
	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game.
	 *
	 * @param command
	 *            The command to be processed.
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		boolean wantToQuit = true;
		if (command.hasSecondWord()) {
			Writer.println("Quit what?");
			wantToQuit = false;
		}
		return wantToQuit;
	}
	/**
	 * Displays the player's current score.
	 */
	private void showScore() {
		Writer.println("Your score is: " + player.getScore());
	}

	/**
	 * Displays the number of turns taken.
	 */
	private void showTurns() {
		Writer.println("You have taken " + player.getTurns() + " turns.");
	}

	/**
	 * Displays the player's inventory.
	 */
	private void showInventory() {
		if (player.getInventory().isEmpty()) {
			Writer.println("You are not carrying anything.");
		} else {
			Writer.println("You are carrying:");
			for (Item item : player.getInventory().values()) {
				Writer.println("  " + item.getName());
			}
		}
	}

	/**
	 * Examines an item in the room or inventory.
	 *
	 * @param command The examine command.
	 */
	private void examine(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Examine what?");
			return;
		}
		
		String itemName = command.getRestOfLine();
		Item item = player.getCurrentRoom().getItem(itemName);
		
		if (item == null) {
			item = player.getInventoryItem(itemName);
		}
		
		if (item == null) {
			Writer.println("You don't see that item.");
		} else {
			Writer.println(item.toString());
		}
	}

	/**
	 * Takes an item from the current room.
	 *
	 * @param command The take command.
	 */
	private void take(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Take what?");
			return;
		}
		
		String itemName = command.getRestOfLine();
		Item item = player.getCurrentRoom().getItem(itemName);
		
		if (item == null) {
			Writer.println("You don't see that item.");
			return;
		}
		
		if (item.getWeight() > Player.getMaxCarryWeight()) {
			Writer.println("That item is too heavy to lift.");
			return;
		}
		
		if (!player.addItem(item)) {
			Writer.println("You are carrying too much.");
			return;
		}
		
		player.getCurrentRoom().removeItem(itemName);
		Writer.println("You took the " + itemName + ".");
	}

	/**
	 * Drops an item from the player's inventory.
	 *
	 * @param command The drop command.
	 */
	private void drop(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Drop what?");
			return;
		}
		
		String itemName = command.getRestOfLine();
		Item item = player.removeInventoryItem(itemName);
		
		if (item == null) {
			Writer.println("You don't have that item.");
			return;
		}
		
		player.getCurrentRoom().addItem(item);
		Writer.println("You dropped the " + itemName + ".");
	}

	/**
	 * Locks a door in the current room.
	 *
	 * @param command The lock command.
	 */
	private void lock(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Lock what?");
			return;
		}
		
		String direction = command.getRestOfLine();
		Door door = player.getCurrentRoom().getExit(direction);
		
		if (door == null) {
			Writer.println("There is no door in that direction.");
			return;
		}
		
		if (door.isLocked()) {
			Writer.println("That door is already locked.");
			return;
		}
		
		door.setLocked(true);
		Writer.println("You locked the door to the " + direction + ".");
	}

	/**
	 * Unlocks a door in the current room.
	 *
	 * @param command The unlock command.
	 */
	private void unlock(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Unlock what?");
			return;
		}
		
		String direction = command.getRestOfLine();
		Door door = player.getCurrentRoom().getExit(direction);
		
		if (door == null) {
			Writer.println("There is no door in that direction.");
			return;
		}
		
		if (!door.isLocked()) {
			Writer.println("That door is already unlocked.");
			return;
		}
		
		door.setLocked(false);
		Writer.println("You unlocked the door to the " + direction + ".");
	}

	/**
	 * Packs an item from the room or inventory into a container.
	 *
	 * @param command The pack command.
	 */
	private void pack(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Pack what?");
			return;
		}
		
		String[] parts = command.getRestOfLine().split(" ");
		if (parts.length < 3 || !parts[parts.length - 2].equals("in")) {
			Writer.println("Usage: pack <item> in <container>");
			return;
		}
		
		String containerName = parts[parts.length - 1];
		Container container = null;
		
		// Try to find container in inventory first
		Item invItem = player.getInventoryItem(containerName);
		if (invItem instanceof Container container1) {
			container = container1;
		} else {
			// Try to find container in room
			Item roomItem = player.getCurrentRoom().getItem(containerName);
			if (roomItem instanceof Container container1) {
				container = container1;
			}
		}
		
		if (container == null) {
			Writer.println("You don't see a container called " + containerName + ".");
			return;
		}
		
		// Get the item to pack (everything before "in")
		StringBuilder itemNameBuilder = new StringBuilder();
		for (int i = 0; i < parts.length - 2; i++) {
			if (i > 0) itemNameBuilder.append(" ");
			itemNameBuilder.append(parts[i]);
		}
		String itemName = itemNameBuilder.toString();
		
		Item item = player.getInventoryItem(itemName);
		if (item == null) {
			item = player.getCurrentRoom().getItem(itemName);
		}
		
		if (item == null) {
			Writer.println("You don't see that item.");
			return;
		}
		
		container.addItem(item);
		player.removeInventoryItem(itemName);
		player.getCurrentRoom().removeItem(itemName);
		Writer.println("You packed the " + itemName + " into the " + containerName + ".");
	}

	/**
	 * Unpacks an item from a container.
	 *
	 * @param command The unpack command.
	 */
	private void unpack(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Unpack what?");
			return;
		}
		
		String[] parts = command.getRestOfLine().split(" ");
		if (parts.length < 3 || !parts[parts.length - 2].equals("from")) {
			Writer.println("Usage: unpack <item> from <container>");
			return;
		}
		
		String containerName = parts[parts.length - 1];
		Container container = null;
		
		// Try to find container in inventory first
		Item invItem = player.getInventoryItem(containerName);
		if (invItem instanceof Container container1) {
			container = container1;
		} else {
			// Try to find container in room
			Item roomItem = player.getCurrentRoom().getItem(containerName);
			if (roomItem instanceof Container container1) {
				container = container1;
			}
		}
		
		if (container == null) {
			Writer.println("You don't see a container called " + containerName + ".");
			return;
		}
		
		// Get the item to unpack (everything before "from")
		StringBuilder itemNameBuilder = new StringBuilder();
		for (int i = 0; i < parts.length - 2; i++) {
			if (i > 0) itemNameBuilder.append(" ");
			itemNameBuilder.append(parts[i]);
		}
		String itemName = itemNameBuilder.toString();
		
		Item item = container.getItem(itemName);
		if (item == null) {
			Writer.println("That item is not in the " + containerName + ".");
			return;
		}
		
		container.removeItem(itemName);
		player.getCurrentRoom().addItem(item);
		Writer.println("You unpacked the " + itemName + " from the " + containerName + ".");


		}

		/**
	 * Drink an item (for resizing).
	 */
	private void drink(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Drink what?");
			return;
		}
		String itemName = command.getRestOfLine();
		Item item = player.getInventoryItem(itemName);

		if (item == null) {
			Writer.println("You don't have that item.");
			return;
		}
		if (itemName.toLowerCase().contains("drinkme")) {
			if (player.getSize().equals("small")) {
				Writer.println("You're already small!");
				return;
			}
			player.setSize("small");
			Writer.println("You drink the potion... the world grows HUGE around you.");
			Writer.println("You are now SMALL. You can access crawlspaces!");
			player.removeInventoryItem(itemName);
		} else {
			Writer.println("You can't drink that.");
		}
	}

	/**
	 * Eat an item (for resizing).
	 */
	private void eat(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Eat what?");
			return;
		}
		String itemName = command.getRestOfLine();
		Item item = player.getInventoryItem(itemName);

		if (item == null) {
			Writer.println("You don't have that item.");
			return;
		}
		if (itemName.toLowerCase().contains("eatme")) {
			if (player.getSize().equals("normal")) {
				Writer.println("You're already normal size!");
				return;
			}
			player.setSize("normal");
			Writer.println("You eat the cake... the world shrinks around you.");
			Writer.println("You are now NORMAL size again.");
			player.removeInventoryItem(itemName);
		} else {
			Writer.println("You can't eat that.");
		}
	}
	/**
	 * Interact with NPCs and solve riddles.
	 */
	private void riddle(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Talk to whom?");
			return;
		}

		String npcName = command.getRestOfLine().toLowerCase();
		NPC npc = npcs.get(npcName);

		if (npc == null) {
			Writer.println("That person is not here.");
			return;
		}

		if (npc.isSolved()) {
			Writer.println(npc.getName() + " has nothing more to say.");
			return;
		}
		

		Writer.println();
		Writer.println("► " + npc.getName() + " gazes at you with a curious expression...");
		Writer.println();
		Writer.println(npc.getRiddle());
		Writer.println();
		Writer.println("What is your answer?");
		Writer.println("(A) First option");
		Writer.println("(B) Second option");
		Writer.println("(C) Third option");
		Writer.println("(D) Fourth option");
		Writer.println();
		Writer.print("Your choice (A/B/C/D): ");

		String answer = Reader.getResponse();

		if (npc.getNPCType().equals("backward")) {
			String playerNameBackward = new StringBuilder(player.getPlayerName()).reverse().toString();
			npc.setCorrectAnswer(playerNameBackward);
		}

		if (npc.checkAnswer(answer)) {
			Writer.println();
			Writer.println("✓ " + npc.getName() + " nods slowly... You were correct!");
			npc.setSolved(true);
			player.gainSanity(15);
			Writer.println("Sanity +15");
		} else {
			Writer.println();
			Writer.println("✗ " + npc.getName() + " smiles wickedly. That's not right...");
			player.loseSanity(5);
			Writer.println("Sanity -5");

			if (player.getSanity() <= 0) {
				Writer.println();
				Writer.println("╔════════════════════════════════════════════════════════╗");
				Writer.println("║                    GAME OVER                           ║");
				Writer.println("║  Your sanity has shattered. Wonderland claims you...   ║");
				Writer.println("╚════════════════════════════════════════════════════════╝");
				System.exit(0);
			}
		}
		Writer.println();
	}

	/**
	 * Helper method to pad a string to a certain length
	 */
	private String padString(String str, int length) {
		if (str.length() >= length) {
			return str.substring(0, length);
		}
		int padding = length - str.length();
		int leftPad = padding / 2;
		int rightPad = padding - leftPad;
		return " ".repeat(leftPad) + str + " ".repeat(rightPad);
	}

	/**
	 * Helper method to get available exits as a string
	 */
	private String getAvailableExits() {
		Room current = player.getCurrentRoom();
		StringBuilder exits = new StringBuilder();
		boolean first = true;
		
		// Check in compass order: north, east, south, west
		String[] directions = {"north", "south", "east", "west", "up", "down"};
		for (String dir : directions) {
			if (current.getExit(dir) != null) {
				if (!first) exits.append(", ");
				exits.append(dir);
				first = false;
			}
		}
		
		return exits.toString();
	}
}
