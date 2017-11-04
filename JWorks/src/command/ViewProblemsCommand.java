package command;

import action.Action;
import action.ViewQuestionAction;
import io.OutputGen;

public class ViewProblemsCommand extends Command {
    public ViewProblemsCommand(OutputGen outputStream) {
        super(outputStream);
    }

    /**
     * Views all problems saved in storage.
     * @param args the arguments for the command to use. No arguments are required.
     */
    @Override
    public boolean execute(String[] args) {
        // Pass to appropriate action -- TODO: get instance of actions rather than creating new
        Action action =  new ViewQuestionAction();
        action.execute(false);

        // Uncomment once we can retrieve objects from API
        //outputStream.output(problems);
        return true;
    }
}
