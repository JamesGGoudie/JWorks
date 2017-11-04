package command;

import action.AddProblemSetAction;
import io.OutputGen;
import models.SimpleProblemSet;

public class AddSimpleProblemSetCommand extends Command {
    public AddSimpleProblemSetCommand(OutputGen outputStream) {
        super(outputStream);
    }

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

        AddProblemSetAction action = new AddProblemSetAction();
        action.execute(problemSet);
        return true;
    }
}
