package gui;

import javafx.scene.layout.Pane;
import models.*;

import java.time.Instant;
import java.util.*;

public class ViewProblemSetScreenManager extends Manager {
    /**
     * Load and display the screen to view all problems.
     *
     * @param innerPane The pane that new contents are loading into
     */
    public void showScreen(Pane innerPane) {
        loader = loadNewPane(loader, innerPane, "ViewProblemSetScreen.fxml");
        ViewProblemSetScreenController controller =
                loader.getController();
        controller.start(this);
    }

    /**
     * Retrieves all problem sets that are saved, filtered by what the logged in user can see.
     * Students can only see problem sets that are within the assignment dates.
     * Instructors can see all problem sets.
     * @return the list of all problem sets that are visible to the logged in user
     */
    public List<ProblemSet> getVisibleProblemSets() {
        List<ProblemSet> problemSets = getAllProblemSets();
        List<ProblemSet> filteredProblemSets = new ArrayList<>();
        Date now = Date.from(Instant.now());

        for (ProblemSet ps : problemSets) {
            // Users can only see problem sets within range
            if (isUserStudent()) {
                if (now.after(ps.getStartTime()) && now.before(ps.getEndTime())) {
                    filteredProblemSets.add(ps);
                }
            } else {
                // instructors can see every ps
                filteredProblemSets.add(ps);
            }
        }

        return filteredProblemSets;
    }

    /**
     * Retrieves the maximum score for the user on the given problem set. -1 if it was not attempted.
     * @param problemSet the problem set to get the user's best score for
     * @return the best score of the user on the given Problem Set. -1 if it was not attempted.
     */
    public int getBestScore(ProblemSet problemSet) {
        List<ProblemSetAttempt> attempts = getStudentAttempts();
        int max = -1;

        for (ProblemSetAttempt attempt : attempts) {
            if (attempt.getProblemSet().equals(problemSet)) {
                if (attempt.getNumberofCorrectAnswers() > max) {
                    max = attempt.getNumberofCorrectAnswers();
                }
            }
        }

        if (max == -1) {
            return -1;
        }

        return (100 * max / problemSet.getQuestions().size());
    }

    /**
     * Returns whether or not the currently logged in user is a student
     * @return whether or not the currently logged in user is a student
     */
    public boolean isUserStudent() {
        return (interpreter.getCurrentUser() instanceof Student);
    }

    /**
     * Retrieves a list of all problem sets
     * @return a list of all the problem sets
     */
    private List<ProblemSet> getAllProblemSets() {
        interpreter.executeAction(new String[] {"ViewAllProblemSetsCommand"});
        return (List<ProblemSet>) interpreter.getOutputGenerator().getLastResult();

    }

    /**
     * Retrieves a list of all problem set attempts of the current student. Returns all attempts if user is instructor.
     * @return a list of all problem set attempts of the current student. All attempts if the user is an instructor.
     */
    private List<ProblemSetAttempt> getStudentAttempts() {
        interpreter.executeAction(new String[] {"ViewAllAttemptsCommand"});
        List<ProblemSetAttempt> attempts = (List<ProblemSetAttempt>) interpreter.getOutputGenerator().getLastResult();

        if (!isUserStudent()) {
            return attempts;
        }

        // Filter by current student
        List<ProblemSetAttempt> filteredAttempts = new ArrayList<>();

        for (ProblemSetAttempt attempt : attempts) {
            if (attempt.getStudent().equals(interpreter.getCurrentUser())) {
                filteredAttempts.add(attempt);
            }
        }

        return filteredAttempts;
    }

}
