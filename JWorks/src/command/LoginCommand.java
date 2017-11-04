package command;

import databaseAPI.DatabaseAPI;
import io.OutputGen;

public class LoginCommand extends Command {

    public LoginCommand(DatabaseAPI api, OutputGen outputStream) {
        super(api, outputStream);
    }

    /**
     * Command to log into the application.
     * @param args the arguments for the command to use. First argument is the username, the second argument is the
     *             password.
     * @return whether or not authentication succeeded.
     */
    @Override
    public boolean execute(String[] args) {
        // Parse arguments
        if (args.length != 2) {
            return false;
        }
        
        String user = args[0];
        String password = args[1];

        // Call authentication action
        // TODO: actually authenticate
        return true;
    }
}
