package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a Problem Set with a determinate set of problems
 */
public class SimpleProblemSet extends ProblemSet {

    protected List<Problem> problems;

    /**
     * Initializes a Problem Set with an empty list of Problems.
     */
    public SimpleProblemSet() {
        super();
        this.problems = new ArrayList<>();
    }

    /**
     * Initializes a Problem Set with the given list of Problems.
     * @param problems the list of Problems that the Problem Set contains
     */
    public SimpleProblemSet(List<Problem> problems) {
        super();
        this.problems = problems;
    }

    /**
     * Retrieves the determinate list of Problems contained in this Problem Set.
     * @return the list of Problems contained in this Problem Set
     */
    @Override
    public List<Problem> getQuestions() {
        return problems;
    }

    /**
     * Removes the given Problem from the Problem Set
     * @param problem the Problem to remove from the Problem Set
     * @return true if the Problem has been removed, false otherwise
     */
    public boolean removeProblem(Problem problem) {
        return problems.remove(problem);
    }

    /**
     * Adds the specified Problem to the Problem Set
     * @param problem the Problem to add to the Problem Set
     */
    public void addProblem(Problem problem) {
        problems.add(problem);
    }

    /**
     * Equality comparison.
     * @param other the other object to compare to
     * @return true iff the problem sets are equal
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ProblemSet)) {
            return false;
        }

        ProblemSet otherPs = (ProblemSet) other;

        // Must have same creator, dates, and attempts
        final boolean sameCreator = this.creatorID == otherPs.creatorID;
        final boolean sameAttempts = this.maxAttempts == otherPs.maxAttempts;
        final boolean sameDates = this.startTime.equals(otherPs.startTime) &&
                this.endTime.equals(otherPs.endTime);

        if (!(sameCreator && sameAttempts && sameDates)) {
            return false;
        }

        // Iterate through all problems and answers
        List<Problem> otherProblems = otherPs.getQuestions();
        List<Problem> theseProblems = this.getQuestions();

        if (otherProblems.size() != theseProblems.size()) {
            return false;
        }

        for (int i = 0; i < theseProblems.size(); i++) {
            Problem p1 = theseProblems.get(i);
            Problem p2 = otherProblems.get(i);

            if (!(p1.getProblem().equals(p2.getProblem()))
                    && p1.getAnswer().equals(p2.getAnswer())) {
                return false;
            }
        }

        return true;
    }
}
