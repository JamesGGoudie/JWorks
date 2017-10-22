package problem;

/**
 * Represents a base problem with a question and answer
 */
public abstract class Problem {
    /**
     * Gets the question of this problem.
     */
    public abstract String getQuestion();

    /**
     * Gets the answer to this problem.
     * @return The answer to the problem.
     */
    public abstract String getAnswer();
}