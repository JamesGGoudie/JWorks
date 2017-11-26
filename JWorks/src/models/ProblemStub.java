package models;

public class ProblemStub extends Problem {
    /**
     * Creates a problem stub. Used for adding problems to problem sets, as only the question id is needed,
     * and not the actual details of the question.
     *
     * @param id the id of the problem to create.
     */
    public ProblemStub(int id) {
        this.setId(id);
    }

    /**
     * Gets the question of this problem.
     *
     * @return the question component of the problem. This is null, as this is just a stub problem!
     */
    @Override
    public String getProblem() {
        return null;
    }

    /**
     * Gets the answer to this problem.
     *
     * @return The answer to the problem. This is null, as this is just a stub problem!
     */
    @Override
    public String getAnswer() {
        return null;
    }
}
