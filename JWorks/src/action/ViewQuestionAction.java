package action;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseExtractAPI;
import exceptions.DatabaseSelectException;
import io.OutputGenerator;
import models.Problem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewQuestionAction extends Action {
    /**
     * Executes this Action to output a list of all saved Problems.
     * @param params The parameters to pass into the Action.
     *               The first parameter is the database API to use.
     */
    @Override
    public Object execute(Object... params) {
        // Read from API
        DatabaseExtractAPI api = (DatabaseExtractAPI) params[0];
        List<Problem> problems = new ArrayList<>();
        try {
            problems = api.actOnDatabase(problems);
        } catch (DatabaseSelectException | SQLException e) {
            e.printStackTrace();
        }

        return problems;
    }
}