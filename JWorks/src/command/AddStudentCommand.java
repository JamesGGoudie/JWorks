package command;

import action.AddStudentAction;
import databaseAPI.DatabaseAPI;
import io.OutputGen;
import models.Student;

public class AddStudentCommand extends Command {
    /**
     * Creates a new command using the specified database API method and output generator.
     *
     * @param databaseAPI  the database API instance to use with the action
     * @param outputStream the output generator to use with the command
     */
    public AddStudentCommand(DatabaseAPI databaseAPI, OutputGen outputStream) {
        super(databaseAPI, outputStream);
    }

    /**
     * Executes the given command with the provided arguments. Any outputs created are the command's output generator.
     *
     * @param args the arguments for the command to use.
     *             First argument is the user's name (first and last) as a String.
     *             Second argument is the user's email as a String.
     *             Third argument is the user's password as a String.
     *             Fourth argument is the user's student number as an integer.
     *             If a fifth argument exists, output any errors via output generator.
     * @return whether or not the command succeeded.
     */
    @Override
    public boolean execute(String[] args) {
        if (args.length != 4 && args.length != 5) {
            return false;
        }

        // Parse arguments
        String name = args[0];
        String email = args[1];
        String password = args[2];
        int studentNumber = Integer.parseInt(args[3]);

        // Create student object
        Student s = new Student(name, email, password, studentNumber);
        AddStudentAction action = new AddStudentAction();

        // Pass into action
        boolean result = (boolean) action.execute(s, databaseAPI);

        if (args.length != 4) {
            if (!result) {
                outputStream.output("Something went wrong! Does the student already exist?");
            } else {
                outputStream.output("Successfully added student!");
            }
        }
        return result;
    }
}
