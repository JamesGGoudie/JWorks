package command;

import action.Action;
import action.ViewQuestionAction;
import databaseAPI.DatabaseAPI;
import io.OutputGen;
import models.Problem;

import java.util.List;

public class ViewProblemsCommand extends Command {
    public ViewProblemsCommand(DatabaseAPI api, OutputGen outputStream) {
        super(api, outputStream);
    }

    /**
     * Views all problems saved in storage.
     * @param args the arguments for the command to use. No arguments are required.
     */
    @Override
    public boolean execute(String[] args) {
        // Pass to appropriate action -- TODO: get instance of actions rather than creating new
        Action action =  new ViewQuestionAction();
        List<Problem> problems = (List<Problem>) action.execute(databaseAPI);

        // Uncomment once we can retrieve objects from API
        outputStream.outputPayload(problems);

        return (problems.size() > 0);
    }
}
