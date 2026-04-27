package edu.kings;
/**
 * Represents the player controlled character in the game and tracks their current room
 */

public class Player {

    private Room currentRoom;
    /**
     * the room where the player begins the game
     */
    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
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
        this.currentRoom = newRoom;
    }
}
