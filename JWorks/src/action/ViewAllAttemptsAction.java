package action;

import databaseAPI.DatabaseExtractAPI;
import exceptions.DatabaseSelectException;

import java.sql.SQLException;

public class ViewAllAttemptsAction extends Action {
    /**
     * Executes this Action with the given parameters.
     *
     * @param params The parameters to pass into the Action.
     *               The first parameter is the database extract API used.
     * @return a List of all problem set attempts, returns null if otherwise
     */
    @Override
    public Object execute(Object... params) {
        // Parse params
        DatabaseExtractAPI api = (DatabaseExtractAPI) params[0];

        try {
            return api.getAllAttempts();
        } catch (SQLException | DatabaseSelectException e) {
            return null;
        }
    }
}
