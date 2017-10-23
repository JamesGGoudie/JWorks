package action;

import problem.Problem;

public class ActionParameters {

    private Problem problem;

    /**
     * Creates a new Parameter object with no information.
     */
    public ActionParameters() {

    }

    /**
     * Adds a Problem to the parameters.
     * @param problem The problem to pass in as an argument.
     */
    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    /**
     * Retrieves the Problem in the current parameter object if it exists.
     * @return the Problem in the current parameter object if it exists, null otherwise.
     */
    public Problem getProblem() {
        return problem;
    }
}