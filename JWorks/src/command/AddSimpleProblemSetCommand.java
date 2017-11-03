package command;

import action.ActionParameters;
import action.AddProblemSetAction;
import models.SimpleProblemSet;

public class AddSimpleProblemSetCommand implements ICommand {
    /**
     * Saves a new Simple Problem Set, given a list of Problem IDs.
     * @param args the arguments for the command to use. This should be a list of Problem IDs to add the Problem set.
     * @return whether or not the Problem Set is created
     */
    @Override
    public boolean execute(String[] args) {
        // Create problem set
        SimpleProblemSet problemSet = new SimpleProblemSet();

        for (String id : args) {
            // TODO: Add problems via id
            // problemSet.addProblem()
        }

        ActionParameters params = new ActionParameters();
        params.setProblemSet(problemSet);

        AddProblemSetAction action = new AddProblemSetAction();
        action.execute(params);
        return true;
    }
}
