package models;

import java.util.List;

public abstract class ProblemSet extends DatabaseObject {

    protected int maxAttempts;


    protected ProblemSet() {
        super();
    }

    /**
     * Retrieves a list of Problems that the problem set contains.
     * @return the Problems in the problem set
     */
    public abstract List<Problem> getQuestions();

    /**
     * Returns the maximum number of attempts for this Problem Set.
     * @return the maximum number of attempts for this Problem Set. -1 represents no limit.
     */
    public int getMaximumNumberOfAttempts() {
        return maxAttempts;
    };

    /**
     * Sets the maximum number of attempts for this problem set
     * @param maxAttempts the maxmimum number of attempts for this problem set
     */
    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
