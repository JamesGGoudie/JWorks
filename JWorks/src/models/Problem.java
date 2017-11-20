package models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a base problem with a question and answer
 */
public abstract class Problem extends DatabaseObject {

    // Properties
    protected final SimpleStringProperty problemProperty = new SimpleStringProperty("");
    protected final SimpleStringProperty answerProperty = new SimpleStringProperty("");
    protected int creatorID;

    /**
     * Creates a new Problem.
     */
    protected Problem() {
        super();
    }

    /**
     * Gets the question of this problem.
     * @return the question component of the problem
     */
    public abstract String getProblem();

    /**
     * Gets the answer to this problem.
     * @return The answer to the problem.
     */
    public abstract String getAnswer();

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }
}