package gui;

import javafx.scene.layout.Pane;
import models.ProblemSet;
import models.ProblemSetAttempt;
import models.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ViewProblemSetStatisticsScreenManager extends Manager {

    private ProblemSet problemSet;

    /**
     * Initializes a manager for a statistics screen that displays statistics for the given problem set.
     * @param problemSet the problem set to view statistics for
     */
    public ViewProblemSetStatisticsScreenManager(ProblemSet problemSet) {
        this.problemSet = problemSet;
    }

    /**
     * Load and display the screen to view problem set statistics.
     *
     * @param innerPane The pane that new contents are loading into
     */
    public void showScreen(Pane innerPane) {
        loader = loadNewPane(loader, innerPane, "ViewProblemSetStatisticsScreen.fxml");
        ViewProblemSetStatisticsScreenController controller =
                loader.getController();
        controller.start(this);
    }

    /**
     * Returns a list of rows that contain all the attempts for this problem set.
     * @return a list of rows that contain all the attempts for this problem set.
     */
    public List<ViewProblemSetStatisticsRow> getRows() {
        interpreter.executeAction(new String[] {"ViewAllAttemptsCommand"});
        List<ProblemSetAttempt> attempts = (List<ProblemSetAttempt>) interpreter.getOutputGenerator().getLastResult();

        if (attempts == null) {
            return Collections.EMPTY_LIST;
        }

        // Create set to keep track of students that participated
        HashSet<Student> studentSet = new HashSet<>();

        // Filter by problem set
        List<ProblemSetAttempt> filteredAttempts = new ArrayList<>();

        for (ProblemSetAttempt attempt : attempts) {
            if (attempt.getProblemSet().equals(problemSet)) {
                filteredAttempts.add(attempt);
                studentSet.add(attempt.getStudent());
            }
        }

        // Create rows from the student set
        List<ViewProblemSetStatisticsRow> rows = new ArrayList<>();

        for (Student student : studentSet) {
            ViewProblemSetStatisticsRow row = new ViewProblemSetStatisticsRow(student, problemSet);
            row.addAttempts(filteredAttempts);
        }

        return rows;
    }

    public ProblemSet getProblemSet() {
        return problemSet;
    }
}
