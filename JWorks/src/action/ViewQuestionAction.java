package action;

import io.OutputGenerator;
import models.Problem;

public class ViewQuestionAction extends Action {
    /**
     * Executes this Action to output a list of all saved Problems.
     * @param params The parameters to pass into the Action. There are no required parameters for this method.
     */
    @Override
    public void execute(ActionParameters params) {
        // Read from api
        Problem[] problems = new Problem[0]; // TODO: get problems from database API

        // Output all questions - TODO: get instance of output generator rather than instantiating it
        OutputGenerator output = new OutputGenerator();

        for (Problem p : problems) {
            output.output("Q: " + p.getQuestion());
            output.output("A: " + p.getAnswer());
        }
    }
}
