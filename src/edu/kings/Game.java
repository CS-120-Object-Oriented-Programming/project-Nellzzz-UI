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
		player = new Player(world.getRoom("mirrorchamber"));
		npcs = new HashMap<>();
		createNPCs();
	}

	/**
	 * Creates all NPCs and their riddles.
	 */
	private void createNPCs() {
		NPC cheshireCat = new NPC(
			"Cheshire Cat",
			"I am not myself, for I am a picture of that self. Face the camera!",
			"camera",
			5,
			"camera"
		);
		npcs.put("cheshirecat", cheshireCat);

		NPC whiteRabbit = new NPC(
			"White Rabbit",
			"Catch me if you can! What time is it RIGHT NOW?",
			"12:34",
			5,
			"keyboard"
		);
		npcs.put("whiterabbit", whiteRabbit);

		NPC madHatter = new NPC(
			"Mad Hatter",
			"There is no room! There is no room!",
			"drop",
			5,
			"inventory"
		);
		npcs.put("madhatter", madHatter);

		NPC caterpillar = new NPC(
			"Caterpillar",
			"Who... are... YOU? Tell me your name... BACKWARDS!",
			"",
			5,
			"backward"
		);
		npcs.put("caterpillar", caterpillar);

		NPC javaCoffee = new NPC(
			"Java Coffee Drinker",
			"What does Java need to wake up?",
			"beans",
			5,
			"standard"
		);
		npcs.put("javacoffee", javaCoffee);

		NPC legRiddle = new NPC(
			"Leg Riddler",
			"What has a bottom at the top?",
			"legs",
			5,
			"standard"
		);
		npcs.put("legs", legRiddle);

		NPC tweedleDee = new NPC(
			"Tweedledee",
			"If you need to go NORTH through the mirror, which way do you walk?",
			"south",
			5,
			"standard"
		);
		npcs.put("tweedledee", tweedleDee);
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

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
			case PACK:
				pack(command);
				break;
			case UNPACK:
				unpack(command);
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
			// if there is no second word, we don't know where to go...
			Writer.println("Go where?");
		} else {
			String direction = command.getRestOfLine();

			// Try to leave current.
			Door doorway = player.getCurrentRoom().getExit(direction);

			if (doorway == null) {
				Writer.println("There is no door!");
			} else {
				Room newRoom = doorway.getDestination();
				player.setCurrentRoom(newRoom);
				Writer.println(newRoom.toString());
		}
	}
}
	/**
	 * Print out the closing message for the player.
	 */
	private void printGoodbye() {
		Writer.println("I hope you weren't too bored here on the Campus of Kings!");
		Writer.println("Thank you for playing.  Good bye.");
		Writer.println();
		Writer.println("You have earned " + player.getScore() + " points in " + player.getTurns() + " turns.");
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		Writer.println("You are lost. You are alone. You wander");
		Writer.println("around at the university.");
		Writer.println();
		Writer.println("Your command words are:");
		Writer.println("   go quit help look status back examine take drop inventory score turns");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		Writer.println();
		Writer.println("Welcome to the Campus of Kings!");
		Writer.println("Campus of Kings is a new, incredibly boring adventure game.");
		Writer.println("Type 'help' if you need help.");
		Writer.println();
		printLocationInformation();
	}
	/**
	 * Print out the player's current status, including their current location and inventory.
	 */
	private void status() {
		Writer.println("========== STATUS ==========");
    	Writer.println("Name: " + player.getPlayerName());
		Writer.println("Sanity: " + player.getSanity() + "/" + 100);
		Writer.println("Size: " + player.getSize());
		Writer.println("Score: " + player.getScore());
		Writer.println("Turns: " + player.getTurns());
		Writer.println("============================");
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
		if (invItem instanceof Container) {
			container = (Container) invItem;
		} else {
			// Try to find container in room
			Item roomItem = player.getCurrentRoom().getItem(containerName);
			if (roomItem instanceof Container) {
				container = (Container) roomItem;
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
		if (invItem instanceof Container) {
			container = (Container) invItem;
		} else {
			// Try to find container in room
			Item roomItem = player.getCurrentRoom().getItem(containerName);
			if (roomItem instanceof Container) {
				container = (Container) roomItem;
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

		Writer.println("\n" + npc.getName() + " says: " + npc.getRiddle());
		Writer.println("Your answer:");

		String answer = Reader.getResponse();

		if (npc.getNPCType().equals("backward")) {
			String playerNameBackward = new StringBuilder(player.getPlayerName()).reverse().toString();
			npc.setCorrectAnswer(playerNameBackward);
		}

		if (npc.checkAnswer(answer)) {
			Writer.println(npc.getName() + " nods slowly. You were correct.");
			npc.setSolved(true);
			player.gainSanity(15);
			Writer.println("Sanity +15");
		} else {
			Writer.println(npc.getName() + " smiles wickedly. That's not right.");
			player.loseSanity(5);
			Writer.println("Sanity -5");

			if (player.getSanity() <= 0) {
				Writer.println("\n=== GAME OVER ===");
				Writer.println("Your sanity has shattered. The Wonderland claims another victim...");
				System.exit(0);
			}
		}
	}
}
