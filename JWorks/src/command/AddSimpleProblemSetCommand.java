package command;

import action.AddProblemSetAction;
import databaseAPI.DatabaseAPI;
import io.OutputGen;
import models.ProblemStub;
import models.SimpleProblemSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddSimpleProblemSetCommand extends Command {
    public AddSimpleProblemSetCommand(DatabaseAPI api, OutputGen outputStream) {
        super(api, outputStream);
    }

    /**
     * Saves a new Simple Problem Set, given a list of Problem IDs.
     * @param args the arguments for the command to use.
     *             The first argument is the String version of the Date representing the start time of the problem set.
     *              The date is formatted using SimpleDateFormat.
     *             The second argument is the String version of the Date representing the end time of the problem set.
     *              The date is formatted using SimpleDateFormat.
     *             The third argument is the number of attempts the problem set has. -1 for unlimited.
     *             The fourth argument is the number of problems to add to the problem set, denoted n.
     *             The n next arguments are problem IDs to add to the problem set.
     *             The remaining arguments are the problem set tags.
     * @return whether or not the Problem Set is created
     */
    @Override
    public boolean execute(String[] args) {
        // Create problem set
        SimpleProblemSet problemSet = new SimpleProblemSet();

        // Handle pre-problem id arguments
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(args[0]);
            endDate = dateFormat.parse(args[1]);
        } catch (ParseException e) {
            outputStream.output("Invalid date format!");
            return false;
        }

        int maxAttempts = Integer.parseInt(args[2]);
        int numProblems = Integer.parseInt(args[3]);

        // Prepopulate Problem Set information
        problemSet.setStartTime(startDate);
        problemSet.setEndTime(endDate);
        problemSet.setMaxAttempts(maxAttempts);

        // Parse problem ID arguments
        for (int i = 0; i < numProblems; i++) {
            // Create question stubs -- only the question ids are needed for the problem sets
            problemSet.addProblem(new ProblemStub(Integer.parseInt(args[i + 4])));
        }

        // Parse tags
        List<String> problemSetTags = new ArrayList<>();
        for (int i = 4 + numProblems; i < args.length; i++) {
            problemSetTags.add(args[i]);
        }

        problemSet.addTags(problemSetTags);

        AddProblemSetAction action = new AddProblemSetAction();
        Object result = action.execute(problemSet, databaseAPI);
        if (result != null) {
            outputStream.output("Problem set created");
        }

        return result != null;
    }
}
