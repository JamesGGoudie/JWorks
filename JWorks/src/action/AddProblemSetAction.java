package action;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseStoreAPI;
import exceptions.DatabaseInsertException;
import models.ProblemSet;

import java.sql.SQLException;

public class AddProblemSetAction extends Action {
    /**
     * Adds the given Problem Set into storage.
     * @param params The parameters to pass into the Action.
     *               The first parameter is the Problem Set to add.
     *               The second parameter is a DatabaseStoreAPI.
     * @return The problem set object if it succeeds.
     */
    @Override
    public Object execute(Object... params) {
        // Get database instance
        DatabaseStoreAPI api = (DatabaseStoreAPI) params[1];
        ProblemSet problemSet = (ProblemSet) params[0];

        try {
            api.actOnDatabase(problemSet);
        } catch (DatabaseInsertException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return problemSet;
    }
}