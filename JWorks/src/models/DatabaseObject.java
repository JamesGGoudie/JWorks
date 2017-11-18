package models;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class DatabaseObject {
    protected final SimpleIntegerProperty idProperty = new SimpleIntegerProperty(-1);

    protected DatabaseObject() {}

    /**
     * Gets the primary key for this object.
     * @return the unique key of this object
     */
    public int getId() {
        return idProperty.get();
    }

    /**
     * Sets the primary key for this object
     * @param id the new primary key for this object
     */
    public void setId(int id)
    {
        idProperty.set(id);
    }

    /**
     * Returns whether or not this object has an identifier key
     * @return whether or not this object has an identifier key
     */
    public boolean hasId() {
        return idProperty.get() == -1;
    }
}
