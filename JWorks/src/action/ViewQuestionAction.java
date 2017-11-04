package action;

import databaseAPI.DatabaseExtractAPI;
import io.OutputGenerator;
import models.Problem;

public class ViewQuestionAction extends Action {
    /**
     * Executes this Action to output a list of all saved Problems.
     * @param params The parameters to pass into the Action. There are no required parameters for this method.
     */
    @Override
    public Object execute(Object... params) {
        // Read from api
        /*Problem[] problems = new Problem[0]; // TODO: get problems from database API
        return problems;
         */
        DatabaseExtractAPI api = new DatabaseExtractAPI();
        api.actOnDatabase(1, new String[0]);
        return true;
    }
}
