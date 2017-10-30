package command;

public interface ICommand {
    /**
     * Executes the given command with the provided arguments.
     * @param args the arguments for the command to use
     * @return whether or not the command succeeded
     */
    boolean execute(String[] args);
}
