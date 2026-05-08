package edu.kings;

import java.util.HashMap;

/**
 * Represents the player controlled character in the game and tracks their current room,
 * inventory, score, and turns.
 */
public class Player {

    private Room currentRoom;
    private Room previousRoom;
    private int score;
    private int turns;
    private int sanity;
    private static final int MAX_SANITY = 100;
    private static final int MIN_SANITY = 0;
    private static final double MAX_CARRY_WEIGHT = 50.0;
    private final HashMap<String, Item> inventory;
    private double currentWeight;
    private String playerSize;
    private String playerName;

    /**
     * Constructor for Player.
     */
    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        this.previousRoom = null;
        this.score = 0;
        this.turns = 0;
        this.turns = 0;
        this.sanity = MAX_SANITY;
        this.inventory = new HashMap<>();
        this.currentWeight = 0;
        this.playerSize = "normal";
        this.playerName = "Alex";//Defaut can be changed 
    }
    //may need to remove getter player name 
    public String getPlayerName() {
    return playerName;
}

    public void setPlayerName(String name) {
        this.playerName = name;
}

    /**
     * Gets the current room the player is in.
     *
     * @return The current room.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Sets the current room the player is in and updates previous room.
     *
     * @param newRoom The new room.
     */
    public void setCurrentRoom(Room newRoom) {
        this.previousRoom = this.currentRoom;
        this.currentRoom = newRoom;
        this.turns++;
    }

    /**
     * Gets the previous room.
     */
    public Room getPreviousRoom() {
        return previousRoom;
    }

    /**
     * Gets the player's current score.
     *
     * @return The current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Increments the player's score by the specified amount.
     *
     * @param points The points to add.
     */
    public void incrementScore(int points) {
        this.score += points;
    }

    /**
     * Gets the number of turns taken.
     *
     * @return The current turn count.
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Adds an item to the player's inventory if weight allows.
     *
     * @param item The item to add.
     * @return true if item was added, false otherwise.
     */
    public boolean addItem(Item item) {
        if (currentWeight + item.getWeight() > MAX_CARRY_WEIGHT) {
            return false;
        }
        if (item.getWeight() > MAX_CARRY_WEIGHT) {
            return false;
        }
        inventory.put(item.getName().toLowerCase(), item);
        currentWeight += item.getWeight();
        return true;
    }

    /**
     * Gets an item from the player's inventory by name.
     *
     * @param name The name of the item.
     * @return The item or null if not found.
     */
    public Item getInventoryItem(String name) {
        return inventory.get(name.toLowerCase());
    }

    /**
     * Removes an item from the player's inventory.
     *
     * @param name The name of the item to remove.
     * @return The item that was removed or null if not found.
     */
    public Item removeInventoryItem(String name) {
        Item item = inventory.remove(name.toLowerCase());
        if (item != null) {
            currentWeight -= item.getWeight();
        }
        return item;
    }

    /**
     * Gets all items in the player's inventory.
     *
     * @return HashMap of items in inventory.
     */
    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    /**
     * Gets the player's current carry weight.
     *
     * @return The current weight.
     */
    public double getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Gets the maximum carry weight.
     *
     * @return The maximum carry weight.
     */
    public static double getMaxCarryWeight() {
        return MAX_CARRY_WEIGHT;
    }
    /*Gets Sanity. */
    public int getSanity() {
        return sanity;
    }
    public void loseSanity(int amount){
        this.sanity -= amount;
        if(this.sanity < MIN_SANITY) {
            this.sanity = MIN_SANITY;
        }
    }
    /*Increases sanity by specified amount. */
    public void gainSanity(int amount) {
        this.sanity += amount;
        if(this.sanity > MAX_SANITY) {
            this.sanity = MAX_SANITY;
        }
    }
    /* Gets the player's size. */
    public String getSize() {
        return playerSize;
    }
    public void setSize(String size) {
        this.playerSize = size;
    }
}
