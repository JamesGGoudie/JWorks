package gui;

import javafx.scene.layout.Pane;
import models.ProblemSet;
import models.ProblemSetAttempt;
import models.Student;

public class CompleteProblemSetScreenManager extends Manager {
    private StudentInnerScreenManager homeScreenManager;
    private Pane innerPane;

    /**
     * Load a screen to attempt a problem set.
     *
     * @param innerPane The pane that new contents are loading into
     * @param student   The currently logged in Student
     * @param problemSet The problem set the student is attempting
     */
    public void showScreen(Pane innerPane, Student student, ProblemSet problemSet, StudentInnerScreenManager homeScreenManager) {
        loader = loadNewPane(loader, innerPane, "CompleteProblemSetScreen.fxml");
        CompleteProblemSetScreenController controller =
                loader.getController();
        controller.start(this, problemSet, student);
        this.homeScreenManager = homeScreenManager;
        this.innerPane = innerPane;
    }

    /**
     * Saves a problem set attempt and outputs the user's grade.
     * @param attempt the attempt to submit to the database
     */
    public void submitAttempt(ProblemSetAttempt attempt) {
        // TODO: Save to database
        int correctAnswers = attempt.getNumberofCorrectAnswers();
        int maxCorrectAnswers = attempt.getProblemSet().getQuestions().size();

        int score = 100 * correctAnswers / maxCorrectAnswers;

        interpreter.getOutputGenerator().output("You scored "
                + correctAnswers
                + " out of "
                + maxCorrectAnswers
                + " for a grade of "
                + score
                + "%.");

        returnToMainScreen();
    }

    public void returnToMainScreen() {
        homeScreenManager.showScreen(innerPane);
    }
}
