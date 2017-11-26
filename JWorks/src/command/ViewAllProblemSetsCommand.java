package command;

import action.ViewAllProblemSetsAction;
import databaseAPI.DatabaseAPI;
import io.OutputGen;
import models.Problem;
import models.ProblemSet;

import java.util.List;

public class ViewAllProblemSetsCommand extends Command {
    /**
     * Creates a new command using the specified database API method and output generator.
     *
     * @param databaseAPI  the database API instance to use with the action
     * @param outputStream the output generator to use with the command
     */
    public ViewAllProblemSetsCommand(DatabaseAPI databaseAPI, OutputGen outputStream) {
        super(databaseAPI, outputStream);
    }

    /**
     * Calls the action to get all problem sets. Outputs the results to the output generator.
     *
     * @param args the arguments for the command to use.
     *             No arguments are needed for this command.
     * @return whether or not the command succeeded
     */
    @Override
    public boolean execute(String[] args) {
        ViewAllProblemSetsAction action = new ViewAllProblemSetsAction();
        List<ProblemSet> problemSets;
        Object actionResult = action.execute(databaseAPI);

        if (actionResult instanceof List) {
            problemSets = (List<ProblemSet>) actionResult;
        } else {
            return false;
        }

        outputStream.outputPayload(problemSets);

        return true;
    }
}
