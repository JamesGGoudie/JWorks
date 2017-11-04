package command;

import databaseAPI.DatabaseAPI;
import io.OutputGen;

public abstract class Command {

    protected OutputGen outputStream;
    protected DatabaseAPI databaseAPI;

    /**
     * Creates a new command using the specified database API method and output generator.
     * @param databaseAPI the database API instance to use with the action
     * @param outputStream the output generator to use with the command
     */
    public Command(DatabaseAPI databaseAPI, OutputGen outputStream) {
        this.outputStream = outputStream;
        this.databaseAPI = databaseAPI;
    }

    /**
     * Executes the given command with the provided arguments. Any outputs created are the command's output generator.
     * @param args the arguments for the command to use
     * @return whether or not the command succeeded
     */
    public abstract boolean execute(String[] args);
}
