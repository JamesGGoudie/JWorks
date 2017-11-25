package command;

import action.ViewAllAttemptsAction;
import databaseAPI.DatabaseAPI;
import io.OutputGen;
import models.ProblemSetAttempt;

import java.util.List;

public class ViewAllAttemptsCommand extends Command {
    /**
     * Creates a new command using the specified database API method and output generator.
     *
     * @param databaseAPI  the database API instance to use with the action
     * @param outputStream the output generator to use with the command
     */
    public ViewAllAttemptsCommand(DatabaseAPI databaseAPI, OutputGen outputStream) {
        super(databaseAPI, outputStream);
    }

    /**
     * Executes the given command with the provided arguments. Any outputs created are the command's output generator.
     *
     * @param args the arguments for the command to use. No arguments are needed.
     * @return whether or not the command succeeded
     */
    @Override
    public boolean execute(String[] args) {
        ViewAllAttemptsAction action = new ViewAllAttemptsAction();
        List<ProblemSetAttempt> attemptList = (List<ProblemSetAttempt>) action.execute(databaseAPI);
        outputStream.outputPayload(attemptList);
        return (attemptList == null);
    }
}
