package action;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseDriverAPI;
import databaseAPI.DatabaseStoreAPI;
import exceptions.DatabaseInsertException;
import io.OutputGenerator;
import models.Problem;

import java.sql.Connection;
import java.sql.SQLException;

public class AddQuestionAction extends Action {
    /**
     * Executes this Action to add the given Problem object into storage.
     * @param params The parameters to pass into the Action.
     *               The first parameter is the Problem to add.
     *               The second parameter is the database API to use.
     * @return the problem added
     */
    @Override
    public Object execute(Object... params) {
        // Get the problem and add it to the database
        Problem problem = (Problem) params[0];

        // Instantiate database access
        DatabaseStoreAPI api = (DatabaseStoreAPI) params[1];
        try {
            api.actOnDatabase(problem);
            api.actOnDatabase(problem.getTags(), problem);
        } catch (DatabaseInsertException | SQLException e) {
            e.printStackTrace();
        }

        return problem;
    }
}
