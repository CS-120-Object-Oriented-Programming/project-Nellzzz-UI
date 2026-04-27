package edu.kings;
/**
 * Represents the player controlled character in the game and tracks their current room
 */

public class Player {

    private Room currentRoom;
    private Room previousRoom;
    private final int score = 0;
    private final int turns = 0;
    /**
     * the room where the player begins the game
     */
    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        this.previousRoom = null;
    }
    /**
     * Gets the current room the player is in
     * @return the current room
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }
    /**
     * Sets the current room the player is in
     * @param newRoom the new room
     */
    public void setCurrentRoom(Room newRoom) {
        previousRoom = currentRoom;
        currentRoom = newRoom;
    }
    /**
     * Sets the previous room
     */    /**
     * Gets the previous room
     */
    public Room getPreviousRoom(){
        return previousRoom;
    }

    /**
     * Gets the player's current score
     */
    public int getScore() {
        return score;
    }

    /**
     * get the number of turns taken
     */
    public int getTurns() {
        return turns;
    }
}
