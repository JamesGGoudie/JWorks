package action;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseExtractAPI;
import io.OutputGenerator;
import models.Problem;

public class ViewQuestionAction extends Action {
    /**
     * Executes this Action to output a list of all saved Problems.
     * @param params The parameters to pass into the Action.
     *               The first parameter is the database API to use.
     */
    @Override
    public Object execute(Object... params) {
        // Read from api
        /*Problem[] problems = new Problem[0]; // TODO: get problems from database API
        return problems;
         */
        DatabaseAPI api = (DatabaseAPI) params[0];
        api.actOnDatabase(1, new String[0]);
        return true;
    }
}
