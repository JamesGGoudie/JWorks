package gui;

import models.Problem;
import models.ProblemSet;

import java.util.List;

/**
 * View problems manager to handle the viewing of problems in a particular problem set
 */
public class ViewProblemSetProblemsManager extends ViewProblemsManager {
    private ProblemSet problemSet;

    /**
     * Creates a new view problems manager with the given problem set to display problems for.
     * @param problemSet the problem set to view
     */
    public ViewProblemSetProblemsManager(ProblemSet problemSet) {
        super();
        this.problemSet = problemSet;
    }

    @Override
    public List<Problem> getProblems() {
        return problemSet.getQuestions();
    }
}
