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
}
