package edu.kings;
import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via doors. The doors are labeled north, east, south, west.
 * For each direction, the room stores a reference to an instance of door.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */


public class Room {
	
	/** Counter for the total number of rooms created in the world. */
	private static int counter;
	/** The name of this room.  Room names should be unique. */
	private final String name;
	/** The description of this room. */
	private final String description;

	private HashMap<String, Door> exits;
	private HashMap<String, Item> items;


	/**
	 * Static initializer.
	 */
	static {
		counter = 0;
	}
	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 *
	 * @param name  The room's name.
	 * @param description
	 *            The room's description.
	 */
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		exits = new HashMap<String, Door>();
		items = new HashMap<String, Item>();
		counter++;
	}

	/**
	 * Returns the name of this room.
	 *
	 * @return The name of this room.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the description of this room.
	 *
	 * @return The description of this room.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the number of rooms that have been created in the world.
	 * @return The number of rooms that have been created in the world.
	 */
	public static int getCounter() {
		return counter;
	}
	public void setExit(String direction, Door neighbor) {
		exits.put(direction, neighbor);
	}
	public Door getExit(String direction) {
		return exits.get(direction);
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(name).append(":\n");
		result.append("You are ").append(description).append("\n");
		
		if (!items.isEmpty()) {
			result.append("Items: ");
			for (String itemName : items.keySet()) {
				result.append(itemName).append(" ");
			}
			result.append("\n");
		}
		
		result.append("Exits: ");
		for (String direction : exits.keySet()) {
			result.append(direction).append(" ");
		}
		result.append("\n");
		return result.toString();
	}

	/**
	 * Adds an item to the room.
	 *
	 * @param item The item to add.
	 */
	public void addItem(Item item) {
		items.put(item.getName().toLowerCase(), item);
	}

	/**
	 * Gets an item from the room by name.
	 *
	 * @param name The name of the item.
	 * @return The item or null if not found.
	 */
	public Item getItem(String name) {
		return items.get(name.toLowerCase());
	}

	/**
	 * Removes an item from the room by name.
	 *
	 * @param name The name of the item to remove.
	 * @return The item that was removed or null if not found.
	 */
	public Item removeItem(String name) {
		return items.remove(name.toLowerCase());
	}
}
