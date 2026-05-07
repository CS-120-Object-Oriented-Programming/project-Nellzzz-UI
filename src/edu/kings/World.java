package edu.kings;
import java.util.HashMap;

/**
 * This class represents the entire world that makes up the "Campus of Kings"
 * application. "Campus of Kings" is a very simple, text based adventure game.
 * Users can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 *
 * This world class creates the world where the game takes place.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class World {
	/** The rooms in the world. */
	private final HashMap<String, Room> rooms;

	/**
	 * Constructor for the world.
	 */
	public World() {
		rooms = new HashMap<>();
		createRooms();
	}

	/**
	 * Gets a room by name.
	 *
	 * @param name The provided name of the room.
	 * @return The room associated with the provided name
	 */
	public Room getRoom(String name) {
		return rooms.get(name.toLowerCase());
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// Private helper methods

	/**
	 * Helper method for adding a Room to the world.
	 *
	 * @param theRoom The room to add to the world.
	 */
	private void addRoom(Room theRoom) {
		rooms.put(theRoom.getName().toLowerCase(), theRoom);
	}

	/**
	 * Helper method for creating doors between rooms.
	 *
	 * @param from The room where the door originates.
	 * @param direction The direction of the door in the from room.
	 * @param to The room where the door goes.
	 */
	private void createDoor(Room from, String direction, Room to) {
		from.setExit(direction, new Door(to));
	}

	/**
	 * This method creates all of the individual places in this world and all
	 * the doors connecting them.
	 */
	private void createRooms() {
		// FLOOR 1 - Main Wonderland Rooms
		Room mirrorChamber = new Room("Mirror Chamber", "You stand before an ornate mirror. Your reflection stares back, but something feels... wrong.");
		addRoom(mirrorChamber);

		Room purpleRoom = new Room("Purple Room", "You are in a purple kitchen that smells of burnt pepper. A treasure chest sits on the table, and a Cheshire Cat grins from atop it!");
		addRoom(purpleRoom);

		Room blueRoom = new Room("Blue Room", "You are in a room filled with ticking clocks. Clockwork animals move around you.");
		addRoom(blueRoom);

		Room yellowRoom = new Room("Yellow Room", "You are in a room with a floor of quicksand. It pulls at your feet with each step.");
		addRoom(yellowRoom);

		Room orangeRoom = new Room("Orange Room", "You are in a toy room overcrowded with dolls that whisper when you are not looking.");
		addRoom(orangeRoom);

		Room redRoom = new Room("Red Room", "You are in the Card Throne, where playing card guards stand at attention.");
		addRoom(redRoom);

		Room greenRoom = new Room("Green Room", "You are in an impossible garden where flowers speak riddles.");
		addRoom(greenRoom);

		Room blackRoom = new Room("Black Room", "You are in a shadowy hall where darkness seems to have weight.");
		addRoom(blackRoom);

		Room elevatorRoom = new Room("Elevator Room", "You are in a grand elevator chamber with mirrors on every wall.");
		addRoom(elevatorRoom);

		// FLOOR 2 - Mirrored Rooms
		Room floor2Mirror = new Room("Floor 2 Mirror", "You stand before another mirror, but this one shows a different reflection.");
		addRoom(floor2Mirror);

		Room floor2Purple = new Room("Floor 2 Purple", "You are in a mirrored kitchen. Everything is reversed.");
		addRoom(floor2Purple);

		Room floor2Blue = new Room("Floor 2 Blue", "You are in a mirrored clock room. The ticking sounds backwards.");
		addRoom(floor2Blue);

		Room floor2Yellow = new Room("Floor 2 Yellow", "You are in a mirrored quicksand room. The sand flows upward.");
		addRoom(floor2Yellow);

		Room floor2Orange = new Room("Floor 2 Orange", "You are in a mirrored toy room with silent dolls.");
		addRoom(floor2Orange);

		Room floor2Red = new Room("Floor 2 Red", "You are in a mirrored Card Throne.");
		addRoom(floor2Red);

		Room floor2Green = new Room("Floor 2 Green", "You are in a mirrored garden with opposite flowers.");
		addRoom(floor2Green);

		Room floor2Black = new Room("Floor 2 Black", "You are in a mirrored shadow hall where light has weight.");
		addRoom(floor2Black);

		Room throneRoom = new Room("Throne Room", "You stand before the Queen of Hearts. This is the end.");
		addRoom(throneRoom);

		// CRAWLSPACES - Small rooms accessible when small
		Room hiddenCellar = new Room("Hidden Cellar", "You are in a small hidden cellar beneath the kitchen. It smells of earth and secrets.");
		addRoom(hiddenCellar);

		Room hiddenAttic = new Room("Hidden Attic", "You are in a cramped attic above the clock room. Dust particles dance in strange patterns.");
		addRoom(hiddenAttic);

		Room hiddenChamber = new Room("Hidden Chamber", "You are in a mysterious chamber hidden behind the toys. Strange machinery hums.");
		addRoom(hiddenChamber);

		// Connect FLOOR 1 rooms
		this.createDoor(mirrorChamber, "north", purpleRoom);
		this.createDoor(purpleRoom, "south", mirrorChamber);
		
		this.createDoor(purpleRoom, "east", blueRoom);
		this.createDoor(blueRoom, "west", purpleRoom);
		
		this.createDoor(blueRoom, "north", yellowRoom);
		this.createDoor(yellowRoom, "south", blueRoom);
		
		this.createDoor(yellowRoom, "east", orangeRoom);
		this.createDoor(orangeRoom, "west", yellowRoom);
		
		this.createDoor(orangeRoom, "north", redRoom);
		this.createDoor(redRoom, "south", orangeRoom);
		
		this.createDoor(redRoom, "east", greenRoom);
		this.createDoor(greenRoom, "west", redRoom);
		
		this.createDoor(greenRoom, "north", blackRoom);
		this.createDoor(blackRoom, "south", greenRoom);
		
		this.createDoor(blackRoom, "east", elevatorRoom);
		this.createDoor(elevatorRoom, "west", blackRoom);

		// Connect FLOOR 2 rooms (mirrored)
		this.createDoor(elevatorRoom, "up", floor2Mirror);
		this.createDoor(floor2Mirror, "down", elevatorRoom);
		
		this.createDoor(floor2Mirror, "north", floor2Purple);
		this.createDoor(floor2Purple, "south", floor2Mirror);

		this.createDoor(floor2Purple, "east", floor2Blue);
		this.createDoor(floor2Blue, "west", floor2Purple);
		
		this.createDoor(floor2Blue, "north", floor2Yellow);
		this.createDoor(floor2Yellow, "south", floor2Blue);
		
		this.createDoor(floor2Yellow, "east", floor2Orange);
		this.createDoor(floor2Orange, "west", floor2Yellow);
		
		this.createDoor(floor2Orange, "north", floor2Red);
		this.createDoor(floor2Red, "south", floor2Orange);
		
		this.createDoor(floor2Red, "east", floor2Green);
		this.createDoor(floor2Green, "west", floor2Red);
		
		this.createDoor(floor2Green, "north", floor2Black);
		this.createDoor(floor2Black, "south", floor2Green);
		
		this.createDoor(floor2Black, "east", throneRoom);
		this.createDoor(throneRoom, "west", floor2Black);

		// Connect CRAWLSPACES (accessible when small)
		this.createDoor(purpleRoom, "down", hiddenCellar);
		this.createDoor(hiddenCellar, "up", purpleRoom);
		
		this.createDoor(blueRoom, "down", hiddenAttic);
		this.createDoor(hiddenAttic, "up", blueRoom);
		
		this.createDoor(orangeRoom, "down", hiddenChamber);
		this.createDoor(hiddenChamber, "up", orangeRoom);

		createItems();
	}

	/**
	 * Helper method to populate rooms with items.
	 */
	
	private void createItems() {
		// Mirror Chamber items
		Item candle = new Item("Candle", "A white candle that flickers without reason", 5, 0.1);
		rooms.get("mirror chamber").addItem(candle);

		// Purple Room items
		Item drinkMe = new Item("Drink Me", "A bottle labeled 'DRINK ME!' in elegant script", 10, 0.1);
		rooms.get("purple room").addItem(drinkMe);

		Item floorboard = new Item("Floorboard", "A loose wooden floorboard. Something is hidden beneath...", 0, 0.1);
		rooms.get("purple room").addItem(floorboard);

		// Create treasure chest container
		Container treasureChest = new Container("Chest", "An extraordinary treasure chest with a brass lock", 0, 15.0);
		Item matches = new Item("Matches", "A box of damp matches that can be used to light a candle", 0, 0.5);
		treasureChest.addItem(matches);
		rooms.get("purple room").addItem(treasureChest);

		// Blue Room items
		Item silverKey = new Item("Silver Key", "A worn but gleaming silver key. Wonder when it was last used?", 15, 0.1);
		Item pocketWatch = new Item("Pocket Watch", "A pocket watch that ticks backwards", 10, 0.1);
		Item compass = new Item("Compass", "A compass that points in the wrong direction. Rather unfortunate.", 5, 0.5);
		rooms.get("blue room").addItem(silverKey);
		rooms.get("blue room").addItem(pocketWatch);
		rooms.get("blue room").addItem(compass);

		// Yellow Room items
		Item eatMe = new Item("Eat Me", "A small cake with cream. The words 'EAT ME' written on it.", 10, 0.5);
		Item hourglass = new Item("Hourglass", "An hourglass with black sand", 20, 1.0);
		rooms.get("yellow room").addItem(eatMe);
		rooms.get("yellow room").addItem(hourglass);

		// Orange Room items
		Container musicBox = new Container("Music Box", "A beautiful music box with a tiny dancing figure", 25, 8.0);
		Item roboticToy = new Item("Robotic Toy", "A small robotic toy that moves when wound", 15, 2.0);
		musicBox.addItem(roboticToy);
		Item doll = new Item("Doll", "A porcelain doll with too many eyes", 10, 1.0);
		rooms.get("orange room").addItem(musicBox);
		rooms.get("orange room").addItem(doll);

		// Red Room items
		Item cardCrown = new Item("Card Crown", "A crown made of playing cards", 20, 0.2);
		Item redCard = new Item("Red Card", "A single red playing card (Heart Ace)", 5, 0.1);
		rooms.get("red room").addItem(cardCrown);
		rooms.get("red room").addItem(redCard);

		// Green Room items
		Item flower = new Item("Flower", "A flower that whispers secrets", 10, 0.1);
		Item seeds = new Item("Seeds", "Mysterious seeds that glow faintly", 8, 0.05);
		rooms.get("green room").addItem(flower);
		rooms.get("green room").addItem(seeds);

		// Black Room items
		Item shadowKey = new Item("Shadow Key", "A key made of solidified shadow", 30, 0.5);
		rooms.get("black room").addItem(shadowKey);

		// Elevator Room items
		Item loadingIcon = new Item("Loading", "....", 0, 0.1);
		rooms.get("elevator room").addItem(loadingIcon);

		// Floor 2 items
		Item candle2 = new Item("Candle", "A white candle that flickers", 5, 0.1);
		rooms.get("floor 2 mirror").addItem(candle2);

		Item drinkMe2 = new Item("Drink Me", "A bottle labeled 'DRINK ME!'", 10, 0.1);
		rooms.get("floor 2 purple").addItem(drinkMe2);

		Item goldKey = new Item("Gold Key", "A gleaming gold key", 20, 0.1);
		rooms.get("floor 2 blue").addItem(goldKey);

		Item eatMe2 = new Item("Eat Me", "A slice of cake labeled 'EAT ME'", 10, 0.5);
		rooms.get("floor 2 yellow").addItem(eatMe2);

		// Throne Room - final prize
		Item crown = new Item("Queen Crown", "The Crown of Hearts - the final prize", 100, 2.0);
		rooms.get("throne room").addItem(crown);

		// Hidden Crawlspace items
		Item crystal = new Item("Crystal", "A glowing crystal hidden in the cellar", 50, 0.5);
		rooms.get("hidden cellar").addItem(crystal);

		Item journal = new Item("Journal", "A journal with fragmented memories", 40, 0.5);
		rooms.get("hidden attic").addItem(journal);

		Item mechanism = new Item("Mechanism", "A strange mechanical device", 60, 3.0);
		rooms.get("hidden chamber").addItem(mechanism);
	}
}
