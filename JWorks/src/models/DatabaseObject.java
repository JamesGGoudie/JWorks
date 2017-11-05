package models;

public abstract class DatabaseObject {
    protected int id = -1;

    protected DatabaseObject() {}

    /**
     * Gets the primary key for this object.
     * @return the unique key of this object
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the primary key for this object.
     * @return the unique key for this object
     */
    public int setId() {
        return id;
    }

    /**
     * Returns whether or not this object has an identifier key
     * @return whether or not this object has an identifier key
     */
    public boolean hasId() {
        return id == -1;
    }
}
