package action;

import databaseAPI.DatabaseStoreAPI;
import models.ProblemSet;

public class AddProblemSetAction extends Action {
    /**
     * Adds the given Problem Set into storage.
     * @param params The parameters to pass into the Action. This should be the Problem Set to add.
     * @return The problem set object if it succeeds.
     */
    @Override
    public Object execute(Object... params) {
        // Connect to database
        DatabaseStoreAPI api = new DatabaseStoreAPI();


        // TODO: Add problem set to database
        ProblemSet problemSet = (ProblemSet) params[0];
         // api.actOnDatabase(2, )

        return problemSet;
    }
}
