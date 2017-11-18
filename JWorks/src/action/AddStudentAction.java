package action;

import databaseAPI.DatabaseStoreAPI;
import exceptions.DatabaseInsertException;
import models.Student;

import java.sql.SQLException;

public class AddStudentAction extends Action {
    /**
     * Executes this Action with the given parameters.
     *
     * @param params The parameters to pass into the Action.
     *               The first parameter is Student object to add.
     *               The second parameter is the database API to use.
     * @return the output of the action. True if successful. False otherwise.
     */
    @Override
    public Object execute(Object... params) {
        // Parse parameters
        Student student = (Student) params[0];
        DatabaseStoreAPI api = (DatabaseStoreAPI) params[1];

        // Attempt to insert into database
        try {
            api.actOnDatabase(student);
        } catch (DatabaseInsertException|SQLException e) {
            // Unsuccessful upon exception
            return false;
        }

        // Reached only if successful
        return true;
    }
}
