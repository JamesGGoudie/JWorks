package gui;

import javafx.scene.layout.Pane;
import models.ProblemSet;
import models.ProblemSetAttempt;
import models.Student;

import java.util.*;

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
        HashSet<Integer> studentSet = new HashSet<>();
        HashMap<Integer, Student> studentNumToStudent = new HashMap<>();

        // Filter by problem set
        List<ProblemSetAttempt> filteredAttempts = new ArrayList<>();

        for (ProblemSetAttempt attempt : attempts) {
            if (attempt.getProblemSet().equals(problemSet)) {
                filteredAttempts.add(attempt);
                studentSet.add(attempt.getStudent().getStudentNumber());
                studentNumToStudent.put(attempt.getStudent().getStudentNumber(), attempt.getStudent());
            }
        }

        // Create rows from the student set
        List<ViewProblemSetStatisticsRow> rows = new ArrayList<>();

        for (Integer studentNum : studentSet) {
            ViewProblemSetStatisticsRow row = new ViewProblemSetStatisticsRow(studentNumToStudent.get(studentNum), problemSet);
            row.addAttempts(filteredAttempts);
            rows.add(row);
        }

        return rows;
    }

    public ProblemSet getProblemSet() {
        return problemSet;
    }
}
