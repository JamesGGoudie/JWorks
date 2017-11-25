package action;

import databaseAPI.DatabaseStoreAPI;
import exceptions.DatabaseInsertException;
import models.Problem;
import models.ProblemSetAttempt;

public class AddProblemSetAttemptAction extends Action {
    /**
     * Executes this Action with the given parameters. Saves the given problem set attempt into the database.
     *
     * @param params The parameters to pass into the Action.
     *               The first parameter is the problem set attempt model.
     *               The second parameter is the database store api to use.
     * @return true if the addition is successful
     */
    @Override
    public Object execute(Object... params) {
        // Parse args
        ProblemSetAttempt attempt = (ProblemSetAttempt) params[0];
        DatabaseStoreAPI api = (DatabaseStoreAPI) params[1];

        // Call database api to store
        try {
            api.actOnDatabase(attempt);
        } catch (DatabaseInsertException e) {
            return false;
        }
        return true;
    }
}
