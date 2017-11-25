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
     * @param problemSet The problem set the student is attempting
     */
    public void showScreen(Pane innerPane, ProblemSet problemSet) {
        loader = loadNewPane(loader, innerPane, "CompleteProblemSetScreen.fxml");
        CompleteProblemSetScreenController controller =
                loader.getController();
        controller.start(this, problemSet, (Student) interpreter.getCurrentUser());
        this.homeScreenManager = new StudentInnerScreenManager();
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

        // Serialize the attempt and submit to command
        interpreter.executeAction(new String[] {"AddProblemSetAttemptCommand", attempt.serialize()});
        returnToMainScreen();

    }

    public void returnToMainScreen() {
        homeScreenManager.showScreen(innerPane);
    }
}
