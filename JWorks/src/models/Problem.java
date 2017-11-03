package models;

/**
 * Represents a base problem with a question and answer
 */
public abstract class Problem {

    protected int id;

    /**
     * Gets the question of this problem.
     * @return the question component of the problem
     */
    public abstract String getQuestion();

    /**
     * Gets the answer to this problem.
     * @return The answer to the problem.
     */
    public abstract String getAnswer();

    /**
     * Gets the problem ID for this problem.
     * @return the unique ID of this problem
     */
    public int getId() {
        return id;
    }
}