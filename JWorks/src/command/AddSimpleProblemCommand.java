package command;

import action.Action;
import action.ActionParameters;
import action.AddQuestionAction;
import models.Problem;
import models.SingleAnswerProblem;

public class AddSimpleProblemCommand implements ICommand {
    /**
     * Given a question and answer, generates a Problem and adds it to storage.
     * @param args the arguments for the command to use. The first argument is the question and the second should be
     *             the answer
     */
    @Override
    public boolean execute(String[] args) {
        // Check valid args
        if (args.length != 2) {
            return false;
        }

        // Generate problem for params
        String question = args[0];
        String answer = args[1];
        Problem problem = new SingleAnswerProblem(question, answer);

        // Setup action params
        ActionParameters params = new ActionParameters();
        params.setProblem(problem);

        // Pass to appropriate action -- TODO: get instance of actions rather than creating new
        Action action =  new AddQuestionAction();
        action.execute(params);
        return true;
    }
}
