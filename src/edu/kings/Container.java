package edu.kings;

import java.util.HashMap;

/**
 * A Container is an Item that can hold other items.
 */
public class Container extends Item {
    private final HashMap<String, Item> contents;

    /**
     * Constructor for Container.
     *
     * @param name The name of the container.
     * @param description The description.
     * @param points The point value.
     * @param weight The weight of the empty container.
     */
    public Container(String name, String description, int points, double weight) {
        super(name, description, points, weight);
        contents = new HashMap<>();
    }

    /**
     * Adds an item to the container.
     *
     * @param item The item to add.
     */
    public void addItem(Item item) {
        contents.put(item.getName().toLowerCase(), item);
    }

    /**
     * Gets an item from the container by name.
     *
     * @param name The name of the item.
     * @return The item or null if not found.
     */
    public Item getItem(String name) {
        return contents.get(name.toLowerCase());
    }

    /**
     * Removes an item from the container.
     *
     * @param name The name of the item.
     * @return The item that was removed or null if not found.
     */
    public Item removeItem(String name) {
        return contents.remove(name.toLowerCase());
    }

    /**
     * Gets all items in the container.
     *
     * @return HashMap of items.
     */
    public HashMap<String, Item> getContents() {
        return contents;
    }

    /**
     * Returns true if this is a container.
     *
     * @return true.
     */
    @Override
    public boolean isContainer() {
        return true;
    }

    /**
     * Returns the complete description including contents.
     *
     * @return Description with contents list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getName()).append(": ").append(getDescription());
        result.append(" (Weight: ").append(getWeight()).append(")\n");
        
        if (!contents.isEmpty()) {
            result.append("Contains: ");
            for (String itemName : contents.keySet()) {
                result.append(itemName).append(" ");
            }
        }
        
        return result.toString();
    }
}
