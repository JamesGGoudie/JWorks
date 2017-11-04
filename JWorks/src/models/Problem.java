package models;

/**
 * Represents a base problem with a question and answer
 */
public abstract class Problem extends DatabaseObject {

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
    public abstract String getQuestion();

    /**
     * Gets the answer to this problem.
     * @return The answer to the problem.
     */
    public abstract String getAnswer();
}