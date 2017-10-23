package command;

import action.Action;
import action.ActionParameters;
import action.AddQuestionAction;
import action.ViewQuestionAction;
import problem.Problem;
import problem.SingleAnswerProblem;

public class ViewProblemsCommand implements ICommand {
    /**
     * Views all problems saved in storage.
     * @param args the arguments for the command to use. No arguments are required.
     */
    @Override
    public boolean execute(String[] args) {
        // Setup action params -- none needed
        ActionParameters params = new ActionParameters();

        // Pass to appropriate action -- TODO: get instance of actions rather than creating new
        Action action =  new ViewQuestionAction();
        action.Execute(params);
        return true;
    }
}
