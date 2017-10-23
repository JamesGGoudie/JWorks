package action;

import io.OutputGenerator;
import problem.Problem;

public class AddQuestionAction extends Action {
    /**
     * Executes this Action to add the given Problem object into storage.
     * @param params The parameters to pass into the Action. This should contain a Problem.
     */
    @Override
    public void execute(ActionParameters params) {
        // Get the problem and add it to the database
        Problem problem = params.getProblem();

        // TODO: Access database API

        // Output success
        OutputGenerator output = new OutputGenerator();
        output.output("Question successfully added");
    }
}
