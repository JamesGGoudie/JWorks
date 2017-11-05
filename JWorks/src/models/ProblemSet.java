package models;

import java.util.Date;
import java.util.List;

public abstract class ProblemSet extends DatabaseObject {

    protected int maxAttempts;
    protected Date startTime;
    protected Date endTime;


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
    public int getMaxAttempts() {
        return maxAttempts;
    };

    /**
     * Sets the maximum number of attempts for this problem set
     * @param maxAttempts the maxmimum number of attempts for this problem set
     */
    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
