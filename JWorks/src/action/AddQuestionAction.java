package action;

import databaseAPI.DatabaseDriverAPI;
import databaseAPI.DatabaseStoreAPI;
import io.OutputGenerator;
import models.Problem;

import java.sql.Connection;

public class AddQuestionAction extends Action {
    /**
     * Executes this Action to add the given Problem object into storage.
     * @param params The parameters to pass into the Action. This should be a Problem object.
     */
    @Override
    public Object execute(Object... params) {
        // Get the problem and add it to the database
        Problem problem = (Problem) params[0];
        String[] dbArgs = { problem.getQuestion(), problem.getAnswer() };

        // Instantiate database access
        Connection connection = DatabaseDriverAPI.connectOrCreateDataBase();
        DatabaseDriverAPI.initialize(connection);

        DatabaseStoreAPI database = new DatabaseStoreAPI();
        database.actOnDatabase(1, dbArgs);

        return problem;
    }
}
