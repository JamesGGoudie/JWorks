package action;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseDriverAPI;
import databaseAPI.DatabaseStoreAPI;
import io.OutputGenerator;
import models.Problem;

import java.sql.Connection;

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
        String[] dbArgs = { problem.getQuestion(), problem.getAnswer() };

        // Instantiate database access
        DatabaseAPI api = (DatabaseAPI) params[1];
        api.actOnDatabase(1, dbArgs);

        return problem;
    }
}
