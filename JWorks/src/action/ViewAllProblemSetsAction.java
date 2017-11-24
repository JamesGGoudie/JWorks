package action;

import databaseAPI.DatabaseExtractAPI;
import models.ProblemSet;

import java.util.List;

public class ViewAllProblemSetsAction extends Action {
    /**
     * Executes this Action with the given parameters.
     *
     * @param params The parameters to pass into the Action.
     *               The first parameter is the database extract used to get the problem sets.
     * @return a list of Problem Sets.
     */
    @Override
    public Object execute(Object... params) {
        // Parse arguments
        DatabaseExtractAPI api = (DatabaseExtractAPI) params[0];
        
        // Call database
        List<ProblemSet> problemSets = null; // = api.
        return problemSets;
    }
}
