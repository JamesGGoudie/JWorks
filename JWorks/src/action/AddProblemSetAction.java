package action;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseStoreAPI;
import models.ProblemSet;

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
        DatabaseAPI api = (DatabaseAPI) params[1];

        // TODO: Add problem set to database
        ProblemSet problemSet = (ProblemSet) params[0];
         // api.actOnDatabase(2, )

        return problemSet;
    }
}
