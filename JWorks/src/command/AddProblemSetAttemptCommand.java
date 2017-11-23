package command;

import action.AddProblemSetAttemptAction;
import databaseAPI.DatabaseAPI;
import io.OutputGen;
import models.Problem;
import models.ProblemSetAttempt;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class AddProblemSetAttemptCommand extends Command {
    /**
     * Creates a new command using the specified database API method and output generator.
     *
     * @param databaseAPI  the database API instance to use with the action
     * @param outputStream the output generator to use with the command
     */
    public AddProblemSetAttemptCommand(DatabaseAPI databaseAPI, OutputGen outputStream) {
        super(databaseAPI, outputStream);
    }

    /**
     * Executes the given command with the provided arguments. Any outputs created are the command's output generator.
     * Saves the problem set attempt.
     *
     * @param args the arguments for the command to use. The first and only argument is a serialized version of the
     *             problem set attempt object.
     * @return whether or not the command succeeded
     */
    @Override
    public boolean execute(String[] args) {
        // Init properties
        AddProblemSetAttemptAction action = new AddProblemSetAttemptAction();
        ProblemSetAttempt attempt;

        // Deserialize string argument to get problem set attempt
        try {
            byte[] byteArray = args[0].getBytes();
            ByteArrayInputStream byteInput = new ByteArrayInputStream(byteArray);
            ObjectInputStream in = new ObjectInputStream(byteInput);
            attempt = (ProblemSetAttempt) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }

        // Send to action
        Object result = action.execute(attempt, databaseAPI);
        return (result == null);
    }
}
