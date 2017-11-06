package command;

import action.Action;
import action.AddQuestionAction;
import databaseAPI.DatabaseAPI;
import io.OutputGen;
import models.Problem;
import models.SingleAnswerProblem;

public class AddSimpleProblemCommand extends Command {

    public AddSimpleProblemCommand(DatabaseAPI api, OutputGen outputStream) {
        super(api, outputStream);
    }

    /**
     * Given a question and answer, generates a Problem and adds it to storage.
     * @param args the arguments for the command to use. The first argument is the question and the second should be
     *             the answer
     */
    @Override
    public boolean execute(String[] args) {
        // Check valid args
        if (args.length != 2) {
            return false;
        }

        // Generate problem for params
        String question = args[0];
        String answer = args[1];
        Problem problem = new SingleAnswerProblem(question, answer);

        // Pass to appropriate action -- TODO: get instance of actions rather than creating new
        Action action =  new AddQuestionAction();
        action.execute(problem, databaseAPI);

        outputStream.output("Question successfully added.");
        return true;
    }
}
