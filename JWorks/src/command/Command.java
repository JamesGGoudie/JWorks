package command;

import io.OutputGen;

public abstract class Command {

    protected OutputGen outputStream;

    public Command(OutputGen outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Executes the given command with the provided arguments. Any outputs created are the command's output generator.
     * @param args the arguments for the command to use
     * @return whether or not the command succeeded
     */
    public abstract boolean execute(String[] args);
}
