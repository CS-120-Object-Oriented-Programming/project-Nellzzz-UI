package edu.kings;

public class Item {
    private final String name;
    private String description;
    private final int points;
    private final double weight;

    /**
     * Constructor for Item.
     *
     * @param name The name of the item.
     * @param description The description of the item.
     * @param points The points the item is worth.
     * @param weight The weight of the item.
     */
    public Item(String name, String description, int points, double weight) {
        this.name = name;
        this.description = description;
        this.points = points;
        this.weight = weight;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the item.
     *
     * @return The description of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the item.
     *
     * @param description The new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the point value of the item.
     *
     * @return The point value.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Gets the weight of the item.
     *
     * @return The weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Checks if this item is a container.
     *
     * @return true if this item is a container, false otherwise.
     */
    public boolean isContainer() {
        return false;
    }

    /**
     * Returns the complete description of the item.
     *
     * @return String format: "name: description (Weight: weight)"
     */
    @Override
    public String toString() {
        return name + ": " + description + " (Weight: " + weight + ")";
    }
}


