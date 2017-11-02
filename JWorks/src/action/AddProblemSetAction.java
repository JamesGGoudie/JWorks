package action;

import problem.ProblemSet;

public class AddProblemSetAction extends Action {
    @Override
    public void execute(ActionParameters params) {
        // TODO: Connect to database

        // TODO: Add problem set to database
        ProblemSet problemSet = params.getProblemSet();
    }
}
